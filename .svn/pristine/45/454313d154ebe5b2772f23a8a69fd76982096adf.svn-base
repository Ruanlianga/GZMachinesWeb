package com.bonus.bm.service;

import java.util.List;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.beans.ZNode;

public interface CompanyService extends BaseService<CompanyBean> {

	List<CompanyBean> getCompany();

	List<CompanyBean> getCompanyType();

	List<CompanyBean> getUnit(CompanyBean o);

	List<ZNode> unitTree(CompanyBean o);

	List<CompanyBean> getUnitName(CompanyBean o);

	CompanyBean findByNameAndCompanyId(CompanyBean o);
}
