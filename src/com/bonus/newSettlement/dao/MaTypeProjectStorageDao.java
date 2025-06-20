package com.bonus.newSettlement.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface MaTypeProjectStorageDao extends BaseDao<MaTypeProjectStorageBean> {

	List<MaTypeProjectStorageBean> findUnSltMaTypeList(MaTypeProjectStorageBean o);
	
	List<MaTypeProjectStorageBean> findUnSltMaTypeLists(MaTypeProjectStorageBean o);

	Integer updateBean(MaTypeProjectStorageBean storage);

	int insertBean(MaTypeProjectStorageBean temp);

	List<MaTypeProjectStorageBean> findCanBackListById(MaTypeProjectStorageBean storage);

	List<MaTypeProjectStorageBean> findUnFinishMaTypeList(MaTypeProjectStorageBean o);

	Integer updateIsFinish(MaTypeProjectStorageBean storage);

	List<MaTypeProjectStorageBean> findCancelBackListById(MaTypeProjectStorageBean storage);

	int updateCancelBean(MaTypeProjectStorageBean storage);
	

}
