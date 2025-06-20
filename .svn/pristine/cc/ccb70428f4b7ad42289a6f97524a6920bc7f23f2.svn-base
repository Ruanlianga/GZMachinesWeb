package com.bonus.repairCheck.service;

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
import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.dao.PutInStorageTaskDao;
import com.bonus.rm.dao.ReturnMaterialDetailsDao;
import com.bonus.scrap.beans.ScrapDetailsBean;
import com.bonus.scrap.dao.ScrapDetailsDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.dao.UserDao;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("repairCheck")
public class RepairCheckDetailsServiceImp extends BaseServiceImp<RepairCheckDetailsBean>
		implements RepairCheckDetailsService {

	@Autowired
	UserDao uDao;

	@Autowired
	RepairCheckDetailsDao dao;

	@Autowired
	MachineDao maDao;

	@Autowired
	ReturnMaterialDetailsDao rmDao;

	@Autowired
	TaskRecordDao trDao;

	@Autowired
	PutInStorageTaskDao psDao;

	@Autowired
	ScrapDetailsDao sdDao;

	@Autowired
	RepairDetailsDao rdDao;

	@Override
	public List<RepairCheckDetailsBean> findCheckTask(RepairCheckDetailsBean o) {
		List<RepairCheckDetailsBean> list = dao.findCheckTask(o);
		return list;
	}

	@Override
	public List<RepairCheckDetailsBean> findCheckTaskDetails(RepairCheckDetailsBean o) {
		List<RepairCheckDetailsBean> list = dao.findCheckTaskDetails(o);
		return list;
	}

	@Override
	public List<RepairCheckDetailsBean> findCheckDevice(RepairCheckDetailsBean o) {
		List<RepairCheckDetailsBean> list = dao.findCheckDevice(o);
		return list;
	}

	@Override
	public void checkOperation(RepairCheckDetailsBean o) {
		String taskId = o.getTaskId();
		String id = o.getId();
		if ("0".equals(o.getIsCount())) {
			// 不计数1.修改检验数量和任务状态2.修改机具状态3.添加检验明细4.修改检验任务状态
			// 查询机具的状态1.repairTaskId , moldeId, deviceCode
			deviceCodeCheck(o);
			o.setTaskId(taskId);
			o.setId(id);
			updateIsFinish(o);
		} else {
			// 计数1.生成任务2.添加检验明细3.修改检验数量
			if (StringHelper.isNotEmpty(o.getRepairQualifiedNum()) && !"0".equals(o.getRepairQualifiedNum())) {
				// 生成维修入库任务
				//判断第几次检验
				o.setTaskId(taskId);
				o.setId(id);
				RepairCheckDetailsBean rcdBean = dao.findDefinitionId(o);
				if ("24".equals(rcdBean.getDefinitionId())) {
					addReturnRepairInputTask(o);
				}else{
					addRepairInputTask(o);
				}
				// 添加检验明细
			} 
			if ((StringHelper.isNotEmpty(o.getRepairUnqualifiedNum()) && !"0".equals(o.getRepairUnqualifiedNum()))
					|| (StringHelper.isNotEmpty(o.getScrapUnqualifiedNum()) && !"0".equals(o.getScrapUnqualifiedNum()))) {
				// 重新生成维修任务
				o.setTaskId(taskId);
				o.setId(id);
				addRepairTask(o);
			} 
			if (StringHelper.isNotEmpty(o.getScrapQualifiedNum()) && !"0".equals(o.getScrapQualifiedNum())) {
				// 生成维修报废任务
				//判断第几次检验
				o.setTaskId(taskId);
				o.setId(id);
				RepairCheckDetailsBean rcdBean = dao.findDefinitionId(o);
				if ("24".equals(rcdBean.getDefinitionId())) {
					addReturnScrapTask(o);
				}else{
					addScrapTask(o);
				}
			}
			o.setTaskId(taskId);
			o.setId(id);
			addCheckDetails(o);
			o.setTaskId(taskId);
			o.setId(id);
			updateCheckNum(o);
			o.setTaskId(taskId);
			o.setId(id);
			updateIsFinish(o);
		}

	}

	public void deviceCodeCheck(RepairCheckDetailsBean o) {
		String taskId = o.getTaskId();
		String id = o.getId();
		RepairCheckDetailsBean bean = dao.findDeviceRmstatus(o);
		if ("5".equals(bean.getRmStatus()) && "1".equals(o.getIsQualified())) {
			//判断是第几次检验
			RepairCheckDetailsBean rcdBean = dao.findDefinitionId(o);
			if ("24".equals(rcdBean.getDefinitionId())) {
				addReturnRepairInputTask(o);
			}else{
				addRepairInputTask(o);
			}
			o.setBatchStatus("9");// 维修后检验合格 待入库
			updateDevStatus(o);// 修改机具状态
			o.setRmStatus("8");
			o.setTaskId(taskId);
			o.setId(id);
			addCheckDetails(o);// 添加检验明细
			o.setTaskId(taskId);
			o.setId(id);
			updateCheckNum(o);// 修改检验数量
		} 
		if ("6".equals(bean.getRmStatus()) && "1".equals(o.getIsQualified())) {
			//判断第几次检验
			RepairCheckDetailsBean rcdBean = dao.findDefinitionId(o);
			if ("24".equals(rcdBean.getDefinitionId())) {
				addReturnScrapTask(o);
			}else{
				addScrapTask(o);
			}
			o.setBatchStatus("22");// 维修报废检验合格 待报废
			updateDevStatus(o);// 修改机具状态
			o.setRmStatus("10");
			o.setTaskId(taskId);
			o.setId(id);
			addCheckDetails(o);// 添加检验明细
			o.setTaskId(taskId);
			o.setId(id);
			updateCheckNum(o);// 修改检验数量
		}
		if (("6".equals(bean.getRmStatus()) && "0".equals(o.getIsQualified()))
				|| ("5".equals(bean.getRmStatus()) && "0".equals(o.getIsQualified()))) {
			addRepairTask(o);
			o.setBatchStatus("23");// 维修检验不通过 重新维修
			updateDevStatus(o);// 修改机具状态
			o.setRmStatus("9");
			o.setTaskId(taskId);
			o.setId(id);
			addCheckDetails(o);// 添加检验明细
			o.setTaskId(taskId);
			o.setId(id);
			updateCheckNum(o);// 修改检验数量
		}

	}

	// 建立检验入库任务 1.taskId2.supId,modelId
	public void addRepairInputTask(RepairCheckDetailsBean o) {
		// 判断该退料任务下有无维修检验合格任务
		TaskRecordBean bean = new TaskRecordBean();
		bean.setTaskId(o.getSupId());
		bean.setDefinitionId("21");
		// 查询是否有任务
		List<TaskRecordBean> list = trDao.findRepairTask(bean);
		if (list != null && list.size() > 0) {
			if ("0".equals(o.getIsCount())) {
				// 根据检验id查询现有入库数量
				RepairCheckDetailsBean rcBean = dao.findInputNumById(o);
				if (rcBean != null) {
					float inputNum = Float.parseFloat(rcBean.getInputNum());
					float repairQualifiedNum = inputNum + 1;
					PutInStorageTaskBean psBean = new PutInStorageTaskBean();
					psBean.setPrePutNum(repairQualifiedNum + "");
					psBean.setCheckId(o.getId());
					psBean.setMaModelId(o.getModelId());
					psDao.updateInputNum(psBean);
				}else{
					o.setRepairQualifiedNum("1");
					o.setTaskId(list.get(0).getTaskId());
					addRepairInputDetails(o);
				}
				
			} else {
				o.setTaskId(list.get(0).getTaskId());
				addRepairInputDetails(o);
			}
		} else {
			// 建立任务
			if ("0".equals(o.getIsCount())) {
				o.setRepairQualifiedNum("1");
				buildRepairInputTask(o);
			} else {
				buildRepairInputTask(o);
			}
		}
	}
	
	// 建立检验入库任务 1.taskId2.supId,modelId
		public void addReturnRepairInputTask(RepairCheckDetailsBean o) {
			// 判断该退料任务下有无维修检验合格任务
			TaskRecordBean bean = new TaskRecordBean();
			bean.setTaskId(o.getSupId());
			bean.setDefinitionId("25");//二次检验入库
			// 查询是否有任务
			List<TaskRecordBean> list = trDao.findRepairTask(bean);
			if (list != null && list.size() > 0) {
				if ("0".equals(o.getIsCount())) {
					// 根据检验id查询现有入库数量
					RepairCheckDetailsBean rcBean = dao.findInputNumById(o);
					if (rcBean != null) {
						float inputNum = Float.parseFloat(rcBean.getInputNum());
						float repairQualifiedNum = inputNum + 1;
						PutInStorageTaskBean psBean = new PutInStorageTaskBean();
						psBean.setPrePutNum(repairQualifiedNum + "");
						psBean.setCheckId(o.getId());
						psBean.setMaModelId(o.getModelId());
						psDao.updateInputNum(psBean);
					}else{
						o.setRepairQualifiedNum("1");
						o.setTaskId(list.get(0).getTaskId());
						addRepairInputDetails(o);
					}
					
				} else {
					o.setTaskId(list.get(0).getTaskId());
					addRepairInputNumDetails(o);
				}
			} else {
				// 建立任务
				if ("0".equals(o.getIsCount())) {
					o.setRepairQualifiedNum("1");
					buildReturnRepairInputTask(o);
				} else {
					buildReturnRepairInputTask(o);
				}
			}
		}

	// 维修合格入库任务
	public void buildRepairInputTask(RepairCheckDetailsBean o) {
		TaskRecordBean bean = new TaskRecordBean();
		String number = findNumber(o);
		bean.setNumber(number);
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(o.getUserId());
		bean.setDefinitionId("21");// 21维修合格入库任务
		bean.setProcessId("4");
		bean.setIsFinish("0");
		bean.setTaskId(o.getSupId());
		trDao.insert(bean);
		o.setTaskId(bean.getId());
		addRepairInputDetails(o);
	}
	
	// 维修合格入库任务
	public void buildReturnRepairInputTask(RepairCheckDetailsBean o) {
		TaskRecordBean bean = new TaskRecordBean();
		String number = findNumber(o);
		bean.setNumber(number);
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(o.getUserId());
		bean.setDefinitionId("25");// 21维修合格入库任务
		bean.setProcessId("4");
		bean.setIsFinish("0");
		bean.setTaskId(o.getSupId());
		trDao.insert(bean);
		o.setTaskId(bean.getId());
		addRepairInputDetails(o);
	}

	// 添加入库任务明细
	public void addRepairInputDetails(RepairCheckDetailsBean o) {
		PutInStorageTaskBean psBean = new PutInStorageTaskBean();
		psBean.setTaskId(o.getTaskId());
		psBean.setMaModelId(o.getModelId());
		PutInStorageTaskBean bean = psDao.findByTask(psBean);
		if (bean != null) {
			String prePutNum = bean.getPrePutNum();
			int putNum = Integer.parseInt(prePutNum) + 1;
			psBean.setPrePutNum(putNum+"");
			psDao.updatePrePutNum(psBean);
		}else {
			psBean.setPutTime(DateTimeHelper.getNowTime());
			psBean.setPrePutNum(o.getRepairQualifiedNum());
			psBean.setCheckId(o.getId());
			psDao.insert(psBean);
		}
		
		
	}
	// 添加入库任务明细
	public void addRepairInputNumDetails(RepairCheckDetailsBean o) {
		PutInStorageTaskBean psBean = new PutInStorageTaskBean();
		psBean.setTaskId(o.getTaskId());
		psBean.setMaModelId(o.getModelId());
		
		psBean.setPutTime(DateTimeHelper.getNowTime());
		psBean.setPrePutNum(o.getRepairQualifiedNum());
		psBean.setCheckId(o.getId());
		psDao.insert(psBean);
	}

	// 生成入库单号
	public String findNumber(RepairCheckDetailsBean o) {
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		String nowDay = DateTimeHelper.getFormatNowMonthAndDay();
		String operationTime = DateTimeHelper.getNowDate();
		TaskRecordBean bean = new TaskRecordBean();
		bean.setOperationTime(operationTime);
		bean.setDefinitionId("21");
		String count = trDao.findNumber(bean);
		int str = Integer.parseInt(count) + 1;
		String counts = String.format("%03d", str);
		String code = "RK" + yearLast + nowDay + counts;
		return code;
	}

	// 建立检验报废任务 1.taskId2.supId,modelId
	public void addScrapTask(RepairCheckDetailsBean o) {
		// 判断该退料任务下有无维修检验报废任务
		TaskRecordBean bean = new TaskRecordBean();
		bean.setTaskId(o.getSupId());
		bean.setDefinitionId("22");// 维修检验 同意报废任务
		// 查询是否有任务
		List<TaskRecordBean> list = trDao.findRepairTask(bean);
		if (list != null && list.size() > 0) {
			// 添加明细
			if ("0".equals(o.getIsCount())) {
				// 根据检验id查询现有报废数量
				RepairCheckDetailsBean rcBean = dao.findScrapNumById(o);
				if (rcBean != null) {
					float inputNum = Float.parseFloat(rcBean.getScrapNum());
					float scrapQualifiedNum = inputNum + 1;
					ScrapDetailsBean sdBean = new ScrapDetailsBean();
					sdBean.setScrapNum(scrapQualifiedNum + "");
					sdBean.setCheckId(o.getId());
					sdBean.setModelId(o.getModelId());
					sdDao.updateScrapNum(sdBean);
				}else{
					o.setScrapQualifiedNum("1");
					o.setTaskId(list.get(0).getTaskId());
					addScrapDetails(o);
				}
			} else {
				o.setTaskId(list.get(0).getTaskId());
				addScrapDetails(o);
			}
		} else {
			// 建立任务
			if ("0".equals(o.getIsCount())) {
				o.setScrapQualifiedNum("1");
				buildScrapTask(o);
			} else {
				buildScrapTask(o);
			}
		}

	}
	
	// 建立检验报废任务 1.taskId2.supId,modelId
		public void addReturnScrapTask(RepairCheckDetailsBean o) {
			// 判断该退料任务下有无维修检验报废任务
			TaskRecordBean bean = new TaskRecordBean();
			bean.setTaskId(o.getSupId());
			bean.setDefinitionId("26");// 维修检验 同意报废任务
			// 查询是否有任务
			List<TaskRecordBean> list = trDao.findRepairTask(bean);
			if (list != null && list.size() > 0) {
				// 添加明细
				if ("0".equals(o.getIsCount())) {
					// 根据检验id查询现有入库数量
					RepairCheckDetailsBean rcBean = dao.findScrapNumById(o);
					if (rcBean != null) {
						float inputNum = Float.parseFloat(rcBean.getScrapNum());
						float scrapQualifiedNum = inputNum + 1;
						ScrapDetailsBean sdBean = new ScrapDetailsBean();
						sdBean.setScrapNum(scrapQualifiedNum + "");
						sdBean.setCheckId(o.getId());
						sdBean.setModelId(o.getModelId());
						sdDao.updateScrapNum(sdBean);
					}else{
						o.setScrapQualifiedNum("1");
						o.setTaskId(list.get(0).getTaskId());
						addScrapDetails(o);
					}
				} else {
					o.setTaskId(list.get(0).getTaskId());
					addScrapDetails(o);
				}
			} else {
				// 建立任务
				if ("0".equals(o.getIsCount())) {
					o.setScrapQualifiedNum("1");
					buildReturnScrapTask(o);
				} else {
					buildReturnScrapTask(o);
				}
			}

		}

	// 报废合格 报废任务
	public void buildScrapTask(RepairCheckDetailsBean o) {
		TaskRecordBean bean = new TaskRecordBean();
		String number = findScrapNumber(o);
		bean.setNumber(number);
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(o.getUserId());
		bean.setDefinitionId("22");// 维修检验 同意报废任务
		bean.setProcessId("4");
		bean.setIsFinish("0");
		bean.setTaskId(o.getSupId());
		trDao.insert(bean);
		o.setTaskId(bean.getId());
		addScrapDetails(o);
	}

	// 报废合格 报废任务
		public void buildReturnScrapTask(RepairCheckDetailsBean o) {
			TaskRecordBean bean = new TaskRecordBean();
			String number = findScrapNumber(o);
			bean.setNumber(number);
			bean.setOperationTime(DateTimeHelper.getNowTime());
			bean.setOperationUserId(o.getUserId());
			bean.setDefinitionId("26");// 维修检验 同意报废任务
			bean.setProcessId("4");
			bean.setIsFinish("0");
			bean.setTaskId(o.getSupId());
			trDao.insert(bean);
			o.setTaskId(bean.getId());
			addScrapDetails(o);
		}
	// 添加报废任务明细
	public void addScrapDetails(RepairCheckDetailsBean o) {
		ScrapDetailsBean bean = new ScrapDetailsBean();
		bean.setTaskId(o.getTaskId());
		bean.setModelId(o.getModelId());
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setScrapNum(o.getScrapQualifiedNum());
		bean.setCheckId(o.getId());
		sdDao.insert(bean);
	}

	// 生成报废单号
	public String findScrapNumber(RepairCheckDetailsBean o) {
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		String nowDay = DateTimeHelper.getFormatNowMonthAndDay();
		String operationTime = DateTimeHelper.getNowDate();
		TaskRecordBean bean = new TaskRecordBean();
		bean.setOperationTime(operationTime);
		bean.setDefinitionId("11");
		String count = trDao.findNumber(bean);
		int str = Integer.parseInt(count) + 1;
		String counts = String.format("%03d", str);
		String code = "BF" + yearLast + nowDay + counts;
		return code;
	}

	// 建立检验维修1.taskId2.supId,modelId
	public void addRepairTask(RepairCheckDetailsBean o) {
		// 判断该退料任务下有无维修检验（不合格反推）维修任务
		TaskRecordBean bean = new TaskRecordBean();
		bean.setTaskId(o.getSupId());
		bean.setDefinitionId("23");
		// 查询是否有任务
		List<TaskRecordBean> list = trDao.findRepairTask(bean);
		if (list != null && list.size() > 0) {
			if ("0".equals(o.getIsCount())) {
				// 根据检验id查询现有入库数量
				RepairCheckDetailsBean rcBean = dao.findRepairNumById(o);
				if (rcBean != null) {
					float inputNum = Float.parseFloat(rcBean.getRepairNum());
					float repairUnqualifiedNum = inputNum + 1;
					RepairDetailsBean rdBean = new RepairDetailsBean();
					rdBean.setRepairNum(repairUnqualifiedNum + "");
					rdBean.setCheckId(o.getId());
					rdBean.setModelId(o.getModelId());
					rdDao.updateRepairNum(rdBean);
				}else{
					o.setNums("1");
					o.setTaskId(list.get(0).getTaskId());
					addRepairDetails(o);
				}
			} else {
				o.setTaskId(list.get(0).getTaskId());
				addRepairDetails(o);
			}
		} else {
			// 建立任务
			if ("0".equals(o.getIsCount())) {
				o.setNums("1");
				buildRepairTask(o);
			} else {
				buildRepairTask(o);
			}
		}

	}

	// 维修合格维修任务
	public void buildRepairTask(RepairCheckDetailsBean o) {
		TaskRecordBean bean = new TaskRecordBean();
		String number = findRepairNumber(o);
		bean.setNumber(number);
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(o.getUserId());
		bean.setDefinitionId("23");// 21维修合格入库任务
		bean.setProcessId("4");
		bean.setIsFinish("0");
		bean.setTaskId(o.getSupId());
		trDao.insert(bean);
		o.setTaskId(bean.getId());
		addRepairDetails(o);
	}

	// 添加维修任务明细
	public void addRepairDetails(RepairCheckDetailsBean o) {
		RepairDetailsBean bean = new RepairDetailsBean();
		bean.setTaskId(o.getTaskId());
		bean.setModelId(o.getModelId());
		bean.setOperationTime(DateTimeHelper.getNowTime());
		if ("1".equals(o.getIsCount())) {
			float repairNum = 0;
			float scrapNum = 0;
			if (StringHelper.isNotEmpty(o.getRepairUnqualifiedNum()) && !"0".equals(o.getRepairUnqualifiedNum())) {
				repairNum = Float.parseFloat(o.getRepairUnqualifiedNum());
			}else{
				repairNum = 0;
			}
			if (StringHelper.isNotEmpty(o.getScrapUnqualifiedNum()) && !"0".equals(o.getScrapUnqualifiedNum())) {
				scrapNum = Float.parseFloat(o.getScrapUnqualifiedNum());
			}else{
				scrapNum = 0;
			}
			float nums = repairNum + scrapNum;
			o.setNums(nums + "");
		}
		bean.setRepairNum(o.getNums());
		bean.setCheckId(o.getId());
		rdDao.insert(bean);
	}

	// 生成维修单号
	public String findRepairNumber(RepairCheckDetailsBean o) {
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		String nowDay = DateTimeHelper.getFormatNowMonthAndDay();
		String operationTime = DateTimeHelper.getNowDate();
		TaskRecordBean bean = new TaskRecordBean();
		bean.setOperationTime(operationTime);
		bean.setDefinitionId("18");
		String count = trDao.findNumber(bean);
		int str = Integer.parseInt(count) + 1;
		String counts = String.format("%03d", str);
		String code = "WX" + yearLast + nowDay + counts;
		return code;
	}

	// 修改机具状态
	public void updateDevStatus(RepairCheckDetailsBean o) {
		MachineBean maBean = new MachineBean();
		maBean.setTypeId(o.getModelId());
		maBean.setDeviceCode(o.getDeviceCode());
		maBean.setBatchStatus(o.getBatchStatus());
		maDao.updateMachineStatus(maBean);
	}

	// 添加检验记录
	public void addCheckDetails(RepairCheckDetailsBean o) {
		ReturnMaterialDetailsBean bean = new ReturnMaterialDetailsBean();
		bean.setSupId(o.getTaskId());
		bean.setCode(o.getDeviceCode());
		bean.setModelId(o.getModelId());
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setState("8");// 检验
		if ("1".equals(o.getIsCount())) {
			if (StringHelper.isNotEmpty(o.getRepairQualifiedNum()) && !"0".equals(o.getRepairQualifiedNum())) {
				bean.setMaNum(o.getRepairQualifiedNum());
				bean.setRmStatus("8");// 维修检验合格
				rmDao.insertInfo(bean);
			} 
			if ((StringHelper.isNotEmpty(o.getRepairUnqualifiedNum()) && !"0".equals(o.getRepairUnqualifiedNum()))
					|| (StringHelper.isNotEmpty(o.getScrapUnqualifiedNum()) && !"0".equals(o.getScrapUnqualifiedNum()))) {
				float repairNum = 0;
				float scrapNum = 0;
				if (StringHelper.isNotEmpty(o.getRepairUnqualifiedNum()) && !"0".equals(o.getRepairUnqualifiedNum())) {
					repairNum = Float.parseFloat(o.getRepairUnqualifiedNum());
				}else{
					repairNum = 0;
				}
				
				if (StringHelper.isNotEmpty(o.getScrapUnqualifiedNum()) && !"0".equals(o.getScrapUnqualifiedNum())) {
					scrapNum = Float.parseFloat(o.getScrapUnqualifiedNum());
				}else{
					scrapNum = 0;
				}
				float nums = repairNum + scrapNum;
				bean.setMaNum(nums + "");
				bean.setRmStatus("9");// 维修检验不合格
				rmDao.insertInfo(bean);
			} 
			if (StringHelper.isNotEmpty(o.getScrapQualifiedNum()) && !"0".equals(o.getScrapQualifiedNum())) {
				bean.setMaNum(o.getScrapQualifiedNum());
				bean.setRmStatus("10");// 维修报废合格
				rmDao.insertInfo(bean);
			}
			
			// 修改状态
			o.setRmStatus("11");// 维修检验完成
			float thisCheckNums = Float.parseFloat(o.getThisCheckNum());
			float checkNum = Float.parseFloat(o.getCheckNum());
			if(thisCheckNums == checkNum ){
				dao.updateInfo(o);// 修改明细状态
			}
			
		
		} else {
			RepairCheckDetailsBean rcBean = dao.findDeviceRmstatus(o);
			if ("5".equals(rcBean.getRmStatus()) && "1".equals(o.getIsQualified())) {
				o.setRmStatus("8");// 入库
			}
			if ("6".equals(bean.getRmStatus()) && "1".equals(o.getIsQualified())) {
				o.setRmStatus("10");// 报废
			}
			if(("6".equals(bean.getRmStatus()) && "0".equals(o.getIsQualified()))
					|| ("5".equals(bean.getRmStatus()) && "0".equals(o.getIsQualified()))) {
				o.setRmStatus("9");// 检验
			}
			bean.setMaNum("1");
			bean.setRmStatus(o.getRmStatus());
			rmDao.insertInfo(bean);// 插入检验明细
			dao.updateDeviceInfo(o);// 修改明细状态
		}
	}

	public void updateCheckNum(RepairCheckDetailsBean o) {
		if ("0".equals(o.getIsCount())) {
			RepairCheckDetailsBean bean = dao.findAlCheckNum(o);
			float thisCheckNum = 1f;
			float alcheckNum = Float.parseFloat(bean.getAlCheckNum());
			alcheckNum = alcheckNum + thisCheckNum;
			float checkNum = Float.parseFloat(o.getCheckNum());
			if (checkNum == alcheckNum) {
				float nums = alcheckNum ;
				o.setIsSure("1");
				o.setAlCheckNum(nums + "");
			} else {
				float nums = alcheckNum;
				o.setIsSure("0");
				o.setAlCheckNum(nums + "");
			}
			o.setCheckTime(DateTimeHelper.getNowTime());
			dao.update(o);
		} else {
		/*	o.setIsSure("1");
			o.setAlCheckNum(o.getCheckNum());
			o.setCheckTime(DateTimeHelper.getNowTime());
			dao.update(o);*/
			
			//RepairCheckDetailsBean bean = dao.findAlCheckNum(o);
		
			float thisCheckNums = Float.parseFloat(o.getThisCheckNum());
			float checkNum = Float.parseFloat(o.getCheckNum());
			if (checkNum == thisCheckNums) {
				float nums = thisCheckNums ;
				o.setIsSure("1");
				o.setAlCheckNum(nums + "");
			} else {
				float nums = thisCheckNums;
				o.setIsSure("0");
				o.setAlCheckNum(nums + "");
			}
			o.setCheckTime(DateTimeHelper.getNowTime());
			dao.update(o);
		}
	}

	// 修改总任务状态
	public void updateIsFinish(RepairCheckDetailsBean o) {
		o.setIsSure("0");
		List<RepairCheckDetailsBean> list = dao.findIsSure(o);
		if (list != null && list.size() > 0) {
			return;
		} else {
			TaskRecordBean trBean = new TaskRecordBean();
			trBean.setId(o.getTaskId());
			trBean.setIsFinish("1");
			trBean.setIsSplit("1");
			trDao.update(trBean);
		}
	}

	@Override
	public List<RepairCheckDetailsBean> getCheckIndexList(RepairCheckDetailsBean o) {
		// TODO Auto-generated method stub
		if("0".equals(o.getNotCheck())) {
			return dao.getCheckIndexList(o);
		}else {
			List<RepairCheckDetailsBean> rsList = new ArrayList<RepairCheckDetailsBean>();
			
			List<RepairCheckDetailsBean> list = dao.getCheckedIndexList(o);
			
			for(RepairCheckDetailsBean rc : list){
				RepairCheckDetailsBean repairTimeBean = dao.getMaxRepairTime(rc);
				RepairCheckDetailsBean checkTimeBean = dao.getMaxCheckTime(rc);
				if(repairTimeBean != null){
					rc.setRepairTime(repairTimeBean.getRepairTime());
				}
				if (checkTimeBean != null) {
					rc.setCheckTime(checkTimeBean.getCheckTime());
				}
				rsList.add(rc);
			}
			
			ListSort(rsList);
			
			return rsList;
		}
		
	}

	@Override
	@Transactional
	public int submitNumCheck(RepairCheckDetailsBean o) {
		int res = 0;
		try {
			//非固资
			
			float thisRepairQualifiedNum = o.getNewRepairQualifiedNum();//29
			float thisScrapeQualifiedNum = o.getNewScrapQualifiedNum();
			float thisRepairUnQualifiedNum = o.getNewRepairUnqualifiedNum();
			float thisScrapeUnQualifiedNum = o.getNewScrapUnqualifiedNum();
			String modelId = o.getModelId();//162
			String isCount = o.getIsCount();//1
			
			//根据modelId查询checkId,checkNum ----检验合格入库
			if(thisRepairQualifiedNum + thisScrapeQualifiedNum > 0 ) {
				//有检验合格数量和待报废通过
				res = matchCheckData(thisRepairQualifiedNum,thisScrapeQualifiedNum,modelId,o);
				if(res == 0) {
					throw new ZeroAffectRowsException("检验通过失败!");
				}
			} 
			
			if(thisRepairUnQualifiedNum + thisScrapeUnQualifiedNum > 0 ) {
				//有检验不合格数量和待报废不通过
				res = matchnotPassCheckData(thisRepairUnQualifiedNum,thisScrapeUnQualifiedNum,modelId,o);
				if(res == 0) {
					throw new ZeroAffectRowsException("检验不通过失败!");
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
private int matchnotPassCheckData(float thisRepairUnQualifiedNum, float thisScrapeUnQualifiedNum, String modelId,
			RepairCheckDetailsBean o) {
	int res = 0;
	try {
		//有非固资维修不通过的数据
		if(thisRepairUnQualifiedNum > 0 || thisScrapeUnQualifiedNum >0) {
			List<RepairCheckDetailsBean> list = dao.getCheckId(modelId);
			if(list != null && list.size() > 0) {
				for(int i = 0;i < list.size(); i++) {
					float checkNum = Float.parseFloat(list.get(i).getCheckNum());//已检验数量
					float scrapNum = Float.parseFloat(list.get(i).getScrapNum());//已检验数量
					RepairCheckDetailsBean upBean = new RepairCheckDetailsBean();
					RepairCheckDetailsBean inBean = new RepairCheckDetailsBean();
					//循环匹配,修改已完成数量
					if(thisRepairUnQualifiedNum > 0) {
						if(Math.abs(thisRepairUnQualifiedNum - checkNum) < 0.000001) {//检验合格数=本次循环匹配的维修数
							//isSure,合格数,checkId,
							upBean.setAlCheckNum(thisRepairUnQualifiedNum+"");
							upBean.setCheckId(list.get(i).getCheckId());
							upBean.setIsSure("1");
							res = dao.updateCheckNum(upBean);
								if (res == 0) {
									throw new ZeroAffectRowsException("循环匹配修改已修数量失败,库存小于传入合格!");
								}
							//wir表插入检验记录
							inBean.setModelId(list.get(i).getModelId());
							inBean.setSupId(list.get(i).getTaskId());
							inBean.setNumber(thisRepairUnQualifiedNum+"");
							inBean.setUserId(o.getUserId());
							inBean.setTypeId("8");
							inBean.setRmStatus("8");
							res = dao.insertWirRecord(inBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
							}
							break;
						}else if((checkNum < thisRepairUnQualifiedNum)) {//合格数多于循环维修数
							upBean.setAlCheckNum(checkNum+"");
							upBean.setCheckId(list.get(i).getCheckId());
							upBean.setIsSure("1");
							res = dao.updateCheckNum(upBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("循环匹配修改已修数量失败,库存小于传入合格!");
							}
							//wir表插入检验记录
							inBean.setModelId(list.get(i).getModelId());
							inBean.setSupId(list.get(i).getTaskId());
							inBean.setNumber(checkNum+"");
							inBean.setUserId(o.getUserId());
							inBean.setTypeId("8");
							inBean.setRmStatus("8");
							res = dao.insertWirRecord(inBean);
							//wir表插入检验记录
							if (res == 0) {
								throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
							}
							thisRepairUnQualifiedNum = thisRepairUnQualifiedNum - checkNum;
						}else if(checkNum > thisRepairUnQualifiedNum){
							//合格数小于循环维修数
							upBean.setAlCheckNum(thisRepairUnQualifiedNum+"");
							upBean.setCheckId(list.get(i).getCheckId());
							upBean.setIsSure("0");
							res = dao.updateCheckNum(upBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("循环匹配修改已修数量失败,库存小于传入合格!");
							}
							//wir表插入检验记录
							inBean.setModelId(list.get(i).getModelId());
							inBean.setSupId(list.get(i).getTaskId());
							inBean.setNumber(thisRepairUnQualifiedNum+"");
							inBean.setUserId(o.getUserId());
							inBean.setTypeId("8");
							inBean.setRmStatus("9");
							res = dao.insertWirRecord(inBean);
							//wir表插入检验记录
							if (res == 0) {
								throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
							}
						}else{
							System.err.println("检验合格数不存在");
						}
						//wrd  根据model查询最新未完成加入  repairNum+
						RepairCheckDetailsBean bean = dao.getOneDataFromWrd(o);
						o.setCheckId(bean.getCheckId());
						res = dao.updateReturnData(o);
						if (res == 0) {
							throw new ZeroAffectRowsException("非固资不通过修改退料数量失败!");
						}
					}else {
						System.err.println("无检验合格数");
					}
					
					//有报废申请通过的数量
					if(thisScrapeUnQualifiedNum > 0) {
						if(Math.abs(thisScrapeUnQualifiedNum - scrapNum) < 0.000001) {//检验待报废数=本次循环匹配的维修数
							//isSure,合格数,checkId,
							upBean.setAlScrapNum(thisScrapeUnQualifiedNum+"");
							upBean.setCheckId(list.get(i).getCheckId());
							upBean.setIsSure("1");
							res = dao.updateCheckNum(upBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("修改wrc表数据失败");
							}
							//wir表插入检验记录
							inBean.setModelId(list.get(i).getModelId());
							inBean.setSupId(list.get(i).getTaskId());
							inBean.setNumber(thisScrapeUnQualifiedNum+"");
							inBean.setUserId(o.getUserId());
							inBean.setTypeId("8");
							inBean.setRmStatus("9");
							res = dao.insertWirRecord(inBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
							}
							break;
						}else if((scrapNum < thisScrapeUnQualifiedNum)) {//待报废多于循环维修数
							upBean.setAlScrapNum(scrapNum+"");
							upBean.setCheckId(list.get(i).getCheckId());
							upBean.setIsSure("1");
							res = dao.updateCheckNum(upBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("修改wrc表数据失败");
							}
							//wir表插入检验记录
							inBean.setModelId(list.get(i).getModelId());
							inBean.setSupId(list.get(i).getTaskId());
							inBean.setNumber(scrapNum+"");
							inBean.setUserId(o.getUserId());
							inBean.setTypeId("8");
							inBean.setRmStatus("10");
							res = dao.insertWirRecord(inBean);
							//wir表插入检验记录
							if (res == 0) {
								throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
							}
							thisScrapeUnQualifiedNum = thisScrapeUnQualifiedNum - scrapNum;
						}else if(scrapNum > thisScrapeUnQualifiedNum){
							//待报废小于循环维修数
							upBean.setAlScrapNum(thisScrapeUnQualifiedNum+"");
							upBean.setCheckId(list.get(i).getCheckId());
							upBean.setIsSure("0");
							res = dao.updateCheckNum(upBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("修改wrc表数据失败");
							}
							//wir表插入检验记录
							inBean.setModelId(list.get(i).getModelId());
							inBean.setSupId(list.get(i).getTaskId());
							inBean.setNumber(thisScrapeUnQualifiedNum+"");
							inBean.setUserId(o.getUserId());
							inBean.setTypeId("8");
							inBean.setRmStatus("10");
							//wir表插入检验记录
							res = dao.insertWirRecord(inBean);
							if (res == 0) {
								throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
							}
						}else{
							System.err.println("检验合格数不存在");
						}
					
					}else {
						System.err.println("无检验不合格数");
					}
			}
				
				//wrd  根据model查询最新未完成加入  repairNum+
				RepairCheckDetailsBean bean = dao.getOneDataFromWrd(o);
				o.setCheckId(bean.getCheckId());
				res = dao.updateReturnData(o);
				if (res == 0) {
					throw new ZeroAffectRowsException("非固资不通过修改退料数量失败!");
				}
		}
	}
	}catch (Exception e) {
		// TODO: handle exception'
		e.printStackTrace();
	}
	return res;
}

	//（非固资）检验维修合格，申请待报废
	private int matchCheckData(float thisRepairQualifiedNum,float thisScrapeQualifiedNum,String modelId,RepairCheckDetailsBean o) {
		int res = 0;
		try {
			float qfNum = thisRepairQualifiedNum;
			float sfNum = thisScrapeQualifiedNum;
			// TODO Auto-generated method stub
					//1.根据modelId取维修任务进行循环匹配
					List<RepairCheckDetailsBean> list = dao.getCheckId(modelId);//1 checkId 803  taskId 19410
					if(list != null && list.size() > 0) {
						for(int i = 0;i < list.size(); i++) {
							float checkNum = Float.parseFloat(list.get(i).getCheckNum());//已检验合格数量   29
							float scrapNum = Float.parseFloat(list.get(i).getScrapNum());//已检验申请待报废数量 0
							RepairCheckDetailsBean upBean = new RepairCheckDetailsBean();
							RepairCheckDetailsBean inBean = new RepairCheckDetailsBean();
							//循环匹配,修改检验合格的数量
							if(thisRepairQualifiedNum > 0) {
								if(Math.abs(thisRepairQualifiedNum - checkNum) < 0.000001) {//检验合格数=本次循环匹配的维修数  √
									//根据checkId修改isSure及alcheckNum
									upBean.setAlCheckNum(thisRepairQualifiedNum+"");
									upBean.setCheckId(list.get(i).getCheckId());
									upBean.setIsSure("1");
									res = dao.updateCheckNum(upBean);
										if (res == 0) {
											throw new ZeroAffectRowsException("非固资循环匹配检验合格数量失败");
										}
									//wir表插入检验记录1
									inBean.setModelId(list.get(i).getModelId());        //162
									inBean.setSupId(list.get(i).getTaskId());//19410
									inBean.setNumber(thisRepairQualifiedNum+"");//29
									inBean.setUserId(o.getUserId());
									inBean.setTypeId("8");
									inBean.setRmStatus("8");
									res = dao.insertWirRecord(inBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
									}
									thisRepairQualifiedNum = 0;
									continue;
								}else if((checkNum < thisRepairQualifiedNum)) {//合格数多于循环维修数
									upBean.setAlCheckNum(checkNum+"");
									upBean.setCheckId(list.get(i).getCheckId());
									upBean.setIsSure("1");
									res = dao.updateCheckNum(upBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("非固资循环匹配检验合格数量失败");
									}
									//wir表插入检验记录
									inBean.setModelId(list.get(i).getModelId());
									inBean.setSupId(list.get(i).getTaskId());
									inBean.setNumber(checkNum+"");
									inBean.setUserId(o.getUserId());
									inBean.setTypeId("8");
									inBean.setRmStatus("8");
									res = dao.insertWirRecord(inBean);
									//wir表插入检验记录
									if (res == 0) {
										throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
									}
									thisRepairQualifiedNum = thisRepairQualifiedNum - checkNum;
								}else if(checkNum > thisRepairQualifiedNum){
									//合格数小于循环维修数
									upBean.setAlCheckNum(thisRepairQualifiedNum+"");
									upBean.setCheckId(list.get(i).getCheckId());
									upBean.setIsSure("0");
									res = dao.updateCheckNum(upBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("非固资循环匹配检验合格数量失败");
									}
									//wir表插入检验记录
									inBean.setModelId(list.get(i).getModelId());
									inBean.setSupId(list.get(i).getTaskId());
									inBean.setNumber(thisRepairQualifiedNum+"");
									inBean.setUserId(o.getUserId());
									inBean.setTypeId("8");
									inBean.setRmStatus("8");
									res = dao.insertWirRecord(inBean);
									//wir表插入检验记录
									if (res == 0) {
										throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
									}
								}else{
									System.err.println("检验合格数不存在");
								}
							}else {
								System.err.println("无检验合格数");
							}
							
							
							//有报废申请通过的数量
							if(thisScrapeQualifiedNum > 0) {
								if(Math.abs(thisScrapeQualifiedNum - scrapNum) < 0.000001) {//检验待报废数=本次循环匹配的维修数
									//isSure,合格数,checkId,
									upBean.setAlScrapNum(thisScrapeQualifiedNum+"");
									upBean.setCheckId(list.get(i).getCheckId());
									upBean.setIsSure("1");
									res = dao.updateCheckNum(upBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("修改wrc表数据失败");
									}
									//wir表插入检验记录
									inBean.setModelId(list.get(i).getModelId());
									inBean.setSupId(list.get(i).getTaskId());
									inBean.setNumber(thisScrapeQualifiedNum+"");
									inBean.setUserId(o.getUserId());
									inBean.setTypeId("8");
									inBean.setRmStatus("10");
									res = dao.insertWirRecord(inBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
									}
									thisScrapeQualifiedNum = 0;
									continue;
								}else if((scrapNum < thisScrapeQualifiedNum)) {//待报废多于循环维修数
									upBean.setAlScrapNum(scrapNum+"");
									upBean.setCheckId(list.get(i).getCheckId());
									upBean.setIsSure("1");
									res = dao.updateCheckNum(upBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("修改wrc表数据失败");
									}
									//wir表插入检验记录
									inBean.setModelId(list.get(i).getModelId());
									inBean.setSupId(list.get(i).getTaskId());
									inBean.setUserId(o.getUserId());
									inBean.setNumber(scrapNum+"");
									inBean.setTypeId("8");
									inBean.setRmStatus("10");
									res = dao.insertWirRecord(inBean);
									//wir表插入检验记录
									if (res == 0) {
										throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
									}
									thisScrapeQualifiedNum = thisScrapeQualifiedNum - scrapNum;
								}else if(scrapNum > thisScrapeQualifiedNum){
									//待报废小于循环维修数
									upBean.setAlScrapNum(thisScrapeQualifiedNum+"");
									upBean.setCheckId(list.get(i).getCheckId());
									upBean.setIsSure("0");
									res = dao.updateCheckNum(upBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("修改wrc表数据失败");
									}
									//wir表插入检验记录
									inBean.setModelId(list.get(i).getModelId());
									inBean.setSupId(list.get(i).getTaskId());
									inBean.setNumber(thisScrapeQualifiedNum+"");
									inBean.setUserId(o.getUserId());
									inBean.setTypeId("8");
									inBean.setRmStatus("10");
									//wir表插入检验记录
									res = dao.insertWirRecord(inBean);
									if (res == 0) {
										throw new ZeroAffectRowsException("检验合格插入wir记录失败!");
									}
								}else{
									System.err.println("检验合格数不存在");
								}
								
								
							}else {
								System.err.println("无检验不合格数");
							}
						}
						if(qfNum > 0) {
							//创建入库任务
							res = newBuildReturnRepairInputTask(o);
						}
						if(sfNum > 0) {
							//创建报废任务
							o.setSupId(list.get(0).getTaskId());
							res = newScrapPassTask(o);
						}
						
					}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}


	//创建报废任务
	public int  newScrapPassTask(RepairCheckDetailsBean o) {
		int res = 0;
		try {
			TaskRecordBean bean = new TaskRecordBean();
			String number = findScrapNumber(o);
			bean.setNumber(number);
			bean.setOperationTime(DateTimeHelper.getNowTime());
			bean.setOperationUserId(o.getUserId());
			bean.setDefinitionId("19");// 21维修合格入库任务
			bean.setProcessId("4");
			bean.setIsFinish("0");
			bean.setTaskId(o.getSupId());
			res = trDao.insert(bean);
			if (res == 0) {
				throw new ZeroAffectRowsException("生成新单据失败!");
			}
			o.setTaskId(bean.getId());
			res = addNewScrapDetails(o);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	
	// 添加报废任务明细
		public int addNewScrapDetails(RepairCheckDetailsBean o) {
			int res = 0;
			try {
				ScrapDetailsBean bean = new ScrapDetailsBean();
				bean.setTaskId(o.getTaskId());
				bean.setModelId(o.getModelId());
				bean.setOperationTime(DateTimeHelper.getNowTime());
				bean.setScrapNum(o.getNewScrapQualifiedNum()+"");
				bean.setCheckId(o.getId());
				res = sdDao.insert(bean);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return res;
		}
	
	// 维修合格入库任务
	public int newBuildReturnRepairInputTask(RepairCheckDetailsBean o) {
		int res = 0;
		try {
			String supId = buildTask(o);
			o.setTaskId(supId);
			res = newAddRepairInputDetails(o, res);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	
	public String buildTask(RepairCheckDetailsBean o) {
		TaskRecordBean bean = new TaskRecordBean();
		RepairCheckDetailsBean company = dao.findCompanyId(o);
		String number = findNumber(o);// 生成入库单号
		bean.setNumber(number);
		bean.setCompanyId(company.getCompanyId());
		bean.setOperationTime(DateTimeHelper.getNowTime());
		bean.setOperationUserId(o.getUserId());
		bean.setDefinitionId("21");// 21维修合格入库任务
		bean.setProcessId("4");
		bean.setIsFinish("0");
		int res = trDao.insert(bean);
		if (res == 0) {
			throw new ZeroAffectRowsException("生成新单据失败!");
		}
		return bean.getId();
	}
	
	
	// 添加入库任务明细
	public int  newAddRepairInputDetails(RepairCheckDetailsBean o, int inputNum) {
		int res = 0;
		try {
			PutInStorageTaskBean psBean = new PutInStorageTaskBean();
			psBean.setTaskId(o.getTaskId());
			psBean.setMaModelId(o.getModelId());
			psBean.setPutTime(DateTimeHelper.getNowTime());
			if("1".equals(o.getIsCount())) {
				psBean.setPrePutNum(o.getNewRepairQualifiedNum()+"");
			}else {
				psBean.setPrePutNum(inputNum+"");
			}
			psBean.setCheckId(o.getUserId());
			res =dao.insertNewDetail(psBean);
			if (res == 0) {
				throw new ZeroAffectRowsException("新增入库记录失败!");
			}
		}catch (Exception e) {
			// TODO: handle exceptione
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 判空
	 * @param repairNum
	 * @return
	 */
	public int isNum(String repairNum) {
		int num = 0;
		if(StringHelper.isEmptyAndNull(repairNum)) {
			num = 0;
		}else {
			num =1;
		}
		return num;
	}
	/**
	 * 判断两个数字大小
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int deCompare(String num1,String num2) {
		int num = 0;
		int deNum1 =  Integer.parseInt(num1);
		int deNum2 =  Integer.parseInt(num2);
		if(deNum1 == deNum2) {
			num = 1;
		}else if(deNum1 > deNum2) {
			num = 2;
		}else {
			num = 0;
		}
		return num;
	}

	@Override
	public List<RepairCheckDetailsBean> getRepairCodeList(RepairCheckDetailsBean o) {
		// TODO Auto-generated method stub
		if("0".equals(o.getPassCheck())) {
			return dao.getRepairCodeList(o);//未完成固资详情
		}else {
			return dao.getRepairCodeList1(o);//已完成固资详情
		}
	}

	//固资检验-通过/不通过
	@Override
	@Transactional
	public int submitCodeCheck(RepairCheckDetailsBean o) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			String passStatus = o.getPassStatus();
			
			
			RepairCheckDetailsBean[] arr = o.getArr();
			
			boolean flag = false;
			boolean scrap = false;
			
			//检验通过
			if("1".equals(passStatus)){
				
				
				for(int i = 0; i< arr.length ;i++) {
				
						//通过
						res = updateCheckPassStatus(arr[i]);
							if(res == 0) {
								throw new ZeroAffectRowsException("固资检验通过修改状态失败");
							}
						RepairCheckDetailsBean bean = dao.getAlCherckNum(arr[i]);//获取已检已报废数量
						if("8".equals(arr[i].getRmStatus())) {
							if(bean.getCheckInNum()>0) {
								
								flag = true;
								String alCheckNum = bean.getAlCheckNum();
								if (alCheckNum == null || alCheckNum == "") {
									alCheckNum = "0";
								}
								float checkNum = Float.parseFloat(alCheckNum) + 1;
								bean.setAlCheckNum(String.valueOf(checkNum));
							}
						}else{
							if(bean.getScrapInNum()>0) {
								scrap = true;
								o.setAlScrapNum(bean.getScrapNum());
								String alScrapNum = bean.getAlScrapNum();
								if (alScrapNum == null || alScrapNum == "") {
									alScrapNum = "0";
								}
								float scrapNum = Float.parseFloat(alScrapNum) + 1;
								bean.setAlScrapNum(String.valueOf(scrapNum));	
								
							}
						}
						res= dao.updateWrcNum(bean);//modelId,taskId
						
						if(res == 0) {
							throw new ZeroAffectRowsException("修改wrc表数据失败");
						}
							
				}
				
			}else{
				//检验不通过
				
				
				for(int i = 0; i< arr.length ;i++) {
					
					RepairCheckDetailsBean bean = dao.getNewCheckCodeDate(arr[i]);//sql未完成
					//修改状态
					res = updateCheckNotPassStatus(arr[i],bean);
					if(res == 0) {
						throw new ZeroAffectRowsException("检验不通过失败");
					}
						
				}
			
			}
			
			if(flag){
				
				String supId = buildTask(o);
				int inputNum = 0;
				
				for(int i = 0; i< arr.length ;i++) {
					
					
					if("8".equals(arr[i].getRmStatus())) {
						arr[i].setRmStatus("8");
						arr[i].setOperationTime(DateTimeHelper.getNowTime());
						res = dao.updateInfoType(arr[i]);
						if(res == 0) {
							throw new ZeroAffectRowsException("修改wir检验记录失败");
						}
						inputNum++;
						RepairCheckDetailsBean inBean = new RepairCheckDetailsBean();
						inBean.setModelId(arr[i].getModelId());        //162
						inBean.setTaskId(arr[i].getTaskId());//19410
						inBean.setDeviceCode(arr[i].getDeviceCode());
						inBean.setSupId(supId);
						inBean.setNumber("1");//29
						inBean.setTypeId("3");
						inBean.setRmStatus("12");
						res = dao.insertWirRecord(inBean);
						if(res == 0) {
							throw new ZeroAffectRowsException("插入wir表固资通过记录数据失败");
						}
					}
				
				}
				
				o.setSupId(supId);
				res = newBuildReturnInputTask(o,inputNum);
			}
			
			if(scrap){
				
				
				TaskRecordBean bean = new TaskRecordBean();
				String number = findScrapNumber(o);
				bean.setNumber(number);
				bean.setOperationTime(DateTimeHelper.getNowTime());
				bean.setOperationUserId(o.getUserId());
				bean.setDefinitionId("26");// 维修检验 同意报废任务
				bean.setProcessId("4");
				bean.setIsFinish("0");
				bean.setTaskId(o.getSupId());
				res = trDao.insert(bean);
				if (res == 0) {
					throw new ZeroAffectRowsException("生成新单据失败!");
				}
				o.setSupId(bean.getId());
				
				int scrapNum = 0;
				for(int i = 0; i< arr.length ;i++) {
					
					if("10".equals(arr[i].getRmStatus())) {
						res = dao.updateInfoType(arr[i]);
						if(res == 0) {
							throw new ZeroAffectRowsException("修改wir检验记录失败");
						}
						scrapNum++;
						buildScrapDetails(o,arr[i]);
					}
				}
				o.setScrapNum(String.valueOf(scrapNum));
				buildScrap(o);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int buildScrap(RepairCheckDetailsBean o) {
		ScrapDetailsBean scrapDetailsBean = new ScrapDetailsBean();
		scrapDetailsBean.setTaskId(o.getSupId());
		scrapDetailsBean.setOperationTime(DateTimeHelper.getNowTime());
		scrapDetailsBean.setModelId(o.getModelId());
		if("1".equals(o.getIsCount())) {
			scrapDetailsBean.setScrapNum(o.getNewScrapQualifiedNum()+"");
		}else {
			scrapDetailsBean.setScrapNum(o.getScrapNum());
		}
		int res = sdDao.insert(scrapDetailsBean);
		if(res == 0) {
			throw new ZeroAffectRowsException("插入wsd表固资通过记录数据失败");
		}
		return res;
	}
	

	public int buildScrapDetails(RepairCheckDetailsBean o,RepairCheckDetailsBean p) {
		RepairCheckDetailsBean inBean = new RepairCheckDetailsBean();
		inBean.setModelId(p.getModelId());        //162
		inBean.setTaskId(p.getTaskId());//19410
		inBean.setDeviceCode(p.getDeviceCode());
		inBean.setNumber("1");//29
		inBean.setTypeId("9");
		inBean.setRmStatus("3");
		inBean.setSupId(o.getSupId());
		int res = dao.insertWirRecord(inBean);
		if(res == 0) {
			throw new ZeroAffectRowsException("插入wir表固资通过记录数据失败");
		}
		return res;
	}
	
	// 维修合格入库任务
		public int newBuildReturnInputTask(RepairCheckDetailsBean o, int inputNum) {
			int res = 0;
			try {
				o.setTaskId(o.getSupId());
				res = newAddRepairInputDetails(o,inputNum);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return res;
		}
	
	//修改检验通过状态
	private int updateCheckPassStatus(RepairCheckDetailsBean o) {
		int res = 0;
		try {
			if("5".equals(o.getRmStatus())) {
				o.setRmStatus("8");//检验合格
				o.setBatchStatus("9");//在库
			}else {
				o.setRmStatus("10");
				o.setBatchStatus("10");//在库
			}
	        res =  dao.updateCheckPassStatus(o);//修改wir表机具状态
		       if(res == 0) {
					throw new ZeroAffectRowsException("检验不通过失败");
				}
		    res = dao.updateCheckPassMMStatus(o);//修改mm表机具状态
		        if(res == 0) {
					throw new ZeroAffectRowsException("检验不通过失败");
				}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
	
	//修改检验未通过状态及数据
	private int updateCheckNotPassStatus(RepairCheckDetailsBean arr, RepairCheckDetailsBean bean) {
		int res = 0;
		try {
			if("5".equals(arr.getRmStatus())) {
				//维修合格
				arr.setAlCheckNum("1");
				arr.setNewRepairUnqualifiedNum(1);
			}else {
				arr.setAlScrapNum("1");
				arr.setNewScrapUnqualifiedNum(1);
			}
			res = dao.updateWrcCheckNum(arr);
			if(res == 0) {
				throw new ZeroAffectRowsException("检验不通过修改wrc表数据失败");
			}
			arr.setRmStatus("11");
			arr.setOperationTime(DateTimeHelper.getNowTime());
			res = dao.updateInfoType(arr);
			if(res == 0) {
				throw new ZeroAffectRowsException("修改wir检验记录失败");
			}	
			bean.setRmStatus("2");
			res = dao.updateWirRmStatus(bean);
		        if(res == 0) {
					throw new ZeroAffectRowsException("检验不通过修改wir表数据失败");
				}
		        
	        RepairCheckDetailsBean rcbean = dao.getOneDataFromWrd(arr);
	        arr.setCheckId(rcbean.getCheckId());
			res = dao.updateReturnData(arr);
			if(res == 0) {
				throw new ZeroAffectRowsException("检验不通过修改wir表数据失败");
			}
			arr.setBatchStatus("7");
	        res = dao.updateMMBatchStatus(arr);
		        if(res == 0) {
					throw new ZeroAffectRowsException("检验不通过修改mm表数据失败");
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<RepairCheckDetailsBean> getCheckedNumList(RepairCheckDetailsBean o) {
		return dao.getCheckedNumList(o);
	}
	
	private void ListSort(List<RepairCheckDetailsBean> list) {
        Collections.sort(list, new Comparator<RepairCheckDetailsBean>() {
            @Override
            //定义一个比较器
            public int compare(RepairCheckDetailsBean o1, RepairCheckDetailsBean o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                	if (StringHelper.isEmpty(o1.getCheckTime())) {
						return 1;
					}else if (StringHelper.isEmpty(o2.getCheckTime())) {
						return -1;
					}else {
						Date dt1 = format.parse(o1.getCheckTime());
	                    Date dt2 = format.parse(o2.getCheckTime());
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
