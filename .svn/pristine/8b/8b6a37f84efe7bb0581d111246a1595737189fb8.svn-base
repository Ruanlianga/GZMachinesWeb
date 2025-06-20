package com.bonus.ma.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineRfidBean;
import com.bonus.pis.beans.PutInStorageBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface MachineRfidDao extends BaseDao<MachineRfidBean> {

	public int insertBean(MachineRfidBean o);

	public List<MachineRfidBean> findMachineByCode(MachineRfidBean o);

	public int insertRfidBind(MachineRfidBean o);

	public int updateMaEpcCode(MachineRfidBean o);

	public List<MachineRfidBean> findMachineByEpcCode(MachineRfidBean o);

	public List<MachineRfidBean> findListByEpcCode(MachineRfidBean o);

	public MachineRfidBean getAlOutNum(MachineRfidBean o);

	public void updateAlOutNum(MachineRfidBean o);

	public void updateMachineStatus(MachineBean bean);

	public void addOutRecord(MachineRfidBean o);

	public List<MachineRfidBean> getBaseList(MachineRfidBean o);

	public List<MachineRfidBean> getRfidDeviceInfo(MachineRfidBean o);

	public MachineRfidBean confirmOutTask(MachineRfidBean o);

	public void updateIsSure(MachineRfidBean o);

	public List<MachineRfidBean> findIsSureInfo(MachineRfidBean o);

	public MachineRfidBean getAlPutNum(MachineRfidBean o);

	public int updateAlPutNum(MachineRfidBean o);

	public int addPutRecord(MachineRfidBean o);

	public MachineRfidBean confirmPutTask(MachineRfidBean o);

	public int updatePutIsSure(MachineRfidBean o);

	public List<MachineRfidBean> findIsFinish(MachineRfidBean o);

	public List<MachineRfidBean> findMachineListByEpcCode(MachineRfidBean o);
	
	public List<MachineRfidBean> getRfidNMachineNum(MachineRfidBean o);
	
	public List<MachineRfidBean> getMachineDetails(MachineRfidBean o);
	
	public MachineRfidBean getRfidNMachineStatus(MachineRfidBean o);

}
