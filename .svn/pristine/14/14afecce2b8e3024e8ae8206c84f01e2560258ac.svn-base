package com.bonus.ma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.GpsBindingBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface GpsBindingDao extends  BaseDao<GpsBindingBean> {
	/**
	 * 检验gisCode唯一性
	 * @param o
	 * @return
	 */
	List<GpsBindingBean> getGisCodeBylist(GpsBindingBean o);
	/**
	 * gisCode 保存
	 * @param bean
	 * @return
	 */
	int updateGisCode(GpsBindingBean bean);
	/**
	 * gisCode流水保存
	 * @param bean
	 */
	void insertGisCodeFlow(GpsBindingBean bean);
	/**
	 * 对gps进行解绑
	 * @param o
	 */
	int unBoundGps(GpsBindingBean o);
	/**
	 * 
	 * @param o
	 * @param page
	 * @return
	 */
	public List<GpsBindingBean> findGpsFlowPage(@Param("param") GpsBindingBean o, @Param("t") Page<GpsBindingBean> page);
	/**
	 * @param bean
	 */
	void insertGpsBding(GpsBindingBean bean);
	
	void deleteGpsBding(GpsBindingBean bean);
	/**
	 * gps绑定插入定位数据
	 * @param bean
	 */
	void insertGssLocation(GpsBindingBean bean);
	
	void deleteGpsCode(GpsBindingBean o);
}
