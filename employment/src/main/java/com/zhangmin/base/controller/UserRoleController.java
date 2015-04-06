/**  
 * @Title: UserRoleController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.base.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.base.entity.Role;
import com.zhangmin.base.entity.UserRole;
import com.zhangmin.base.service.UserRoleService;
import com.zhangmin.base.service.RoleService;
import com.zhangmin.center.controller.UserController;
import com.zhangmin.center.entity.UserInfo;
import com.zhangmin.center.service.UserInfoService;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: UserRoleController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Controller
public class UserRoleController extends BaseController{

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserController userController;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 
	 * @Description: 跳转到添加用户角色界面
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value="/userRoleController/view")
	public ModelAndView view(HttpServletRequest request,UserRole userRole){
		ModelAndView view =new ModelAndView();
		try {
			List<Role> roleList = roleService.roleListInfo();
			userRole = userRoleService.findUserRoleById(userRole.getId());
			UserInfo user = userInfoService.findUserById(userRole.getUser().getId());
			view.addObject("roleList", roleList);
			view.addObject("userInfo", user);
			view.addObject("userRole", userRole);
			view.setViewName("userRole/addUserRole");
		} catch (Exception e) {
		}
		return view;
	}
	/**
	 * 
	 * @Description: 保存用户角色信息
	 * @param @param request
	 * @param @param session
	 * @param @param userRole
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/userRoleController/save",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView save(UserRole userRole,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userRoleService.saveUserRole(userRole);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/userRoleController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = userRoleService.getPagedUserRoleInfo(pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("userRole/userRoleList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找需要更新的用户角色
	 * @param @param userRole
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/userRoleController/find")
	public ModelAndView find(UserRole userRole) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			UserRole userRoleInfo = userRoleService.findUserRoleById(userRole.getId());
			view.addObject("roleInfo", userRoleInfo);
			
			List<Role> roleList = roleService.roleListInfo();
			UserInfo user = userInfoService.findUserById(userRoleInfo.getUser().getId());
			view.addObject("roleList", roleList);
			view.addObject("userInfo", user);
			view.addObject("userRole", userRoleInfo);
			view.setViewName("userRole/updateUserRole");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 更新用户角色信息
	 * @param @param userRole
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/userRoleController/update")
	public ModelAndView update(UserRole userRole,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			
			UserRole userRoleInfo = userRoleService.findUserRoleById(userRole.getId());
			userRole.setCreateDate(userRoleInfo.getCreateDate());
			String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			userRole.setUpdateDate(updateDate);
			userRole.setFlag(userRoleInfo.getFlag());
			userRoleService.updateUserRole(userRole);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 删除用户角色信息
	 * @param @param userRole
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/userRoleController/delete")
	public ModelAndView delete(UserRole userRole,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			userRoleService.deleteUserRole(userRole.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
}
