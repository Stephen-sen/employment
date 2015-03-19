/** 
 * @Title: BaseAction1.java 
 * @Package com.action.support 
 * @author 张敏
 * @date 2015-2-3 下午03:29:30 
 * @version V1.0 
 */
package com.action.support;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.zhangmin.constant.Global;

import net.sf.json.JSONArray;

/**
 * @ClassName: BaseAction1
 * @Description: TODO(描述)
 * @author 张敏
 * @date 2015-2-3 下午03:29:30
 */
public class BaseController {
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
}
