package com.bonus.cost.beans;

/**
 * 工程费用计算结果时间段实体类
 * @author syruan
 */
public class ProjectCostCalculationSegment {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 计算结果明细ID
     */
    private Integer calculationDetailId;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金额
     */
    private Double amount;

    // getter和setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalculationDetailId() {
        return calculationDetailId;
    }

    public void setCalculationDetailId(Integer calculationDetailId) {
        this.calculationDetailId = calculationDetailId;
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

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
} 