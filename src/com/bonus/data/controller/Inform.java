package com.bonus.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.bonus.core.DateTimeHelper;
import com.bonus.index.service.IndexHomeService;
import com.bonus.ma.beans.MaStockBean;
import com.bonus.ma.service.MaStockService;


@Component
public class Inform {

	@Autowired
	private IndexHomeService hService;


	//@Scheduled(cron = "0 */5 * * * ? ") // 间隔5分钟执行
	@Scheduled(cron = "0 0 1 * * ? ") // 凌晨1点执行
	public void taskCycle() {
		System.out.println("===springMVC定时器启动====");

		 
		try {
			// 生成首页定时数据
			hService.insertIndexInfo();

	

		} catch (Exception e) {
		
			e.printStackTrace();
		}

		System.out.println("===springMVC定时器结束====");
	}


}