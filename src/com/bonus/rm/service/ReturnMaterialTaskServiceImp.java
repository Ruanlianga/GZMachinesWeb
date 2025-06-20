package com.bonus.rm.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.lease.beans.AgreementBean;
import com.bonus.lease.dao.AgreementDao;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.newSettlement.dao.MaTypeProjectStorageDao;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.beans.ReturnMaterialTaskBean;
import com.bonus.rm.dao.ReturnMaterialTaskDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.wf.beans.ProcessRecordBean;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.ProcessRecordDao;
import com.bonus.wf.dao.TaskRecordDao;
import com.sun.xml.internal.ws.message.StringHeader;

@Service("returnMaterialTask")
public class ReturnMaterialTaskServiceImp extends BaseServiceImp<ReturnMaterialTaskBean>
		implements ReturnMaterialTaskService {

	@Autowired
	ReturnMaterialTaskDao dao;

	@Autowired
	AgreementDao adao;

	@Autowired
	ProcessRecordDao prdao;

	@Autowired
	TaskRecordDao trdao;

	@Autowired
	MaTypeProjectStorageDao storageDao;

	@Override
	public List<AgreementBean> findAgreeCode(AgreementBean o) {
		return adao.findAgreeCode(o);
	}

	@Override
	public void addTask(ReturnMaterialTaskBean o) {
		// 新增退料流程记录
		UserBean user = UserShiroHelper.getRealCurrentUser();
	      String companyId = user.getCompanyId();
		ProcessRecordBean process = new ProcessRecordBean();
		String userId = UserShiroHelper.getRealCurrentUser().getId() + "";
		process.setOperationTime(DateTimeHelper.getNowTime());
		process.setOperationUserId(userId);
		process.setProcessId("3");
		prdao.insert(process);
		// 新增退料任务建立任务记录
		TaskRecordBean task = new TaskRecordBean();
		task.setDefinitionId("8");
		task.setCompanyId(companyId);
		task.setIsFinish("0");
		task.setOperationTime(DateTimeHelper.getNowTime());
		task.setOperationUserId(userId);
		task.setProcessId("3");
		task.setRemark(o.getRemark());
		task.setNumber(o.getNumber());
		task.setLeasePerson(o.getUserName());
		task.setPhone(o.getPhone());
		task.setSubcontractors(o.getSubcontractors());
		trdao.insert(task);
		AgreementBean a = new AgreementBean();
		a.setCode(o.getAgreementCode());
		List<AgreementBean> list = adao.findAgreeCodeId(a);
		String agreeId = "";
		if (list.size() > 0) {
			agreeId = list.get(0).getId();
		}
		task.setAgreementId(agreeId);
		task.setTaskId(task.getId());
		trdao.addTaskAgreement(task);
	}

	@Override
	public String findNumber(ReturnMaterialTaskBean o) {
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		String nowDay = DateTimeHelper.getFormatNowMonthAndDay();
		String operationTime = DateTimeHelper.getNowDate();
		o.setReturnMaterialTime(operationTime);
		String count = dao.findNumber(o);
		int str = Integer.parseInt(count) + 1;
		String counts = String.format("%03d", str);
		String code = "TZ" + yearLast + nowDay + counts;
		return code;
	}

	@Override
	public List<ReturnMaterialTaskBean> findAllTask(ReturnMaterialTaskBean o) {
		return dao.findAllTask(o);
	}

	@Override
	@Transactional
	public int updateBean(ReturnMaterialTaskBean o) {
		int result = 0;
		try {
			// 退料记录查询
			List<ReturnMaterialDetailsBean> backList = dao.findBackRecord(o);
			// ------------操作工程库存表,配合结算相关数据修改-------开始------------
			if (backList.size() > 0) {
				
				dao.updateAudit(o);
				
				result = dao.updateBean(o);
				for (int i = 0; i < backList.size(); i++) {
					String rmNum = backList.get(i).getBackNum();
					String modelId = backList.get(i).getModelId();
					String maId = backList.get(i).getMaId();
					String backDate = backList.get(i).getBackTime();
					String agreementId = backList.get(i).getAgreementId();
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
					if (null != maId && !"".equals(maId)) {
						MachineBean mb = new MachineBean();
						mb.setId(maId);
						storage.setMachine(mb);
					}
					Float rn = Float.parseFloat(rmNum);
					List<MaTypeProjectStorageBean> list = storageDao.findCanBackListById(storage);
					if (list != null && list.size() > 0) {
						if (StringHelper.isNotEmpty(maId)) {
							storage = list.get(0);
							// 设疑状态已退
							storage.setStatus(0);
							storage.setBackDate(backDate);
							result = storageDao.updateBean(storage);
							if (result == 0) {
								throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中machine修改操作错误!");
							}
						} else {
							int size = list.size();
							for (int j = 0; j < size; j++) {
								storage = list.get(j);
								Float num2 = storage.getNum();
								if (Math.abs(num2 - rn) < 0.000001) {
									storage.setStatus(0);
									storage.setBackDate(backDate);
									result = storageDao.updateBean(storage);
									if (result == 0) {
										throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中工器具修改操作错误!");
									}
									break;
								} else if (num2 < rn) {
									storage.setStatus(0);
									storage.setBackDate(backDate);
									result = storageDao.updateBean(storage);
									if (result == 0) {
										throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中工器具修改操作错误!");
									}
									rn = rn - num2;
								} else if (num2 > rn) {
									storage.setNum(num2 - rn);
									result = storageDao.updateBean(storage);
									if (result == 0) {
										throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中工器具修改操作错误!");
									}
									storage.setType(type);
									storage.setAgreement(agreement);
									storage.setNum(rn);
									storage.setId(null);
									storage.setBackDate(backDate);
									storage.setStatus(1);
									result = storageDao.insertBean(storage);
									if (result == 0) {
										throw new ZeroAffectRowsException("确认退库任务失败,工程库存表中工器具插入操作错误!");
									}
									break;
								}
							}
						}
					} else {
						System.err.println("i=" + i);
						throw new ZeroAffectRowsException("确认退库任务失败,设备或工器具在工程库存表中未找到可退项列表!");
					}
				}
			} else {
				result = 0;
			}
			// ----------------------------操作工程库存表,配合结算相关数据修改-------结束---------------------------
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<ReturnMaterialTaskBean> findDevByWorkId(ReturnMaterialTaskBean o) {
		List<ReturnMaterialTaskBean> list = dao.findDevByWorkId(o);
		return list;
	}

	@Override
	public List<ReturnMaterialTaskBean> findDevByUnitId(ReturnMaterialTaskBean o) {
		List<ReturnMaterialTaskBean> list = dao.findDevByUnitId(o);
		return list;
	}

	@Override
	public Integer deleteTask(ReturnMaterialTaskBean o) {
		Integer res = dao.deleteTask(o);
		List<ReturnMaterialTaskBean> list=dao.findMaIdList(o);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String maId=list.get(i).getMaId();
				ReturnMaterialTaskBean bean= new ReturnMaterialTaskBean();
				bean.setId(maId);
				dao.updateBatch(bean);
			}
		}
		return res;
	}

}
