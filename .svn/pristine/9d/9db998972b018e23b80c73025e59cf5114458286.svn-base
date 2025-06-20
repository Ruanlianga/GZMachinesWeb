package com.bonus.bm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.CompanyTypeBean;
import com.bonus.bm.dao.CompanyTypeDao;
import com.bonus.sys.BaseServiceImp;

@Service("comTypes")
public class CompanyTypeServiceImp extends BaseServiceImp<CompanyTypeBean> implements CompanyTypeService  {

	@Autowired
	private CompanyTypeDao dao;

	@Override
	public List<CompanyTypeBean> getCompanyType() {
		return dao.getCompanyType();
	}

	@Override
	public CompanyTypeBean findByName(String name) {
		return dao.findByName(name);
	}


}
