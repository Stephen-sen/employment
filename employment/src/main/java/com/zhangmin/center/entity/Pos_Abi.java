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
 * ClassName: Com_Abi 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
public class Pos_Abi extends BaseEntity{

	private Position position;
	private Ability ability;
	private Company company;
	private String score;
	private String[] scoreKeyArray;
	private String[] scoreValArray;
	
	
	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
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
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
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
