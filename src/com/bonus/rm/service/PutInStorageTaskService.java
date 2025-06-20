package com.bonus.rm.service;


import com.bonus.rm.beans.PutInStorageTaskBean;
import com.bonus.sys.BaseService;


public interface PutInStorageTaskService extends BaseService<PutInStorageTaskBean>{

	String updatePutPerson(PutInStorageTaskBean o);
	
	String isRelease(PutInStorageTaskBean o);

	int deleteReturn(PutInStorageTaskBean o);

}
