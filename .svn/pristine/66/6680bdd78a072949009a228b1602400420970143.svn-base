package com.bonus.lease.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bonus.lease.beans.AgreementBean;
import com.bonus.sys.BaseService;

public interface AgreementService extends BaseService<AgreementBean>{

	public int insertBean(AgreementBean o);
	
	public void deleteBatch(String chks);
	
	public String getCount(AgreementBean o);

	public String findCode(AgreementBean o);

	public List<AgreementBean> checkAgreement(AgreementBean o);

	public List<AgreementBean> expAgreement(AgreementBean o);

	public List<AgreementBean> findAgreeCode(AgreementBean o);

	public List<AgreementBean> findAgreeCodeId(AgreementBean agreeBean);
	
	public int updateBean(AgreementBean o);
	
	public List<AgreementBean> findAgreement(AgreementBean o);

	public void updateIdByTaskId(String id, String taskId);

	public void deleteByTaskId(String taskId);

	public List<AgreementBean> findByCompany(AgreementBean agree);

	public AgreementBean findIdByTaskId(String taskId);

	public String findAgreementCode(AgreementBean o);

	public AgreementBean getAgreementCode(AgreementBean o);
	
	public List<AgreementBean> getAgreementCodes(AgreementBean o);

	public int updUrlAndUrlPath(AgreementBean o);

	
}
