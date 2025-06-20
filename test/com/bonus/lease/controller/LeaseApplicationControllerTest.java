package com.bonus.lease.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.lease.beans.LeaseApplicationBean;
import com.bonus.lease.service.LeaseApplicationService;
import com.bonus.sys.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class LeaseApplicationControllerTest {

	
	@Autowired
	private LeaseApplicationService service;
	@Test
	public void testfindByPage() {
		LeaseApplicationBean o = new LeaseApplicationBean();
		Page<LeaseApplicationBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				Page<LeaseApplicationBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
}
