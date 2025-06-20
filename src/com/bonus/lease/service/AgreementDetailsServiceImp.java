package com.bonus.lease.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.lease.beans.AgreementDetailsBean;
import com.bonus.lease.dao.AgreementDetailsDao;
import com.bonus.sys.BaseServiceImp;

@Service("agreementDetails")
public class AgreementDetailsServiceImp extends BaseServiceImp<AgreementDetailsBean> implements AgreementDetailsService{

	@Autowired AgreementDetailsDao dao;
	
	@Override
	public int insertBean(AgreementDetailsBean o) {
		return dao.insertBean(o);
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<AgreementDetailsBean> list = new ArrayList<AgreementDetailsBean>();
			for (String s : chk) {
				try {
					//int id = Integer.parseInt(s);
					AgreementDetailsBean sd = new AgreementDetailsBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}
	
}
