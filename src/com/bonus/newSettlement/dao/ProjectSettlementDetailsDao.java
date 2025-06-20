package com.bonus.newSettlement.dao;

import com.bonus.core.BonusBatis;
import com.bonus.newSettlement.beans.ProjectSettlementDetailsBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface ProjectSettlementDetailsDao extends BaseDao<ProjectSettlementDetailsBean> {

	Integer insertBean(ProjectSettlementDetailsBean bean);
	
	

	
	
}
