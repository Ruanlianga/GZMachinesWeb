package com.bonus.ma.service;

import java.util.List;

import com.bonus.ma.beans.GpsBindingBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface   GpsBindingService extends BaseService<GpsBindingBean>{
	/**
	 *检验gisCode唯一性 
	 * @param o
	 * @return
	 */
	List<GpsBindingBean> getGisCodeBylist(GpsBindingBean o);

	String updateGisCode(GpsBindingBean o);

	String unBoundGps(GpsBindingBean o);
	
	Page<GpsBindingBean> findGpsFlowPage(GpsBindingBean o, Page<GpsBindingBean> page);

}
