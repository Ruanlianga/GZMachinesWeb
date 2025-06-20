package com.bonus.lease.service;

import java.util.List;

import com.bonus.lease.beans.ReceiveDetailsBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface ReceiveDetailsService extends BaseService<ReceiveDetailsBean> {

	public int insertBean(ReceiveDetailsBean o);
	
	public List<ReceiveDetailsBean> findBean(ReceiveDetailsBean o);
	
	public void updateBean(ReceiveDetailsBean o);

	public ReceiveDetailsBean getMachinesNum(ReceiveDetailsBean o);

	public List<ReceiveDetailsBean> getAllReceiveTask(ReceiveDetailsBean o);

	public List<ReceiveDetailsBean> findMaReceiveMsg(ReceiveDetailsBean o);

	public String isSure(ReceiveDetailsBean o);

	public void allSure(ReceiveDetailsBean o);

	public ReceiveDetailsBean getPreMachinesNum(ReceiveDetailsBean o);

	public void add(ReceiveDetailsBean o);

	public Page<ReceiveDetailsBean> findAuditing(ReceiveDetailsBean o, Page<ReceiveDetailsBean> page);

	public List<ReceiveDetailsBean> findTaskId(ReceiveDetailsBean o);

	public void delOutTask(ReceiveDetailsBean bean);

	public String batchDeletion(ReceiveDetailsBean o);

}
