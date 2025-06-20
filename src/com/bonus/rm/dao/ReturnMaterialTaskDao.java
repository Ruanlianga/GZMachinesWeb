package com.bonus.rm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.beans.ReturnMaterialTaskBean;
import com.bonus.sys.BaseDao;

//退料任务
@BonusBatis
public interface ReturnMaterialTaskDao extends BaseDao<ReturnMaterialTaskBean>{

	String findNumber(ReturnMaterialTaskBean o);

	List<ReturnMaterialTaskBean> findAllTask(ReturnMaterialTaskBean o);

	int updateBean(ReturnMaterialTaskBean o);

	List<ReturnMaterialDetailsBean> findBackRecord(ReturnMaterialTaskBean o);

	List<ReturnMaterialTaskBean> findDevByWorkId(ReturnMaterialTaskBean o);

	List<ReturnMaterialTaskBean> findDevByUnitId(@Param("param")ReturnMaterialTaskBean o);

	Integer deleteTask(ReturnMaterialTaskBean o);
	
	List<ReturnMaterialTaskBean> findMaIdList(ReturnMaterialTaskBean o);
	
	Integer updateBatch(ReturnMaterialTaskBean o);

	void updateAudit(ReturnMaterialTaskBean o);
}
