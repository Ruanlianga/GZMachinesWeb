package com.bonus.lease.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.LeaseApplicationBean;
import com.bonus.plan.beans.ProNeedInfoBean;
import com.bonus.plan.beans.ProOutInfoVo;
import com.bonus.sys.BaseDao;
import org.apache.ibatis.annotations.Param;

//租赁申请
@BonusBatis
public interface LeaseApplicationDao extends BaseDao<LeaseApplicationBean>{

	// 查询该工程之前是否已经存在计划
	int getPlanIsExistProject(Integer projectId);

	// 该工程之前存在需求计划，进行一个增加出库单数量的处理
	int updateProjectOutOrderNum(Integer proId);

	// 修改需求计划单的设备出库数量
	int updateProjectPlanOutNum(ProNeedInfoBean o);

	// 该工程之前不存在需求计划，进行一个增加计划处理
	int insertProjectOutOrderNum(Integer proId);

	// 判断该工程该日期是否已经存在领料单,有则返回领料单id 无则null
	Integer getLeaseOrderIsExistProject(Integer projectId);

	// 新增工程领料单出库明细details表数据
	int insertLeaseOutOrderDetails(ProNeedInfoBean o);

	// 新增工程领料出库单
	int insertLeaseOrderByProject(ProOutInfoVo o);

	String findApplyNumber(LeaseApplicationBean o);

	String findLeaseApplyNumber(LeaseApplicationBean o);

	Integer deleteApply(LeaseApplicationBean o);

	List<LeaseApplicationBean> getApplyNumberById();

	void delByNumber(String applyNumber);

	void delByNumber(LeaseApplicationBean leaseApplicationBean);

	List<LeaseApplicationBean> getApplyNumberById(LeaseApplicationBean o);
	
	List<LeaseApplicationBean> getTaskDetails(LeaseApplicationBean o);

	List<LeaseApplicationBean> getSubInfo(LeaseApplicationBean o);
}
