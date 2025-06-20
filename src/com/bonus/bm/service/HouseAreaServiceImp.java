package com.bonus.bm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.HouseAreaBean;
import com.bonus.bm.dao.HouseAreaDao;
import com.bonus.sys.BaseServiceImp;

@Service("houseArea")
public class HouseAreaServiceImp extends BaseServiceImp<HouseAreaBean> implements HouseAreaService{

	@Autowired HouseAreaDao dao;
	
	@Override
	public int insertBean(HouseAreaBean o) {
		return dao.insertBean(o);
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<HouseAreaBean> list = new ArrayList<HouseAreaBean>();
			for (String s : chk) {
				try {
					//int id = Integer.parseInt(s);
					HouseAreaBean sd = new HouseAreaBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}

}
