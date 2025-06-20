package com.bonus.bm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.beans.CompanyTypeBean;
import com.bonus.bm.service.CompanyTypeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/companyType/")
public class CompanyTypeController extends BaseController<CompanyTypeBean> {

	@Autowired
	private CompanyTypeService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/company_type_list";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<CompanyTypeBean> page, CompanyTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<CompanyTypeBean> station = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", station);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(CompanyTypeBean o) {
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

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(CompanyTypeBean o) {
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

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(CompanyTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyTypeBean> list = service.find(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insert(CompanyTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			CompanyTypeBean bean = service.findByName(o.getName());
			if(bean == null){
				service.insert(o);
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			}else{
				ar.setSucceedMsg("单位类型已存在");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes treeDelete(CompanyTypeBean o) {
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

	@RequestMapping(value = "getCompanyType", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCompanyType() {
		AjaxRes ar = getAjaxRes();
		try {
			List<CompanyTypeBean> list = service.getCompanyType();
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

}
