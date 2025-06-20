package com.bonus.lease.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.core.DateTimeHelper;
import com.bonus.lease.beans.AgreementBean;
import com.bonus.lease.dao.AgreementDao;
import com.bonus.ma.beans.MachineBean;
import com.bonus.sys.BaseServiceImp;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service("agreement")
public class AgreementServiceImp extends BaseServiceImp<AgreementBean> implements AgreementService {

	@Autowired
	AgreementDao dao;

	@Override
	public int insertBean(AgreementBean o) {
		return dao.insertBean(o);
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<AgreementBean> list = new ArrayList<AgreementBean>();
			for (String s : chk) {
				try {
					// int id = Integer.parseInt(s);
					AgreementBean sd = new AgreementBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}

	@Override
	public String getCount(AgreementBean o) {
		String counts = dao.getCount(o);
		return counts;
	}

	@Override
	public String findCode(AgreementBean o) {
		return dao.findCode(o);
	}

	@Override
	public List<AgreementBean> checkAgreement(AgreementBean o) {
		List<AgreementBean> list = dao.checkAgreement(o);
		return list;
	}

	@Override
	public List<AgreementBean> expAgreement(AgreementBean o) {
		List<AgreementBean> list = dao.expAgreement(o);
		return list;
	}

	@Override
	public List<AgreementBean> findAgreeCode(AgreementBean o) {
		return dao.findAgreeCode(o);
	}

	@Override
	public List<AgreementBean> findAgreeCodeId(AgreementBean o) {
		return dao.findAgreeCodeId(o);
	}

	@Override
	public int updateBean(AgreementBean o) {
		// TODO Auto-generated method stub
		return dao.updateBean(o);
	}

	@Override
	public List<AgreementBean> findAgreement(AgreementBean o) {
		// TODO Auto-generated method stub
		return dao.findAgreement(o);
	}



	@Override
	public void updateIdByTaskId(String id, String taskId) {
		dao.updateIdByTaskId(id,taskId);
	}

	@Override
	public void deleteByTaskId(String taskId) {
		dao.deleteByTaskId(taskId);
	}

	@Override
	public List<AgreementBean> findByCompany(AgreementBean agree) {
		return dao.findByCompany(agree);
	}

	@Override
	public AgreementBean findIdByTaskId(String taskId) {
		return dao.findIdByTaskId(taskId);
	}

	@Override
	public String findAgreementCode(AgreementBean o) {
		return dao.findAgreementCode(o);
	}

	@Override
	public AgreementBean getAgreementCode(AgreementBean o) {
		return dao.getAgreementCode(o);
	}

	@Override
	public List<AgreementBean> getAgreementCodes(AgreementBean o) {
		// TODO Auto-generated method stub
		return dao.getAgreementCodes(o);
	}

	@Override
	public int updUrlAndUrlPath(AgreementBean o) {
		// TODO Auto-generated method stub
		return dao.updUrlAndUrlPath(o);
	}

}
