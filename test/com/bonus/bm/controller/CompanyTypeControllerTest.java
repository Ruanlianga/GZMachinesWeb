package com.bonus.bm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.bm.beans.CompanyTypeBean;
import com.bonus.bm.service.CompanyTypeService;
import com.bonus.sys.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class CompanyTypeControllerTest {

	@Autowired
	private CompanyTypeService service;
	
	@Test
	public void testfindByPage() {
		CompanyTypeBean o = new CompanyTypeBean();
		Page<CompanyTypeBean> page = new Page<>();
			try {
				Page<CompanyTypeBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
}
