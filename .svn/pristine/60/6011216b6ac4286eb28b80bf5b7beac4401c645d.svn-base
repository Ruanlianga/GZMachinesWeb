package com.bonus.ma.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.MaOrgBean;
import com.bonus.ma.dao.MaOrgDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.ZNode;

@Service("maOrg")
public class MaOrgServiceImp extends BaseServiceImp<MaOrgBean> implements MaOrgService{

	@Autowired 
	private MaOrgDao dao;

	@Override
	public List<ZNode> getOrgTree(MaOrgBean o) {
		return dao.getOrgTree(o);
	}

	@Override
	public List<MaOrgBean> getMaListByOrg(MaOrgBean o) {
		return dao.getMaListByOrg(o);
	}

	@Override
	public int updateOrgRelation(String orgId, String chks) {
		int  res = 0;
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<MaOrgBean> list = new ArrayList<MaOrgBean>();
			for (String s : chk) {
					MaOrgBean mo = new MaOrgBean();
					mo.setMaModelId(s);
					mo.setOrgId(orgId);
					list.add(mo);
			}
		try {
			res = dao.insertOrgRelation(list);
		} catch (Exception e) {
			logger.error("插入分公司机具类型表失败!", e);
		}
		}
		return res;	
		
	}



}
