package com.bonus.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.sys.BaseController;
import com.bonus.sys.beans.FileUploadInfoBean;
import com.bonus.sys.dao.FileUploadInfoDao;
import com.bonus.sys.service.FileUploadService;

@Controller      
@RequestMapping("/backstage/fileUpload/")
public class FileUploadController extends BaseController<Object> {
	
    	@Autowired
    	public FileUploadInfoDao dao;
    	
    	@Autowired
    	public FileUploadService service;
    	
    	@Autowired
    	protected JdbcTemplate jdbcTemplate;

    	 
    	@RequestMapping(value = "downloadDocument")
    	@ResponseBody
    	public void downloadDocument(HttpServletRequest request, HttpServletResponse response,FileUploadInfoBean o) {
    		service.downloadDocument(request, response, o);
    	}
    	 
}
