package com.bonus.repair.service;

import java.util.List;

import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.sys.BaseService;

public interface RepairDetailsService extends BaseService<RepairDetailsBean>{

	List<RepairDetailsBean> findRepairTask(RepairDetailsBean o);

	List<RepairDetailsBean> findRepairTaskDetails(RepairDetailsBean o);

	List<RepairDetailsBean> findRepairDevice(RepairDetailsBean o);

	void repairOperation(RepairDetailsBean o);

	List<RepairDetailsBean> findModelName(RepairDetailsBean o);

	List<RepairDetailsBean> findTypeName(RepairDetailsBean o);
	
	String finishRepair(RepairDetailsBean o);
	
	String repairSplit(RepairDetailsBean o);

	List<RepairDetailsBean> getRepairIndexList(RepairDetailsBean o);

	int submitNumRepair(RepairDetailsBean o);

	List<RepairDetailsBean> findRepairCodeList(RepairDetailsBean o);

	int submitCodeRepair(RepairDetailsBean o);

	int splitCodeRepair(RepairDetailsBean o);

	void submitNumScrap(RepairDetailsBean o);

	int submitCodeScrap(RepairDetailsBean o);
	
	List<RepairDetailsBean> findRepairCodeListFinish(RepairDetailsBean o);

	List<RepairDetailsBean> getRepairedNumList(RepairDetailsBean o);
	
	List<RepairDetailsBean> getRepairDetails(RepairDetailsBean o);
	
	List<RepairDetailsBean> getRepairMan(RepairDetailsBean o);

}
