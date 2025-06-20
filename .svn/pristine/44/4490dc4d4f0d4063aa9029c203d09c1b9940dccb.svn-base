package com.bonus.lease.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.AgreementBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface AgreementDao extends BaseDao<AgreementBean> {

	public int insertBean(AgreementBean o);

	public String getCount(AgreementBean o);

	public String findCode(AgreementBean o);

	public List<AgreementBean> checkAgreement(AgreementBean o);

	public List<AgreementBean> expAgreement(AgreementBean o);

	public List<AgreementBean> findAgreeCode(AgreementBean o);

	public List<AgreementBean> findAgreeCodeId(AgreementBean o);

	public int updateBean(AgreementBean o);

	public List<AgreementBean> findAgreement(AgreementBean o);

	public void updateIdByTaskId(@Param("id") String id, @Param("taskId") String taskId);

	public void deleteByTaskId(String taskId);

	public List<AgreementBean> findByCompany(AgreementBean agree);

	public AgreementBean findIdByTaskId(String taskId);

	public String findAgreementCode(AgreementBean o);

	public AgreementBean getAgreementCode(AgreementBean o);
	
	public List<AgreementBean> getAgreementCodes(AgreementBean o);

	public int updUrlAndUrlPath(AgreementBean o);

}
