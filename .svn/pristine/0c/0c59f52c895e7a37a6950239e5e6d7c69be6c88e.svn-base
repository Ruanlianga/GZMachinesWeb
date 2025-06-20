package com.bonus.lease.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.lease.beans.MachineReceiveBean;
import com.bonus.lease.service.MachineReceiveService;
import com.bonus.sys.Page;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class MachineReceiveControllerTest {
	
	@Autowired
	private MachineReceiveService service;

	
	@Test
	public void testfindByPage() {
		MachineReceiveBean o = new MachineReceiveBean();
		Page<MachineReceiveBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				Page<MachineReceiveBean> station = service.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
	
	
	@Test
	public void testFindSheet() {
		try {
			MachineReceiveBean o = new MachineReceiveBean();
			o.setStartTime("2022-05-20");
			o.setEndTime("2022-06-20");
			List<MachineReceiveBean> result = service.findLeaseSheet(o);
		} catch (Exception e) {
		}
	}

}
