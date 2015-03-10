/**  
 * @Title: LoginAction.java
 * @Package com.zhangmin.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.user.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseAction;
import com.alibaba.fastjson.util.Base64;
import com.duruxin.util.MD5;
import com.zhangmin.base.entity.UserInfo;
import com.zhangmin.constant.Const;
import com.zhangmin.constant.Global;
import com.zhangmin.constant.Util;
import com.zhangmin.user.service.UserInfoService;

/**
 * ClassName: LoginAction 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
@Controller
public class LoginController extends BaseAction{
	
	@Autowired
	private UserInfoService userInfoService;
	
	private MD5 md5 = new MD5();
	
	@RequestMapping(value = "/dologin", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView doLogin(HttpSession session,UserInfo user,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		String viewName = "login";
		boolean flag = true;
		//String validationCode = (String) session.getAttribute("verifycode");
		String validationCode = "1234";
		String viewValidationCode = request.getParameter("verifycode");
		if(!Util.isEmpty(viewValidationCode)){
			if(!viewValidationCode.equalsIgnoreCase(validationCode)){
				super.setMessageCode(Const.USER_VALIDATION_CODE);
				flag = false;
				flag = true;
			}
			if(flag){
				UserInfo param = new UserInfo();
				param.setUserName(user.getUserName());
				param.setPassword(md5.getMD5ofStr(user.getPassword()));
				UserInfo userInfo = userInfoService.getUserInfo(param);
				if(userInfo == null){
					super.setMessageCode(Const.USER_USERNAME_NOT_FOUND);
				}else{
					if(!userInfo.getPassword().endsWith(param.getPassword())){
						super.setMessageCode(Const.USER_PASSWORD_FAIL);
					}
					else{
						viewName = "index";
						session.setAttribute(Global.USER_INFO,userInfo);
						userInfoService.updateLastLoginDate(userInfo);
					}
				}
			}
			view.addObject("messagecode", super.getMessageCode());
		}
		view.setViewName(viewName);
		return view;
		
	}
	
	/**
	 * 功能：显示框架内容
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin_top", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView admin_top(HttpSession session,HttpServletRequest request){
		ModelAndView andView = new ModelAndView("admin_top");
		return andView;
	}
	
	@RequestMapping(value = "/left", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView left(HttpSession session,HttpServletRequest request){
		ModelAndView andView = new ModelAndView("left");
		return andView;
	}
	
	@RequestMapping(value = "/right", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView right(HttpSession session,HttpServletRequest request){
		ModelAndView andView = new ModelAndView("right");
		return andView;
	}
}
