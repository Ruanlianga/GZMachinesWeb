package com.bonus.sys;

import java.io.ByteArrayOutputStream;

public class StringHelper {

	private static String hexString = "0123456789ABCDEF";

	public static String replaceAll(String str, String oldStr, String newStr) {
		return str.replaceAll(oldStr, newStr);
	}

	public static boolean contains(String s1, String s2) {
		if (isEmpty(s1)) {
			return false;
		}
		return s1.contains(s2);
	}
	
	public static int getInt(String valStr){
		return getInt(valStr,0);
	}
	
	public static int getInt(String valStr,int defaulVal){
		int val = 0;
		try{
			val = Integer.parseInt(valStr);
		}catch (Exception e) {
			val = defaulVal;
		}
		return val;
	}
	
	public static float getFloat(String valStr){
		return getFloat(valStr,0);
	}
	
	public static float getFloat(String valStr,int defaulVal){
		float val = 0;
		try{
			val = Float.parseFloat(valStr);
		}catch (Exception e) {
			val = defaulVal;
		}
		return val;
	}

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	public static String fillPrefixZero(int v, int len) {
		String vStr = v + "";
		while (vStr.length() < len) {
			vStr = "0" + vStr;
		}
		return vStr;
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串 不为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 处理时间，获取编号
	 */
	public static String time2Code(String time) {
		if (isNotEmpty(time)) {
			time = time.replace("-", "").replace(" ", "").replace(":", "");
		}
		return time;
	}

}
