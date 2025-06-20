package com.bonus.bm.dao;

import java.util.List;

import com.bonus.bm.beans.CompanyTypeBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface CompanyTypeDao extends BaseDao<CompanyTypeBean>{

	List<CompanyTypeBean> getCompanyType();

	CompanyTypeBean findByName(String name);
	
}
