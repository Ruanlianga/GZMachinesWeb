package com.bonus.index.beans;

import java.util.List;

import com.bonus.data.beans.GpsDataBean;
public class PositionBean extends GpsDataBean{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String code;//gps编号
	private String status;
	private String lon;
	private String lat;
	private String uploadCycle;
	private String isActive;
	private String keyWord;
	private String gpsCode;
	private String points;
	private String fenceName;
	private String newgpsCode;
	private String alarmType; 
	private String typeName;
	private String typeNameId;
	private String deviceCode;
	private String deviceModel;
	private List<Integer> nearbyIds;
	private Integer nums;			
	private Integer jNum;			//机动绞磨数量
	private Integer sNum;			//手扶机绞磨数量
	private String pId;
	private String name;
	private String LEVEL;
	private String address;
	private String proName;
	private String useUnit;
	private String useTime;
	private String startTime;
	private String endTime;
	private List<PositionBean> oldGps;
	
	private String deviceType;
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getTypeNameId() {
		return typeNameId;
	}
	public void setTypeNameId(String typeNameId) {
		this.typeNameId = typeNameId;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	public List<PositionBean> getOldGps() {
		return oldGps;
	}
	public void setOldGps(List<PositionBean> oldGps) {
		this.oldGps = oldGps;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(String lEVEL) {
		LEVEL = lEVEL;
	}
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public List<Integer> getNearbyIds() {
		return nearbyIds;
	}
	public void setNearbyIds(List<Integer> nearbyIds) {
		this.nearbyIds = nearbyIds;
	}
	@Override
	public String toString() {
		return "PositionBean [id=" + id + ", code=" + code + ", status=" + status + ", lon=" + lon + ", lat=" + lat
				+ ", uploadCycle=" + uploadCycle + ", isActive=" + isActive + ", keyWord=" + keyWord + ", gpsCode="
				+ gpsCode + ", points=" + points + ", fenceName=" + fenceName + ", newgpsCode=" + newgpsCode
				+ ", alarmType=" + alarmType + ", typeName=" + typeName + "]";
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNewgpsCode() {
		return newgpsCode;
	}
	public void setNewgpsCode(String newgpsCode) {
		this.newgpsCode = newgpsCode;
	}
	public String getGpsCode() {
		return gpsCode;
	}
	public void setGpsCode(String gpsCode) {
		this.gpsCode = gpsCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUploadCycle() {
		return uploadCycle;
	}
	public void setUploadCycle(String uploadCycle) {
		this.uploadCycle = uploadCycle;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getFenceName() {
		return fenceName;
	}
	public void setFenceName(String fenceName) {
		this.fenceName = fenceName;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public Integer getjNum() {
		return jNum;
	}
	public void setjNum(Integer jNum) {
		this.jNum = jNum;
	}
	public Integer getsNum() {
		return sNum;
	}
	public void setsNum(Integer sNum) {
		this.sNum = sNum;
	}
	
}
