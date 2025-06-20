package com.bonus.app.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.repairCheck.beans.RepairCheckDetailsBean;
import com.bonus.repairCheck.service.RepairCheckDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class AppRepairCheckControllerTest {

	@Autowired
	private RepairCheckDetailsService service;
	
	@Test
	public void testGetCheckIndexList() {
		RepairCheckDetailsBean o = new RepairCheckDetailsBean();
		o.setNotCheck("0");
		service.getCheckIndexList(o);
	}
	
	@Test
	public void testGetCheckNumIndexList() {
		RepairCheckDetailsBean o = new RepairCheckDetailsBean();
		o.setNotCheck("0");
		service.getCheckedNumList(o);
	}

}
