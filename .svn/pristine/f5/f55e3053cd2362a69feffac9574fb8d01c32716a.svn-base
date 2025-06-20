package com.bonus.scrap.service;

import java.util.List;

import com.bonus.scrap.beans.ScrapApplyBean;
import com.bonus.scrap.beans.ScrapApplyFileBean;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseService;

public interface ScrapApplyService extends BaseService<ScrapApplyBean>{

	List<ScrapApplyBean> findParentTypeList(ScrapApplyBean bean);
	List<ScrapApplyBean> findTypeList(ScrapApplyBean bean);
	List<ScrapApplyBean> findMaCodeList(ScrapApplyBean bean);
	AjaxRes saveInventoryScrap(ScrapApplyBean o);
	List<ScrapApplyBean> findInventoryScrapDetailsById(ScrapApplyBean o);
	int fileUpload(ScrapApplyFileBean o);
	List<ScrapApplyFileBean> findFileList(ScrapApplyFileBean o);
	ScrapApplyBean findRemark(ScrapApplyBean o);
}
