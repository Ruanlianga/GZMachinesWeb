package com.bonus.settlement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.settlement.beans.SettlementAuditBean;
import com.bonus.settlement.service.SettlementAuditService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/settlementAudit/")
public class SettlementAuditController extends BaseController<SettlementAuditBean> {

	@Autowired
	private SettlementAuditService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/settlement/settlementAudit_list";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<SettlementAuditBean> page, SettlementAuditBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<SettlementAuditBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "settlementAudit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes settlementAudit(SettlementAuditBean o) {
		AjaxRes ar = getAjaxRes();
		try {
		    service.settlementAudit(o);
		 	ar.setSucceedMsg("审核成功");
		} catch (Exception e) {
			ar.setFailMsg("审核失败");
			logger.error(e.toString(), e);
		}
		return ar;
	}
}
