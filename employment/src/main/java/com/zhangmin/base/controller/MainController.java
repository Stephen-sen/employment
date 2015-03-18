/**  
 * @Title: MainController.java
 * @Package com.zhangmin.base.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-16
 */
package com.zhangmin.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangmin.constant.Stat;


/**
 * ClassName: MainController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-16
 */
public class MainController {

	@RequestMapping("/main/back")
	public String back(HttpServletRequest request) throws Exception{
		String targetUrl = null;
		try{
			targetUrl = getSessionListUrl(request);
			return "redirect:"+targetUrl;
		}catch(Exception e){ 
			throw e;
		}
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
