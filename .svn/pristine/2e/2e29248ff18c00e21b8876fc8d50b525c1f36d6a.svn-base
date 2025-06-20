package com.bonus.sys.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.StationBean;

@BonusBatis
public interface StationDao extends BaseDao<StationBean> {

    int deleteByPrimaryKey(Integer id);

    int insertBean(StationBean record);

    int insertSelective(StationBean record);

    StationBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StationBean record);

    int updateByPrimaryKey(StationBean record);
    
    public List<StationBean> findPostName(StationBean o);
}