package com.bonus.settlement.dao;

import com.bonus.core.BonusBatis;
import com.bonus.settlement.beans.SettlementBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface SettlementDao extends BaseDao<SettlementBean> {

	String findCode(SettlementBean o);

	

}
