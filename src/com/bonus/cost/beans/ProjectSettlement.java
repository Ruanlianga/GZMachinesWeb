package com.bonus.cost.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目结算记录实体类
 * @author syruan
 */
public class ProjectSettlement {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 工程ID
     */
    private String projectId;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 领料数量
     */
    private Integer leaseCount;

    /**
     * 退料数量
     */
    private Integer returnCount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 删除标记（0正常，1删除）
     */
    private Byte delFlag;

    /**
     * 领料明细列表（非数据库字段）
     */
    private List<ProjectLeaseCostDetail> leaseDetails;

    /**
     * 退料明细列表（非数据库字段）
     */
    private List<ProjectLeaseCostDetail> returnDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Integer getLeaseCount() {
        return leaseCount;
    }

    public void setLeaseCount(Integer leaseCount) {
        this.leaseCount = leaseCount;
    }

    public Integer getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(Integer returnCount) {
        this.returnCount = returnCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public List<ProjectLeaseCostDetail> getLeaseDetails() {
        return leaseDetails;
    }

    public void setLeaseDetails(List<ProjectLeaseCostDetail> leaseDetails) {
        this.leaseDetails = leaseDetails;
    }

    public List<ProjectLeaseCostDetail> getReturnDetails() {
        return returnDetails;
    }

    public void setReturnDetails(List<ProjectLeaseCostDetail> returnDetails) {
        this.returnDetails = returnDetails;
    }
} 