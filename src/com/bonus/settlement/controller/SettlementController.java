package com.bonus.settlement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.settlement.beans.SettlementBean;
import com.bonus.settlement.service.SettlementService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/settlement/")
public class SettlementController extends BaseController<SettlementBean> {

	@Autowired
	private SettlementService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/settlement/settlement_list";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<SettlementBean> page, SettlementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<SettlementBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(SettlementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<SettlementBean> list = service.find(o);
			SettlementBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findCode(SettlementBean o) {
		AjaxRes ar = getAjaxRes();
		String nowDay = DateTimeHelper.getNowDateFomart();
		String nowMonth = DateTimeHelper.getNowMonth();
		o.setTime(nowMonth);
		String count = service.findCode(o);
		int counts = Integer.parseInt(count) + 1;
		String code = "JS" + nowDay + "-" + counts;
		ar.setSucceedMsg(code);
		return ar;
	}
}
