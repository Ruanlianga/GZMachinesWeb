package com.bonus.ma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.ma.beans.DisassemblyManagementBean;
import com.bonus.ma.beans.InventoryRecordBean;
import com.bonus.ma.beans.LibNumsInventoryBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.service.DisassemblyManagementService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

/**
 * 拆组管理
 * @author xj
 *
 *
 */
@Controller
@RequestMapping("/disassembly/")
public class DisassemblyManagementController  extends BaseController<DisassemblyManagementBean>{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DisassemblyManagementService service;
	/**
	 * 拆分
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/disassembly";
	}
	
	@RequestMapping("historyFlow")
	public String details(Model model) {
		return "/ma/historyFlowlist";
	}
	
	/**
	 * 合成新的机具
	 * @param model
	 * @return
	 */
	@RequestMapping("newList")
	public String newList(Model model) {
		return "/ma/synthesis";
	}
	
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<DisassemblyManagementBean> page, DisassemblyManagementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<DisassemblyManagementBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findByPageTwo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPageTwo(Page<DisassemblyManagementBean> page, DisassemblyManagementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<DisassemblyManagementBean> result = service.findByPageTwo(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getListDataById", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getListDataById(DisassemblyManagementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<DisassemblyManagementBean> list = service.getListDataById(o);
			DisassemblyManagementBean bean = list.get(0);
			ar.setSucceed(bean);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 依据设备code查询信息
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "getListDataByDeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getListDataByDeviceCode(DisassemblyManagementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<DisassemblyManagementBean> list = service.getListDataByDeviceCode(o);
			if(list!=null && list.size()>0 ){
				ar.setSucceed(1);//数据存在
			}else{
				ar.setSucceed(0);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 依据设备code查询信息
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "insertDatasplit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insertDatasplit(DisassemblyManagementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String 	msg= service.insertDatasplit(o);
			ar.setSucceed(msg);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	/**
	 * 依据设备code查询信息
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "addDataSynthesis", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addDataSynthesis(DisassemblyManagementBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String 	msg= service.addDataSynthesis(o);
			ar.setSucceed(msg);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
}
