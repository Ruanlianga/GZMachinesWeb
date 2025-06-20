package com.bonus.doc.docutil;

import org.apache.ibatis.type.Alias;

@Alias("DocVariousCountBean")
public class DocVariousCountBean {
	
	private String unit;
	private String project_create_time;
	private String project_num;
	private String avg_time;
	private String yearmonth;
	private String subordinate;
	private String regiontype;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProject_create_time() {
		return project_create_time;
	}
	public void setProject_create_time(String project_create_time) {
		this.project_create_time = project_create_time;
	}
	public String getProject_num() {
		return project_num;
	}
	public void setProject_num(String project_num) {
		this.project_num = project_num;
	}
	public String getAvg_time() {
		return avg_time;
	}
	public void setAvg_time(String avg_time) {
		this.avg_time = avg_time;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public String getSubordinate() {
		return subordinate;
	}
	public void setSubordinate(String subordinate) {
		this.subordinate = subordinate;
	}
	public String getRegiontype() {
		return regiontype;
	}
	public void setRegiontype(String regiontype) {
		this.regiontype = regiontype;
	}
	@Override
	public String toString() {
		return "DocVariousCountBean [unit=" + unit + ", project_create_time=" + project_create_time + ", project_num="
				+ project_num + ", avg_time=" + avg_time + ", yearmonth=" + yearmonth + ", subordinate=" + subordinate
				+ ", regiontype=" + regiontype + "]";
	}

}
