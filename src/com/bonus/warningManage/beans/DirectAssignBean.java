package com.bonus.warningManage.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * @Author 无畏
 * @Date   2019-04-24
 * @Table  bm_direct_assign
 * @Model  工地直转任务实体类
 */
public class DirectAssignBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id; //直转单id
    
    private Integer backProjectId;//直转退料方工程id
	
    private Integer collerProjectId;//直转接收方工程id
    
    private String backUnitName; //退料单位名称
    
    private String backAgreementCode;//退料协议协议编码
    
    private String collerAgreementCode;//领料协议编码
    
    private Integer backAgreementId;//退料协议协议id
    
    private Integer collerAgreementId;//领料协议id
    
    private String collerUnitName;//领料单位名称
    
    private String collerUnitId;//领料单位id
    
    private String backUnitId;//退料单位id
    
    private String backProjectName;//直转退料方工程名
	
    private String collerProjectName;//直转接收方工程名
    
    private String collerName;//接收人姓名
    
    private String collerPhone;//接收人电话

    private String backerName;//退料人姓名
    
    private String backerPhone;//退料人电话
    
    private Date createTime;//创建时间
    
    private Date updateTime;//修改时间
    
    private Date assignTime;//直接执行时间
    
    private Integer isTrue;//是否已经完成直转标识符
    
    private String remark;//备注
    
    private String keyWord;//关键字
    
    private String imgPath;//图片路径
    
    private DirectAssignObjectBean[] machines;//直转单内的机具数组
    
    private DirectAssignObjectBean[] updateCodeMachines;//直转单内的机具数组
    
    private DirectAssignObjectBean[] newMachines;//直转单内的机具数组
    
    private DirectAssignObjectBean[] updateTypeMachines;//直转单内的机具数组

    private String handleTime;
    
    
    public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	@Override
    public String toString() {
	return "DirectAssignBean [id=" + id + ", backProjectId=" + backProjectId + ", collerProjectId="
		+ collerProjectId + ", backUnitName=" + backUnitName + ", backAgreementCode=" + backAgreementCode
		+ ", collerAgreementCode=" + collerAgreementCode + ", collerUnitName=" + collerUnitName
		+ ", collerUnitId=" + collerUnitId + ", backUnitId=" + backUnitId + ", backProjectName="
		+ backProjectName + ", collerProjectName=" + collerProjectName + ", collerName=" + collerName
		+ ", collerPhone=" + collerPhone + ", backerName=" + backerName + ", backerPhone=" + backerPhone
		+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", assignTime=" + assignTime
		+ ", isTrue=" + isTrue + ", remark=" + remark + ", keyWord=" + keyWord + ", machines="
		+ Arrays.toString(machines) + "]";
    }

    public DirectAssignObjectBean[] getMachines() {
        return machines;
    }

    public void setMachines(DirectAssignObjectBean[] machines) {
        this.machines = machines;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBackProjectId() {
        return backProjectId;
    }

    public void setBackProjectId(Integer backProjectId) {
        this.backProjectId = backProjectId;
    }

    public Integer getCollerProjectId() {
        return collerProjectId;
    }

    public void setCollerProjectId(Integer collerProjectId) {
        this.collerProjectId = collerProjectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    public String getCollerName() {
        return collerName;
    }

    public void setCollerName(String collerName) {
        this.collerName = collerName;
    }

    public String getCollerPhone() {
        return collerPhone;
    }
    
    public void setCollerPhone(String collerPhone) {
        this.collerPhone = collerPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBackerName() {
        return backerName;
    }

    public void setBackerName(String backerName) {
        this.backerName = backerName;
    }

    public String getBackerPhone() {
        return backerPhone;
    }

    public void setBackerPhone(String backerPhone) {
        this.backerPhone = backerPhone;
    }

    public String getBackProjectName() {
        return backProjectName;
    }

    public void setBackProjectName(String backProjectName) {
        this.backProjectName = backProjectName;
    }

    public String getCollerProjectName() {
        return collerProjectName;
    }

    public void setCollerProjectName(String collerProjectName) {
        this.collerProjectName = collerProjectName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getBackUnitName() {
        return backUnitName;
    }

    public void setBackUnitName(String backUnitName) {
        this.backUnitName = backUnitName;
    }

    public String getCollerUnitName() {
        return collerUnitName;
    }

    public void setCollerUnitName(String collerUnitName) {
        this.collerUnitName = collerUnitName;
    }

    public String getCollerUnitId() {
        return collerUnitId;
    }

    public void setCollerUnitId(String collerUnitId) {
        this.collerUnitId = collerUnitId;
    }

    public String getBackUnitId() {
        return backUnitId;
    }

    public void setBackUnitId(String backUnitId) {
        this.backUnitId = backUnitId;
    }

    public String getBackAgreementCode() {
        return backAgreementCode;
    }

    public void setBackAgreementCode(String backAgreementCode) {
        this.backAgreementCode = backAgreementCode;
    }

    public String getCollerAgreementCode() {
        return collerAgreementCode;
    }

    public void setCollerAgreementCode(String collerAgreementCode) {
        this.collerAgreementCode = collerAgreementCode;
    }

    public DirectAssignObjectBean[] getUpdateCodeMachines() {
        return updateCodeMachines;
    }

    public void setUpdateCodeMachines(DirectAssignObjectBean[] updateCodeMachines) {
        this.updateCodeMachines = updateCodeMachines;
    }

    public DirectAssignObjectBean[] getUpdateTypeMachines() {
        return updateTypeMachines;
    }

    public void setUpdateTypeMachines(DirectAssignObjectBean[] updateTypeMachines) {
        this.updateTypeMachines = updateTypeMachines;
    }

    public DirectAssignObjectBean[] getNewMachines() {
        return newMachines;
    }

    public void setNewMachines(DirectAssignObjectBean[] newMachines) {
        this.newMachines = newMachines;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getBackAgreementId() {
        return backAgreementId;
    }

    public void setBackAgreementId(Integer backAgreementId) {
        this.backAgreementId = backAgreementId;
    }

    public Integer getCollerAgreementId() {
        return collerAgreementId;
    }

    public void setCollerAgreementId(Integer collerAgreementId) {
        this.collerAgreementId = collerAgreementId;
    }
}
