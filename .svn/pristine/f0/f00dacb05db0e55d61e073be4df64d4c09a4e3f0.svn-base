package com.bonus.newInput.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.newInput.service.NewInputQrcodeService;
import com.bonus.sys.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class NewInputQrcodeControllerTest {


	@Autowired
	private NewInputQrcodeService service;

	@Test
	public void testfindByPage() {
		NewInputQrcodeBean o = new NewInputQrcodeBean();
		Page<NewInputQrcodeBean> page = new Page<>();
			try {
				String companyId = "11";
				page.setCompanyId(companyId);
				Page<NewInputQrcodeBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}

}
