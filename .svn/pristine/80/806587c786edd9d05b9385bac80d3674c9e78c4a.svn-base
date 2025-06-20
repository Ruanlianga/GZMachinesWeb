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
import com.bonus.sys.beans.OrgBean;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.beans.ZNode;
import com.bonus.sys.service.OrgService;
import com.bonus.sys.service.UserService;

@Controller
@RequestMapping("/backstage/org/")
public class OrgController extends BaseController<ZNode> {

	@Autowired
	private OrgService service;

	@Autowired
	private UserService userService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/sys/orglist";
	}

	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findAll() {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			List<ZNode> list = userService.getOrgBeans(user);
			if (list != null) {
				for (ZNode bean : list) {
					if ("0".equals(bean.getpId())) {
						bean.setIcon(getRequest().getContextPath()
								+ "/static/css/sys/images/home.gif");
					} else {
						bean.setIcon(getRequest().getContextPath()
								+ "/static/css/sys/images/user_group.gif");
					}
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
	public AjaxRes update(OrgBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.updateOrgBean(o);
			ar.setSucceedMsg(GlobalConst.UPDATE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.UPDATE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes add(OrgBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			service.insertOrgBean(o);
			ar.setSucceedMsg(GlobalConst.SAVE_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.SAVE_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes del(OrgBean o) {
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
	
	@RequestMapping(value = "findRepairGroup", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findRepairGroup(OrgBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			List<OrgBean> list = service.findRepairGroup(o);
			ar.setSucceed(list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

}
