package com.bonus.core;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

	public static String getString(HttpServletRequest request, String key) {
		return request.getParameter(key);
	}
	
	public static String getAttrString(HttpServletRequest request, String key) {
		Object o = request.getAttribute(key);
		if(o == null){
			return "";
		}
		return o.toString();
	}
	
	public static String getString(HttpServletRequest request, String key,String defaultValue) {
		String value = request.getParameter(key);
		if(StringHelper.isEmpty(value)){
			return defaultValue;
		}
		return value;
	}
	
	public static float getFloat(HttpServletRequest request, String key) {
		String tmp = getString(request,key);
		try{
			return Float.parseFloat(tmp);
		} catch(Exception e){
			return 0;
		}
	}

	
	public static int getInt(HttpServletRequest request, String key,int defaultValue) {
		String tmp = getString(request,key);
		try{
			return Integer.parseInt(tmp);
		} catch(Exception e){
			return defaultValue;
		}
	}

	public static int getInt(HttpServletRequest request, String key) {
		String tmp = getString(request,key);
		try{
			return Integer.parseInt(tmp);
		} catch(Exception e){
			return -1;
		}
	}

}
