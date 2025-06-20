package com.bonus.plan.beans;

import java.util.List;

/**
 *计划申请实体类
 * @author xj

 */
public class PlanApplyBean {
	
		
	private String id;
	/**
	 * 计划编号
	 */
	private String code;
	
	/**
	 * 计划编号
	 */
	private String proId;
	
	/**
	 * 工程id
	 */
	private String projectId;
		
	/**
	 * 项目部分
	 */
	private String projectPart;
	
		/**
		 * 施工地点
		 */
	private String projectContent;
	
	
	/**
	 * 施工地点
	 */
	private String remark;
			


		/**
		 * 需要时间
		 */
	private String needTime;
		/**
		 * 创建人
		 */
	private String creator;
		/**
		 * 创建时间
		 */
	private String createTime;
		/**
		 * 状态
		 */
	private String status;
	/**
	 * 更新人
	 */
	private String updater;
		/**
		 * 更新时间
		 */
	private String updateTime;
	
	
	private String keyWord;
	
	
	private String statusType;
	
	private String proName;
	
	
	private String jsonData;
	
	private List<PlanDataDetailBean> details;
	 
	
	private List<AuditBean> auditList;
	
	/**
	 * 审核状态
	 */
	private String auditStatus;

	
	

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public List<AuditBean> getAuditList() {
		return auditList;
	}

	public void setAuditList(List<AuditBean> auditList) {
		this.auditList = auditList;
	}

	public List<PlanDataDetailBean> getDetails() {
		return details;
	}

	public void setDetails(List<PlanDataDetailBean> details) {
		this.details = details;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectPart() {
		return projectPart;
	}

	public void setProjectPart(String projectPart) {
		this.projectPart = projectPart;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}
	public String getRemark() {
	return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNeedTime() {
		return needTime;
	}

	public void setNeedTime(String needTime) {
		this.needTime = needTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
	
	
	
	

}
