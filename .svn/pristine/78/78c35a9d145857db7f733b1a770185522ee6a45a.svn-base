package com.bonus.lease.service;


import java.util.List;

import com.bonus.lease.beans.AgreementBean;
import com.bonus.lease.beans.LeaseApplicationBean;
import com.bonus.sys.BaseService;


public interface LeaseApplicationService extends BaseService<LeaseApplicationBean>{

	void addLeaseApply(LeaseApplicationBean o);

	List<AgreementBean> findAgreeCode(AgreementBean o);

	String findApplyNumber(LeaseApplicationBean o);

	void buildLeaseTask(LeaseApplicationBean o);

	Integer deleteApply(LeaseApplicationBean o);
	
	List<LeaseApplicationBean> getTaskDetails(LeaseApplicationBean o);

	List<LeaseApplicationBean> getSubInfo(LeaseApplicationBean o);
}
