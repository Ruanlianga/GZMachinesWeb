package com.bonus.settlement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.settlement.beans.SettlementMoneyBean;
import com.bonus.settlement.dao.SettlementMoneyDao;
import com.bonus.sys.BaseServiceImp;

@Service("settleMoney")
public class SettlementMoneyServiceImp extends BaseServiceImp<SettlementMoneyBean>
		implements SettlementMoneyService {

	@Autowired
	private SettlementMoneyDao dao;



}
