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
 * @Description: 企业基类
 * @author 张敏
 * @date 2015-2-28
 */
public class Company extends BaseEntity {

	private String name;
	private String position; //职位
	private String description; //描述
	private String demand;//要求
	private String score;//每样要求分值
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
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the demand
	 */
	public String getDemand() {
		return demand;
	}
	/**
	 * @param demand the demand to set
	 */
	public void setDemand(String demand) {
		this.demand = demand;
	}
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	
	
}
