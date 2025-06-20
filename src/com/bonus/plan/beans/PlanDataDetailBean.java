package com.bonus.plan.beans;

/**
 * 新增内容详情
 * @author xj
 *
 *
 */
public class PlanDataDetailBean {
	
	/**
	 * 类型		
	 */
	private String  applyId;
	
	/**
	 * 类型		
	 */
	private String  type;
	/**
	 * 名称
	 */
	private String typeName;
	/**
	 * 规格
	 */
	private String module;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 需用量
	 */
	private int needNum;
	/**
	 * 需用天数
	 */
	private String times;
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 规格id
	 */
	private String moduleId;
	
	
	

	public String getApplyId() {
		return applyId;
	}


	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getModule() {
		return module;
	}


	public void setModule(String module) {
		this.module = module;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public int getNeedNum() {
		return needNum;
	}


	public void setNeedNum(int needNum) {
		this.needNum = needNum;
	}


	public String getTimes() {
		return times;
	}


	public void setTimes(String times) {
		this.times = times;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getModuleId() {
		return moduleId;
	}


	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	
	
	

}
