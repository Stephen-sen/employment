/**  
 * @Title: PostionController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.zhangmin.center.entity.Ability;
import com.zhangmin.center.entity.Company;
import com.zhangmin.center.entity.Job;
import com.zhangmin.center.entity.Pos_Abi;
import com.zhangmin.center.entity.Position;
import com.zhangmin.center.entity.Stu_Abi;
import com.zhangmin.center.entity.UserInfo;
import com.zhangmin.center.service.AbilityService;
import com.zhangmin.center.service.CompanyService;
import com.zhangmin.center.service.JobService;
import com.zhangmin.center.service.Pos_AbiService;
import com.zhangmin.center.service.PositionService;
import com.zhangmin.center.service.Stu_AbiService;
import com.zhangmin.constant.Const;
import com.zhangmin.constant.Global;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

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
	@Autowired
	private PositionController positionController;
	@Autowired
	private CompanyController companyController;
	@Autowired
	private Stu_AbiService stu_AbiService;
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
	/**
	 * 
	 * @Description: 跳转到为考核素质分配分值界面
	 * @param @param job
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-23
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jobController/setScoreView")
	public ModelAndView setScoreView(Job job,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Map abilityMap = pos_AbiService.getAbilityInfo(job);
			view.addObject("abilityMap", abilityMap);
			view.addObject("jobInfo", job);
			view.setViewName("/job/setScore");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 当素质分值分值界面返回到上一步时，通过ajax先把之前保存的数据删除
	 * @param @param companyId
	 * @param @param positionId
	 * @param @param request
	 * @param @return   
	 * @return Object  
	 * @author 张敏
	 * @date 2015-3-23
	 */
	@RequestMapping(value="/jobController/backUpStep")
	public @ResponseBody Object backUpStep(String companyId,String positionId,HttpServletRequest request){
		List<String> strList = new ArrayList<String>();
		try {
			jobService.deleteUpDate(companyId,positionId);
			strList.add(Const.MSG_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(JSONArray.toJSON(strList));
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
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jobController/list")
	public ModelAndView list(Job jobInfo,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			pageNo = pageNo == null?1:pageNo;
			int pageSize = this.getCookiesPageSize(request);
			Page pagedData = jobService.getPagedJobInfo(jobInfo,pageNo, pageSize);
			List<Job> jobListTemp=pagedData.getResult();
			List<Job> jobList=new ArrayList<Job>();
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute(Global.USER_INFO);
			for (Job job : jobListTemp) {
			List<Stu_Abi> stuAbiList = stu_AbiService.getStatus(userInfo.getId(), job.getId());
			if(stuAbiList.size()!=0){
				job.setStatus("1");
				}else{
					job.setStatus("0");
				}
			jobList.add(job);
			}
			/**
			 * 重新组装pagedData
			 */
			Page pagedDatas = new Page(pagedData.getStart(), pagedData.getTotalCount(), pagedData.getPageSize(), jobList);
			String type=request.getParameter("type");
			view.addObject("pagedData", pagedDatas );
			view.addObject("type", type);
			view.setViewName("/job/jobList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jobController/find")
	public ModelAndView find(Job job,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Job jobInfo = jobService.findJobById(job.getId());
			Map abilityMap = pos_AbiService.getAbilityInfo(jobInfo);
			
			List<Position> positionList=positionService.getPositionInfo();
			view.addObject("positionList", positionList);
			view.addObject("abilityMap", abilityMap);
			view.addObject("jobInfo", jobInfo);
			view.setViewName("/job/updateJob");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/jobController/detail")
	public ModelAndView jobDetail(Job job,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Job jobInfo = jobService.findJobDetail(job);
			Map abilityMap = pos_AbiService.getAbilityInfo(jobInfo);
			view.addObject("abilityMap", abilityMap);
			view.addObject("jobInfo", jobInfo);
			view.setViewName("/job/jobDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	/**
	 * 
	 * @Description: 更新发布的职位信息，首先更新公司信息和职位信息，然后更新职位素质表的score，最后更新job表
	 * @param @param job
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-24
	 */
	@RequestMapping(value = "/jobController/update")
	public ModelAndView update(Job job,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Company companyInfo = job.getCompany();
			companyInfo=companyService.findCompanyById(companyInfo.getId());
			companyController.update(companyInfo,pageNo,request);
			
			Position positionInfo = job.getPosition();
			positionInfo = positionService.findPositionById(positionInfo.getId());
			positionController.update(positionInfo,pageNo,request);
			
			Job jobInfo = jobService.findJobById(job.getId());
			Pos_Abi pos_Abi = job.getPos_Abi();
			pos_Abi.setCompany(job.getCompany());
			pos_Abi.setPosition(job.getPosition());
			pos_AbiService.updatePos_Abi(pos_Abi);
			
			job.setCreateDate(jobInfo.getCreateDate());
			String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
			job.setUpdateDate(updateDate);
			job.setFlag(jobInfo.getFlag());
			jobService.updateJob(job);
			view= list(job,pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 先删除招聘信息表，再删除职位素质表
	 * @param @param job
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-24
	 */
	@RequestMapping(value = "/jobController/delete")
	public ModelAndView delete(Job job,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Job jobInfo = jobService.findJobDetail(job);
			jobService.deleteJob(jobInfo.getId());
			pos_AbiService.deletePos_Abi(jobInfo);
			view= list(job,pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value="/jobController/getJobList")
	public@ResponseBody Object ajaxGetJobList(){
		List<Job> jobList = new ArrayList<Job>();
		try {
			jobList = jobService.jobList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(JSONArray.toJSON(jobList));
	}
	
	@RequestMapping(value="/jobController/ajaxCheckJob")
	public@ResponseBody Object ajaxCheckJob(Job job){
		List<String> item = new ArrayList<String>();
		try {
			List<Job> jobList = jobService.findJob(job);
			if(jobList.size()>0){
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
