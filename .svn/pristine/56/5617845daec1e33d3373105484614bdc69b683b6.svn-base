package com.bonus.doc.docutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConvertUtils {

	/**
	 * 根据小写数字格式的日期转换成大写格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getUpperDate(String date) {
		char[] upper = "〇一二三四五六七八九十".toCharArray();
		// 支持yyyy-MM-dd、yyyy/MM/dd、yyyyMMdd等格式
		if (date == null)
			return null;
		// 非数字的都去掉
		date = date.replaceAll("\\D", "");
		if (date.length() != 8)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {// 年
			sb.append(upper[Integer.parseInt(date.substring(i, i + 1))]);
		}
		sb.append("年");// 拼接年
		int month = Integer.parseInt(date.substring(4, 6));
		if (month <= 10) {
			sb.append(upper[month]);
		} else {
			sb.append("十").append(upper[month % 10]);
		}
		sb.append("月");// 拼接月

		int day = Integer.parseInt(date.substring(6));
		if (day <= 10) {
			sb.append(upper[day]);
		} else if (day < 20) {
			sb.append("十").append(upper[day % 10]);
		} else {
			sb.append(upper[day / 10]).append("十");
			int tmp = day % 10;
			if (tmp != 0)
				sb.append(upper[tmp]);
		}
		sb.append("日");// 拼接日
		return sb.toString();
	}

	/**
	 * 阿拉伯数字转中文数字
	 */
	public static String digit2Chinese(int i) {
		Map<Integer, String> m = new HashMap<Integer, String>();
		m.put(1, "一");
		m.put(2, "二");
		m.put(3, "三");
		m.put(4, "四");
		m.put(5, "五");
		m.put(6, "六");
		m.put(7, "七");
		m.put(8, "八");
		m.put(9, "九");
		m.put(10, "十");
		m.put(11, "十一");
		m.put(12, "十二");
		m.put(13, "十三");
		m.put(14, "十四");
		m.put(15, "十五");
		m.put(16, "十六");
		return m.get(i);
	}

	/**
	 * 获取当前月份
	 */
	public static int getCurrentMonth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			return cal.get(Calendar.MONTH) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * 获取当前月份上一月
	 */
	public static int getCurrentMonthDecrease(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			return cal.get(Calendar.MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 获取当前年份
	 */
	public static int getCurrentYear(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			return cal.get(Calendar.YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 月份减一
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthlyDecrease(String month) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(month));
		calendar.add(Calendar.MONTH, -1);// 当前时间前去一个月，即一个月前的时间
		return sdf.format(calendar.getTime());
	}
	

	/**
	 * 获取某个月份的天数
	 * 
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Date date = sdf.parse(month);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;

	}

}
