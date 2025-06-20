package com.bonus.scrap.dao;



import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.scrap.beans.ScrapAuditBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface ScrapAuditDao extends BaseDao<ScrapAuditBean> {

	int insertBean(ScrapAuditBean audit);

	int insertDetails(ScrapAuditBean details);

	ScrapAuditBean findScrapApplyById(ScrapAuditBean o);

	Integer updateBean(ScrapAuditBean o);

	int updateISF(ScrapAuditBean o);

	int updateBe(ScrapAuditBean o);

	int updateRF(ScrapAuditBean scrapAuditBean);
	
	int updateStatus(ScrapAuditBean o);

	ScrapAuditBean getApply(ScrapAuditBean o);
	
	int updateApplyStatus(ScrapAuditBean o);
	
	ScrapAuditBean findScrapFileById(ScrapAuditBean o);

}
