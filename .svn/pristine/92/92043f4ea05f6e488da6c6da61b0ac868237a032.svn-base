package com.bonus.repairCheck.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repairCheck.beans.RepairCheckDetailsBean;
import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface RepairCheckDetailsDao extends BaseDao<RepairCheckDetailsBean> {

	List<RepairCheckDetailsBean> findCheckTask(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> findCheckTaskDetails(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> findCheckDevice(RepairCheckDetailsBean o);

	void updateInfo(RepairCheckDetailsBean o);

	RepairCheckDetailsBean findDeviceRmstatus(RepairCheckDetailsBean o);

	RepairCheckDetailsBean findInputNumById(RepairCheckDetailsBean o);

	RepairCheckDetailsBean findScrapNumById(RepairCheckDetailsBean o);

	RepairCheckDetailsBean findRepairNumById(RepairCheckDetailsBean o);

	RepairCheckDetailsBean findAlCheckNum(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> findIsSure(RepairCheckDetailsBean o);

	void updateDeviceInfo(RepairCheckDetailsBean o);

	RepairCheckDetailsBean findDefinitionId(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> getCheckIndexList(RepairCheckDetailsBean o);
	List<RepairCheckDetailsBean> getCheckedIndexList(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> getCheckId(String modelId);
	void insertRecordInfo(RepairCheckDetailsBean rcBean);

	int updateCheckNum(RepairCheckDetailsBean rcBean);

	List<RepairCheckDetailsBean> getRepairCodeList(RepairCheckDetailsBean o);
	List<RepairCheckDetailsBean> getRepairCodeList1(RepairCheckDetailsBean o);
	
	int isCheckPass(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> getReapirPassId(RepairCheckDetailsBean o);

	int updateCheckPassStatus(RepairCheckDetailsBean o);

	int updateCheckPassMMStatus(RepairCheckDetailsBean o);

	int updateWrcNum(RepairCheckDetailsBean repairCheckDetailsBean);
	
	int insertWirRecord(RepairCheckDetailsBean rcBean);

	RepairCheckDetailsBean getAlCherckNum(RepairCheckDetailsBean repairCheckDetailsBean);

	RepairCheckDetailsBean getNewCheckCodeDate(RepairCheckDetailsBean repairCheckDetailsBean);

	int updateWirRmStatus(RepairCheckDetailsBean o);

	int updateMMBatchStatus(RepairCheckDetailsBean o);

	int updateWrcCheckNum(RepairCheckDetailsBean o);

	RepairCheckDetailsBean getOneDataFromWrd(RepairCheckDetailsBean o);

	int updateReturnData(RepairCheckDetailsBean o);

	void insertTowsd(RepairCheckDetailsBean bean);

	int insertNewDetail(PutInStorageTaskBean psBean);

	int insertCheckDetails(RepairCheckDetailsBean rcBean);
	
	RepairCheckDetailsBean findCompanyId(RepairCheckDetailsBean o);
	
	int insertScrapDetails(RepairCheckDetailsBean o);
	
	int updateInfoType(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> getCheckedNumList(RepairCheckDetailsBean o);

	RepairCheckDetailsBean getMaxRepairTime(RepairCheckDetailsBean rc);

	RepairCheckDetailsBean getMaxCheckTime(RepairCheckDetailsBean rc);
}
