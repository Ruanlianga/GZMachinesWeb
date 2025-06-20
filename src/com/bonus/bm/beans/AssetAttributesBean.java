package com.bonus.bm.beans;

public class AssetAttributesBean {

	private String id;

	private String value;

	private String name; // 资产属性

	private String isActive;

	private String keyWord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "AssetAttributesBean [id=" + id + ", name=" + name + ", keyWord=" + keyWord + "]";
	}

}
