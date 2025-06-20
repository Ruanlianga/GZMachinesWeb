package com.bonus.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.lease.beans.OutStorageCheckBean;
import com.bonus.lease.service.OutStorageCheckService;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.service.MachineService;
import com.bonus.sys.BaseController;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/app/outcheck/")
public class AppOutStorageInspectionController extends BaseController<Object> {

	@Autowired
	private OutStorageCheckService oscService;

	@Autowired
	private MachineService mService;

	/**
	 * 出库检验任务列表
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "outCheckList", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageCheckBean> outStorageInspectionList(OutStorageCheckBean o, HttpServletRequest request) {
		List<OutStorageCheckBean> list = new ArrayList<>();
		try {
			list = oscService.getOutStorageCheckList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 出库检验任务列表详情
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "outCheckListDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageCheckBean> inspectionListDetails(OutStorageCheckBean o, HttpServletRequest request) {
		List<OutStorageCheckBean> list = new ArrayList<>();
		try {
			list = oscService.getOutStorageCheckDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 出库检验记录
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getCheckRecordList", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageCheckBean> getCheckRecordList(OutStorageCheckBean o, HttpServletRequest request) {
		List<OutStorageCheckBean> list = new ArrayList<>();
		try {
			list = oscService.getCheckRecordList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 数量出库检验信息
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getCheckInfoByNum", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageCheckBean> getCheckInfoByNum(OutStorageCheckBean o, HttpServletRequest request) {
		List<OutStorageCheckBean> list = new ArrayList<>();
		try {
			list = oscService.getCheckInfoByNum(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 数量出库检验
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doCheckByNum", method = RequestMethod.POST)
	@ResponseBody
	public int doInspectByNum(OutStorageCheckBean o, HttpServletRequest request) {
		int res = 0;
		try {
			OutStorageCheckBean bean = oscService.getAlCheckNum(o);
			String preCheckNum = o.getPreCheckNum();
			String thisCheckNum = o.getThisCheckNum();
			String alCheckNum = bean.getAlCheckNum();
			float alCheckNums = 0;
			float thisCheckNums = 0;
			float preCheckNums = Float.parseFloat(preCheckNum);
			thisCheckNums = Float.parseFloat(thisCheckNum);
			if (StringHelper.isEmpty(alCheckNum)) {
				alCheckNum = "0";
				alCheckNums = Float.parseFloat(alCheckNum);
				alCheckNums = alCheckNums + thisCheckNums;
				o.setAlCheckNum(alCheckNums + "");
			} else {
				alCheckNums = Float.parseFloat(alCheckNum);
				alCheckNums = alCheckNums + thisCheckNums;
				o.setAlCheckNum(alCheckNums + "");
			}

			if (preCheckNums > alCheckNums) {
				oscService.updateAlCheckNum(o);
				addCheckRecord(o);
				res = 1;
			} else {
				res = -1;
			}
			if (preCheckNums == alCheckNums) {
				oscService.updateAlCheckNum(o);
				addCheckRecord(o);
				res = 2;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	/**
	 * 二维码出库检验
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "docheckByqrCode", method = RequestMethod.POST)
	@ResponseBody
	public int doInspectByqrCode(OutStorageCheckBean o, HttpServletRequest request) {
		int res = 0;
		try {
			// 插入已检数量
			res = updateAlCheckNum(o);
			if (res == 1) {
				// 改变机具状态
				res = updateMachineStatus(o);
				// 出库检验记录
				res = addCheckRecord(o);
			}else{
				res = -2;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	/**
	 * 设备编码出库检验
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "docheckBydeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public int docheckBydeviceCode(OutStorageCheckBean o, HttpServletRequest request) {
		int res = 0;
		try {
			// 插入已检数量
			res = updateAlCheckNum(o);
			if (res == 1) {
				// 改变机具状态
				res = updateMachineStatus(o);
				// 出库检验记录
				res = addCheckRecord(o);
			}else{
				res = -1;//插入已检数量失败
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	private int updateAlCheckNum(OutStorageCheckBean o) {
		// 查询已检验数量
		OutStorageCheckBean bean = oscService.getAlCheckNum(o);
		String preCheckNum = o.getPreCheckNum();
		String alCheckNum = bean.getAlCheckNum();
		if (StringHelper.isEmpty(alCheckNum) || "null".equals(alCheckNum)) {
			alCheckNum = "0";
			int alCheckNums = Integer.parseInt(alCheckNum);
			int preCheckNums = Integer.parseInt(preCheckNum);
			if (preCheckNums > alCheckNums) {
				alCheckNums = alCheckNums + 1;
				o.setAlCheckNum(alCheckNums + "");
				oscService.updateAlCheckNum(o);
			}
		} else {
			int alCheckNums = Integer.parseInt(alCheckNum);
			int preCheckNums = Integer.parseInt(preCheckNum);
			if (preCheckNums > alCheckNums) {
				alCheckNums = alCheckNums + 1;
				o.setAlCheckNum(alCheckNums + "");
				oscService.updateAlCheckNum(o);
			} else {
				return 2;// 达到预检数
			}
		}
		return 1;
	}

	private int updateMachineStatus(OutStorageCheckBean o) {
		String machineStatus = o.getMachineStatus();
		String deviceCode = o.getDeviceCode();
		String typeId = o.getTypeId();
		if ("5".equals(machineStatus)) {
			MachineBean bean = new MachineBean();
			bean.setDeviceCode(deviceCode);
			bean.setTypeId(typeId);
			bean.setBatchStatus("8");
			mService.updateMachineStatus(bean);
		} else {
			return -1;
		}
		return 1;
	}

	private int addCheckRecord(OutStorageCheckBean o) {
		try {
			o.setType("1");
			o.setCheckTime(DateTimeHelper.getNowTime());
			oscService.addCheckRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return 1;
	}

	
	/**
	 * 确认出库检验信息
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "confirmCheckTask", method = RequestMethod.POST)
	@ResponseBody
	public int confirmCheckTask(OutStorageCheckBean o, HttpServletRequest request) {
		int res = 0;
		try {
			float totalPreCheckNums = 0;
			float totalAlCheckNums = 0;
			OutStorageCheckBean bean = oscService.confirmCheckTask(o);
			String totalPreCheckNum = bean.getPreCheckNum();
			String totalAlCheckNum = bean.getAlCheckNum();
			totalPreCheckNums = Float.parseFloat(totalPreCheckNum);
			totalAlCheckNums = Float.parseFloat(totalAlCheckNum);
			if(totalPreCheckNums == totalAlCheckNums){
				res = 1;
			}else{
				res = -1;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	
	
	@RequestMapping(value = "buildOutPutTask", method = RequestMethod.POST)
	@ResponseBody
	public String buildOutPutTask(OutStorageCheckBean o) {
		String res = "";
		try {
			oscService.buildOutPutTask(o);
			res = "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}
		return res;
	}

}
