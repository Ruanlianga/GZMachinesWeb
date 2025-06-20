package com.bonus.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bonus.data.beans.GpsDataBean;
import com.bonus.data.service.GpsDataService;
import com.bonus.index.beans.PositionBean;
import com.bonus.index.service.PositionService;
import com.bonus.sys.BaseController;

@Controller
@RequestMapping("/backstage/gps/")
public class GpsDataController extends BaseController<GpsDataBean>{

	@Autowired private GpsDataService service;
	
	@Autowired private PositionService posService;
	//		@Scheduled(cron = "0 0 13 * * ?")
//		@Scheduled(cron = "0 */2 * * * ? ") 
//	@Scheduled(cron = "0 0 23 * * ? ")  //30分钟执行一次
	@RequestMapping("getAccessToken")
	public void getAccessToken(){
		GpsDataBean bean = new GpsDataBean();
		try {
			logger.info("进入定时器");
 			String token = service.getAccessToken(bean,"");
 			logger.info("获取gps的token="+token);
			if(token != "" || !"".equals(token)){
				List<PositionBean> list = posService.findAll();
				String imeis;
				if(list.size() > 0){
					for (int i = 0; i < list.size(); i++) {
						imeis = list.get(i).getCode();
						logger.info("获取gps的imeis="+imeis);
						if(!"".equals(imeis) || imeis != null){
							service.getPosInfo(bean,token,imeis);
							
						}
					}
				}
			}else{
				
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
	}
	
	
}