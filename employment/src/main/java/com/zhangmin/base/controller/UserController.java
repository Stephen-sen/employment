/**  
 * @Title: UserController.java
 * @Package com.zhangmin.user.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-15
 */
package com.zhangmin.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseAction;
import com.zhangmin.base.entity.UserInfo;
import com.zhangmin.base.service.UserInfoService;
import com.zhaosen.base.Page;
import com.zhaosen.util.MD5;

/**
 * ClassName: UserController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-15
 */

@Controller
public class UserController  extends BaseAction{

	@Autowired
	private UserInfoService userInfoService;
	
	private MD5 md5 = new MD5();
	/**
	 * @Description: 跳转到注册界面
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-15
	 */
	@RequestMapping(value = "/userController/regist")
	public ModelAndView addView(HttpServletRequest request) throws Exception{
		ModelAndView mav=new ModelAndView("user/regist");
		return mav;
		}
	
	/**
	 * 
	 * @Description: 保存用户注册信息
	 * @param @param request
	 * @param @param session
	 * @param @param user
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/userController/save", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView save(HttpServletRequest request,HttpSession session,UserInfo user) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			user.setPassWord(md5.getMD5ofStr(user.getPassWord()));
			userInfoService.saveUserInfo(user);
			view.setViewName("login");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查询所有注册用户信息
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/userController/list")
	public ModelAndView list(UserInfo userInfo,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = userInfoService.getPagedUserInfo(userInfo, pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("user/userList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 删除注册用户
	 * @param @param request
	 * @param @param userInfo
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/userController/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView delete(UserInfo userInfo,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			
			userInfoService.deleteUser(userInfo.getId());
			view=list(userInfo,pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找用户信息，用来更新用户使用
	 * @param @param request
	 * @param @param userInfo
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/userController/find", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView find(HttpServletRequest request,UserInfo userInfo) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userInfo = userInfoService.findUserById(userInfo.getId());
			view.addObject("userInfo",userInfo);
			view.setViewName("user/updateUser");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 更新用户信息
	 * @param @param request
	 * @param @param userInfo
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/userController/update", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView update(UserInfo userInfo,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userInfoService.updateUser(userInfo);
			view=list(new UserInfo(),pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 更改用户的状态，如果原状态为0则置为1，反正置为0.
	 * @param @param userId   
	 * @return void  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/userController/statusMan", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView statusMan(UserInfo userInfo,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userInfoService.userStatusMan(userInfo.getId());
			view=list(userInfo,pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找待审核注册用户
	 * @param @param request
	 * @param @param userInfo
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-16
	 */
	@RequestMapping(value = "/userController/registList")
	public ModelAndView registList(UserInfo userInfo,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = userInfoService.getPagedRegistList(userInfo, pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("user/registList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 审核注册用户 0：待审核，1:正常，2：注销，3：审核不同
	 * @param @param request
	 * @param @param user
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-17
	 */
	@RequestMapping(value = "/userController/registPass")
	public ModelAndView registPass(UserInfo user,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			UserInfo userInfo = new UserInfo();
				userInfo = userInfoService.findUserById(user.getId());
				userInfo.setStatus(user.getStatus());
				userInfoService.registPass(userInfo);
				
				view=registList(new UserInfo(),pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	}
