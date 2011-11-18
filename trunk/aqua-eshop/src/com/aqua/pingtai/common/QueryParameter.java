package com.aqua.pingtai.common;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class QueryParameter implements Serializable {

	protected HashMap<String, String> parameter;

	public QueryParameter() {
		parameter = new HashMap<String, String>();
	}
	
	/**
	 * 添加参数
	 * @param key
	 * @param value  字符串类型
	 */
	public void addParameter(String key, String value) {
		parameter.put(key, value);
	}
	
	/**
	 * 根据key获取value(String) 
	 * @param key
	 * @return  
	 */
	public String getParameter(String key) {
		String values = parameter.get(key);
		return values;
	}
	
	/**
	 * 获取parameter的Map对象
	 * @return
	 */
	public HashMap<String, String> getParameterMap() {
		return parameter;
	}
	
	/**
	 * 根据key获取value(String) 
	 * @param key
	 * @return  
	 */
	public String getParameterValueByKey(String key) {
		return parameter.get(key);
	}
	
}
