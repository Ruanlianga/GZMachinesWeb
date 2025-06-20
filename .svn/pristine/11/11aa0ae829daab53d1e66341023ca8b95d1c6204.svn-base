package com.bonus.lease.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.service.OutStorageService;
import com.bonus.sys.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class OutStorageControllerTest {

	@Autowired
	private OutStorageService service;
	@Test
	public void testfindByPage() {
		OutStorageBean o = new OutStorageBean();
		Page<OutStorageBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				o.setStartTime("2022-05-20");
				o.setEndTime("2022-10-20");
				Page<OutStorageBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}

	@Test
	public void testfindApproval() {
		OutStorageBean o = new OutStorageBean();
		Page<OutStorageBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				o.setStartTime("2022-05-20");
				o.setEndTime("2022-10-20");
				Page<OutStorageBean> station = service.findApproval(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
	
	@Test
	public void testBackData() {
		OutStorageBean o = new OutStorageBean();
		o.setcTaskId("1");
		o.setMaModelId("1");
		 service.isBackApproval(o);
	}
	
	
}
