package com.bonus.rm.service;


import com.bonus.rm.beans.ReturnAuditBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;


public interface ReturnAuditService extends BaseService<ReturnAuditBean>{

	String isExamine(ReturnAuditBean o);
	
	String isApproval(ReturnAuditBean o);

	String putInExamine(ReturnAuditBean o);

	Page<ReturnAuditBean> findPutInAudit(ReturnAuditBean o, Page<ReturnAuditBean> page);

	String batchAudit(ReturnAuditBean o);

	String putCancelExamine(ReturnAuditBean o);

	Page<ReturnAuditBean> findAuditByPage(ReturnAuditBean o, Page<ReturnAuditBean> page);

	String putCancelApproval(ReturnAuditBean o);

}
