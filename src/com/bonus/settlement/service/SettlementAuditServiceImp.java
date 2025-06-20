package com.bonus.settlement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.settlement.beans.SettlementAuditBean;
import com.bonus.settlement.dao.SettlementAuditDao;
import com.bonus.sys.BaseServiceImp;


@Service("settlementAudit")
public class SettlementAuditServiceImp extends BaseServiceImp<SettlementAuditBean> implements SettlementAuditService {
	
	@Autowired
	private SettlementAuditDao dao;
	
	@Override
	public void settlementAudit(SettlementAuditBean o) {
		String auditTime = DateTimeHelper.getNowTime();
		o.setSettlementDate(auditTime);
		dao.settlementAudit(o);
	}


	
}
