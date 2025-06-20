package com.bonus.lease.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.lease.beans.ReceiveDetailsBean;
import com.bonus.lease.service.ReceiveDetailsService;
import com.bonus.sys.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class ReceiveDetailsControllerTest {

	@Autowired
	private ReceiveDetailsService service;
	@Test
	public void testfindByPage() {
		ReceiveDetailsBean o = new ReceiveDetailsBean();
		Page<ReceiveDetailsBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				Page<ReceiveDetailsBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}

}
