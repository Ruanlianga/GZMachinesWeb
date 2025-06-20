package com.bonus.scrap.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repair.service.RepairDetailsService;
import com.bonus.scrap.beans.ScrapDetailsBean;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class ScrapDetailsServiceImpTest {
	
	@Autowired
	ScrapDetailsService service;

	@Test
	public void testGetScrapIndexList() {
		ScrapDetailsBean bean = new ScrapDetailsBean();
		
		bean.setIsFinish("0");
		service.getScrapIndexList(bean);
	}

	@Test
	public void testFindScrapCodeList() {
		ScrapDetailsBean bean = new ScrapDetailsBean();
		
		bean.setModelId("2400");
		bean.setIsCount("0");
		service.findScrapCodeList(bean);
	}

	@Test
	public void testSubmitCodeScrap() {
		ScrapDetailsBean bean = new ScrapDetailsBean();
		
		ScrapDetailsBean[] arr = new ScrapDetailsBean[2];
		for (int i=0 ; i < arr.length; i++){
			ScrapDetailsBean rb =  new ScrapDetailsBean();
			if(i ==0){
				rb.setId("912");
				rb.setModelId("2402");
				rb.setDeviceCode("jm250");	
				rb.setThisScrapNum(1);
			}
			if(i ==1){
				rb.setId("913");
				rb.setModelId("2402");
				rb.setDeviceCode("jm117");	
				rb.setThisScrapNum(1);
			}
			arr[i]= rb;
		}
		bean.setArr(arr);
		bean.setModelId("2402");
		bean.setIsCount("0");
		bean.setScrapReson("");
		
		service.submitCodeScrap(bean);
	}

	@Test
	public void testSubmitNumScrap() {
		ScrapDetailsBean bean = new ScrapDetailsBean();
		
		bean.setModelId("537");
		bean.setScrapReson("");
		bean.setIsCount("1");
		bean.setThisScrapNum(7);
		service.submitNumScrap(bean);
	}

	@Test
	public void testFindScrapCodeListFinish() {
		ScrapDetailsBean bean = new ScrapDetailsBean();
		
		bean.setModelId("2400");
		bean.setIsCount("0");
		service.findScrapCodeListFinish(bean);
	}

}
