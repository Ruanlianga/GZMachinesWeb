package com.bonus.newInput.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.newInput.beans.MachinesAcceptBean;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface MachinesAcceptDao extends BaseDao<MachinesAcceptBean>{

	List<MachinesAcceptBean> findInput(MachinesAcceptBean o);
	
	String  findCode(MachinesAcceptBean o);
	
	List<MachinesAcceptBean> findParts(MachinesAcceptBean o);
	
	void updAcpNum(MachinesAcceptBean o);
}
