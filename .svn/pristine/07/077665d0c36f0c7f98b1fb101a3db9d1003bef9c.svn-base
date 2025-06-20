package com.bonus.lease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.PushtoSingle;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.beans.OutStorageCheckBean;
import com.bonus.lease.dao.OutStorageCheckDao;
import com.bonus.lease.dao.OutStorageDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.dao.UserDao;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("outstorage")
public class OutStorageCheckServiceImp extends BaseServiceImp<OutStorageCheckBean> implements OutStorageCheckService {

	@Autowired
	OutStorageCheckDao dao;

	@Autowired
	TaskRecordDao trDao;

	@Autowired
	OutStorageDao osDao;

	@Autowired
	UserDao uDao;

	private PushtoSingle ps = new PushtoSingle();

	@Override
	public int insertBean(OutStorageCheckBean o) {
		return dao.insertBean(o);
	}

	@Override
	public List<OutStorageCheckBean> getOutStorageCheckList(OutStorageCheckBean o) {
		// TODO Auto-generated method stub
		return dao.getOutStorageCheckList(o);
	}

	@Override
	public int doCheck(OutStorageCheckBean o) {
		int res = 1;
		try {
			dao.doCheck(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return -1;// 数据插入出错
		}
		return res;
	}

	@Override
	public List<OutStorageCheckBean> getOutStorageCheckDetails(OutStorageCheckBean o) {
		// TODO Auto-generated method stub
		return dao.getOutStorageCheckDetails(o);
	}

	@Override
	public List<OutStorageCheckBean> getModelInfo(OutStorageCheckBean o) {
		// TODO Auto-generated method stub
		return dao.getModelInfo(o);
	}

	@Override
	public void updateAlCheckNum(OutStorageCheckBean o) {
		dao.updateAlCheckNum(o);

	}

	@Override
	public void addCheckRecord(OutStorageCheckBean o) {
		dao.addCheckRecord(o);

	}

	@Override
	public OutStorageCheckBean getAlCheckNum(OutStorageCheckBean o) {
		// TODO Auto-generated method stub
		return dao.getAlCheckNum(o);
	}

	@Override
	public void buildOutPutTask(OutStorageCheckBean o) {
		dao.isSure(o);
		//修改总检验任务状态
		isFinish(o);
		//查询任务id
		List<OutStorageCheckBean> osList = dao.findSupId(o);
		OutStorageBean outBean = new OutStorageBean();
		outBean.setTaskId(osList.get(0).getTaskId());
		//根据任务Id查询任务
		List<OutStorageBean> list = osDao.findOutTask(outBean);
		OutStorageCheckBean bean = dao.findById(o);
		o.setAlCheckNum(bean.getAlCheckNum());
		o.setOutPersonId(bean.getOutPersonId());
		// 判断该领料任务下有无出库任务
		if (list != null && list.size() > 0) {
			o.setTaskId(list.get(0).getId());
			addOutDetail(o);
		} else {
			o.setTaskId(bean.getTaskId());
			buildOutTask(o);
		}
		UserBean uBean = new UserBean();
		int id = Integer.parseInt(bean.getOutPersonId());
		uBean = uDao.findCIdByUserId(id);
		ps.push(uBean.getcId(), "机具领料", "您有一条新的出库任务，请注意查收");
	}

	// 创建出库任务
	public void buildOutTask(OutStorageCheckBean o) {
		TaskRecordBean trBean = new TaskRecordBean();
		trBean.setOperationTime(DateTimeHelper.getNowTime());
		trBean.setOperationUserId(o.getCheckerId());
		trBean.setDefinitionId("5");
		trBean.setProcessId("2");
		trBean.setIsFinish("0");
		trBean.setTaskId(o.getTaskId());
		trDao.insert(trBean);
		o.setTaskId(trBean.getId());
		addOutDetail(o);
	}

	// 添加出库明细
	public void addOutDetail(OutStorageCheckBean o) {
		OutStorageBean osBean = new OutStorageBean();
		osBean.setTaskId(o.getTaskId());
		osBean.setPreCollerNum(o.getAlCheckNum());
		osBean.setMaModelId(o.getMaModelId());
		osBean.setOutPersonId(o.getOutPersonId());
		osBean.setOutTime(DateTimeHelper.getNowTime());
		osDao.insert(osBean);
	}
	
	// 修改总任务状态
		public void isFinish(OutStorageCheckBean o) {
			List<OutStorageCheckBean> list = dao.findIsFinish(o);
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
	public List<OutStorageCheckBean> getCheckInfoByNum(OutStorageCheckBean o) {
		// TODO Auto-generated method stub
		return dao.getCheckInfoByNum(o);
	}

	@Override
	public List<OutStorageCheckBean> getCheckRecordList(OutStorageCheckBean o) {
		// TODO Auto-generated method stub
		return dao.getCheckRecordList(o);
	}

	@Override
	public OutStorageCheckBean confirmCheckTask(OutStorageCheckBean o) {
		// TODO Auto-generated method stub
		return dao.confirmCheckTask(o);
	}

	@Override
	public Page<OutStorageCheckBean> outStorageCheckQuery(OutStorageCheckBean o, Page<OutStorageCheckBean> page) {
		page.setResults(dao.outStorageCheckQuery(o, page));
		return page;
	}

}
