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

import com.bonus.bm.beans.AssetAttributesBean;
import com.bonus.bm.service.AssetAttributesService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/asset/")
public class AssetAttributesController extends BaseController<AssetAttributesBean> {

	@Autowired
	private AssetAttributesService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/bm/assetAttributes_list";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<AssetAttributesBean> page,AssetAttributesBean o){
		AjaxRes ar = getAjaxRes();
			try {
				Page<AssetAttributesBean> result = service.findByPage(o, page);
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
	public AjaxRes find(AssetAttributesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<AssetAttributesBean> list = service.find(o);
			AssetAttributesBean station = list.get(0);
			ar.setSucceed(station);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAll(AssetAttributesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<AssetAttributesBean> list = service.find(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(AssetAttributesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.update(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(AssetAttributesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.insert(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(AssetAttributesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.delete(o);
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}

}
