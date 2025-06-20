package com.bonus.bm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.ModelBean;
import com.bonus.bm.dao.ModelDao;
import com.bonus.sys.BaseServiceImp;

@Service("model")
public class ModelServiceImp extends BaseServiceImp<ModelBean> implements ModelService{

	@Autowired ModelDao dao;
	
	@Override
	public int insertBean(ModelBean o) {
		return dao.insertBean(o);
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<ModelBean> list = new ArrayList<ModelBean>();
			for (String s : chk) {
				try {
					//int id = Integer.parseInt(s);
					ModelBean sd = new ModelBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}

	@Override
	public List<ModelBean> findModel(ModelBean o) {
		List<ModelBean> list = dao.findModel(o);
		return list;
	}

}
