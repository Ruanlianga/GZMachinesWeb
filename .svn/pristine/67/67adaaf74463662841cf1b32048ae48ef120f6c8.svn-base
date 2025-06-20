package com.bonus.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.data.beans.FenceBean;
import com.bonus.data.dao.FenceDao;
import com.bonus.sys.BaseServiceImp;

@Service("Fence")
public class FenceServiceImp extends BaseServiceImp<FenceBean> implements FenceService{

	@Autowired
	private FenceDao dao;
	
	@Override
	public List<FenceBean> findFence(FenceBean bean) {
		// TODO Auto-generated method stub
		return dao.findFence(bean);
	}

	@Override
	public List<FenceBean> findNoPage(FenceBean bean) {
		// TODO Auto-generated method stub
		return dao.findNoPage(bean);
	}
	
	

}

