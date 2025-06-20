package com.bonus.ma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.ma.beans.DisassemblyManagementBean;
import com.bonus.ma.beans.GpsBindingBean;
import com.bonus.ma.service.GpsBindingService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;


@Controller
@RequestMapping("/gpsbinding/")
public class GpsBindingController  extends BaseController<GpsBindingBean> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GpsBindingService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/gisbinding";
	}
	
	@RequestMapping("listSpecial")
	public String listSpecial(Model model) {
		return "/ma/gisbindingSpecial";
	}
	
	@RequestMapping("flowList")
	public String flowList(Model model) {
		return "/ma/gisbindingFlow";
	}
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<GpsBindingBean> page, GpsBindingBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<GpsBindingBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "findByPageSpecial", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPageSpecial(Page<GpsBindingBean> page, GpsBindingBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<GpsBindingBean> result = service.findByPageTwo(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "findGpsFlowPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findGpsFlowPage(Page<GpsBindingBean> page, GpsBindingBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<GpsBindingBean> result = service.findGpsFlowPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getGisCodeBylist", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getGisCodeBylist(GpsBindingBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<GpsBindingBean> list = service.getGisCodeBylist(o);
			if(list!=null && list.size()>0){
				ar.setSucceed(1);
			}else{
				ar.setSucceed(0);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value = "updateGisCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateGisCode(GpsBindingBean o) {
		AjaxRes ar = getAjaxRes();
		String msg="保存成功!";
		try {
			  msg = service.updateGisCode(o);
			 ar.setSucceed(msg);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "unBoundGps", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes unBoundGps(GpsBindingBean o) {
		AjaxRes ar = getAjaxRes();
		String msg="解绑成功!";
		try {
			  msg = service.unBoundGps(o);
			 ar.setSucceed(msg);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
}
