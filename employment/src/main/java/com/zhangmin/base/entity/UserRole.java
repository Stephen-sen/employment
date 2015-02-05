/**  
 * @Title: UserRole.java
 * @Package com.zhangmin.base.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.base.entity;

/**
 * ClassName: UserRole 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class UserRole extends BaseEntity{

	private String isDefault;
	private UserInfo userInfo;
	private Role role;
	/**
	 * @return the isDefault
	 */
	public String getIsDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * @return the userInfo
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}
	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
