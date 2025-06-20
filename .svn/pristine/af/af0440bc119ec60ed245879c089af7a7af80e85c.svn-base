package com.bonus.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.service.OutStorageService;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.ma.service.MachineService;
import com.bonus.sys.BaseController;
import com.bonus.sys.Page;

@Controller
@RequestMapping("/backstage/app/out/")
public class AppOutStorageController extends BaseController<Object> {

	@Autowired
	private OutStorageService osService;
	
	@Autowired
	private MachineService mService;

	/**
	 * 出库任务列表
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "outStorageList", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageBean> outStorageInspectionList(OutStorageBean o, HttpServletRequest request) {
		List<OutStorageBean> list = new ArrayList<>();
		try {
			System.out.println(o);
			list = osService.getOutStorageList(o);
			for(OutStorageBean bean : list) {
				List<OutStorageBean> items = osService.findIsFinishById(bean);
				Integer is = 0;
				for(OutStorageBean item: items) {
					String i = item.getIsFinish();
					is += Integer.parseInt(i);
				}
//				if(is == items.size()) {
//					list = null;
//				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		System.out.println(list);
		return list;
	}
	/**
	 * 出库任务列表详情
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "outStorageDetailsList", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageBean> outStorageDetailsList(OutStorageBean o, HttpServletRequest request) {
		List<OutStorageBean> list = new ArrayList<>();
		try {
			list = osService.getOutStorageDetailsList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 设备租赁分布查询
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping("query")
	public String query() {
		return "/lease/equipLeaseQueryList";
	}
	
	/**
	 * 设备租赁分布查询内容
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping("findQueryContent")
	public String findQueryContent(@RequestBody Page<OutStorageBean> page,OutStorageBean o,Model model) {
		page = osService.findQueryContent(page,o);
		model.addAttribute("page", page);
		return "/lease/equipLeaseQueryContent";
	}
	
	
	
	/**
	 * 出库记录
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOutRecordList", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageBean> getCheckRecordList(OutStorageBean o, HttpServletRequest request) {
		List<OutStorageBean> list = new ArrayList<>();
		try {
			list = osService.getOutRecordList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 二维码出库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doOutByqrCode", method = RequestMethod.POST)
	@ResponseBody
	public int doOutByqrCode(OutStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			// 插入已出库数量
			res = updateAlOutkNum(o);
			if (res == 1) {
				// 改变机具状态
				res = updateMachineStatus(o);
				// 出库记录
				res = addOutRecord(o);
			}else{
				res = -2;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	/**
	 * 二维码出库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doOutBydeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public int doOutBydeviceCode(OutStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			//判断设备是否已出库
			boolean isPut = isPutIn(o);
			if(!isPut){
				res = -3;//机具编码重复
			}else{
				// 插入已出库数量
				res = updateAlOutkNum(o);
				if (res == 1) {
					// 改变机具状态
					res = updateMachineStatus(o);
					// 出库记录
					res = addOutRecord(o);
				}else{
					res = -2;
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	private int updateAlOutkNum(OutStorageBean o) {
		// 查询已出库数量
		OutStorageBean bean = osService.getAlOutNum(o);
		String preOutNum = o.getPreOutNum();
		String alOutkNum = bean.getAlOutNum();
		if (StringHelper.isEmpty(alOutkNum) || "null".equals(alOutkNum)) {
			alOutkNum = "0";
			int alOutkNums = Integer.parseInt(alOutkNum);
			int preOutNums = Integer.parseInt(preOutNum);
			if (preOutNums > alOutkNums) {
				alOutkNums = alOutkNums + 1;
				o.setAlOutNum(alOutkNums + "");
				osService.updateAlOutNum(o);
			}
		} else {
			int alOutkNums = Integer.parseInt(alOutkNum);
			int preOutNums = Integer.parseInt(preOutNum);
			if (preOutNums > alOutkNums) {
				alOutkNums = alOutkNums + 1;
				o.setAlOutNum(alOutkNums + "");
				osService.updateAlOutNum(o);
			} else {
				return 2;// 达到预检数
			}
		}
		return 1;
	}

	private int updateMachineStatus(OutStorageBean o) {
		String machineStatus = o.getMachineStatus();
		String deviceCode = o.getDeviceCode();
		String typeId = o.getTypeId();
		if ("5".equals(machineStatus)) {
			MachineBean bean = new MachineBean();
			bean.setDeviceCode(deviceCode);
			bean.setTypeId(typeId);
			bean.setBatchStatus("6");
			mService.updateMachineStatus(bean);
		} else {
			return -1;
		}
		return 1;
	}

	private int addOutRecord(OutStorageBean o) {
		try {
			o.setType("2");
			o.setOutTime(DateTimeHelper.getNowTime());
			osService.addOutRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return 1;
	}
	
	public boolean isPutIn(OutStorageBean o){
		//根据任务及编码查询是否存在
		boolean flag = true;
		List<OutStorageBean> list = new ArrayList<OutStorageBean>();
		try {
			list = osService.findCodeBySupIdAndModId(o);
			for(int i = 0;i < list.size();i++){
				if(o.getDeviceCode().equals(list.get(i).getDeviceCode())){
					flag = false;
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return flag;
		
	}
	/**
	 * 数量出库信息
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getOutInfoByNum", method = RequestMethod.POST)
	@ResponseBody
	public List<OutStorageBean> getOutInfoByNum(OutStorageBean o, HttpServletRequest request) {
		List<OutStorageBean> list = new ArrayList<>();
		try {
			list = osService.getOutInfoByNum(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	/**
	 * 取消出库
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cancelOutTask", method = RequestMethod.POST)
	@ResponseBody
	public int cancelOutTask(OutStorageBean o) {
		int res = 0;
		try {
			Integer task = 0;
			Integer collar = 0;
			Integer out = 0;
			OutStorageBean r = osService.findSomeId(o);
			
			task = osService.cancelOutTask(r);
			collar = osService.updateCollarTask(r);
			out = osService.updateOutTask(r);
			if(r != null){
				// 修改库存
				osService.updateStorageNum(r);
			}
			
			
			if( task ==1 && collar ==1 && out == 1){
				res = 1;
			}else{
				res = -1;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	/**
	 * 数量出库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doOutByNum", method = RequestMethod.POST)
	@ResponseBody
	public int doInspectByNum(OutStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			OutStorageBean bean = osService.getAlOutNum(o);
			String preOutNum = o.getPreOutNum();
			String thisOutNum = o.getThisOutNum();
			String alOutNum = bean.getAlOutNum();
			int alOutNums = 0;
			int thisOutNums = 0;
			int preOutNums = Integer.parseInt(preOutNum);
			thisOutNums =Integer.parseInt(thisOutNum);
			if (StringHelper.isEmpty(alOutNum)) {
				alOutNum = "0";
				alOutNums = Integer.parseInt(alOutNum);
				alOutNums = alOutNums + thisOutNums;
				o.setAlOutNum(alOutNums + "");
			} else {
				alOutNums = Integer.parseInt(alOutNum);
				alOutNums = alOutNums + thisOutNums;
				o.setAlOutNum(alOutNums + "");
			}

			if (preOutNums > alOutNums) {
				res = osService.updateAlOutNum(o);
				if(res>0){
					addOutRecord(o);
					res = 1;
				}
			
				
			} else {
				res = -1;
			}
			if (preOutNums == alOutNums) {
				res = osService.updateAlOutNum(o);
				if(res>0){
					addOutRecord(o);
					res = 2;
				}
			
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	/**
	 * 确认出库信息
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "confirmOutTask", method = RequestMethod.POST)
	@ResponseBody
	public int confirmCheckTask(OutStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			float totalPreOutNums = 0;
			float totalAlOutNums = 0;
			OutStorageBean bean = osService.confirmOutTask(o);
			String totalPreOutNum = bean.getPreOutNum();
			String totalAlOutNum = bean.getAlOutNum();
			totalPreOutNums = Float.parseFloat(totalPreOutNum);
			totalAlOutNums = Float.parseFloat(totalAlOutNum);
			if(totalPreOutNums == totalAlOutNums){
				osService.addStorageData(o);
				
				res = 1;
			}else{
				res = -1;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	
	
	@RequestMapping(value = "buildAuditingTask", method = RequestMethod.POST)
	@ResponseBody
	public String buildAuditingTask(OutStorageBean o, HttpServletRequest request) {
		String res="";
		try {
			osService.buildAuditingTask(o);
			res = "1";
		} catch (Exception e) {
			res = "-1";
			logger.error(e.toString(), e);
		}
		return res;
	}
	
}
