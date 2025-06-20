package com.bonus.multiple.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.multiple.beans.MachineInputDetailsBean;
import com.bonus.multiple.service.MachineInputDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/inputDetail/")
public class MachineInputDetailsController extends BaseController<MachineInputDetailsBean>{
	
	@Autowired 
	private MachineInputDetailsService service;
	
	@RequestMapping("list")	
	public String index(Model model){
		return "/multiple/ma_input_list";
	}
	
	@RequestMapping(value="findByPage",method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<MachineInputDetailsBean> page,MachineInputDetailsBean o){
		AjaxRes ar = getAjaxRes();
			try {
				Page<MachineInputDetailsBean> result = service.findByPage(o, page);
				Map<String,Object> p = new HashMap<String,Object>();
				p.put("list",result);
				ar.setSucceed(p);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		return ar;
	}
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(MachineInputDetailsBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			//
			List<MachineInputDetailsBean> list = service.find(o);
			MachineInputDetailsBean dust = list.get(0);
			ar.setSucceed(dust);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
}
