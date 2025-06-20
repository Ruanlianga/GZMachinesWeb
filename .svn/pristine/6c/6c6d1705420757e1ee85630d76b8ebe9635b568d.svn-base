package com.bonus.rm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.rm.beans.ReturnMaterialAccBean;
import com.bonus.rm.service.ReturnMaterialAccService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/rm/taskAcc/")
public class ReturnMaterialAccController extends BaseController<ReturnMaterialAccBean> {

	@Autowired
	private ReturnMaterialAccService service;
	

	@RequestMapping("list")
	public String index(Model model) {
		return "/rm/tasklistacc";
	}
	
	@RequestMapping("taskAccDetails")
	public String taskAccDetails(Model model) {
		return "/rm/taskAccDetails";
	}
	


	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<ReturnMaterialAccBean> page, ReturnMaterialAccBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<ReturnMaterialAccBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "findByPageTwo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPageTwo(Page<ReturnMaterialAccBean> page, ReturnMaterialAccBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String companyId = user.getCompanyId();
			o.setOrgId(companyId);
			Page<ReturnMaterialAccBean> result = service.findByPageTwo(o, page);
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
