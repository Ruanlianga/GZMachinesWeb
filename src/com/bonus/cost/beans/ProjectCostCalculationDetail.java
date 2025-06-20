package com.bonus.cost.beans;

import java.util.List;

/**
 * 工程费用计算结果明细实体类
 * @author syruan
 */
public class ProjectCostCalculationDetail {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 计算结果主表ID
     */
    private Integer calculationId;

    /**
     * 机具类型ID
     */
    private Integer machineTypeId;

    /**
     * 机具类型名称
     */
    private String machineTypeName;

    /**
     * 机具规格
     */
    private String machineModel;

    /**
     * 物资单位
     */
    private String machineUnit;

    /**
     * 单价
     */
    private Double price;

    /**
     * 当前数量
     */
    private Integer currentCount;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 第一次领料时间
     */
    private String firstLeaseTime;

    /**
     * 最后一次退料时间
     */
    private String lastReturnTime;

    /**
     * 时间段列表（非数据库字段）
     */
    private List<ProjectCostCalculationSegment> segments;
    
    /**
     * 领料和退料记录列表（非数据库字段）
     */
    private List<ProjectLeaseCostDetail> details;

    // getter和setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(Integer calculationId) {
        this.calculationId = calculationId;
    }

    public Integer getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(Integer machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public String getMachineTypeName() {
        return machineTypeName;
    }

    public void setMachineTypeName(String machineTypeName) {
        this.machineTypeName = machineTypeName;
    }

    public String getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel;
    }

    public String getMachineUnit() {
        return machineUnit;
    }

    public void setMachineUnit(String machineUnit) {
        this.machineUnit = machineUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFirstLeaseTime() {
        return firstLeaseTime;
    }

    public void setFirstLeaseTime(String firstLeaseTime) {
        this.firstLeaseTime = firstLeaseTime;
    }

    public String getLastReturnTime() {
        return lastReturnTime;
    }

    public void setLastReturnTime(String lastReturnTime) {
        this.lastReturnTime = lastReturnTime;
    }

    public List<ProjectCostCalculationSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<ProjectCostCalculationSegment> segments) {
        this.segments = segments;
    }

    public List<ProjectLeaseCostDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ProjectLeaseCostDetail> details) {
        this.details = details;
    }
} 