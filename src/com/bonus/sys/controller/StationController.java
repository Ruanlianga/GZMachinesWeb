package com.bonus.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.beans.StationBean;
import com.bonus.sys.service.StationService;

@Controller
@RequestMapping("/backstage/station/")
public class StationController extends BaseController<StationBean>{
	
	@Autowired
	private StationService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/sys/stationlist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<StationBean> page, StationBean o) {
		AjaxRes ar = getAjaxRes();
		if (ar.setNoAuth(doSecurityIntercept(GlobalConst.RESOURCES_TYPE_MENU,"/backstage/station/list"))) {
			try {
				Page<StationBean> station = service.findByPage(o, page);
				Map<String, Object> p  = new HashMap<String, Object>();
				p.put("list", station);
				ar.setSucceed(p);
			} catch (Exception e){
				logger.error(e.toString(), e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
				
			}
		}
		return ar;
	}
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(StationBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<StationBean> list = service.find(o);
			StationBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(StationBean o){
		AjaxRes ar=getAjaxRes();
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(StationBean o){
		AjaxRes ar=getAjaxRes();
		try {
			int res = service.insertBean(o);
			if(res==1)ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else ar.setFailMsg("登录名已存在");	
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(StationBean o){
		AjaxRes ar=getAjaxRes();
		try {
			service.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="delBatch", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks){
		AjaxRes ar=getAjaxRes();
		try {
			service.delBatchStation(chks);;
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="findPostName", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findNum(StationBean o){
		AjaxRes ar = getAjaxRes();
			try {
				List<StationBean> list = service.findPostName(o);
				ar.setSucceed(list);
			} catch (Exception e) {
				logger.error(e.toString(),e);
				ar.setFailMsg(GlobalConst.DATA_FAIL);
			}
		return ar;
	}

}
