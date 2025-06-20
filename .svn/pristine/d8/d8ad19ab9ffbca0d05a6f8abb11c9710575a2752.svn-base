package com.bonus.plan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.plan.beans.AuditBean;
import com.bonus.plan.beans.PlanApplyAuditBean;
import com.bonus.plan.beans.PlanApplyBean;
import com.bonus.plan.service.PlanApplicationService;
import com.bonus.plan.service.PlanAuditService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

/**
 * 计划审核
 * @author xj
 */
@Controller
@RequestMapping("/backstage/planAudit/")
public class PlanAuditController  extends BaseController<PlanApplyAuditBean> {
	
	
	@Autowired
	private PlanAuditService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/demandPlan/plan_check_list";
	}
	
	@RequestMapping("checkDetail")
	public String checkDetail(Model model) {
		return "/demandPlan/child/apply_plan_check_detail";
	}
	
	@RequestMapping("auditForm")
	public String auditForm(Model model) {
		return "/demandPlan/child/audit_form";
	}
	
	/**
	 * 查询审核数据 列表
	 * @param page
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<PlanApplyAuditBean> page, PlanApplyAuditBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<PlanApplyAuditBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	
	
	/**
	 * 计划审核
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "planAudit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes planAudit(@RequestBody AuditBean  o) {
		return service.planAudit(o);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
