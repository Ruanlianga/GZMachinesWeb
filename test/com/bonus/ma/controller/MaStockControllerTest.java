package com.bonus.ma.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.ma.beans.MaStockBean;
import com.bonus.ma.service.MaStockService;
import com.bonus.sys.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class MaStockControllerTest {

	@Autowired
	private MaStockService service;
	@Test
	public void testfindByPage() {
		MaStockBean o = new MaStockBean();
		Page<MaStockBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				Page<MaStockBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
}
