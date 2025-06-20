package com.bonus.warningManage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.warningManage.beans.CheckDetailsBean;
import com.bonus.warningManage.dao.CheckDetailsDao;
import com.bonus.sys.BaseServiceImp;

@Service("checkDetails")
public class CheckDetailsServiceImp extends BaseServiceImp<CheckDetailsBean> implements CheckDetailsService{

	@Autowired
	private CheckDetailsDao dao;
	
	/*@Override
	public int InsertBean(CheckDetailsBean o) {
		return dao.insertBean(o);
	}*/

	
}
