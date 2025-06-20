package com.bonus.scrap.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.scrap.beans.ScrapAuditBean;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class ScrapAuditServiceImpTest {
	@Autowired
	private ScrapAuditService service;
	
	@Test
	public void approvalApply() {
		ScrapAuditBean bean= new ScrapAuditBean();
		bean.setId(1);
		service.findScrapApplyById(bean);
	}
	
	@Test
	public void findScrapFileById() {
		ScrapAuditBean bean= new ScrapAuditBean();
		bean.setId(1);
		service.findScrapFileById(bean);
	}
	

}
