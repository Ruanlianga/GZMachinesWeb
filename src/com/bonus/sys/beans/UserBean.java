package com.bonus.sys.beans;

import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("UserBean")
public class UserBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer orgId;
	
	private String orgName;

	private Integer postId;

	private String loginName;

	private String picUrl;

	private String skin;

	private String salt;

	private String passwd;

	private String sex;

	private String mail;
	
	private String postName;

	private String qq;

	private String telphone;

	private Date createTime;

	private Date updateTime;

	private String isActive;
	
	private String projectId;
	
	private String projectName;
	
	private String officeAddress;			//办公地点
	
	private String postDuty;
	
	//下面是查询使用
	private String keyWord;
	
	private int roleId;
	
	private String cId;
	
	private String companyId;//所属分公司id
	
	private String companyName;//所属分公司
	
	private String parentId;//
	
	private String token;
	
	
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getOrgName() {
		return orgName;
	}
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl == null ? null : picUrl.trim();
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin == null ? null : skin.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd == null ? null : passwd.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail == null ? null : mail.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone == null ? null : telphone.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive == null ? null : isActive.trim();
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getPostDuty() {
		return postDuty;
	}

	public void setPostDuty(String postDuty) {
		this.postDuty = postDuty;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", orgId=" + orgId + ", orgName=" + orgName + ", postId="
				+ postId + ", loginName=" + loginName + ", picUrl=" + picUrl + ", skin=" + skin + ", salt=" + salt
				+ ", passwd=" + passwd + ", sex=" + sex + ", mail=" + mail + ", postName=" + postName + ", qq=" + qq
				+ ", telphone=" + telphone + ", createTime=" + createTime + ", updateTime=" + updateTime + ", isActive="
				+ isActive + ", keyWord=" + keyWord + ", roleId=" + roleId + "]";
	}
	
	
}