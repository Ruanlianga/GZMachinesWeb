package com.bonus.ma.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.ma.dao.MachineRfidDao;
import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.pis.dao.PutInStorageDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;


@Service("machinerfid")
public class MachineRfidServiceImp extends BaseServiceImp<MachineRfidBean> implements MachineRfidService {

	@Autowired
	private MachineRfidDao dao;
	
	@Autowired
	TaskRecordDao trDao;
	
	@Autowired
	PutInStorageDao pisDao;

	@Override
	public int insertBean(MachineRfidBean o) {
		return dao.insertBean(o);
	}

	@Override
	public List<MachineRfidBean> findMachineByCode(MachineRfidBean o) {
		return dao.findMachineByCode(o);
	}

	@Override
	public int submitRfidBind(MachineRfidBean o) {
		int res = 0;
		
		String createTime = DateTimeHelper.getNowTime();
		try {
			o.setCreator(o.getUserId());
			o.setCreateTime(createTime);
			
			res = dao.insertRfidBind(o);
			
			res = dao.updateMaEpcCode(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return res;
	}

	@Override
	public List<MachineRfidBean> findMachineByEpcCode(MachineRfidBean o) {
		return dao.findMachineByEpcCode(o);
	}

	@Override
	public List<MachineRfidBean> findListByEpcCode(MachineRfidBean o) {
		return dao.findListByEpcCode(o);
	}

	@Override
	public int submitRfidOut(MachineRfidBean o) {
		String mStr = o.getmStr();
		try {
			
			JSONArray arr = new JSONArray(mStr);
			for(int i =0;i < arr.length();i++){
				MachineRfidBean obj = new MachineRfidBean();
				obj = o;
				JSONObject js = arr.getJSONObject(i);
				String maId = js.getString("maId");
				String deviceCode = js.getString("deviceCode");
				logger.info("maId:"+maId);
			    obj.setMaId(maId);
			    obj.setDeviceCode(deviceCode);
				// 修改已出库数量
				updateAlOutkNum(obj);
				// 出库记录
				addOutRecord(obj);
				//修改机具状态
				MachineBean bean = new MachineBean();
				bean.setId(maId);
				bean.setBatchStatus("6");
				
				updateMachineStatus(bean);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return 1;
	}

	private int addOutRecord(MachineRfidBean o) {
		try {
			o.setType("2");
			o.setThisOutNum("1");
			o.setOutTime(DateTimeHelper.getNowTime());
			dao.addOutRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return 1;
		
	}

	private void updateMachineStatus(MachineBean bean) {
		dao.updateMachineStatus(bean);
		
	}

	private int updateAlOutkNum(MachineRfidBean o) {
		// 查询已出库数量
		MachineRfidBean bean = dao.getAlOutNum(o);
		String preOutNum = o.getPreOutNum();
		String alOutkNum = bean.getAlOutNum();
		if (StringHelper.isEmpty(alOutkNum) || "null".equals(alOutkNum)) {
			alOutkNum = "0";
			int alOutkNums = Integer.parseInt(alOutkNum);
			int preOutNums = Integer.parseInt(preOutNum);
			if (preOutNums > alOutkNums) {
				alOutkNums = alOutkNums + 1;
				o.setAlOutNum(alOutkNums + "");
				dao.updateAlOutNum(o);
			}
		} else {
			int alOutkNums = Integer.parseInt(alOutkNum);
			int preOutNums = Integer.parseInt(preOutNum);
			if (preOutNums > alOutkNums) {
				alOutkNums = alOutkNums + 1;
				o.setAlOutNum(alOutkNums + "");
				dao.updateAlOutNum(o);
			} else {
				return 2;// 达到预检数
			}
		}
		return 1;
	}

	@Override
	public List<MachineRfidBean> getBaseList(MachineRfidBean o) {
		return dao.getBaseList(o);
	}

	@Override
	public int confirmOutTask(MachineRfidBean o) {
		int res = 0;
		try {
			float totalPreOutNums = 0;
			float totalAlOutNums = 0;
			MachineRfidBean bean = dao.confirmOutTask(o);
			String totalPreOutNum = bean.getPreOutNum();
			String totalAlOutNum = bean.getAlOutNum();
			totalPreOutNums = Float.parseFloat(totalPreOutNum);
			totalAlOutNums = Float.parseFloat(totalAlOutNum);
			if(totalPreOutNums == totalAlOutNums){
				res = 1;
				buildAuditingTask(o);
			}else{
				res = -1;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

    //创建出库审核
	public int buildAuditingTask(MachineRfidBean o) {
		int res = 0;
		dao.updateIsSure(o);
		// 修改出库任务状态
		res = updateIsFinish(o);
		return res;
	}
	
	// 修改出库任务状态
		public int updateIsFinish(MachineRfidBean o) {
			int res = -1;
			List<MachineRfidBean> list = dao.findIsSureInfo(o);
			if (list != null && list.size() > 0) {
				return res;
			} else {
				TaskRecordBean bean = new TaskRecordBean();
				bean.setId(o.getTaskId());
				bean.setIsFinish("1");
				trDao.update(bean);
				res = 1;
			}
			return res;
		}


	@Override
	public List<MachineRfidBean> getRfidDeviceInfo(MachineRfidBean o) {
		List<MachineRfidBean> list = new ArrayList<>();
		list = dao.getRfidDeviceInfo(o);
		return list;
	}

	@Override
	public int submitRfidPut(MachineRfidBean o) {
		String mStr = o.getmStr();
		try {
			JSONArray arr = new JSONArray(mStr);
			for(int i =0;i < arr.length();i++){
				MachineRfidBean obj = new MachineRfidBean();
				obj = o;
				JSONObject js = arr.getJSONObject(i);
				String maId = js.getString("maId");
				String deviceCode = js.getString("deviceCode");
				logger.info("maId:"+maId);
			    obj.setMaId(maId);
			    obj.setDeviceCode(deviceCode);
				// 修改已入库数量
				updateAlPutkNum(obj);
				// 入库记录
				addPutRecord(obj);
				// 修改机具状态
				MachineBean bean = new MachineBean();
				bean.setId(maId);
				bean.setBatchStatus("5");
				updateMachineStatus(bean);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return 1;
	}

	private int updateAlPutkNum(MachineRfidBean o) {
		try {
			// 查询已入库数量
			MachineRfidBean bean = dao.getAlPutNum(o);
			String prePutNum = o.getPrePutNum();
			String alPutNum = bean.getAlPutNum();
			if (StringHelper.isEmpty(alPutNum) || "null".equals(alPutNum)) {
				int alPutNums = 0;
				int prePutNums = Integer.parseInt(prePutNum);
				if (prePutNums > alPutNums) {
					alPutNums = alPutNums + 1;
					o.setAlPutNum(alPutNums + "");
					dao.updateAlPutNum(o);
				}
			} else {
				int alPutNums = Integer.parseInt(alPutNum);
				int prePutNums = Integer.parseInt(prePutNum);
				if (prePutNums > alPutNums) {
					alPutNums = alPutNums + 1;
					o.setAlPutNum(alPutNums + "");
					dao.updateAlPutNum(o);
					if ( Math.abs(prePutNums - alPutNums) < 0.000001) {
						PutInStorageBean put = new PutInStorageBean();
						put.setId(o.getId());
						updateIsExample(put);
						updateIsSure(put);
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
		pisDao.updateIsExample(o);
	}
	
	private void updateIsSure(PutInStorageBean o) {
		pisDao.updateIsSure(o);
	}
	
	private int addPutRecord(MachineRfidBean o) {
		try {
			o.setType("3");
			o.setThisPutNum("1");
			o.setPutTime(DateTimeHelper.getNowTime());
			dao.addPutRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return 1;
		
	}

	@Override
	public int confirmPutTask(MachineRfidBean o) {
		int res = 0;
		try {
			float totalPrePutNums = 0;
			float totalAlPutNums = 0;
			MachineRfidBean bean = dao.confirmPutTask(o);
			String totalPrePutNum = bean.getPrePutNum();
			String totalAlPutNum = bean.getAlPutNum();
			totalPrePutNums = Float.parseFloat(totalPrePutNum);
			totalAlPutNums = Float.parseFloat(totalAlPutNum);
			if(totalPrePutNums == totalAlPutNums){
				res = 1;
				buildAuditingPutTask(o);
			}else{
				res = -1;
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}
	
	//创建入库审核
	public int buildAuditingPutTask(MachineRfidBean o) {
		int res = 0;
		dao.updatePutIsSure(o);
		// 修改入库任务状态
		res = updatePutIsFinish(o);
		return res;
	}
	
	public int updatePutIsFinish(MachineRfidBean o) {
		List<MachineRfidBean> list = dao.findIsFinish(o);
		TaskRecordBean trBean = new TaskRecordBean();
		trBean.setId(o.getTaskId());
		if (list == null || list.size() < 1) {
			trBean.setIsFinish("1");
			trDao.update(trBean);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public List<MachineRfidBean> findMachineListByEpcCode(MachineRfidBean o) {

		return dao.findMachineListByEpcCode(o);
	}
	
	@Override
	public List<MachineRfidBean> getRfidNMachineNum(MachineRfidBean o) {

		return dao.getRfidNMachineNum(o);
	}
	
	@Override
	public List<MachineRfidBean> getMachineDetails(MachineRfidBean o) {

		return dao.getMachineDetails(o);
	}
	
	@Override
	public MachineRfidBean getRfidNMachineStatus(MachineRfidBean o) {

		return dao.getRfidNMachineStatus(o);
	}
	
}
