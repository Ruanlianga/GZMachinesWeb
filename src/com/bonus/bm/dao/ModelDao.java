package com.bonus.bm.dao;

import java.util.List;

import com.bonus.bm.beans.ModelBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface ModelDao extends BaseDao<ModelBean>{

	public int insertBean(ModelBean o);
	
	public List<ModelBean> findModel(ModelBean o);
	
	
}
