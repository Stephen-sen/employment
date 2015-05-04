/**  
 * @Title: LoginAction.java
 * @Package com.zhangmin.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.base.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.base.entity.Menu;
import com.zhangmin.base.entity.RoleMenu;
import com.zhangmin.base.entity.UserRole;
import com.zhangmin.base.service.RoleMenuService;
import com.zhangmin.base.service.UserRoleService;
import com.zhangmin.center.entity.UserInfo;
import com.zhangmin.center.service.UserInfoService;
import com.zhangmin.constant.Const;
import com.zhangmin.constant.Global;
import com.zhangmin.constant.Util;
import com.zhaosen.util.DateUtil;
import com.zhaosen.util.MD5;

/**
 * ClassName: LoginAction 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	private MD5 md5 = new MD5();
	
	/**
	 * 
	 * @Description: 用户登录管理
	 * @param @param session
	 * @param @param user
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/dologin", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView doLogin(HttpSession session,UserInfo user,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			String viewName = "login";
			if(session.getAttribute(Global.USER_INFO) !=null){
				viewName = "index";
			}else{
			boolean flag = true;
			String validationCode = (String) session.getAttribute("verifycode");
			String viewValidationCode = request.getParameter("verifycode");
			if(!Util.isEmpty(viewValidationCode)){
				if(!viewValidationCode.equalsIgnoreCase(validationCode)){
					view.addObject("userName", user.getUserName());
					super.setMessageCode(Const.USER_VALIDATION_CODE);
					flag = false;
				}
				if(flag){
					UserInfo param = new UserInfo();
					param.setUserName(user.getUserName());
					param.setPassWord(md5.getMD5ofStr(user.getPassWord()));
					UserInfo userInfo = userInfoService.getUserInfo(param);
					if(userInfo == null){
						super.setMessageCode(Const.USER_USERNAME_NOT_FOUND);
					}else{
						if(!userInfo.getPassWord().endsWith(param.getPassWord())){
							super.setMessageCode(Const.USER_PASSWORD_FAIL);
						}
						else{
							viewName = "index";
							UserRole userRole = userRoleService.findUserRole(userInfo.getId());
							session.setAttribute("userRole", userRole);
							List<RoleMenu> roleMenuMess = roleMenuService.findRoleMenu(userRole.getRole().getId());
							List<Menu> userMenuList = new ArrayList<Menu>();
							StringBuffer btStr = new StringBuffer();
							for (RoleMenu roleMenu : roleMenuMess)
							{
								userMenuList.add(roleMenu.getMenu());
								if(roleMenu.getMenu().getButtonCode()!=null){
									btStr.append(roleMenu.getMenu().getButtonCode());
								}
							}
							session.setAttribute("userMenuList", userMenuList);
							session.setAttribute(Global.USER_BUTTON_STR, btStr);
							if((userInfo.getLoginTimes()+"").equals("null")){
								userInfo.setLoginTimes(1);
							}
							else{
								userInfo.setLoginTimes(userInfo.getLoginTimes() + 1);
							}
							session.setAttribute(Global.USER_INFO,userInfo);
							session.setAttribute("lastLoginDate", userInfo.getLastLoginDate());
							session.setAttribute("lastLoginIp", userInfo.getLastLoginIp());
							String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
							userInfo.setLastLoginDate(createDate);
							userInfo.setUpdateDate(createDate);
							userInfo.setLastLoginIp(Util.getIpAddr(request));
							userInfoService.updateLogin(userInfo);
						}
					}
				}
				view.addObject("messagecode", super.getMessageCode());
			}else{
				view.addObject("messagecode", Const.USER_VALIDATION_CODE_NULL);
			}
			}
			view.setViewName(viewName);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET,RequestMethod.POST })
	public ModelAndView logout(HttpSession session,HttpServletRequest request){
		session.removeAttribute(Global.USER_INFO);
		ModelAndView andView = new ModelAndView("login");
		return andView;
	}
}
