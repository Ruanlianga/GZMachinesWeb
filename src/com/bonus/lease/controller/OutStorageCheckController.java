package com.bonus.lease.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.lease.beans.OutStorageCheckBean;
import com.bonus.lease.service.OutStorageCheckService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/outstorage/")
public class OutStorageCheckController extends BaseController<OutStorageCheckBean> {

	@Autowired
	private OutStorageCheckService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/lease/outstoragechecklist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<OutStorageCheckBean> page, OutStorageCheckBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<OutStorageCheckBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findByPageOne", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPageOne(Page<OutStorageCheckBean> page, OutStorageCheckBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<OutStorageCheckBean> result = service.findByPageOne(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
}
