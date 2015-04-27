/**  
 * @Title: MenuController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.base.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangmin.base.entity.Menu;
import com.zhangmin.base.service.MenuService;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: MenuController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Controller
public class MenuController extends BaseController{

	@Autowired
	private MenuService menuService;
	/**
	 * 
	 * @Description: 跳转到添加菜单界面
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value="/menuController/view")
	public ModelAndView view(HttpServletRequest request){
		ModelAndView view =new ModelAndView();
		try {
			
			view.setViewName("menu/addMenu");
		} catch (Exception e) {
		}
		return view;
	}
	/**
	 * 
	 * @Description: 保存菜单信息
	 * @param @param request
	 * @param @param session
	 * @param @param menu
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/menuController/save",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView save(Menu menu,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			menuService.saveMenu(menu);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/menuController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = menuService.getPagedMenuInfo(pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("menu/menuList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找需要更新的菜单
	 * @param @param menu
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/menuController/find")
	public ModelAndView find(Menu menu) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Menu menuInfo = menuService.findMenuById(menu.getId());
			view.addObject("menuInfo", menuInfo);
			view.setViewName("menu/updateMenu");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 更新菜单信息
	 * @param @param menu
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/menuController/update")
	public ModelAndView update(Menu menu,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			
			Menu menuInfo = menuService.findMenuById(menu.getId());
			menu.setCreateDate(menuInfo.getCreateDate());
			String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			menu.setUpdateDate(updateDate);
			menu.setFlag(menuInfo.getFlag());
			menuService.updateMenu(menu);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 删除菜单信息
	 * @param @param menu
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/menuController/delete")
	public ModelAndView delete(Menu menu,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			menuService.deleteMenu(menu.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/menuController/getMenuList")
	public @ResponseBody Object  ajaxgetMenuList(Menu menu,HttpServletRequest request) throws Exception{
		List<Menu> menuList = new ArrayList<Menu>();
		try{
			JSONArray arrayJson1 = new JSONArray();
			menuList= menuService.menuList(menu);
			for (Menu menu2 : menuList) {
				JSONObject json1 = new JSONObject();
				json1.put("id", menu2.getId());
				json1.put("name", menu2.getName());
				arrayJson1.add(json1);
			}
			return String.valueOf(arrayJson1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		}
}
