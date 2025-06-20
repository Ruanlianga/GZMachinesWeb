package com.bonus.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.ma.beans.MachineRfidInfoBean;
import com.bonus.ma.service.MachineRfidInfoService;
import com.bonus.ma.service.MachineRfidService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/app/rfidInfo/")
public class AppRfidInfoController extends BaseController<Object> {

	
	@Autowired
	private MachineRfidInfoService mService;
	

    
	/**
	 * 绑定机具
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "submitRfidEnter", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes submitRfidBind(MachineRfidInfoBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			  logger.info("进入后台epcCode:"+o.getEpcCode()+"deviceCode:"+o.getDeviceCode()+"maId:"+o.getMaId());
			  List<MachineRfidInfoBean> epcList = mService.findMachineByEpcCode(o);
			  if(epcList!=null && epcList.size()>0){
				    ar.setRes(0);
		            ar.setFailMsg("fail：epc编号已绑定："+o.getEpcCode());
			  } else{
				  
	
		  			int res = mService.submitRfidEnter(o);
					 
		  			if(res!= 0){
			            ar.setRes(1);
			            ar.setResMsg("success");
			            ar.setSucceed("is null");
		  			}else{
			            ar.setRes(0);
			            ar.setResMsg("error");
			            ar.setSucceed("is null");
		  			}  
				  }
		
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 查询绑定机具
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "findListByEpcCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findListByEpcCode(MachineRfidInfoBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			logger.info("进入后台epcCode:"+o.getEpcCode());
			List<MachineRfidInfoBean> epcList = mService.findListByEpcCode(o);
			if(epcList!=null && epcList.size()>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed(epcList);
				
			}else{
				ar.setRes(0);
				ar.setFailMsg("fail");
				ar.setSucceed("is null");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
}
