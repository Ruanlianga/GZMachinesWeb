package com.bonus.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.RoleBean;
import com.bonus.sys.beans.ZNode;
import com.bonus.sys.dao.RoleDao;

@Service("RoleService")
public class RoleServiceImp extends BaseServiceImp<RoleBean> implements
		RoleService {

	@Override
	public int delete(Integer id) {
		return ((RoleDao) baseDao).deleteByPrimaryKey(id);
	}

	@Override
	public void insert(RoleBean record) {
		((RoleDao) baseDao).insertBean(record);
	}

	@Override
	public void update(RoleBean record) {
		((RoleDao) baseDao).updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ZNode> getRoleBeans() {
		return ((RoleDao) baseDao).getRoleBeans();
	}

}
