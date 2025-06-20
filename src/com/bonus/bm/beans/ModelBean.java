package com.bonus.bm.beans;

public class ModelBean {

	private String id;
	private String type;
	private String name;
	
	private String keyWord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "ModelBean [id=" + id + ", type=" + type + ", name=" + name + ", keyWord=" + keyWord + "]";
	}
	
	
}
