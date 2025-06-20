package com.bonus.newInput.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.service.MachineService;
import com.bonus.newInput.beans.MachinesAcceptBean;
import com.bonus.newInput.service.InputDetailsService;
import com.bonus.newInput.service.MachinesAcceptService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/accept/")
public class MachinesAccpetController extends BaseController<MachinesAcceptBean> {

	@Autowired
	private MachinesAcceptService service;

	@Autowired
	private InputDetailsService idService;

	@Autowired
	private MachineService mService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/newInput/acceptlist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<MachinesAcceptBean> page, MachinesAcceptBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			Page<MachinesAcceptBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(MachinesAcceptBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachinesAcceptBean> list = service.find(o);
			MachinesAcceptBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	

	@RequestMapping(value = "findParts", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findParts(MachinesAcceptBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachinesAcceptBean> list = service.findParts(o);
			MachinesAcceptBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "findInput", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findInput(MachinesAcceptBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachinesAcceptBean> list = service.findInput(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}


	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes edit(MachinesAcceptBean o, HttpServletRequest req) {
		AjaxRes ar = getAjaxRes();
		try {
			String taskId = req.getParameter("taskId");
			String maTypeId = req.getParameter("maTypeId");
			String checkNum = req.getParameter("editNum");
			String machineNum = req.getParameter("machineNum");
			o.setTaskId(taskId);
			o.setMaTypeId(maTypeId);
			o.setCheckNum(checkNum);
			service.updAcpNum(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	// 查询库存量
	public String findStockNums() {
		MachineBean bean = new MachineBean();
		bean.setBuyTime(DateTimeHelper.getNowYear());
		String stockNums = mService.findStockNums(bean);
		return stockNums;
	}

}
