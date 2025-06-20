package com.bonus.plan.beans;


/**
 * 工程需要数据
 */
public class ProNeedInfoBean {
    /**
     * 主键
     */
    private String id;
    /**
     * 工程id
     */
    private String proId;

	private Integer planOutId;
    /**
     * 类型
     */
    private String type;
    /**
     * 名称
     */
    private String name;
    /**
     * 规格型号
     */
    private String module;
    /**
     * 规格id
     */
    private String moduleId;
    /**
     * 需求类型 1计划 2新增
     */
    private String needType;
    /**
     * 单位
     */
    private String unit;
    /**
     * 需要数量
     */
    private int needNum;
    /**
     * 发货数量
     */
    private int fhNum;
    /**
     * 调整量
     */
    private int tzNum;
    /**
     * 备注
     */
    private String remarks;

	public Integer getPlanOutId() {
		return planOutId;
	}

	public void setPlanOutId(Integer planOutId) {
		this.planOutId = planOutId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
     * 数据类型
     */
	private String dataType;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getNeedType() {
		return needType;
	}
	public void setNeedType(String needType) {
		this.needType = needType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getNeedNum() {
		return needNum;
	}
	public void setNeedNum(int needNum) {
		this.needNum = needNum;
	}
	public int getFhNum() {
		return fhNum;
	}
	public void setFhNum(int fhNum) {
		this.fhNum = fhNum;
	}
	public int getTzNum() {
		return tzNum;
	}
	public void setTzNum(int tzNum) {
		this.tzNum = tzNum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



}
