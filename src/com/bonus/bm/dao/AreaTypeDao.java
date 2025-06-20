package com.bonus.bm.dao;

import java.util.List;

import com.bonus.bm.beans.AreaTypeBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.ZNode;


@BonusBatis
public interface AreaTypeDao extends BaseDao<AreaTypeBean>{

	public int insertBean(AreaTypeBean o);
	
	public List<AreaTypeBean> findAreaType(AreaTypeBean o);
	public List<AreaTypeBean> findma(AreaTypeBean o);
	
	public List<AreaTypeBean> findmt(AreaTypeBean o);
	
	
	public List<ZNode> orgTree(AreaTypeBean o);
	
}
