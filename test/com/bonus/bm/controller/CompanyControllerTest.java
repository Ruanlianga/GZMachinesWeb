package com.bonus.bm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.bm.beans.CompanyBean;
import com.bonus.bm.service.CompanyService;
import com.bonus.sys.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class CompanyControllerTest {
	
	@Autowired
	private CompanyService comService;
	
	@Test
	public void testfindByPage() {
		CompanyBean o = new CompanyBean();
		Page<CompanyBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				Page<CompanyBean> station = comService.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}


}
