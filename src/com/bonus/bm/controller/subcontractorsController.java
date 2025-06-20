package com.bonus.bm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.beans.subcontractorsBean;
import com.bonus.bm.service.subcontractorsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/subcontractors/")
public class subcontractorsController extends BaseController<subcontractorsBean> {

	@Autowired
	private subcontractorsService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/subcontractorsManager";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<subcontractorsBean> page, subcontractorsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<subcontractorsBean> station = service.findByPage(o, page);
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
	public AjaxRes del(subcontractorsBean o) {
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
	public AjaxRes update(subcontractorsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes treeDelete(subcontractorsBean o) {
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
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insert(subcontractorsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			subcontractorsBean bean = service.findByName(o.getName());
			if(bean == null){
				service.insert(o);
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			}else{
				ar.setSucceedMsg("分包商已存在");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(subcontractorsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<subcontractorsBean> list = service.find(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findSubcontractors", method = RequestMethod.POST)
	@ResponseBody
	public List<subcontractorsBean> findSubcontractors(subcontractorsBean o) {
		List<subcontractorsBean> list = new ArrayList<subcontractorsBean>();
		try {
			list = service.findSubcontractors();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/*
	@RequestMapping(value = "getCompanyType", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCompanyType() {
		AjaxRes ar = getAjaxRes();
		try {
			List<subcontractorsBean> list = service.getCompanyType();
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}*/

}
