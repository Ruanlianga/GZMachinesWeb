package com.bonus.sys.service;

import java.util.List;

import com.bonus.sys.BaseService;
import com.bonus.sys.beans.StationBean;

public interface StationService extends BaseService<StationBean> {

    int deleteByPrimaryKey(Integer id);

    int insertBean(StationBean record);

    int insertSelective(StationBean record);

    StationBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StationBean record);

    int updateByPrimaryKey(StationBean record);
    
	public void delBatchStation(String chks);
	
	public List<StationBean> findPostName(StationBean o);
}