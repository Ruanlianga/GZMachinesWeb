package com.bonus.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
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
import com.bonus.sys.PageData;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.ResourcesBean;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.service.ResourcesService;

@Controller
@RequestMapping("/backstage/")
public class BackstageController extends BaseController<Object> {

	@Autowired
	public ResourcesService menuService;
	
	/**
	 * 访问系统首页
	 */
	@RequestMapping("index")
	public String index(Model model) {
		// shiro获取用户信息
		UserBean currentAccount = UserShiroHelper.getCurrentUser();
		model.addAttribute("currentAccount", currentAccount);
		return "/sys/index";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "menu/getMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMenu() {
		AjaxRes ar = getAjaxRes();
		List<ResourcesBean> menus = new ArrayList<ResourcesBean>();
		PageData pd = this.getPageData();
		String ref = pd.getString("ref");
		String layer = pd.getString("layer");
		Object menu_o = null;
		try {
			// shiro获取用户信息,shiro管理的session
			Session session = SecurityUtils.getSubject().getSession();
			// 获得用户
			UserBean acount = (UserBean) session
					.getAttribute(GlobalConst.SESSION_USER);
			// 获得用户Id
			int userId = acount.getId();
			logger.debug("userId=" + userId);
			if (!"y".equals(ref)) {
				menu_o = session.getAttribute(GlobalConst.SESSION_MENULIST);
				logger.debug("menu_o=" + menu_o);
			}
			menus = (List<ResourcesBean>) menu_o;
			if (menus == null) {
				if (StringUtils.isBlank(layer)){
					layer = "1";
				}
				menus = menuService.findMenuTree(userId, layer);
				session.setAttribute(GlobalConst.SESSION_MENULIST, menus);
			}
			if (menus != null) {
				// 将对象处理成树
				String html = MenuTreeHelper.buildTreeHtml(menus);
				ar.setSucceed(html);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg("获取菜单失败");
		}
		return ar;
	}
	

}
