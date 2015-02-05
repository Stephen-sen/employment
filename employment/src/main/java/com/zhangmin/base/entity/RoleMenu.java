/**  
 * @Title: RoleMenu.java
 * @Package com.zhangmin.base.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.base.entity;

/**
 * ClassName: RoleMenu 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class RoleMenu extends BaseEntity{

	private Role role;
	private Menu menu;
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
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
