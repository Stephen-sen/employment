/**  
 * @Title: UserLog.java
 * @Package com.zhangmin.base.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.base.entity;

/**
 * ClassName: UserLog 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class UserLog extends BaseEntity{

	private String customIP;
	private String title;
	private String url;
	private String times;
	private String content;
	private String userInfo;
	/**
	 * @return the customIP
	 */
	public String getCustomIP() {
		return customIP;
	}
	/**
	 * @param customIP the customIP to set
	 */
	public void setCustomIP(String customIP) {
		this.customIP = customIP;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the times
	 */
	public String getTimes() {
		return times;
	}
	/**
	 * @param times the times to set
	 */
	public void setTimes(String times) {
		this.times = times;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the userInfo
	 */
	public String getUserInfo() {
		return userInfo;
	}
	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
}
