package com.bonus.ma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.MaLeaseBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface MaLeaseDao extends BaseDao<MaLeaseBean> {

	List<MaLeaseBean> findbackCode(@Param("param")MaLeaseBean o, @Param("t") Page<MaLeaseBean> page);

	List<MaLeaseBean> findleaseCode(@Param("param")MaLeaseBean o, @Param("t") Page<MaLeaseBean> page);

	List<MaLeaseBean> finduseCode(@Param("param")MaLeaseBean o, @Param("t") Page<MaLeaseBean> page);

}
