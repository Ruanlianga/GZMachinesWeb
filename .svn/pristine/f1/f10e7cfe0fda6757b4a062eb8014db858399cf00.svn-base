package com.bonus.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.ResourcesBean;
import com.bonus.sys.beans.ZNode;

@BonusBatis
public interface ResourcesDao extends BaseDao<ResourcesBean>{
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ResourcesBean record);

    ResourcesBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourcesBean record);

    int updateByPrimaryKey(ResourcesBean record);

	List<ResourcesBean> resAuthorized(@Param("userId")int userId, @Param("type")String type);

	List<ResourcesBean> findMenuTree(@Param("userId")int userId, @Param("layer")String layer);

	List<ZNode> getResources(@Param("roleId")int roleId);

	void deleteRoleResouces(@Param("roleId")int roleId);

	void insertRoleResouce(@Param("roleId")int roleId, @Param("resId")int resId);

	List<ZNode> listResources(ResourcesBean o);

	 /**
     * 获取子资源数量
     * @param parentId
     * @return
     */
	public int childCount(@Param("parentId")String parentId);
	 /**
     * 获取子资源数量(批量)
     * @param os
     * @return
     */
	public int childBatchCount(List<ResourcesBean> os);
	 /**
     * 获取资源并包括子资源
     * @param os
     * @return
     */
	public ResourcesBean findAndson(ResourcesBean r);
	 /**
     * 获取资源并包括子资源
     * @param os
     * @return
     */
	public void updateBatchLayer(@Param("list")List<String> rs,@Param("layer")String layer);
	
	
	
	
}