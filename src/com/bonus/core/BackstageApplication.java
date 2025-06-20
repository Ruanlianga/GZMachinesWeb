package com.bonus.core;

/**
 * 送电分公司
 * @author cshu
 *
 */
public class BackstageApplication {
	
	private final static String CS_LOGIN_PATH = "140.210.216.134:1935";
	
	private final static String CS_USER_NAME = "root";
	
	
	private final static String CS_EP_ID = "system";
	
	private final static String url = "http://140.210.216.134:1935/gz_imw/";
	
	private final static String imageUrlPrefix = "http://140.210.216.134:1935/gz_imw/images/";
	

	private final static String fileUrlPrefix = "/opt/gzImw/web_tomcat/webapps/gz_imw";
	//测试
	//private final static String fileUrlPrefix = "/opt/jar/jijv/tomcat-GZM/webapps/GZMachinesWeb";

	
	public static String getCsLoginPath() {
		return CS_LOGIN_PATH;
	}
	
	public static String getCsUserName() {
		return CS_USER_NAME;
	}
	
	
	
	public static String getUrl() {
		return url;
	}

	public static String getImageUrlPrefix(){
		return imageUrlPrefix;
	}

	public static String getFileurlprefix() {
		return fileUrlPrefix;
	}

}
