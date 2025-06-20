package com.bonus.rm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.rm.beans.ReturnMaterialAccBean;
import com.bonus.rm.dao.ReturnMaterialAccDao;
import com.bonus.sys.BaseServiceImp;

@Service("returnMaterialAcc")
public class ReturnMaterialAccServiceImp extends BaseServiceImp<ReturnMaterialAccBean>
		implements ReturnMaterialAccService {

	@Autowired
	ReturnMaterialAccDao dao;




}
