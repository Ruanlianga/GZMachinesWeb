package com.bonus.plan.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bonus.plan.beans.AuditBean;
import com.bonus.plan.beans.PlanApplyAuditBean;
import com.bonus.plan.beans.PlanApplyBean;
import com.bonus.plan.beans.PlanDataDetailBean;
import com.bonus.plan.beans.ProNeedInfoBean;
import com.bonus.plan.beans.ProPlanInfoBean;
import com.bonus.plan.dao.PlanAuditDao;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.beans.UserBean;
import com.thoughtworks.xstream.mapper.Mapper;
/**
 * 系统审核业务流程
 * @author xj
 */
@Service("PlanAuditServiceImpl")
public class PlanAuditServiceImpl  extends BaseServiceImp<PlanApplyAuditBean> implements PlanAuditService {
	
	
	@Autowired
	private PlanAuditDao dao;
	
	/**
	 * 计划审核
	 */
	@Override
	@Transactional
	public AjaxRes planAudit(AuditBean o) {
		AjaxRes ar=getAjaxRes();
		try {
			Session session = SecurityUtils.getSubject().getSession();
			UserBean acount = (UserBean) session
					.getAttribute(GlobalConst.SESSION_USER);
			String userName=acount.getLoginName();
			System.err.println("userName===="+userName);
			String applyId=o.getApplyId();
			if(StringUtils.isEmpty(applyId)) {
				ar.setFailMsg("请选择计划进行审核");
				return ar;
			}else {
				//需要配置审核流程节点 、按照不同的用户 角色权限 进行审核
				//查询计划节点 及状态
				PlanApplyBean plan= dao.getPlanApplyDta(applyId);
				//目前未配置角色 全部都可以审核
				String statusType=plan.getStatusType();
				String status=plan.getStatus();
				if(!"1".equals(status)) {
					ar.setFailMsg("该计划已被审核,请刷新重试");
					return ar;
				}
				String auditStatus=o.getAuditStatus();
				//审核通过
				if("2".equals(auditStatus)) {
					ar.setSucceedMsg("审核成功!");
					auditSuccess(o,statusType,acount,plan);
				}else {//审核驳回
					ar.setSucceedMsg("驳回成功!");
					aduitError(o,statusType,acount);
				}
			}
			
		}catch (Exception e) {
			ar.setFailMsg("审核失败,请联系管理员");
			throw new RuntimeException("审核失败");
		}
		return ar;
	}
	
	/**
	 * 审核通过
	 */
	public void auditSuccess(AuditBean vo,String statusType,UserBean user,PlanApplyBean plan) {
		//添加审核记录
		vo.setAuditType(statusType);//审核节点	
		vo.setAuditor(user.getId());
		vo.setUpdater(user.getId());
		vo.setAuditUser(user.getLoginName());
		dao.insertAuditRecord(vo);
		//修改下次 审核流程
		vo.setAuditStatus("1");
		if("2".equals(statusType)){
			vo.setAuditType("3");
		}else if("3".equals(statusType)){
			vo.setAuditType("4");
		}else if("4".equals(statusType)){
			vo.setAuditType("1");
			vo.setAuditStatus("2");
			//全部审核通过 将需求同步到 工程需求表中
			String proId=plan.getProId();
			String applyId=vo.getApplyId();
			//依据applyId去吧本次审核的数据全部找到 进行
			insertProNeed(proId,applyId);
		}
		//修改审核状态 
		dao.updatePlanAudit(vo);
	}
	
	public void  insertProNeed(String proId,String applyId) {
		try {
			//工程 计划-出库 统计
			ProPlanInfoBean proPlanInfo=dao.getProPlanInfo(proId);
			int count=dao.getPlanNeedNum(applyId);
			if(proPlanInfo==null || StringUtils.isEmpty(proPlanInfo.getProId())) {
				proPlanInfo=new ProPlanInfoBean();
				proPlanInfo.setPlanNum(1);
				proPlanInfo.setNeedNum(count);
				proPlanInfo.setStatus(0);
				proPlanInfo.setOutNum(0);
				proPlanInfo.setRecordNum(0);
				proPlanInfo.setOutNum2(0);
				proPlanInfo.setRecordNum2(0);
				proPlanInfo.setLastDay("-");
				proPlanInfo.setProId(proId);
				proPlanInfo.setTzNum(0);
				dao.insertProPlanInfo(proPlanInfo);
			}else {
				int planNum=proPlanInfo.getPlanNum();
				int needNum=proPlanInfo.getNeedNum();
				int status=proPlanInfo.getStatus();
				planNum=planNum+1;
				needNum=needNum+count;
				if(status==2) {
					status=1;
				}
				proPlanInfo.setPlanNum(planNum);
				proPlanInfo.setNeedNum(needNum);
				proPlanInfo.setStatus(status);
				dao.updateProPlanInfo(proPlanInfo);
			}
			//工程 计划-出库 详情
			List<PlanDataDetailBean> list=dao.getPlanDetails(applyId);
			for (PlanDataDetailBean vo : list) {
				ProNeedInfoBean bean=dao.getProNeedInfo(proId,vo.getModuleId());
				//源工程无此数据
				if(bean==null ||  StringUtils.isEmpty(bean.getId())) {
					bean=new ProNeedInfoBean();
					bean.setProId(proId);
					bean.setType(vo.getType());
					bean.setModule(vo.getModule());
					bean.setName(vo.getTypeName());
					bean.setModuleId(vo.getModuleId());
					bean.setNeedNum(vo.getNeedNum());
					bean.setUnit(vo.getUnit());
					bean.setNeedType("1");
					bean.setFhNum(0);
					bean.setTzNum(0);
					dao.insertProNeedInfo(bean);
				}else {
					int needNum=bean.getNeedNum();
					int num=vo.getNeedNum();
					int newNum=needNum+num;
					bean.setNeedNum(newNum);
					dao.updateNeedNum(bean);
				}
			}
		}catch (Exception e) {
			throw new RuntimeException("审核失败");
		}
		
		
	}
	
	
	/**
	 * 审核驳回
	 */
	public void aduitError(AuditBean vo,String statusType,UserBean user) {
		//修改审核状态 
		vo.setAuditStatus(statusType);//审核节点	
		dao.updatePlanAudit(vo);
		vo.setAuditor(user.getId());
		vo.setUpdater(user.getId());
		vo.setAuditUser(user.getLoginName());
		dao.insertAuditRecord(vo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
