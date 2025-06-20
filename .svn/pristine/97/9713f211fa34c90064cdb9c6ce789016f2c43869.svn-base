package com.bonus.pis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.PushtoSingle;
import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.pis.dao.PutInStorageDao;
import com.bonus.rm.beans.PutInStorageAuditBean;
import com.bonus.rm.service.PutInStorageAuditService;
import com.bonus.rm.service.PutInStorageAuditServiceImp;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("putstorage")
public class PutInStorageServiceImp extends BaseServiceImp<PutInStorageBean> implements PutInStorageService {

	@Autowired
	PutInStorageDao dao;
	
	@Autowired
	TaskRecordDao trDao;
	@Autowired
	PutInStorageAuditService putService;
	private PushtoSingle ps = new PushtoSingle();

	@Override
	public List<PutInStorageBean> putInStorageList(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.putInStorageList(o);
	}

	@Override
	public List<PutInStorageBean> putInStorageDetails(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.putInStorageDetails(o);
	}

	@Override
	public PutInStorageBean getAlPutNum(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getAlPutNum(o);
	}

	@Override
	public void updateAlPutNum(PutInStorageBean o) {
		// TODO Auto-generated method stub
	     dao.updateAlPutNum(o);
	}

	@Override
	public void addPutRecord(PutInStorageBean o) {
		// TODO Auto-generated method stub
		dao.addPutRecord(o);
	}
	
	@Override
	public void updateMachineNum(PutInStorageBean o) {
		// TODO Auto-generated method stub
		dao.updateMachineNum(o);
	}

	@Override
	public List<PutInStorageBean> getPutInfoByNum(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getPutInfoByNum(o);
	}

	@Override
	public List<PutInStorageBean> getPutRecordList(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getPutRecordList(o);
	}

	@Override
	public PutInStorageBean confirmPutTask(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.confirmPutTask(o);
	}

	@Override
	public void updatePutTask(PutInStorageBean o) {
		// TODO Auto-generated method stub
		dao.updatePutTask(o);
		isFinish(o);
		
		PutInStorageAuditBean bean = new PutInStorageAuditBean();
		bean.setId(o.getId());
		bean.setTaskId(o.getTaskId());
		bean.setMaModelId(o.getMaModelId());
		bean.setIsCount(o.getIsCount());
		putService.isAudit(bean);
	}
	
	public void isFinish(PutInStorageBean o) {
		List<PutInStorageBean> list = dao.findIsFinish(o);
		TaskRecordBean trBean = new TaskRecordBean();
		trBean.setId(o.getTaskId());
		if (list == null || list.size() < 1) {
			trBean.setIsFinish("1");
			trDao.update(trBean);
		} else {
			return;
		}
	}
	@Override
	public Page<PutInStorageBean> putInStorageQuery(PutInStorageBean o, Page<PutInStorageBean> page) {
		// TODO Auto-generated method stub
		page.setResults(dao.putInStorageQuery(o,page));
		return page;
	}

	@Override
	public PutInStorageBean findBackMachine(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.findBackMachine(o);
	}
	
	@Override
	public List<PutInStorageBean> machinePutInStorageList(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.machinePutInStorageList(o);
	}
	
	@Override
	public List<PutInStorageBean> machinePutInStorageDetails(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.machinePutInStorageDetails(o);
	}
	
	@Override
	public void updateIsExample(PutInStorageBean o) {
		// TODO Auto-generated method stub
		dao.updateIsExample(o);
	}
	
	@Override
	public void updateIsSure(PutInStorageBean o) {
		// TODO Auto-generated method stub
		dao.updateIsSure(o);
	}
	
	@Override
	public List<PutInStorageBean> machinePutInStorageDetailsFinish(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.machinePutInStorageDetailsFinish(o);
	}

	@Override
	public int updatePutRecordTwo(PutInStorageBean o) {
		// TODO Auto-generated method stub
		return dao.updatePutRecordTwo(o);
	}
	
}
