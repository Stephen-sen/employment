/**  
 * @Title: PostionController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.center.entity.Position;
import com.zhangmin.center.service.PositionService;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: PostionController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Controller
public class PositionController extends BaseController {

	@Autowired
	private PositionService positionService;
	/**
	 * 
	 * @Description: 跳转到职业新增界面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/positionController/view")
	public ModelAndView view(){
		ModelAndView view = new ModelAndView();
		view.setViewName("position/addPosition");
		return view;
	}
	/**
	 * 
	 * @Description: 保存职位信息
	 * @param @param position
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/positionController/save")
	public ModelAndView save(Position position, Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			positionService.savePosition(position);
			view = list(pageNo,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	/**
	 * 
	 * @Description: 查询职位信息
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/positionController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			pageNo = pageNo == null?1:pageNo;
			int pageSize = this.getCookiesPageSize(request);
			Page pagedData = positionService.getPagedPositionInfo(pageNo, pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("position/positionList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value="/positionController/find")
	public ModelAndView find(Position position,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Position positionInfo = positionService.findPositionById(position.getId());
			view.addObject("positionInfo", positionInfo);
			view.setViewName("position/updatePosition");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/positionController/update")
	public ModelAndView update(Position position,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Position positionInfo = positionService.findPositionById(position.getId());
			position.setCreateDate(positionInfo.getCreateDate());
			String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			position.setUpdateDate(updateDate);
			position.setFlag(positionInfo.getFlag());
			positionService.updatePosition(position);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/positionController/delete")
	public ModelAndView delete(Position position,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			positionService.deletePosition(position.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
}
