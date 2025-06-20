package com.bonus.settlement.calc;

import java.util.ArrayList;
import java.util.List;

import com.bonus.settlement.beans.SettlementDetailsBean;

public class BalanceAgreementModelBean {
	
	private String agreementCode;
	
	private String maModelId;
	
	private String maCode;
	
	private boolean isCount = false;
	
	private List<SettlementDetailsBean> collars = new ArrayList<SettlementDetailsBean>();
	
	private List<SettlementDetailsBean> backs = new ArrayList<SettlementDetailsBean>();

	public String getMaCode() {
		return maCode;
	}

	public void setMaCode(String maCode) {
		this.maCode = maCode;
	}

	public boolean isCount() {
		return isCount;
	}

	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}

	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public String getMaModelId() {
		return maModelId;
	}

	public void setMaModelId(String maModelId) {
		this.maModelId = maModelId;
	}

	public List<SettlementDetailsBean> getCollars() {
		return collars;
	}
	
	public void addCollar(SettlementDetailsBean bean){
		this.collars.add(bean);
	}
	
	public void addBack(SettlementDetailsBean bean){
		this.backs.add(bean);
	}

	public void setCollars(List<SettlementDetailsBean> collars) {
		this.collars = collars;
	}

	public List<SettlementDetailsBean> getBacks() {
		return backs;
	}

	public void setBacks(List<SettlementDetailsBean> backs) {
		this.backs = backs;
	}


	
}
