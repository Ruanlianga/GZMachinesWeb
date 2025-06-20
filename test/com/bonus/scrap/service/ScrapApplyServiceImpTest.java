package com.bonus.scrap.service;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.scrap.beans.ScrapApplyBean;
import com.bonus.scrap.beans.ScrapApplyFileBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class ScrapApplyServiceImpTest {
	@Autowired
	ScrapApplyService service;
	

	@Test
	public void testFindFileList() {
		// TODO Auto-generated method stub
		ScrapApplyFileBean bean=new ScrapApplyFileBean();
		bean.setId("1");
		service.findFileList(bean);
	}
	
	@Test
	public void testFindInventoryScrapDetailsById() {
		// TODO Auto-generated method stub
		ScrapApplyBean bean=new ScrapApplyBean();
		bean.setId(1);
		service.findInventoryScrapDetailsById(bean);
	}
}
