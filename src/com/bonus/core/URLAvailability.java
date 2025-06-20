package com.bonus.core;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * 功能简述： 描述一个URL地址是否有效 
 * @author lvjilong
 * @time 2018/01/15
 *
 */
public class URLAvailability {
	private static Logger logger =Logger.getLogger(URLAvailability.class);
	
	private static URL url;  
	private static HttpURLConnection con;  
	private static int state = -1;  
	/** 
	   * 功能：检测当前URL是否可连接或是否有效, 
	   * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用 
	   * @param urlStr 指定URL网络地址 
	   * @return URL 
	   */  
	public static synchronized boolean isConnect(String urlStr) {  
	   int counts = 0;  
	   boolean tf=false;
	   if (urlStr == null || urlStr.length() <= 0) {                         
		   tf=false;                 
	   }  
	   while (counts < 5) {  
	    try {  
	     url = new URL(urlStr);  
	     con = (HttpURLConnection) url.openConnection();  
	     state = con.getResponseCode();  
	     if (state == 200) {  
	    	 tf=true;
	    	 return tf;
	     }
	     break;
	    }catch (Exception ex) {  
	     counts++;   
	     logger.warn("url:"+urlStr+",is not connect!,connect :"+counts+"num");
	     urlStr = null;  
	     tf=false;
	     continue;  
	    }  
	   }  
	   return tf;
	}  
}
