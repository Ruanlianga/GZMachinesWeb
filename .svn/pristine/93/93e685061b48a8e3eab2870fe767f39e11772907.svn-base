package com.bonus.newInput.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bonus.newInput.beans.NewInputBean;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface NewInputService extends BaseService<NewInputBean> {

	void add(NewInputBean o);

	public Object uploadPhoto(HttpServletRequest request, NewInputBean o);

	void isExamine(NewInputBean o);

	void isApproval(NewInputBean o);

	Page<NewInputBean> findIsExamine(NewInputBean o, Page<NewInputBean> page);

	Page<NewInputBean> findIsApproval(NewInputBean o, Page<NewInputBean> page);
	
	public List<NewInputBean> newPurchaseReceipt(NewInputBean o);

	public int deleteQrcode(NewInputBean o);
	
}
