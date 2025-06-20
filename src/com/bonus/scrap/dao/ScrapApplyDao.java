package com.bonus.scrap.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.scrap.beans.ScrapApplyBean;
import com.bonus.scrap.beans.ScrapApplyFileBean;
import com.bonus.sys.BaseDao;
import org.apache.ibatis.annotations.Param;

@BonusBatis
public interface ScrapApplyDao extends BaseDao<ScrapApplyBean> {

	List<ScrapApplyBean> findParentTypeList(ScrapApplyBean bean);
	List<ScrapApplyBean> findTypeList(ScrapApplyBean bean);
	List<ScrapApplyBean> findMaCodeList(ScrapApplyBean bean);
	Integer insertFromBean(ScrapApplyBean o);
	Integer insertDetailsBean(ScrapApplyBean scrapApplyBean);
	List<String> findExistsMaCodeList(@Param("maIdList") List<String> maIdList);
	int findApplyNowDateNum();
	List<ScrapApplyBean> findInventoryScrapDetailsById(ScrapApplyBean o);
	int fileUpload(ScrapApplyFileBean o);
	List<ScrapApplyFileBean> findFileList(ScrapApplyFileBean o);
	ScrapApplyBean findRemark(ScrapApplyBean o);
}
