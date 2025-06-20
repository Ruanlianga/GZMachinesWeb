package com.bonus.sys.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.OrgBean;

@BonusBatis
public interface OrgDao extends BaseDao<OrgBean>{
	
    int deleteByPrimaryKey(Integer id);

    int insertBean(OrgBean record);

    int insertSelective(OrgBean record);

    OrgBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrgBean record);

    int updateByPrimaryKey(OrgBean record);

	List<OrgBean> findRepairGroup(OrgBean o);
}