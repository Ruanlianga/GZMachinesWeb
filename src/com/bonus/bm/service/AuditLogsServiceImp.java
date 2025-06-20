package com.bonus.bm.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.bm.beans.AuditLogsBean;
import com.bonus.bm.dao.AuditLogsDao;
import com.bonus.core.DateTimeHelper;
import com.bonus.sys.BaseServiceImp;

@Service("auditLogs")
public class AuditLogsServiceImp extends BaseServiceImp<AuditLogsBean> implements AuditLogsService{

	@Autowired AuditLogsDao dao;

	@Override
	public int addAuditLogs(HttpServletRequest request,String userId, String url, String module, String method,String des, String state) {
		String time = DateTimeHelper.getNowTime();
//		InetAddress address;
		String loginIp = "";
//		try {
//			address = InetAddress.getLocalHost();
//			loginIp = address.getHostAddress();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		AuditLogsBean bean = new AuditLogsBean(loginIp, userId, url, module, method, time, des, time, state);
		return dao.addAuditLogs(bean);
	}

}

