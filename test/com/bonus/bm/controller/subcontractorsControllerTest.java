package com.bonus.bm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.bm.beans.subcontractorsBean;
import com.bonus.bm.service.subcontractorsService;
import com.bonus.sys.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class subcontractorsControllerTest {

	@Autowired
	private subcontractorsService service;
	@Test
	public void testfindByPage() {
		subcontractorsBean o = new subcontractorsBean();
		Page<subcontractorsBean> page = new Page<>();
			try {
				Page<subcontractorsBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
}
