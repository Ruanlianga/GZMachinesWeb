package com.bonus.ma.service;

import java.util.List;

import com.bonus.ma.beans.MaStockBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface MaStockService extends BaseService<MaStockBean> {
	
	List<MaStockBean> export(MaStockBean o); // 导出

	Page<MaStockBean> findStockDetails(MaStockBean o, Page<MaStockBean> page);

	Page<MaStockBean> findStockLoss(MaStockBean o, Page<MaStockBean> page);

	List<MaStockBean> findByPage(MaStockBean o);

}
