package com.bonus.index.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.index.beans.IndexCheckWarnBean;
import com.bonus.index.beans.IndexHomeCalendarBean;
import com.bonus.index.beans.IndexHomeDetailsBean;
import com.bonus.index.beans.IndexHomeTaskBean;
import com.bonus.index.beans.IndexInuseWarnBean;
import com.bonus.index.beans.IndexProjectBean;
import com.bonus.index.beans.IndexStorageWarnBean;
import com.bonus.index.beans.IndexTodoWarnBean;
import com.bonus.index.beans.IndexTotalWarnBean;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface IndexHomeDetailsDao extends BaseDao<IndexHomeDetailsBean>{

	List<IndexHomeDetailsBean> getMaTypeDetails(IndexHomeDetailsBean o);

	IndexHomeTaskBean getInStorage(IndexHomeTaskBean o);

	IndexHomeTaskBean getNewStorage(IndexHomeTaskBean o);

	IndexHomeTaskBean getOutStorage(IndexHomeTaskBean o);

	IndexHomeTaskBean getBackStorage(IndexHomeTaskBean o);

	IndexHomeTaskBean getScrapStorage(IndexHomeTaskBean o);

	IndexHomeTaskBean getPdStorage(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaUseInfo(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaOutTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaBackTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaScrapTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaPdTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaNewTask(IndexHomeTaskBean o);

	List<IndexHomeTaskBean> getMaInTask(IndexHomeTaskBean o);

	List<IndexStorageWarnBean> getStorageWarn(IndexStorageWarnBean o);

	List<IndexCheckWarnBean> getCheckWarn(IndexCheckWarnBean o);

	List<IndexInuseWarnBean> getInUseWarn(IndexInuseWarnBean o);

	// 查询计划详情中即将到期的数据
	List<IndexInuseWarnBean> getPlanDetailsAboutExpireList(IndexInuseWarnBean o);

	List<IndexTotalWarnBean> getTotalChangeWarn(IndexTotalWarnBean o);

	IndexTotalWarnBean getTotalInfo(IndexTotalWarnBean o);

	List<IndexProjectBean> getProjectDiff(IndexProjectBean o);

	List<IndexProjectBean> getProjectCompany(IndexProjectBean o);

	List<IndexTodoWarnBean> getToOutList(IndexTodoWarnBean o);

	List<IndexTodoWarnBean> getToNewList(IndexTodoWarnBean o);

	List<IndexTodoWarnBean> getToBackList(IndexTodoWarnBean o);

	List<IndexProjectBean> getProjectMaDiff(IndexProjectBean o);

	List<IndexHomeCalendarBean> getCalendarOut(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarBack(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarRepair(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarScrap(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarRepairInput(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarNewInput(IndexHomeCalendarBean o);

	List<IndexHomeCalendarBean> getCalendarPd(IndexHomeCalendarBean o);

	
}
