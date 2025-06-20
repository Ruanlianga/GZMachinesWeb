package com.bonus.bm.dao;

import java.util.List;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.ZNode;

@BonusBatis
public interface CompanyDao extends BaseDao<CompanyBean> {

	List<CompanyBean> getCompany();

	List<CompanyBean> getCompanyType();

	List<CompanyBean> getUnit(CompanyBean o);

	List<ZNode> unitTree(CompanyBean o);

	List<CompanyBean> getUnitName(CompanyBean o);

	CompanyBean findByNameAndCompanyId(CompanyBean o);
}
