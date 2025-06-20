package com.bonus.repair.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface RepairDetailsDao extends BaseDao<RepairDetailsBean> {

	List<RepairDetailsBean> findRepairDevice(RepairDetailsBean o);

	List<RepairDetailsBean> findRepairTask(RepairDetailsBean o);

	List<RepairDetailsBean> findRepairTaskDetails(RepairDetailsBean o);

	RepairDetailsBean findAlRepairNum(RepairDetailsBean o);

	RepairDetailsBean findInfoNums(RepairDetailsBean o);

	void updateNum(RepairDetailsBean o);

	void updateInfo(RepairDetailsBean o);

	void insertPart(RepairDetailsBean o);

	RepairDetailsBean findScrapNum(RepairDetailsBean o);

	void updateScrapNum(RepairDetailsBean o);

	RepairDetailsBean findRepairInfo(RepairDetailsBean o);

	List<RepairDetailsBean> findIsSure(RepairDetailsBean o);

	void updateRepairNum(RepairDetailsBean rdBean);

	RepairDetailsBean findCheckInfo(RepairDetailsBean o);

	RepairDetailsBean findInfo(RepairDetailsBean o);

	RepairDetailsBean findRepairId(RepairDetailsBean o);

	List<RepairDetailsBean> findReturnRepairDevice(RepairDetailsBean o);

	List<RepairDetailsBean> findModelName(RepairDetailsBean o);

	List<RepairDetailsBean> findTypeName(RepairDetailsBean o);

	List<RepairDetailsBean> getSplitListByTask(RepairDetailsBean o);

	int updateSplitStatus(RepairDetailsBean o);

	RepairDetailsBean getSuRepairNumByTask(RepairDetailsBean o);

	void updateSplitRepairNum(RepairDetailsBean rdBean);

	List<RepairDetailsBean> getIsSureNum(RepairDetailsBean o);
	
	List<RepairDetailsBean> getSplitNotFinish(String supId);

	RepairDetailsBean getRepairSupId(RepairDetailsBean o);

	RepairDetailsBean getRepeatData(RepairDetailsBean o);

	List<RepairDetailsBean> getRepairIndexList(RepairDetailsBean o);

	List<RepairDetailsBean> getRepairedIndexList(RepairDetailsBean o);

	List<RepairDetailsBean> getMatchRepairList(RepairDetailsBean o);

	int updateRepairDetails(RepairDetailsBean mtBean);

	int insertRepairRecord(RepairDetailsBean mtBean);

	List<RepairDetailsBean> findRepairCodeList(RepairDetailsBean o);

	int updateBackStatus(RepairDetailsBean o);

	int updateDeviceStatus(RepairDetailsBean o);

	List<RepairDetailsBean> getRepairedCodeList(RepairDetailsBean o);

	int insertCheckRecord(RepairDetailsBean rb);

	int updateIsSplitCode(RepairDetailsBean rb);

	List<RepairDetailsBean> findRepairCodeListFinish(RepairDetailsBean o);

	List<RepairDetailsBean> getRepairedNumList(RepairDetailsBean o);

	RepairDetailsBean getMaxRepairTime(RepairDetailsBean rb);

	RepairDetailsBean getNumByModelId(RepairDetailsBean o);
	
	int insertFileDetails(RepairDetailsBean mtBean);

	List<RepairDetailsBean> getRepairDetails(RepairDetailsBean o);
	
	List<RepairDetailsBean> getRepairMan(RepairDetailsBean o);
}
