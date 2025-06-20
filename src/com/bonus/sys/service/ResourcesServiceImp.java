package com.bonus.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.ResourcesBean;
import com.bonus.sys.beans.ZNode;
import com.bonus.sys.dao.ResourcesDao;

@Service("ResourcesService")
public class ResourcesServiceImp extends BaseServiceImp<ResourcesBean>
		implements ResourcesService {

	protected Logger logger = LoggerFactory
			.getLogger(ResourcesServiceImp.class);

	@Override
	public List<ResourcesBean> resAuthorized(int userId, String type) {
		return ((ResourcesDao) baseDao).resAuthorized(userId, type);
	}
  
	@Override
	public List<ResourcesBean> findMenuTree(int userId, String layer) {
		return ((ResourcesDao) baseDao).findMenuTree(userId, layer);
	}

	@Override
	public List<ZNode> getResources(int roleId) {
		return ((ResourcesDao) baseDao).getResources(roleId);
	}

	@Override
	public void updateResouces(int roleId, String chks) {
		ResourcesDao resDao = (ResourcesDao) baseDao;
		// 事务删除
		resDao.deleteRoleResouces(roleId);

		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			for (String s : chk) {
				try {
					int resId = Integer.parseInt(s);
					resDao.insertRoleResouce(roleId, resId);
				} catch (Exception e) {
					logger.error("插入角色资源表失败!", e);
					return;
				}
			}
		}
	}

	@Override
	public List<ZNode> listResources(ResourcesBean o) {
		return ((ResourcesDao) baseDao).listResources(o);
	}

	@Override
	public int tranDeleteBatch(List<ResourcesBean> os) {
		int res = 0;
		ResourcesDao dao = (ResourcesDao) baseDao;
		int childCount = dao.childBatchCount(os);
		if (childCount == 0) {
			dao.deleteBatch(os);
			res = 1;
		}
		return res;

	}

	@Override
	public synchronized int updateMenu(ResourcesBean o) {
		int res = 0;
		if (!StringUtils.equals(o.getId() + "", o.getParentId())) {
			ResourcesDao dao = ((ResourcesDao) baseDao);
			o.setUrl(StringUtils.trim(o.getUrl()));
			// 只有父级为0的菜单才能修改层级
			ResourcesBean r = dao.findAndson(o);
			// 层级发生改变才修改层级，优化速度
			if (o.getLayer() != r.getLayer()) {
				List<String> rs = new ArrayList<String>();
				setAllSonRes(rs, r);
				dao.updateBatchLayer(rs, String.valueOf(o.getLayer()));
			}
			dao.updateByPrimaryKeySelective(o);
			res = 1;
		} else {
			res = 2;
		}
		return res;
	}

	private void setAllSonRes(List<String> list, ResourcesBean res) {
		list.add(res.getId() + "");
		List<ResourcesBean> nodes = res.getNodes();
		if (nodes != null && nodes.size() > 0) {
			for (ResourcesBean r : nodes) {
				setAllSonRes(list, r);
			}
		}
	}

	@Override
	public int tranDelete(ResourcesBean o) {
		int res = 0;
		ResourcesDao dao = (ResourcesDao) baseDao;
		String resId = o.getId() + "";
		int childCount = dao.childCount(resId);
		if (childCount == 0) {
			dao.deleteByPrimaryKey(o.getId());
			res = 1;
		}
		return res;
	}

}
