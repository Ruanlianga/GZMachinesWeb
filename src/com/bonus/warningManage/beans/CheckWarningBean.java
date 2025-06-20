package com.bonus.warningManage.beans;

public class CheckWarningBean {

	private String taskId;
	private String id;
	private String machinesId;
	private String machinesType;
	private String machinesModel;
	private String venderName;
	private String outFactortTime;
	private String buyPrice;
	private String leasePrice;
	private String keeper;
	private String keyWord;
	private String repairStatus;
	private String repairMan;
	private String maType;
	private String agreementCode;
	private String projectName;
	private String remarks;
	private String maName;
	private String deviceCode;
	private String level;
	private String thisCheckTime;
	private String nextCheckTime;
	private String unitName;
	private String scope;
	
	private String orgId;
	

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getThisCheckTime() {
		return thisCheckTime;
	}

	public void setThisCheckTime(String thisCheckTime) {
		this.thisCheckTime = thisCheckTime;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNextCheckTime() {
		return nextCheckTime;
	}

	public void setNextCheckTime(String nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}

	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getMaName() {
		return maName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMaType() {
		return maType;
	}

	public void setMaType(String maType) {
		this.maType = maType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMachinesId() {
		return machinesId;
	}

	public void setMachinesId(String machinesId) {
		this.machinesId = machinesId;
	}

	public String getMachinesType() {
		return machinesType;
	}

	public void setMachinesType(String machinesType) {
		this.machinesType = machinesType;
	}

	public String getMachinesModel() {
		return machinesModel;
	}

	public void setMachinesModel(String machinesModel) {
		this.machinesModel = machinesModel;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getOutFactortTime() {
		return outFactortTime;
	}

	public void setOutFactortTime(String outFactortTime) {
		this.outFactortTime = outFactortTime;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getLeasePrice() {
		return leasePrice;
	}

	public void setLeasePrice(String leasePrice) {
		this.leasePrice = leasePrice;
	}

	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}

	public String getRepairMan() {
		return repairMan;
	}

	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}

	@Override
	public String toString() {
		return "RepairDetailsBean [taskId=" + taskId + ", id=" + id + ", machinesId=" + machinesId + ", machinesType="
				+ machinesType + ", machinesModel=" + machinesModel + ", venderName=" + venderName + ", outFactortTime="
				+ outFactortTime + ", buyPrice=" + buyPrice + ", leasePrice=" + leasePrice + ", keeper=" + keeper
				+ ", keyWord=" + keyWord + ", repairStatus=" + repairStatus + "]";
	}
	
	
	
}
