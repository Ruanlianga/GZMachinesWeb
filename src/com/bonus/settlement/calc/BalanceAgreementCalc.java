package com.bonus.settlement.calc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bonus.settlement.beans.SettlementDetailsBean;


public class BalanceAgreementCalc {

	Logger logger = Logger.getLogger(BalanceAgreementCalc.class);

	private String agreementCode;

	private float leaseMoney;
	
	private String settlementDate;

	private List<BalanceAgreementModelResultBean> results = new ArrayList<BalanceAgreementModelResultBean>();
	
	public BalanceAgreementCalc(String settlementDate) {
		super();
		this.settlementDate = settlementDate;
	}

	public void calc(List<SettlementDetailsBean> collars, List<SettlementDetailsBean> backs) throws Exception {
		BalanceAgreementModelGroup group = new BalanceAgreementModelGroup(collars, backs);
		List<BalanceAgreementModelBean> beans = group.groupByModelId();
		for (BalanceAgreementModelBean b : beans) {
			leaseMoney += calc(b);
		}
	}

	private float calc(BalanceAgreementModelBean b) throws Exception {
		IModelCalc calc = null;
//		List<PaidSettlementDetailsBean> list = new ArrayList<PaidSettlementDetailsBean>();
	
		if(b.isCount()) {
			calc = new BalanceAgreementNumberModelCalc(b,settlementDate);
		} else {
			calc = new BalanceAgreementCodeModelCalc(b,settlementDate);
		}
		List<BalanceAgreementModelResultBean> result = calc.calc();
		float sum = 0;
//		Collections.sort(result, new ResultDateComparator());
		for (BalanceAgreementModelResultBean r : result) {
			logger.warn("###################################################################################");
			logger.warn("协议编码：" + r.getAgreementCode() + ",规格型号：" + r.getTypeId());
			logger.warn("出租日期：" + r.getLeaseDate()+",退租日期：" + r.getReturnDate()+",租赁天数：" + r.getDays());
			logger.warn("租赁单价：" + r.getLeasePrice()+",租赁数量：" + r.getLeaseNum() +",退租数量"+ r.getReturnNum() +",租赁费用："+r.getLeaseMoney()+",是否计数"+r.isCount());
			logger.warn("###################################################################################");
			sum += r.getLeaseMoney();
			results.add(r);
		}
		return sum;
	}
	
	public List<BalanceAgreementModelResultBean> getResults() {
		return results;
	}

	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public float getLeaseMoney() {
		return leaseMoney;
	}
	

}
