package com.bonus.lease.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.lease.beans.AgreementBean;
import com.bonus.lease.service.AgreementService;
import com.bonus.sys.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class AgreementControllerTest {

	
	@Autowired
	private AgreementService service;
	
	@Test
	public void testfindByPage() {
		AgreementBean o = new AgreementBean();
		Page<AgreementBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				Page<AgreementBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}

}
