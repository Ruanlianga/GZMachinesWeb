package com.bonus.newInput.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.newInput.beans.NewReportBean;
import com.bonus.newInput.service.NewReportService;
import com.bonus.sys.Page;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class NewReportControllerTest {

	
	@Autowired
	private NewReportService service;
	@Test
	public void testfindByPage() {
		NewReportBean o = new NewReportBean();
		Page<NewReportBean> page = new Page<>();
			try {
				String companyId = "11";
				page.setCompanyId(companyId);
				Page<NewReportBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
}
