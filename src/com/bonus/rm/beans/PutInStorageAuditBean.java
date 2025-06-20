package com.bonus.rm.beans;

//入库审核
public class PutInStorageAuditBean {
	private String id;
	private String supId;
	private String taskId;				//所属任务
	private String machineName;			//机具名称
	private String machineModel;		//机具规格
	private String machineStatus;       //机具状态
	private String typeId;			    //规格Id
	private String putTime;		        //入库时间
	private String putPersonId;         //入库人Id
	private String putPerson;           //入库人
	private String prePutNum;           //预入库数量
	private String thisPutNum;          //本次入库数量
	private String alPutNum;          //已入库数量
	private String deviceCode;          //设备编码
	private String isCount;             //是否计数
	private String isActive;
	private String startTime;
	private String endTime;
	private String agreementId;
	private String agreementCode;
	private String leaseName;
	private String projectName;
	private String applyNumber;
	private String applyTime;
	private String keyWord;
	private String type;              //1检验任务详情
	private String maModelId;
	private String isSure;
	private String remark;
	private String isExamine;
	private String checkId;
	private String auditTime;
	private String storageNum;
	private String definitionName;
	private String ids;
	private String operationTime;
	private String companyId;
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	@Override
	public String toString() {
		return "PutInStorageAuditBean [id=" + id + ", supId=" + supId + ", taskId=" + taskId + ", machineName="
				+ machineName + ", machineModel=" + machineModel + ", machineStatus=" + machineStatus + ", typeId="
				+ typeId + ", putTime=" + putTime + ", putPersonId=" + putPersonId + ", putPerson=" + putPerson
				+ ", prePutNum=" + prePutNum + ", thisPutNum=" + thisPutNum + ", alPutNum=" + alPutNum + ", deviceCode="
				+ deviceCode + ", isCount=" + isCount + ", isActive=" + isActive + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", agreementId=" + agreementId + ", agreementCode=" + agreementCode
				+ ", leaseName=" + leaseName + ", projectName=" + projectName + ", applyNumber=" + applyNumber
				+ ", applyTime=" + applyTime + ", keyWord=" + keyWord + ", type=" + type + ", maModelId=" + maModelId
				+ ", isSure=" + isSure + ", remark=" + remark + ", isExamine=" + isExamine + ", checkId=" + checkId
				+ ", auditTime=" + auditTime + ", storageNum=" + storageNum + ", ids=" + ids + "]";
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getStorageNum() {
		return storageNum;
	}
	public void setStorageNum(String storageNum) {
		this.storageNum = storageNum;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getIsExamine() {
		return isExamine;
	}
	public void setIsExamine(String isExamine) {
		this.isExamine = isExamine;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineModel() {
		return machineModel;
	}
	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}
	public String getMachineStatus() {
		return machineStatus;
	}
	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getPutTime() {
		return putTime;
	}
	public void setPutTime(String putTime) {
		this.putTime = putTime;
	}
	public String getPutPersonId() {
		return putPersonId;
	}
	public void setPutPersonId(String putPersonId) {
		this.putPersonId = putPersonId;
	}
	public String getPutPerson() {
		return putPerson;
	}
	public void setPutPerson(String putPerson) {
		this.putPerson = putPerson;
	}
	public String getPrePutNum() {
		return prePutNum;
	}
	public void setPrePutNum(String prePutNum) {
		this.prePutNum = prePutNum;
	}
	public String getThisPutNum() {
		return thisPutNum;
	}
	public void setThisPutNum(String thisPutNum) {
		this.thisPutNum = thisPutNum;
	}
	public String getAlPutNum() {
		return alPutNum;
	}
	public void setAlPutNum(String alPutNum) {
		this.alPutNum = alPutNum;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getIsCount() {
		return isCount;
	}
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getLeaseName() {
		return leaseName;
	}
	public void setLeaseName(String leaseName) {
		this.leaseName = leaseName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getApplyNumber() {
		return applyNumber;
	}
	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMaModelId() {
		return maModelId;
	}
	public void setMaModelId(String maModelId) {
		this.maModelId = maModelId;
	}
	public String getIsSure() {
		return isSure;
	}
	public void setIsSure(String isSure) {
		this.isSure = isSure;
	}
	public String getDefinitionName() {
		return definitionName;
	}
	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}

}