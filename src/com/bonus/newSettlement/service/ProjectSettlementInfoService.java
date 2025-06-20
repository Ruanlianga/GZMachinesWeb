package com.bonus.newSettlement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bonus.newSettlement.beans.ProjectSettlementInfoBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface ProjectSettlementInfoService extends BaseService<ProjectSettlementInfoBean> {

	int insertSlt(ProjectSettlementInfoBean o);

	ProjectSettlementInfoBean findSettlementInfoById(ProjectSettlementInfoBean o);

	List<ProjectSettlementInfoBean> findBaseInfoById(ProjectSettlementInfoBean o);

	List<ProjectSettlementInfoBean> findMaInfoById(ProjectSettlementInfoBean o);

	Page<ProjectSettlementInfoBean> findSettleContent(Page<ProjectSettlementInfoBean> page,
			ProjectSettlementInfoBean o);

	Integer updateSettleSta(ProjectSettlementInfoBean o);
	
	Object uploadFile(HttpServletRequest request, ProjectSettlementInfoBean o);

	ProjectSettlementInfoBean findSettlementFileUrlById(ProjectSettlementInfoBean o);

    

}
