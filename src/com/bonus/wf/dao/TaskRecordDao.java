package com.bonus.wf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;
import com.bonus.wf.beans.TaskRecordBean;

@BonusBatis
public interface TaskRecordDao extends BaseDao<TaskRecordBean>{

	List<TaskRecordBean> findAll(TaskRecordBean o);

	void addTaskAgreement(TaskRecordBean tr);

	void updateIsFinish(TaskRecordBean trBean);

	List<TaskRecordBean> findTaskInfo(TaskRecordBean bean);

	List<TaskRecordBean> findRepairTask(TaskRecordBean bean);

	String findNumber(TaskRecordBean bean);

	List<TaskRecordBean> findUnFinishContent(@Param("param")Page<TaskRecordBean> page, TaskRecordBean o);

	void findSomeId(OutStorageBean o);
	
	TaskRecordBean checkTask(String supId);
}
