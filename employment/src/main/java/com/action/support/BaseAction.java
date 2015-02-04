/** 
 * @Title: BaseAction1.java 
 * @Package com.action.support 
 * @author 张敏
 * @date 2015-2-3 下午03:29:30 
 * @version V1.0 
 */
package com.action.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * @ClassName: BaseAction1
 * @Description: TODO(描述)
 * @author 张敏
 * @date 2015-2-3 下午03:29:30
 */
public class BaseAction {
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	protected JSONArray result;

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

}
