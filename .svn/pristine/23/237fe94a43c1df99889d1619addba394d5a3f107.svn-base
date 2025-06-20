package com.bonus.index.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.index.beans.PositionBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;

public interface PositionService extends BaseService<PositionBean>{

	String gpsCode(@Param("param") PositionBean bean);
	String gpsCodeBind(@Param("param") PositionBean bean);
	List<PositionBean> findBindPosition(PositionBean o);
	List<PositionBean> findAll();
	Page<PositionBean> findDeviceDetail(@Param("param") PositionBean o, Page<PositionBean> page);
	
	List<PositionBean> findNoPage(PositionBean o);
	
	Page<PositionBean> findInLibDevice(@Param("param") PositionBean o, Page<PositionBean> page);
	List<ZNode> getMainTree(PositionBean o);
	List<PositionBean> findOldGpsData(PositionBean o);
	
	Page<PositionBean> findOldGpsDatas(PositionBean o, Page<PositionBean> page);
	List<CompanyBean> getTypeName();
	List<CompanyBean> getDeviceModel();
	List<CompanyBean> getDeviceCode();
	List<CompanyBean> getCode();
	List<ZNode> getMainSpecialTree(PositionBean o);
}
