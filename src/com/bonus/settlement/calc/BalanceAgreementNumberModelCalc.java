package com.bonus.settlement.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.settlement.beans.SettlementDetailsBean;

public class BalanceAgreementNumberModelCalc implements IModelCalc {

	private Logger logger = Logger.getLogger(BalanceAgreementNumberModelCalc.class);

	private String settlementDate;

	private List<SettlementDetailsBean> collars;

	private List<SettlementDetailsBean> backs;

	public BalanceAgreementNumberModelCalc(BalanceAgreementModelBean bean, String settlementDate) {
		super();
		this.collars = bean.getCollars();
		this.backs = bean.getBacks();
		this.settlementDate = settlementDate;
	}

	public List<BalanceAgreementModelResultBean> calc() throws Exception {
		Collections.sort(collars, new CollarDateComparator());
		Collections.sort(backs, new BackDateComparator());

		Map<SettlementDetailsBean, SettlementDetailsBean> maps = new LinkedHashMap<SettlementDetailsBean, SettlementDetailsBean>();
		for (int i = 0; i < collars.size(); i++) {
			SettlementDetailsBean bean = collars.get(i);
			Map<SettlementDetailsBean, SettlementDetailsBean> tmps = matchCollarAndBack(bean);
			maps.putAll(tmps);
		}
		return calc(maps);
	}

	private Map<SettlementDetailsBean, SettlementDetailsBean> matchCollarAndBack(
			SettlementDetailsBean collar) throws Exception {

		float collorNum = parse(collar.getLeaseNum());

		Map<SettlementDetailsBean, SettlementDetailsBean> tmps = new LinkedHashMap<SettlementDetailsBean, SettlementDetailsBean>();
		float backNumSum = 0;
		int size = backs.size();
		for (int i = 0; i < size; i++) {
			SettlementDetailsBean bean = backs.remove(0);
			float backNum = parse(bean.getReturnNum());
			backNumSum += backNum;
			if (collorNum == backNumSum) {
				SettlementDetailsBean key = (SettlementDetailsBean) BeanUtils.cloneBean(collar);
				key.setLeaseNum(backNum + "");
				tmps.put(key, bean);
				break;
			} else if (collorNum < backNumSum) {
				List<SettlementDetailsBean> dd = splitBackByNum(bean, collorNum, backNumSum);
				SettlementDetailsBean key = (SettlementDetailsBean) BeanUtils.cloneBean(collar);
				key.setLeaseNum(dd.get(0).getReturnNum());
				tmps.put(key, dd.get(0));
				backs.add(0, dd.get(1));
				break;
			} else {
				SettlementDetailsBean key = (SettlementDetailsBean) BeanUtils.cloneBean(collar);
				key.setLeaseNum(backNum + "");
				tmps.put(key, bean);
				
			}
		}
		// 产生丢失的费用
		float remainNum = collorNum - backNumSum;
		if (remainNum > 0) {
			SettlementDetailsBean key = (SettlementDetailsBean) BeanUtils.cloneBean(collar);
			key.setLeaseNum(remainNum + "");
			tmps.put(key, null);
		}
		return tmps;
	}

	private List<SettlementDetailsBean> splitBackByNum(SettlementDetailsBean bean, float collorNum,
			float backNumSum) throws Exception {
		List<SettlementDetailsBean> tmps = new ArrayList<SettlementDetailsBean>();
		SettlementDetailsBean backBean1 = (SettlementDetailsBean) BeanUtils.cloneBean(bean);
		float backNum1 = parse(bean.getReturnNum()) - (backNumSum - collorNum);
		backBean1.setReturnNum(backNum1+"");
		SettlementDetailsBean backBean2 = (SettlementDetailsBean) BeanUtils.cloneBean(bean);
		float backNum2 = backNumSum - collorNum;
		backBean2.setReturnNum(backNum2 + "");
		tmps.add(backBean1);
		tmps.add(backBean2);
		return tmps;
	}

	private BalanceAgreementModelResultBean calcCost(SettlementDetailsBean collarBean,
			SettlementDetailsBean backBean) {
		BalanceAgreementModelResultBean resultBean = new BalanceAgreementModelResultBean();
		if (backBean == null) {
			resultBean.setCollar(collarBean);
			String deviceName = collarBean.getDeviceName();
			String deviceModel = collarBean.getDeviceModel();
			String deviceUnit = collarBean.getDeviceUnit();
			String projectName = collarBean.getProjectName();
			int days = DateTimeHelper.calcDate(collarBean.getLeaseDate(), settlementDate) + 1;
			float leasePrice = parse(collarBean.getLeasePrice());
			float buyPrice = parse(collarBean.getBuyPrice());
			float leaseNum = parse(collarBean.getLeaseNum());
			float leaseMoney = leasePrice * leaseNum * days;
			resultBean.setDeviceName(deviceName);
			resultBean.setDeviceModel(deviceModel);
			resultBean.setDeviceUnit(deviceUnit);
			resultBean.setLeaseMoney(leaseMoney);
			resultBean.setDays(days);
			resultBean.setProjectName(projectName);
			resultBean.setLeasePrice(leasePrice);
			resultBean.setBuyPrice(buyPrice);
			resultBean.setLeaseNum(leaseNum);
			resultBean.setReturnNum(0);
			resultBean.setReturnDate(settlementDate);
			resultBean.setSettlementDate(settlementDate);
			resultBean.setIsCount(true);
			resultBean.setAgreementCode(collarBean.getAgreementId());
		} else {
			resultBean.setAgreementCode(collarBean.getAgreementCode());
			resultBean.setCollar(collarBean);
			String deviceName = collarBean.getDeviceName();
			String deviceModel = collarBean.getDeviceModel();
			String deviceUnit = collarBean.getDeviceUnit();
			String projectName = collarBean.getProjectName();
			int days = DateTimeHelper.calcDate(collarBean.getLeaseDate(), backBean.getReturnDate()) + 1;
			// backs
			float leasePrice = parse(collarBean.getLeasePrice());
			float buyPrice = parse(collarBean.getBuyPrice());
			float leaseNum = parse(collarBean.getLeaseNum());
			float returnNum = parse(backBean.getReturnNum());
			float leaseMoney = leasePrice * leaseNum * days;
			if (days < 1) {
				days -= 2;
				leaseMoney = 0;
			}
			resultBean.setDeviceName(deviceName);
			resultBean.setDeviceModel(deviceModel);
			resultBean.setDeviceUnit(deviceUnit);
			resultBean.setLeaseMoney(leaseMoney);
			resultBean.setBuyPrice(buyPrice);
			resultBean.setDays(days);
			resultBean.setProjectName(projectName);
			resultBean.setSettlementDate(settlementDate);
			resultBean.setLeasePrice(leasePrice);
			resultBean.setLeaseNum(leaseNum);
			resultBean.setReturnNum(returnNum);
			resultBean.setAgreementCode(collarBean.getAgreementId());
			resultBean.setBack(backBean);
		}
		resultBean.setIsCount(true);
		return resultBean;
	}

	// 租赁结算清单
	private List<BalanceAgreementModelResultBean> calc(Map<SettlementDetailsBean, SettlementDetailsBean> maps) {
		List<BalanceAgreementModelResultBean> bacrList = new ArrayList<BalanceAgreementModelResultBean>();
		// 获取出租
		Set<SettlementDetailsBean> collarSet = maps.keySet();
		for (SettlementDetailsBean collarBean : collarSet) {
			SettlementDetailsBean backBean = maps.get(collarBean);
			BalanceAgreementModelResultBean tmps = calcCost(collarBean, backBean);
			bacrList.add(tmps);
		}
		return bacrList;
	}

	private float parse(String num) {
		if(StringHelper.isEmpty(num)) {
			num="0";
		}
		return Float.parseFloat(num);
	}

}
