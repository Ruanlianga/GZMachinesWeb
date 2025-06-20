package com.bonus.newInput.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.beans.NewInputBean;
import com.bonus.newInput.dao.InputDetailsDao;
import com.bonus.newInput.dao.NewInputDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class InputDetailsServiceImpTest {

	
	@Autowired
	private InputDetailsDao dao;
	

	@Autowired
	private NewInputDao nDao;
	
	@Autowired
	private MachineDao mDao;

	@Autowired
	private MachineTypeDao mtDao;

	@Test
	public void devIsExist() {
		InputDetailsBean bean = new InputDetailsBean();
		bean.setTaskId("4276");
		bean.setMaModelId("2377");
		
		List<InputDetailsBean> list = dao.find(bean);
		if(list!=null && list.size()>0){
			System.err.println("已存在！");
		}
	}
	
	@Test
	public void updateStasus() {
		NewInputBean bean = new NewInputBean();
		bean.setTaskId("4276");
		bean.setTaskStatus("2");
		nDao.updateStatus(bean);
	}
	
	@Test
	public void addMachineDetails() {
		
		InputDetailsBean idbean = new InputDetailsBean();
		idbean.setTaskId("4199");
		String nums = "3";
		int num = Integer.parseInt(nums);
		String typeId = "2400";
		MachineBean bean = new MachineBean();
		bean.setType(typeId);
		bean.setBatchStatus("1");//待通知人员
		bean.setVerderId("2");
		bean.setBuyTime(DateTimeHelper.getNowTime());
	
			for (int i = 0; i < num; i++) {
				
	            String deviceType = "1";
				
				bean.setDeviceType(deviceType);
				
				mDao.insert(bean);
				idbean.setMaId(bean.getId());
				dao.insertMaNewInput(idbean);
			}
		
	}
	

	

}
