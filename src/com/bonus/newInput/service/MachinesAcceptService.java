package com.bonus.newInput.service;

import java.util.List;

import com.bonus.newInput.beans.MachinesAcceptBean;
import com.bonus.sys.BaseService;

public interface MachinesAcceptService extends BaseService<MachinesAcceptBean> {

	List<MachinesAcceptBean> findInput(MachinesAcceptBean o);
	
	String  findCode(MachinesAcceptBean o);
	
	List<MachinesAcceptBean> findParts(MachinesAcceptBean o);
	
	void updAcpNum(MachinesAcceptBean o);
}
