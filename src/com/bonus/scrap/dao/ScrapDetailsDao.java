package com.bonus.scrap.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.scrap.beans.ScrapDetailsBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface ScrapDetailsDao extends BaseDao<ScrapDetailsBean> {

	void addScrapReason(ScrapDetailsBean o);

	List<ScrapDetailsBean> findScrapDevice(ScrapDetailsBean o);

	List<ScrapDetailsBean> findScrapTask(ScrapDetailsBean o);

	List<ScrapDetailsBean> findScrapTaskDetails(ScrapDetailsBean o);

	void updateInfo(ScrapDetailsBean o);

	ScrapDetailsBean findAlScrapNum(ScrapDetailsBean o);

	ScrapDetailsBean findInfoNums(ScrapDetailsBean o);

	void updateScrapNum(ScrapDetailsBean sdBean);

	ScrapDetailsBean findInfoId(ScrapDetailsBean o);

	List<ScrapDetailsBean> findCheckInfo(ScrapDetailsBean o);

	List<ScrapDetailsBean> findReturnScrapDevice(ScrapDetailsBean o);

	ScrapDetailsBean findCheckTaskId(ScrapDetailsBean o);

	void surePeople(ScrapDetailsBean o);

	ScrapDetailsBean getRepeatData(ScrapDetailsBean o);

	List<ScrapDetailsBean> getScrapIndexList(ScrapDetailsBean o);

	List<ScrapDetailsBean> getScrapedIndexList(ScrapDetailsBean o);

	List<ScrapDetailsBean> findScrapCodeList(ScrapDetailsBean o);
	
	List<ScrapDetailsBean> getMatchScrapList(ScrapDetailsBean o);
	
	int updateScrapStatus(ScrapDetailsBean o);
	
	int updateDeviceStatus(ScrapDetailsBean o);
	
	int updateScrapDetails(ScrapDetailsBean o);
	
	int insertScrapRecord(ScrapDetailsBean o);
	
	List<ScrapDetailsBean> findScrapCodeListFinish(ScrapDetailsBean o);

}
