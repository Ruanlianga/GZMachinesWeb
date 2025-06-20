package com.bonus.ma.service;

import java.util.List;

import com.bonus.ma.beans.MachineRfidInfoBean;
import com.bonus.sys.BaseService;

public interface MachineRfidInfoService extends BaseService<MachineRfidInfoBean>{

	List<MachineRfidInfoBean> findMachineByEpcCode(MachineRfidInfoBean o);

	int submitRfidEnter(MachineRfidInfoBean o);

	List<MachineRfidInfoBean> findListByEpcCode(MachineRfidInfoBean o);
	
	

}
