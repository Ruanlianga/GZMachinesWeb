package com.bonus.ma.service;

import java.util.List;
import java.util.Map;

import com.bonus.ma.beans.DisassemblyManagementBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

/**
 * 机具拆逻辑处理层
 * @author xj
 */
public interface DisassemblyManagementService extends BaseService<DisassemblyManagementBean>{
	/**
	 * 依据id查询信息
	 * @param o
	 * @return
	 */
	List<DisassemblyManagementBean> getListDataById(DisassemblyManagementBean o);
	/**
	 * 依据设备code 查询对应信息
	 * @param o
	 * @return
	 */
	List<DisassemblyManagementBean> getListDataByDeviceCode(DisassemblyManagementBean o);
	/**
	 * 数据拆分处理
	 * @param o
	 * @return
	 */
	String insertDatasplit(DisassemblyManagementBean o);
	/**
	 * 机具合成处理
	 * @param o
	 * @return
	 */
	String addDataSynthesis(DisassemblyManagementBean o);
	/**
	 * 获取机具下拉选
	 * @return
	 */
	Map<String, Object> getInitSelect(DisassemblyManagementBean o);
	
	
	

}
