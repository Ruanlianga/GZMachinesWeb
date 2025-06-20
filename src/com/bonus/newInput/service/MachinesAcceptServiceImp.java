package com.bonus.newInput.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.newInput.beans.MachinesAcceptBean;
import com.bonus.newInput.dao.MachinesAcceptDao;
import com.bonus.sys.BaseServiceImp;

@Service("MachinesAcceptService")
public class MachinesAcceptServiceImp extends BaseServiceImp<MachinesAcceptBean> implements MachinesAcceptService {

	@Autowired
	private MachinesAcceptDao dao;
	@Override
	public List<MachinesAcceptBean> findInput(MachinesAcceptBean o) {
		return dao.findInput(o);
	}
	@Override
	public String findCode(MachinesAcceptBean o) {
		return dao.findCode(o);
	}
	@Override
	public List<MachinesAcceptBean> findParts(MachinesAcceptBean o) {
		return dao.findParts(o);
	}
	@Override
	public void updAcpNum(MachinesAcceptBean o) {
		dao.updAcpNum(o);
		
	}
	

}
