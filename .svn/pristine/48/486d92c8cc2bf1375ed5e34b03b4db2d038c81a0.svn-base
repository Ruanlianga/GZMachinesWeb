package com.bonus.ma.service;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.CheckQrCodeBean;
import com.bonus.ma.dao.CheckQrCodeDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.ZNode;

@Service("checkQrCode")
public class CheckQrCodeServiceImp extends BaseServiceImp<CheckQrCodeBean> implements CheckQrCodeService{

	@Autowired CheckQrCodeDao dao;
	
	@Override
	public int insertBean(CheckQrCodeBean o) {
		return dao.insertBean(o);
	}

	@Override
	public int findCount(CheckQrCodeBean o) {
		// TODO Auto-generated method stub
		return dao.findCount(o);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<CheckQrCodeBean> list = new ArrayList<CheckQrCodeBean>();
			for (String s : chk) {
				try {
					//int id = Integer.parseInt(s);
					CheckQrCodeBean sd = new CheckQrCodeBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}

}
