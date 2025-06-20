package com.bonus.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.ma.service.MachineRfidService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;

@Controller
@RequestMapping("/backstage/app/rfid/")
public class AppMachineRfidController extends BaseController<Object> {

	
	@Autowired
	private MachineRfidService mService;
	

    
	/**
	 * 绑定机具
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "submitRfidBind", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes submitRfidBind(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			  logger.info("进入后台epcCode:"+o.getEpcCode()+"deviceCode:"+o.getDeviceCode()+"maId:"+o.getMaId());
			  List<MachineRfidBean> epcList = mService.findMachineByEpcCode(o);
			  if(epcList!=null && epcList.size()>0){
				    ar.setRes(0);
		            ar.setFailMsg("fail：epc编号已绑定："+o.getEpcCode());
			  } else{
				  	List<MachineRfidBean> list = mService.findMachineByCode(o);
				  
					  if(list.size() == 1){
				  			String maId = list.get(0).getMaId();
				  			o.setMaId(maId);
				  			int res = mService.submitRfidBind(o);
							 
				  			if(res!= 0){
					            ar.setRes(1);
					            ar.setResMsg("success");
					            ar.setSucceed("is null");
				  			}else{
					            ar.setRes(0);
					            ar.setResMsg("error");
					            ar.setSucceed("is null");
				  			}   
					  }else{
						    ar.setRes(2);
				            ar.setResMsg("successMore");
				            ar.setSucceed(list);
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
	public AjaxRes findListByEpcCode(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			logger.info("进入后台epcCode:"+o.getEpcCode());
			List<MachineRfidBean> epcList = mService.findListByEpcCode(o);
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
	@RequestMapping(value = "findMachineListByEpcCode", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findMachineListByEpcCode(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			logger.info("进入后台epcCode:"+o.getEpcCode());
			List<MachineRfidBean> epcList = mService.findMachineListByEpcCode(o);
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
	
	/**
	 * 提交机具入库
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "submitRfidPut", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes submitRfidPut(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = mService.submitRfidPut(o);
			if(res>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed("入库成功");
				
			}else{
				ar.setRes(0);
				ar.setFailMsg("fail");
				ar.setSucceed("入库失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 完成机具入库
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "confirmPutTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes confirmPutTask(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int res = mService.confirmPutTask(o);
			if(res>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed("完成入库成功");
				
			}else{
				ar.setRes(0);
				ar.setFailMsg("fail");
				ar.setSucceed("完成出库失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
	/**
	 * 提交机具出库
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "submitRfidOut", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes submitRfidOut(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			logger.info("进入后台:"+o.getTaskId()+"mStr"+o.getmStr());
			int res = mService.submitRfidOut(o);
			if(res>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed("出库成功");
				
			}else{
				ar.setRes(0);
				ar.setFailMsg("fail");
				ar.setSucceed("出库失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 完成机具出库
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "confirmOutTask", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes confirmOutTask(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			logger.info("进入后台:"+o.getTaskId()+"mStr"+o.getmStr());
			int res = mService.confirmOutTask(o);
			if(res>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed("完成出库成功");
				
			}else{
				ar.setRes(0);
				ar.setFailMsg("fail");
				ar.setSucceed("完成出库失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	/**
	 * 根据RFID编号查询机具信息
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "getRfidDeviceInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getRfidDeviceInfo(MachineRfidBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			logger.info("进入后台:"+o.getEpcCode());
			List<MachineRfidBean> list = mService.getRfidDeviceInfo(o);
			ar.setResMsg("success");
			
			if(list !=null && list.size()>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed(list);
				
			}else{
				ar.setRes(0);
				ar.setFailMsg("fail:芯片未绑定机具！");
				ar.setSucceed("失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getRfidNMachineNum", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getRfidNMachineNum(MachineRfidBean o) throws Exception {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineRfidBean> list = mService.getRfidNMachineNum(o);
			if(list !=null && list.size()>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed(list);
			}else{
				ar.setRes(0);
				ar.setFailMsg("无相关信息");
				ar.setSucceed("失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getRfidNMachineDetails", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getMachineDetails(MachineRfidBean o) throws Exception {
		AjaxRes ar = getAjaxRes();
		try {
			List<MachineRfidBean> list = mService.getMachineDetails(o);
			if(list !=null && list.size()>0){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed(list);
			}else{
				ar.setRes(0);
				ar.setFailMsg("无相关信息");
				ar.setSucceed("失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	@RequestMapping(value = "getRfidNMachineStatus", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes getRfidNMachineStatus(MachineRfidBean o) throws Exception {
		AjaxRes ar = getAjaxRes();
		try {
			MachineRfidBean bean = mService.getRfidNMachineStatus(o);
			if(bean !=null){
				ar.setRes(1);
				ar.setResMsg("success");
				ar.setSucceed(bean);
			}else{
				ar.setRes(0);
				ar.setFailMsg("无相关信息");
				ar.setSucceed("失败");
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
	
}
