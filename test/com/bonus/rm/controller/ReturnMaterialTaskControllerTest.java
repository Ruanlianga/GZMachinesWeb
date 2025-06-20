package com.bonus.rm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.rm.beans.ReturnMaterialTaskBean;
import com.bonus.rm.service.ReturnMaterialTaskService;
import com.bonus.sys.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class ReturnMaterialTaskControllerTest {

	@Autowired
	private ReturnMaterialTaskService service;
	@Test
	public void testfindByPage() {
		ReturnMaterialTaskBean o = new ReturnMaterialTaskBean();
		Page<ReturnMaterialTaskBean> page = new Page<>();
		String companyId = "11";
		o.setOrgId(companyId);
		o.setStartTime("2022-05-20");
		o.setEndTime("2022-10-20");
		Page<ReturnMaterialTaskBean> station = service.findByPage(o, page);
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("list", station);
		
	}

}
