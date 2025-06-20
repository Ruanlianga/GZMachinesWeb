
package com.bonus.newSettlement.beans;

import java.util.Arrays;
import java.util.List;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.bm.beans.ProjectManageBean;
import com.bonus.lease.beans.AgreementBean;
import com.bonus.sys.beans.UserBean;

/**
 * @Author js
 * @Date   2020-06-11
 * @Table  project_settlement_info
 * @Model  结算基本信息表
 */
public class ProjectSettlementInfoBean  implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	    
	private Integer id; 
	
	private String orgId;
	
	private String code;
	    
	private String machineMoney;
	
	private String typeMoney;
	
	private String baseMoney;
	
	private String lastMoney;
	
	private String addMoney;
	
	private String subMoney;
	
	private String cpsMoney;
		
	private String status;
	
	private String sltMan;
	
	private String sltDate;
	
	private String sltManPhone;
	
	private String keyWord;
	
	private String nums;
	
	private AgreementBean agreement;//协议
	
	private CompanyBean company;//往来单位
	
	private ProjectManageBean project;//工程
	
	private ProjectSettlementDetailsBean[] items;
	
	private List<ProjectSettlementDetailsBean> mas;
	
	private Integer[] chks;
	
    private String createTime;
    
    private UserBean creator;
    
    private UserBean updater;
    
    private String updateTime;
    
    private String remarks;
    
    private String isFixedAssets; //是否固定资产
    
    private String isActive;
    private String cpyName;
    private String cpyUnitName;
    private String fileUrl;
	private String oldFileUrl;
	private String companyName;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getCpyName() {
		return cpyName;
	}

	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}

	public String getCpyUnitName() {
		return cpyUnitName;
	}

	public void setCpyUnitName(String cpyUnitName) {
		this.cpyUnitName = cpyUnitName;
	}

	public String getIsFixedAssets() {
		return isFixedAssets;
	}

	public void setIsFixedAssets(String isFixedAssets) {
		this.isFixedAssets = isFixedAssets;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer[] getChks() {
		return chks;
	}

	public void setChks(Integer[] chks) {
		this.chks = chks;
	}

	public List<ProjectSettlementDetailsBean> getMas() {
		return mas;
	}

	public void setMas(List<ProjectSettlementDetailsBean> mas) {
		this.mas = mas;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSltMan() {
		return sltMan;
	}

	public void setSltMan(String sltMan) {
		this.sltMan = sltMan;
	}

	public String getSltDate() {
		return sltDate;
	}

	public void setSltDate(String sltDate) {
		this.sltDate = sltDate;
	}

	public String getSltManPhone() {
		return sltManPhone;
	}

	public void setSltManPhone(String sltManPhone) {
		this.sltManPhone = sltManPhone;
	}

	public AgreementBean getAgreement() {
		return agreement;
	}

	public void setAgreement(AgreementBean agreement) {
		this.agreement = agreement;
	}

	public CompanyBean getCompany() {
		return company;
	}

	public void setCompany(CompanyBean company) {
		this.company = company;
	}

	public ProjectManageBean getProject() {
		return project;
	}

	public void setProject(ProjectManageBean project) {
		this.project = project;
	}
	

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public UserBean getCreator() {
		return creator;
	}

	public void setCreator(UserBean creator) {
		this.creator = creator;
	}

	public UserBean getUpdater() {
		return updater;
	}

	public void setUpdater(UserBean updater) {
		this.updater = updater;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getMachineMoney() {
		return machineMoney;
	}

	public void setMachineMoney(String machineMoney) {
		this.machineMoney = machineMoney;
	}

	public String getTypeMoney() {
		return typeMoney;
	}

	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}

	public String getBaseMoney() {
		return baseMoney;
	}

	public void setBaseMoney(String baseMoney) {
		this.baseMoney = baseMoney;
	}

	public String getLastMoney() {
		return lastMoney;
	}

	public void setLastMoney(String lastMoney) {
		this.lastMoney = lastMoney;
	}

	public String getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(String addMoney) {
		this.addMoney = addMoney;
	}

	public String getSubMoney() {
		return subMoney;
	}

	public void setSubMoney(String subMoney) {
		this.subMoney = subMoney;
	}

	public String getCpsMoney() {
		return cpsMoney;
	}

	public void setCpsMoney(String cpsMoney) {
		this.cpsMoney = cpsMoney;
	}

	public ProjectSettlementDetailsBean[] getItems() {
		return items;
	}

	public void setItems(ProjectSettlementDetailsBean[] items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ProjectSettlementInfoBean [id=" + id + ", code=" + code + ", machineMoney=" + machineMoney
				+ ", typeMoney=" + typeMoney + ", baseMoney=" + baseMoney + ", lastMoney=" + lastMoney + ", addMoney="
				+ addMoney + ", subMoney=" + subMoney + ", cpsMoney=" + cpsMoney + ", status=" + status + ", sltMan="
				+ sltMan + ", sltDate=" + sltDate + ", sltManPhone=" + sltManPhone + ", keyWord=" + keyWord
				+ ", agreement=" + agreement + ", company=" + company + ", project=" + project + ", items="
				+ Arrays.toString(items) + ", mas=" + mas + ", chks=" + Arrays.toString(chks) + ", createTime="
				+ createTime + ", creator=" + creator + ", updater=" + updater + ", updateTime=" + updateTime
				+ ", remarks=" + remarks + ", isFixedAssets=" + isFixedAssets + ", isActive=" + isActive + ", cpyName="
				+ cpyName + ", cpyUnitName=" + cpyUnitName + "]";
	}

	public String getOldFileUrl() {
		return oldFileUrl;
	}

	public void setOldFileUrl(String oldFileUrl) {
		this.oldFileUrl = oldFileUrl;
	}

	


	
}