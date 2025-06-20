package com.bonus.sys;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.bonus.sys.beans.UserBean;

/**
 * 封装shiro用对象获取
 * 
 */
public class UserShiroHelper {
	/**
	 * 获取当前对象的拷贝
	 * 
	 * @return
	 */
	public static UserBean getCurrentUser() {
		UserBean customer = null;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if (null != session) {
			Object obj = session.getAttribute(GlobalConst.SESSION_USER);
			if (null != obj && obj instanceof UserBean) {
				try {
					/**
					 * 复制一份对象，防止被错误操作
					 */
					customer = (UserBean) BeanUtils.cloneBean((UserBean) obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return customer;
	}

	/**
	 * 获取当前真实的对象，可以进行操作实体
	 * 
	 * @return
	 */
	public static UserBean getRealCurrentUser() {
		UserBean customer = null;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if (null != session) {
			Object obj = session.getAttribute(GlobalConst.SESSION_USER);
			if (null != obj && obj instanceof UserBean) {
				try {
					/**
					 * 不复制一份对象，防止被错误操作
					 */
					customer = (UserBean) obj;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return customer;
	}
}
