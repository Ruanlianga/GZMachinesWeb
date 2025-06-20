package com.bonus.lease.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.OutStorageCheckBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface OutStorageCheckDao extends BaseDao<OutStorageCheckBean> {

	public int insertBean(OutStorageCheckBean o);

	public List<OutStorageCheckBean> getOutStorageCheckList(OutStorageCheckBean o);

	public void doCheck(OutStorageCheckBean o);

	public List<OutStorageCheckBean> getOutStorageCheckDetails(OutStorageCheckBean o);

	public List<OutStorageCheckBean> getModelInfo(OutStorageCheckBean o);

	public void updateAlCheckNum(OutStorageCheckBean o);

	public void addCheckRecord(OutStorageCheckBean o);

	public OutStorageCheckBean getAlCheckNum(OutStorageCheckBean o);

	public void checkDetails(OutStorageCheckBean bean);

	public void isSure(OutStorageCheckBean o);

	public OutStorageCheckBean findById(OutStorageCheckBean o);

	public List<OutStorageCheckBean> getCheckInfoByNum(OutStorageCheckBean o);

	public List<OutStorageCheckBean> getCheckRecordList(OutStorageCheckBean o);

	public OutStorageCheckBean confirmCheckTask(OutStorageCheckBean o);

	public List<OutStorageCheckBean> outStorageCheckQuery(@Param("param") OutStorageCheckBean o, Page<OutStorageCheckBean> page);

	public List<OutStorageCheckBean> findCheckCount(OutStorageCheckBean bean);

	public List<OutStorageCheckBean> findIsFinish(OutStorageCheckBean o);

	public List<OutStorageCheckBean> findSupId(OutStorageCheckBean o);

	

	

}
