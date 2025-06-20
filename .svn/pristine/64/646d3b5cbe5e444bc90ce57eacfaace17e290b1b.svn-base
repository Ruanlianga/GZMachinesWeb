package com.bonus.bm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.ScrapReasonBean;
import com.bonus.bm.dao.ScrapReasonDao;
import com.bonus.sys.BaseServiceImp;

@Service("scrapReason")
public class ScrapReasonServiceImp extends BaseServiceImp<ScrapReasonBean> implements ScrapReasonService{

	@Autowired ScrapReasonDao dao;
	
	@Override
	public int insertBean(ScrapReasonBean o) {
		return dao.insertBean(o);
	}

	@Override
	public List<ScrapReasonBean> findScrapReason(ScrapReasonBean o) {
		return dao.findScrapReason(o);
	}

}

