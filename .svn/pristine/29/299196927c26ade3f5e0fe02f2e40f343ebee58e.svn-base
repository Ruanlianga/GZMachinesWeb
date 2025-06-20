package com.bonus.settlement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.settlement.beans.SettlementBean;
import com.bonus.settlement.dao.SettlementDao;
import com.bonus.sys.BaseServiceImp;

@Service("settlement")
public class SettlementServiceImp extends BaseServiceImp<SettlementBean> implements SettlementService {

	@Autowired
	SettlementDao dao;

	@Override
	public String findCode(SettlementBean o) {
		// TODO Auto-generated method stub
		return dao.findCode(o);
	}
	
	
}
