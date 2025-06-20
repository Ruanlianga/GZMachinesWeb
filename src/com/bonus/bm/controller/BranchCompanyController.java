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

import com.bonus.bm.beans.BranchCompanyBean;
import com.bonus.bm.service.BranchCompanyService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/branchCompany/")
public class BranchCompanyController extends BaseController<BranchCompanyBean> {

	@Autowired
	private BranchCompanyService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/branch_company_list";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<BranchCompanyBean> page, BranchCompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<BranchCompanyBean> station = service.findByPage(o, page);
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
	public AjaxRes del(BranchCompanyBean o) {
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
	public AjaxRes update(BranchCompanyBean o) {
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
	public AjaxRes find(BranchCompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<BranchCompanyBean> list = service.find(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "getBranchCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getCompany() {
		AjaxRes ar = getAjaxRes();
		try {
			List<BranchCompanyBean> list = service.getBranchCompany();
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insert(BranchCompanyBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<BranchCompanyBean> list = service.findByNameAndCode(o.getName(),o.getCode());
			if(list.size() > 0){
				ar.setSucceedMsg("该分公司或分公司编号已存在！！！");
				
			}else if (list.size() ==0 ||  null == list ){
				service.insert(o);
				ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes treeDelete(BranchCompanyBean o) {
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

}
