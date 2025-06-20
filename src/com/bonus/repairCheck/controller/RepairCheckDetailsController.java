package com.bonus.repairCheck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonus.repairCheck.beans.RepairCheckDetailsBean;
import com.bonus.repairCheck.service.RepairCheckDetailsService;
import com.bonus.sys.BaseController;

@Controller
@RequestMapping("/backstage/repairCheck/")
public class RepairCheckDetailsController extends BaseController<RepairCheckDetailsBean> {

	@Autowired
	private RepairCheckDetailsService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/scrap/scrapAppointList";
	}
	
}
