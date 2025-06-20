package com.bonus.ma.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.VenderBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.ZNode;


@BonusBatis
public interface VenderDao extends BaseDao<VenderBean>{

	public int insertBean(VenderBean o);
	
	public List<VenderBean> findVender(VenderBean o);

	public List<ZNode> maVenderTree(VenderBean o);
	
	public List<ZNode> makeeper(VenderBean o);
	
	public void insertMachinesUrl(VenderBean o); 
	
	public void updateVend(VenderBean o);  //修改路径
	
}
