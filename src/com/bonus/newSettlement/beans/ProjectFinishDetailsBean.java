
package com.bonus.newSettlement.beans;

import com.bonus.ma.beans.MachineTypeBean;

/**
 * @Author js
 * @Date   2021-10-07
 * @Table  project_finish_details
 * @Model  工程完结详情表
 */
public class ProjectFinishDetailsBean  implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	    
	private String id;//
	
	private String sltInfo;
	
	private String agreementId;
	
	private String typeId;
	
	private String machineId;
	
	private String deviceName;
	
	private String deviceModel;
	
	private String deviceUnit;
	
	private String deviceCode;
	
	private String isCount;
	
	private Float num;
	
	private Float price;
	
	private String backDate;
	
	private String startDate;
	
	private String endDate;
	
	private String remarks;

	private String fileUrl;
	
	private String isFinish;
	
	private String dayLen;
	
	private String status;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSltInfo() {
		return sltInfo;
	}

	public void setSltInfo(String sltInfo) {
		this.sltInfo = sltInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceUnit() {
		return deviceUnit;
	}

	public void setDeviceUnit(String deviceUnit) {
		this.deviceUnit = deviceUnit;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getIsCount() {
		return isCount;
	}

	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

	public String getDayLen() {
		return dayLen;
	}

	public void setDayLen(String dayLen) {
		this.dayLen = dayLen;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	
	
	
}