package com.bonus.bm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bonus.bm.beans.ProjectManageBean;
import com.bonus.bm.service.ProjectManageService;
import com.bonus.sys.Page;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class ProjectManageControllerTest {

	@Autowired
	private ProjectManageService pmService;
	
	@Test
	public void testfindByPage() {
		ProjectManageBean o = new ProjectManageBean();
		Page<ProjectManageBean> page = new Page<>();
			try {
				String companyId = "11";
				o.setCompanyId(companyId);
				Page<ProjectManageBean> station = pmService.findByPage(o, page);
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("list", station);
			} catch (Exception e) {
				
			}
	}
}
