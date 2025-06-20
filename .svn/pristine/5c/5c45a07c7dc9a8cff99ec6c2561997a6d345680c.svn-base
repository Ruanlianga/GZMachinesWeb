package com.bonus.lease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.PushtoSingle;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.lease.beans.OutStorageCheckBean;
import com.bonus.lease.beans.ReceiveDetailsBean;
import com.bonus.lease.dao.OutStorageCheckDao;
import com.bonus.lease.dao.OutStorageDao;
import com.bonus.lease.dao.ReceiveDetailsDao;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.newSettlement.dao.MaTypeProjectStorageDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.dao.UserDao;
import com.bonus.wf.beans.TaskRecordBean;
import com.bonus.wf.dao.TaskRecordDao;

@Service("receiveDetails")
public class ReceiveDetailsServiceImp extends BaseServiceImp<ReceiveDetailsBean> implements ReceiveDetailsService{

	@Autowired ReceiveDetailsDao dao;
	
	@Autowired TaskRecordDao trDao;
	
	@Autowired OutStorageCheckDao osDao;
	
	@Autowired UserDao uDao;
	
	@Autowired MaTypeProjectStorageDao storageDao;
	
	@Autowired OutStorageDao outDao;
	
	 @Autowired MachineDao maDao;
	
	private PushtoSingle ps = new PushtoSingle();
	@Override
	public int insertBean(ReceiveDetailsBean o) {
		int res = 1;
		try {
			res= dao.insertBean(o);
		} catch (Exception e) {
			return -1;
		}
		return res;
	}
	
	@Override
	public List<ReceiveDetailsBean> findBean(ReceiveDetailsBean o) {
		List<ReceiveDetailsBean> list = dao.findBean(o);
		return list;
	}
	
	@Override
	public void updateBean(ReceiveDetailsBean o) {
		dao.updateBean(o);
	}

	@Override
	public ReceiveDetailsBean getMachinesNum(ReceiveDetailsBean o) {
		return dao.getMachinesNum(o);
	}

	@Override
	public List<ReceiveDetailsBean> getAllReceiveTask(ReceiveDetailsBean o) {
		List<ReceiveDetailsBean> list = dao.getAllReceiveTask(o);
		return list;
	}

	@Override
	public List<ReceiveDetailsBean> findMaReceiveMsg(ReceiveDetailsBean o) {
		return dao.findMaReceiveMsg(o);
	}

	@Override
	@Transactional
	public String isSure(ReceiveDetailsBean o) {
		try {
			dao.isSure(o);
			//修改总任务状态
			isFinish(o);
			OutStorageCheckBean bean = new OutStorageCheckBean();
			bean.setTaskId(o.getTaskId());
			List<OutStorageCheckBean> list = osDao.findCheckCount(bean);
			if (list != null && list.size() > 0) {
				o.setTaskId(list.get(0).getId());
				insertCheckDetails(o);
			}else{
				buildCheckTask(o);
				UserBean uBean = new UserBean();
				int id = Integer.parseInt(o.getCheckerId());
				uBean = uDao.findCIdByUserId(id);
				if(uBean!=null){
					ps.push(uBean.getcId(), "机具领料", "您有一条新的机具领料任务，请注意查收");
				}
				
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return "-1";
		}
		return "1";
	}
	
	public void isFinish(ReceiveDetailsBean o){
		String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
		List<ReceiveDetailsBean> list = dao.findIsFinish(o);
		if (list != null && list.size() > 0) {
			return;
		}else{
			TaskRecordBean trBean = new TaskRecordBean();
			trBean.setCompanyId(companyId);
			trBean.setId(o.getTaskId());
			trBean.setIsFinish("1");
			trDao.update(trBean);
			
		}
	}
	/**
	 * 创建领料出库任务 注：原为创建出库检验任务，现取消检验流程，直接生成出库任务
	 * @param o
	 */
	public void buildCheckTask(ReceiveDetailsBean o) {
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			String userId = UserShiroHelper.getRealCurrentUser().getId()+"";
			TaskRecordBean bean = new TaskRecordBean();
			bean.setOperationTime(DateTimeHelper.getNowTime());
			bean.setOperationUserId(userId);
			bean.setDefinitionId("5");
			bean.setProcessId("2");
			bean.setIsFinish("0");
			bean.setCompanyId(companyId);
			bean.setTaskId(o.getTaskId());
			trDao.insert(bean);
			o.setTaskId(bean.getId());
			insertCheckDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	/**
	 * 添加检验明细
	 * @param o
	 * @anthor gqk
	 */
	public void insertCheckDetails(ReceiveDetailsBean o) {
//		OutStorageCheckBean bean = new OutStorageCheckBean();
//		bean.setPreCheckNum(o.getPreCollerNum());
//		bean.setTaskId(o.getTaskId());
//		bean.setCheckTime(DateTimeHelper.getNowTime());
//		bean.setMachineModel(o.getMaModelId());
//		bean.setCheckerId(o.getCheckerId());
		OutStorageBean osBean = new OutStorageBean();
		osBean.setTaskId(o.getTaskId());
		osBean.setPreCollerNum(o.getPreCollerNum());
		osBean.setMaModelId(o.getMaModelId());
		osBean.setOutPersonId(o.getCheckerId());//客服代表
		osBean.setOutTime(DateTimeHelper.getNowTime());
		outDao.insert(osBean);
	}
	
	@Override
	public void allSure(ReceiveDetailsBean o) {
		dao.allSure(o);
	}

	@Override
	public ReceiveDetailsBean getPreMachinesNum(ReceiveDetailsBean o) {
		return dao.getPreMachinesNum(o);
	}

	@Override
	public void add(ReceiveDetailsBean o) {
		dao.insertBean(o);
	}

	@Override
	public Page<ReceiveDetailsBean> findAuditing(ReceiveDetailsBean o, Page<ReceiveDetailsBean> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReceiveDetailsBean> findTaskId(ReceiveDetailsBean o) {
		List<ReceiveDetailsBean> list = dao.findTaskId(o);
		return list;
	}

	@Override
	public void delOutTask(ReceiveDetailsBean o) {
		List<ReceiveDetailsBean> list = dao.findMachineDetails(o);
		for(ReceiveDetailsBean bean : list){
			MachineBean ma = new MachineBean();
			ma.setId(bean.getMaId());
			ma.setBatchStatus("5");
			maDao.update(ma);
		}
		dao.delOutTask(o);
		dao.delOutTaskRecord(o);
	}

	@Override
	public String batchDeletion(ReceiveDetailsBean o) {
		// TODO Auto-generated method stub
		int num=dao.batchDeletion(o);
		if(num==1){
			return "1";
		}else{
			return "0";
		}
	}

}
