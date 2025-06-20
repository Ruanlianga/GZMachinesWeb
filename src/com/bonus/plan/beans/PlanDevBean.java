package com.bonus.plan.beans;

import java.util.List;

public class PlanDevBean {
	
	private String id;
	
	private String name;
	
	
	private String pId;
	
	private String unit;
	
	private String level;
	
	
	private String pName;
	
	
	private String ppName;
	/**
	 * 子集合
	 */
	private List<PlanDevBean> childer;
	/**
	 * 
	 *  子设备数量
	 *  */
	private String num;
	
	
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getPpName() {
		return ppName;
	}
	public void setPpName(String ppName) {
		this.ppName = ppName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public List<PlanDevBean> getChilder() {
		return childer;
	}
	public void setChilder(List<PlanDevBean> childer) {
		this.childer = childer;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
	

}
