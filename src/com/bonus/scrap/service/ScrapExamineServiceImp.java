package com.bonus.scrap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.scrap.beans.ScrapExamineBean;
import com.bonus.scrap.dao.ScrapExamineDao;
import com.bonus.scrap.dao.ScrapTaskRecordDao;
import com.bonus.sys.BaseServiceImp;

@Service("scrapExamine")
public class ScrapExamineServiceImp extends BaseServiceImp<ScrapExamineBean> implements ScrapExamineService {
		
		@Autowired
		private ScrapExamineDao dao;

		@Override
		public List<ScrapExamineBean> export(ScrapExamineBean o) {
			// TODO Auto-generated method stub
			return dao.export(o);
		}

		
		
		
}