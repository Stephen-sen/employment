/**  
 * @Title: MajorController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.center.controller;

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
import com.zhangmin.center.entity.Major;
import com.zhangmin.center.service.MajorService;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: MajorController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Controller
public class MajorController extends BaseController{

	@Autowired
	private MajorService majorService;
	/**
	 * 
	 * @Description: 跳转到添加专业界面
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value="/majorController/view")
	public ModelAndView view(HttpServletRequest request){
		ModelAndView mav=new ModelAndView("major/addMajor");
		return mav;
	}
	/**
	 * 
	 * @Description: 保存专业信息
	 * @param @param request
	 * @param @param session
	 * @param @param major
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/majorController/save",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView save(Major major,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			majorService.saveMajor(major);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/majorController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = majorService.getPagedMajorInfo(pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("major/majorList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找需要更新的专业
	 * @param @param major
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/majorController/find")
	public ModelAndView find(Major major) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Major majorInfo = majorService.findMajorById(major.getId());
			view.addObject("majorInfo", majorInfo);
			view.setViewName("major/updateMajor");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 更新专业信息
	 * @param @param major
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/majorController/update")
	public ModelAndView update(Major major,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			
			Major majorInfo = majorService.findMajorById(major.getId());
			major.setCreateDate(majorInfo.getCreateDate());
			String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			major.setUpdateDate(updateDate);
			major.setFlag(majorInfo.getFlag());
			majorService.updateMajor(major);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 删除专业信息
	 * @param @param major
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/majorController/delete")
	public ModelAndView delete(Major major,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			majorService.deleteMajor(major.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value="/majorController/ajaxCheckMajor")
	public@ResponseBody Object ajaxCheckMajor(String name){
		List<String> item = new ArrayList<String>();
		try {
			List<Major> majorList = majorService.findMajor(name);
			if(majorList.size()>0){
				item.add("true");
			}else{
				item.add("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(JSONArray.toJSON(item));
	}
}
