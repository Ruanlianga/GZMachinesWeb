package com.bonus.bm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.BranchCompanyBean;
import com.bonus.bm.dao.BranchCompanyDao;
import com.bonus.sys.BaseServiceImp;

@Service("branchCom")
public class BranchCompanyServiceImp extends BaseServiceImp<BranchCompanyBean> implements BranchCompanyService  {

	@Autowired
	private BranchCompanyDao dao;

	@Override
	public List<BranchCompanyBean> getBranchCompany() {
		List<BranchCompanyBean> list = dao.getBranchCompany();
		return list;
	}

	@Override
	public List<BranchCompanyBean> findByNameAndCode(String name,String code) {
		return dao.findByNameAndCode(name,code);
	}

	

}
