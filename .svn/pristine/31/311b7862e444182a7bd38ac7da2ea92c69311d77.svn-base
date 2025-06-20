package com.bonus.newSettlement.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.newSettlement.beans.ProjectSettlementInfoBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface ProjectSettlementInfoDao extends BaseDao<ProjectSettlementInfoBean> {

	Integer insertBean(ProjectSettlementInfoBean o);

	ProjectSettlementInfoBean findSettlementInfoById(ProjectSettlementInfoBean o);

	List<ProjectSettlementInfoBean> findBaseInfoById(ProjectSettlementInfoBean o);

	List<ProjectSettlementInfoBean> findMaInfoById(ProjectSettlementInfoBean o);

	List<ProjectSettlementInfoBean> findSettleContent(@Param("param")Page<ProjectSettlementInfoBean> page,ProjectSettlementInfoBean o);

	Integer updateSettleSta(ProjectSettlementInfoBean o);
	
	Object uploadFile(HttpServletRequest request, ProjectSettlementInfoBean o);

	ProjectSettlementInfoBean findSettlementFileUrlById(ProjectSettlementInfoBean o);
	
	
}
