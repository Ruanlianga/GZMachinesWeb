package com.bonus.lease.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.dao.OutStorageDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class OutStorageServiceImpTest {

	
	@Autowired
	OutStorageDao dao;
	
	@Test
	public void isExamine() {
		
		OutStorageBean  bean = new OutStorageBean();
		int userId = 1;
		bean.setExamineUser(userId+"");
		bean.setTaskId("4050");
		bean.setMaModelId("10");
		dao.isExamine(bean);
	
	}

	//@Test
	public void rejectExamine() {
		
	     	int result = 0;
			OutStorageBean  obean = new OutStorageBean();
			//1修改审核状态
			int userId = 1;
			obean.setExamineUser(userId+"");
			obean.setIsExamine("2");
			obean.setTaskId("4046");
			obean.setMaModelId("2447");
			obean.setAuditRemark("cs");
			result = dao.updateExaStatus(obean);
			//2删除发布领料任务
			OutStorageBean bean = dao.findOutTaskInfo(obean);
			if(bean != null){
				result = dao.delOutTask(bean);
				result = dao.delOutDetail(bean);
			}
	
	}
}
