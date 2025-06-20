package com.bonus.bm.service;

import com.bonus.bm.beans.HouseAreaBean;
import com.bonus.sys.BaseService;

public interface HouseAreaService extends BaseService<HouseAreaBean>{

	public int insertBean(HouseAreaBean o);
	
	public void deleteBatch(String chks);
	
	
}
