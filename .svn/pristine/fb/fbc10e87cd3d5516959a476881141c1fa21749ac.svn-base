package com.bonus.newSettlement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.newSettlement.dao.MaTypeProjectStorageDao;
import com.bonus.sys.BaseServiceImp;

@Service("MaTypeProjectStorageService")
public class MaTypeProjectStorageServiceImp extends BaseServiceImp<MaTypeProjectStorageBean> implements MaTypeProjectStorageService {

	@Autowired
	MaTypeProjectStorageDao dao;

	@Override
	public List<MaTypeProjectStorageBean> findUnSltMaTypeList(MaTypeProjectStorageBean o) {
		return dao.findUnSltMaTypeList(o);
	}

	@Override
	public List<MaTypeProjectStorageBean> findUnSltMaTypeLists(MaTypeProjectStorageBean o) {
		// TODO Auto-generated method stub
		return dao.findUnSltMaTypeLists(o);
	}

	@Override
	public List<MaTypeProjectStorageBean> findUnFinishMaTypeList(MaTypeProjectStorageBean o) {
		// TODO Auto-generated method stub
		return dao.findUnFinishMaTypeList(o);
	}
	
	
}
