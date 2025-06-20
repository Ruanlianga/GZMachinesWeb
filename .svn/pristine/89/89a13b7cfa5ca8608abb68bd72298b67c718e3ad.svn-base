package com.bonus.pis.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.pis.beans.PutInStorageBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class PutInStorageServiceImpTest {

	@Autowired
	PutInStorageService service;
	
	@Test
	public void testMachinePutInStorageList() {
		PutInStorageBean bean = new PutInStorageBean();
		
		bean.setIsSure("0");
		service.machinePutInStorageList(bean);
	}

	@Test
	public void testMachinePutInStorageDetails() {
		PutInStorageBean bean = new PutInStorageBean();
		
		bean.setId("75");
		service.machinePutInStorageDetailsFinish(bean);
	}

	@Test
	public void testMachinePutInStorageDetailsFinish() {
		PutInStorageBean bean = new PutInStorageBean();
		
		bean.setId("75");
		service.machinePutInStorageList(bean);
	}

}
