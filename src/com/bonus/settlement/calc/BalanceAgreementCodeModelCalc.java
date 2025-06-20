package com.bonus.settlement.calc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.settlement.beans.SettlementDetailsBean;

public class BalanceAgreementCodeModelCalc implements IModelCalc {

	private Logger logger = Logger.getLogger(BalanceAgreementCodeModelCalc.class);

	private String settlementDate;

	private List<SettlementDetailsBean> collars;

	private List<SettlementDetailsBean> backs;
	
	public BalanceAgreementCodeModelCalc(BalanceAgreementModelBean bean,String settlementDate) {
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
			SettlementDetailsBean collar = collars.get(i);
			SettlementDetailsBean back = findBackByCode(collar);
			maps.put(collar, back);
		}
		return calc(maps);
	}


	// 租赁结算清单
	private List<BalanceAgreementModelResultBean> calc(
			Map<SettlementDetailsBean, SettlementDetailsBean> maps) throws ParseException {
		List<BalanceAgreementModelCodeResultBean> bacrList = new ArrayList<BalanceAgreementModelCodeResultBean>();
		// 获取出租
		Set<SettlementDetailsBean> collarSet = maps.keySet();
		for (SettlementDetailsBean collarBean : collarSet) {
			SettlementDetailsBean back = maps.get(collarBean);
			BalanceAgreementModelCodeResultBean r = calcResult(collarBean, back);
			bacrList.add(r);
		}
		return groupAndCombine(bacrList);
	}
	
	//同一个规格、出租时间、退组时间租赁费用合并
	private List<BalanceAgreementModelResultBean> groupAndCombine(List<BalanceAgreementModelCodeResultBean> bacrList) {
			
		Map<String,List<BalanceAgreementModelCodeResultBean>> maps = new HashMap<String,List<BalanceAgreementModelCodeResultBean>>();
		for(BalanceAgreementModelCodeResultBean bean : bacrList) {
			String key = bean.getTypeId() + bean.getLeaseDate() + bean.getReturnDate();
			
			List<BalanceAgreementModelCodeResultBean> tmps = maps.get(key);
			if(tmps == null){
				tmps = new ArrayList<BalanceAgreementModelCodeResultBean>();
			}
			tmps.add(bean);
			maps.put(key, tmps);
						
		}
		
		Set<String> keys = maps.keySet();
		List<BalanceAgreementModelResultBean> list = new ArrayList<BalanceAgreementModelResultBean>();
		for(String key : keys){
			List<BalanceAgreementModelCodeResultBean> tmps = maps.get(key);
			BalanceAgreementModelResultBean b = combine(tmps);
			b.setIsCount(false);
			list.add(b);
		}
		return list;
	}
	
	
	private BalanceAgreementModelResultBean combine(List<BalanceAgreementModelCodeResultBean> tmps){
		BalanceAgreementModelResultBean f = new BalanceAgreementModelResultBean();
		float leaseNum = 0;
		float leaseMoney = 0;
		float returnNum = 0;
		boolean tf = true;
		for(BalanceAgreementModelCodeResultBean b: tmps) {
			if(tf){
				f.setAgreementCode(b.getAgreementCode());
				f.setCollar(b.getCollar());
				f.setBack(b.getBack());
				f.setDeviceModel(b.getDeviceModel());
				f.setDeviceName(b.getDeviceName());
				f.setDeviceUnit(b.getDeviceUnit());
				f.setDays(b.getDays());
				f.setSettlementDate(settlementDate);
				f.setLeasePrice(b.getLeasePrice());
				f.setBuyPrice(b.getBuyPrice());
				f.setProjectName(b.getProjectName());
				f.setReturnDate(b.getReturnDate());
				tf = false;
			}
			leaseNum += 1;
			if(b.getBack() != null){
				returnNum += 1;
			}
			leaseMoney += b.getLeaseMoney();
		}
		f.setLeaseNum(leaseNum);
		f.setReturnNum(returnNum);
		f.setLeaseMoney(leaseMoney);
		return f;
	}

	private BalanceAgreementModelCodeResultBean calcResult(SettlementDetailsBean collarBean,SettlementDetailsBean back) throws ParseException {
		BalanceAgreementModelCodeResultBean bacrBean = new BalanceAgreementModelCodeResultBean();
		if (back == null) {
			bacrBean.setCollar(collarBean);
			String deviceName = collarBean.getDeviceName();
			String deviceModel = collarBean.getDeviceModel();
			String deviceUnit = collarBean.getDeviceUnit();
			String projectName = collarBean.getProjectName();
			  if(StringHelper.isEmpty(settlementDate)){
					settlementDate = DateTimeHelper.getNowDate();
				}	
			int days = DateTimeHelper.calcDate(collarBean.getLeaseDate(), settlementDate) + 1;
			float leasePrice = parse(collarBean.getLeasePrice());
			float buyPrice = parse(collarBean.getBuyPrice());
			float leaseMoney = leasePrice * days;
			bacrBean.setDeviceName(deviceName);
			bacrBean.setDeviceModel(deviceModel);
			bacrBean.setDeviceUnit(deviceUnit);
			bacrBean.setLeaseMoney(leaseMoney);
			bacrBean.setBuyPrice(buyPrice);
			bacrBean.setDays(days);
			bacrBean.setLeasePrice(leasePrice);
			bacrBean.setProjectName(projectName);
			bacrBean.setReturnDate(settlementDate);
			bacrBean.setSettlementDate(settlementDate);
			bacrBean.setAgreementCode(collarBean.getAgreementId());
		} else {
			bacrBean.setAgreementCode(collarBean.getAgreementCode());
			bacrBean.setCollar(collarBean);
			String deviceName = collarBean.getDeviceName();
			String deviceModel = collarBean.getDeviceModel();
			String deviceUnit = collarBean.getDeviceUnit();
			String projectName = collarBean.getProjectName();
			int days = DateTimeHelper.calcDate(collarBean.getLeaseDate(), back.getReturnDate()) + 1;
			float leasePrice = parse(collarBean.getLeasePrice());
			float buyPrice = parse(collarBean.getBuyPrice());
			float leaseMoney = leasePrice * days;
			bacrBean.setDeviceName(deviceName);
			bacrBean.setDeviceModel(deviceModel);
			bacrBean.setDeviceUnit(deviceUnit);
			bacrBean.setBuyPrice(buyPrice);
			bacrBean.setLeaseMoney(leaseMoney);
			bacrBean.setDays(days);
			bacrBean.setProjectName(projectName);
			bacrBean.setSettlementDate(settlementDate);
			bacrBean.setLeasePrice(leasePrice);
			bacrBean.setAgreementCode(collarBean.getAgreementId());
			bacrBean.setBack(back);
		}
		return bacrBean;
	}
	
	private float parse(String num) {
		if(StringHelper.isEmpty(num)){
			num ="0";
		}
		return Float.parseFloat(num);
	}


	private SettlementDetailsBean findBackByCode(SettlementDetailsBean collar) throws Exception {
		int size = backs.size();
		String leaseCode = collar.getDeviceCode();
		for (int i = 0; i < size; i++) {
			SettlementDetailsBean bean = backs.get(i);
			String backCode = bean.getDeviceCode();
			if(leaseCode.equals(backCode)){
				backs.remove(i);
				return bean;
			}
		}
		return null;
	}

}
