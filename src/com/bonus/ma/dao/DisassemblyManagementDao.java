package com.bonus.ma.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.DisassemblyManagementBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.sys.BaseDao;

/**
 * 机具拆分管理 数据库接口层
 * @author xj
 *
 *
 */
@BonusBatis
public interface DisassemblyManagementDao extends  BaseDao<DisassemblyManagementBean>{
	
	/**
	 * 依据id查询相关信息
	 * @param o
	 * @return
	 */
	List<DisassemblyManagementBean> getListDataById(DisassemblyManagementBean o);
	/**
	 * 依据设备code查询设备信息
	 * @param o
	 * @return
	 */
	List<DisassemblyManagementBean> getListDataByDeviceCode(DisassemblyManagementBean o);
	//流水主表
	void insertData(DisassemblyManagementBean vo);
	//数据历史设备记录表
	void insertRecode(DisassemblyManagementBean vo);
	//生成新生记录表
	void inserNewRecode(DisassemblyManagementBean vo);
	/**
	 * 查询在库数量进行计算
	 * @param bean
	 * @return
	 */
	List<MachineBean> getOrgRelationNum(MachineBean bean);
	/**
	 * 库存数量更改
	 * @param vo
	 */
	void updaDataeRelationNum(MachineBean vo);
	/**
	 * 获取机具类型
	 * @param bean
	 * @return
	 */
	List<DisassemblyManagementBean> getInitSelect(DisassemblyManagementBean bean); 

}
