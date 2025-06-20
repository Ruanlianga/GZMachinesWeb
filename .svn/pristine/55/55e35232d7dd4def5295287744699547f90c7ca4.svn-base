package com.bonus.plan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonus.plan.beans.DeliveryBean;
import com.bonus.sys.BaseController;

/**
 * @author xj
 * 机具公司发货
 */
@Controller
@RequestMapping("/backstage/delivery/")
public class DeliveryController extends BaseController<DeliveryBean>{
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/demandPlan/delivery_list";
	}

}
