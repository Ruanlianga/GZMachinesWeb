package com.bonus.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.json.JacksonHelper;
import com.bonus.sys.BaseController;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.service.UserService;

@Controller
@RequestMapping("/backstage/app/")
public class AppController extends BaseController<Object> {

	@Autowired
	private UserService userservice;

	@RequestMapping(value = "loginApp", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String errInfo = "";
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		String username = request.getParameter("telphone");
		String password = request.getParameter("passwd");
		String cId = request.getParameter("cId");
		// shiro加入身份验证
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toUpperCase());
		token.setRememberMe(true);
		try {
			if (!currentUser.isAuthenticated()) {
				currentUser.login(token);
			}
			// 记录登录日志
		} catch (UnknownAccountException uae) {
			errInfo = "usererror";// 用户名或密码有误
		} catch (IncorrectCredentialsException ice) {
			errInfo = "usererror"; // 密码错误
		} catch (LockedAccountException lae) {
			errInfo = "inactive";// 未激活
		} catch (ExcessiveAttemptsException eae) {
			errInfo = "attemptserror";// 错误次数过多
		} catch (AuthenticationException ae) {
			errInfo = "codeerror";// 验证未通过
		}
		// 验证是否登录成功
		if (!currentUser.isAuthenticated()) {
			token.clear();
		}
		UserBean bean = new UserBean();
		if (StringUtils.isEmpty(errInfo)) {
			errInfo = "success"; // 验证成功
			bean = userservice.findUserBeanByLoginName(username);
			UserBean o = new UserBean();
			o.setcId(cId);
			o.setId(bean.getId());
			userservice.update(o);
			currentUser.logout();
		}
		map.put("result", errInfo);
		map.put("userInformationApp", bean);
		return map;
	}

	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public Object findAll(UserBean o) throws Exception {
		UserBean result = userservice.findAll(o);
		if (result == null) {
			return "{\"list\":" + "[" + "]" + "}";
		} else {
			String json = new JacksonHelper().jsonSerialize(result);
			return "{\"list\":" + json.toString() + "}";
		}
	}

}
