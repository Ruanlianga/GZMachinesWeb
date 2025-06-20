package com.bonus.newInput.beans;

import com.bonus.ma.beans.MachineBean;

public class NewInputQrcodeBean extends MachineBean{

	private String id;
	private String maModelId;// 规格Id
	private String qrCodeNum;// 合格数量,做为打印二维码数量
	private String fileUrl;// 验收附件
	private String checkTime;// 
	private String operator;
	private String operationTime;
	private String keyWord;
	private String startTime;
	private String endTime;
	private String isSure;// 任务状态
	private String maType;
	private String maModel;
	private String taskId;
	private String qrCodeStatus;//二维码打印状态
	private String qrCode;//二维码编码
	private String deviceCode;//设备编码
	private String genTime;//生成时间
	private String launchName;
	private String receiveName;
	private String launchTime;
	private String finishTime;
	private String checkStatus;
	private String maTypeId;
	private String maVender;
	private String customerRep;//客服代表
	private String checkNum;//检验数量
	private String qualifiedNum;//合格数量
	private String alreadyBindingNum;//已绑数量
	private String nums;//已扫描入库数量
	private String state;
	private String rmStatus;
	private String supId;
	private String thisInputNum;
	private String alInputNum;
	private String bindTime;
	private String inputTime;
	private String isFinish;
	private String companyId;
	private String maId;
	private String checker;//检验员
	private String batchStatus;

	 
	public String getBatchStatus() {
		return batchStatus;
	}
	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getMaId() {
		return maId;
	}
	public void setMaId(String maId) {
		this.maId = maId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getRmStatus() {
		return rmStatus;
	}
	public void setRmStatus(String rmStatus) {
		this.rmStatus = rmStatus;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getQualifiedNum() {
		return qualifiedNum;
	}
	public void setQualifiedNum(String qualifiedNum) {
		this.qualifiedNum = qualifiedNum;
	}
	public String getAlreadyBindingNum() {
		return alreadyBindingNum;
	}
	public void setAlreadyBindingNum(String alreadyBindingNum) {
		this.alreadyBindingNum = alreadyBindingNum;
	}
	public String getCustomerRep() {
		return customerRep;
	}
	public void setCustomerRep(String customerRep) {
		this.customerRep = customerRep;
	}
	public String getMaVender() {
		return maVender;
	}
	public void setMaVender(String maVender) {
		this.maVender = maVender;
	}
	public String getMaTypeId() {
		return maTypeId;
	}
	public void setMaTypeId(String maTypeId) {
		this.maTypeId = maTypeId;
	}
	public String getLaunchTime() {
		return launchTime;
	}
	public void setLaunchTime(String launchTime) {
		this.launchTime = launchTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getLaunchName() {
		return launchName;
	}
	public void setLaunchName(String launchName) {
		this.launchName = launchName;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaModelId() {
		return maModelId;
	}
	public void setMaModelId(String maModelId) {
		this.maModelId = maModelId;
	}
	public String getQrCodeNum() {
		return qrCodeNum;
	}
	public void setQrCodeNum(String qrCodeNum) {
		this.qrCodeNum = qrCodeNum;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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
	public String getIsSure() {
		return isSure;
	}
	public void setIsSure(String isSure) {
		this.isSure = isSure;
	}
	public String getMaType() {
		return maType;
	}
	public void setMaType(String maType) {
		this.maType = maType;
	}
	public String getMaModel() {
		return maModel;
	}
	public void setMaModel(String maModel) {
		this.maModel = maModel;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getQrCodeStatus() {
		return qrCodeStatus;
	}
	public void setQrCodeStatus(String qrCodeStatus) {
		this.qrCodeStatus = qrCodeStatus;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getGenTime() {
		return genTime;
	}
	public void setGenTime(String genTime) {
		this.genTime = genTime;
	}
	public String getThisInputNum() {
		return thisInputNum;
	}
	public void setThisInputNum(String thisInputNum) {
		this.thisInputNum = thisInputNum;
	}
	public String getAlInputNum() {
		return alInputNum;
	}
	public void setAlInputNum(String alInputNum) {
		this.alInputNum = alInputNum;
	}
	public String getBindTime() {
		return bindTime;
	}
	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

}
