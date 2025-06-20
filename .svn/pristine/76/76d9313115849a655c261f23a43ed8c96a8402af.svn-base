package com.bonus.pis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface PutInStorageDao extends BaseDao<PutInStorageBean> {

	List<PutInStorageBean> putInStorageList(PutInStorageBean o);

	List<PutInStorageBean> putInStorageDetails(PutInStorageBean o);

	PutInStorageBean getAlPutNum(PutInStorageBean o);

	void updateAlPutNum(PutInStorageBean o);

	void addPutRecord(PutInStorageBean o);
	
	void updateMachineNum(PutInStorageBean o);

	List<PutInStorageBean> getPutInfoByNum(PutInStorageBean o);

	List<PutInStorageBean> getPutRecordList(PutInStorageBean o);

	PutInStorageBean confirmPutTask(PutInStorageBean o);

	void updatePutTask(PutInStorageBean o);

	List<PutInStorageBean> putInStorageQuery(@Param("param") PutInStorageBean o, Page<PutInStorageBean> page);

	PutInStorageBean findBackMachine(PutInStorageBean o);

	List<PutInStorageBean> findIsFinish(PutInStorageBean o);

	void updateInputNum(PutInStorageBean psBean);

	List<PutInStorageBean> machinePutInStorageList(PutInStorageBean o);
	
	List<PutInStorageBean> machinePutInStorageDetails(PutInStorageBean o);
	
	void updateIsExample(PutInStorageBean o);
	
	void updateIsSure(PutInStorageBean o);
	
	List<PutInStorageBean> machinePutInStorageDetailsFinish(PutInStorageBean o);

	int updatePutRecordTwo(PutInStorageBean o);

}
