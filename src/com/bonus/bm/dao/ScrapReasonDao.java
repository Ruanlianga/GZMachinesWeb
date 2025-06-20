package com.bonus.bm.dao;

import java.util.List;

import com.bonus.bm.beans.ScrapReasonBean;
import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface ScrapReasonDao extends BaseDao<ScrapReasonBean>{

	public int insertBean(ScrapReasonBean o);

	public List<ScrapReasonBean> findScrapReason(ScrapReasonBean o);
	
}
