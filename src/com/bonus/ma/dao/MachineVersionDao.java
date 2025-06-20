package com.bonus.ma.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.MachineVersionBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface MachineVersionDao extends BaseDao<MachineVersionBean> {

	List<MachineVersionBean> getDeviceTypeList();

	String getDeviceType(String typeId);



}
