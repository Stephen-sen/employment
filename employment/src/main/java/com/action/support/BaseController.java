/** 
 * @Title: BaseAction1.java 
 * @Package com.action.support 
 * @author 张敏
 * @date 2015-2-3 下午03:29:30 
 * @version V1.0 
 */
package com.action.support;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.zhangmin.base.entity.UserRole;
import com.zhangmin.center.entity.UserInfo;
import com.zhangmin.constant.Global;
import com.zhangmin.constant.Stat;

import net.sf.json.JSONArray;

/**
 * @ClassName: BaseAction1
 * @Description: TODO(描述)
 * @author 张敏
 * @date 2015-2-3 下午03:29:30
 */
public class BaseController{
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	protected JSONArray result;

	protected String messageCode="01";
	
	public JSONArray getResult() {
		return result;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @return the messageCode
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	/**
	 * 从session中取得用户pageSize数据;
	 * @param request
	 * @return 如果session中不存在，则取出默认值；
	 */
	protected int getCookiesPageSize(HttpServletRequest request){
		int pageSize=Global.DEFAULT_PAGE_SIZE;
		if(!StringUtils.isEmpty( getCookiesValue("pageSize",request))){
			pageSize= Integer.parseInt(getCookiesValue("pageSize",request));
		}
		return pageSize;
	}
	
	/**
	 * 从cookies 上取相应的值，如果找不到返回''
	 * @param key
	 * @param request
	 * @return
	 */
	protected String getCookiesValue(String key,HttpServletRequest request){
		String keyval = "";
		Cookie cookies[] = request.getCookies(); // 将适用目录下所有Cookie读入并存入cookies数组中
		Cookie sCookie = null;
		String sname = null;
		if (cookies == null) // 如果没有任何cookie
		{
		} else {
			for (int i = 0; i < cookies.length; i++) // 循环列出所有可用的Cookie
			{
				sCookie = cookies[i];
				sname = sCookie.getName();
				if (sname.equals(key)) {
					keyval = sCookie.getValue();
				}
			}
		}
		return keyval;
	}
	
	/**
	 * 功能：写cookies到客户机
	 * @param key
	 * @param request
	 */
	protected void setCookiesValue(String key,String value,HttpServletRequest request){
		String keyval = "";
		Cookie cookies[] = request.getCookies(); // 将适用目录下所有Cookie读入并存入cookies数组中
		Cookie sCookie = null;
		String sname = null;
		if (cookies == null) // 如果没有任何cookie
		{
		} else {
			for (int i = 0; i < cookies.length; i++) // 循环列出所有可用的Cookie
			{
				sCookie = cookies[i];
				sname = sCookie.getName();
				if (sname.equals(key)) {
					keyval = sCookie.getValue();
				}
			}
		}
	}
	/**
	 * 向session设置值
	 * @param key
	 * @param value
	 * void
	 *  
	 */
	protected void setSessionValue(String key,String value,HttpServletRequest request){
		request.getSession().setAttribute(key, value);
	}
	
	/**
	 * 向session设置值
	 * @param key
	 * @param value
	 * @param request
	 */
	protected void setSessionObj(String key,Object value,HttpServletRequest request){
		request.getSession().setAttribute(key, value);
	}
	
	/**
	 * 获取session中的参数值
	 * @param key
	 * @return
	 * String
	 *  
	 */
	protected String getSessionValue(String key,HttpServletRequest request){
		return (String)request.getSession().getAttribute(key);
	}
	
	/**
	 * 功能：查看当前登录用户信息
	 * @param request
	 * @return 返回UserInfo对象 
	 */
	protected UserInfo getSessionUser(HttpServletRequest request) {
		return (UserInfo) request.getSession().getAttribute(
				Global.USER_INFO);
	}
	
	/**
	 * 功能：查看当前访问菜单
	 * @param request
	 * @return 返回BiUserInfo对象 
	 */
	protected List<UserRole> getSessionBiUserRoleList(HttpServletRequest request) {
		return (List<UserRole>) request.getSession().getAttribute(
				Global.USER_ROLE_LIST);
	}
	
	/**
	 * 功能：查看当前访问菜单
	 * @param request
	 * @return 返回BiUserInfo对象 
	 */
	protected UserRole getSessionBiUserRole(HttpServletRequest request) {
		return (UserRole) request.getSession().getAttribute(
				Global.USER_ROLE);
	}
	
	/**
	 * 功能：查看当前访问菜单
	 * @param request
	 * @return 返回BiUserInfo对象 
	 */
	protected UserInfo getSessionBiMenu(HttpServletRequest request) {
		return (UserInfo) request.getSession().getAttribute(
				Global.USER_INFO);
	}
	
	/**
	 * 保存用户对象到Session中
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request,UserInfo user) {
		request.getSession().setAttribute(Global.USER_INFO,
				user);
	}
	
	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url  以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
	
	public void setSessionListUrl(HttpServletRequest request){
		//把路径信息写入到session
		String uri = request.getServletPath().toString();
		String queryString = request.getQueryString();
		String queryUrl = (uri + (null != queryString ? ("?" + queryString): ""));
		Stat urlStat=(Stat)request.getSession().getAttribute("urlStat");
		if(null!=urlStat){
			urlStat.push(queryUrl);
		}
		else{
			urlStat=new Stat();
			urlStat.push(queryUrl);
		}
		request.getSession().setAttribute("urlStat", urlStat);
	}
	
	
	public Stat getUrlStat(HttpServletRequest request){
		return (Stat)request.getSession().getAttribute("urlStat");
	}
	/**
	 * 获取用户请求地址及参数 
	 * @param request
	 * @return
	 */
	public String getSessionListUrl(HttpServletRequest request){
		String url="/main/index.do";
		Stat urlStat=(Stat)request.getSession().getAttribute("urlStat");
		if(null!=urlStat){
			Object urlObj=urlStat.pop();
			if(null!=urlObj){
				url=urlObj.toString();
			}
		}
		return url;
	}
}
