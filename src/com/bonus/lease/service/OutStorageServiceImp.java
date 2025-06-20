package com.bonus.lease.service;

import java.text.DecimalFormat;
import java.util.List;

import com.bonus.lease.dao.LeaseApplicationDao;
import com.bonus.plan.beans.ProNeedInfoBean;
import com.bonus.plan.dao.PlanAuditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bm.beans.LogBean;
import com.bonus.bm.dao.LogDao;
import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.beans.ReceiveDetailsBean;
import com.bonus.lease.dao.OutStorageDao;
import com.bonus.lease.dao.ReceiveDetailsDao;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.newSettlement.dao.MaTypeProjectStorageDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("out")
public class OutStorageServiceImp extends BaseServiceImp<OutStorageBean> implements OutStorageService {

	@Autowired
	OutStorageDao dao;

	@Autowired
	TaskRecordDao trDao;

	@Autowired
	MachineDao maDao;

	@Autowired
	MachineTypeDao mtDao;

	@Autowired
	ReceiveDetailsDao rdDao;

	@Autowired
	MaTypeProjectStorageDao storageDao;

	@Autowired
	private PlanAuditDao planAuditDao;

	@Autowired
	private LeaseApplicationDao leaseApplicationDao;
	
	@Autowired
	LogDao logDao;

	@Override
	public void buildAuditingTask(OutStorageBean o) {
		dao.isSure(o);
		// 修改出库任务状态
		updateIsFinish(o);
	}

	// 修改出库任务状态
	public void updateIsFinish(OutStorageBean o) {
		List<OutStorageBean> list = dao.findIsSureInfo(o);
		if (list != null && list.size() > 0) {
			return;
		} else {
			TaskRecordBean bean = new TaskRecordBean();
			bean.setId(o.getTaskId());
			bean.setIsFinish("1");
			trDao.update(bean);

		}
	}

	/*
	 * 生成审核任务
	 * 
	 * 暂时不用
	 **/
	public void addAuditingTask(OutStorageBean o) {
		OutStorageBean bean = dao.findByTaskId(o);
		TaskRecordBean trBean = new TaskRecordBean();
		trBean.setOperationTime(DateTimeHelper.getNowTime());
		trBean.setOperationUserId(o.getOutPersonId());
		trBean.setDefinitionId("6");
		trBean.setProcessId("2");
		trBean.setIsFinish("0");
		trBean.setTaskId(bean.getTaskId());
		trDao.insert(trBean);
	}

	@Override
	public String isExamine(OutStorageBean o) {
		String res = "";
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();
			o.setExamineUser(userId+"");
			dao.isExamine(o);
			//updateStatus(o);
			res = "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}

		return res;
	}

	/**
	 * 
	 * @param o
	 */
	public void updateStatus(OutStorageBean o) {
		MachineTypeBean mtBean = new MachineTypeBean();
		mtBean.setMaModelId(o.getMaModelId());
		MachineTypeBean mBean = mtDao.findByModelId(mtBean);
		if ("0".equals(mBean.getIsCount())) {
			List<OutStorageBean> list = dao.findCodeByModel(o);
			MachineBean maBean = new MachineBean();
			for (OutStorageBean bean : list) {
				maBean.setTypeId(o.getMaModelId());
				maBean.setDeviceCode(bean.getMaCode());
				maBean.setBatchStatus("15");// 出库审核通过待批准
				maDao.updateMachineStatus(maBean);
			}
		} else {
			return;
		}
	}

	@Override
	public String isApproval(OutStorageBean o) {
		String taskId = o.getTaskId();
		String maModelId = o.getMaModelId();
		Integer leaseProjectId = o.getLeaseProjectId();
		Integer planOutId = o.getPlanOutId();
		String outNum1 = o.getOutNum();
		String res = "";
		try {
			//1、数据校验
			//数据一致性校验
			o.setTaskId(taskId);
			o.setMaModelId(maModelId);
			List<OutStorageBean> sameList = dao.getSameInfo(o);
			if (sameList==null || sameList.size() <= 0) {
				return "-1";
			}
			//查询数据是否被记录
			o.setTaskId(taskId);
			o.setMaModelId(maModelId);
			List<OutStorageBean> repeatlist = dao.getRepeatInfo(o);
			if(repeatlist != null && repeatlist.size()>0) {
				return "-1";
			}
			//库存不足校验
			LogBean logBean= new LogBean();
			logBean.setModel("领料出库");
			logBean.setFun("isApproval");
			logBean.setTask(taskId);
			logBean.setTypeId(maModelId);
			LogBean inBean=logDao.findInNum(logBean);
			String inNum = inBean.getInNum();
			String outNum = outNum1;
			//增加库存不足校验
			float thisOutNum = Float.parseFloat(outNum);
			float stNum = Float.parseFloat(inNum);
			if(thisOutNum >stNum){
				return "-1";
			}
			//增加批准数和出库数校验
			o.setTaskId(taskId);
			o.setMaModelId(maModelId);
			OutStorageBean ou = dao.getPreOutInfo(o);
			String preNum = ou.getPreOutNum();
			if(!outNum.equals(preNum)) {
				return "-1";
			}

			//2、业务处理
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();

			// 需求计划的领料单--数据处理------------start----------------
			// 首先查询该ModelId物资信息
            try {
                MachineTypeBean machineTypeBean = mtDao.findById(maModelId);
                if (machineTypeBean != null) {
                    machineTypeBean.setLeaseProjectId(leaseProjectId);

                    //然后查询该物资是否有计划
                    ProNeedInfoBean planNeedInfo = planAuditDao.getProNeedInfo(String.valueOf(leaseProjectId), maModelId);
                    if (planNeedInfo != null && planNeedInfo.getId() != null) {
                        // 说明该工程有该规格设备的计划，那么直接修改发货量即可
                        planNeedInfo.setFhNum(Integer.parseInt(outNum1));
                        planNeedInfo.setModuleId(maModelId);
                        planNeedInfo.setProId(String.valueOf(leaseProjectId));
                        int updateProPlanOutNum = planAuditDao.updateProPlanOutInfo(planNeedInfo);
                        if (updateProPlanOutNum > 0) {
                            System.out.println("计划发货数量修改成功");
                        } else {
                            System.err.println("计划发货数量修改失败");
                        }
                    } else {
                        // 说明该工程没有该规格设备的计划，那么需要新增一条计划和发货信息
                        planNeedInfo = new ProNeedInfoBean();
                        planNeedInfo.setProId(String.valueOf(leaseProjectId));
						planNeedInfo.setModuleId(maModelId);
                        planNeedInfo.setType(machineTypeBean.getTypeName());
                        planNeedInfo.setName(machineTypeBean.getParentName());
                        planNeedInfo.setModule(machineTypeBean.getName());
                        planNeedInfo.setNeedType("2");
                        planNeedInfo.setUnit(machineTypeBean.getUnit());
                        planNeedInfo.setNeedNum(0);
                        planNeedInfo.setFhNum(Integer.parseInt(outNum1));
                        planNeedInfo.setTzNum(0);
                        planAuditDao.insertProNeedInfo(planNeedInfo);
                    }


					// 增加领料出库单的明细
					if (planOutId != null && planOutId > 0) {
						ProNeedInfoBean needInfoBean = new ProNeedInfoBean();
						needInfoBean.setPlanOutId(planOutId);
						needInfoBean.setModuleId(maModelId);
						needInfoBean.setType(machineTypeBean.getTypeName());
						needInfoBean.setName(machineTypeBean.getParentName());
						needInfoBean.setModule(machineTypeBean.getName());
						needInfoBean.setUnit(machineTypeBean.getUnit());
						needInfoBean.setFhNum(Integer.parseInt(outNum1));
						needInfoBean.setTzNum(0);
						needInfoBean.setRemarks(o.getRemark());
						needInfoBean.setDataType("1");
						leaseApplicationDao.insertLeaseOutOrderDetails(needInfoBean);
					}

					// 修改需求计划的整体进度
					ProNeedInfoBean planStatusBean = new ProNeedInfoBean();
					planStatusBean.setProId(String.valueOf(leaseProjectId));
					planStatusBean.setFhNum(Integer.parseInt(outNum1));
					leaseApplicationDao.updateProjectPlanOutNum(planStatusBean);
				}
            } catch (NumberFormatException e) {
				System.err.println("需求计划领料单相关的逻辑处理出错：" + e.getMessage());
            }
            // 需求计划的领料单--数据处理-------------end------------------

			o.setApprovalUser(String.valueOf(userId));
			o.setTaskId(taskId);
			o.setMaModelId(maModelId);
			dao.isApproval(o);
			
			//查询状态是否被改变
			OutStorageBean outStorageBean=new OutStorageBean();
			outStorageBean.setTaskId(taskId);
			outStorageBean.setMaModelId(maModelId);
			String isApprovalStatu=dao.getIsApprovalStatus(outStorageBean);
			if(!StringHelper.isEmptyAndNull(isApprovalStatu) && "1".equals(isApprovalStatu)) {
				o.setTaskId(taskId);
				o.setMaModelId(maModelId);
				updateDevStatus(o);
				updateAlCollerNum(o);
				OutStorageBean bean = dao.getOutTask(o);
				if(bean != null){
					updateOutStatus(bean);
				}
				
				//3、日志记录
				String description="在库数:"+inBean.getInNum()+";出库数:"+ outNum1;
				logBean.setDescription(description);
				logBean.setTime(DateTimeHelper.currentDateTime());
				logBean.setCreator(UserShiroHelper.getRealCurrentUser().getId()+"");
				
				logDao.insertLog(logBean);
				return "1";
			}else {
				return "-1";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}
		return res;
	}

	private void updateOutStatus(OutStorageBean bean) {
		dao.updateOutStatus(bean);
		
	}

	public void updateAlCollerNum(OutStorageBean o) {
		ReceiveDetailsBean bean = new ReceiveDetailsBean();
		bean.setTaskId(o.getTaskId());
		bean.setMaModelId(o.getMaModelId());
		bean.setAlreadyCollerNum(o.getOutNum());
		rdDao.update(bean);
	}

	public void updateDevStatus(OutStorageBean o) {
	/*	MachineTypeBean mmtBean = new MachineTypeBean();
		mmtBean.setMaModelId(o.getMaModelId());
		MachineTypeBean mmBean = mtDao.findByModelId(mmtBean);
		if (mmBean.getIsCount().equals("0")) {
			List<OutStorageBean> list = dao.findCodeByModel(o);
			MachineBean maBean = new MachineBean();
			for (OutStorageBean bean : list) {
				maBean.setTypeId(o.getMaModelId());
				maBean.setDeviceCode(bean.getMaCode());
				maBean.setBatchStatus("6");
				// 改状态
				maDao.updateMachineStatus(maBean);
			}
			updateMaNum(o);
		} else {
			updateMaNum(o);
		}*/
		
		updateMaNum(o);
	}

	public void updateMaNum(OutStorageBean o) {
		MachineBean maBean = new MachineBean();
		maBean.setTypeId(o.getMaModelId());
		MachineBean mBean = maDao.findMachineNum(maBean);
		// 修改库存
		float sums = Float.parseFloat(mBean.getSums());
		float outNum = Float.parseFloat(o.getOutNum());
		float maNum = sums - outNum;
		DecimalFormat mFormat = new DecimalFormat(".000");
		String formatNum = mFormat.format(maNum);
		MachineTypeBean mtBean = new MachineTypeBean();
		mtBean.setId(o.getMaModelId());
		mtBean.setNums(formatNum);
		// 修改库存
		mtDao.update(mtBean);
	}

	@Override
	public List<OutStorageBean> getOutStorageList(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getOutStorageList(o);
	}

	@Override
	public List<OutStorageBean> getOutStorageDetailsList(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getOutStorageDetailsList(o);
	}

	@Override
	public OutStorageBean getAlOutNum(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getAlOutNum(o);
	}

	@Override
	public int updateAlOutNum(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.updateAlOutNum(o);
	}

	@Override
	public void addOutRecord(OutStorageBean o) {
		// TODO Auto-generated method stub
		dao.addOutRecord(o);
	}

	@Override
	public List<OutStorageBean> getOutInfoByNum(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getOutInfoByNum(o);
	}

	@Override
	@Transactional
	public OutStorageBean confirmOutTask(OutStorageBean o) {

		
		return dao.confirmOutTask(o);
	}

	@Override
	public List<OutStorageBean> getOutRecordList(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getOutRecordList(o);
	}

	@Override
	public Page<OutStorageBean> outStorageQuery(OutStorageBean o, Page<OutStorageBean> page) {
		// TODO Auto-generated method stub
		page.setResults(dao.outStorageQuery(o, page));
		return page;
	}

	@Override
	public Page<OutStorageBean> findApproval(OutStorageBean o, Page<OutStorageBean> page) {
		page.setResults(dao.findApproval(o, page));
		return page;
	}

	@Override
	public Integer cancelOutTask(OutStorageBean o) {
		int task = 0;
		task = dao.cancelOutTask(o);
		return task;
	}

	@Override
	public Integer updateCollarTask(OutStorageBean o) {
		int collar = 0;
		collar = dao.updateCollarTask(o);
		return collar;
	}

	@Override
	public Integer updateOutTask(OutStorageBean o) {
		int out = 0;
		out = dao.updateOutTask(o);
		return out;
	}

	@Override
	public OutStorageBean findSomeId(OutStorageBean o) {

		return dao.findSomeId(o);
	}

	@Override
	public Page<OutStorageBean> findQueryContent(Page<OutStorageBean> page, OutStorageBean o) {
		// TODO Auto-generated method stub
		page.setResults(dao.findQueryContent(page, o));
		return page;
	}

	@Override
	public List<OutStorageBean> findIsFinishById(OutStorageBean bean) {
		// TODO Auto-generated method stub
		return dao.findIsFinishById(bean);
	}

	@Override
	public void updateOutNum(OutStorageBean osbean) {
		// TODO Auto-generated method stub
		dao.updateOutNum(osbean);
	}

	@Override
	public List<OutStorageBean> findCodeBySupIdAndModId(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.findCodeBySupIdAndModId(o);
	}


	@Override
	@Transactional
	public void addStorageData(OutStorageBean o) {
		// ----------------------------操作工程库存表,配合结算相关数据修改-------开始---------------------------
				List<MaTypeProjectStorageBean> waitList = rdDao.findProjectStorageListByTaskId(o);
				if (waitList != null && waitList.size() > 0) {
					int size = waitList.size();
					MaTypeProjectStorageBean temp = null;
					for (int i = 0; i < size; i++) {
						temp = waitList.get(i);
						int result = storageDao.insertBean(temp);
						if (result == 0) {
							throw new ZeroAffectRowsException("结束失败,工程库存插入操作失败!");
						}
						temp = null;
					}

				} else {
					throw new ZeroAffectRowsException("结束失败,工程待入库列表获取失败!");
				}
				// ----------------------------操作工程库存表,配合结算相关数据修改-------结束---------------------------
	}

	@Override
	@Transactional
	public String rejectExamine(OutStorageBean o) {
		String res = "";
		int result = 0;
		try {
			//1修改审核状态
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();
			o.setExamineUser(userId+"");
			o.setIsExamine("2");
			o.setAuditRemark(o.getAuditRemark());
			result = dao.updateExaStatus(o);
			if (result == 0) {
				throw new ZeroAffectRowsException("修改审核状态操作失败!");
			}
			//2删除发布领料任务
			OutStorageBean bean = dao.findOutTaskInfo(o);
			if(bean != null){
				
				result = dao.delOutTask(bean);
				if (result == 0) {
					throw new ZeroAffectRowsException("修改出库任务操作失败!");
				}
				
				result = dao.delOutDetail(bean);
				if (result == 0) {
					throw new ZeroAffectRowsException("修改出库任务数据操作失败!");
				}
				
			}
			res = "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}

		return res;
	}

	@Override
	@Transactional
	public String rejectApproval(OutStorageBean o) {
		String res = "";
		int result = 0;
		try {
			//1修改审核状态
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();
			o.setApprovalUser(userId+"");
			o.setIsApproval("2");
		    o.setApprovalRemark(o.getApprovalRemark());
			result = dao.updateExaStatus(o);
			if (result == 0) {
				throw new ZeroAffectRowsException("修改审核状态操作失败!");
			}
			//2删除发布领料任务
			OutStorageBean bean = dao.findOutTaskInfo(o);
			if(bean != null){
				
				result = dao.delOutTask(bean);
				if (result == 0) {
					throw new ZeroAffectRowsException("修改出库任务操作失败!");
				}
				
				result = dao.delOutDetail(bean);
				if (result == 0) {
					throw new ZeroAffectRowsException("修改出库任务数据操作失败!");
				}
				
			}
			res = "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}

		return res;
	}

	@Override
	public Page<OutStorageBean> findBackApproval(OutStorageBean o, Page<OutStorageBean> page) {
		// TODO Auto-generated method stub
		page.setResults(dao.findBackApproval(o, page));
		return page;
	}

	@Override
	public String isBackApproval(OutStorageBean o) {
		UserBean user = UserShiroHelper.getRealCurrentUser();
		int userId = user.getId();
		
		List<OutStorageBean> list=dao.findAlredyCollarNumList(o);
		
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String alredyCollarNum=list.get(i).getAlredyCollarNum();
				String id=list.get(i).getId();
				String taskId = list.get(i).getTaskId();
				//等于null
				if("0".equals(alredyCollarNum)){
					dao.addBackRecord(o.getTaskId(),o.getMaModelId(),null,o.getOutNum(),o.getOperationTime(),userId);
				}else{
					
					//修改app已操作的领料单
					updateDoneRecord(id,userId,o,taskId);

				}
		
			
				//删除wf_collar_details 领料详情
				dao.delCollarDetaiils(o);
		
				//删除wf_task_record 出库任务
				dao.delCollarTask(taskId);
		
				//删除wf_ma_outstock  删除app出库记录
				dao.delOutStockInfo(id);
			
				//修改mm_type 修改机具数量
				String num=dao.findTypeNum(o.getMaModelId());
				float nums=Float.parseFloat(num) + Float.parseFloat(o.getOutNum());
				dao.updateTypeNum(nums+"",o.getMaModelId());
		
				//删除bm_logs
				dao.delBmLogs(o);
			}
		}	
			
		return "1";
	}

	public void updateDoneRecord(String id,int userId,OutStorageBean o,String taskId) {
		List<OutStorageBean> listInfo=dao.findInfoRecord(taskId);
		if(listInfo.size()>0){
			for(int j=0;j<listInfo.size();j++){
				if(listInfo.get(j) != null){
					if(listInfo.get(j).getMaId() !=null){
						String maId=listInfo.get(j).getMaId();
						
						//修改mm_machine 修改机具状态
						dao.updateMachinesBatchStats(maId);
						
						
						dao.addBackRecord(o.getTaskId(),o.getMaModelId(),maId,o.getOutNum(),o.getOperationTime(),userId);
					}
				}else{
					dao.addBackRecord(o.getTaskId(),o.getMaModelId(),null,o.getOutNum(),o.getOperationTime(),userId);
				}
				
			}
		}
		
		//删除wf_info_record  删除机具详情
		dao.delInfoRecord(taskId,o.getMaModelId());
	}

	@Override
	public void updateStorageNum(OutStorageBean r) {
		dao.updateStorageNum(r);
		
	}

}
