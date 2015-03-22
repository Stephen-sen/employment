/**  
 * @Title: PostionController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.center.entity.Ability;
import com.zhangmin.center.entity.Company;
import com.zhangmin.center.entity.Job;
import com.zhangmin.center.entity.Pos_Abi;
import com.zhangmin.center.entity.Position;
import com.zhangmin.center.service.AbilityService;
import com.zhangmin.center.service.CompanyService;
import com.zhangmin.center.service.JobService;
import com.zhangmin.center.service.Pos_AbiService;
import com.zhangmin.center.service.PositionService;
import com.zhaosen.base.Page;

/**
 * ClassName: PostionController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Controller
public class JobController extends BaseController {

	@Autowired
	private JobService jobService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private AbilityService abilityService;
	@Autowired
	private Pos_AbiService pos_AbiService;
	/**
	 * 
	 * @Description: 跳转到招聘新增界面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jobController/view")
	public ModelAndView view(){
		ModelAndView view = new ModelAndView();
		List<Company> companyList=companyService.getCompanyInfo();
		view.addObject("companyList", companyList);
		List<Position> positionList=positionService.getPositionInfo();
		view.addObject("positionList", positionList);
		Map abilityMap = abilityService.findFistAbilityList();
		view.addObject("abilityMap", abilityMap);
		view.setViewName("/job/addJob");
		return view;
	}
	/**
	 * 
	 * @Description: 保存招聘信息
	 * @param @param job
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/jobController/save")
	public ModelAndView save(Job job, Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			
			Pos_Abi pos_Abi = new Pos_Abi();
			String abilityTem = null;
			String[] array = job.getAbilityArry();
			for (String str : array) {
				abilityTem = abilityTem+","+str;
			}
			String abilityStr = abilityTem.substring(5, abilityTem.length());
			String[] abilityId = abilityStr.split(",");
			for (String stringId : abilityId) {
				Ability ability = new Ability();
				ability.setId(stringId);
				pos_Abi.setAbility(ability);
				pos_Abi.setCompany(job.getCompany());
				pos_Abi.setPosition(job.getPosition());
				pos_AbiService.savePos_Abi(pos_Abi);
			}
			jobService.saveJob(job);
			view = setScoreView(job,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jobController/setScoreView")
	public ModelAndView setScoreView(Job job,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Map abilityMap = pos_AbiService.getAbilityInfo(job);
			view.addObject("abilityMap", abilityMap);
			view.setViewName("/job/setScore");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查询招聘信息
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/jobController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			pageNo = pageNo == null?1:pageNo;
			int pageSize = this.getCookiesPageSize(request);
			Page pagedData = jobService.getPagedJobInfo(pageNo, pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("/job/jobList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value="/jobController/find")
	public ModelAndView find(Job job,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Job jobInfo = jobService.findJobById(job.getId());
			view.addObject("jobInfo", jobInfo);
			view.setViewName("/job/updateJob");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/jobController/update")
	public ModelAndView update(Job job,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			jobService.updateJob(job);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/jobController/delete")
	public ModelAndView delete(Job job,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			jobService.deleteMajor(job.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
}
