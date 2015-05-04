/**  
 * @Title: MainController.java
 * @Package com.zhangmin.base.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-16
 */
package com.zhangmin.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.action.support.BaseController;


/**
 * ClassName: MainController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-16
 */
@Controller
public class MainController extends BaseController{

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
}
