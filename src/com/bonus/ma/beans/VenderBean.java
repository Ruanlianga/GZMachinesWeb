package com.bonus.ma.beans;

public class VenderBean {

	private String id;
	private String name;
	private String address;
	private String companyMan;
	private String mainPerson;
	private String phone;
	private String scopeBusiness;		//主要经营范围
	private String remark;
	private String picUrl;				//营业执照
	private String picName;
	
	private String keyWord;

	/**
	 * @return the id
	 */
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the companyMan
	 */
	public String getCompanyMan() {
		return companyMan;
	}

	/**
	 * @param companyMan the companyMan to set
	 */
	public void setCompanyMan(String companyMan) {
		this.companyMan = companyMan;
	}

	/**
	 * @return the mainPerson
	 */
	public String getMainPerson() {
		return mainPerson;
	}

	/**
	 * @param mainPerson the mainPerson to set
	 */
	public void setMainPerson(String mainPerson) {
		this.mainPerson = mainPerson;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the scopeBusiness
	 */
	public String getScopeBusiness() {
		return scopeBusiness;
	}

	/**
	 * @param scopeBusiness the scopeBusiness to set
	 */
	public void setScopeBusiness(String scopeBusiness) {
		this.scopeBusiness = scopeBusiness;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	
}
