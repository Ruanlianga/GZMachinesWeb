package com.bonus.scrap.service;

import java.util.List;

import com.bonus.scrap.beans.ScrapAuditBean;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseService;

public interface ScrapAuditService extends BaseService<ScrapAuditBean>{

	int insertBean(ScrapAuditBean audit);

	int insertDetails(ScrapAuditBean details);

	ScrapAuditBean findScrapApplyById(ScrapAuditBean o);

	AjaxRes saveScrapResult(ScrapAuditBean o);

	AjaxRes rejectScrapApply(ScrapAuditBean o);

	ScrapAuditBean findScrapFileById(ScrapAuditBean o);

}
