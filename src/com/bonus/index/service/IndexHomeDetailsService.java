package com.bonus.index.service;

import java.util.List;

import com.bonus.index.beans.IndexCheckWarnBean;
import com.bonus.index.beans.IndexHomeCalendarBean;
import com.bonus.index.beans.IndexHomeDetailsBean;
import com.bonus.index.beans.IndexHomeTaskBean;
import com.bonus.index.beans.IndexInuseWarnBean;
import com.bonus.index.beans.IndexProjectBean;
import com.bonus.index.beans.IndexStorageWarnBean;
import com.bonus.index.beans.IndexTodoWarnBean;
import com.bonus.index.beans.IndexTotalWarnBean;
import com.bonus.sys.BaseService;

public interface IndexHomeDetailsService extends BaseService<IndexHomeDetailsBean>{

	List<IndexHomeDetailsBean> getMaTypeDetails(IndexHomeDetailsBean o);

	List<IndexHomeTaskBean> getMaChangeInfo(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaUseInfo(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaChangeTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaOutTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaBackTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaScrapTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaPdTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaNewTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaInTask(IndexHomeTaskBean o);

	List<IndexStorageWarnBean> getStorageWarn(IndexStorageWarnBean o);

	List<IndexCheckWarnBean> getCheckWarn(IndexCheckWarnBean o);

	List<IndexInuseWarnBean> getInUseWarn(IndexInuseWarnBean o);

	List<IndexInuseWarnBean> getPlanDetailsAboutExpireList(IndexInuseWarnBean o);

	List<IndexTotalWarnBean> getTotalChangeWarn(IndexTotalWarnBean o);

	List<IndexProjectBean> getProjectDiff(IndexProjectBean o);

	List<IndexProjectBean> getProjectCompany(IndexProjectBean o);

	List<IndexTodoWarnBean> getTodoList(IndexTodoWarnBean o);

	List<IndexTodoWarnBean> getToOutList(IndexTodoWarnBean o);

	List<IndexTodoWarnBean> getToBackList(IndexTodoWarnBean o);

	List<IndexTodoWarnBean> getToNewList(IndexTodoWarnBean o);

	List<IndexProjectBean> getProjectMaDiff(IndexProjectBean o);

	List<IndexHomeTaskBean> getProjectMaRecord(IndexHomeTaskBean o);

	List<IndexHomeCalendarBean> getCalendarOut(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarBack(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarRepair(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarScrap(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarRepairInput(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarNewInput(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarPd(IndexHomeCalendarBean o);

	
	
}
