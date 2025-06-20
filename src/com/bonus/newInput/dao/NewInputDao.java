package com.bonus.newInput.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.newInput.beans.NewInputBean;
import com.bonus.newInput.beans.NewInputCheckBean;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface NewInputDao extends BaseDao<NewInputBean> {

	public int insertBean(NewInputBean o);

	public List<NewInputBean> findDetails(@Param("param") NewInputBean o, @Param("t") Page<NewInputBean> page);

	public List<NewInputBean> findById(NewInputBean o);

	public void updateDetail(NewInputBean o);

	public int addDetails(NewInputBean o);

	public void delById(NewInputBean o);

	public void delDetails(List<NewInputBean> os);

	public List<NewInputBean> findByYsNums(@Param("param") NewInputBean o, @Param("t") Page<NewInputBean> page);

	public void insertInvoice(NewInputBean o);

	public void insertMachinesUrl(NewInputBean o);

	public List<NewInputBean> findNewTaskList(NewInputBean o);
	
	public List<NewInputBean> newPurchaseReceipt(NewInputBean o);

	public List<NewInputBean> findQrcode(@Param("param") NewInputBean o, @Param("t") Page<NewInputBean> page); // 新购二维码

	public NewInputBean findSheet(NewInputBean o);

	public NewInputBean findApplyNumber(String applyNumber);

	public List<NewInputBean> isFinish(NewInputBean o, Page<NewInputBean> page);

	public void add(NewInputBean o);

	public void updateStatus(NewInputBean bean);

	public NewInputBean findTaskById(NewInputBean nBean);

	public NewInputCheckBean findByCheckTaskId(NewInputCheckBean o);

	public void isExamine(NewInputBean o);

	public List<NewInputBean> findIsExamine(@Param("param") NewInputBean o, @Param("t") Page<NewInputBean> page);

	public List<NewInputBean> findIsApproval(@Param("param") NewInputBean o, @Param("t") Page<NewInputBean> page);

	public List<NewInputBean> findCodeByModel(NewInputBean o);

	public List<NewInputBean> findDetailsByTask(NewInputBean o);

	public void isApproval(NewInputBean o);

	public int deleteQrcode(NewInputBean o);
	
	public List<NewInputBean> findMaId(NewInputBean o);
	
	public int deleteMachine(NewInputBean o);

	public int delDetails(NewInputBean o);
}
