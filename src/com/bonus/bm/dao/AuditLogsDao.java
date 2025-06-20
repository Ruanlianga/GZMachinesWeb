package com.bonus.bm.dao;

import com.bonus.bm.beans.AuditLogsBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface AuditLogsDao extends BaseDao<AuditLogsBean>{

	int addAuditLogs(AuditLogsBean bean);

}
