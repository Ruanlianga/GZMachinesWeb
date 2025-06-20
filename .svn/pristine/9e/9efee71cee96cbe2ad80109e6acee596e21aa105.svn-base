package com.bonus.newInput.controller;

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

import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.newInput.service.NewInputQrcodeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/inputQrcode/")
public class NewInputQrcodeController extends BaseController<NewInputQrcodeBean> {

	@Autowired
	private NewInputQrcodeService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/newInput/newInputQrcodelist";
	}

	@RequestMapping("details")
	public String details(Model model) {
		return "/newInput/newInputQrcodeDetails";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<NewInputQrcodeBean> page, NewInputQrcodeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setOrgId(companyId);
			Page<NewInputQrcodeBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findQrcodeByTaskId", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findQrcodeByTaskId(Page<NewInputQrcodeBean> page, NewInputQrcodeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<NewInputQrcodeBean> result = service.findQrcodeByTaskId(page,o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	/**
	 *新购 二维码绑定
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "building", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes building(NewInputQrcodeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.building(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findByQrcode", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> findByQrcode(NewInputQrcodeBean o) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = service.findByQrcode(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	@RequestMapping(value = "findByDevCode", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> findByDevCode(NewInputQrcodeBean o) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = service.findByDevCode(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
}
