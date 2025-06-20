package com.bonus.data.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.data.beans.GpsDataBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface GpsDataDao extends BaseDao<GpsDataBean>{

	String getDeviceCode(String gpsCode);

	List<GpsDataBean> findRealData();

	void addHisData(GpsDataBean bean);

	String findIsOnline(String gpsCode);

}
