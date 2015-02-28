/**  
 * @Title: Type.java
 * @Package com.zhangmin.center.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-28
 */
package com.zhangmin.center.entity;

import com.zhangmin.base.entity.BaseEntity;

/**
 * ClassName: Type 
 * @Description: 二级考核表基类
 * @author 张敏
 * @date 2015-2-28
 */
public class Type extends BaseEntity {

	private String name;//二级考核名称
	private String description;//考核内容描述
	private String score;//所占分值
	private String maxScore;//最大分值
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
	/**
	 * @return the maxScore
	 */
	public String getMaxScore() {
		return maxScore;
	}
	/**
	 * @param maxScore the maxScore to set
	 */
	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}
	
	
}
