/**  
 * @Title: RoleController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.base.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.base.entity.Role;
import com.zhangmin.base.service.RoleService;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: RoleController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Controller
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	/**
	 * 
	 * @Description: 跳转到添加角色界面
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value="/roleController/view")
	public ModelAndView view(HttpServletRequest request){
		ModelAndView view =new ModelAndView();
		try {
			
			view.setViewName("role/addRole");
		} catch (Exception e) {
		}
		return view;
	}
	/**
	 * 
	 * @Description: 保存角色信息
	 * @param @param request
	 * @param @param session
	 * @param @param role
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleController/save",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView save(Role role,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			roleService.saveRole(role);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/roleController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = roleService.getPagedRoleInfo(pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("role/roleList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找需要更新的角色
	 * @param @param role
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleController/find")
	public ModelAndView find(Role role) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Role roleInfo = roleService.findRoleById(role.getId());
			view.addObject("roleInfo", roleInfo);
			view.setViewName("role/updateRole");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 更新角色信息
	 * @param @param role
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleController/update")
	public ModelAndView update(Role role,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			
			Role roleInfo = roleService.findRoleById(role.getId());
			role.setCreateDate(roleInfo.getCreateDate());
			String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			role.setUpdateDate(updateDate);
			role.setFlag(roleInfo.getFlag());
			roleService.updateRole(role);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 删除角色信息
	 * @param @param role
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleController/delete")
	public ModelAndView delete(Role role,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			roleService.deleteRole(role.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
}
