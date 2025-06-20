package com.bonus.sys.service;

import java.util.List;

import com.bonus.sys.BaseService;
import com.bonus.sys.beans.ResourcesBean;
import com.bonus.sys.beans.ZNode;

public interface ResourcesService extends BaseService<ResourcesBean> {

	List<ResourcesBean> resAuthorized(int userId, String type);

	List<ResourcesBean> findMenuTree(int userId, String layer);

	List<ZNode> getResources(int roleId);

	void updateResouces(int id, String chks);

	List<ZNode> listResources(ResourcesBean o);

	int tranDeleteBatch(List<ResourcesBean> list);

	int updateMenu(ResourcesBean o);

	int tranDelete(ResourcesBean o);

}
