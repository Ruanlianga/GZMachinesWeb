package com.bonus.scrap.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.scrap.beans.ScrapTaskRecordBean;
import com.bonus.sys.BaseService;

public interface ScrapTaskRecordService extends BaseService<ScrapTaskRecordBean>{

	public Object uploadMachines(HttpServletRequest request,ScrapTaskRecordBean o);
	
	public List<ScrapTaskRecordBean> findMaPhotoUrl(ScrapTaskRecordBean o);
	
	public int updatePhotoUrl(ScrapTaskRecordBean o);

}
