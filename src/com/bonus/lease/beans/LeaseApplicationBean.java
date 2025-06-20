package com.bonus.lease.beans;

import java.util.List;

import com.bonus.bm.beans.subcontractorsBean;

//租赁申请
public class LeaseApplicationBean {
	
	private String id;
	private String applyNumber;			//申请单号
	private String proposer;			//申请人
	private String applyTime;			//申请时间
	private String phone;				//联系电话
	private String unitName;			//申请单位
	private String workName;			//申请工程
	private String agreementId;			//协议ID
	private String agreementCode;		//协议号
	private String operator;			//操作人
	private String operationTime;		//操作时间
	private String remark;				//备注
	private String isSure;				//是否确认
	private String taskId;
	private String keyWord;
	private String startTime;
	private String endTime;
	private String ids;
	private String subcontractors;
	private String companyId;

	// 工程id
	private Integer projectType;
	
	private String token;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	private List<subcontractorsBean> subcontractorsList; // 

	@Override
	public String toString() {
		return "LeaseApplicationBean [id=" + id + ", applyNumber=" + applyNumber + ", proposer=" + proposer
				+ ", applyTime=" + applyTime + ", phone=" + phone + ", unitName=" + unitName + ", workName=" + workName
				+ ", agreementId=" + agreementId + ", agreementCode=" + agreementCode + ", operator=" + operator
				+ ", operationTime=" + operationTime + ", remark=" + remark + ", isSure=" + isSure + ", taskId="
				+ taskId + ", keyWord=" + keyWord + ", startTime=" + startTime + ", endTime=" + endTime + ", ids=" + ids
				+ "]";
	}
	public String getSubcontractors() {
		return subcontractors;
	}
	public void setSubcontractors(String subcontractors) {
		this.subcontractors = subcontractors;
	}
	public List<subcontractorsBean> getSubcontractorsList() {
		return subcontractorsList;
	}

	public void setSubcontractorsList(List<subcontractorsBean> subcontractorsList) {
		this.subcontractorsList = subcontractorsList;
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
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

	public String getIsSure() {
		return isSure;
	}

	public void setIsSure(String isSure) {
		this.isSure = isSure;
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

	public Integer getProjectType() {
		return projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
}
