package com.bonus.app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repair.service.RepairDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class AppRepairControllerTest {
	
	@Autowired
	private RepairDetailsService service;

	@Test
	public void testGetRepairMan() {
		RepairDetailsBean o = new RepairDetailsBean();
		//service.getRepairMan(o);
	}

}
