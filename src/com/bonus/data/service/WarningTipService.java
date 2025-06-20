package com.bonus.data.service;

import java.util.List;

import com.bonus.data.beans.WarningTipBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface WarningTipService extends BaseService<WarningTipBean>{

	Page<WarningTipBean> findTip(WarningTipBean bean, Page<WarningTipBean> page);

	List<WarningTipBean> findByCode(WarningTipBean bean);

	List<WarningTipBean> findWranTip(WarningTipBean o);

}
