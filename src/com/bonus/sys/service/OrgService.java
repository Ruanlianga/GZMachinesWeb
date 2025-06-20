package com.bonus.sys.service;

import java.util.List;

import com.bonus.sys.BaseService;
import com.bonus.sys.beans.OrgBean;

public interface OrgService extends BaseService<OrgBean> {

	int delete(Integer id);

	int insertOrgBean(OrgBean record);

	int updateOrgBean(OrgBean record);

	List<OrgBean> findRepairGroup(OrgBean o);

}
