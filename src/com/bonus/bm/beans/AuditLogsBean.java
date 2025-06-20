package com.bonus.bm.beans;

public class AuditLogsBean {

	private String id;
	private String userId;
	private String userName;
	private String loginIp;			//当前操作人ip
	private String actionUrl;		//操作请求的链接
	private String module;			//执行的模块
	private String method;			//执行的方法
	private String actionTime;		//执行操作时间
	private String description;
	private String gmtCreate;		//执行的时间
	private String state;			//1表示成功，-1表示失败！
	
	private String keyWord;
	
	private String startTime;
	
	private String endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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
	
	public AuditLogsBean() {
		super();
	}
	
	
	public AuditLogsBean(String loginIp,String userId, String actionUrl, String module,
			String method, String actionTime, String description, String gmtCreate, String state) {
		super();
		this.userId = userId;
		this.loginIp = loginIp;
		this.actionUrl = actionUrl;
		this.module = module;
		this.method = method;
		this.actionTime = actionTime;
		this.description = description;
		this.gmtCreate = gmtCreate;
		this.state = state;
	}
	
}
