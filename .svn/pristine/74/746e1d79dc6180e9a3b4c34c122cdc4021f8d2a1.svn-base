package com.bonus.repair.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repair.service.RepairDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/repair/")
public class RepairDetailsController extends BaseController<RepairDetailsBean> {

	@Autowired
	private RepairDetailsService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/scrap/scrapAppointList";
	}

	@RequestMapping(value = "findRepairTask", method = RequestMethod.POST)
	@ResponseBody
	public List<RepairDetailsBean> findRepairTask( RepairDetailsBean o) {
		List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
		try {
			list = service.findRepairTask(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(RepairDetailsBean o) {
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
	
}
