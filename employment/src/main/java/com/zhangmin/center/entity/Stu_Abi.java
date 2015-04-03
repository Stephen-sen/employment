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
	private Pos_Abi pos_Abi;
	private Job job;
	private String score;
	private String[] scoreKeyArray;
	private String[] scoreValArray;
	private double conformity;//符合度
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
	 * @return the pos_Abi
	 */
	public Pos_Abi getPos_Abi() {
		return pos_Abi;
	}
	/**
	 * @param posAbi the pos_Abi to set
	 */
	public void setPos_Abi(Pos_Abi posAbi) {
		pos_Abi = posAbi;
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
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	/**
	 * @return the conformity
	 */
	public double getConformity() {
		return conformity;
	}
	/**
	 * @param conformity the conformity to set
	 */
	public void setConformity(double conformity) {
		this.conformity = conformity;
	}
	/**
	 * @return the scoreKeyArray
	 */
	public String[] getScoreKeyArray() {
		return scoreKeyArray;
	}
	/**
	 * @param scoreKeyArray the scoreKeyArray to set
	 */
	public void setScoreKeyArray(String[] scoreKeyArray) {
		this.scoreKeyArray = scoreKeyArray;
	}
	/**
	 * @return the scoreValArray
	 */
	public String[] getScoreValArray() {
		return scoreValArray;
	}
	/**
	 * @param scoreValArray the scoreValArray to set
	 */
	public void setScoreValArray(String[] scoreValArray) {
		this.scoreValArray = scoreValArray;
	}
}
