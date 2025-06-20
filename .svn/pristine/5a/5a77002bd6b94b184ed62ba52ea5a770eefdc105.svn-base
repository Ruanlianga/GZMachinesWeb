package com.bonus.repairCheck.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repairCheck.beans.RepairCheckDetailsBean;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class RepairCheckDetailsServiceImpTest {
	@Autowired
	RepairCheckDetailsService service;
	
	@Test
	public void testGetCheckIndexList() {
		RepairCheckDetailsBean bean = new RepairCheckDetailsBean();
		bean.setKeyWord("");
		bean.setNotCheck("1");
		service.getCheckIndexList(bean);
	}

	@Test
	public void testSubmitNumCheck() {
		RepairCheckDetailsBean bean = new RepairCheckDetailsBean();
	
		bean.setModelId("2402");
		bean.setIsCount("1");
		bean.setIsCount("1");
		bean.setNewRepairQualifiedNum(5);
		bean.setNewRepairUnqualifiedNum(2);
		bean.setNewScrapQualifiedNum(3);
		bean.setNewScrapUnqualifiedNum(2);
		bean.setUserId("262");
		service.submitNumCheck(bean);
	}

	@Test
	public void testSubmitCodeCheck() {
		RepairCheckDetailsBean bean = new RepairCheckDetailsBean();
		bean.setKeyWord("");
		bean.setModelId("2402");
		service.getRepairCodeList(bean);
	}

	@Test
	public void testSubmitCodeRepair() throws Exception {
		RepairCheckDetailsBean bean = new RepairCheckDetailsBean();
		
		RepairCheckDetailsBean[] arr = new RepairCheckDetailsBean[2];
		for (int i=0 ; i < arr.length; i++){
			RepairCheckDetailsBean rb =  new RepairCheckDetailsBean();
			if(i ==0){
				rb.setWirId("15261");
				rb.setModelId("386");
				rb.setMmId("226");	
				rb.setDeviceCode("ym220");	
				rb.setIsCount("0");
				rb.setPassCheck("1");
				rb.setTaskId("15395");
				rb.setReStatus("5");
			}
			if(i ==1){
				rb.setWirId("16838");
				rb.setModelId("386");
				rb.setMmId("226");	
				rb.setDeviceCode("ym221");	
				rb.setIsCount("0");
				rb.setPassCheck("1");
				rb.setTaskId("15395");
				rb.setReStatus("5");
			}
			arr[i]=rb;
			
		}
		bean.setArr(arr);
		bean.setModelId("386");
		bean.setIsCount("0");

		service.submitCodeCheck(bean);
	}

}
