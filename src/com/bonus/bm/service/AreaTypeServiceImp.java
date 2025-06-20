package com.bonus.bm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.AreaTypeBean;
import com.bonus.bm.dao.AreaTypeDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.ZNode;

@Service("areaType")
public class AreaTypeServiceImp extends BaseServiceImp<AreaTypeBean> implements AreaTypeService{

	@Autowired AreaTypeDao dao;
	
	@Override
	public int insertBean(AreaTypeBean o) {
		return dao.insertBean(o);
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<AreaTypeBean> list = new ArrayList<AreaTypeBean>();
			for (String s : chk) {
				try {
					//int id = Integer.parseInt(s);
					AreaTypeBean sd = new AreaTypeBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}

	@Override
	public List<AreaTypeBean> findAreaType(AreaTypeBean o) {
		List<AreaTypeBean> list = dao.findAreaType(o);
		return list;
	}

	@Override
	public List<AreaTypeBean> findma(AreaTypeBean o) {
		
		return dao.findma(o);
	}

	@Override
	public List<AreaTypeBean> findmt(AreaTypeBean o) {
		// TODO Auto-generated method stub
		return dao.findmt(o);
	}

	@Override
	public List<ZNode> orgTree() {
		// TODO Auto-generated method stub
		return dao.orgTree(null);
	}

}

