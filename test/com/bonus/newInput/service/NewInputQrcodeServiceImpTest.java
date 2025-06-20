package com.bonus.newInput.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.newInput.dao.NewInputQrcodeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class NewInputQrcodeServiceImpTest {

	@Autowired
	private NewInputQrcodeDao dao;
	
	@Test
	public void testConfirmStorage() {
		NewInputQrcodeBean o = new NewInputQrcodeBean();
		o.setOperationTime(DateTimeHelper.getNowTime());
		o.setState("5");
	
		o.setNums("1");
		dao.updateAlInputNum(o);
		o.setTaskId("1");
		dao.insertInfoRecord(o);
		dao.updateNewTask(o);
	}
	
	

}
