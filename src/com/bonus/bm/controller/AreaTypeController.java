package com.bonus.bm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.beans.AreaTypeBean;
import com.bonus.bm.service.AreaTypeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;

@Controller
@RequestMapping("/backstage/areaType/")
public class AreaTypeController extends BaseController<AreaTypeBean> {

	@Autowired
	private AreaTypeService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/areaTypelist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<AreaTypeBean> page,AreaTypeBean o){
		AjaxRes ar = getAjaxRes();
			try {
				Page<AreaTypeBean> result = service.findByPage(o, page);
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
	public AjaxRes find(AreaTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<AreaTypeBean> list = service.find(o);
			AreaTypeBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(AreaTypeBean o){
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
	public AjaxRes add(AreaTypeBean o){
		AjaxRes ar=getAjaxRes();
		try {
			int res = service.insertBean(o);
			if(res==1)ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
			else ar.setFailMsg(GlobalConst.SAVE_FAIL);	
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="del", method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(AreaTypeBean o){
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
			service.deleteBatch(chks);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value="findAreaType",method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAreaType(AreaTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<AreaTypeBean> list = service.findAreaType(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	@RequestMapping(value="findma",method=RequestMethod.POST)
	@ResponseBody
	public AjaxRes findma(AreaTypeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<AreaTypeBean> list = service.findma(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "orgTree", method = RequestMethod.POST) // 获得文件夹的树
	@ResponseBody
	public AjaxRes orgTree(AreaTypeBean o) {

		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = service.orgTree();
		
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	
	
}
