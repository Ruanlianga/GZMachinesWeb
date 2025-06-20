package com.bonus.newInput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.beans.NewInputCheckBean;
import com.bonus.newInput.dao.InputDetailsDao;
import com.bonus.newInput.dao.NewInputDao;
import com.bonus.sys.BaseServiceImp;

@Service("newCheck")
public class NewInputCheckServiceImp extends BaseServiceImp<NewInputCheckBean> implements NewInputCheckService{

	@Autowired 
	private NewInputDao dao;
	
	@Autowired
	MachineDao maDao;
	
	@Autowired
	InputDetailsDao idDao;

	@Autowired
	MachineTypeDao mtDao;
	/**
	 * 1.判断机具是否计数
	 * 2.不计数（生成新购二维码打印任务，生成新购绑定任务，生成生成新购入库任务）
	 * 3.计数（生成新购入库任务）
	 * 
	 */
	@Override
	public int isSure(NewInputCheckBean o) {
		MachineTypeBean mmtBean = new MachineTypeBean();
		mmtBean.setMaModelId(o.getMaModelId());
		MachineTypeBean mBean = mtDao.findByModelId(mmtBean);
		if ("0".equals(mBean.getIsCount())) {
			NewInputCheckBean bean = dao.findByCheckTaskId(o);
			InputDetailsBean idBean = new InputDetailsBean();
			idBean.setTaskId(bean.getTaskId());
			idBean.setMaModelId(o.getMaModelId());
			idBean.setCheckStatus("5");
			idDao.isSure(idBean);
		}else{
			NewInputCheckBean bean = dao.findByCheckTaskId(o);
			InputDetailsBean idBean = new InputDetailsBean();
			idBean.setTaskId(bean.getTaskId());
			idBean.setMaModelId(o.getMaModelId());
			idBean.setCheckStatus("7");
			idDao.isSure(idBean);
		}
		return 1;
	}
	
}
