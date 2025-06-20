package com.bonus.rm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.MachineBean;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.rm.beans.ReturnAuditBean;
import com.bonus.scrap.beans.ScrapDetailsBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

//退料任务
@BonusBatis
public interface ReturnAuditDao extends BaseDao<ReturnAuditBean>{

	void isExamine(ReturnAuditBean o);
	
	void isApproval(ReturnAuditBean o);

	List<ReturnAuditBean> findPutInAudit(@Param("param")ReturnAuditBean o, Page<ReturnAuditBean> page);
	
	List<ReturnAuditBean> findAuditByPage(@Param("param")ReturnAuditBean o, Page<ReturnAuditBean> page);

	void updatePutInIsExamine(ReturnAuditBean o);

	ReturnAuditBean findPutInTaskId(ReturnAuditBean o);

	ReturnAuditBean findDeviceNums(ReturnAuditBean o);

	String findNumber(RepairDetailsBean o);

	String findScrapNumber(ScrapDetailsBean o);

	void batchAudit(@Param("ids")List<Integer> intList, ReturnAuditBean o);

	ReturnAuditBean findManyId(Integer id);

	int updateExStatus(ReturnAuditBean o);

	int updateInfoData(ReturnAuditBean o);

	List<ReturnAuditBean> findBackRecord(ReturnAuditBean o);

	List<ReturnAuditBean> findPutCodebyInfo(PutInStorageTaskBean o);
	
	List<ReturnAuditBean> findScrapCodebyInfo(ScrapDetailsBean o);
	
	int insertInfo(ReturnAuditBean o);

	int updateMaStatus(MachineBean mb);

}
