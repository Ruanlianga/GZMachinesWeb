package com.bonus.lease.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.LeaseApplicationBean;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.beans.ReceiveDetailsBean;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface ReceiveDetailsDao extends BaseDao<ReceiveDetailsBean> {

	public int insertBean(ReceiveDetailsBean o);
	
	public List<ReceiveDetailsBean> findBean(ReceiveDetailsBean o);
	
	public void updateBean(ReceiveDetailsBean o);

	public List<ReceiveDetailsBean> getAllReceiveTask(ReceiveDetailsBean o);
	
	public ReceiveDetailsBean getMachinesNum(ReceiveDetailsBean o);
	
	public List<ReceiveDetailsBean> findMaReceiveMsg(ReceiveDetailsBean o);

	public void isSure(ReceiveDetailsBean o);
	
	public void allSure(ReceiveDetailsBean o);

	public ReceiveDetailsBean getPreMachinesNum(ReceiveDetailsBean o);

	public void insertCheckTask(ReceiveDetailsBean o);

	public List<ReceiveDetailsBean> findIsFinish(ReceiveDetailsBean o);

	public List<MaTypeProjectStorageBean> findProjectStorageListByTaskId(ReceiveDetailsBean o);

	public List<MaTypeProjectStorageBean> findProjectStorageListByTaskId(OutStorageBean o);

	public List<ReceiveDetailsBean> findTaskId(ReceiveDetailsBean o);

	public void delOutTask(ReceiveDetailsBean bean);

	public void delOutTaskRecord(ReceiveDetailsBean bean);

	public LeaseApplicationBean findOutTaskId(ReceiveDetailsBean bean);
	
	public List<ReceiveDetailsBean> findMachineDetails(ReceiveDetailsBean o);

	public int batchDeletion(ReceiveDetailsBean o);

}
