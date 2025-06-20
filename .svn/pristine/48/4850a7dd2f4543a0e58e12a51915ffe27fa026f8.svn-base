package com.bonus.repair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.repair.beans.RepairTaskRecordBean;
import com.bonus.repair.dao.RepairTaskRecordDao;
import com.bonus.sys.BaseServiceImp;

@Service("repairRecord")
public class RepairTaskRecordServiceImp extends BaseServiceImp<RepairTaskRecordBean> implements RepairTaskRecordService {

	@Autowired
	private RepairTaskRecordDao dao;
	
	
	@Override
	public List<RepairTaskRecordBean> getPrintDetails(RepairTaskRecordBean o){
		return dao.getPrintDetails(o);
	}
	
	public int saveRemark(RepairTaskRecordBean o) {
		return dao.saveRemark(o);
	}
	
	@Override
	public int updateUrl(RepairTaskRecordBean o) {
		return dao.updateUrl(o);
	}
	
}
