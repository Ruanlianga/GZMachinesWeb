package com.bonus.ma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.MaStockBean;
import com.bonus.ma.dao.MaStockDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;

@Service
public class MaStockServiceImpl extends BaseServiceImp<MaStockBean> implements MaStockService {

	@Autowired
	private MaStockDao dao;

	@Override
	public List<MaStockBean> export(MaStockBean o) {
		// TODO Auto-generated method stub
		return dao.export(o);
	}

	@Override
	public Page<MaStockBean> findStockDetails(MaStockBean o, Page<MaStockBean> page) {
		page.setResults(dao.findStockDetails(o,page));
		return page;
	}

	@Override
	public Page<MaStockBean> findStockLoss(MaStockBean o, Page<MaStockBean> page) {
		page.setResults(dao.findStockLoss(o,page));
		return page;
	}

	@Override
	public List<MaStockBean> findByPage(MaStockBean o) {
		return dao.findByPage(o);
	}

}
