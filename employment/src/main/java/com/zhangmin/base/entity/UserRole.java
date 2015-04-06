/**  
 * @Title: UserRole.java
 * @Package com.zhangmin.base.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.base.entity;

import com.zhangmin.center.entity.UserInfo;

/**
 * ClassName: UserRole 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class UserRole extends BaseEntity{

	private String isDefault;
	private UserInfo user;
	private Role role;
	private String userRoleStr;
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
	 * @return the user
	 */
	public UserInfo getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(UserInfo user) {
		this.user = user;
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
	/**
	 * @return the userRoleStr
	 */
	public String getUserRoleStr() {
		return userRoleStr;
	}
	/**
	 * @param userRoleStr the userRoleStr to set
	 */
	public void setUserRoleStr(String userRoleStr) {
		this.userRoleStr = userRoleStr;
	}
	
	
}
