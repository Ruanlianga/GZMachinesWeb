package com.bonus.repair.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.repair.beans.RepairTaskRecordBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface RepairTaskRecordDao extends BaseDao<RepairTaskRecordBean> {

	public List<RepairTaskRecordBean> getPrintDetails(@Param("param") RepairTaskRecordBean o);

	public int saveRemark(RepairTaskRecordBean o);
	
	public int updateUrl(RepairTaskRecordBean o);

}
