package com.bonus.ma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.LibNumsInventoryBean;
import com.bonus.ma.dao.LibNumsInventoryDao;
import com.bonus.sys.BaseServiceImp;

@Service("inventory")
public class LibNumsInventoryServiceImp extends BaseServiceImp<LibNumsInventoryBean> implements LibNumsInventoryService{

	@Autowired LibNumsInventoryDao dao;

	@Override
	public void addLibs(LibNumsInventoryBean o) {
		dao.addLibs(o);
		
	}

	@Override
	public String findByModelId(LibNumsInventoryBean o) {
		return dao.findByModelId(o);
	}
	
}
