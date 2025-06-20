package com.bonus.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.StationBean;
import com.bonus.sys.dao.StationDao;

@Service("StationService")
public class StationServiceImp extends BaseServiceImp<StationBean> implements StationService {
	
	@Autowired StationDao stationdao;

    public int deleteByPrimaryKey(Integer id) {
        Object newKey = ((StationDao)baseDao).deleteByPrimaryKey(id);
        return ((Integer) newKey).intValue();
    }

    public int insertBean(StationBean record) {
        Object newKey = ((StationDao)baseDao).insertBean(record);
        return ((Integer) newKey).intValue();
    }

    public int insertSelective(StationBean record) {
        Object newKey = ((StationDao)baseDao).insertSelective(record);
        return ((Integer) newKey).intValue();
    }

    public StationBean selectByPrimaryKey(Integer id) {
        Object newKey = ((StationDao)baseDao).selectByPrimaryKey(id);
        return (StationBean) newKey;
    }

    public int updateByPrimaryKeySelective(StationBean record) {
        Object newKey = ((StationDao)baseDao).updateByPrimaryKeySelective(record);
        return ((Integer) newKey).intValue();
    }

    public int updateByPrimaryKey(StationBean record) {
        Object newKey = ((StationDao)baseDao).updateByPrimaryKey(record);
        return ((Integer) newKey).intValue();
    }

	@Override
	public void delBatchStation(String chks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StationBean> findPostName(StationBean o) {
		List<StationBean> list = stationdao.findPostName(o);
		return list;
	}
	
	
}