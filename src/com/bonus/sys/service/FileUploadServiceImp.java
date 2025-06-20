package com.bonus.sys.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.PropertyHelper;
import com.bonus.sys.beans.FileUploadInfoBean;
import com.bonus.sys.dao.FileUploadInfoDao;

@Service("FileUploadService")
public class FileUploadServiceImp extends BaseServiceImp<FileUploadInfoBean> implements FileUploadService{

    
    @Autowired
    FileUploadInfoDao fileDao;
    
	@Override
	public void downloadDocument(HttpServletRequest request, HttpServletResponse response,FileUploadInfoBean o) {
	 
		FileUploadInfoBean file = fileDao.findByFileId(o);
		
		if(file != null) {
			String realSavePath =  PropertyHelper.getPropertyByKey("fileSavePath");
			
			String filePath = realSavePath+file.getFolderName()+"/"+file.getSaveName()+file.getSuffix();
			
			System.err.println("filePath="+filePath);
			
			try {
				downLoad(request,file,response);
			} catch (Exception e) {
				logger.error("下载失败"+e,e);
				e.printStackTrace();
			}
			}else {
				
			}
		}
	
	private void downLoad(HttpServletRequest request,FileUploadInfoBean file, HttpServletResponse response) {
		try {
			//String paths = request.getSession().getServletContext().getRealPath(""); 
			String realSavePath =  PropertyHelper.getPropertyByKey("fileSavePath");
			
			String filePath = realSavePath+file.getFolderName()+"/"+file.getSaveName()+file.getSuffix();
		    File f = new File(filePath);
			if (!f.exists()) {
				logger.warn("文件不存在");
			}
	        String fileName = f.getName();
	        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

	        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
	        byte[] buf = new byte[1024];
	        int len = 0;
	        response.reset(); // 非常重要
	        // 纯下载方式
	            response.setContentType("application/octet-stream");
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	        OutputStream out = response.getOutputStream();
	        while ((len = br.read(buf)) > 0){
	        	  out.write(buf, 0, len);
	        }
	        br.close();
	        out.close();
		} catch (Exception e) {
			logger.warn("download file error:", e);
		}
	}
	
}
