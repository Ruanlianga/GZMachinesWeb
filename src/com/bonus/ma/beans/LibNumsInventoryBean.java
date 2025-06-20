package com.bonus.ma.beans;

public class LibNumsInventoryBean {

	private String id;
	private String maTypeId;
	private String maTypeName;
	private String maModelId;
	private String maModelName;
	private String num; 				//库存数
	private String nums; 				//总保有量
	private String isProfit;			//是否盘盈
	private String inNums;				//盘入数量
	private String remark;
	private String createTime;			//创建时间
	private String unit;
	private String orgId;
	private String companyName;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaTypeId() {
		return maTypeId;
	}
	public void setMaTypeId(String maTypeId) {
		this.maTypeId = maTypeId;
	}
	public String getMaTypeName() {
		return maTypeName;
	}
	public void setMaTypeName(String maTypeName) {
		this.maTypeName = maTypeName;
	}
	public String getMaModelId() {
		return maModelId;
	}
	public void setMaModelId(String maModelId) {
		this.maModelId = maModelId;
	}
	public String getMaModelName() {
		return maModelName;
	}
	public void setMaModelName(String maModelName) {
		this.maModelName = maModelName;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	public String getIsProfit() {
		return isProfit;
	}
	public void setIsProfit(String isProfit) {
		this.isProfit = isProfit;
	}
	public String getInNums() {
		return inNums;
	}
	public void setInNums(String inNums) {
		this.inNums = inNums;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
