package com.bonus.wf.beans;

//任务记录
public class TaskRecordBean{

	private String id;
	private String definitionId;				//所属任务ID
	private String definitionName;				//所属任务名称
	private String processId;					//所属流程ID
	private String processName;					//所属流程名称
	private String operationUserId;				//操作人员ID
	private String operationUserName;			//操作人员名称
	private String operationTime;				//操作时间					
	private String number;						//任务单号
	private String isFinish;					//是否完成
	private String remark;						//备注
	private String leasePerson;					//租赁人员
	private String phone;						//租赁电话
	private String taskId;						
	private String agreementId;					//协议ID
	private String checkerId;				
	private String checkerName;	
	private String creatorName;
	private String isExamine2;
	private String isApproval2;
	private String responer2;
	private String responer8;
	private String responer11;
	private String checkStatus12;
	private String isExamine12;
	private String isApproval12;
	private String checker12;
	private String responer12;
	private String responer18;
	private String responer19;
	private String responer20;
	private String responer21;
	private String responer22;
	private String responer23;
	private String responer24;
	private String responer25;
	private String unitName;
	private String projectName;
	private String companyId;
	private String supId;
	
	private String isSplit;

	// 计划出库单ID
	private Integer planOutId;



	public Integer getPlanOutId() {
		return planOutId;
	}

	public void setPlanOutId(Integer planOutId) {
		this.planOutId = planOutId;
	}

	
	public String getIsSplit() {
		return isSplit;
	}
	public void setIsSplit(String isSplit) {
		this.isSplit = isSplit;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	private String subcontractors;
	
	public String getSubcontractors() {
		return subcontractors;
	}
	public void setSubcontractors(String subcontractors) {
		this.subcontractors = subcontractors;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getIsExamine2() {
		return isExamine2;
	}
	public void setIsExamine2(String isExamine2) {
		this.isExamine2 = isExamine2;
	}
	public String getIsApproval2() {
		return isApproval2;
	}
	public void setIsApproval2(String isApproval2) {
		this.isApproval2 = isApproval2;
	}
	public String getResponer2() {
		return responer2;
	}
	public void setResponer2(String responer2) {
		this.responer2 = responer2;
	}
	public String getResponer8() {
		return responer8;
	}
	public void setResponer8(String responer8) {
		this.responer8 = responer8;
	}
	public String getResponer11() {
		return responer11;
	}
	public void setResponer11(String responer11) {
		this.responer11 = responer11;
	}
	public String getCheckStatus12() {
		return checkStatus12;
	}
	public void setCheckStatus12(String checkStatus12) {
		this.checkStatus12 = checkStatus12;
	}
	public String getIsExamine12() {
		return isExamine12;
	}
	public void setIsExamine12(String isExamine12) {
		this.isExamine12 = isExamine12;
	}
	public String getIsApproval12() {
		return isApproval12;
	}
	public void setIsApproval12(String isApproval12) {
		this.isApproval12 = isApproval12;
	}
	public String getChecker12() {
		return checker12;
	}
	public void setChecker12(String checker12) {
		this.checker12 = checker12;
	}
	public String getResponer12() {
		return responer12;
	}
	public void setResponer12(String responer12) {
		this.responer12 = responer12;
	}
	public String getResponer18() {
		return responer18;
	}
	public void setResponer18(String responer18) {
		this.responer18 = responer18;
	}
	public String getResponer19() {
		return responer19;
	}
	public void setResponer19(String responer19) {
		this.responer19 = responer19;
	}
	public String getResponer20() {
		return responer20;
	}
	public void setResponer20(String responer20) {
		this.responer20 = responer20;
	}
	public String getResponer21() {
		return responer21;
	}
	public void setResponer21(String responer21) {
		this.responer21 = responer21;
	}
	public String getResponer22() {
		return responer22;
	}
	public void setResponer22(String responer22) {
		this.responer22 = responer22;
	}
	public String getResponer23() {
		return responer23;
	}
	public void setResponer23(String responer23) {
		this.responer23 = responer23;
	}
	public String getResponer24() {
		return responer24;
	}
	public void setResponer24(String responer24) {
		this.responer24 = responer24;
	}
	public String getResponer25() {
		return responer25;
	}
	public void setResponer25(String responer25) {
		this.responer25 = responer25;
	}
	@Override
	public String toString() {
		return "TaskRecordBean [id=" + id + ", definitionId=" + definitionId + ", definitionName=" + definitionName
				+ ", processId=" + processId + ", processName=" + processName + ", operationUserId=" + operationUserId
				+ ", operationUserName=" + operationUserName + ", operationTime=" + operationTime + ", number=" + number
				+ ", isFinish=" + isFinish + ", remark=" + remark + ", leasePerson=" + leasePerson + ", phone=" + phone
				+ ", taskId=" + taskId + ", agreementId=" + agreementId + ", checkerId=" + checkerId + ", checkerName="
				+ checkerName + ", creatorName=" + creatorName + ", isExamine2=" + isExamine2 + ", isApproval2="
				+ isApproval2 + ", responer2=" + responer2 + ", responer8=" + responer8 + ", responer11=" + responer11
				+ ", checkStatus12=" + checkStatus12 + ", isExamine12=" + isExamine12 + ", isApproval12=" + isApproval12
				+ ", checker12=" + checker12 + ", responer12=" + responer12 + ", responer18=" + responer18
				+ ", responer19=" + responer19 + ", responer20=" + responer20 + ", responer21=" + responer21
				+ ", responer22=" + responer22 + ", responer23=" + responer23 + ", responer24=" + responer24
				+ ", responer25=" + responer25 + "]";
	}
	public String getCheckerId() {
		return checkerId;
	}
	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	public String getDefinitionName() {
		return definitionName;
	}
	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getOperationUserId() {
		return operationUserId;
	}
	public void setOperationUserId(String operationUserId) {
		this.operationUserId = operationUserId;
	}
	public String getOperationUserName() {
		return operationUserName;
	}
	public void setOperationUserName(String operationUserName) {
		this.operationUserName = operationUserName;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLeasePerson() {
		return leasePerson;
	}
	public void setLeasePerson(String leasePerson) {
		this.leasePerson = leasePerson;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	
}
