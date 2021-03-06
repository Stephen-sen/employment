/**  
 * @Title: Global.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.constant;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;

/**
 * ClassName: Global 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class Global  {
	
	public static String USER_INFO="USER_INFO";
	public static String USER_ROLE_LIST="USER_ROLE_LIST";
	public static String USER_ORGAN="USER_ORGAN";
	public static String USER_RIGHT_ORGAN="USER_RIGHT_ORGAN";
	public static String USER_ALL_MENU="USER_ALL_MENU";
	public static String USER_BUTTON_STR="USER_BUTTON_STR";
	public static String USER_ROLE="USER_ROLE";
	public static String LOGIN_TO_URL="LOGIN_TO_URL";
	public static String USER_EMPLOYEE_INFO="USER_EMPLOYEE_INFO";
	
	public static int DEFAULT_PAGE_SIZE=15;
	
	
	
	
	public final static String GLOBAL_CONFIG_URL="config/global-config.xml";
	private static CompositeConfiguration config;
	
	/**
	 * 在xml配置文件中取得配置参数的值 实例： <name1><name2>value</name2></name1>
	 * 可通过getConfigValue(name1.name2)取得value
	 * @param configName
	 *        参数名称，用"."来标识结构
	 * @return 指定参数的值
	 */
	public static String getGlobalConfigValue(String configName) {
		if (config == null) {
			initGlobalConfig();
		}
		return config.getString(configName);
	}
	
	/**
	 * 初始化Global-config全局配置
	 */
	private static void initGlobalConfig() {
		config = new CompositeConfiguration();
		try {
			config.addConfiguration(new org.apache.commons.configuration.XMLConfiguration(GLOBAL_CONFIG_URL));
		} catch (ConfigurationException e) {
			config = null;
		}
	}
	
	
}

