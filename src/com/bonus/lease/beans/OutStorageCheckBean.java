package com.bonus.lease.beans;
/**
 * 出库检验
 * @author xj
 *
 *
 */
public class OutStorageCheckBean extends OutStorageBean{

	private String id;
	private String supId;
	private String taskId;				//所属任务
	private String machineName;			//机具名称
	private String machineModel;		//机具规格
	private String machineStatus;       //机具状态
	private String typeId;			    //规格Id
	private String checkTime;		    //检验时间
	private String checkerId;           //检验人Id
	private String checker;             //检验人
	private String preCheckNum;         //预检验数量
	private String thisCheckNum;        //本次检验数量
	private String alCheckNum;          //已检验数量
	private String deviceCode;          //设备编码
	private String isCount;             //是否计数
	private String isActive;
	private String startTime;
	private String endTime;
	private String agreementId;
	private String agreementCode;
	private String leaseName;
	private String projectName;
	private String applyNumber;
	private String applyTime;
	private String keyWord;
	private String type;              //1检验任务详情
	private String totalPreCheckNum;  //预检验总数
	private String totalAlCheckNum;   //已检验总数
	private String maModelId;
	private String isSure;
	private String checkId;
	private String checkCount;
	private String isFinish;
	private String companyId;
	
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
	public String getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(String checkCount) {
		this.checkCount = checkCount;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getMaModelId() {
		return maModelId;
	}
	public void setMaModelId(String maModelId) {
		this.maModelId = maModelId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineModel() {
		return machineModel;
	}
	public void setMachineModel(String machineModel) {
		this.machineModel = machineModel;
	}
	public String getMachineStatus() {
		return machineStatus;
	}
	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckerId() {
		return checkerId;
	}
	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getPreCheckNum() {
		return preCheckNum;
	}
	public void setPreCheckNum(String preCheckNum) {
		this.preCheckNum = preCheckNum;
	}
	
	public String getThisCheckNum() {
		return thisCheckNum;
	}
	public void setThisCheckNum(String thisCheckNum) {
		this.thisCheckNum = thisCheckNum;
	}
	public String getAlCheckNum() {
		return alCheckNum;
	}
	public void setAlCheckNum(String alCheckNum) {
		this.alCheckNum = alCheckNum;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getIsCount() {
		return isCount;
	}
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getAgreementCode() {
		return agreementCode;
	}
	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}
	public String getLeaseName() {
		return leaseName;
	}
	public void setLeaseName(String leaseName) {
		this.leaseName = leaseName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getApplyNumber() {
		return applyNumber;
	}
	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTotalPreCheckNum() {
		return totalPreCheckNum;
	}
	public void setTotalPreCheckNum(String totalPreCheckNum) {
		this.totalPreCheckNum = totalPreCheckNum;
	}
	public String getTotalAlCheckNum() {
		return totalAlCheckNum;
	}
	public void setTotalAlCheckNum(String totalAlCheckNum) {
		this.totalAlCheckNum = totalAlCheckNum;
	}
	public String getIsSure() {
		return isSure;
	}
	public void setIsSure(String isSure) {
		this.isSure = isSure;
	}
	
	
}
