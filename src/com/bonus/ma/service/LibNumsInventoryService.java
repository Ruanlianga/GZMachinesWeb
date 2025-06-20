package com.bonus.ma.service;

import com.bonus.ma.beans.LibNumsInventoryBean;
import com.bonus.sys.BaseService;

public interface LibNumsInventoryService extends BaseService<LibNumsInventoryBean>{

	void addLibs(LibNumsInventoryBean o);

	String findByModelId(LibNumsInventoryBean o);

	
}
