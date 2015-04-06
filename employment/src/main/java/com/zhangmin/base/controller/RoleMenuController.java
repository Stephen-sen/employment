/**  
 * @Title: RoleMenuController.java
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangmin.base.entity.Menu;
import com.zhangmin.base.entity.Role;
import com.zhangmin.base.entity.RoleMenu;
import com.zhangmin.base.service.MenuService;
import com.zhangmin.base.service.RoleMenuService;
import com.zhangmin.base.service.RoleService;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: RoleMenuController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Controller
public class RoleMenuController extends BaseController{

	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private RoleController roleController;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	/**
	 * 
	 * @Description: 跳转到添加角色权限界面
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value="/roleMenuController/view")
	public ModelAndView view(HttpServletRequest request){
		ModelAndView view =new ModelAndView();
		try {
			view.setViewName("roleMenu/addRoleMenu");
		} catch (Exception e) {
		}
		return view;
	}
	/**
	 * 
	 * @Description: 保存角色权限信息
	 * @param @param request
	 * @param @param session
	 * @param @param roleMenu
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleMenuController/save",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView save(RoleMenu roleMenu,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			/*
			 * 先删除再添加
			 */
			List<RoleMenu> roleMenuList = roleMenuService.findRoleMenu(roleMenu.getRole().getId());
			roleMenuService.deleteAll(roleMenuList);
			
			String roleMenuStr = roleMenu.getRoleMenuStr();
			if(roleMenuStr != null && !roleMenuStr.equals("")){
				String[] roleMenus = roleMenuStr.split(",");
				for (String str : roleMenus)
				{
					if(str != null){
						RoleMenu roleMenuInfo = new RoleMenu();
						roleMenuInfo.setRole(roleMenu.getRole());
						Menu menu = new Menu();
						menu.setId(str);
						roleMenuInfo.setMenu(menu);
						roleMenuService.saveRoleMenu(roleMenuInfo);
					}
				}
			}
			view= roleController.list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/roleMenuController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = roleMenuService.getPagedRoleMenuInfo(pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("roleMenu/roleMenuList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找需要更新的角色权限
	 * @param @param roleMenu
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleMenuController/find")
	public ModelAndView find(Role role) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			JSONArray arrayJson = new JSONArray();
			Role roleInfo = roleService.findRoleById(role.getId());
			List<Menu> menuList = menuService.menuListInfo();
			List<RoleMenu> roleMenuList = roleMenuService.findRoleMenu(role.getId());
			if(roleMenuList !=null){
				for (RoleMenu roleMenu : roleMenuList) {
					JSONObject json = new JSONObject();
		    		json.put("menuId", roleMenu.getMenu().getId());
		    		arrayJson.add(json);
				}
				view.addObject("idjson", arrayJson);
			}
			view.addObject("menuList", menuList);
			view.addObject("roleInfo", roleInfo);
			view.addObject("roleMenuList", roleMenuList);
			view.setViewName("roleMenu/addRoleMenu");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 更新角色权限信息
	 * @param @param roleMenu
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleMenuController/update")
	public ModelAndView update(RoleMenu roleMenu,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			
			RoleMenu roleMenuInfo = roleMenuService.findRoleMenuById(roleMenu.getId());
			roleMenu.setCreateDate(roleMenuInfo.getCreateDate());
			String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			roleMenu.setUpdateDate(updateDate);
			roleMenu.setFlag(roleMenuInfo.getFlag());
			roleMenuService.updateRoleMenu(roleMenu);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 删除角色权限信息
	 * @param @param roleMenu
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/roleMenuController/delete")
	public ModelAndView delete(RoleMenu roleMenu,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			roleMenuService.deleteRoleMenu(roleMenu.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
}
