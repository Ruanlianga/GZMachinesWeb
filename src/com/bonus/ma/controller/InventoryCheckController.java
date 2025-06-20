package com.bonus.ma.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.InventoryRecordBean;
import com.bonus.ma.service.InventoryRecordService;
import com.bonus.ma.service.MachineTypeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/InventoryCheck/")
public class InventoryCheckController extends BaseController<InventoryRecordBean> {

	@Autowired
	private InventoryRecordService service;

	@Autowired
	private MachineTypeService mtservice;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/InventoryChecklist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<InventoryRecordBean> page, InventoryRecordBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<InventoryRecordBean> result = service.findByPage(o, page);
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
