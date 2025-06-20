package com.bonus.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.StringHelper;
import com.bonus.repairCheck.beans.RepairCheckDetailsBean;
import com.bonus.repairCheck.service.RepairCheckDetailsService;
import com.bonus.sys.BaseController;

@Controller
@RequestMapping("/backstage/app/repairCheck/")
public class AppRepairCheckController extends BaseController<Object> {

	@Autowired
	private RepairCheckDetailsService service;
	//维修任务
	@RequestMapping(value = "findCheckTask", method = RequestMethod.POST)
	@ResponseBody
	public List<RepairCheckDetailsBean> findCheckTask(RepairCheckDetailsBean o) {
		if(o.getStartTime() != null && o.getStartTime() != "") {
			o.setStartTime(o.getStartTime() + " 00:00:00");
			o.setEndTime(o.getEndTime() + " 23:59:59");
		}
		List<RepairCheckDetailsBean> list = new ArrayList<RepairCheckDetailsBean>();
		try {
			list = service.findCheckTask(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	//维修明细
	@RequestMapping(value = "findCheckTaskDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<RepairCheckDetailsBean> findCheckTaskDetails(RepairCheckDetailsBean o) {
		List<RepairCheckDetailsBean> list = new ArrayList<RepairCheckDetailsBean>();
		try {
			String str = o.getIsSplit();
			if(StringHelper.isEmpty(str)){
				
				o.setIsSplit("1");
			}
			list = service.findCheckTaskDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	//检验明细
		@RequestMapping(value = "getCheckIndexList", method = RequestMethod.POST)
		@ResponseBody
		public List<RepairCheckDetailsBean> getCheckIndexList(RepairCheckDetailsBean o) {
			List<RepairCheckDetailsBean> list = new ArrayList<RepairCheckDetailsBean>();
			try {
				list = service.getCheckIndexList(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return list;
		}
		
		//检验明细
		@RequestMapping(value = "getCheckedNumList", method = RequestMethod.POST)
		@ResponseBody
		public List<RepairCheckDetailsBean> getCheckedNumList(RepairCheckDetailsBean o) {
			List<RepairCheckDetailsBean> list = new ArrayList<RepairCheckDetailsBean>();
			try {
				list = service.getCheckedNumList(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return list;
		}
		
	
	
		//固资维修明细查询code
		@RequestMapping(value = "getRepairCheckCodeList", method = RequestMethod.POST)
		@ResponseBody
		public List<RepairCheckDetailsBean> getRepairCodeList(RepairCheckDetailsBean o) {
			List<RepairCheckDetailsBean> list = new ArrayList<RepairCheckDetailsBean>();
			try {
				list = service.getRepairCodeList(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return list;
		}
		
	//查询具体机具
	@RequestMapping(value = "findCheckDevice", method = RequestMethod.POST)
	@ResponseBody
	public List<RepairCheckDetailsBean> findCheckDevice(RepairCheckDetailsBean o) {
		List<RepairCheckDetailsBean> list = new ArrayList<RepairCheckDetailsBean>();
		try {
			list = service.findCheckDevice(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	//查询具体机具
		@RequestMapping(value = "checkOperation", method = RequestMethod.POST)
		@ResponseBody
		public int checkOperation(RepairCheckDetailsBean o) {
			int res = 0;
			try {
				service.checkOperation(o);
				res = 1;
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
			return res;
		}
		
		//查询具体机具   非固资本次维修
		@RequestMapping(value = "submitNumCheck", method = RequestMethod.POST)
		@ResponseBody
		public int submitNumCheck(RepairCheckDetailsBean o) {
			int res = 0;
			try {
				res = service.submitNumCheck(o);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
			return res;
		}
	
		//固资检验通过
		@RequestMapping(value = "submitCodeCheck", method = RequestMethod.POST)
		@ResponseBody
		public int submitCodeCheck(HttpServletRequest request,@RequestBody RepairCheckDetailsBean o) {
			int res = 0;
			try {
				int num = service.submitCodeCheck(o);
				if(num == 1) {
					res = 1;
				}else {
					res = -1;
				}
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = -1;
			}
			return res;
		}
				
}
