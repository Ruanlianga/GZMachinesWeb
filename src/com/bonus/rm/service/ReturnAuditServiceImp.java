package com.bonus.rm.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.bm.beans.LogBean;
import com.bonus.bm.dao.LogDao;
import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.lease.beans.AgreementBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.newSettlement.dao.MaTypeProjectStorageDao;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.repair.dao.RepairDetailsDao;
import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.rm.beans.ReturnAuditBean;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.dao.PutInStorageTaskDao;
import com.bonus.rm.dao.ReturnAuditDao;
import com.bonus.scrap.beans.ScrapDetailsBean;
import com.bonus.scrap.dao.ScrapDetailsDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("returnAudit")
public class ReturnAuditServiceImp extends BaseServiceImp<ReturnAuditBean> implements ReturnAuditService {

	@Autowired
	ReturnAuditDao dao;

	@Autowired
	TaskRecordDao trDao;

	@Autowired
	PutInStorageTaskDao psDao;

	@Autowired
	MachineTypeDao mtDao;

	@Autowired
	MachineDao maDao;

	@Autowired
	RepairDetailsDao rdDao;

	@Autowired
	ScrapDetailsDao sdDao;
	
	@Autowired
	LogDao logDao;
	

	@Autowired
	MaTypeProjectStorageDao storageDao;
	
	
	@Override
	public Page<ReturnAuditBean> findAuditByPage(ReturnAuditBean o, Page<ReturnAuditBean> page) {
		page.setResults(dao.findAuditByPage(o, page));
		return page;
	}

	/**
	 * 审核 -- 1.判断该批退料机具的状态 2.根据不同状态建立不同的任务
	 */
	@Override
	public String isExamine(ReturnAuditBean o) {
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();
			o.setExamineUser(userId+"");
			dao.isExamine(o);
			return "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return "-1";
		}
	}
	
	/**
	 * 审核 -- 1.判断该批退料机具的状态 2.根据不同状态建立不同的任务
	 */
	@Override
	public String isApproval(ReturnAuditBean o) {
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();
			o.setApprovalUser(String.valueOf(userId));
			dao.isApproval(o);
			o.setOperationTime(DateTimeHelper.getNowTime());
			buildTask(o);
			return "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return "-1";
		}
	}

	/**
	 * 建立维修或报废任务
	 */
	public void buildTask(ReturnAuditBean o) {
		// 根据任务查询维修报废数量
		ReturnAuditBean bean = dao.findDeviceNums(o);
		if(bean != null){
			if (StringHelper.isNotEmpty(bean.getRepairNum()) && !"0".equals(bean.getRepairNum())) {
				RepairDetailsBean rdBean = new RepairDetailsBean();
				rdBean.setTaskId(o.getTaskId());
				rdBean.setModelId(o.getModelId());
				rdBean.setRepairNum(bean.getRepairNum());
				buildRepairTask(rdBean);
			}
			if (StringHelper.isNotEmpty(bean.getNum()) && !"0".equals(bean.getNum())) {
				
				PutInStorageTaskBean piBean = new PutInStorageTaskBean();
				piBean.setSupId(o.getId());
				piBean.setTaskId(o.getTaskId());
				piBean.setMaModelId(o.getModelId());
				piBean.setPrePutNum(bean.getNum());
				buildPutTask(piBean);
			}
			if (StringHelper.isNotEmpty(bean.getScrapNum()) && !"0".equals(bean.getScrapNum())) {
				ScrapDetailsBean sdBean = new ScrapDetailsBean();
				sdBean.setSupId(o.getId());
				sdBean.setTaskId(o.getTaskId());
				sdBean.setScrapChecker(o.getCheckerId());
				sdBean.setModelId(o.getModelId());
				sdBean.setScrapNum(bean.getScrapNum());
				buildScrapTask(sdBean);
			}
		}
	}

	private void buildPutTask(PutInStorageTaskBean piBean) {
	
			TaskRecordBean bean = new TaskRecordBean();
			bean.setDefinitionId("11");
			bean.setIsFinish("0");
			bean.setTaskId(piBean.getTaskId());
			bean.setProcessId("3");
			String userId = UserShiroHelper.getRealCurrentUser().getId() + "";
			bean.setOperationUserId(userId);
			bean.setRemark(piBean.getRemark());
			bean.setOperationTime(DateTimeHelper.getNowTime());
			trDao.insert(bean);
			piBean.setTaskId(bean.getId());
			addTaskDetails(piBean);
			
			List<ReturnAuditBean> putList = dao.findPutCodebyInfo(piBean);
			
			
			for(ReturnAuditBean putBean : putList){
				String isCount = putBean.getIsCount();
				if (isCount.equals("0")) {
					putBean.setOperationTime(DateTimeHelper.getNowTime());
					putBean.setRmStatus("12");
					putBean.setType("3");
					putBean.setTaskId(bean.getId());
					putBean.setNum("1");
					int res = dao.insertInfo(putBean);
					if (res == 0) {
						throw new ZeroAffectRowsException("生成修试入库数据失败");
					}
				}
			}
			
	}
	
	private void addTaskDetails(PutInStorageTaskBean o) {
		o.setPutTime(DateTimeHelper.getNowTime());
		
		PutInStorageTaskBean ptb = psDao.getRepeatData(o);
		if(ptb !=null){
			logger.error("入库插入重复"+o.getTaskId()+"-"+o.getMaModelId());
		}else{
			psDao.insert(o);
		}
		
	}
	// 建立维修任务
	public void buildRepairTask(RepairDetailsBean o) {
		// 判断该退料任务下有无维修任务
		TaskRecordBean bean = new TaskRecordBean();
		bean.setTaskId(o.getTaskId());
		bean.setDefinitionId("18");
		// 查询领料任务
		List<TaskRecordBean> list = trDao.findRepairTask(bean);
		if (list != null && list.size() > 0) {
			// 添加明细
			o.setTaskId(list.get(0).getTaskId());
			o.setOperationTime(DateTimeHelper.getNowTime());
			
			RepairDetailsBean rb = rdDao.getRepeatData(o);
			if(rb!=null){
				logger.error("维修插入重复"+o.getTaskId()+"-"+o.getModelId());
			}else{
				rdDao.insert(o);
			}
			
			
		} else {
			// 建立任务
			addRepairTask(o);
		}

	}

	// 任务总表添加维修任务
	public void addRepairTask(RepairDetailsBean o) {
		String userId = UserShiroHelper.getRealCurrentUser().getId() + "";
		TaskRecordBean bean = new TaskRecordBean();
		String number = findNumber(o);
		bean.setNumber(number);
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(userId);
		bean.setDefinitionId("18");
		bean.setProcessId("4");
		bean.setIsFinish("0");
		bean.setTaskId(o.getTaskId());
		trDao.insert(bean);
		// 添加明细
		o.setTaskId(bean.getId());
		o.setOperationTime(DateTimeHelper.getNowTime());
		RepairDetailsBean rb = rdDao.getRepeatData(o);
		if(rb!=null){
			logger.error("维修插入重复"+o.getTaskId()+"-"+o.getModelId());
		}else{
			rdDao.insert(o);
		}
	
	}

	public String findNumber(RepairDetailsBean o) {
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		String nowDay = DateTimeHelper.getFormatNowMonthAndDay();
		String operationTime = DateTimeHelper.getNowDate();
		o.setOperationTime(operationTime);
		String count = dao.findNumber(o);
		int str = Integer.parseInt(count) + 1;
		String counts = String.format("%03d", str);
		String code = "WX" + yearLast + nowDay + counts;
		return code;
	}

	
	// 建立报废任务
	public void buildScrapTask(ScrapDetailsBean o) {
		// 判断该退料任务下有无报废任务
		TaskRecordBean bean = new TaskRecordBean();
		bean.setTaskId(o.getTaskId());
		bean.setDefinitionId("19");
		// 查询报废任务
		List<TaskRecordBean> list = trDao.findRepairTask(bean);
		if (list != null && list.size() > 0) {
			// 添加明细
			o.setTaskId(list.get(0).getTaskId());
			o.setOperationTime(DateTimeHelper.getNowTime());
			
			ScrapDetailsBean ptb = sdDao.getRepeatData(o);
			if(ptb !=null){
				logger.error("报废插入重复"+o.getTaskId()+"-"+o.getModelId());
			}else{
				sdDao.insert(o);
			}
			
		} else {
			// 建立任务
			addScarpTask(o);
		}

	}

	// 任务总表添加报废任务
	public void addScarpTask(ScrapDetailsBean o) {
		String userId = UserShiroHelper.getRealCurrentUser().getId() + "";
		TaskRecordBean bean = new TaskRecordBean();
		bean.setOperationTime(DateTimeHelper.getNowTime());
		String number = findScrapNumber(o);
		bean.setNumber(number);
		bean.setOperationUserId(userId);
		bean.setDefinitionId("19");
		bean.setProcessId("4");
		bean.setIsFinish("0");
		bean.setTaskId(o.getTaskId());
		trDao.insert(bean);
		// 添加明细
		o.setTaskId(bean.getId());
		o.setOperationTime(DateTimeHelper.getNowTime());
		sdDao.insert(o);
		
		List<ReturnAuditBean> scrapList = dao.findScrapCodebyInfo(o);
		for(ReturnAuditBean scrapBean : scrapList){
			scrapBean.setOperationTime(DateTimeHelper.getNowTime());
			scrapBean.setRmStatus("3");
			scrapBean.setType("9");
			scrapBean.setTaskId(bean.getId());
			scrapBean.setNum("1");
			int res = dao.insertInfo(scrapBean);
			if (res == 0) {
				throw new ZeroAffectRowsException("生成报废数据失败");
			}
		}
	}
	
	public String findScrapNumber(ScrapDetailsBean o) {
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		String nowDay = DateTimeHelper.getFormatNowMonthAndDay();
		String operationTime = DateTimeHelper.getNowDate();
		o.setOperationTime(operationTime);
		String count = dao.findScrapNumber(o);
		int str = Integer.parseInt(count) + 1;
		String counts = String.format("%03d", str);
		String code = "BF" + yearLast + nowDay + counts;
		return code;
	}
	@Override
	public Page<ReturnAuditBean> findPutInAudit(ReturnAuditBean o, Page<ReturnAuditBean> page) {
		page.setResults(dao.findPutInAudit(o, page));
		return page;
	}

	@Override
	public String putInExamine(ReturnAuditBean o) {
		try {
			ReturnAuditBean raBean = dao.findPutInTaskId(o);
			PutInStorageTaskBean bean = new PutInStorageTaskBean();
			bean.setTaskId(raBean.getId());
			bean.setMaModelId(o.getModelId());
			
			//添加日志
			LogBean logBean= new LogBean();
			logBean.setModel("退料入库审核");
			logBean.setFun("putInExamine");
			logBean.setTask(o.getTaskId());
			logBean.setTypeId(o.getModelId());
			LogBean inBean=logDao.findInNum(logBean);
			String description="在库数:"+inBean.getInNum()+";退料数:"+o.getMaNum();
			logBean.setDescription(description);
			logBean.setTime(DateTimeHelper.currentDateTime());
			logBean.setCreator(UserShiroHelper.getRealCurrentUser().getId()+"");
			logDao.insertLog(logBean);
			
			// 修改入库审核状态
			psDao.updatePutInIsExamine(bean);
			// 修改入库任务状态
			isFinish(bean);
			// 判断是否计数
			boolean isCount = isCount(o);
			if (isCount) {
				// 不计数 1.改机具状态 2.改库存
				updateMaNum(o);
			} else {
				updateDevStatus(bean);
				updateMaNum(o);
			}
			return "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return "-1";
		}

	}

	private void updateDevStatus(PutInStorageTaskBean bean) {
		List<PutInStorageTaskBean> list = psDao.findCodeByModelId(bean);
		MachineBean maBean = new MachineBean();
		for (PutInStorageTaskBean psBean : list) {
			maBean.setTypeId(bean.getMaModelId());
			maBean.setDeviceCode(psBean.getDeviceCode());
			maBean.setBatchStatus("5");
			maDao.updateMachineStatus(maBean);
		}
	}

	private void isFinish(PutInStorageTaskBean bean) {
		List<PutInStorageTaskBean> list = psDao.findIsSure(bean);
		if (list != null && list.size() > 0) {
			return;
		} else {
			psDao.updateIsFinish(bean);
		}
	}

	private boolean isCount(ReturnAuditBean o) {
		MachineTypeBean bean = new MachineTypeBean();
		bean.setMaModelId(o.getModelId());
		MachineTypeBean mtBean = mtDao.findByModelId(bean);
		if ("0".equals(mtBean.getIsCount())) {
			return false;
		}
		return true;
	}

	/***
	 * 修改库存
	 * 
	 * @param o
	 */
	public void updateMaNum(ReturnAuditBean o) {
		MachineBean maBean = new MachineBean();
		maBean.setTypeId(o.getModelId());
		MachineBean mBean = maDao.findMachineNum(maBean);
		// 修改库存
		float sums = Float.parseFloat(mBean.getSums());
		float outNum = Float.parseFloat(o.getMaNum());
		float maNum = sums + outNum;
		DecimalFormat mFormat = new DecimalFormat(".000");
		String formatNum = mFormat.format(maNum);
		MachineTypeBean mtBean = new MachineTypeBean();
		mtBean.setId(o.getModelId());
		mtBean.setNums(formatNum);
		// 修改库存
		mtDao.update(mtBean);
	}

	@Override
	public String batchAudit(ReturnAuditBean o) {
		try {
			String ids = o.getIds();
			List<Integer> intList = getIntList(ids);
			dao.batchAudit(intList,o);
			for(Integer id : intList) {
				ReturnAuditBean os = dao.findManyId(id);
				os.setOperationTime(DateTimeHelper.getNowTime());
				buildTask(os);
			}
			
			return "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return "-1";
		}
	}

	@Override
	@Transactional
	public String putCancelExamine(ReturnAuditBean o) {
		int res = 0;
		String str = "";
		try {
			// 修改状态审核驳回
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();
			o.setExamineUser(userId+"");
			o.setIsExamine("2");
			o.setAuditRemark(o.getAuditRemark());
			res = dao.updateExStatus(o);
			if (res == 0) {
				throw new ZeroAffectRowsException("修改审核驳回状态失败!");
			}
			
			//ma_type_project_storage 退料数据修改
			res = updateStorageData(o);
			if (res == 0) {
				throw new ZeroAffectRowsException("修改ma_type_project_storage 退料数据修改失败!");
			}
			
			//退料记录变更记录
			res = updateBackRecord(o);
			if (res == 0) {
				throw new ZeroAffectRowsException("修改退料记录变更记录失败!");
			}
			
		
		} catch (Exception e) {
			logger.error(e.toString(), e);
		
		}
		if(res > 0){
			str = "1";
		}
		return str;
	}

	private int updateStorageData(ReturnAuditBean o) {
		int res = 0;
		try {
			//根据协议，机具 查询当天退料数据
			List<ReturnAuditBean> backList = dao.findBackRecord(o);
			String agreementId = o.getAgreementId();
			//修改ma_type_project_storage
			int backSize = backList.size();
			// ------------操作工程库存表,配合结算相关数据修改-------开始------------
			if (backSize > 0) {
				for (int i = 0; i < backList.size(); i++) {
					String rmNum = backList.get(i).getMaNum();
					String modelId = backList.get(i).getModelId();
					String maId = backList.get(i).getMaId();
					String backDate = backList.get(i).getBackTime();
					
					String isCount = backList.get(i).getIsCount();
					MaTypeProjectStorageBean storage = new MaTypeProjectStorageBean();
					AgreementBean agreement = new AgreementBean();
					agreement.setId(agreementId);// 协议ID
					MachineTypeBean type = new MachineTypeBean();
					type.setId(modelId);// 机具类型Id
					type.setIsCount(isCount);// 是否计数
					type.setNums(rmNum);// 数量
					storage.setType(type);
					storage.setAgreement(agreement);
					storage.setBackDate(backDate);
					if (StringHelper.isNotEmpty(maId)) {
						MachineBean mb = new MachineBean();
						mb.setId(maId);
						storage.setMachine(mb);
						// 修改机具状态
						res = dao.updateMaStatus(mb);
						if (res == 0) {
							throw new ZeroAffectRowsException("修改审核驳回机具状态失败!");
						}
					}
					Float rn = Float.parseFloat(rmNum);
					List<MaTypeProjectStorageBean> list = storageDao.findCancelBackListById(storage);
					if (list != null && list.size() > 0) {
						if (StringHelper.isNotEmpty(maId)) {
							storage = list.get(0);
							// 设状态在用
							storage.setStatus(1);
							storage.setBackDate(null);
							res = storageDao.updateCancelBean(storage);
							if (res == 0) {
								throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中machine修改操作错误!");
							}
						} else {
							int size = list.size();
							for (int j = 0; j < size; j++) {
								storage = list.get(j);
								Float num2 = storage.getNum();
								if (Math.abs(num2 - rn) < 0.000001) {
									storage.setStatus(1);
									storage.setBackDate(null);
									res = storageDao.updateCancelBean(storage);
									if (res == 0) {
										throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中工器具修改操作错误!");
									}
									break;
								} else if (num2 < rn) {
									storage.setStatus(1);
									storage.setBackDate(null);
									res = storageDao.updateBean(storage);
									if (res == 0) {
										throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中工器具修改操作错误!");
									}
									rn = rn - num2;
								} else{
									if(backSize>size){
										storage.setStatus(1);
										storage.setBackDate(null);
										res = storageDao.updateBean(storage);
										if (res == 0) {
											throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中工器具修改操作错误!");
										}
									}
								}
							}
						}
					} else {
						System.err.println("i=" + i);
						throw new ZeroAffectRowsException("确认退库任务失败,设备或工器具在工程库存表中未找到可退项列表!");
					}
					
				}
			}
		
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = -1;
		}
		return res;
	}

	private int updateBackRecord(ReturnAuditBean o) {
		int res = 0;
		try {
		
			List<ReturnAuditBean> list = dao.findBackRecord(o);
			//修改wf_info_record
			for(ReturnAuditBean bean : list){
				res = dao.updateInfoData(bean);
				if (res == 0) {
					throw new ZeroAffectRowsException("wf_info_record修改操作错误!");
				}
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = -1;
		}
		return res;
	}

	@Override
	@Transactional
	public String putCancelApproval(ReturnAuditBean o) {
		int res = 0;
		String str = "";
		try {
			// 修改状态批准驳回
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int userId = user.getId();
			o.setApprovalUser(userId+"");
			o.setIsApproval("2");
			o.setApprovalRemark(o.getApprovalRemark());
			res = dao.updateExStatus(o);
			if (res == 0) {
				throw new ZeroAffectRowsException("修改审核驳回状态失败!");
			}
			
			//ma_type_project_storage 退料数据修改
			res = updateStorageData(o);
			if (res == 0) {
				throw new ZeroAffectRowsException("修改ma_type_project_storage 退料数据修改失败!");
			}
			
			//退料记录变更记录
			res = updateBackRecord(o);
			if (res == 0) {
				throw new ZeroAffectRowsException("修改退料记录变更记录失败!");
			}
			
		
		} catch (Exception e) {
			logger.error(e.toString(), e);
		
		}
		if(res > 0){
			str = "1";
		}
		return str;
	}

}
