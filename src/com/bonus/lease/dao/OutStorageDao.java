package com.bonus.lease.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.repair.beans.RepairDetailsBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface OutStorageDao extends BaseDao<OutStorageBean> {

	void isSure(OutStorageBean o);

	OutStorageBean findByTaskId(OutStorageBean o);

	void isExamine(OutStorageBean o);

	void isApproval(OutStorageBean o);

	List<OutStorageBean> findCodeByModel(OutStorageBean o);

	void updateStatus(OutStorageBean o);

	List<OutStorageBean> getOutStorageList(OutStorageBean o);

	List<OutStorageBean> getOutStorageDetailsList(OutStorageBean o);

	OutStorageBean getAlOutNum(OutStorageBean o);

	int updateAlOutNum(OutStorageBean o);

	void addOutRecord(OutStorageBean o);
	
	public List<OutStorageBean> getOutInfoByNum(OutStorageBean o);

	OutStorageBean confirmOutTask(OutStorageBean o);

	List<OutStorageBean> getOutRecordList(OutStorageBean o);

	List<OutStorageBean> outStorageQuery(@Param("param") OutStorageBean o, Page<OutStorageBean> page);

	List<OutStorageBean> findOutTask(OutStorageBean outBean);

	List<OutStorageBean> findIsSureInfo(OutStorageBean o);

	List<OutStorageBean> findApproval(@Param("param") OutStorageBean o, Page<OutStorageBean> page);

	int cancelOutTask(OutStorageBean o);

	int updateCollarTask(OutStorageBean o);

	int updateOutTask(OutStorageBean o);

	OutStorageBean findSomeId(OutStorageBean o);

	List<OutStorageBean> findQueryContent(@Param("param")Page<OutStorageBean> page, OutStorageBean o);

	List<OutStorageBean> findIsFinishById(OutStorageBean bean);

	void updateOutNum(OutStorageBean osbean);
	
	List<OutStorageBean> findCodeBySupIdAndModId(OutStorageBean o);

	List<RepairDetailsBean> findOutSum(OutStorageBean o);

	List<OutStorageBean> getRepeatInfo(OutStorageBean o);

	void rejectExamine(OutStorageBean o);

	OutStorageBean findOutTaskInfo(OutStorageBean o);

	int delOutTask(OutStorageBean bean);

	int delOutDetail(OutStorageBean bean);

	int updateExaStatus(OutStorageBean o);

	List<OutStorageBean> findBackApproval(@Param("param") OutStorageBean o, Page<OutStorageBean> page);

	List<OutStorageBean> findAlredyCollarNumList(OutStorageBean o);

	void delCollarDetaiils(OutStorageBean o);
	
	void delOutStockInfo(String id);

	List<OutStorageBean> findInfoRecord(String id);

	void updateMachinesBatchStats(String maId);

	void delInfoRecord(@Param("id") String id,@Param("maModelId") String maModelId);

	void addBackRecord(@Param("taskId") String taskId,@Param("maModelId")  String maModelId,@Param("maId") String maId,@Param("outNum") String outNum,@Param("operationTime") String operationTime,@Param("userId") int userId);

	String findTypeNum(String maModelId);

	void updateTypeNum(@Param("num") String num,@Param("maModelId") String maModelId);

	void delCollarTask(String taskId);

	OutStorageBean getOutTask(OutStorageBean o);

	void updateOutStatus(OutStorageBean bean);

	void updateStorageNum(OutStorageBean r);

	OutStorageBean getPreOutInfo(OutStorageBean o);

	List<OutStorageBean> getSameInfo(OutStorageBean o);
	
	/**
	 * 查询领料批准状态
	 * @param o
	 * @return
	 */
	String   getIsApprovalStatus(OutStorageBean o);

	void delBmLogs(OutStorageBean o);

}
