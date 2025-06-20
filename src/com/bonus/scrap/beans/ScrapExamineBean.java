package com.bonus.scrap.beans;



import com.bonus.repair.beans.RepairDetailsBean;

//报废任务明细 
public class ScrapExamineBean {
	private String id;
	private String taskId;
	private String scrapTime;// 报废时间
	private String scrapNum;// 报废数量
	private String typeId;
	private String typetId;
	

	private String typeName;
	private String modelId;
	private String isCount;
	private String modelName;
	private String scrapChecker; // 报废检验员
	private String number; // 退料单号
	private String scrapNumber; // 报废单号
	private String remark;
	private String operator; // 操作人
	private String operationTime; // 操作时间
	private String agreementCode; // 协议号
	private String isFinish; // 是否完成
	private String scrapPerson; //报废人员
	private String scrapPersonId;
	private String isActive;
	private String isSure;
	private String startTime;
	private String endTime;
	private String keyWord;
	private String supId;
	private String deviceCode;
	private String projectName;
	private String companyName;
	private String alScrapNum;
	private String rmStatus;
	private String infoId;
	private String scrapReson;
	private String nums;
	private String checkId;//检验任务ID
	private String definitionId;
	private String batchStatus;
	private String scrapPart;
	private String infoType;
	private String userId;
	private String createTime;
    private String creator;
    private String auditor;
    private String auditTime;
    private String machine;
    private String machineCode;
    private String scrapCode;
    private String type;
    private String typeParentId;
	
    
    public String getTypetId() {
		return typetId;
	}

	public void setTypetId(String typetId) {
		this.typetId = typetId;
	}
	public String getTypeParentId() {
		return typeParentId;
	}

	public void setTypeParentId(String typeParentId) {
		this.typeParentId = typeParentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScrapCode() {
		return scrapCode;
	}

	public void setScrapCode(String scrapCode) {
		this.scrapCode = scrapCode;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	private String scrapUrl;
	
	private float scrapingNum;
	
	private float thisScrapNum;
	
	private ScrapDetailsBean[] arr;
	private float paraNum;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	
	public String getScrapPart() {
		return scrapPart;
	}

	public void setScrapPart(String scrapPart) {
		this.scrapPart = scrapPart;
	}

	public String getScrapUrl() {
		return scrapUrl;
	}

	public void setScrapUrl(String scrapUrl) {
		this.scrapUrl = scrapUrl;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public float getScrapingNum() {
		return scrapingNum;
	}

	public void setScrapingNum(float scrapingNum) {
		this.scrapingNum = scrapingNum;
	}

	public float getThisScrapNum() {
		return thisScrapNum;
	}

	public void setThisScrapNum(float thisScrapNum) {
		this.thisScrapNum = thisScrapNum;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getRmStatus() {
		return rmStatus;
	}

	public void setRmStatus(String rmStatus) {
		this.rmStatus = rmStatus;
	}

	public String getAlScrapNum() {
		return alScrapNum;
	}

	public void setAlScrapNum(String alScrapNum) {
		this.alScrapNum = alScrapNum;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getSupId() {
		return supId;
	}

	public void setSupId(String supId) {
		this.supId = supId;
	}

	public String getIsCount() {
		return isCount;
	}

	public void setIsCount(String isCount) {
		this.isCount = isCount;
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

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getScrapPerson() {
		return scrapPerson;
	}

	public void setScrapPerson(String scrapPerson) {
		this.scrapPerson = scrapPerson;
	}

	public String getScrapPersonId() {
		return scrapPersonId;
	}

	public void setScrapPersonId(String scrapPersonId) {
		this.scrapPersonId = scrapPersonId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsSure() {
		return isSure;
	}

	public void setIsSure(String isSure) {
		this.isSure = isSure;
	}

	public String getScrapTime() {
		return scrapTime;
	}

	public void setScrapTime(String scrapTime) {
		this.scrapTime = scrapTime;
	}

	public String getScrapNum() {
		return scrapNum;
	}

	public void setScrapNum(String scrapNum) {
		this.scrapNum = scrapNum;
	}

	public String getScrapChecker() {
		return scrapChecker;
	}

	public void setScrapChecker(String scrapChecker) {
		this.scrapChecker = scrapChecker;
	}

	public String getScrapNumber() {
		return scrapNumber;
	}

	public void setScrapNumber(String scrapNumber) {
		this.scrapNumber = scrapNumber;
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

	public String getScrapReson() {
		return scrapReson;
	}

	public void setScrapReson(String scrapReson) {
		this.scrapReson = scrapReson;
	}

	public ScrapDetailsBean[] getArr() {
		return arr;
	}

	public void setArr(ScrapDetailsBean[] arr) {
		this.arr = arr;
	}

	public float getParaNum() {
		return paraNum;
	}

	public void setParaNum(float paraNum) {
		this.paraNum = paraNum;
	}

	
}

