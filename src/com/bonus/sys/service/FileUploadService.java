package com.bonus.sys.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bonus.sys.BaseService;
import com.bonus.sys.beans.FileUploadInfoBean;

public interface FileUploadService extends BaseService<FileUploadInfoBean>{
    	
	public void downloadDocument(HttpServletRequest request, HttpServletResponse response, FileUploadInfoBean o);

}
