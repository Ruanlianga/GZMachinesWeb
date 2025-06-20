package com.bonus.plan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.plan.beans.AuditBean;
import com.bonus.plan.beans.PlanApplyBean;
import com.bonus.plan.beans.PlanDataDetailBean;
import com.bonus.plan.beans.PlanDevBean;
import com.bonus.plan.beans.PlanProBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface PlanApplicationDao extends BaseDao<PlanApplyBean> {
	
	/**
	 * 查询设备集合
	 * @param o
	 * @return
	 */
	List<PlanDevBean> getDevTreeList(PlanDevBean o);
	/**
	 * 数据u插入
	 * @param o
	 * @return
	 */
	int insertData(PlanApplyBean o);
	/**
	 * 查询当日数量
	 * @param o
	 * @return
	 */
	int getTodayPlanNum(PlanApplyBean o);
	/**
	 * 插入 list数据
	 * @param details
	 * @param o
	 */
	int insertDetail(@Param("list") List<PlanDataDetailBean> details,@Param("param") PlanApplyBean o);
	/**
	 * 查询计划内容
	 * @return
	 */
	PlanApplyBean getPlanDetailsbyId(PlanApplyBean o);
	/**
	 * 查询审核记录
	 * @param o
	 * @return
	 */
	List<AuditBean> getAuditList(PlanApplyBean o);
	/**
	 * 查询申请记录详情
	 * @param o
	 * @return
	 */
	List<PlanDataDetailBean> getDetailsList(PlanApplyBean o);
	/**
	 * 删除详情数据
	 * @param o
	 */
	void deleteDetails(PlanApplyBean o);
	/**
	 * 修改计划内容
	 * @param o
	 * @return
	 */
	int updatePlan(PlanApplyBean o);
	
	
	/**
	 * 修改计划内容
	 * @param o
	 * @return
	 */
	int updatePlanAndSub(PlanApplyBean o);
	/**
	 * 工程下拉选
	 * @param o
	 * @return
	 */
	List<PlanProBean> getProList(PlanDevBean o);
	

}
