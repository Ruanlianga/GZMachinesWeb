package com.bonus.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.bm.beans.LogBean;
import com.bonus.bm.dao.LogDao;
import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.service.MachineService;
import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.pis.service.PutInStorageService;
import com.bonus.sys.BaseController;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/app/put/")
public class AppPutInStorageController extends BaseController<Object> {

	@Autowired
	private PutInStorageService pisService;

	@Autowired
	private MachineService mService;
	
	@Autowired
	LogDao logDao;

	/**
	 * 入库任务列表
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "putInStorageList", method = RequestMethod.POST)
	@ResponseBody
	public List<PutInStorageBean> putInStorageList(PutInStorageBean o, HttpServletRequest request) {
		List<PutInStorageBean> list = new ArrayList<>();
		try {
			list = pisService.putInStorageList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 入库任务列表详情
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "putInStorageDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<PutInStorageBean> putInStorageDetails(PutInStorageBean o, HttpServletRequest request) {
		List<PutInStorageBean> list = new ArrayList<>();
		try {
			list = pisService.putInStorageDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 入库记录
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getPutRecordList", method = RequestMethod.POST)
	@ResponseBody
	public List<PutInStorageBean> getPutRecordList(PutInStorageBean o, HttpServletRequest request) {
		List<PutInStorageBean> list = new ArrayList<>();
		try {
			list = pisService.getPutRecordList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 数量入库信息
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getPutInfoByNum", method = RequestMethod.POST)
	@ResponseBody
	public List<PutInStorageBean> getPutInfoByNum(PutInStorageBean o, HttpServletRequest request) {
		List<PutInStorageBean> list = new ArrayList<>();
		try {
			list = pisService.getPutInfoByNum(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	/**
	 * 数量入库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doPutByNum", method = RequestMethod.POST)
	@ResponseBody
	public int doPutByNum(PutInStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			PutInStorageBean bean = pisService.getAlPutNum(o);
			String prePutkNum = o.getPrePutNum();
			String thisPutNum = o.getThisPutNum();
			String alPutNum = bean.getAlPutNum();
			int alPutNums = 0;
			int thisPutNums = 0;
			int prePutNums = Integer.parseInt(prePutkNum);
			if (StringHelper.isEmpty(thisPutNum)) {
				thisPutNum = "0";
			}
			thisPutNums = Integer.parseInt(thisPutNum);
			if (StringHelper.isEmpty(alPutNum)) {
				alPutNum = "0";
				alPutNums = Integer.parseInt(alPutNum);
				alPutNums = alPutNums + thisPutNums;
				o.setAlPutNum(alPutNums + "");
			} else {
				alPutNums = Integer.parseInt(alPutNum);
				alPutNums = alPutNums + thisPutNums;
				o.setAlPutNum(alPutNums + "");
			}
			if (prePutNums > alPutNums) {
				pisService.updateAlPutNum(o);
				addPutRecord(o);
				res = 1;
			} else {
				res = 3;
			}
			if (prePutNums == alPutNums) {
				pisService.updateAlPutNum(o);
				addPutRecord(o);
				res = 2;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	/**
	 * 二维码入库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doPutByqrCode", method = RequestMethod.POST)
	@ResponseBody
	public int doPutByqrCode(PutInStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			
				// 插入已入库数量
				res = updateAlPutNum(o);
				if (res == 1) {
					// 改变机具状态
					res = updateMachineStatus(o);
					// 入库记录
					res = addPutRecord(o);
				} else {
					res = -2;// 二维码入库失败
				}
		
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	private boolean isBack(PutInStorageBean o) {
		try {
			PutInStorageBean psBean = new PutInStorageBean();
			psBean = pisService.findBackMachine(o);
			if (psBean != null) {
				String supId1 = o.getSupId();
				String supId2 = psBean.getSupId();
				if (supId1.equals(supId2)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			logger.error(e.toString(), e);
			return false;
		}
	}

	/**
	 * 设备编码入库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doPutBydeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public int doPutBydeviceCode(PutInStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			// 是否是预料工程机具
			boolean isBack = isBack(o);
			if (isBack) {
				// 插入已入库数量
				res = updateAlPutNum(o);
				if (res == 1) {
					// 改变机具状态
					res = updateMachineStatus(o);
					// 入库记录
					res = addPutRecord(o);
				} else {
					res = -2;
				}
			} else {
				res = -3;// 不是该工程退料机具，不能入库
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	private int updateAlPutNum(PutInStorageBean o) {
		try {
			// 鏌ヨ宸插叆搴撴暟閲�
			PutInStorageBean bean = pisService.getAlPutNum(o);
			String prePutNum = o.getPrePutNum();
			String alPutNum = bean.getAlPutNum();
			if (StringHelper.isEmpty(alPutNum) || "null".equals(alPutNum)) {
				int alPutNums = 0;
				int prePutNums = Integer.parseInt(prePutNum);
				if (prePutNums > alPutNums) {
					alPutNums = alPutNums + 1;
					o.setAlPutNum(alPutNums + "");
					pisService.updateAlPutNum(o);
				}
			} else {
				int alPutNums = Integer.parseInt(alPutNum);
				int prePutNums = Integer.parseInt(prePutNum);
				if (prePutNums > alPutNums) {
					alPutNums = alPutNums + 1;
					o.setAlPutNum(alPutNums + "");
					pisService.updateAlPutNum(o);
					if ( Math.abs(prePutNums - alPutNums) < 0.000001) {
						updateIsExample(o);
						updateIsSure(o);
					}
				} else {
					return 2;// 杈惧埌棰勬鏁�
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -2;
		}
		return 1;
	}

	private int updateMachineStatus(PutInStorageBean o) {
		String machineStatus = o.getMachineStatus();
		String deviceCode = o.getDeviceCode();
		String typeId = o.getTypeId();
		if ("9".equals(machineStatus)) {
			MachineBean bean = new MachineBean();
			bean.setDeviceCode(deviceCode);
			bean.setTypeId(typeId);
			bean.setBatchStatus("5");
			mService.updateMachineStatus(bean);
		} else {
			return -1;
		}
		return 1;
	}
	
	private int updateMachineNum(PutInStorageBean o) {
		try {
			LogBean logBean= new LogBean();
			logBean.setModel("入库审核");
			logBean.setFun("isAudit");
			logBean.setTask(o.getTaskId());
			logBean.setTypeId(o.getTypeId());
			LogBean inBean=logDao.findInNum(logBean);
			String description="在库数:"+inBean.getInNum()+";入库数: "+o.getThisPutNum();
			logBean.setDescription(description);
			logBean.setTime(DateTimeHelper.currentDateTime());
//			logBean.setCreator(UserShiroHelper.getRealCurrentUser().getId()+"");
			logDao.insertLog(logBean);
			
			pisService.updateMachineNum(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return 1;
	}

	private int addPutRecord(PutInStorageBean o) {
		try {
			o.setType("3");
			o.setRmStatus("13");
			o.setPutTime(DateTimeHelper.getNowTime());
			pisService.addPutRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return 1;
	}

	/**
	 * 确认入库信息
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "confirmPutTask", method = RequestMethod.POST)
	@ResponseBody
	public int confirmPutTask(PutInStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			float prePutNums = 0;
			float AlPutNums = 0;
			PutInStorageBean bean = pisService.confirmPutTask(o);
			String prePutNum = bean.getPrePutNum();
			String alPutNum = bean.getAlPutNum();
			prePutNums = Float.parseFloat(prePutNum);
			AlPutNums = Float.parseFloat(alPutNum);
			if (prePutNums == AlPutNums) {
				res = 1;
			} else {
				res = -1;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	/**
	 * 入库完成
	 * 
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "updatePutTask", method = RequestMethod.POST)
	@ResponseBody
	public String updatePutTask(PutInStorageBean o) {
		String res = "";
		try {
			pisService.updatePutTask(o);
			res = "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}
		return res;
	}
	
	/**
	 * 修试入库任务列表
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "machinePutInStorageList", method = RequestMethod.POST)
	@ResponseBody
	public List<PutInStorageBean> machinePutInStorageList(PutInStorageBean o, HttpServletRequest request) {
		List<PutInStorageBean> list = new ArrayList<>();
		try {
			list = pisService.machinePutInStorageList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	/**
	 * 修试入库列表详情
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "machinePutInStorageDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<PutInStorageBean> machinePutInStorageDetails(PutInStorageBean o, HttpServletRequest request) {
		List<PutInStorageBean> list = new ArrayList<>();
		try {
			list = pisService.machinePutInStorageDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	/**
	 * 设备编码入库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doInspectByDeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public int doInspectByDeviceCode(PutInStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			String deciceCodes = o.getDeviceCodes();
			String[] deviceCode = deciceCodes.split(",");
			for(int i = 0; i<deviceCode.length  ; i++){
				o.setDeviceCode(deviceCode[i]);
				// 插入已入库数量
				res = updateAlPutNumTwo(o);	
				// 改变机具状态
				res = updateMachineStatusTwo(o);
				// 修改入库记录状态
				res = updatePutRecordTwo(o);
				
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	
	private int updatePutRecordTwo(PutInStorageBean o) {
		try {
			return 	pisService.updatePutRecordTwo(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return 0;
		
	}

	private int updateAlPutNumTwo(PutInStorageBean o) {
		try {
			// 查询已入库数量
			PutInStorageBean bean = pisService.getAlPutNum(o);
			String prePutNum = bean.getPrePutNum();
			String alPutNum = bean.getAlPutNum();
			if (StringHelper.isEmpty(alPutNum) || "null".equals(alPutNum)) {
				int alPutNums = 0;
				int prePutNums = Integer.parseInt(prePutNum);
				if (prePutNums > alPutNums) {
					alPutNums = alPutNums + 1;
					o.setAlPutNum(alPutNums + "");
					o.setThisPutNum("1");
					pisService.updateAlPutNum(o);
					updateMachineNum(o);
				}
			} else {
				int alPutNums = Integer.parseInt(alPutNum);
				int prePutNums = Integer.parseInt(prePutNum);
				if (prePutNums > alPutNums) {
					alPutNums = alPutNums + 1;
					o.setAlPutNum(alPutNums + "");
					o.setThisPutNum("1");
					pisService.updateAlPutNum(o);
					updateMachineNum(o);
					if ( Math.abs(prePutNums - alPutNums) < 0.000001) {
						updateIsExample(o);
						updateIsSure(o);
					}
				} else {
					return 2;// 达到预检数
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -2;
		}
		return 1;
	}
	
	private void updateIsExample(PutInStorageBean o) {
		pisService.updateIsExample(o);
	}

	private void updateIsSure(PutInStorageBean o) {
		pisService.updateIsSure(o);
	}
	
	private int updateMachineStatusTwo(PutInStorageBean o) {
		MachineBean maBean = new MachineBean();
		maBean.setDeviceCode(o.getDeviceCode());
		MachineBean machineBean = mService.findByDeviceCode(maBean);
		String machineStatus = machineBean.getBatchStatus();
		String deviceCode = o.getDeviceCode();
		String typeId = o.getTypeId();
		if ("9".equals(machineStatus)) {
			MachineBean bean = new MachineBean();
			bean.setDeviceCode(deviceCode);
			bean.setTypeId(typeId);
			bean.setBatchStatus("5");
			mService.updateMachineStatus(bean);
		} else {
			return -1;
		}
		return 1;
	}
	
	private int addPutRecordTwo(PutInStorageBean o) {
		try {
			o.setType("3");
			o.setRmStatus("13");
			o.setPutTime(DateTimeHelper.getNowTime());
			pisService.addPutRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return 1;
	}
	
	/**
	 * 数量入库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doInspectByNum", method = RequestMethod.POST)
	@ResponseBody
	public int doInspectByNum(PutInStorageBean o, HttpServletRequest request) {
		int res = 0;
		try {
			PutInStorageBean bean = pisService.getAlPutNum(o);
			String prePutkNum = o.getPrePutNum();
			String thisPutNum = o.getThisPutNum();
			String alPutNum = bean.getAlPutNum();
			float alPutNums = 0;
			float thisPutNums = 0;
			float prePutNums = Float.parseFloat(prePutkNum);
			if (StringHelper.isEmpty(thisPutNum)) {
				thisPutNum = "0";
			}
			thisPutNums = Integer.parseInt(thisPutNum);
			if (StringHelper.isEmpty(alPutNum)) {
				alPutNum = "0";
				alPutNums =  Float.parseFloat(alPutNum);
				alPutNums = alPutNums + thisPutNums;
				o.setAlPutNum(alPutNums + "");
			} else {
				alPutNums = Float.parseFloat(alPutNum);
				alPutNums = alPutNums + thisPutNums;
				o.setAlPutNum(alPutNums + "");
			}
			if (prePutNums > alPutNums) {
				pisService.updateAlPutNum(o);
				addPutRecord(o);
				updateMachineNum(o);
				res = 1;
			} else {
				res = 3;
			}
			if (prePutNums == alPutNums) {
				pisService.updateAlPutNum(o);
				addPutRecord(o);
				updateMachineNum(o);
				updateIsExample(o);
				updateIsSure(o);
				res = 2;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	
	/**
	 * 修试入库已完成列表详情
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "machinePutInStorageDetailsFinish", method = RequestMethod.POST)
	@ResponseBody
	public List<PutInStorageBean> machinePutInStorageDetailsFinish(PutInStorageBean o, HttpServletRequest request) {
		List<PutInStorageBean> list = new ArrayList<>();
		try {
			list = pisService.machinePutInStorageDetailsFinish(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}


}
