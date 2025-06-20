package com.bonus.repair.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.repair.beans.RepairDetailsBean;
/**
 * @author xj
 *
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class RepairDetailsServiceImpTest {
	
	@Autowired
	RepairDetailsService service;

	@Test
	public void testGetRepairIndexList() throws Exception {
		RepairDetailsBean bean = new RepairDetailsBean();
	
		bean.setIsFinsish("0");
		service.getRepairIndexList(bean);
	}
	

//	@Test
	public void testSubmitNumRepair() throws Exception {
		RepairDetailsBean bean = new RepairDetailsBean();
	
	
		bean.setModelId("537");
		bean.setRepairPart("cs");
		bean.setIsCount("1");
		bean.setThisRepairNum(7);
		service.submitNumRepair(bean);
	}

//	@Test
	public void testSubmitCodeRepair() throws Exception {
		RepairDetailsBean bean = new RepairDetailsBean();
		
		RepairDetailsBean[] arr = new RepairDetailsBean[2];
		for (int i=0 ; i < arr.length; i++){
			RepairDetailsBean rb =  new RepairDetailsBean();
			if(i ==0){
				rb.setId("912");
				rb.setModelId("2402");
				rb.setDeviceCode("jm250");	
				rb.setThisRepairNum(1);
			}
			if(i ==1){
				rb.setId("913");
				rb.setModelId("2402");
				rb.setDeviceCode("jm117");	
				rb.setThisRepairNum(1);
			}
			arr[i]=rb;
			
		}
		bean.setArr(arr);
		bean.setModelId("2402");
		bean.setRepairPart("cs");
		bean.setIsCount("0");

		service.submitCodeRepair(bean);
	}
	//@Test
	public void testSplitCodeRepair() throws Exception {
		RepairDetailsBean bean = new RepairDetailsBean();
		
		
		bean.setModelId("2402");
		

		service.splitCodeRepair(bean);
	}
	
	
}
