package com.bonus.rm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.rm.service.PutInStorageTaskService;
import com.bonus.sys.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class PutInStorageTaskControllerTest {

	@Autowired
	private  PutInStorageTaskService service;
	@Test
	public void testfindByPage() {
		PutInStorageTaskBean o = new PutInStorageTaskBean();
		Page<PutInStorageTaskBean> page = new Page<>();
		String companyId = "11";
		o.setOrgId(companyId);
		o.setStartTime("2022-05-20");
		o.setEndTime("2023-10-20");
		Page<PutInStorageTaskBean> station = service.findByPage(o, page);
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("list", station);
		
	}
	
	@Test
	public void testfindBackCode() {
		PutInStorageTaskBean o = new PutInStorageTaskBean();
		Page<PutInStorageTaskBean> page = new Page<>();
		String companyId = "11";
		o.setOrgId(companyId);
		o.setStartTime("2022-05-20");
		o.setEndTime("2023-10-20");
		Page<PutInStorageTaskBean> list = service.findByPageTwo(o, page);
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("list", list);
		
	}
	
	
	@Test
	public void updatePutPerson() {
		PutInStorageTaskBean o = new PutInStorageTaskBean();
		String companyId = "11";
		o.setOrgId(companyId);
		o.setPutPersonId("1");
		service.updatePutPerson(o);
		
	}
	
	@Test
	public void deleteReturn() {
		PutInStorageTaskBean o = new PutInStorageTaskBean();
		String companyId = "11";
		o.setOrgId(companyId);
		o.setId("599");
		service.deleteReturn(o);
		
	}

}
