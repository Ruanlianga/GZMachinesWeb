package com.bonus.plan.service;


import java.util.List;

import com.bonus.plan.beans.AuditBean;
import com.bonus.plan.beans.PlanApplyBean;
import com.bonus.plan.beans.PlanDataDetailBean;
import com.bonus.plan.beans.PlanDevBean;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;


/**
 * 计划申请 接口层
 * @author xj
 *
 *
 */
public interface PlanApplicationService  extends BaseService<PlanApplyBean>{
	/**
	 * 新增计划信息
	 * @param o
	 * @return
	 */
	AjaxRes addPlan(PlanApplyBean o);
	/**
	 * 
	 * @param o
	 * @return
	 */
	AjaxRes getDevTreeList(PlanDevBean o);
	/**
	 * 查看计划详情
	 * @param o
	 * @return
	 */
	AjaxRes getPlanDetails(PlanApplyBean o);
	
	/**
	 * 导出数据
	 * @param o
	 * @return
	 */
	List<PlanDataDetailBean> getDetailsList(PlanApplyBean o);
	/**
	 * 修改审核记录
	 * @param o
	 * @return
	 */
	AjaxRes updatePlan(PlanApplyBean o);
	/**
	 * 查询
	 * @param o
	 * @return
	 */
	AjaxRes getProList(PlanDevBean o);



}
