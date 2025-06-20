package com.bonus.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.RoleBean;
import com.bonus.sys.beans.ZNode;
import com.bonus.sys.service.RoleService;

@Controller
@RequestMapping("/backstage/role/")
public class RoleController extends BaseController<RoleBean> {
	
	@Autowired
	private RoleService service;
	
	@RequestMapping("list")
	public String index(Model model) {
		return "/sys/rolelist";
	}
	
	@RequestMapping("reslist")
	public String roleIndex(Model model) {
		return "/sys/rolereslist";
	}
	
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAll() {
		AjaxRes ar = getAjaxRes();
		try {
			List<ZNode> list = service.getRoleBeans();
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
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes update(RoleBean o) {
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
	public AjaxRes add(RoleBean o) {
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
	public AjaxRes del(RoleBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.delete(o.getId());
			ar.setSucceedMsg(GlobalConst.DEL_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DEL_FAIL);
		}
		return ar;
	}
	
	
	@RequestMapping(value = "findUserRole", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findUserRole(RoleBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int id = UserShiroHelper.getCurrentUser().getId();
			ar.setSucceed(id);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

}
