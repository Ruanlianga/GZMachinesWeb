package com.bonus.sys.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.RoleBean;
import com.bonus.sys.beans.ZNode;

@BonusBatis
public interface RoleDao extends BaseDao<RoleBean>{
	
    int deleteByPrimaryKey(Integer id);

    int insertBean(RoleBean record);

    int insertSelective(RoleBean record);

    RoleBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleBean record);

    int updateByPrimaryKey(RoleBean record);

	List<ZNode> getRoleBeans();

}