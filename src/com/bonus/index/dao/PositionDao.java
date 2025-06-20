package com.bonus.index.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.core.BonusBatis;
import com.bonus.index.beans.PositionBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;


@BonusBatis
public interface PositionDao extends BaseDao<PositionBean>{
    //替换绑定
	String gpsCode(@Param("param") PositionBean bean);
	//绑定gps
    String gpsCodeBind(@Param("param") PositionBean bean);

	List<PositionBean> findBindPosition(PositionBean o);
	
	List<PositionBean> findAll();
	
	List<PositionBean> findDeviceDetail(@Param("param") PositionBean o, Page<PositionBean> page);
	
	List<PositionBean> findNoPage(@Param("param") PositionBean o);
	
	List<PositionBean> findInLibDevice(@Param("param") PositionBean o, Page<PositionBean> page);
	
	List<ZNode> getMainTree(PositionBean o);
	
	List<PositionBean> findOldGpsData(@Param("param") PositionBean o,Page<PositionBean> page);
	List<CompanyBean> getTypeName();
	List<CompanyBean> getDeviceModel();
	List<CompanyBean> getDeviceCode();
	List<CompanyBean> getCode();
	List<ZNode> getMainSpecialTree(PositionBean o);
}
