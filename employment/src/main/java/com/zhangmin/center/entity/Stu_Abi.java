/**  
 * @Title: Com_Abi.java
 * @Package com.zhangmin.center.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.center.entity;

import com.zhangmin.base.entity.BaseEntity;

/**
 * ClassName: Stu_Abi 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
public class Stu_Abi extends BaseEntity{

	private UserInfo student;
	private Ability ability;
	private String score;
	
	/**
	 * @return the student
	 */
	public UserInfo getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(UserInfo student) {
		this.student = student;
	}
	/**
	 * @return the ability
	 */
	public Ability getAbility() {
		return ability;
	}
	/**
	 * @param ability the ability to set
	 */
	public void setAbility(Ability ability) {
		this.ability = ability;
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
