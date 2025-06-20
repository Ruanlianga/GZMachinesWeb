package com.bonus.bm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.subcontractorsBean;
import com.bonus.bm.dao.subcontractorsDao;
import com.bonus.sys.BaseServiceImp;

@Service("subcontractors")
public class subcontractorsServiceImp extends BaseServiceImp<subcontractorsBean> implements subcontractorsService  {

	@Autowired
	private subcontractorsDao dao;

/*	@Override
	public List<subcontractorsBean> getCompanyType() {
		return dao.getCompanyType();
	}
*/
	@Override
	public subcontractorsBean findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<subcontractorsBean> findSubcontractors() {
		// TODO Auto-generated method stub
		return dao.findSubcontractors();
	}


}
