package com.bonus.rm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.LogBean;
import com.bonus.bm.dao.LogDao;
import com.bonus.core.DateTimeHelper;
import com.bonus.rm.beans.PutInStorageAuditBean;
import com.bonus.rm.dao.PutInStorageAuditDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.bonus.wf.dao.TaskRecordDao;

@Service("putAudit")
public class PutInStorageAuditServiceImp extends BaseServiceImp<PutInStorageAuditBean>
		implements PutInStorageAuditService {
	
	@Autowired PutInStorageAuditDao dao;

	@Autowired
	TaskRecordDao trDao;
	
	@Autowired
	LogDao logDao;
	@Override
	public String isAudit(PutInStorageAuditBean o) {
		String res = "0";
		//审核入库任务
		res = auditPutTask(o);
		if("1".equals(res)){
			String isCount = o.getIsCount();
			//计数和编码
		    if("0".equals(isCount)){
		    	updateMachineStatus(o);
		    }
			//修改库存
		        updateStorageNum(o);
		}
		return res;
	}

	private void updateStorageNum(PutInStorageAuditBean o) {
		List<PutInStorageAuditBean> list = dao.findPutTaskInfo(o);
		if(list.size() > 0 && list != null){
			for(PutInStorageAuditBean bean : list){
				//添加日志
				LogBean logBean= new LogBean();
				logBean.setModel("入库审核");
				logBean.setFun("isAudit");
				logBean.setTask(o.getTaskId());
				logBean.setTypeId(o.getMaModelId());
				LogBean inBean=logDao.findInNum(logBean);
				String description="在库数:"+inBean.getInNum()+";入库数:"+bean.getAlPutNum();
				logBean.setDescription(description);
				logBean.setTime(DateTimeHelper.currentDateTime());
//				logBean.setCreator(UserShiroHelper.getRealCurrentUser().getId()+"");
				logDao.insertLog(logBean);
				
				float storageNum = Float.parseFloat(bean.getStorageNum());
			    float alPutNum = Float.parseFloat(bean.getAlPutNum());
			    storageNum = storageNum + alPutNum;
			    
			    bean.setStorageNum(storageNum+"");
				dao.updateStorageNum(bean);
			}
		}
	}

	private void updateMachineStatus(PutInStorageAuditBean o) {
		List<PutInStorageAuditBean> list = dao.findPutMaInfo(o);
		if(list != null && list.size() > 0){
			for(PutInStorageAuditBean bean : list){
				String machineStatus = bean.getMachineStatus();
				if("18".equals(machineStatus)){//入库审核
					bean.setMachineStatus("5");
				}
				dao.updateMachineStatus(bean);
			}
		}
	}

	private String auditPutTask(PutInStorageAuditBean o) {
		String res ="0";
		String auditTime = DateTimeHelper.getNowTime();
		o.setAuditTime(auditTime);
		try {
			dao.auditPutTask(o);
			res = "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}
		return res;
	}


	
}
