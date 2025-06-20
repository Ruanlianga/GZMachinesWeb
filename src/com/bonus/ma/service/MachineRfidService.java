package com.bonus.ma.service;

import java.util.List;

import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.sys.BaseService;

public interface MachineRfidService extends BaseService<MachineRfidBean>{
	
	public int insertBean(MachineRfidBean o);

	public List<MachineRfidBean> findMachineByCode(MachineRfidBean o);

	public int submitRfidBind(MachineRfidBean o);

	public List<MachineRfidBean> findMachineByEpcCode(MachineRfidBean o);

	public List<MachineRfidBean> findListByEpcCode(MachineRfidBean o);

	public int submitRfidOut(MachineRfidBean o);

	public List<MachineRfidBean> getBaseList(MachineRfidBean o);

	public int confirmOutTask(MachineRfidBean o);


	public List<MachineRfidBean> getRfidDeviceInfo(MachineRfidBean o);

	public int submitRfidPut(MachineRfidBean o);

	public int confirmPutTask(MachineRfidBean o);

	public List<MachineRfidBean> findMachineListByEpcCode(MachineRfidBean o);
	
	public List<MachineRfidBean> getRfidNMachineNum(MachineRfidBean o);
	
	public List<MachineRfidBean> getMachineDetails(MachineRfidBean o);
	
	public MachineRfidBean getRfidNMachineStatus(MachineRfidBean o);

}
