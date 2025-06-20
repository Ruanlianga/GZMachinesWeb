package com.bonus.bm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.bm.beans.ProjectManageBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.ZNode;

@BonusBatis
public interface ProjectManageDao extends BaseDao<ProjectManageBean> {

	List<ProjectManageBean> getVolLever();

	List<ProjectManageBean> getProjectType();

	public List<ZNode> findWorkTree();

	List<ProjectManageBean> getProjectName(ProjectManageBean o);

	List<ZNode> projectTree(ProjectManageBean o);

	List<ZNode> projectTreeByUnitId(ProjectManageBean o);

	ProjectManageBean findByName(String name);

	List<ProjectManageBean> getProvinces();

	List<ProjectManageBean> getCities(@Param("param")ProjectManageBean o);

	List<ProjectManageBean> getDistricts(@Param("param")ProjectManageBean o);

}
