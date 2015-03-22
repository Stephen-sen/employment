/**  
 * @Title: PostionController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.center.entity.Ability;
import com.zhangmin.center.service.AbilityService;
import com.zhaosen.base.Page;

/**
 * ClassName: PostionController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Controller
public class AbilityController extends BaseController {

	@Autowired
	private AbilityService abilityService;
	/**
	 * 
	 * @Description: 跳转到素质信息新增界面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/abilityController/view")
	public ModelAndView view(){
		ModelAndView view = new ModelAndView();
		List<Ability> preAbilityList = abilityService.findPreAbility();
		view.addObject("preAbilityList", preAbilityList);
		view.setViewName("/ability/addAbility");
		return view;
	}
	/**
	 * 
	 * @Description: 保存素质信息信息
	 * @param @param ability
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/abilityController/save")
	public ModelAndView save(Ability ability, Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			abilityService.saveAbility(ability);
			view = list(pageNo,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	/**
	 * 
	 * @Description: 查询素质信息信息
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/abilityController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			pageNo = pageNo == null?1:pageNo;
			int pageSize = this.getCookiesPageSize(request);
			Page pagedData = abilityService.getPagedAbilityInfo(pageNo, pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("/ability/abilityList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value="/abilityController/find")
	public ModelAndView find(Ability ability,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Ability abilityInfo = abilityService.findAbilityById(ability.getId());
			view.addObject("abilityInfo", abilityInfo);
			List<Ability> preAbilityList = abilityService.findPreAbility();
			view.addObject("preAbilityList", preAbilityList);
			view.setViewName("/ability/updateAbility");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/abilityController/update")
	public ModelAndView update(Ability ability,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			abilityService.updateAbility(ability);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/abilityController/delete")
	public ModelAndView delete(Ability ability,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			abilityService.deleteAbility(ability.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
}
