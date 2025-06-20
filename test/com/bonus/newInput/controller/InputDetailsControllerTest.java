package com.bonus.newInput.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.service.InputDetailsService;
import com.bonus.sys.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class InputDetailsControllerTest {

	
	@Autowired
	private InputDetailsService service;
	
	@Test
	public void testfindByPage() {
		InputDetailsBean o = new InputDetailsBean();
		Page<InputDetailsBean> page = new Page<>();
			try {
				String companyId = "11";
				page.setCompanyId(companyId);
				Page<InputDetailsBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
}
