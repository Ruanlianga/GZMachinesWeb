package com.bonus.bm.service;

import javax.servlet.http.HttpServletRequest;

import com.bonus.bm.beans.AuditLogsBean;
import com.bonus.sys.BaseService;

public interface AuditLogsService extends BaseService<AuditLogsBean>{

	int addAuditLogs(HttpServletRequest request,String userId, String url, String module, String method,String des,String state);
	
}
