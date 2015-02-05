/**  
 * @Title: BaseEntity.java
 * @Package com.zhangmin.base.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.base.entity;

import java.util.Date;

import com.duruxin.util.DateUtil;

/**
 * ClassName: BaseEntity 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public abstract class BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private Date createDate;
	private UserInfo createUser;
	private Date updateDate;
	private UserInfo updateUser;
	private String flag;
	private String sort;
	private String sortSequence;
	public String getSortSequence() {
		return sortSequence;
	}

	public void setSortSequence(String sortSequence) {
		this.sortSequence = sortSequence;
	}

	private String memo;
	
	private Date beginDate;
	private Date endDate;

	//page column
	

	public String getSort() {
		return sort;
	}

	/**
	 * @return the createUser
	 */
	public UserInfo getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(UserInfo createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateUser
	 */
	public UserInfo getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(UserInfo updateUser) {
		this.updateUser = updateUser;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	//将数据库的日期"2014-1-22 19:46:50"转换成字符串格式"2014-01-22"
	public String getCreateDateString() {
		String date = "";
		if(null!=getCreateDate()){
			date = DateUtil.convertDateToString(getCreateDate(), DateUtil.DATE_FORMAT_yyyyMMdd);
		}
		return date;
	}
	//将页面字符串日期"Wed Jan 22 19:46:50 CST 2014"转换成日期类型"2014-1-22 19:46:50"，存到数据库
	public void setCreateDateString(String value) {
		if(null!=value && !"".equals(value)){
			setCreateDate(DateUtil.convertStringToDate(value, DateUtil.DATE_FORMAT_yyyyMMdd));
		}
	}
	
	public String getUpdateDateString() {
		String date = "";
		if(null!=getUpdateDate()){
			date = DateUtil.convertDateToString(getUpdateDate(), DateUtil.DATE_FORMAT_yyyyMMdd);
		}
		return date;
	}
	
	public void setUpdateDateString(String value) {
		if(null!=value && !"".equals(value)){
			setUpdateDate(DateUtil.convertStringToDate(value, DateUtil.DATE_FORMAT_yyyyMMdd));
		}
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getBeginDateString() {
		String date = "";
		if(null!=getBeginDate()){
			date = DateUtil.convertDateToString(getBeginDate(), DateUtil.DATE_FORMAT_yyyyMMdd);
		}
		return date;
	}
	
	public void setBeginDateString(String value) {
		if(null!=value && !"".equals(value)){
			setBeginDate(DateUtil.convertStringToDate(value, DateUtil.DATE_FORMAT_yyyyMMdd));
		}
	}
	public String getEndDateString() {
		String date = "";
		if(null!=getEndDate()){
			date = DateUtil.convertDateToString(getEndDate(), DateUtil.DATE_FORMAT_yyyyMMdd);
		}
		return date;
	}
	
	public void setEndDateString(String value) {
		if(null!=value && !"".equals(value)){
			setEndDate(DateUtil.convertStringToDate(value, DateUtil.DATE_FORMAT_yyyyMMdd));
		}
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}

