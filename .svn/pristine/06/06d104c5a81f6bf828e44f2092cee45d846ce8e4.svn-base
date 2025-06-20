package com.bonus.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bonus.core.UUIDHelper;
import com.bonus.sys.beans.ResourcesBean;
import com.bonus.sys.service.ResourcesService;

public class BaseController<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ResourcesService resourcesService;

	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}
   
	public AjaxRes getAjaxRes() {
		return new AjaxRes();
	}
	
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){	
		return UUIDHelper.get32UUID();
	}
	
   
	/**
	 * 得到PageData
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 资源的权限（URl级别）
	 * 
	 * @param type
	 *            资源类别(优化速度)
	 * @return
	 */
	protected boolean doSecurityIntercept(String type) {
		try {
			String servletPath = getRequest().getServletPath();
			servletPath = StringUtils.substringBeforeLast(servletPath, ".");// 去掉后面的后缀
			int userId = UserShiroHelper.getCurrentUser().getId();
			List<ResourcesBean> authorized = resourcesService.resAuthorized(userId,
					type);
			for (ResourcesBean r : authorized) {
				if (r != null && StringUtils.isNotBlank(r.getUrl())) {
					if (StringUtils.equals(r.getUrl(), servletPath)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return false;
	}

	/**
	 * 资源的权限（URl级别,拥有第一级资源权限，这资源才能访问）
	 * 
	 * @param type
	 *            资源类别(优化速度)
	 * @param url
	 *            第一级资源
	 * @return
	 */
	
	protected boolean doSecurityIntercept(String type, String url) {
		try {
			int userId = UserShiroHelper.getCurrentUser().getId();
			List<ResourcesBean> authorized = resourcesService.resAuthorized(
					userId, type);
			logger.debug("authorized=" + authorized + ",type=" + type
					+ ",userId=" + userId + ",url=" + url);
			for (ResourcesBean r : authorized) {
				if (r != null && StringUtils.isNotBlank(r.getUrl())) {
					if (StringUtils.equals(r.getUrl(), url)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return false;
	}

}
