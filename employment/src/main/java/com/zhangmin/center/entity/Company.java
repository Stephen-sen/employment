/**  
 * @Title: company.java
 * @Package com.zhangmin.center.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-28
 */
package com.zhangmin.center.entity;

import com.zhangmin.base.entity.BaseEntity;

/**
 * ClassName: company 
 * @Description: 企业实体类
 * @author 张敏
 * @date 2015-2-28
 */
public class Company extends BaseEntity {

	private String name;
	private String buildDate;//公司创建日期
	private String address;//公司地址
	private String staffNum;//公司人数
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the buildDate
	 */
	public String getBuildDate() {
		return buildDate;
	}
	/**
	 * @param buildDate the buildDate to set
	 */
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	/**
	 * @return the staffNum
	 */
	public String getStaffNum() {
		return staffNum;
	}
	/**
	 * @param staffNum the staffNum to set
	 */
	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
}
