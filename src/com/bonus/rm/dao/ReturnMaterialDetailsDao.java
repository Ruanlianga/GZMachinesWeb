package com.bonus.rm.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.sys.BaseDao;

//退料任务
@BonusBatis
public interface ReturnMaterialDetailsDao extends BaseDao<ReturnMaterialDetailsBean>{

	List<ReturnMaterialDetailsBean> findByCode(ReturnMaterialDetailsBean o);

	List<ReturnMaterialDetailsBean> findByTaskId(ReturnMaterialDetailsBean o);

	void insertInfo(ReturnMaterialDetailsBean o);

	String findSupId(ReturnMaterialDetailsBean o);

	Object findLeaseBackNum(ReturnMaterialDetailsBean o);

	List<ReturnMaterialDetailsBean> findTaskDetail(ReturnMaterialDetailsBean o);

	List<ReturnMaterialDetailsBean> findTaskDetailInfo(ReturnMaterialDetailsBean o);

}
