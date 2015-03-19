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
import com.zhangmin.center.entity.Company;
import com.zhangmin.center.service.CompanyService;
import com.zhaosen.base.Page;

/**
 * ClassName: PostionController 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Controller
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;
	/**
	 * 
	 * @Description: 跳转到公司新增界面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/companyController/view")
	public ModelAndView view(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/company/addCompany");
		return view;
	}
	/**
	 * 
	 * @Description: 保存公司信息
	 * @param @param company
	 * @param @param pageNo
	 * @param @param request
	 * @param @return   
	 * @return ModelAndView  
	 * @author 张敏
	 * @date 2015-3-19
	 */
	@RequestMapping(value="/companyController/save")
	public ModelAndView save(Company company, Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			companyService.saveCompany(company);
			view = list(pageNo,request);
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
	@RequestMapping(value="/companyController/list")
	public ModelAndView list(Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			pageNo = pageNo == null?1:pageNo;
			int pageSize = this.getCookiesPageSize(request);
			Page pagedData = companyService.getPagedCompanyInfo(pageNo, pageSize);
			view.addObject("pagedData", pagedData );
			view.setViewName("/company/companyList");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value="/companyController/find")
	public ModelAndView find(Company company,Integer pageNo,HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try {
			Company companyInfo = companyService.findCompanyById(company.getId());
			view.addObject("companyInfo", companyInfo);
			view.setViewName("/company/updateCompany");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value = "/companyController/update")
	public ModelAndView update(Company company,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			companyService.updateCompany(company);
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
	
	@RequestMapping(value = "/companyController/delete")
	public ModelAndView delete(Company company,Integer pageNo,HttpServletRequest request) throws Exception{
		ModelAndView view =new ModelAndView();
		try{
			companyService.deleteMajor(company.getId());
			view= list(pageNo,request);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return view;
		}
}
