package com.bonus.settlement.calc;

import com.bonus.settlement.beans.SettlementDetailsBean;

public class BalanceAgreementModelResultBean {

	private String id;
	
	private String agreementCode;
	
	private String agreementId;

	private SettlementDetailsBean collar;

	private SettlementDetailsBean back;
	
	private boolean isCount = false;
	
	private float leaseMoney;

	private float lossMoney;

	private int days;

	private float leasePrice;// 单价

	private float buyPrice;//原值
	
	private String projectName;
	
	private float leaseNum;
	
	private float returnNum;
	
	private String returnDate;
	
	private String deviceName;
	
	private String deviceModel;
	
	private String deviceUnit;
	
	private String deviceCode;
	
	private String leaseTotal;
	
	private float lossNum; 
	
	private String settlementDate; 
	
	private String lltotal;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getLeaseTotal() {
		return leaseTotal;
	}

	public void setLeaseTotal(String leaseTotal) {
		this.leaseTotal = leaseTotal;
	}

	public boolean isCount() {
		return isCount;
	}

	public void setIsCount(boolean isCount) {
		this.isCount = isCount;
	}

	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public SettlementDetailsBean getCollar() {
		return collar;
	}

	public void setCollar(SettlementDetailsBean collar) {
		this.collar = collar;
	}

	public SettlementDetailsBean getBack() {
		return back;
	}

	public void setBack(SettlementDetailsBean back) {
		this.back = back;
	}

	public float getLeaseMoney() {
		return leaseMoney;
	}

	public void setLeaseMoney(float leaseMoney) {
		this.leaseMoney = leaseMoney;
	}

	public float getLossMoney() {
		return lossMoney;
	}

	public void setLossMoney(float lossMoney) {
		this.lossMoney = lossMoney;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public float getLeasePrice() {
		return leasePrice;
	}

	public void setLeasePrice(float leasePrice) {
		this.leasePrice = leasePrice;
	}

	public float getLeaseNum() {
		return leaseNum;
	}

	public void setLeaseNum(float leaseNum) {
		this.leaseNum = leaseNum;
	}

	public String getLeaseDate() {
		if(collar != null){
			return collar.getLeaseDate();
		}
		return null;
	}

	public String getReturnDate() {
		if(back != null){
			return back.getReturnDate();
		}
		return returnDate;
	}
	
	public String getTypeId() {
		if(collar != null){
			return collar.getTypeId();
		}
		return null;
	}

	public float getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(float returnNum) {
		this.returnNum = returnNum;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceUnit() {
		return deviceUnit;
	}

	public void setDeviceUnit(String deviceUnit) {
		this.deviceUnit = deviceUnit;
	}

	public float getLossNum() {
		return lossNum;
	}

	public void setLossNum(float lossNum) {
		this.lossNum = lossNum;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getLltotal() {
		return lltotal;
	}

	public void setLltotal(String lltotal) {
		this.lltotal = lltotal;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String string) {
		this.projectName = string;
	}

	@Override
	public String toString() {
		return "BalanceAgreementModelResultBean [agreementCode=" + agreementCode + ", agreementId=" + agreementId
				+ ", collar=" + collar + ", back=" + back + ", isCount=" + isCount + ", leaseMoney=" + leaseMoney
				+ ", lossMoney=" + lossMoney + ", days=" + days + ", leasePrice=" + leasePrice + ", leaseNum="
				+ leaseNum + ", returnNum=" + returnNum + ", returnDate=" + returnDate + ", deviceName=" + deviceName
				+ ", deviceModel=" + deviceModel + ", deviceUnit=" + deviceUnit + ", deviceCode=" + deviceCode
				+ ", leaseTotal=" + leaseTotal + "]";
	}
	
}
