package com.bonus.rm.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.sys.BaseDao;

//入库任务
@BonusBatis
public interface PutInStorageTaskDao extends BaseDao<PutInStorageTaskBean>{

	void  updatePutPerson(PutInStorageTaskBean o);

	List<PutInStorageTaskBean> findPutInTaskNum(PutInStorageTaskBean o);

	void updatePutInIsExamine(PutInStorageTaskBean bean);

	List<PutInStorageTaskBean> findCodeByModelId(PutInStorageTaskBean bean);

	List<PutInStorageTaskBean> findIsSure(PutInStorageTaskBean bean);

	void updateIsFinish(PutInStorageTaskBean bean);

	void updateInputNum(PutInStorageTaskBean psBean);

	void updatePutServer(PutInStorageTaskBean o);

	PutInStorageTaskBean findByTask(PutInStorageTaskBean o);

	void updatePrePutNum(PutInStorageTaskBean psBean);

	PutInStorageTaskBean getRepeatData(PutInStorageTaskBean o);

	void insertNewDetail(PutInStorageTaskBean psBean);

	int deletePut(PutInStorageTaskBean o);

	List<PutInStorageTaskBean> getPutInfoList(PutInStorageTaskBean o);

	void deletePutTask(PutInStorageTaskBean o);

	PutInStorageTaskBean getRepairInfo(PutInStorageTaskBean o);

	int updateRepairNum(PutInStorageTaskBean bean);


	List<PutInStorageTaskBean> getWfInfoRecord(PutInStorageTaskBean o);

	void deleteInfo(PutInStorageTaskBean info);

	void updateMaStatus(String deviceCode, String modelId);

	PutInStorageTaskBean getRmInfo(String deviceCode, String modelId);

	void updateRmStatus(PutInStorageTaskBean bean);

}
