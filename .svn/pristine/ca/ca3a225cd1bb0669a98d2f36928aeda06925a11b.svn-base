package com.bonus.ma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.MachineRfidInfoBean;
import com.bonus.ma.dao.MachineRfidInfoDao;
import com.bonus.sys.BaseServiceImp;


@Service("machinerfidinfo")
public class MachineRfidInfoServiceImp extends BaseServiceImp<MachineRfidInfoBean> implements MachineRfidInfoService {

	@Autowired
	private MachineRfidInfoDao dao;

	@Override
	public List<MachineRfidInfoBean> findMachineByEpcCode(MachineRfidInfoBean o) {
		return dao.findMachineByEpcCode(o);
	}

	@Override
	public int submitRfidEnter(MachineRfidInfoBean o) {
		return dao.insertRfidEnter(o);
	}

	@Override
	public List<MachineRfidInfoBean> findListByEpcCode(MachineRfidInfoBean o) {
		return dao.findListByEpcCode(o);
	}
	

}
