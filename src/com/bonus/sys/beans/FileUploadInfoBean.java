package com.bonus.sys.beans;

import java.util.Date;

/**
 * @Author 无畏
 * @Date   2019-05-30
 * @Table  file_upload_info
 * @Model  文件上传实体类
 */
public class FileUploadInfoBean  {
	private static final long serialVersionUID = 1L;

    private String id;

     
    private String folderName;//文件夹名称
    
    private String preName;//文件原名
    
    private String saveName;
    
    private String modelFlag;

    private Date uploadTime;

    private Integer tempFlag;

    private Integer ownerId;
    
    private String suffix;
    
    private String imgUrl;
    
    private String type;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FileUploadInfoBean() {
    	super();
    }

    public FileUploadInfoBean(String folderName, String preName, String saveName, String modelFlag, Date uploadTime,
	    Integer tempFlag, Integer ownerId) {
		super();
		this.folderName = folderName;
		this.preName = preName;
		this.saveName = saveName;
		this.modelFlag = modelFlag;
		this.uploadTime = uploadTime;
		this.tempFlag = tempFlag;
		this.ownerId = ownerId;
    }
    
    public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
 
    
    public String getModelFlag() {
        return modelFlag;
    }

    public void setModelFlag(String modelFlag) {
        this.modelFlag = modelFlag;
    }
 

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getTempFlag() {
        return tempFlag;
    }

    public void setTempFlag(Integer tempFlag) {
        this.tempFlag = tempFlag;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
}