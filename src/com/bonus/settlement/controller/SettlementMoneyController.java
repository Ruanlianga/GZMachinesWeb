package com.bonus.settlement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonus.settlement.beans.SettlementMoneyBean;
import com.bonus.settlement.service.SettlementMoneyService;
import com.bonus.sys.BaseController;

@Controller
@RequestMapping("/backstage/settlementMoney/")
public class SettlementMoneyController extends BaseController<SettlementMoneyBean> {

	@Autowired
	private SettlementMoneyService service;


	@RequestMapping("list")
	public String index(Model model) {
		return "/settlement/settlementMoney_list";
	}
	
}
