/**  
 * @Title: Appender.java
 * @Package com.zhangmin.constant
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-11
 */
package com.zhangmin.constant;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * ClassName: Appender 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-11
 */
public class LogAppender  extends DailyRollingFileAppender {

	/* 重写log4j中DailyRollingFileAppender的isAsSevereAsThreshold方法，用来分级别记录日志
	 * @see org.apache.log4j.AppenderSkeleton#isAsSevereAsThreshold(org.apache.log4j.Priority)
	 */
	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		// TODO Auto-generated method stub
		return this.getThreshold().equals(priority);  
	}
}
