/**  
 * @Title: UserLog.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.constant;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;

/**
 * ClassName: UserLog 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class UserLog {
	
	public final static String USERLOG_URL="config/userlog-config.xml";
	private static CompositeConfiguration config;
	
	public static String getUserLog(String configName)
	{
		if(config==null)
		{
			initUserLog();
		}
		return config.getString(configName);
	}

	private static void initUserLog() {
		config = new CompositeConfiguration();
		try {
			config.addConfiguration(new org.apache.commons.configuration.XMLConfiguration(USERLOG_URL));
		} catch (ConfigurationException e) {
			config = null;
		}
	}
}

