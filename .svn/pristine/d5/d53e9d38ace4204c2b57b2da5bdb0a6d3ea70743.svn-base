package com.bonus.core.json;

import com.alibaba.fastjson.JSON;

public interface JsonHelper {

	public String jsonSerialize(Object value);

	public <T> T jsonDeserialize(String value, Class<?> tClass);
	
	 /**
     * 将任意bean转换成字符串
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        String jsonStr = JSON.toJSONString(value);
        return jsonStr;
    }

    /**
     * 把一个字符串转换成bean对象
     * @param str
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        T bean = JSON.parseObject(str, clazz);
        return bean;
    }

}
