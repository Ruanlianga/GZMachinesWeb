package com.bonus.newInput.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.newInput.beans.NewInputCheckBean;
import com.bonus.newInput.service.NewInputCheckService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/new/check/")
public class NewInputCheckController extends BaseController<NewInputCheckBean> {

	@Autowired
	private NewInputCheckService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/newInput/newInputBatchlist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<NewInputCheckBean> page, NewInputCheckBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<NewInputCheckBean> result = service.findByPage(o, page);
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
	public AjaxRes update(NewInputCheckBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(NewInputCheckBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "isSure", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes isSure(NewInputCheckBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = service.isSure(o);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else
				ar.setFailMsg(GlobalConst.SAVE_FAIL);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		ar.setSucceed(o);
		return ar;
	}

}
