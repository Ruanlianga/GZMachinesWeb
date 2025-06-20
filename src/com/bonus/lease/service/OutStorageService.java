package com.bonus.lease.service;

import java.util.List;

import com.bonus.lease.beans.OutStorageBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface OutStorageService extends BaseService<OutStorageBean> {

	void buildAuditingTask(OutStorageBean o);

	String isExamine(OutStorageBean o);

	String isApproval(OutStorageBean o);

	List<OutStorageBean> getOutStorageList(OutStorageBean o);

	List<OutStorageBean> getOutStorageDetailsList(OutStorageBean o);

	OutStorageBean getAlOutNum(OutStorageBean o);

	int updateAlOutNum(OutStorageBean o);

	void addOutRecord(OutStorageBean o);
	
	public List<OutStorageBean> getOutInfoByNum(OutStorageBean o);

	OutStorageBean confirmOutTask(OutStorageBean o);

	List<OutStorageBean> getOutRecordList(OutStorageBean o);

	Page<OutStorageBean> outStorageQuery(OutStorageBean o, Page<OutStorageBean> page);

	Page<OutStorageBean> findApproval(OutStorageBean o, Page<OutStorageBean> page);

	Integer cancelOutTask(OutStorageBean o);

	Integer updateCollarTask(OutStorageBean o);

	Integer updateOutTask(OutStorageBean o);

	OutStorageBean findSomeId(OutStorageBean o);

	Page<OutStorageBean> findQueryContent(Page<OutStorageBean> page, OutStorageBean o);

	List<OutStorageBean> findIsFinishById(OutStorageBean bean);

	void updateOutNum(OutStorageBean osbean);

	List<OutStorageBean> findCodeBySupIdAndModId(OutStorageBean o);


	void addStorageData(OutStorageBean o);

	String rejectExamine(OutStorageBean o);

	String rejectApproval(OutStorageBean o);

	Page<OutStorageBean> findBackApproval(OutStorageBean o, Page<OutStorageBean> page);

	String isBackApproval(OutStorageBean o);

	void updateStorageNum(OutStorageBean r);

}
