package com.bonus.pis.service;

import java.util.List;

import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface PutInStorageService extends BaseService<PutInStorageBean>{

	List<PutInStorageBean> putInStorageList(PutInStorageBean o);

	List<PutInStorageBean> putInStorageDetails(PutInStorageBean o);

	PutInStorageBean getAlPutNum(PutInStorageBean o);

	void updateAlPutNum(PutInStorageBean o);

	void updateMachineNum(PutInStorageBean o);
	
	void addPutRecord(PutInStorageBean o);

	List<PutInStorageBean> getPutInfoByNum(PutInStorageBean o);

	List<PutInStorageBean> getPutRecordList(PutInStorageBean o);

	PutInStorageBean confirmPutTask(PutInStorageBean o);

	void updatePutTask(PutInStorageBean o);

	Page<PutInStorageBean> putInStorageQuery(PutInStorageBean o, Page<PutInStorageBean> page);

	PutInStorageBean findBackMachine(PutInStorageBean o);

	List<PutInStorageBean> machinePutInStorageList(PutInStorageBean o);
	
	List<PutInStorageBean> machinePutInStorageDetails(PutInStorageBean o);
	
	void updateIsExample(PutInStorageBean o);
	
	void updateIsSure(PutInStorageBean o);
	
	List<PutInStorageBean> machinePutInStorageDetailsFinish(PutInStorageBean o);

	int updatePutRecordTwo(PutInStorageBean o);
	
}
