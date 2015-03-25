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
public class Job extends BaseEntity {

	private Company company; 
	private Position position;
	private String contactPerson;
	private String contactTel;
	private String description; //职位描述
	private Double salary; //薪水
	private String demand;//要求
	private Pos_Abi pos_Abi;//要求
	private String[] abilityArry;//suzhi
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
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}
	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	/**
	 * @return the contactTel
	 */
	public String getContactTel() {
		return contactTel;
	}
	/**
	 * @param contactTel the contactTel to set
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
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
	/**
	 * @return the abilityArry
	 */
	public String[] getAbilityArry() {
		return abilityArry;
	}
	/**
	 * @param abilityArry the abilityArry to set
	 */
	public void setAbilityArry(String[] abilityArry) {
		this.abilityArry = abilityArry;
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
	
	
}
