/**  
 * @Title: Menu.java
 * @Package com.zhangmin.base.entity
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
package com.zhangmin.base.entity;

/**
 * ClassName: Menu 
 * @Description: TODO
 * @author 张敏
 * @date 2015-2-5
 */
public class Menu extends BaseEntity{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String sequence;
	private String shortName;
	private String isChangYong;
	private String menuCode;
	private String description;
	private String logo;
	private String url;
	private String menuType;
	private Menu preId;
	private String buttonCode;
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
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the isChangYong
	 */
	public String getIsChangYong() {
		return isChangYong;
	}
	/**
	 * @param isChangYong the isChangYong to set
	 */
	public void setIsChangYong(String isChangYong) {
		this.isChangYong = isChangYong;
	}
	/**
	 * @return the menuCode
	 */
	public String getMenuCode() {
		return menuCode;
	}
	/**
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the menuType
	 */
	public String getMenuType() {
		return menuType;
	}
	/**
	 * @param menuType the menuType to set
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	/**
	 * @return the preId
	 */
	public Menu getPreId() {
		return preId;
	}
	/**
	 * @param preId the preId to set
	 */
	public void setPreId(Menu preId) {
		this.preId = preId;
	}
	/**
	 * @return the buttonCode
	 */
	public String getButtonCode() {
		return buttonCode;
	}
	/**
	 * @param buttonCode the buttonCode to set
	 */
	public void setButtonCode(String buttonCode) {
		this.buttonCode = buttonCode;
	}
}
