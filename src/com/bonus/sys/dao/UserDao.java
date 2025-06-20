package com.bonus.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.beans.ZNode;

@BonusBatis
public interface UserDao extends BaseDao<UserBean> {

	public int deleteByPrimaryKey(Integer id);

	public int insertSelective(UserBean record);

	public int insertBean(UserBean record);

	public UserBean selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(UserBean record);

	public int updateByPrimaryKey(UserBean record);

	public UserBean findUserBeanByLoginName(String loginName);

	public UserBean findUserBeanByLoginNameOrPhone(String loginNameOrPhone);

	public void setSetting(UserBean cu);

	public void resetPwd(UserBean o);

	public List<ZNode> getOrgBeans(UserBean o);

	public int findCountByLoginName(String loginName);

	public List<UserBean> findAllByRole(UserBean o);

	public void insertUserRole(UserBean ub);

	public void deleteUserRole(int roleId);

	public UserBean findAll(UserBean o);

	public List<UserBean> findAllUser();

	public List<UserBean> findByOrg(UserBean o);

	public List<UserBean> findByRepair();

	public String findCompanyName(UserBean o);

	public List<UserBean> getUnit(UserBean o);

	public List<ZNode> findPerson(UserBean o);

	public UserBean findByUserId(@Param("userId") String userId, @Param("flag") int flag);

	public List<UserBean> findByPostId(String postId);

	public List<UserBean> findPersonByOrgId(@Param("orgId") String orgId);

	public List<UserBean> findCIdByOrgId(@Param("orgId") String orgId, @Param("postId") String postId);

	public List<UserBean> findAllPerson();

	public List<ZNode> findCheckPerson(UserBean o);

	public List<ZNode> findServicePerson(UserBean o);

	public UserBean findCIdByUserId(int id);

	public List<UserBean> findAllUserInfo(UserBean o);

	public UserBean findCompanyInfo(UserBean o);
	
	public int insertPic(UserBean o);

}