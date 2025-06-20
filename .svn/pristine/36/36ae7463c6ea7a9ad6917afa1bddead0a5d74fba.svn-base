package com.bonus.rm.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.rm.beans.ReturnMaterialTaskRecordBean;
import com.bonus.sys.BaseDao;

//退料任务
@BonusBatis
public interface ReturnMaterialTaskRecordDao extends BaseDao<ReturnMaterialTaskRecordBean>{

	List<ReturnMaterialTaskRecordBean> findRMSheet(ReturnMaterialTaskRecordBean o);
	
	int updateRemarkbyTaskId(ReturnMaterialTaskRecordBean o);

	List<ReturnMaterialTaskRecordBean> findIdByTaskId(ReturnMaterialTaskRecordBean o);
	
	int updateRemarkMachinebyId(ReturnMaterialTaskRecordBean o);

	List<ReturnMaterialTaskRecordBean> findUnFinishContentDetails(ReturnMaterialTaskRecordBean o);
}
