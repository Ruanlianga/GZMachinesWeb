package com.bonus.lease.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.MachineReceiveBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface MachineReceiveDao extends BaseDao<MachineReceiveBean> {

	List<MachineReceiveBean> findLeaseSheet(MachineReceiveBean o);

	//获取taskId与modelId
	List<MachineReceiveBean>findTaskIdAndModelId(MachineReceiveBean o);
	
	//保存修改的领料单备注
	int saveMaterialRequisition(MachineReceiveBean o);
	
	//保存修改的主任务备注
	int saveMainTask(MachineReceiveBean o);
	
	int del(MachineReceiveBean o);
	
	List<MachineReceiveBean> findDetails(MachineReceiveBean o);

	// 确认任务单已经出库，打印生效
	int updateConfirmStatus(String taskId);
}
