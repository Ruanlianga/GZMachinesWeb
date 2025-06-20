package com.bonus.newSettlement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.newSettlement.beans.ProjectFinishDetailsBean;
import com.bonus.newSettlement.beans.ProjectFinishInfoBean;
import com.bonus.newSettlement.beans.ProjectSettlementDetailsBean;
import com.bonus.newSettlement.dao.MaTypeProjectStorageDao;
import com.bonus.newSettlement.dao.ProjectFinishInfoDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.StringHelper;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Service("ProjectFinishInfoService")
public class ProjectFinishInfoServiceImp extends BaseServiceImp<ProjectFinishInfoBean> implements ProjectFinishInfoService {


	@Autowired
	private ProjectFinishInfoDao infoDao;
	
	@Autowired
	private MaTypeProjectStorageDao storageDao;

	@Override
	@Transactional
	public int saveFinishInfo(ProjectFinishInfoBean o) {
		Integer result = 0;
		try {
			
			String time = DateTimeHelper.getNowDateFomart();
			String nowYear = DateTimeHelper.getNowYear();
			String code = genNextCode("project_finish_info","WG"+nowYear,"WG"+time);
			UserBean user = UserShiroHelper.getRealCurrentUser();
			String orgId = user.getCompanyId();
			o.setOrgId(orgId);
			o.setCode(code);
			o.setCreator(UserShiroHelper.getRealCurrentUser().getId()+"");
			o.setCreateTime(DateTimeHelper.currentDateTime());
			result = infoDao.insertBean(o);
			if(result == 0){
				throw new ZeroAffectRowsException("工程完结失败,完结基本信息插入操作错误!");
			}
			int id = o.getId();
			result = infoDao.updateAgreement(o);
			if(result == 0){
				throw new ZeroAffectRowsException("工程完结失败,修改协议完结错误!");
			}
			
			MaTypeProjectStorageBean storage = new MaTypeProjectStorageBean();
			
			storage.setAgreementId(o.getAgreementId());
			
			result = storageDao.updateIsFinish(storage);
			
			if(result == 0){
				throw new ZeroAffectRowsException("结算生成失败,工程库存信息修改操作错误!");
			}
			
			
			ProjectFinishDetailsBean[] items = o.getArr();
			
			if(items != null && items.length > 0){
				for(ProjectFinishDetailsBean bean : items){
					bean.setSltInfo(id+"");
					result = infoDao.insertDetails(bean);
					if(result == 0){
						throw new ZeroAffectRowsException("工程完结失败,完结详情信息插入操作错误!");
					}
					
				
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public ProjectFinishInfoBean findSettlementInfoById(ProjectFinishInfoBean o) {
		// TODO Auto-generated method stub
		return infoDao.findSettlementInfoById(o);
	}

	@Override
	public void insertFile(ProjectFinishInfoBean o) {
		// TODO Auto-generated method stub
		infoDao.insertFile(o);
	}

	@Override
	public List<ProjectFinishInfoBean> getProFinishFiles(ProjectFinishInfoBean bean) {
		// TODO Auto-generated method stub
		return infoDao.getProFinishFiles(bean);
	}

	

}
