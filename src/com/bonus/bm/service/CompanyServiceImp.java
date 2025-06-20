package com.bonus.bm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.bm.dao.CompanyDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.ZNode;


@Service("CompanyServiceImp")
public class CompanyServiceImp extends BaseServiceImp<CompanyBean> implements CompanyService{

	@Autowired
	private CompanyDao dao;
	
	@Override
	public List<CompanyBean> getCompany() {
		return dao.getCompany();
	}

	@Override
	public List<CompanyBean> getCompanyType() {
		return dao.getCompanyType();
	}

	@Override
	public List<CompanyBean> getUnit(CompanyBean o) {
		return dao.getUnit(o);
	}

	@Override
	public List<ZNode> unitTree(CompanyBean o) {
		return dao.unitTree(o);
	}

	@Override
	public List<CompanyBean> getUnitName(CompanyBean o) {
		return dao.getUnitName(o);
	}

	@Override
	public CompanyBean findByNameAndCompanyId(CompanyBean o) {
		return dao.findByNameAndCompanyId(o);
	}

}
