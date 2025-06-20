package com.bonus.pis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.pis.service.PutInStorageService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/putstorage/")
public class PutInStorageController extends BaseController<PutInStorageBean> {

	@Autowired
	private PutInStorageService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/pis/putstoragelist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<PutInStorageBean> page, PutInStorageBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<PutInStorageBean> result = service.findByPage(o, page);
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
