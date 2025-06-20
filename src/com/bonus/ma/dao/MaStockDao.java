package com.bonus.ma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.MaStockBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface MaStockDao extends BaseDao<MaStockBean> {

	List<MaStockBean> export(@Param("param") MaStockBean o); // 导出

	List<MaStockBean> findStockDetails(@Param("param") MaStockBean o, Page<MaStockBean> page);

	List<MaStockBean> findStockLoss(@Param("param") MaStockBean o, Page<MaStockBean> page);

	List<MaStockBean> findByPage(@Param("param") MaStockBean o);

}
