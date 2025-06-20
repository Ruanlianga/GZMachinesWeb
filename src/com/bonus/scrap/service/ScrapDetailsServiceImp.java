package com.bonus.scrap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.PushtoSingle;
import com.bonus.core.StringHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.dao.ReturnMaterialDetailsDao;
import com.bonus.scrap.beans.ScrapDetailsBean;
import com.bonus.scrap.dao.ScrapDetailsDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.dao.UserDao;

@Service("scrap")
public class ScrapDetailsServiceImp extends BaseServiceImp<ScrapDetailsBean> implements ScrapDetailsService {

	@Autowired
	UserDao uDao;

	@Autowired
	ScrapDetailsDao dao;

	@Autowired
	MachineDao maDao;

	@Autowired
	ReturnMaterialDetailsDao rmDao;

	private PushtoSingle ps = new PushtoSingle();

	@Override
	public void isApproval(ScrapDetailsBean o) {
		UserBean uBean = new UserBean();
		int id = Integer.parseInt(o.getScrapPersonId());
		uBean = uDao.findCIdByUserId(id);
		ps.push(uBean.getcId(), "机具领料", "您有一条新的报废任务，请注意查收");
	}

	// 填写报废原因，通知仓储主管
	@Override
	public void scrapReason(ScrapDetailsBean o) {
		String taskId = o.getTaskId();
		String id = o.getId();
		// 插入报废记录表
		addScrapRecord(o);
		// 修改机具状态
		o.setTaskId(taskId);
		o.setId(id);
		if ("0".equals(o.getIsCount())) {
			MachineBean maBean = new MachineBean();
			maBean.setTypeId(o.getModelId());
			maBean.setDeviceCode(o.getDeviceCode());
			maBean.setBatchStatus("11");
			maDao.updateMachineStatus(maBean);
		}
		o.setScrapTime(DateTimeHelper.getNowTime());
		ScrapDetailsBean bean = dao.findAlScrapNum(o);
		if ("0".equals(o.getIsCount())) {
			float nums = Float.parseFloat(bean.getAlScrapNum());
			float alscrapNum = nums + 1;
			o.setAlScrapNum(alscrapNum + "");
		} else {
			o.setAlScrapNum(o.getScrapNum());
		}
		// 报废原因
		boolean tf = isSure(o);
		if (tf) {
			o.setIsSure("1");
		} else {
			o.setIsSure("0");
		}
		dao.addScrapReason(o);
		UserBean uBean = new UserBean();
		int ids = 13;
		uBean = uDao.findCIdByUserId(ids);
		ps.push(uBean.getcId(), "机具领料", "您有一条新的报废指定任务，请注意查收");
	}

	public boolean isSure(ScrapDetailsBean o) {
		ScrapDetailsBean bean = dao.findInfoNums(o);
		if (bean == null) {
			return false;
		} else {
			float nums = Float.parseFloat(bean.getNums());
			float scrapNum = Float.parseFloat(o.getScrapNum());
			if (scrapNum > nums) {
				return false;
			} else {
				return true;
			}
		}
	}

	public void addScrapRecord(ScrapDetailsBean o) {
		ReturnMaterialDetailsBean bean = new ReturnMaterialDetailsBean();
		bean.setSupId(o.getTaskId());
		bean.setCode(o.getDeviceCode());
		bean.setModelId(o.getModelId());
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setScrapReason(o.getScrapReson());
		bean.setState("7");//报废
		if ("1".equals(o.getIsCount())) {
			bean.setMaNum(o.getScrapNum());
		} else {
			bean.setMaNum("1");
		}
		bean.setRmStatus("4");
		rmDao.insertInfo(bean);
		if (StringHelper.isEmpty(o.getCheckId()) || "null".equals(o.getCheckId())) {
			//查询infoId
			ScrapDetailsBean sBean = dao.findInfoId(o);
			sBean.setRmStatus("4");
			dao.updateInfo(sBean);
		}else{
			List<ScrapDetailsBean> list = dao.findCheckInfo(o);
			ScrapDetailsBean sdBean = new ScrapDetailsBean();
			sdBean.setInfoId(list.get(0).getInfoId());
			sdBean.setRmStatus("4");
			dao.updateInfo(sdBean);
		}
		
	}

	@Override
	public List<ScrapDetailsBean> findScrapDevice(ScrapDetailsBean o) {
		List<ScrapDetailsBean> list = new ArrayList<ScrapDetailsBean>();
		if ("19".equals(o.getDefinitionId())) {
			list = dao.findScrapDevice(o);
		}else{
			list = dao.findReturnScrapDevice(o);
		}
		return list;
	}

	@Override
	public List<ScrapDetailsBean> findScrapTask(ScrapDetailsBean o) {
		List<ScrapDetailsBean> list = dao.findScrapTask(o);
		return list;
	}

	@Override
	public List<ScrapDetailsBean> findScrapTaskDetails(ScrapDetailsBean o) {
		List<ScrapDetailsBean> list = dao.findScrapTaskDetails(o);
		return list;
	}

	@Override
	public void surePeople(ScrapDetailsBean o) {
		dao.surePeople(o);
		
	}

	@Override
	public List<ScrapDetailsBean> getScrapIndexList(ScrapDetailsBean o) {
		String isFinish = o.getIsFinish();
		
		if("0".equals(isFinish)){
			List<ScrapDetailsBean> list = dao.getScrapIndexList(o);
			return list;
		}else{
			List<ScrapDetailsBean> list = dao.getScrapedIndexList(o);
			return list;
		}
	}

	@Override
	public List<ScrapDetailsBean> findScrapCodeList(ScrapDetailsBean o) {
		return dao.findScrapCodeList(o);
	}
	
	
	@Override
	@Transactional
	public int submitCodeScrap(ScrapDetailsBean o) {
		 int res = 0;
			try{
				String isCount = o.getIsCount();
				String scrapUrl = o.getScrapUrl();
				
				String scrapPart = o.getScrapReson();
				
				
				

					
					ScrapDetailsBean [] arr = o.getArr();
					
					for(int i=0; i< arr.length; i++){
						//修改退料机具状态和机具状态
						arr[i].setScrapUrl(scrapUrl);
						arr[i].setScrapPart(scrapPart);
						updateBackScrapStatus(arr[i]);
						
						matchScrapData(arr[i]);
						
					}
					
				
			}catch (Exception e) {
 				res = -1;
 				e.printStackTrace();
 			}
			return res;
	}
	
	private int updateBackScrapStatus(ScrapDetailsBean o) {
		int res = 0;
		try{
		   o.setRmStatus("4");
	       
		   res = dao.updateScrapStatus(o);
		   
			if (res == 0) {
				throw new ZeroAffectRowsException("修改维修报废RmStatus错误");
			}
           
           o.setBatchStatus("10");
           
           res = dao.updateDeviceStatus(o);
           if (res == 0) {
				throw new ZeroAffectRowsException("修改设备报废batchStatus错误");
			}
	    }catch (Exception e) {
			res = -1;
		}
		return res;
		
	}
	
	
	private int matchScrapData(ScrapDetailsBean o) {
		int res = 0;
		
		List<ScrapDetailsBean> list = dao.getMatchScrapList(o);
		
		float paraScrapNum = o.getThisScrapNum();
	
		String deviceCode = o.getDeviceCode();
		
		String scrapUrl = o.getScrapUrl();
		
		String scrapPart = o.getScrapPart();
		
		for(ScrapDetailsBean rb : list){
			
			String repairId = rb.getId();
			String taskId = rb.getTaskId();
			String modelId = rb.getModelId();
		
			float paraNum = rb.getParaNum();
			
			ScrapDetailsBean mtBean = new ScrapDetailsBean();
			
			mtBean.setId(repairId);
			mtBean.setTaskId(taskId);
			mtBean.setModelId(modelId);
			mtBean.setDeviceCode(deviceCode);
			mtBean.setScrapUrl(scrapUrl);
			
			mtBean.setScrapTime(DateTimeHelper.getNowTime());
			if (Math.abs(paraScrapNum - paraNum) < 0.000001) {
				
				//修改维修报废数据 scrap_num + paraScrapNum, is_sure = 1
				mtBean.setThisScrapNum(paraScrapNum);
				mtBean.setIsSure("1");
				mtBean.setScrapTime(DateTimeHelper.getNowDate());
				res =  dao.updateScrapDetails(mtBean);
				
				if (res == 0) {
					throw new ZeroAffectRowsException("修改维修报废数据 scrap_num + paraScrapNum错误");
				}
			
				
				//插入维修记录 scrap_num,   TYPE = 8 , RM_STATUS = 10 ,REMARK = repairPart
				mtBean.setInfoType("9");
				mtBean.setRmStatus("14");
				mtBean.setScrapPart(scrapPart);
				
				res = dao.insertScrapRecord(mtBean);
				
				if (res == 0) {
					throw new ZeroAffectRowsException("插入维修记录 scrap_num错误");
				}
			
				break;
				
			}else if(paraNum < paraScrapNum){
				//修改维修合格数 scrap_num + paraNum
				mtBean.setThisScrapNum(paraNum);
				
				res =  dao.updateScrapDetails(mtBean);
				
				if (res == 0) {
					throw new ZeroAffectRowsException("修改维修报废数据 scrap_num + paraNum错误");
				}
			
				//插入维修记录paraNum
				
				mtBean.setInfoType("9");
				mtBean.setRmStatus("14");
				mtBean.setScrapPart(scrapPart);
				
				res = dao.insertScrapRecord(mtBean);
				
				if (res == 0) {
					throw new ZeroAffectRowsException("插入维修记录 scrap_num错误");
				}
			
				paraScrapNum = paraScrapNum - paraNum;
			}else if(paraNum > paraScrapNum){
				
				//修改维修合格数 scrap_num + paraScrapNum
                mtBean.setThisScrapNum(paraScrapNum);
				
				res =  dao.updateScrapDetails(mtBean);
				
				if (res == 0) {
					throw new ZeroAffectRowsException("修改维修报废数据 scrap_num + paraNum错误");
				}
			
				
				//插入维修记录paraScrapNum
				mtBean.setInfoType("9");
				mtBean.setRmStatus("14");
				mtBean.setScrapPart(scrapPart);
				
				res = dao.insertScrapRecord(mtBean);
				
				if (res == 0) {
					throw new ZeroAffectRowsException("插入维修报废记录 scrap_num错误");
				}
			
				break;
			}else{
				//未匹配到数据
			}
			
		
		}
		return res;
	}
	
	
	@Override
	@Transactional
	public int submitNumScrap(ScrapDetailsBean o) {
		int res = 0;
		try{
			String isCount = o.getIsCount();
			
			
			
				
				 matchScrapData(o);
				
			
		
	       
		}catch (Exception e) {
			res = -1;
		}
		return res;
	}
	
	@Override
	public List<ScrapDetailsBean> findScrapCodeListFinish(ScrapDetailsBean o) {
		return dao.findScrapCodeListFinish(o);
	}
	
}
