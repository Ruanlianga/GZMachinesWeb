package com.bonus.bm.beans;

/**
 * 区域管理
 * @author xj
 *
 */
public class HouseAreaBean {
	private String id;
	private String length;			//长
	private String width;
	private String area;			//面积
	private String lon;				//经度---x轴
	private String lat;				//纬度---y轴
	private String areaType;		//区域类型
	private String houseName;		//所属仓库
	private String orgName;
	private String orgId;
	private String keyWord;		//查询字段
	private String stand;//规格
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getStand() {
		return stand;
	}
	public void setStand(String stand) {
		this.stand = stand;
	}
	@Override
	public String toString() {
		return "HouseAreaBean [id=" + id + ", length=" + length + ", width=" + width + ", area=" + area + ", lon=" + lon
				+ ", lat=" + lat + ", areaType=" + areaType + ", houseName=" + houseName + ", orgName=" + orgName
				+ ", orgId=" + orgId + ", keyWord=" + keyWord + ", stand=" + stand + "]";
	}
	
	
	
}
