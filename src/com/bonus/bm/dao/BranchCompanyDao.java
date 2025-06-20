package com.bonus.bm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.bm.beans.BranchCompanyBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface BranchCompanyDao extends BaseDao<BranchCompanyBean>{
	
	public List<BranchCompanyBean> getBranchCompany();

	public List<BranchCompanyBean> findByNameAndCode(@Param("name") String name,@Param("code") String  code);

}
