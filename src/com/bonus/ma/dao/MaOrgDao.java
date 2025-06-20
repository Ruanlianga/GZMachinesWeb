package com.bonus.ma.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.MaOrgBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.ZNode;

@BonusBatis
public interface MaOrgDao extends BaseDao<MaOrgBean> {

	List<ZNode> getOrgTree(MaOrgBean o);

	List<MaOrgBean> getMaListByOrg(MaOrgBean o);

	void insertOrgRelation(MaOrgBean mo);

	int insertOrgRelation(List<MaOrgBean> list);

	
	
}
