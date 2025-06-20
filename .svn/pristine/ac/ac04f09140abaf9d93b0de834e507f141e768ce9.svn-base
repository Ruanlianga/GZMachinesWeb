package com.bonus.ma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.MaLeaseBean;
import com.bonus.ma.dao.MaLeaseDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;

@Service("MaLeaseService")
public class MaLeaseServiceImp extends BaseServiceImp<MaLeaseBean> implements MaLeaseService{
	@Autowired 
	private MaLeaseDao dao;

	@Override
	public Page<MaLeaseBean> findbackCode(MaLeaseBean o, Page<MaLeaseBean> page) {
		// TODO Auto-generated method stub
		page.setResults(dao.findbackCode(o, page));
		return page;
	}

	@Override
	public Page<MaLeaseBean> findleaseCode(MaLeaseBean o, Page<MaLeaseBean> page) {
		page.setResults(dao.findleaseCode(o, page));
		return page;
	}

	@Override
	public Page<MaLeaseBean> finduseCode(MaLeaseBean o, Page<MaLeaseBean> page) {
		page.setResults(dao.finduseCode(o, page));
		return page;
	}

}
