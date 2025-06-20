package com.bonus.data.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.data.beans.WarningTipBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;


@BonusBatis
public interface WarningTipDao extends BaseDao<WarningTipBean>{
	Page<WarningTipBean> findTip(Page<WarningTipBean> page,WarningTipBean o );

	List<WarningTipBean> findByCode(WarningTipBean bean);

	List<WarningTipBean> findWranTip(WarningTipBean o);

	void addHisData(WarningTipBean wt);
	
}
