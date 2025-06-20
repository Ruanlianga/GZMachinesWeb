package com.bonus.repair.beans;

//维修任务 
public class RepairTaskBean {
	private String id;
	private String agreementId;
	private String leaseCompany;
	private String leaseWork;
	private String typeId;
	private String typeName;
	private String modelId;
	private String modelName;
	private String phone;				//联系电话
	private String isCount;
	private String number;				//退料单号
	private String remark;
	private String operator;			//操作人
	private String operationTime;		//操作时间
	private String agreementCode;		//协议号
	private String isFinish;			//是否完成
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getLeaseCompany() {
		return leaseCompany;
	}
	public void setLeaseCompany(String leaseCompany) {
		this.leaseCompany = leaseCompany;
	}
	public String getLeaseWork() {
		return leaseWork;
	}
	public void setLeaseWork(String leaseWork) {
		this.leaseWork = leaseWork;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIsCount() {
		return isCount;
	}
	public void setIsCount(String isCount) {
		this.isCount = isCount;
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
	
	
}
