package com.bonus.settlement.beans;

public class SettlementDetailsBean {

	private String id;
	private String maId;
	private boolean isCount;
	
	//租赁
	private String agreementId;
	private String agreementCode;
	private String deviceName;
	private String deviceModel;
	private String deviceCode;
	private String typeId;
	private String deviceUnit;
	private String leaseNum;
	private String leaseDate;
	private String returnNum;
	private String returnDate;
	private String stopDays;
	private String leaseDays;
	private String leaseMoney;
	private String leaseTotal;
	
	//丢失
	private String loseNum;
	private String loseMoney;
	private String loseTotal;
	
	//维修
	private String repairCode;
	private String repairNum;
	private String repairMoney;
	private String repairTotal;
	
	//报废
	private String scrapNum;
	private String scrapMoney;
	private String scrapTotal;
	
	private String isSure;
	private String isSettlement;
	private String projectName;
	private String leaseName;
	private String backId;
    private String planLeasePrice;//计划租赁单价
	private String leasePrice;
	private String buyPrice;
	private String settlementTime;
	private String days;
	private String batch;//结算批次
	private String flag;
	private String deductionMoney;
	private String remark;
	private String deductionTotal;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaId() {
		return maId;
	}
	public void setMaId(String maId) {
		this.maId = maId;
	}
	public boolean isCount() {
		return isCount;
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
	public String getAgreementCode() {
		return agreementCode;
	}
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
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
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getDeviceUnit() {
		return deviceUnit;
	}
	public void setDeviceUnit(String deviceUnit) {
		this.deviceUnit = deviceUnit;
	}
	public String getLeaseNum() {
		return leaseNum;
	}
	public void setLeaseNum(String leaseNum) {
		this.leaseNum = leaseNum;
	}
	public String getLeaseDate() {
		return leaseDate;
	}
	public void setLeaseDate(String leaseDate) {
		this.leaseDate = leaseDate;
	}
	public String getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(String returnNum) {
		this.returnNum = returnNum;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getStopDays() {
		return stopDays;
	}
	public void setStopDays(String stopDays) {
		this.stopDays = stopDays;
	}
	public String getLeaseDays() {
		return leaseDays;
	}
	public void setLeaseDays(String leaseDays) {
		this.leaseDays = leaseDays;
	}
	public String getLeaseMoney() {
		return leaseMoney;
	}
	public void setLeaseMoney(String leaseMoney) {
		this.leaseMoney = leaseMoney;
	}
	public String getLeaseTotal() {
		return leaseTotal;
	}
	public void setLeaseTotal(String leaseTotal) {
		this.leaseTotal = leaseTotal;
	}
	public String getLoseNum() {
		return loseNum;
	}
	public void setLoseNum(String loseNum) {
		this.loseNum = loseNum;
	}
	public String getLoseMoney() {
		return loseMoney;
	}
	public void setLoseMoney(String loseMoney) {
		this.loseMoney = loseMoney;
	}
	public String getLoseTotal() {
		return loseTotal;
	}
	public void setLoseTotal(String loseTotal) {
		this.loseTotal = loseTotal;
	}
	public String getRepairCode() {
		return repairCode;
	}
	public void setRepairCode(String repairCode) {
		this.repairCode = repairCode;
	}
	public String getRepairNum() {
		return repairNum;
	}
	public void setRepairNum(String repairNum) {
		this.repairNum = repairNum;
	}
	public String getRepairMoney() {
		return repairMoney;
	}
	public void setRepairMoney(String repairMoney) {
		this.repairMoney = repairMoney;
	}
	public String getRepairTotal() {
		return repairTotal;
	}
	public void setRepairTotal(String repairTotal) {
		this.repairTotal = repairTotal;
	}
	public String getScrapNum() {
		return scrapNum;
	}
	public void setScrapNum(String scrapNum) {
		this.scrapNum = scrapNum;
	}
	public String getScrapMoney() {
		return scrapMoney;
	}
	public void setScrapMoney(String scrapMoney) {
		this.scrapMoney = scrapMoney;
	}
	public String getScrapTotal() {
		return scrapTotal;
	}
	public void setScrapTotal(String scrapTotal) {
		this.scrapTotal = scrapTotal;
	}
	public String getIsSure() {
		return isSure;
	}
	public void setIsSure(String isSure) {
		this.isSure = isSure;
	}
	public String getIsSettlement() {
		return isSettlement;
	}
	public void setIsSettlement(String isSettlement) {
		this.isSettlement = isSettlement;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getLeaseName() {
		return leaseName;
	}
	public void setLeaseName(String leaseName) {
		this.leaseName = leaseName;
	}
	public String getBackId() {
		return backId;
	}
	public void setBackId(String backId) {
		this.backId = backId;
	}
	public String getLeasePrice() {
		return leasePrice;
	}
	public void setLeasePrice(String leasePrice) {
		this.leasePrice = leasePrice;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(String settlementTime) {
		this.settlementTime = settlementTime;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getPlanLeasePrice() {
		return planLeasePrice;
	}
	public void setPlanLeasePrice(String planLeasePrice) {
		this.planLeasePrice = planLeasePrice;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDeductionMoney() {
		return deductionMoney;
	}
	public void setDeductionMoney(String deductionMoney) {
		this.deductionMoney = deductionMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeductionTotal() {
		return deductionTotal;
	}
	public void setDeductionTotal(String deductionTotal) {
		this.deductionTotal = deductionTotal;
	}
	
}
