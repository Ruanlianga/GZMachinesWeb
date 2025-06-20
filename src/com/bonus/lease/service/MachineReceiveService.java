package com.bonus.lease.service;

import java.util.List;

import com.bonus.lease.beans.MachineReceiveBean;
import com.bonus.sys.BaseService;

public interface MachineReceiveService extends BaseService<MachineReceiveBean>{

	List<MachineReceiveBean> findLeaseSheet(MachineReceiveBean o);

	//获取taskId与modelId
	List<MachineReceiveBean>findTaskIdAndModelId(MachineReceiveBean o);
	
	//保存修改的领料单数据
	int saveMaterialRequisition(MachineReceiveBean o);
	
	//保存修改的主任务备注
	int saveMainTask(MachineReceiveBean o);
	
	int del(MachineReceiveBean o);
	
	List<MachineReceiveBean> findDetails(MachineReceiveBean o);

	int updateConfirmStatus(String taskId);
}
