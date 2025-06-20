package com.bonus.ma.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.ma.dao.MachineRfidDao;
import com.bonus.pis.dao.PutInStorageDao;
import com.bonus.wf.dao.TaskRecordDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class MachineRfidServiceImpTest {


	@Autowired
	private MachineRfidDao dao;
	
	@Autowired
	TaskRecordDao trDao;
	
	@Autowired
	PutInStorageDao pisDao;
	
	@Test
	public void testGetRfidNMachineStatus() {
		MachineRfidBean o = new MachineRfidBean();
		o.setId("1");
		dao.getRfidNMachineStatus(o);
	}
	
	@Test
	public void testGetMachineDetails() {
		MachineRfidBean o = new MachineRfidBean();
		o.setId("1");
		dao.getMachineDetails(o);
	}
	
	@Test
	public void testGetRfidNMachineNum() {
		MachineRfidBean o = new MachineRfidBean();
		o.setId("1");
		dao.getRfidNMachineNum(o);
	}

}
