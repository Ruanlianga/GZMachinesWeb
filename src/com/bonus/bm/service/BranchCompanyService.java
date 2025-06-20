package com.bonus.bm.service;

import java.util.List;

import com.bonus.bm.beans.BranchCompanyBean;
import com.bonus.sys.BaseService;

public interface BranchCompanyService extends BaseService<BranchCompanyBean>{

	public List<BranchCompanyBean> getBranchCompany();

	public List<BranchCompanyBean> findByNameAndCode(String name,String code); 
}
