package com.bonus.newInput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.newInput.beans.NewReportBean;
import com.bonus.newInput.dao.NewInputDao;
import com.bonus.newInput.dao.NewReportDao;
import com.bonus.sys.BaseServiceImp;

@Service("newReport")
public class NewReportServiceImp extends BaseServiceImp<NewReportBean> implements NewReportService{
	
	@Autowired 
	private NewReportDao dao;
	 
}
