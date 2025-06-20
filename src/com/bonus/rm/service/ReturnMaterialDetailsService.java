package com.bonus.rm.service;


import java.util.List;

import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.sys.BaseService;


public interface ReturnMaterialDetailsService extends BaseService<ReturnMaterialDetailsBean>{

	Object findByCode(ReturnMaterialDetailsBean o);

	Object receiveDevice(ReturnMaterialDetailsBean o);

	Object findLeaseBackNum(ReturnMaterialDetailsBean o);

	Object reBackNums(ReturnMaterialDetailsBean o);

	List<ReturnMaterialDetailsBean> findTaskDetail(ReturnMaterialDetailsBean o);

	List<ReturnMaterialDetailsBean> findTaskDetailInfo(ReturnMaterialDetailsBean o);

}
