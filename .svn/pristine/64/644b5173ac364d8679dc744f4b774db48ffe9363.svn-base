package com.bonus.bm.dao;

import com.bonus.bm.beans.LogBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface LogDao extends BaseDao<LogBean>{

	public int insertLog(LogBean o);//日志新增
	
	public LogBean findInNum(LogBean o);//获取在库数

	public LogBean findTotalNum(LogBean logBean);

	public void insertTotalLog(LogBean logBean);
}
