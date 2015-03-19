/**  
 * @Title: Com_Pos.java
 * @Package com.zhangmin.center.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.center.entity;

import com.zhangmin.base.entity.BaseEntity;

/**
 * ClassName: Com_Pos 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
public class Com_Pos extends BaseEntity {

	private Company company; 
	private Position position; 
	private String description; //职位描述
	private Double salary; //薪水
	private String demand;//要求
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
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
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
	
	
}
