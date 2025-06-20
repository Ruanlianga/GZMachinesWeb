package com.bonus.data.service;

import com.bonus.data.beans.GpsDataBean;
import com.bonus.sys.BaseService;

public interface GpsDataService extends BaseService<GpsDataBean>{

	String getAccessToken(GpsDataBean bean,String token);

	String getPosInfo(GpsDataBean bean, String token, String imeis);

}
