package com.bonus.repairCheck.service;

import java.util.List;

import com.bonus.repairCheck.beans.RepairCheckDetailsBean;
import com.bonus.sys.BaseService;

public interface RepairCheckDetailsService extends BaseService<RepairCheckDetailsBean>{

	List<RepairCheckDetailsBean> findCheckTask(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> findCheckTaskDetails(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> findCheckDevice(RepairCheckDetailsBean o);

	void checkOperation(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> getCheckIndexList(RepairCheckDetailsBean o);

	int submitNumCheck(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> getRepairCodeList(RepairCheckDetailsBean o);

	int submitCodeCheck(RepairCheckDetailsBean o);

	List<RepairCheckDetailsBean> getCheckedNumList(RepairCheckDetailsBean o);


}
