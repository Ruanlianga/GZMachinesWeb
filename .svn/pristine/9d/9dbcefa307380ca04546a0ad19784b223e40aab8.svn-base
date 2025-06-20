package com.bonus.repair.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repair.dao.RepairDetailsDao;
import com.bonus.repairCheck.beans.RepairCheckDetailsBean;
import com.bonus.repairCheck.dao.RepairCheckDetailsDao;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.dao.ReturnMaterialDetailsDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.dao.UserDao;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("repair")
public class RepairDetailsServiceImp extends BaseServiceImp<RepairDetailsBean> implements RepairDetailsService {

	@Autowired
	UserDao uDao;

	@Autowired
	RepairDetailsDao dao;

	@Autowired
	MachineDao maDao;

	@Autowired
	ReturnMaterialDetailsDao rmDao;
	
	@Autowired
	TaskRecordDao trDao;
	
	@Autowired
	RepairCheckDetailsDao rcDao;

	@Override
	public List<RepairDetailsBean> findRepairDevice(RepairDetailsBean o) {
		List<RepairDetailsBean> list = new ArrayList<RepairDetailsBean>();
		if ("18".equals(o.getDefinitionId())) {
			list = dao.findRepairDevice(o);
		}else{
			list = dao.findReturnRepairDevice(o);
		}
		return list;
	}

	@Override
	public List<RepairDetailsBean> findRepairTask(RepairDetailsBean o) {
		List<RepairDetailsBean> list = dao.findRepairTask(o);
		return list;
	}

	@Override
	public List<RepairDetailsBean> findRepairTaskDetails(RepairDetailsBean o) {
		List<RepairDetailsBean> list = dao.findRepairTaskDetails(o);
		return list;
	}
	
	@Override
	public void repairOperation(RepairDetailsBean o) {
		// 判断合格还是报废
		if ("1".equals(o.getIsScrap())) {
			repairQualified(o);
		} else {
			// 报废1.添加2.修改机具状态3.添加报废明细
			repairScrap(o);
		}

	}

	// 维修合格
	public void repairQualified(RepairDetailsBean o) {
		// 1.修改机具状态，3.修改维修数量2.添加维修明细4.添加配件信息
		String taskId = o.getTaskId();
		String id = o.getId();
		o.setRmStatus("5");
		o.setBatchStatus("19");
		if ("0".equals(o.getIsCount())) {
			updateDevStatus(o);
//			addRepairPart(o);
			addRepairDetails(o);
			updateRepairNum(o);
			o.setTaskId(taskId);
			o.setId(id);
//			buildRepairCheckTask(o);
			o.setTaskId(taskId);
			o.setId(id);
			//修改总任务状态
//			updateIsFinish(o);
		} else {
			addRepairDetails(o);
//			addRepairPart(o);
			updateRepairNum(o);
//			buildRepairCheckTask(o);
			o.setTaskId(taskId);
			o.setId(id);
//			updateIsFinish(o);
		}
	}

	public void repairScrap(RepairDetailsBean o) {
		String taskId = o.getTaskId();
		String id = o.getId();
		o.setRmStatus("6");
		o.setBatchStatus("10");
		if ("0".equals(o.getIsCount())) {
			updateDevStatus(o);
			addRepairDetails(o);
			updateScrapNum(o);
			buildRepairCheckTask(o);
			o.setTaskId(taskId);
			o.setId(id);
			updateIsFinish(o);
		} else {
			addRepairDetails(o);
			updateScrapNum(o);
			buildRepairCheckTask(o);
			o.setTaskId(taskId);
			o.setId(id);
			updateIsFinish(o);
		}

	}

	// 修改机具状态
	public void updateDevStatus(RepairDetailsBean o) {
		MachineBean maBean = new MachineBean();
		maBean.setTypeId(o.getModelId());
		maBean.setDeviceCode(o.getDeviceCode());
		maBean.setBatchStatus(o.getBatchStatus());
		maDao.updateMachineStatus(maBean);
	}

	// 修改维修数量
	public void updateRepairNum(RepairDetailsBean o) {
		RepairDetailsBean bean = dao.findAlRepairNum(o);
		if ("0".equals(o.getIsCount())) {
			float nums = Float.parseFloat(bean.getAlRepairNum());
			float alrepairNum = nums + 1;
			o.setAlRepairNum(alrepairNum + "");
		} else {
			float alRepairNum = Float.parseFloat(bean.getAlRepairNum());
			float operationNum = Float.parseFloat(o.getOperationNum());
			float nums = operationNum + alRepairNum;
			o.setAlRepairNum(nums + "");
		}
		boolean tf = isSure(o);
		if (tf) {
			o.setIsSure("1");
		} else {
			o.setIsSure("0");
		}
		o.setRepairTime(DateTimeHelper.getNowTime());
		dao.updateNum(o);
	}

	// 修改报废数量
		public void updateScrapNum(RepairDetailsBean o) {
			//查询维修报废数
			RepairDetailsBean bean = dao.findScrapNum(o);
			if ("0".equals(o.getIsCount())) {
				float nums = Float.parseFloat(bean.getScrapNum());
				float alrepairNum = nums + 1;
				o.setScrapNum(alrepairNum + "");
			} else {
				float alRepairNum = Float.parseFloat(bean.getScrapNum());
				float operationNum = Float.parseFloat(o.getScrapNum());
				float nums = operationNum + alRepairNum;
				o.setScrapNum(nums + "");
			}
			boolean tf = isSure(o);
			if (tf) {
				o.setIsSure("1");
			} else {
				o.setIsSure("0");
			}
			o.setRepairTime(DateTimeHelper.getNowTime());
			dao.updateScrapNum(o);
		}
	// 判断是否符合已完成
	public boolean isSure(RepairDetailsBean o) {
		RepairDetailsBean bean = dao.findInfoNums(o);
		if (bean == null) {
			return false;
		} else {
			float nums = Float.parseFloat(bean.getNums());
			float repairNum = Float.parseFloat(o.getRepairNum());
			if (repairNum > nums) {
				return false;
			} else {
				return true;
			}
		}
	}

	// 添加维修记录
	//operationNum 维修操作数  
	//repairNum 
	public void addRepairDetails(RepairDetailsBean o) {
		ReturnMaterialDetailsBean bean = new ReturnMaterialDetailsBean();
		bean.setSupId(o.getTaskId());
		bean.setCode(o.getDeviceCode());
		bean.setModelId(o.getModelId());
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setRmStatus(o.getRmStatus());
		bean.setState("6");
		bean.setIsSplit("0");
		if(o.getScrapUrl() != null || o.getScrapUrl() != "")
			bean.setScrapUrl(o.getScrapUrl());
		if ("1".equals(o.getIsCount())) {
			//操作数
			if (StringHelper.isNotEmpty(o.getOperationNum())) {
				bean.setMaNum(o.getOperationNum());
				rmDao.insertInfo(bean);
				//报废数
				if (StringHelper.isNotEmpty(o.getScrapNum())) {
					bean.setMaNum(o.getScrapNum());
					bean.setScrapReason(o.getScrapReason());
					rmDao.insertInfo(bean);
				}
			} else {
				bean.setMaNum(o.getScrapNum());
				bean.setScrapReason(o.getScrapReason());
				rmDao.insertInfo(bean);
			}
		} else {
			bean.setMaNum("1");
			bean.setScrapReason(o.getScrapReason());
			rmDao.insertInfo(bean);
		}
		RepairDetailsBean rdBean = dao.findAlRepairNum(o);
		if ("1".equals(o.getIsCount())) {
			float alRepairNum = Float.parseFloat(rdBean.getAlRepairNum());
			float operationNum = 0;
			if ("0".equals(o.getIsScrap())) {
				operationNum = Float.parseFloat(o.getScrapNum());
			}else{
				operationNum = Float.parseFloat(o.getOperationNum());
			}
			float nums = Float.parseFloat(o.getRepairNum());
			if (StringHelper.isEmpty(o.getCheckId()) || "null".equals(o.getCheckId())) {
				if (nums == (alRepairNum + operationNum)) {
					RepairDetailsBean rBean = dao.findInfo(o);
					rBean.setRmStatus("7");
					dao.updateInfo(rBean);
				}
			}else{
				if (nums == (alRepairNum + operationNum)) {
					RepairDetailsBean rBean = dao.findCheckInfo(o);
					rBean.setRmStatus("7");
					dao.updateInfo(rBean);
				}
			}
		} else {
			// 修改info表信息
			if (StringHelper.isEmpty(o.getCheckId()) || "null".equals(o.getCheckId())) {
				RepairDetailsBean rBean = dao.findInfo(o);
				rBean.setRmStatus(o.getRmStatus());
				dao.updateInfo(rBean);
			}else{
				RepairDetailsBean rBean = dao.findCheckInfo(o);
				rBean.setRmStatus(o.getRmStatus());
				dao.updateInfo(rBean);
			}
			
		}
	}
	

	/*// 添加维修配件
	public void addRepairPart(RepairDetailsBean o) {
		if (StringHelper.isEmpty(o.getCheckId()) || "null".equals(o.getCheckId())) {
			RepairDetailsBean rBean = dao.findInfo(o);
			rBean.setRepairPart(o.getRepairPart());
			dao.insertPart(rBean);
		}else{
			RepairDetailsBean rBean = dao.findCheckInfo(o);
			rBean.setRepairPart(o.getRepairPart());
			dao.insertPart(rBean);
		}
		
	}*/
	
	public void buildRepairCheckTask(RepairDetailsBean o){
		//1.判断是否满足检验任务建立2.满足建立检验任务
		//查询维修数和报废shu
		RepairDetailsBean bean = dao.findRepairInfo(o);
		float scrapNum = Float.parseFloat(bean.getScrapNum());
		float repairNum = Float.parseFloat(bean.getRepairNum());
		float alRepairNum = Float.parseFloat(bean.getAlRepairNum());
		if (repairNum == (scrapNum + alRepairNum)) {
			if (StringHelper.isEmpty(o.getCheckId()) || "null".equals(o.getCheckId())) {
				addRepairCheckTask(o);
			}else{
				addReturnRepairCheckTask(o);
			}
			
		}
		
	}
	
	// 建立维修检验任务 1.taskId2.supId,modelId
		public void addRepairCheckTask(RepairDetailsBean o) {
			// 判断该退料任务下有无维修任务
			TaskRecordBean bean = new TaskRecordBean();
			// 未传值
			bean.setTaskId(o.getSupId());
			bean.setDefinitionId("20");
			// 查询是否有任务
			List<TaskRecordBean> list = trDao.findRepairTask(bean);
			if (list != null && list.size() > 0) {
				// 添加明细
				RepairDetailsBean rdBean = new RepairDetailsBean();
				rdBean.setModelId(o.getModelId());
				rdBean.setTaskId(list.get(0).getTaskId());
				rdBean.setRepairNum(o.getRepairNum());
				rdBean.setId(o.getId());
				addCheckDetails(rdBean);
			} else {
				// 建立任务
				addRepairTask(o);
			}

		}
		public void addReturnRepairCheckTask(RepairDetailsBean o) {
			// 判断该退料任务下有无检验任务
			TaskRecordBean bean = new TaskRecordBean();
			// 未传值
			bean.setTaskId(o.getSupId());
			bean.setDefinitionId("24");
			// 查询是否有任务
			List<TaskRecordBean> list = trDao.findRepairTask(bean);
			if (list != null && list.size() > 0) {
				// 添加明细
				RepairDetailsBean rdBean = new RepairDetailsBean();
				rdBean.setModelId(o.getModelId());
				rdBean.setTaskId(list.get(0).getTaskId());
				rdBean.setRepairNum(o.getRepairNum());
				rdBean.setId(o.getId());
				addCheckDetails(rdBean);
			} else {
				// 建立任务
				addReturnRepairTask(o);
			}

		}

		// 任务总表添加检验任务
		public void addRepairTask(RepairDetailsBean o) {
			TaskRecordBean bean = new TaskRecordBean();
			String number = findNumber(o);
			bean.setNumber(number);
			bean.setOperationTime(DateTimeHelper.getNowTime());
			bean.setOperationUserId(o.getUserId());
			bean.setDefinitionId("20");
			bean.setProcessId("4");
			bean.setIsFinish("0");
			bean.setTaskId(o.getSupId());
			trDao.insert(bean);
			// 添加明细
			RepairDetailsBean rdBean = new RepairDetailsBean();
			rdBean.setModelId(o.getModelId());
			rdBean.setTaskId(bean.getId());
			rdBean.setRepairNum(o.getRepairNum());
			rdBean.setId(o.getId());
			addCheckDetails(rdBean);
		}
		
		public void addReturnRepairTask(RepairDetailsBean o) {
			TaskRecordBean bean = new TaskRecordBean();
			String number = findNumber(o);
			bean.setNumber(number);
			bean.setOperationTime(DateTimeHelper.getNowTime());
			bean.setOperationUserId(o.getUserId());
			bean.setDefinitionId("24");
			bean.setProcessId("4");
			bean.setIsFinish("0");
			bean.setTaskId(o.getSupId());
			trDao.insert(bean);
			// 添加明细
			RepairDetailsBean rdBean = new RepairDetailsBean();
			rdBean.setModelId(o.getModelId());
			rdBean.setTaskId(bean.getId());
			rdBean.setRepairNum(o.getRepairNum());
			rdBean.setId(o.getId());
			addCheckDetails(rdBean);
		}
		//添加任务明细
		public void addCheckDetails(RepairDetailsBean o){
			RepairCheckDetailsBean rcBean = new RepairCheckDetailsBean();
			rcBean.setTaskId(o.getTaskId());
			rcBean.setModelId(o.getModelId());
			rcBean.setOperationTime(DateTimeHelper.getNowTime());
			rcBean.setCheckNum(o.getRepairNum());
			rcBean.setRepairId(o.getId());
			//sql
			rcDao.insert(rcBean);
		}
		//生成检验单号
		public String findNumber(RepairDetailsBean o) {
			String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
			String nowDay = DateTimeHelper.getFormatNowMonthAndDay();
			String operationTime = DateTimeHelper.getNowDate();
			TaskRecordBean bean = new TaskRecordBean();
			bean.setOperationTime(operationTime);
			bean.setDefinitionId("20");
			String count = trDao.findNumber(bean);
			int str = Integer.parseInt(count) + 1;
			String counts = String.format("%03d", str);
			String code = "JY" + yearLast + nowDay + counts;
			return code;
		}
		//修改总任务状态
		public void updateIsFinish(RepairDetailsBean o){
			o.setIsSure("0");
			List<RepairDetailsBean> list = dao.findIsSure(o);
			if (list != null && list.size() > 0) {
				return;
			}else{
				TaskRecordBean trBean = new TaskRecordBean();
				trBean.setId(o.getTaskId());
				trBean.setIsFinish("1");
				trDao.update(trBean);
			}
		}

		@Override
		public List<RepairDetailsBean> findTypeName(RepairDetailsBean o) {
			return dao.findTypeName(o);
		}
		@Override
		public List<RepairDetailsBean> findModelName(RepairDetailsBean o) {
			return dao.findModelName(o);
		}
		
		
		//维修拆分
		@Override
		public String repairSplit(RepairDetailsBean o) {
			String res = "";
			try {
			
					List<RepairDetailsBean> list = dao.getSplitListByTask(o);
					if(list != null && list.size() >0){
						//创建检修任务
						 res = addCheckSplitTask(o,list);//返回 “存在维修检验任务” 或者 “拆分任务建立成功”
						 if( res == "拆分任务建立成功"){
							 res = updateSplitStatus(o,list);//返回  “拆分状态修改完成”
						 }
					}else{
						res = "未查询到维修拆分的机具";//未查询到维修拆分的机具updateSplitStatusTwo
					}
				
			} catch (Exception e) {
				logger.error(e.toString(), e);
				res = "拆分过程中出现错误";
			}
			return res;
		}

		
		//结束维修任务
		@Override
		public String finishRepair(RepairDetailsBean o) {
			String res = "";
			try{
//				查询wf_repair_details表中,（条件is_sure=1，taskId=#{taskId}）的数据，
//				若listIsSure不为空,(为空，则没有进行数据处理)，不为空则进行过数据处理
				List<RepairDetailsBean> listIsSure = dao.getIsSureNum(o);								
				if(listIsSure != null && listIsSure.size() >0){
//					查询wf_task_record,是否有拆分任务未结束的  返回的结果ListSplitNotFinish应为空
//					若ListSplitNotFinish不为空，则表示有维修子任务未完成，需要去完成才能继续进行之后的操作
					
					//查询wf_task_record表中的sup_ID,
					RepairDetailsBean ListGetRepairSupId = dao.getRepairSupId(o);
					
					if(ListGetRepairSupId != null){
						
						//根据SUP_ID查询检验单IS_FINISH= 0，IS_SPLIT is not NULL的数据
						List<RepairDetailsBean> listGetSplitNotFinish = dao.getSplitNotFinish(ListGetRepairSupId.getSupId());
						
						if (listGetSplitNotFinish != null && listGetSplitNotFinish.size() >0) {
							//不为空则返回res值，进行信息提示，继续完成拆分任务，不能进行下一步
//							res = "拆分任务未完成，请继续完成拆分任务，然后在进行结束维修";
						}else{
							//查询的list为空时，进行下一步 	获得维修拆分剩余的操作数据 listOtherData
							List<RepairDetailsBean> listOtherData = dao.getSplitListByTask(o);
							if (listOtherData != null && listOtherData.size() >0 ) {
								//listOtherData不为空时，则创建检验任务
								 res = NewCheckSplitTask(o,listOtherData);//方法重写
								 //结束任务，修改任务总状态，wf_task_record中的IS_FINISH状态改为1
								 updateIsFinish(o);
							}else {
								//listOtherData为空时，则结束任务，wf_task_record中的IS_FINISH状态改为1
								updateIsFinish(o);
							}
							res = "维修任务结束";
						}
					}
					
				}else{
					res = "维修任务未结束，请继续完成维修任务";
				}
			}catch (Exception e) {
				res = "结束维修任务时，出现错误！";
			}
			return res;
		}
		
		private String addCheckSplitTask(RepairDetailsBean o, List<RepairDetailsBean> list) {
			String res = "";
			
			TaskRecordBean isTrue = trDao.checkTask(list.get(0).getSupId());//判断之前是否有过检验任务
			
			if(isTrue != null){
				String code = isTrue.getNumber();
				res = "存在维修检验任务"+code;//存在维修检验任务
				
			}else{
				// 建立任务
				TaskRecordBean bean = new TaskRecordBean();
				String number = findNumber(o);//获取随机检验单号
				bean.setNumber(number);
				bean.setOperationTime(DateTimeHelper.getNowTime());
				bean.setOperationUserId(o.getUserId());
				bean.setDefinitionId("20");
				bean.setProcessId("4");
				bean.setIsFinish("0");
				bean.setIsSplit("0");
				bean.setTaskId(list.get(0).getSupId());
				
				trDao.insert(bean);
				
				String checkId = bean.getId(); 
				
				for(RepairDetailsBean l : list){
					
					RepairDetailsBean rdBean = new RepairDetailsBean();
					
					rdBean.setModelId(l.getModelId());
					rdBean.setTaskId(checkId);
					rdBean.setRepairNum(l.getRepairNum());
					rdBean.setScrapNum(l.getScrapNum());
					rdBean.setCheckNum(l.getCheckNum());
					rdBean.setId(l.getId());
					addCheckDetails(rdBean);//添加检验数据					
					//修改維修拆分数据
					dao.updateSplitRepairNum(rdBean);
				
				}
		        res = "拆分任务建立成功";
			}
			
		
			return res;
		}

		private String NewCheckSplitTask(RepairDetailsBean o, List<RepairDetailsBean> list) {
			
			String res = "";
			try{
				// 建立任务
				TaskRecordBean bean = new TaskRecordBean();
				String number = findNumber(o);
				bean.setNumber(number);
				bean.setOperationTime(DateTimeHelper.getNowTime());
				bean.setOperationUserId(o.getUserId());
				bean.setDefinitionId("20");
				bean.setProcessId("4");
				bean.setIsFinish("0");
				bean.setTaskId(list.get(0).getSupId());
				
				trDao.insert(bean);
				
				String checkId = bean.getId(); 
				
				for(RepairDetailsBean l : list){
					
					RepairDetailsBean rdBean = new RepairDetailsBean();
					
					rdBean.setModelId(l.getModelId());
					rdBean.setTaskId(checkId);
					rdBean.setRepairNum(l.getRepairNum());
					rdBean.setScrapNum(l.getScrapNum());
					rdBean.setCheckNum(l.getCheckNum());
					rdBean.setId(l.getId());
					addCheckDetails(rdBean);//添加检验数据
					res = "添加检验数据成功";
				}
			}catch (Exception e) {
				res = "添加检验数据失败";
			}
			return res;
			
		}
		
		private String updateSplitStatus(RepairDetailsBean o, List<RepairDetailsBean> list) {
			String res = "拆分任务建立失败";
			int status = dao.updateSplitStatus(o);
			if (status > 0) {
				res = "拆分状态修改完成";//拆分状态修改完成 IS_SPLIT = 1
			}
			return res;
		}

		@Override
		public List<RepairDetailsBean> getRepairIndexList(RepairDetailsBean o) {
			
			String isFinsish = o.getIsFinsish();
			
			if("0".equals(isFinsish)){
				List<RepairDetailsBean> list = dao.getRepairIndexList(o);
				return list;
			}else{
				List<RepairDetailsBean> rslist = new ArrayList<>();
				List<RepairDetailsBean> list = dao.getRepairedIndexList(o);
				for(RepairDetailsBean rb : list){
					
					RepairDetailsBean bean = dao.getMaxRepairTime(rb);
					rb.setRepairTime(bean.getRepairTime());
					rslist.add(rb);
				}
				
				ListSort(rslist);
				
				return rslist;
			}
			
		}

		@Override
		@Transactional
		public int submitNumRepair(RepairDetailsBean o) {
		
			int res = 0;
			try{
				String isCount = o.getIsCount();
				String repairIds = "";
				//查询维修数据，匹配完成已修数据，获得repairId
				
				List<RepairDetailsBean> list = dao.getMatchRepairList(o);
				
				if(list != null && list.size() >0){
					
					repairIds = matchRepairData(o,list);
					
				}
				
				// 建立检验任务
			    String checkId = createCheckTask(o);
			
			  //添加检验数据
			    if("1".equals(isCount)){
			    	 res =  insertCheckNumData(o,checkId,repairIds);
			    }
		       
			}catch (Exception e) {
				res = -1;
				e.printStackTrace();
			}
			return res;
		}

		private void updateBackRepairStatus(RepairDetailsBean o) {
			
			int res = 0;
			try{
				   o.setRmStatus("5");
				   
				   res =  dao.updateBackStatus(o);
				   
			       	if (res == 0) {
						throw new ZeroAffectRowsException("修改维修合格rmStatus操作错误!");	
					}
		           
		           o.setBatchStatus("19");
		           
		           res = dao.updateDeviceStatus(o);
		           
		         	if (res == 0) {
						throw new ZeroAffectRowsException("修改维修合格DeviceStatus操作错误!");	
					}
			
			}catch (Exception e) {
				res = -1;
				e.printStackTrace();
			}
		
			
		}

		private String matchRepairData(RepairDetailsBean o, List<RepairDetailsBean> list) {
			int res = 0;
			String repairIds ="";
			try{
			float paraRepairNum = o.getThisRepairNum();
			String[] urls = o.getRepairUrls();
			String deviceCode = o.getDeviceCode();
			String repairPart = o.getRepairPart();
			String userId = o.getUserId();
			String maId = o.getMaId();
			String typeId = o.getModelId();
			
			for(RepairDetailsBean rb : list){
				
				String repairId = rb.getId();
				String taskId = rb.getTaskId();
				String modelId = rb.getModelId();
				float paraNum = rb.getParaNum();
				
				RepairDetailsBean mtBean = new RepairDetailsBean();
				
				mtBean.setId(repairId);
				mtBean.setTaskId(taskId);
				mtBean.setModelId(modelId);
				mtBean.setUserId(userId);
				mtBean.setDeviceCode(deviceCode);
				mtBean.setRepairTime(DateTimeHelper.currentDateTime());
				mtBean.setUserIdArr(o.getUserIdArr());
				mtBean.setUserArr(o.getUserArr());
				if (Math.abs(paraRepairNum - paraNum) < 0.000001) {
					
					//修改维修合格数 al_repairNum + paraRepairNum, is_sure = 1
					mtBean.setThisRepairNum(paraRepairNum);
					mtBean.setIsSure("1");
					res =  dao.updateRepairDetails(mtBean);
					if (res == 0) {
						throw new ZeroAffectRowsException("修改维修合格数 al_repairNum + paraRepairNum操作错误!");
					}
					
					
					//插入维修记录 paraRepairNum,   TYPE = 6 , RM_STATUS = 5 ,REMARK = repairPart
					mtBean.setInfoType("6");
					mtBean.setRmStatus("5");
					mtBean.setRepairPart(repairPart);
					
					res = dao.insertRepairRecord(mtBean);
					if (res == 0) {
						throw new ZeroAffectRowsException("插入维修记录 paraRepairNum操作错误!");
					}
					
					if (urls != null) {
						for(int i = 0;i<urls.length;i++){
							mtBean.setMaId(maId);
							mtBean.setModelId(typeId);
							mtBean.setScrapUrl(urls[i]);
							res = dao.insertFileDetails(mtBean);
						}
					}
					
					repairIds = repairId;
					break;
					
				}else if(paraNum < paraRepairNum){
					//修改维修合格数 al_repairNum + paraNum
					mtBean.setThisRepairNum(paraNum);
					
					res =  dao.updateRepairDetails(mtBean);
					if (res == 0) {
						throw new ZeroAffectRowsException("修改维修合格数 al_repairNum + paraRepairNum操作错误!");
					}
					
					//插入维修记录paraNum
					
					mtBean.setInfoType("6");
					mtBean.setRmStatus("5");
					mtBean.setRepairPart(repairPart);
					
					res = dao.insertRepairRecord(mtBean);
					
					if (res == 0) {
						throw new ZeroAffectRowsException("插入维修记录paraNum操作错误!");
					}

					if (urls != null) {
						for(int i = 0;i<urls.length;i++){
							mtBean.setMaId(maId);
							mtBean.setModelId(typeId);
							mtBean.setScrapUrl(urls[i]);
							res = dao.insertFileDetails(mtBean);
						}
					}
					
					paraRepairNum = paraRepairNum - paraNum;
					repairIds = repairId +",";
				}else if(paraNum > paraRepairNum){
					
					//修改维修合格数 al_repairNum + paraRepairNum
	                mtBean.setThisRepairNum(paraRepairNum);
					
					res =  dao.updateRepairDetails(mtBean);
					if (res == 0) {
						throw new ZeroAffectRowsException("修改维修合格数 al_repairNum + paraRepairNum操作错误!");
					}
					
					//插入维修记录paraNum
					mtBean.setInfoType("6");
					mtBean.setRmStatus("5");
					mtBean.setRepairPart(repairPart);
					
					res = dao.insertRepairRecord(mtBean);

					if (res == 0) {
						throw new ZeroAffectRowsException("插入维修记录paraNum操作错误!");
					}
					
					if (urls.length>0) {
						for(int i = 0;i<urls.length;i++){
							mtBean.setMaId(maId);
							mtBean.setModelId(typeId);
							mtBean.setScrapUrl(urls[i]);
							res = dao.insertFileDetails(mtBean);
						}
					}
					
					repairIds = repairId;
					break;
				}else{
					//未匹配到数据
				}
			}
			
			return repairIds;
			
			}catch (Exception e) {
				res = -1;
				e.printStackTrace();
			}
			return repairIds;
			
		}

	
		private int insertCheckNumData(RepairDetailsBean o, String checkId,String repairIds) {
			int res = 0;
			try{
			String checkNum = "";
			String scrapNum = "";
			RepairCheckDetailsBean rcBean = new RepairCheckDetailsBean();
			rcBean.setTaskId(checkId);
			rcBean.setModelId(o.getModelId());
			rcBean.setOperationTime(DateTimeHelper.getNowTime());
			rcBean.setRepairId(repairIds);
			scrapNum = o.getThisScrapNum()+"";
			checkNum = o.getThisRepairNum()+"";
			rcBean.setCheckNum(checkNum);
			rcBean.setScrapNum(scrapNum);
			res = rcDao.insert(rcBean);

			if (res == 0) {
				throw new ZeroAffectRowsException("插入维修记录paraNum操作错误!");
			}	
			}catch (Exception e) {
				res = -1;
				e.printStackTrace();
			}
			
			return res;
			
			
		}

		private String createCheckTask(RepairDetailsBean o) {
			 int res = 0;
		     String checkId = "";
				try{
				TaskRecordBean bean = new TaskRecordBean();
				String number = findNumber(o);
				bean.setNumber(number);
				bean.setOperationTime(DateTimeHelper.getNowTime());
				bean.setOperationUserId(o.getUserId());
				bean.setDefinitionId("20");
				bean.setProcessId("4");
				bean.setIsFinish("0");
				
				res = trDao.insert(bean);
	 			if (res == 0) {
					throw new ZeroAffectRowsException("插入维修记录paraNum操作错误!");
				}	
	 			
	             checkId = bean.getId(); 
				}catch (Exception e) {
	 				res = -1;
	 			}
             return checkId;
			
		}

		@Override
		public List<RepairDetailsBean> findRepairCodeList(RepairDetailsBean o) {
			
			return dao.findRepairCodeList(o);
		}

		@Override
		@Transactional
		public int submitCodeRepair(RepairDetailsBean o) {
			 int res = 0;
			try{
				String isCount = o.getIsCount();
				String repairIds = "";
				String[] urls = o.getRepairUrls();
				String userIdArr = o.getUserIdArr();
				String userArr = o.getUserArr();
				
				//查询维修数据，匹配完成已修数据
				
				List<RepairDetailsBean> list = dao.getMatchRepairList(o);
				
				if(list != null && list.size() >0){
					
					RepairDetailsBean [] arr = o.getArr();
					
					for(int i=0; i< arr.length; i++){
						//修改退料机具状态和机具状态
						updateBackRepairStatus(arr[i]);
						arr[i].setUserArr(userArr);
						arr[i].setUserIdArr(userIdArr);
						arr[i].setRepairUrls(urls);
						repairIds += matchRepairData(arr[i],list);
						
					}
					
				}
				res = 1;
			}catch (Exception e) {
				logger.error(e.toString(), e);
 				res = -1;
 			}
			return res;
		}

		@Override
		@Transactional
		public int splitCodeRepair(RepairDetailsBean o) {
			 int res = 0;
			 try{
		    	 res =  insertCheckCodeData(o);
		     }catch (Exception e) {
				res = -1;
			}
			return res;
		}

		private int insertCheckCodeData(RepairDetailsBean o) {
			//查询设备提交数据
			
			List<RepairDetailsBean> list = dao.getRepairedCodeList(o);
			
			RepairDetailsBean bean = dao.getNumByModelId(o);
			if(list != null  ){
				 // 建立检验任务
			     String checkId = createCheckTask(o);
			     
			     insertCheckCodeData(bean,list,checkId);
			     
			}
			return 1;
		}

		private int insertCheckCodeData(RepairDetailsBean rdBean,List<RepairDetailsBean> l, String checkId) {
			int res = 0;
			 try{
			String repairIds = "";
			
			String checkNum = rdBean.getCheckNum();
			String scrapNum = rdBean.getScrapNum();
			
			for(RepairDetailsBean rb : l){
				
				String repairId = rb.getTaskId();
				
				rb.setCheckId(checkId);
				rb.setThisRepairNum(1);
				
				rb.setInfoType("8");
				dao.updateIsSplitCode(rb);
				
				int info = dao.insertCheckRecord(rb);
				
				if (info == 0){
					throw new ZeroAffectRowsException("生成wf_info_record数据");
				}
				
				repairIds = repairId +",";
		      
			}
			
			
			RepairCheckDetailsBean rcBean = new RepairCheckDetailsBean();
			rcBean.setTaskId(checkId);
			rcBean.setModelId(l.get(0).getModelId());
			rcBean.setOperationTime(DateTimeHelper.getNowTime());
			rcBean.setRepairId(repairIds);
			
			rcBean.setCheckNum(checkNum+"");
			rcBean.setScrapNum(scrapNum+"");
			int check = rcDao.insertCheckDetails(rcBean);
			if (check == 0) {
				throw new ZeroAffectRowsException("创建检验单错误");
			}
			}catch (Exception e) {
					res = -1;
			}
			return res;
			
			
		}

		@Override
		@Transactional
		public void submitNumScrap(RepairDetailsBean o) {
			int res = 0;
			try{
				String isCount = o.getIsCount();
				String repairIds = "";
				
				//查询维修数据，匹配完成已修数据，获得repairId
				
				List<RepairDetailsBean> list = dao.getMatchRepairList(o);
				
				if(list != null && list.size() >0){
					
					repairIds = matchScrapData(o,list);
					
				}
				
				// 建立检验任务
			    String checkId = createCheckTask(o);
			
			  //添加检验数据
			    if("1".equals(isCount)){
			    	 res =  insertCheckNumData(o,checkId,repairIds);
			    }
		       
			}catch (Exception e) {
				res = -1;
			}
			
		}

		private String matchScrapData(RepairDetailsBean o, List<RepairDetailsBean> list) {
			int res = 0;
			
			float paraRepairNum = o.getThisScrapNum();
		
			String deviceCode = o.getDeviceCode();
			
			String scrapUrl = o.getScrapUrl();
			String userId = o.getUserId();
			String repairPart = o.getScrapReson();
			
			String repairIds ="";
			for(RepairDetailsBean rb : list){
				
				String repairId = rb.getId();
				String taskId = rb.getTaskId();
				String modelId = rb.getModelId();
			
				float paraNum = rb.getParaNum();
				
				RepairDetailsBean mtBean = new RepairDetailsBean();
				mtBean.setUserId(userId);
				mtBean.setId(repairId);
				mtBean.setTaskId(taskId);
				mtBean.setModelId(modelId);
				mtBean.setDeviceCode(deviceCode);
				mtBean.setScrapUrl(scrapUrl);
				
				mtBean.setRepairTime(DateTimeHelper.currentDateTime());
				if (Math.abs(paraRepairNum - paraNum) < 0.000001) {
					
					//修改维修报废数据 scrap_num + paraRepairNum, is_sure = 1
					mtBean.setThisScrapNum(paraRepairNum);
					mtBean.setIsSure("1");
					res =  dao.updateRepairDetails(mtBean);
					
					if (res == 0) {
						throw new ZeroAffectRowsException("修改维修报废数据 scrap_num + paraRepairNum错误");
					}
				
					
					//插入维修记录 scrap_num,   TYPE = 6 , RM_STATUS = 6 ,REMARK = repairPart
					mtBean.setInfoType("6");
					mtBean.setRmStatus("6");
					mtBean.setRepairPart(repairPart);
					
					res = dao.insertRepairRecord(mtBean);
					
					if (res == 0) {
						throw new ZeroAffectRowsException("插入维修记录 scrap_num错误");
					}
				
					repairIds = repairId;
					break;
					
				}else if(paraNum < paraRepairNum){
					//修改维修合格数 scrap_num + paraNum
					mtBean.setThisScrapNum(paraNum);
					
					res =  dao.updateRepairDetails(mtBean);
					
					if (res == 0) {
						throw new ZeroAffectRowsException("修改维修报废数据 scrap_num + paraNum错误");
					}
				
					//插入维修记录paraNum
					
					mtBean.setInfoType("6");
					mtBean.setRmStatus("6");
					mtBean.setRepairPart(repairPart);
					
					res = dao.insertRepairRecord(mtBean);
					
					if (res == 0) {
						throw new ZeroAffectRowsException("插入维修记录 scrap_num错误");
					}
				
					paraRepairNum = paraRepairNum - paraNum;
					repairIds = repairId +",";
				}else if(paraNum > paraRepairNum){
					
					//修改维修合格数 scrap_num + paraRepairNum
	                mtBean.setThisScrapNum(paraRepairNum);
					
					res =  dao.updateRepairDetails(mtBean);
					
					if (res == 0) {
						throw new ZeroAffectRowsException("修改维修报废数据 scrap_num + paraNum错误");
					}
				
					//插入维修记录paraRepairNum
					mtBean.setInfoType("6");
					mtBean.setRmStatus("6");
					mtBean.setRepairPart(repairPart);
					
					res = dao.insertRepairRecord(mtBean);
					
					if (res == 0) {
						throw new ZeroAffectRowsException("插入维修报废记录 scrap_num错误");
					}
				
					repairIds = repairId;
					break;
				}else{
					//未匹配到数据
				}
				
			
			}
			return repairIds;
		}

		@Override
		@Transactional
		public int submitCodeScrap(RepairDetailsBean o) {
			 int res = 0;
				try{
					String isCount = o.getIsCount();
					String repairIds = "";
					String repairPart = o.getScrapReason();
					String scrapUrl = o.getScrapUrl();
					
					//查询维修数据，匹配完成已修数据，获得repairId
					
					List<RepairDetailsBean> list = dao.getMatchRepairList(o);
					
					if(list != null && list.size() >0){
						
						RepairDetailsBean [] arr = o.getArr();
						
						for(int i=0; i< arr.length; i++){
							//修改退料机具状态和机具状态
							arr[i].setScrapUrl(scrapUrl);
							arr[i].setRepairPart(repairPart);
							updateBackScrapStatus(arr[i]);
							
							repairIds += matchScrapData(arr[i],list);
							
						}
						
					}
					
				}catch (Exception e) {
	 				res = -1;
	 			}
				return res;
		}

		private int updateBackScrapStatus(RepairDetailsBean o) {
			int res = 0;
			try{
			   o.setRmStatus("6");
		       
			   res = dao.updateBackStatus(o);
			   
				if (res == 0) {
					throw new ZeroAffectRowsException("修改维修报废RmStatus错误");
				}
	           
	           o.setBatchStatus("10");
	           
		   		if (res == 0) {
					throw new ZeroAffectRowsException("修改设备报废batchStatus错误");
				}
	           
	           dao.updateDeviceStatus(o);
			    }catch (Exception e) {
	 				res = -1;
	 			}
			return res;
			
		}
		
		@Override
		public List<RepairDetailsBean> findRepairCodeListFinish(RepairDetailsBean o) {
			
			return dao.findRepairCodeListFinish(o);
		}

		@Override
		public List<RepairDetailsBean> getRepairedNumList(RepairDetailsBean o) {
			// TODO Auto-generated method stub
			return dao.getRepairedNumList(o);
		}
		
		@Override
		public List<RepairDetailsBean> getRepairDetails(RepairDetailsBean o) {
			
			return dao.getRepairDetails(o);
		}

		@Override
		public List<RepairDetailsBean> getRepairMan(RepairDetailsBean o) {
			return dao.getRepairMan(o);
		}
		
		private void ListSort(List<RepairDetailsBean> list) {
	        Collections.sort(list, new Comparator<RepairDetailsBean>() {
	            @Override
	            //定义一个比较器
	            public int compare(RepairDetailsBean o1, RepairDetailsBean o2) {
	                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                try {
	                	if (StringHelper.isEmpty(o1.getRepairTime())) {
							return 1;
						}else if (StringHelper.isEmpty(o2.getRepairTime())) {
							return -1;
						}else {
		                    Date dt1 = format.parse(o1.getRepairTime());
		                    Date dt2 = format.parse(o2.getRepairTime());
		                    if (dt2.getTime() > dt1.getTime()) {
		                        return 1;
		                    } else if (dt2.getTime() < dt1.getTime()) {
		                        return -1;
		                    } else {
		                        return 0;
		                    }
						}
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                return 0;
	            }
	        });
		}
		
		
}


