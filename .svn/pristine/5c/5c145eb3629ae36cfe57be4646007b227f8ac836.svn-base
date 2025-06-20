package com.bonus.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.OrgBean;
import com.bonus.sys.dao.OrgDao;

@Service("OrgService")
public class OrgServiceImp extends BaseServiceImp<OrgBean> implements
		OrgService {
	
	@Override
	public int delete(Integer id) {
		return ((OrgDao) baseDao).deleteByPrimaryKey(id);
	}

	@Override
	public int insertOrgBean(OrgBean record) {
		int autoId = ((OrgDao) baseDao).insertBean(record);
		return autoId;
	}

	@Override
	public int updateOrgBean(OrgBean record) {
		int d = ((OrgDao) baseDao).updateByPrimaryKeySelective(record);
		return d;
	}

	@Override
	public List<OrgBean> findRepairGroup(OrgBean o) {
		return ((OrgDao) baseDao).findRepairGroup(o);
	}

}
