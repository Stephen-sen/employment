/**  
 * @Title: BaseEntity.java
 * @Package com.zhangmin.base.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.base.entity;

import java.util.Date;

import com.zhaosen.util.DateUtil;

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
	private String createDate;
	private String createUser;
	private String updateDate;
	private String updateUser;
	private String flag;
	private String sort;
	private String sortSequence;
	private String status;//评价状态（0，未评价，1已评价）
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
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}

