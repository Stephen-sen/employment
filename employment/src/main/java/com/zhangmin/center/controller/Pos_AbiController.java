/**  
 * @Title: PostionController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.center.entity.Pos_Abi;
import com.zhangmin.center.service.Pos_AbiService;
import com.zhaosen.base.Page;

/**
 * ClassName: PostionController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Controller
public class Pos_AbiController extends BaseController {

	@Autowired
	private Pos_AbiService pos_AbiService;
	@Autowired
	private JobController jobController;
	/**
	 * 
	 * @Description: 跳转到公司新增界面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/pos_AbiController/view")
	public ModelAndView view(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/pos_Abi/addPos_Abi");
		return view;
	}
	/**
	 * 
	 * @Description: 保存为素质分配的分值
	 * @param @param pos_Abi
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/pos_AbiController/save")
	public ModelAndView save(Pos_Abi pos_Abi, Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			pos_AbiService.updatePos_Abi(pos_Abi);
			view = jobController.list(pageNo,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	/**
	 * 
	 * @Description: 查询公司信息
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/pos_AbiController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			pageNo = pageNo == null?1:pageNo;
			int pageSize = this.getCookiesPageSize(request);
			Page pagedData = pos_AbiService.getPagedPos_AbiInfo(pageNo, pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("/pos_Abi/pos_AbiList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value="/pos_AbiController/find")
	public ModelAndView find(Pos_Abi pos_Abi,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Pos_Abi pos_AbiInfo = pos_AbiService.findPos_AbiById(pos_Abi.getId());
			view.addObject("pos_AbiInfo", pos_AbiInfo);
			view.setViewName("/pos_Abi/updatePos_Abi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/pos_AbiController/update")
	public ModelAndView update(Pos_Abi pos_Abi,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pos_AbiService.updatePos_Abi(pos_Abi);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/*
	@RequestMapping(value = "/pos_AbiController/delete")
	public ModelAndView delete(Pos_Abi pos_Abi,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pos_AbiService.deletePos_Abi(pos_Abi);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}*/
}
