package com.bonus.settlement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.settlement.beans.SettlementDetailsBean;
import com.bonus.settlement.dao.SettlementDetailsDao;
import com.bonus.sys.BaseServiceImp;

@Service("settleDetails")
public class SettlementDetailsServiceImp extends BaseServiceImp<SettlementDetailsBean>
		implements SettlementDetailsService {

	@Autowired
	private SettlementDetailsDao dao;

	@Override
	public List<SettlementDetailsBean> getLeaseList(SettlementDetailsBean o) {
		return dao.getLeaseList(o);
	}

	@Override
	public List<SettlementDetailsBean> getBackList(SettlementDetailsBean o) {
		return dao.getBackList(o);
	}

	@Override
	public SettlementDetailsBean findTitle(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.findTitle(o);
	}

	@Override
	public SettlementDetailsBean findSettlementInfo(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.findSettlementInfo(o);
	}

	@Override
	public List<SettlementDetailsBean> findSettlementApply(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.findSettlementApply(o);
	}

	@Override
	public void editLeasePrice(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		dao.editLeasePrice(o);
	}

	@Override
	public List<SettlementDetailsBean> getSettlementApply(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.getSettlementApply(o);
	}

	@Override
	public void editStopDays(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		dao.editStopDays(o);
	}

	@Override
	public List<SettlementDetailsBean> getLoseList(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.getLoseList(o);
	}

	@Override
	public List<SettlementDetailsBean> getRepairList(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.getRepairList(o);
	}

	@Override
	public List<SettlementDetailsBean> getScrapList(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.getScrapList(o);
	}

	@Override
	public List<SettlementDetailsBean> selectBatch(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.selectBatch(o);
	}

	@Override
	public void editDeduction(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		dao.editDeduction(o);
	}

	@Override
	public List<SettlementDetailsBean> getDeductionList(SettlementDetailsBean o) {
		// TODO Auto-generated method stub
		return dao.getDeductionList(o);
	}


}
