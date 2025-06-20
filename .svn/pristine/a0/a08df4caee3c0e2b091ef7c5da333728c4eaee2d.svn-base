package com.bonus.scrap.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.scrap.beans.ScrapDetailsBean;
import com.bonus.scrap.service.ScrapDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

@Controller
@RequestMapping("/backstage/scrap/")
public class ScrapDetailsController extends BaseController<ScrapDetailsBean> {

	@Autowired
	private ScrapDetailsService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/scrap/scrapAppointList";
	}
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ScrapDetailsBean> page, ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<ScrapDetailsBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	@RequestMapping(value = "updatePersons", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updatePersons(ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String[] ids = o.getIds().split(",");
			for(int i = 0;i < ids.length;i++){
				o.setId(ids[i]);
				service.update(o);
			}
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	@RequestMapping(value = "isApproval", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isApproval(ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.isApproval(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}

	@RequestMapping(value = "isApprovals", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isApprovals(ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String[] ids = o.getScrapPersonIds().split(",");
			for(int i = 0;i < ids.length;i++){
				o.setScrapPersonId(ids[i]);
				service.isApproval(o);
			}
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}

	@RequestMapping(value = "surePeople", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes surePeople(ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.surePeople(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
	
	@RequestMapping(value = "surePeoples", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes surePeoples(ScrapDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String[] ids = o.getIds().split(",");
			for(int i = 0;i < ids.length;i++){
				o.setId(ids[i]);
				service.surePeople(o);
			}
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}
}
