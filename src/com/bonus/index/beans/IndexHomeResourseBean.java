package com.bonus.index.beans;

import java.util.List;

public class IndexHomeResourseBean {
	
	private String id ;
	
	private String userId;
	
	private String rsId;
	
	private String rsName;
	
	private String rsIcon;
	
	private String rsUrl;
	
	private String[] list;
	
	private String isCheck;

	private IndexHomeResourseBean [] arr;

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}

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

	public String getRsId() {
		return rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getRsName() {
		return rsName;
	}

	public void setRsName(String rsName) {
		this.rsName = rsName;
	}

	public String getRsIcon() {
		return rsIcon;
	}

	public void setRsIcon(String rsIcon) {
		this.rsIcon = rsIcon;
	}

	public String getRsUrl() {
		return rsUrl;
	}

	public void setRsUrl(String rsUrl) {
		this.rsUrl = rsUrl;
	}
	
	
	
}
