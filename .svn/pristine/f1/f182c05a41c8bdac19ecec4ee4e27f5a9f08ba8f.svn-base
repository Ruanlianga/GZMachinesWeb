package com.bonus.sys;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 */
public class PropertyHelper {

	private static Properties prop;
	
	static{
		
		prop=new Properties();
		
		InputStream ips=PropertyHelper.class.getClassLoader()
				.getResourceAsStream("spring/baseconfig.properties");
		try {
			prop.load(ips);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
    
	public static String getPropertyByKey(String key){
		return prop.getProperty(key);
	}
	
}