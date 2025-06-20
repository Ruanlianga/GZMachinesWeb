package com.bonus.plan.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bonus.core.DateTimeHelper;
import com.bonus.plan.beans.AuditBean;
import com.bonus.plan.beans.PlanApplyBean;
import com.bonus.plan.beans.PlanDataDetailBean;
import com.bonus.plan.beans.PlanDevBean;
import com.bonus.plan.beans.PlanProBean;
import com.bonus.plan.dao.PlanApplicationDao;
import com.bonus.plan.dao.PlanAuditDao;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.beans.UserBean;


/**
 * 计划申请服务层
 * @author xj
 */
@Service("PlanApplicationServiceImp")
public class PlanApplicationServiceImp  extends BaseServiceImp<PlanApplyBean> implements PlanApplicationService {
	
	@Autowired
	private PlanApplicationDao  dao;
	
	
	
	@Autowired
	private PlanAuditDao auditDao;
	/**
	 * -- 新增计划接口
	 */
	@Override
	public AjaxRes addPlan(PlanApplyBean o) {
		AjaxRes ar=getAjaxRes();
		try {
			// shiro获取用户信息,shiro管理的session
			Session session = SecurityUtils.getSubject().getSession();
			// 获得用户
			UserBean acount = (UserBean) session
					.getAttribute(GlobalConst.SESSION_USER);
			String userName=acount.getLoginName();
			o.setCreator(userName);
			o.setUpdater(userName);
			List<PlanDataDetailBean> details=JSON.parseArray(o.getJsonData(), PlanDataDetailBean.class);
			if(details==null || details.size()<1) {
				ar.setFailMsg("请填写机具明细");
				return ar;
			}else {
				int num=dao.getTodayPlanNum(o);
				String code=getCode(num);
				o.setCode(code);
				int suNum=dao.insertData(o);
				if(suNum>0) {
					int success=dao.insertDetail(details,o);
					insetRescord(acount,o,"0");
				}
				
			
			}
			ar.setSucceedMsg("提交成功");
		}catch (Exception e) {
			ar.setFailMsg("提交失败,请联系管理员");
			logger.error(e.toString(), e);
		}
		
		return ar;
	}
	
		/**
		 * 添加发起流程
		 * @param acount
		 * @param o
		 */
	public void insetRescord(UserBean acount,PlanApplyBean o,String setAuditType) {
		AuditBean bean=new AuditBean();
		//流程节点 0 发起申请
		bean.setAuditType(setAuditType);	
		bean.setAuditStatus("0");
		bean.setAuditor(acount.getId());
		bean.setUpdater(acount.getId());
		bean.setAuditUser(acount.getLoginName());
		bean.setApplyId(o.getId());
		auditDao.insertAuditRecord(bean);
	}
	
	/**
	 * 修改计划信息
	 */
	@Override
	public AjaxRes updatePlan(PlanApplyBean o) {
		AjaxRes ar=getAjaxRes();
		try {
			// shiro获取用户信息,shiro管理的session
			Session session = SecurityUtils.getSubject().getSession();
			// 获得用户
			UserBean acount = (UserBean) session
					.getAttribute(GlobalConst.SESSION_USER);
			String userName=acount.getLoginName();
			o.setCreator(userName);
			o.setUpdater(userName);
			List<PlanDataDetailBean> details=JSON.parseArray(o.getJsonData(), PlanDataDetailBean.class);
			if(details==null || details.size()<1) {
				ar.setFailMsg("请填写机具明细");
				return ar;
			}else {
				//修改并提交
				int suNum=dao.updatePlanAndSub(o);
				if(suNum>0) {
					dao.deleteDetails(o);
					int success=dao.insertDetail(details,o);
					insetRescord(acount,o,"1");
				}
			}
			ar.setSucceedMsg("提交成功");
		}catch (Exception e) {
			ar.setFailMsg("提交失败,请联系管理员");
			logger.error(e.toString(), e);
		}
		return ar;
	}
	
	
	private static String getCode(int num) {
		num++;
		String year=DateTimeHelper.getNowYear()+DateTimeHelper.getFormatNowMonthAndDay();
			if(num<10) {
			 return 	year+"00"+num;
			}else if(num<100) {
			return year+"0"+num;
			}
		return year+num;
	}



	/**
	 * 获取设备集合树
	 */
	@Override
	public AjaxRes getDevTreeList(PlanDevBean o) {
		AjaxRes ar = getAjaxRes();
		try {
		//	o.setId("0");
			List<PlanDevBean> list=dao.getDevTreeList(o);
//			for (PlanDevBean planDevBean : list) {
//					getChilderList(planDevBean);
//			}
			 ar.setSucceed(list);
		
		}catch (Exception e) {
			 ar.setSucceed(new ArrayList<PlanDevBean>());
			logger.error(e.toString(), e);
		}
		 return ar;
		
	}
	
	
	public void getChilderList(PlanDevBean vo ) {
		List<PlanDevBean> childer=dao.getDevTreeList(vo);
		if(childer!=null && childer.size()>0) {
			for (PlanDevBean planDevBean : childer) {
					getChilderList(planDevBean);
			}
			vo.setChilder(childer);
		}
		
	}


	/**
	 * 查询计划详情
	 */
	@Override
	public AjaxRes getPlanDetails(PlanApplyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			PlanApplyBean vo=dao.getPlanDetailsbyId(o);
			List<AuditBean> auditList=dao.getAuditList(o);
			List<PlanDataDetailBean> details=dao.getDetailsList(o);
			vo.setAuditList(auditList);
			vo.setDetails(details);
			ar.setSucceed(vo);
		}catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setSucceed(new ArrayList<PlanDevBean>());
		}
		 return ar;
	}



	/**
	 * 获取数据详情
	 */
	@Override
	public List<PlanDataDetailBean> getDetailsList(PlanApplyBean o) {
		List<PlanDataDetailBean> details=dao.getDetailsList(o);
		return details;
	}

	@Override
	public AjaxRes getProList(PlanDevBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<PlanProBean> list=dao.getProList(o);
			ar.setSucceed(list);
		}catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setSucceed(new ArrayList<PlanProBean>());
		}
		 return ar;
		
	}




	

}
