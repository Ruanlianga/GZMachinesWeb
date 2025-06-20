package com.bonus.rm.service;


import java.util.List;

import com.bonus.rm.beans.ReturnMaterialTaskRecordBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;
import com.bonus.wf.beans.TaskRecordBean;


public interface ReturnMaterialTaskRecordService extends BaseService<ReturnMaterialTaskRecordBean>{

	List<ReturnMaterialTaskRecordBean> findRMSheet(ReturnMaterialTaskRecordBean o);

	Page<TaskRecordBean> findUnFinishContent(Page<TaskRecordBean> page, TaskRecordBean o);

	List<ReturnMaterialTaskRecordBean> findIdByTaskId(ReturnMaterialTaskRecordBean o);

	int updateRemarkbyTaskId(ReturnMaterialTaskRecordBean o);

	int updateRemarkMachinebyId(ReturnMaterialTaskRecordBean o);

	List<ReturnMaterialTaskRecordBean> findUnFinishContentDetails(ReturnMaterialTaskRecordBean o);
}
