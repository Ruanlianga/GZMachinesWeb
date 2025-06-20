package com.bonus.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/backstage/index")
public class IndexController {

	/**
	 * 访问首页地图
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mapIndex")
	public ModelAndView toLogin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index/map");
		return mv;
	}
	
	@RequestMapping(value = "/mapSpecialIndex")
	public ModelAndView toSpecialLogin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index/mapSpecial");
		return mv;
	}
}
