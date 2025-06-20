package com.bonus.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.CipherHelper;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.beans.ZNode;
import com.bonus.sys.dao.UserDao;

@Service("UserService")
public class UserServiceImp extends BaseServiceImp<UserBean> implements UserService {

	protected Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserBean findUserBeanByLoginName(String loginName) {
		try {
            return userDao.findUserBeanByLoginName(loginName);
		} catch (Exception e) {
			logger.error("根据登录名查找用户信息时，发生异常", e);
		}
		return null;
	}

	/**
	 * 通过登录名或手机号查找用户
	 */
	public UserBean findUserBeanByLoginNameOrPhone(String loginNameOrPhone) {
		try {
            return userDao.findUserBeanByLoginNameOrPhone(loginNameOrPhone);
		} catch (Exception e) {
			logger.error("根据登录名查找用户信息时，发生异常", e);
		}
		return null;
	}

	@Override
	public void setPerData(UserBean o) {
		UserBean cu = UserShiroHelper.getRealCurrentUser();
		o.setId(cu.getId());
		o.setUpdateTime((new Date()));
		userDao.updateByPrimaryKeySelective(o);
		cu.setName(o.getName());
		cu.setMail(o.getMail());
		cu.setTelphone(o.getTelphone());
	}

	@Override
	public void setSetting(String skin) {
		UserBean cu = UserShiroHelper.getCurrentUser();
		cu.setSkin(skin);
		userDao.setSetting(cu);
		UserShiroHelper.getRealCurrentUser().setSkin(skin);
	}

	@Override
	public int preResetPwd(String opwd, String npwd, String qpwd) {
		int res = 0;
		int accountId = UserShiroHelper.getRealCurrentUser().getId();
		String loginName = UserShiroHelper.getRealCurrentUser().getLoginName();
		if (StringUtils.isNotBlank(opwd) && StringUtils.isNotBlank(npwd)) {
			if (StringUtils.equals(npwd, qpwd)) {
				UserBean o = new UserBean();
				o.setId(accountId);
				UserBean odata = userDao.findUserBeanByLoginName(loginName);
				String oPwdEncrypt = CipherHelper.createPwdEncrypt(loginName, opwd.toUpperCase(), odata.getSalt());
				String odataPwdEncrypt = odata.getPasswd();
				if (StringUtils.equals(oPwdEncrypt, odataPwdEncrypt)) {
					String salt = CipherHelper.createSalt();
					String pwrsMD5 = npwd.toUpperCase();
					o.setPasswd(CipherHelper.createPwdEncrypt(loginName, pwrsMD5, salt));
					o.setSalt(salt);
					userDao.resetPwd(o);
					res = 1;
				} else {
					res = 2;// 密码不正确
				}
			} else {
				res = 3;// 两次密码不一致
			}
		}
		return res;
	}

	@Override
	public int sysResetPwd(UserBean o) {
		int res = 0;
		String pwd = o.getPasswd();
		o.setUpdateTime(new Date());
		int userId = o.getId();
		if (userId >= 0) {
			UserBean odata = userDao.selectByPrimaryKey(userId);
			String loginName = odata.getLoginName();
			// 随机密码,以后发邮箱
			String salt = CipherHelper.createSalt();
			String pwrsMD5 = CipherHelper.generatePassword(pwd);
			o.setPasswd(CipherHelper.createPwdEncrypt(loginName, pwrsMD5, salt));
			o.setSalt(salt);
			userDao.resetPwd(o);
			res = 1;
		} else {
			res = 2;
		}
		return res;

	}

	@Override
	public List<ZNode> getOrgBeans(UserBean o) {
		return userDao.getOrgBeans(o);
	}

	@Override
	public int insertUser(UserBean o) {
		int res = 0;
		String loginName = o.getLoginName();
		// 查询数据库是否已经存在用户名
		if (StringUtils.isNotBlank(loginName) && (userDao.findCountByLoginName(loginName) == 0)) {
			String pwrs = "12345678";// 随机密码,以后发邮箱
			String pwrsMD5 = CipherHelper.generatePassword(pwrs);// 第一次加密md5，
			String salt = CipherHelper.createSalt();
			o.setPasswd(CipherHelper.createPwdEncrypt(loginName, pwrsMD5, salt));
			o.setSalt(salt);
			o.setCreateTime(new Date());
			userDao.insertSelective(o);
			res = o.getId();
		}
		return res;
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<UserBean> list = new ArrayList<UserBean>();
			for (String s : chk) {
				try {
					int id = Integer.parseInt(s);
					UserBean sd = new UserBean();
					sd.setId(id);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			userDao.deleteBatch(list);
		}
	}

	@Override
	public List<UserBean> findAllByRole(UserBean o) {
		return userDao.findAllByRole(o);
	}

	@Override
	public void updateUsers(int roleId, String chks) {
		// 事务删除
		userDao.deleteUserRole(roleId);

		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			for (String s : chk) {
				try {
					UserBean ub = new UserBean();
					int id = Integer.parseInt(s);
					ub.setId(id);
					ub.setRoleId(roleId);
					userDao.insertUserRole(ub);
				} catch (Exception e) {
					logger.error("插入用户角色表失败!", e);
					return;
				}
			}

		}
	}
	
	public int insertPic(UserBean o) {
		return userDao.insertPic(o);
	}

	@Override
	public UserBean findAll(UserBean o) {
		UserBean userList = userDao.findAll(o);
		return userList;
	}

	@Override
	public List<UserBean> findAllUser() {
		return userDao.findAllUser();
	}

	@Override
	public List<UserBean> findByOrg(UserBean o) {
		List<UserBean> list = userDao.findByOrg(o);
		return list;
	}

	@Override
	public List<UserBean> findByRepair() {
		return userDao.findByRepair();
	}

	@Override
	public String findCompanyName(UserBean o) {
		return userDao.findCompanyName(o);
	}

	@Override
	public List<UserBean> getUnit(UserBean o) {
		return userDao.getUnit(o);
	}

	@Override
	public List<ZNode> findPerson(UserBean o) {
		return userDao.findPerson(o);
	}

	@Override
	public UserBean findByUserId(String userId,int flag) {
		return userDao.findByUserId(userId,flag);
	}

	@Override
	public List<UserBean> findByPostId(String postId) {
		return userDao.findByPostId(postId);
	}

	@Override
	public List<UserBean> findPersonByOrgId(String orgId) {
		return userDao.findPersonByOrgId(orgId);
	}

	@Override
	public List<UserBean> findCIdByOrgId(String orgId,String postId) {
		return userDao.findCIdByOrgId(orgId,postId);
	}

	@Override
	public List<UserBean> findAllPerson() {
		return userDao.findAllPerson();
	}

	@Override
	public void insertUserRole(UserBean o) {
		userDao.insertUserRole(o);
	}

	@Override
	public List<ZNode> findCheckPerson(UserBean o) {
		return userDao.findCheckPerson(o);
	}

	@Override
	public List<ZNode> findServicePerson(UserBean o) {
		return userDao.findServicePerson(o);
	}

	@Override
	public List<UserBean> findAllUserInfo(UserBean o) {
		return userDao.findAllUserInfo(o);
	}

	@Override
	public UserBean findCompanyInfo(UserBean o) {
		return userDao.findCompanyInfo(o);
	}

}
