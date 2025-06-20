package com.bonus.settlement.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.settlement.beans.SettlementDetailsBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface SettlementDetailsDao extends BaseDao<SettlementDetailsBean>{


	List<SettlementDetailsBean> getLeaseList(SettlementDetailsBean o);

	List<SettlementDetailsBean> getBackList(SettlementDetailsBean o);

	SettlementDetailsBean findTitle(SettlementDetailsBean o);

	SettlementDetailsBean findSettlementInfo(SettlementDetailsBean o);

	List<SettlementDetailsBean> findSettlementApply(SettlementDetailsBean o);

	void editLeasePrice(SettlementDetailsBean o);

	List<SettlementDetailsBean> getSettlementApply(SettlementDetailsBean o);

	void editStopDays(SettlementDetailsBean o);

	List<SettlementDetailsBean> getLoseList(SettlementDetailsBean o);

	List<SettlementDetailsBean> getRepairList(SettlementDetailsBean o);

	List<SettlementDetailsBean> getScrapList(SettlementDetailsBean o);

	List<SettlementDetailsBean> selectBatch(SettlementDetailsBean o);

	void editDeduction(SettlementDetailsBean o);

	List<SettlementDetailsBean> getDeductionList(SettlementDetailsBean o);

	
}
