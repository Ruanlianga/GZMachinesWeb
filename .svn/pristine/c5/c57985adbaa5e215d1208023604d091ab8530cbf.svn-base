package com.bonus.newSettlement.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.newSettlement.beans.ProjectFinishDetailsBean;
import com.bonus.newSettlement.beans.ProjectFinishInfoBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface ProjectFinishInfoDao extends BaseDao<ProjectFinishInfoBean> {

	Integer insertBean(ProjectFinishInfoBean o);

	Integer insertDetails(ProjectFinishDetailsBean bean);

	Integer updateAgreement(ProjectFinishInfoBean o);

	ProjectFinishInfoBean findSettlementInfoById(ProjectFinishInfoBean o);

	List<ProjectFinishInfoBean> getProFinishFiles(ProjectFinishInfoBean o);
	
	void insertFile(ProjectFinishInfoBean o);
	
}
