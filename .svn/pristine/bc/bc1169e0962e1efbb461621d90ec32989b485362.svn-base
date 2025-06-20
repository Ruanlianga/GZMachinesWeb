package com.bonus.newInput.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.beans.NewInputBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;


@BonusBatis
public interface InputDetailsDao extends BaseDao<InputDetailsBean>{

	public int insertBean(InputDetailsBean o);
	
	public void insertPicUrl(InputDetailsBean o);

	public List<InputDetailsBean> findInputList(InputDetailsBean o);

	public String findActualNum(InputDetailsBean o);
	
	public String findPicUrl(InputDetailsBean o);

	public List<InputDetailsBean> findNewBindingList(InputDetailsBean o);

	public List<InputDetailsBean> findQrcodeByTaskId(@Param("param")InputDetailsBean o, Page<InputDetailsBean> page);

	public List<InputDetailsBean> findQrcode(@Param("param")InputDetailsBean o, Page<InputDetailsBean> page);

	public List<InputDetailsBean> findRfidInputList(InputDetailsBean o);

	public List<NewInputBean> findRfidQrcode();

	public List<InputDetailsBean> findQrcodeByTaskId(@Param("param")InputDetailsBean o);

	public void isSure(InputDetailsBean o);

	public void updateCheckStatus(InputDetailsBean idBean);

	public InputDetailsBean findPersonInfo(InputDetailsBean o);

	public void updateCheckStatusById(InputDetailsBean o);

	/*public void updateCheckUrl(InputDetailsBean o);

	public void updatePhotoUrl(InputDetailsBean o);
*/
	public void updateCheckNum(InputDetailsBean o);

	//public void updateInvoiceUrl(InputDetailsBean bean);

	public List<InputDetailsBean> findByTaskId(@Param("param")InputDetailsBean o);

	public void insertMaNewInput(InputDetailsBean o);

	public List<InputDetailsBean> findMaIdByTask(InputDetailsBean o);

	public List<InputDetailsBean> findMaIdByTaskBachStatus(InputDetailsBean bean);
	
	public int updateInvoiceUrl(InputDetailsBean o);  
	
	public int updatePhotoUrl(InputDetailsBean o);
	
	public int updateCheckUrl(InputDetailsBean o);
	
	public List<InputDetailsBean> findInvoiceUrl(InputDetailsBean o);
	
	public List<InputDetailsBean> findManchinesUrl(InputDetailsBean o);
	
	public List<InputDetailsBean> findcheckUrl(InputDetailsBean o);

	
}
