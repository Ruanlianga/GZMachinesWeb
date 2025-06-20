package com.bonus.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.MenuTreeHelper;
import com.bonus.sys.beans.ResourcesBean;
import com.bonus.sys.beans.ZNode;
import com.bonus.sys.service.ResourcesService;

@Controller
@RequestMapping("/backstage/res/")
public class ResourcesController extends BaseController<ResourcesBean> {

	@Autowired
	private ResourcesService service;

	@RequestMapping("list")
	public String index(Model model) {
		return "/sys/reslist";
	}

	@RequestMapping(value = "listAllParentMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes listAllParentMenu(ResourcesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ResourcesBean> r = service.find(o);
			List<ResourcesBean> tree = MenuTreeHelper.buildTree(r);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", tree);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "listResources", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes listResources(ResourcesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> r = service.listResources(o);
			ar.setSucceed(r);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(ResourcesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			o.setUrl(StringUtils.trim(o.getUrl()));
			o.setParentId(StringUtils.isNotBlank(o.getParentId()) ? o
					.getParentId() : "0");
			service.insert(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "delBatch", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes delBatch(String chks) {
		AjaxRes ar = getAjaxRes();
		try {
			String[] chk = chks.split(",");
			List<ResourcesBean> list = new ArrayList<ResourcesBean>();
			for (String s : chk) {
				ResourcesBean sd = new ResourcesBean();
				int ss = Integer.parseInt(s);
				sd.setId(ss);
				list.add(sd);
			}
			int res = service.tranDeleteBatch(list);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
			else
				ar.setFailMsg("请先删除子资源");

		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}

		return ar;
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes find(ResourcesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<ResourcesBean> list = service.find(o);
			ResourcesBean resources = list.get(0);
			ar.setSucceed(resources);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(ResourcesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = service.updateMenu(o);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
			else if (res == 2)
				ar.setFailMsg("上级资源不能是本资源");
			else
				ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(ResourcesBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = service.tranDelete(o);
			if (res == 1)
				ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
			else
				ar.setFailMsg("请先删除子资源");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
	
		return ar;
	}

	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAll(String roleId) {
		AjaxRes ar = getAjaxRes();
		try {
			int id = 0;
			try {
				id = Integer.parseInt(roleId);
			} catch (Exception e) {
			}
			List<ZNode> list = service.getResources(id);
			if (list != null) {
				for (ZNode bean : list) {
					bean.setIcon(getRequest().getContextPath()
							+ "/static/css/sys/images/user_group.gif");
				}
			}
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "updateResouces", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes updateResouces(String roleId, String chks) {
		AjaxRes ar = getAjaxRes();

		int id = 0;
		try {
			id = Integer.parseInt(roleId);
		} catch (Exception e) {
		}
		try {
			service.updateResouces(id, chks);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

}
