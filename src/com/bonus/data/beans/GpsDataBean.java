package com.bonus.data.beans;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("gpsData")
public class GpsDataBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String gpsCode;			//定位装置编号----定位装置IMEI号
	private String deviceType;		//设备类型
	private String deviceModel;		//设备规格
	private String deviceCode;		//设备编码
	private String collectTime;		//采集时间
	private String lon;				//经度
	private String lat;				//纬度
	private String gpsStatus;		//gps状态------0,离线; 1,在线
	private String posType;			//卫星定位-GPS, 基站定位-LBS, WIFI定位-WIFI, 蓝牙定位-BEACON
	private String hbTime;			//心跳时间
	private String activationFlag;	//是否激活 1-激活 0-未激活
	private String expireFlag;		//是否过期 1-未过期 0-过期
	private String electQuantity;	//设备电量(0-100)
	private String speed;			//速度 (单位:km/h)
	private String powerValue;		//外电电压(0-100)
	private String accStatus;		//Acc状态 0:关闭 1:打开
	private String upTime;			//上传时间
	private String locDesc;			//GPS位置信息
	private String direction;			//方位
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGpsCode() {
		return gpsCode;
	}
	public void setGpsCode(String gpsCode) {
		this.gpsCode = gpsCode;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
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
	public String getGpsStatus() {
		return gpsStatus;
	}
	public void setGpsStatus(String gpsStatus) {
		this.gpsStatus = gpsStatus;
	}
	public String getPosType() {
		return posType;
	}
	public void setPosType(String posType) {
		this.posType = posType;
	}
	public String getHbTime() {
		return hbTime;
	}
	public void setHbTime(String hbTime) {
		this.hbTime = hbTime;
	}
	public String getActivationFlag() {
		return activationFlag;
	}
	public void setActivationFlag(String activationFlag) {
		this.activationFlag = activationFlag;
	}
	public String getExpireFlag() {
		return expireFlag;
	}
	public void setExpireFlag(String expireFlag) {
		this.expireFlag = expireFlag;
	}
	public String getElectQuantity() {
		return electQuantity;
	}
	public void setElectQuantity(String electQuantity) {
		this.electQuantity = electQuantity;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getPowerValue() {
		return powerValue;
	}
	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getUpTime() {
		return upTime;
	}
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
