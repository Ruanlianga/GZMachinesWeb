package com.bonus.plan.beans;
/**
 * 审核 详情
 * @author xj
 *
 *
 */
public class AuditBean {

	/**
	 * 计划id
	 */
	private String applyId;
		/**
		 * 审核人
		 */
	private int auditor;
	/**
	 * 审核时间
	 */
	private String auditTime;
	/**
	 * 审核状态  2 审核通过  3 审核驳回  
	 */
	private String auditStatus;
	
	/**
	 * 备注
	 */
	private String auditRemarks;
	/**
	 * 修改人
	 */
	private int updater;
	
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 审核类型
	 */
	private String auditType;
	
	private String auditUser;
	
	private  int hours;
	
	private  int minutes;
	
	private String createTime;
	
	private String phone;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public int getAuditor() {
		return auditor;
	}
	public void setAuditor(int auditor) {
		this.auditor = auditor;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditRemarks() {
		return auditRemarks;
	}
	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}
	public int getUpdater() {
		return updater;
	}
	public void setUpdater(int updater) {
		this.updater = updater;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	
	
	
	
}
