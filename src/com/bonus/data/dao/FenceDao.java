package com.bonus.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.data.beans.FenceBean;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface FenceDao extends BaseDao<FenceBean>{

	/*List<DeviceEquipBean> getEquip(@Param("param") DeviceEquipBean bean);*/

	List<FenceBean> findFence( @Param("param") FenceBean bean);

	List<FenceBean> findNoPage( @Param("param") FenceBean bean);
}
