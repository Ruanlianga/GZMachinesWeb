package com.bonus.ma.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.ma.beans.GpsBindingBean;
import com.bonus.ma.dao.GpsBindingDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Service("GpsBindingService")
public class   GpsBindingServiceImpl extends BaseServiceImp<GpsBindingBean> implements GpsBindingService{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GpsBindingDao dao;
	/**
	 * 检验gisCode唯一性
	 */
	@Override
	public List<GpsBindingBean> getGisCodeBylist(GpsBindingBean o) {
		return dao.getGisCodeBylist(o);
	}
	/**
	 * 修改gisCode
	 */
	@Override
	public String updateGisCode(GpsBindingBean o) {
		String msg="保存成功";
		GpsBindingBean bean=new GpsBindingBean();
		UserBean user = UserShiroHelper.getRealCurrentUser();
		try {
			String ids=o.getId();
			if(StringHelper.isNotEmpty(ids)){
				String[]  strs=ids.split("@");
				for (String string : strs) {
					String id=string.split("_")[0];
					String gisCode=string.split("_")[1];
					String deviceCode=string.split("_")[2];
					bean.setId(id);
					bean.setGisCode(gisCode);
					bean.setOptTime(DateTimeHelper.getNowTime());
					bean.setOpter(user.getId().toString());
					bean.setDeviceCode(deviceCode);
					bean.setMaId(id);
					int num=dao.updateGisCode(bean);
					if(num==1){
						bean.setType("1");
						dao.insertGisCodeFlow(bean);//插入流水表
						dao.insertGpsBding(bean);
						dao.insertGssLocation(bean);
						
					}
				}
				
			}
			
			
		} catch (Exception e) {
			msg="保存失败";
			logger.error(e.toString(), e);
		}
		
		return msg;
	}
	@Override
	public String unBoundGps(GpsBindingBean o) {
		String msg="解绑成功";
		UserBean user = UserShiroHelper.getRealCurrentUser();
		try {
		int num=dao.unBoundGps(o);
		if(num==1){
			o.setType("2");
			o.setOptTime(DateTimeHelper.getNowTime());
			o.setOpter(user.getId().toString());
			dao.insertGisCodeFlow(o);
			dao.deleteGpsBding(o);
			dao.deleteGpsCode(o);
		}
		} catch (Exception e) {
			msg="解绑失败";
			logger.error(e.toString(), e);
		}
		return msg;
	}
	@Override
	public Page<GpsBindingBean> findGpsFlowPage(GpsBindingBean o, Page<GpsBindingBean> page) {
		
		page.setResults(dao.findGpsFlowPage(o, page));
		
		
		return page;
	}

}
