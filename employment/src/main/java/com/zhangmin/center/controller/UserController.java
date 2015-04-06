/**  
 * @Title: UserController.java
 * @Package com.zhangmin.user.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-15
 */
package com.zhangmin.center.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.zhangmin.base.entity.UserRole;
import com.zhangmin.base.service.UserRoleService;
import com.zhangmin.center.entity.Major;
import com.zhangmin.center.entity.UserInfo;
import com.zhangmin.center.service.MajorService;
import com.zhangmin.center.service.UserInfoService;
import com.zhangmin.constant.Const;
import com.zhangmin.constant.Global;
import com.zhangmin.constant.Util;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;
import com.zhaosen.util.MD5;

/**
 * ClassName: UserController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-15
 */

@Controller
public class UserController  extends BaseController{

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private UserRoleService userRoleService;
	
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
		List<Major> majorList=majorService.majorList();
		mav.addObject("majorList", majorList);
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
	@RequestMapping(value = "/userController/save")
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
			List<Major> majorList=majorService.majorList();
			view.addObject("majorList", majorList);
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
	@RequestMapping(value = "/userController/delete")
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
	@RequestMapping(value = "/userController/find")
	public ModelAndView find(HttpServletRequest request,UserInfo userInfo) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userInfo = userInfoService.findUserById(userInfo.getId());
			view.addObject("userInfo",userInfo);
			List<Major> majorList=majorService.majorList();
			view.addObject("majorList", majorList);
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
	@RequestMapping(value = "/userController/update")
	public ModelAndView update(UserInfo userInfo,Integer pageNo,HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		ModelAndView view =new ModelAndView();
		try{
			String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			
				UserInfo pram = userInfoService.findUserById(userInfo.getId());
				userInfo.setPassWord(pram.getPassWord());
				userInfo.setFlag(pram.getFlag());
				userInfo.setStatus(pram.getStatus());
				userInfo.setLastLoginDate(pram.getLastLoginDate());
				userInfo.setCreateDate(pram.getCreateDate());
				userInfo.setUpdateDate(createDate);
				Date birthdate =  DateUtil.convertStringToDate(userInfo.getBirthDate(), DateUtil.DATE_FORMAT_yyyyMMdd);
				userInfo.setAge(Util.getAge(birthdate));
				userInfoService.updateUser(userInfo);
				UserInfo user=(UserInfo)session.getAttribute(Global.USER_INFO);
				if(user.getId().equals(userInfo.getId())){
					session.removeAttribute(Global.USER_INFO);
					session.setAttribute(Global.USER_INFO,userInfo);
					view.setViewName("right");
				}
				else{
				view=list(new UserInfo(),pageNo,request);
			}
			
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
	@RequestMapping(value = "/userController/statusMan")
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
	 * @Description: 审核注册用户 0：待审核，1:正常，2：注销，3：审核不通过
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
				 
				UserRole userRole = new UserRole();
				userRole.setUser(userInfo);
				userRoleService.saveUserRole(userRole);
				view=registList(new UserInfo(),pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/userController/detail")
	public ModelAndView persionalInfo(UserInfo userInfo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			String id = null;
			String type=request.getParameter("type");
			UserInfo user = (UserInfo)request.getSession().getAttribute(Global.USER_INFO);
			if(null!=type && type.equals("persional")){
				id=user.getId();
			}
			else{
				id=userInfo.getId();
			}
			userInfo = userInfoService.findUserById(id);
			view.addObject("userInfo",userInfo);
			view.setViewName("user/persionalInfo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/userController/resetPasw")
	public ModelAndView updatePasw(HttpServletRequest request,Integer pageNo,UserInfo userInfo) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userInfo.setPassWord(md5.getMD5ofStr("0000"));
			userInfoService.updatePasw(userInfo);
			view=list(userInfo,pageNo,request);
			view.addObject("messageCode", Const.MSG_SUCCESS);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/userController/updatePwdView")
	public ModelAndView updatePwdView(HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			view.setViewName("user/updatePwd");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	@RequestMapping(value = "/userController/updatePwd")
	public ModelAndView updatePwd(HttpServletRequest request,UserInfo userInfo) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userInfo.setPassWord(md5.getMD5ofStr(userInfo.getNewPwd()));
			userInfoService.updatePasw(userInfo);
			view.setViewName("right");
			view.addObject("messageCode", Const.MSG_SUCCESS);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	@RequestMapping(value="/userController/checkOldPwd")
	public@ResponseBody Object ajaxGetcheckOldPwd(UserInfo userInfo,HttpServletRequest request){
		List<String> result = new ArrayList<String>();
		try {
			String oldPwd =userInfo.getPassWord();
			userInfo = userInfoService.findUserById(userInfo.getId());
			if(oldPwd!=null && oldPwd!=""){
				oldPwd =md5.getMD5ofStr(oldPwd);
				if(!oldPwd.endsWith(userInfo.getPassWord())){
					result.add("false");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(JSONArray.toJSON(result));
	}
	}
