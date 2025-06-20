package com.bonus.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.data.beans.WarningTipBean;
import com.bonus.data.dao.WarningTipDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;

@Service("WarningTip")
public class WarningTipServiceImp extends BaseServiceImp<WarningTipBean> implements WarningTipService{
	@Autowired
	private WarningTipDao dao;
 
	@Override
	public Page<WarningTipBean> findTip(WarningTipBean bean, Page<WarningTipBean> page) {
		return dao.findTip(page,bean );
	}

	@Override
	public List<WarningTipBean> findByCode(WarningTipBean bean) {
		List<WarningTipBean> list = dao.findByCode(bean);
		return list;
	}

	@Override
	public List<WarningTipBean> findWranTip(WarningTipBean o) {
		List<WarningTipBean> list = dao.findWranTip(o);
		return list;
	}
 
	

}

