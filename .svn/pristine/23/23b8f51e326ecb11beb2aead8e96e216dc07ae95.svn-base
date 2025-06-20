package com.bonus.newInput.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bonus.ma.beans.MachineBean;
import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.sys.BaseService;

public interface InputDetailsService extends BaseService<InputDetailsBean>{

	int insertBean(InputDetailsBean o);

	int updatePerson(InputDetailsBean o);

	int isSure(InputDetailsBean o);

	void updateCheckStatus(InputDetailsBean idBean);

	public Object uploadMachines(HttpServletRequest request,InputDetailsBean o);
	
	public InputDetailsBean uploadAccept(HttpServletRequest request,InputDetailsBean o);

	int check(InputDetailsBean o);

	void checkUpload(InputDetailsBean bean);
	
	String findPicUrl(InputDetailsBean bean);
	
	public int updateInvoiceUrl(InputDetailsBean o);
	
	public int updatePhotoUrl(InputDetailsBean o);
	
	public int updateCheckUrl(InputDetailsBean o);
	
	public List<InputDetailsBean> findInvoiceUrl(InputDetailsBean o);
	
	public List<InputDetailsBean> findManchinesUrl(InputDetailsBean o);
	
	public List<InputDetailsBean> findcheckUrl(InputDetailsBean o);
	
}

