/**  
 * @Title: Stu_AbiController.java
 * @Package com.zhangmin.center.controller
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.center.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.support.BaseController;
import com.zhangmin.center.entity.Ability;
import com.zhangmin.center.entity.Job;
import com.zhangmin.center.entity.Pos_Abi;
import com.zhangmin.center.entity.Stu_Abi;
import com.zhangmin.center.entity.UserInfo;
import com.zhangmin.center.service.JobService;
import com.zhangmin.center.service.Pos_AbiService;
import com.zhangmin.center.service.Stu_AbiService;
import com.zhangmin.constant.Global;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: Stu_AbiController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Controller
public class Stu_AbiController extends BaseController{

	@Autowired
	private Stu_AbiService stuAbiService;
	@Autowired
	private Pos_AbiService pos_AbiService;
	@Autowired
	private JobService jobService;
	/**
	 * 
	 * @Description: 跳转到添加能力分析界面
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/stuAbiController/view")
	public ModelAndView view(Stu_Abi stuAbi,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		UserInfo student = (UserInfo) request.getSession().getAttribute(Global.USER_INFO);
		stuAbi.setStudent(student);
		Job job = jobService.findJobById(stuAbi.getJob().getId());
		Map abilityMap = pos_AbiService.getAbilityInfo(job);
		view.addObject("job", job);
		view.addObject("abilityMap", abilityMap);
		view.setViewName("stuAbi/addStu_Abi");
		return view;
	}
	/**
	 * 
	 * @Description: 保存个人能力信息
	 * @param @param request
	 * @param @param session
	 * @param @param stuAbi
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/stuAbiController/save",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView save(Stu_Abi stuAbi,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			savestuAbiInfo(stuAbi);
			view = update(stuAbi,pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 解析传过来的数组信息，并保存到数据库
	 * @param @param stuAbi   
	 * @return void  
	 * @author 张敏
	 * @date 2015-3-28
	 */
	public void savestuAbiInfo(Stu_Abi stuAbi){
		String[] scoreKeyArray = stuAbi.getScoreKeyArray();
		String[] scoreValArray = stuAbi.getScoreValArray();
		for (int i = 0;i<scoreKeyArray.length;i++) {
			Ability abilityInfo = new Ability();
			Pos_Abi posAbiInfo = new Pos_Abi();
			abilityInfo.setId(scoreKeyArray[i]);
			stuAbi.setAbility(abilityInfo);
			stuAbi.setScore(scoreValArray[i]);
			
			posAbiInfo=pos_AbiService.getOnePosAbiInfo(stuAbi);
			stuAbi.setPos_Abi(posAbiInfo);
			stuAbiService.saveStu_Abi(stuAbi);
		}
	}
	/**
	 * 
	 * @Description: 更新就业能力表的符合度的值
	 * @param @param stuAbi
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/stuAbiController/update")
	public ModelAndView update(Stu_Abi stuAbi,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			List<Pos_Abi> pos_AbiList=pos_AbiService.getPosAbiInfo(stuAbi.getJob());
			List<Stu_Abi> stu_AbiList = stuAbiService.stuAbiList(stuAbi);
			for (Pos_Abi posAbi : pos_AbiList) {
				String id1 = posAbi.getAbility().getId();
				String score1=posAbi.getScore();
				for (Stu_Abi stuAbi2 : stu_AbiList) {
					String id2=stuAbi2.getAbility().getId();
					String score2=stuAbi2.getScore();
					if(id1.equals(id2)){
						DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
				        String  num = df.format(Double.parseDouble(score2)/Double.parseDouble(score1));//返回的是String类型
				        double conformity=Double.parseDouble(num);
				        stuAbi2.setConformity(conformity);
						String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
						stuAbi2.setUpdateDate(updateDate);
						stuAbiService.updateStu_Abi(stuAbi2);
					}
				}
			}
			view = result(stuAbi);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/stuAbiController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			pageNo = pageNo==null?1:pageNo;
			int pageSize=this.getCookiesPageSize(request);
			Page pagedData = stuAbiService.getPagedStu_AbiInfo(pageNo,pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("stuAbi/stuAbiList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查找需要更新的能力分析
	 * @param @param stuAbi
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/stuAbiController/find")
	public ModelAndView find(Stu_Abi stuAbi) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Stu_Abi stuAbiInfo = stuAbiService.findStu_AbiById(stuAbi.getId());
			view.addObject("stuAbiInfo", stuAbiInfo);
			view.setViewName("stuAbi/updateStu_Abi");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	/**
	 * 
	 * @Description: 删除能力分析信息
	 * @param @param stuAbi
	 * @param @param pageNo
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-18
	 */
	@RequestMapping(value = "/stuAbiController/delete")
	public ModelAndView delete(Stu_Abi stuAbi,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			stuAbiService.deleteStu_Abi(stuAbi.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 获取分析结果
	 * @param @param stuAbi
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-27
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView result(Stu_Abi stuAbi) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			Job job = jobService.findJobById(stuAbi.getJob().getId());
			Map abilityMap = stuAbiService.getAbilityInfo(stuAbi);
			view.addObject("abilityMap", abilityMap);
			view.addObject("job", job);
			view.setViewName("stuAbi/stu_AbiAnalysis");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	/**
	 * 
	 * @Description: 查看分析结果
	 * @param @param stuAbi
	 * @param @param request
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-27
	 */
	@RequestMapping(value = "/stuAbiController/seeResult")
	public ModelAndView seeResult(Stu_Abi stuAbi,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			UserInfo student = (UserInfo) request.getSession().getAttribute(Global.USER_INFO);
			stuAbi.setStudent(student);
			view = result(stuAbi);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/stuAbiController/reAnalysisView")
	public ModelAndView reAnalysisView(Stu_Abi stuAbi,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			UserInfo student = (UserInfo) request.getSession().getAttribute(Global.USER_INFO);
			stuAbi.setStudent(student);
			Job job = jobService.findJobById(stuAbi.getJob().getId());
			Map abilityMap = stuAbiService.getAbilityInfo(stuAbi);
			view.addObject("job", job);
			view.addObject("abilityMap", abilityMap);
			view.setViewName("stuAbi/updateStu_Abi");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	@RequestMapping(value = "/stuAbiController/reAnalysis")
	public ModelAndView reAnalysis(Stu_Abi stuAbi,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			String[] scoreKeyArray = stuAbi.getScoreKeyArray();
			String[] scoreValArray = stuAbi.getScoreValArray();
			for (int i = 0;i<scoreKeyArray.length;i++) {
				Ability abilityInfo = new Ability();
				Pos_Abi posAbiInfo = new Pos_Abi();
				abilityInfo.setId(scoreKeyArray[i]);
				stuAbi.setAbility(abilityInfo);
				
				stuAbi.setScore(scoreValArray[i]);
				
				posAbiInfo=pos_AbiService.getOnePosAbiInfo(stuAbi);
				stuAbi.setPos_Abi(posAbiInfo);
				Stu_Abi stuAbiInfo = stuAbiService.findStu_AbiInfo(stuAbi);
				stuAbi.setId(stuAbiInfo.getId());
				stuAbi.setCreateDate(stuAbiInfo.getCreateDate());
				String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
				stuAbi.setUpdateDate(updateDate);
				stuAbi.setFlag(stuAbiInfo.getFlag());
				DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
		        String  num = df.format(Double.parseDouble(scoreValArray[i])/Double.parseDouble(posAbiInfo.getScore()));//返回的是String类型
		        double conformity=Double.parseDouble(num);
		        stuAbi.setConformity(conformity);
		        stuAbiService.updateStu_Abi(stuAbi);
			}
			view = result(stuAbi);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
}
