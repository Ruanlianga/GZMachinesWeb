package com.bonus.rm.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.rm.beans.PutInStorageAuditBean;
import com.bonus.sys.BaseDao;

//入库审核
@BonusBatis
public interface PutInStorageAuditDao extends BaseDao<PutInStorageAuditBean>{

	void auditPutTask(PutInStorageAuditBean o);

	List<PutInStorageAuditBean> findPutMaInfo(PutInStorageAuditBean o);

	void updateMachineStatus(PutInStorageAuditBean bean);

	List<PutInStorageAuditBean> findPutTaskInfo(PutInStorageAuditBean o);

	void updateStorageNum(PutInStorageAuditBean bean);

	

}
