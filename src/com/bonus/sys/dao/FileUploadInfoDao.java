package com.bonus.sys.dao;

import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.sys.BaseDao;
import com.bonus.sys.beans.FileUploadInfoBean;
 
@BonusBatis

public interface FileUploadInfoDao extends BaseDao<FileUploadInfoBean>{
    
	
    public Integer insertBean(FileUploadInfoBean o);
    
    public List<FileUploadInfoBean> findListByOwnerId(FileUploadInfoBean o);
    //门户网站专用
    public List<FileUploadInfoBean> findListByOwnerIdOfPortal(FileUploadInfoBean o);
    
     
    public FileUploadInfoBean findByFileId(FileUploadInfoBean o);
  
}