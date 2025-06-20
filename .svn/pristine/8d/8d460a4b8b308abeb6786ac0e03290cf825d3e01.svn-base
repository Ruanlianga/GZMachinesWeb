package com.bonus.rm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.rm.dao.PutInStorageTaskDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("putTask")
public class PutInStorageTaskServiceImp extends BaseServiceImp<PutInStorageTaskBean>
		implements PutInStorageTaskService {

	@Autowired PutInStorageTaskDao dao;

	@Autowired
	TaskRecordDao trDao;

	@Override
	public String updatePutPerson(PutInStorageTaskBean o) {
		String isSure = o.getIsSure();
		String res = "1";
		if("0".equals(isSure)){
			dao.updatePutServer(o);
		}else{
			dao.updatePutPerson(o);
		}
		
		if (res == "1") {
			res = "成功发布";
		}
		else {
			res = "发布失败 ";
		}
		return res;
	}
	
	@Override
	public String isRelease(PutInStorageTaskBean o) {
		dao.update(o);
		List<PutInStorageTaskBean> list = dao.findPutInTaskNum(o);
		try {
			if (list != null && list.size() > 0) {
				o.setTaskId(list.get(0).getTaskId());
				addTaskDetails(o);
			} else {
				addPutInTask(o);
			}
			return "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return "-1";
		}
	}

	private void addPutInTask(PutInStorageTaskBean o) {
		TaskRecordBean bean = new TaskRecordBean();
		bean.setDefinitionId("11");
		bean.setIsFinish("0");
		bean.setTaskId(o.getTaskId());
		bean.setProcessId("3");
		String userId = UserShiroHelper.getRealCurrentUser().getId() + "";
		bean.setOperationUserId(userId);
		bean.setRemark(o.getRemark());
		bean.setOperationTime(DateTimeHelper.getNowTime());
		trDao.insert(bean);
		o.setTaskId(bean.getId());
		addTaskDetails(o);
	}

	private void addTaskDetails(PutInStorageTaskBean o) {
		o.setPutTime(DateTimeHelper.getNowTime());
		dao.insert(o);
	}

	@Override
	public int deleteReturn(PutInStorageTaskBean o) {
		int res = 0;
		try {
			
			res = deletePutTask(o);
			
			res = updateRepairNum(o);
			
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		return res;
	}

	private int updateRepairNum(PutInStorageTaskBean o) {
		int res = 0;
		PutInStorageTaskBean bean = dao.getRepairInfo(o);
		
		if(bean !=null ){
			String maModelId = o.getMaModelId();
			String preNum  = o.getPrePutNum();
			bean.setMaModelId(maModelId);
			bean.setPrePutNum(preNum);
			res = dao.updateRepairNum(bean);
		}
		return res;
	}

	private int deletePutTask(PutInStorageTaskBean o) {
		int res = 0;
		
		res = dao.deletePut(o);
		
		List<PutInStorageTaskBean> infoList = dao.getWfInfoRecord(o);
		
		if(infoList != null && infoList.size()>0){
			for(PutInStorageTaskBean info : infoList){
				String deviceCode = info.getDeviceCode();
				if(StringHelper.isNotEmpty(deviceCode)){
					String modelId = info.getMaModelId();
					dao.updateMaStatus(deviceCode,modelId);
					
					PutInStorageTaskBean bean = dao.getRmInfo(deviceCode,modelId);
					if(bean !=null ){
						dao.updateRmStatus(bean);
					}
					
				}
				dao.deleteInfo(info);
			}
		}
		
		List<PutInStorageTaskBean> putList = dao.getPutInfoList(o);
		
		if(putList !=null && putList.size()>0 ){
			
		}else{
			dao.deletePutTask(o);
		}
		
		return res;
		
	}

}
