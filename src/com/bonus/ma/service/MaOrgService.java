package com.bonus.ma.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bonus.ma.beans.MaOrgBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;

public interface MaOrgService extends BaseService<MaOrgBean> {

	List<ZNode> getOrgTree(MaOrgBean o);

	List<MaOrgBean> getMaListByOrg(MaOrgBean o);

	int updateOrgRelation(String orgId, String chks);


}
