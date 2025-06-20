
package com.bonus.newSettlement.beans;

import com.bonus.lease.beans.AgreementBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;

/**
 * @Author js
 * @Date   2020-06-11
 * @Table  ma_type_project_storage
 * @Model  工程库存表
 */
public class MaTypeProjectStorageBean  implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	    
	private Integer id;//
	
	private String agreementId;
	
	private AgreementBean agreement;//协议
		
	private MachineTypeBean type;//工器具
	
	private MachineBean machine;//机具具
		
	private Float num;//
	
	private Float price;
	
	private String startDate;
	
	private String endDate;
	
	private String backDate;
	
	private String lastSltDate;
	
	private Integer status;
	
	private Integer isSlt;
	
	private Integer batchFlag;

	private String isActive;
	
	private String settlementTimes;
	
	private String remarks;
	
	private String isCount;
	
	private String st;
	
	private String dl;
	
	private String uploadFile;
	
	
	
	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getDl() {
		return dl;
	}

	public void setDl(String dl) {
		this.dl = dl;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AgreementBean getAgreement() {
		return agreement;
	}

	public void setAgreement(AgreementBean agreement) {
		this.agreement = agreement;
	}

	public MachineTypeBean getType() {
		return type;
	}

	public void setType(MachineTypeBean type) {
		this.type = type;
	}

	public MachineBean getMachine() {
		return machine;
	}

	public void setMachine(MachineBean machine) {
		this.machine = machine;
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

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public String getLastSltDate() {
		return lastSltDate;
	}

	public void setLastSltDate(String lastSltDate) {
		this.lastSltDate = lastSltDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsSlt() {
		return isSlt;
	}

	public void setIsSlt(Integer isSlt) {
		this.isSlt = isSlt;
	}

	public Integer getBatchFlag() {
		return batchFlag;
	}

	public void setBatchFlag(Integer batchFlag) {
		this.batchFlag = batchFlag;
	}
	

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getSettlementTimes() {
		return settlementTimes;
	}

	public void setSettlementTimes(String settlementTimes) {
		this.settlementTimes = settlementTimes;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	public String getIsCount() {
		return isCount;
	}

	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}

	@Override
	public String toString() {
		return "MaTypeProjectStorageBean [id=" + id + ", agreement=" + agreement + ", type=" + type + ", machine="
				+ machine + ", num=" + num + ", price=" + price + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", backDate=" + backDate + ", lastSltDate=" + lastSltDate + ", status=" + status + ", isSlt=" + isSlt
				+ ", batchFlag=" + batchFlag + "]";
	}
	
	
		
}