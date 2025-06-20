package com.bonus.cost.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

/**
 * 工程领退物资明细实体类
 * @author syruan
 */
public class ProjectLeaseCostDetail {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 机具名称
     */
    private String machineName;

    /**
     * 机具类型名称
     */
    private String machineTypeName;

    /**
     * 机具类型ID
     */
    private Integer machineTypeId;

    /**
     * 机具规格
     */
    private String machineModel;

    /**
     * 租赁单价/天
     */
    private Double price;

    /**
     * 物资单位
     */
    private String machineUnit;

    /**
     * 设备编码
     */
    private String machineCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 协议号
     */
    private String protocolNumber;

    /**
     * 任务单号
     */
    private String taskCode;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 工程ID
     */
    private String projectId;

    /**
     * 领料单位
     */
    private String leaseUnit;

    /**
     * 操作类型(1领料 2退料)
     */
    private Byte operateType;

    /**
     * 是否数量管理(0编码管理 1数量管理)
     */
    private Byte isCount;

    /**
     * 领料数量
     */
    private Integer leaseNum;

    /**
     * 退料数量
     */
    private Integer returnNum;

    /**
     * 操作人员名称
     */
    private String operatePersonName;

    /**
     * 操作日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String operateDate;

    /**
     * 操作时间
     * 注意：数据库字段为varchar类型，需要自定义转换逻辑
     * 格式应为"yyyy-MM-dd HH:mm:ss"
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String operateTime;

    /**
     * 查询开始时间
     */
    private String startTime;

    /**
     * 查询结束时间
     */
    private String endTime;

    /**
     * 查询关键字
     */
    private Map<String, Object> keyWord;

    public Map<String, Object> getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(Map<String, Object> keyWord) {
        this.keyWord = keyWord;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(Integer machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public Byte getIsCount() {
        return isCount;
    }

    public void setIsCount(Byte isCount) {
        this.isCount = isCount;
    }

    public String getMachineUnit() {
        return machineUnit;
    }

    public void setMachineUnit(String machineUnit) {
        this.machineUnit = machineUnit;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }


    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }
    
    public String getMachineName() {
        return machineName;
    }
    
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public String getMachineModel() {
        return machineModel;
    }
    
    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel;
    }
    
    public String getMachineCode() {
        return machineCode;
    }
    
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getProtocolNumber() {
        return protocolNumber;
    }
    
    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getLeaseUnit() {
        return leaseUnit;
    }

    public void setLeaseUnit(String leaseUnit) {
        this.leaseUnit = leaseUnit;
    }

    public Integer getLeaseNum() {
        return leaseNum;
    }

    public void setLeaseNum(Integer leaseNum) {
        this.leaseNum = leaseNum;
    }

    public String getOperatePersonName() {
        return operatePersonName;
    }

    public void setOperatePersonName(String operatePersonName) {
        this.operatePersonName = operatePersonName;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getMachineTypeName() {
        return machineTypeName;
    }

    public void setMachineTypeName(String machineTypeName) {
        this.machineTypeName = machineTypeName;
    }
}
