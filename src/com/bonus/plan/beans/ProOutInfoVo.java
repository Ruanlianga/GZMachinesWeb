package com.bonus.plan.beans;

import java.util.List;

/**
 * @author : 阮世耀
 * @version : 1.0
 * @PackagePath: com.bonus.plan.beans
 * @CreateTime: 2025-03-23  15:09
 * @Description: 工程需求计划出库信息
 */
public class ProOutInfoVo {

    private String id;
    /**
     * 发货人
     */
    private String userName;
    /**
     * 发货时间
     */
    private String createDay;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 工程id
     */
    private String proId;
    /**
     * 出货集合
     */
//    private List<ProOutDetail> list;
    /**
     * 新增数据集合
     */
//    private List<ProAddInfoDetails> addList;

    private String creater;

    private String createUser;


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDay() {
        return createDay;
    }

    public void setCreateDay(String createDay) {
        this.createDay = createDay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
