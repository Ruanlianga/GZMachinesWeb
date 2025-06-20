package com.bonus.lease.beans;

import com.bonus.wf.beans.TaskRecordBean;

public class OutStorageBean extends TaskRecordBean {
    private String id;
	private String maType;// 机具类型
	private String maModel;// 机具规格
	private String alreadyCollerNum;// 已领数量
	private String preCollerNum;// 预领数量
	private String isSure;// 是否确认
	private String maTypeId;
	private String maModelId;
	private String checker;//检验员
	private String customerSrep;//客服代表
	private String leaseCompany;// 租赁单位
	private String leaseProject;// 租赁工程

	@Override
	public Integer getPlanOutId() {
		return planOutId;
	}

	@Override
	public void setPlanOutId(Integer planOutId) {
		this.planOutId = planOutId;
	}

	private Integer planOutId;

	public Integer getLeaseProjectId() {
		return leaseProjectId;
	}

	public void setLeaseProjectId(Integer leaseProjectId) {
		this.leaseProjectId = leaseProjectId;
	}

	private Integer leaseProjectId; // 领料工程id
	private String machinesNum;// 机具数量
	private String remark;// 备注
	private String keyWord;
	private String taskId;
	private String userId;//用户Id
	private String outPersonId;//出库人Id
	private String outTime;//出库时间
	private String checkNum;//检验数量
	private String outNum;//出库数量
	private String isExamine;//是否审核
	private String isApproval;//是否批准
	private String maCode;//机具编码
	private String alOutNum;//已出库数
    private String thisOutNum;//本次出库数
    private String preOutNum;//预出库数
    private String outPerson;
    private String typeId;
    private String deviceCode;
    private String applyNumber;
    private String applyTime;
    private String machineStatus;
    private String type; //2机具出库
    private String isCount;
    private String supId;
    private String agreementId;
    private String startTime;
    private String endTime;
    private String isFinish;
    private String cTaskId;//检验任务Id
    private String mTaskId;//出库任务Id
    private String maId;
    private String storageNum;//库存
    private String batchStatus;//库存
    private String examineUser;
    private String approvalUser;
    
    private String type0;
    private String venderName;
    
    private String auditRemark;
    
    private String approvalRemark;
    
    private String outTaskId;
    
    private String rfidPower;
    
    private String dateNum;
    private String alredyCollarNum;
    private String phone;
    
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAlredyCollarNum() {
		return alredyCollarNum;
	}
	public void setAlredyCollarNum(String alredyCollarNum) {
		this.alredyCollarNum = alredyCollarNum;
	}
	public String getDateNum() {
		return dateNum;
	}
	public void setDateNum(String dateNum) {
		this.dateNum = dateNum;
	}
	public String getRfidPower() {
		return rfidPower;
	}
	public void setRfidPower(String rfidPower) {
		this.rfidPower = rfidPower;
	}
	public String getOutTaskId() {
		return outTaskId;
	}
	public void setOutTaskId(String outTaskId) {
		this.outTaskId = outTaskId;
	}
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	public String getApprovalRemark() {
		return approvalRemark;
	}
	public void setApprovalRemark(String approvalRemark) {
		this.approvalRemark = approvalRemark;
	}
	public String getBatchStatus() {
		return batchStatus;
	}
	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public String getType0() {
		return type0;
	}
	public void setType0(String type0) {
		this.type0 = type0;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getStorageNum() {
		return storageNum;
	}
	public void setStorageNum(String storageNum) {
		this.storageNum = storageNum;
	}
	private String collarId;
    
    private String ids;
    
    private String parentTypeName;
    private String typeName;
    private String unit;
    private String projectName;
    private String companyName;
    private String leaseDate;
    private String leaseCpy;
    private String operationTime;
    
	public String getLeaseCpy() {
		return leaseCpy;
	}
	public void setLeaseCpy(String leaseCpy) {
		this.leaseCpy = leaseCpy;
	}
	public String getParentTypeName() {
		return parentTypeName;
	}
	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLeaseDate() {
		return leaseDate;
	}
	public void setLeaseDate(String leaseDate) {
		this.leaseDate = leaseDate;
	}
	public String getCollarId() {
		return collarId;
	}
	public void setCollarId(String collarId) {
		this.collarId = collarId;
	}
	@Override
	public String toString() {
		return "OutStorageBean [id=" + id + ", maType=" + maType + ", maModel=" + maModel + ", alreadyCollerNum="
				+ alreadyCollerNum + ", preCollerNum=" + preCollerNum + ", isSure=" + isSure + ", maTypeId=" + maTypeId
				+ ", maModelId=" + maModelId + ", checker=" + checker + ", customerSrep=" + customerSrep
				+ ", leaseCompany=" + leaseCompany + ", leaseProject=" + leaseProject + ", machinesNum=" + machinesNum
				+ ", remark=" + remark + ", keyWord=" + keyWord + ", taskId=" + taskId + ", userId=" + userId
				+ ", outPersonId=" + outPersonId + ", outTime=" + outTime + ", checkNum=" + checkNum + ", outNum="
				+ outNum + ", isExamine=" + isExamine + ", isApproval=" + isApproval + ", maCode=" + maCode
				+ ", alOutNum=" + alOutNum + ", thisOutNum=" + thisOutNum + ", preOutNum=" + preOutNum + ", outPerson="
				+ outPerson + ", typeId=" + typeId + ", deviceCode=" + deviceCode + ", applyNumber=" + applyNumber
				+ ", applyTime=" + applyTime + ", machineStatus=" + machineStatus + ", type=" + type + ", isCount="
				+ isCount + ", supId=" + supId + ", agreementId=" + agreementId + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", isFinish=" + isFinish + ", cTaskId=" + cTaskId + ", mTaskId=" + mTaskId
				+ ", maId=" + maId + ", collarId=" + collarId + ", ids=" + ids + ", parentTypeName=" + parentTypeName
				+ ", typeName=" + typeName + ", unit=" + unit + ", projectName=" + projectName + ", companyName="
				+ companyName + ", leaseDate=" + leaseDate + ", leaseCpy=" + leaseCpy + "]";
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getMaId() {
		return maId;
	}
	public void setMaId(String maId) {
		this.maId = maId;
	}
	public String getcTaskId() {
		return cTaskId;
	}
	public void setcTaskId(String cTaskId) {
		this.cTaskId = cTaskId;
	}
	public String getmTaskId() {
		return mTaskId;
	}
	public void setmTaskId(String mTaskId) {
		this.mTaskId = mTaskId;
	}
	public String getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaType() {
		return maType;
	}
	public void setMaType(String maType) {
		this.maType = maType;
	}
	public String getMaModel() {
		return maModel;
	}
	public void setMaModel(String maModel) {
		this.maModel = maModel;
	}
	public String getAlreadyCollerNum() {
		return alreadyCollerNum;
	}
	public void setAlreadyCollerNum(String alreadyCollerNum) {
		this.alreadyCollerNum = alreadyCollerNum;
	}
	public String getPreCollerNum() {
		return preCollerNum;
	}
	public void setPreCollerNum(String preCollerNum) {
		this.preCollerNum = preCollerNum;
	}
	public String getIsSure() {
		return isSure;
	}
	public void setIsSure(String isSure) {
		this.isSure = isSure;
	}
	public String getMaTypeId() {
		return maTypeId;
	}
	public void setMaTypeId(String maTypeId) {
		this.maTypeId = maTypeId;
	}
	public String getMaModelId() {
		return maModelId;
	}
	public void setMaModelId(String maModelId) {
		this.maModelId = maModelId;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getCustomerSrep() {
		return customerSrep;
	}
	public void setCustomerSrep(String customerSrep) {
		this.customerSrep = customerSrep;
	}
	public String getLeaseCompany() {
		return leaseCompany;
	}
	public void setLeaseCompany(String leaseCompany) {
		this.leaseCompany = leaseCompany;
	}
	public String getLeaseProject() {
		return leaseProject;
	}
	public void setLeaseProject(String leaseProject) {
		this.leaseProject = leaseProject;
	}
	public String getMachinesNum() {
		return machinesNum;
	}
	public void setMachinesNum(String machinesNum) {
		this.machinesNum = machinesNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOutPersonId() {
		return outPersonId;
	}
	public void setOutPersonId(String outPersonId) {
		this.outPersonId = outPersonId;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getOutNum() {
		return outNum;
	}
	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}
	public String getIsExamine() {
		return isExamine;
	}
	public void setIsExamine(String isExamine) {
		this.isExamine = isExamine;
	}
	public String getIsApproval() {
		return isApproval;
	}
	public void setIsApproval(String isApproval) {
		this.isApproval = isApproval;
	}
	public String getMaCode() {
		return maCode;
	}
	public void setMaCode(String maCode) {
		this.maCode = maCode;
	}
	public String getAlOutNum() {
		return alOutNum;
	}
	public void setAlOutNum(String alOutNum) {
		this.alOutNum = alOutNum;
	}
	public String getThisOutNum() {
		return thisOutNum;
	}
	public void setThisOutNum(String thisOutNum) {
		this.thisOutNum = thisOutNum;
	}
	public String getPreOutNum() {
		return preOutNum;
	}
	public void setPreOutNum(String preOutNum) {
		this.preOutNum = preOutNum;
	}
	public String getOutPerson() {
		return outPerson;
	}
	public void setOutPerson(String outPerson) {
		this.outPerson = outPerson;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
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
	public String getMachineStatus() {
		return machineStatus;
	}
	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsCount() {
		return isCount;
	}
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
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
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getExamineUser() {
		return examineUser;
	}
	public void setExamineUser(String examineUser) {
		this.examineUser = examineUser;
	}
	public String getApprovalUser() {
		return approvalUser;
	}
	public void setApprovalUser(String approvalUser) {
		this.approvalUser = approvalUser;
	}

	
}
