package com.bonus.bm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.ProjectManageBean;
import com.bonus.bm.dao.ProjectManageDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.ZNode;

@Service("ProjectManageService")
public class ProjectManageServiceImp extends BaseServiceImp<ProjectManageBean>
implements ProjectManageService{

	@Autowired
	private ProjectManageDao pmDao;

	@Override
	public List<ProjectManageBean> getVolLever() {
		return pmDao.getVolLever();
	}

	@Override
	public List<ProjectManageBean> getProjectType() {
		return pmDao.getProjectType();
	}

	@Override
	public List<ZNode> findWorkTree() {
		return pmDao.findWorkTree();
	}

	@Override
	public List<ProjectManageBean> getProjectName(ProjectManageBean o) {
		return pmDao.getProjectName(o);
	}

	@Override
	public List<ZNode> projectTree(ProjectManageBean o) {
		return pmDao.projectTree(o);
	}

	@Override
	public List<ZNode> projectTreeByUnitId(ProjectManageBean o) {
		return pmDao.projectTreeByUnitId(o);
	}

	@Override
	public ProjectManageBean findByName(String name) {
		return pmDao.findByName(name);
	}

	@Override
	public List<ProjectManageBean> getProvinces() {
		return pmDao.getProvinces();
	}

	@Override
	public List<ProjectManageBean> getCities(ProjectManageBean o) {
		return pmDao.getCities(o);
	}

	@Override
	public List<ProjectManageBean> getDistricts(ProjectManageBean o) {
		return pmDao.getDistricts(o);
	}
	
}
