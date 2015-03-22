/**  
 * @Title: Ability.java
 * @Package com.zhangmin.center.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.center.entity;

import com.zhangmin.base.entity.BaseEntity;

/**
 * ClassName: Ability 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
public class Ability extends BaseEntity {

	private String name;//一级考核名称
	private String assessmentType;//考核类型（01：一级考核，02：二级考核）
	private String description;//考核内容描述
	private String maxScore;//考核内容描述
	private Ability preId;//二级考核id
	private String preName;//二级考核名称

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
	 * @return the assessmentType
	 */
	public String getAssessmentType() {
		return assessmentType;
	}

	/**
	 * @param assessmentType the assessmentType to set
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
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


	/**
	 * @return the preId
	 */
	public Ability getPreId() {
		return preId;
	}

	/**
	 * @param preId the preId to set
	 */
	public void setPreId(Ability preId) {
		this.preId = preId;
	}

	/**
	 * @return the preName
	 */
	public String getPreName() {
		return preName;
	}

	/**
	 * @param preName the preName to set
	 */
	public void setPreName(String preName) {
		this.preName = preName;
	}
	
}
