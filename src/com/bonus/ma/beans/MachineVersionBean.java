package com.bonus.ma.beans;

/*
 * 2022.10.12 特殊设备业务（创建）
 * 
 */
public class MachineVersionBean{
	
	private String id;
	
	private String typeId;
	
	private String parentId;
	
	private String type;//1:固资2特殊设备
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
