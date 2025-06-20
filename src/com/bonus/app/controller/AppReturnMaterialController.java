package com.bonus.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.beans.ReturnMaterialTaskBean;
import com.bonus.rm.service.ReturnMaterialDetailsService;
import com.bonus.rm.service.ReturnMaterialTaskService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/app/rm/")
public class AppReturnMaterialController extends BaseController<Object> {

	@Autowired
	private ReturnMaterialTaskService rmtService;
	
	@Autowired ReturnMaterialDetailsService rmdService;

	@RequestMapping(value = "findAllTask", method = RequestMethod.POST)
	@ResponseBody
	public List<ReturnMaterialTaskBean> outStorageInspectionList(ReturnMaterialTaskBean o, HttpServletRequest request) {
		List<ReturnMaterialTaskBean> list = new ArrayList<>();
		try {
			list = rmtService.findAllTask(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	@RequestMapping(value = "findDevByWorkId", method = RequestMethod.POST)
	@ResponseBody
	public List<ReturnMaterialTaskBean> findDevByWorkId(ReturnMaterialTaskBean o, HttpServletRequest request) {
		List<ReturnMaterialTaskBean> list = new ArrayList<>();
		try {
			list = rmtService.findDevByWorkId(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	} 
	@RequestMapping(value = "findDevByUnitId", method = RequestMethod.POST)
	@ResponseBody
	public List<ReturnMaterialTaskBean> findDevByUnitId(ReturnMaterialTaskBean o, HttpServletRequest request) {
		System.err.println(o.getIsCode());
		List<ReturnMaterialTaskBean> list = new ArrayList<>();
		try {
			list = rmtService.findDevByUnitId(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	} 
	@RequestMapping(value = "findByCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByCode(ReturnMaterialDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			Object object = rmdService.findByCode(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", object);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "receive", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes receive(ReturnMaterialDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			Object object = rmdService.receiveDevice(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", object);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findLeaseBackNum", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findLeaseBackNum(ReturnMaterialDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			Object object = rmdService.findLeaseBackNum(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", object);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "reBackNums", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes reBackNums(ReturnMaterialDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			Object object = rmdService.reBackNums(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", object);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "endBack", method = RequestMethod.POST)
	@ResponseBody
	public int endBack(ReturnMaterialTaskBean o, HttpServletRequest request) {
		int res = 1;
		try {
			rmtService.updateBean(o);
			res = 1;
			return res;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;
		}
	}
	
	@RequestMapping(value = "findTaskDetail", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findTaskDetail(ReturnMaterialDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ReturnMaterialDetailsBean> list = rmdService.findTaskDetail(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findTaskDetailInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findTaskDetailInfo(ReturnMaterialDetailsBean o, HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ReturnMaterialDetailsBean> list = rmdService.findTaskDetailInfo(o);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", list);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
}
