/**  
 * @Title: CompanyService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.center.dao.CompanyDao;
import com.zhangmin.center.entity.Company;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: CompanyService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Service
public class CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	public void saveCompany(Company company){
		String currentTime = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		company.setCreateDate(currentTime);
		company.setUpdateDate(currentTime);
		company.setFlag("Y");
		companyDao.save(company);
	}
	
	public Page getPagedCompanyInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=companyDao.getHQLCount(counthql, params);
		List<?> dbList= companyDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Company where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Company findCompanyById(String id){
		String hql = "from Company where id = '" +id + "'and flag = 'y'";
		List<Company> companyInfo = companyDao.find(hql);
		if(companyInfo.size() > 0){
			return companyInfo.get(0);
		}
		return null;
	}
	
	public void updateCompany(Company company){
		companyDao.update(company);
	}
	
	public void deleteCompany(String companyId){
		Company company = findCompanyById(companyId);
		if(company != null){
			companyDao.realDel(company);
		}
	}
	
	public List<Company> getCompanyInfo(){
		String hql="from Company where flag = 'y'";
		return companyDao.find(hql);
	}
	
	public List<Company> findCompany(String name){
		String hql = "from Company where flag ='y' and name='"+ name+"'";
		return companyDao.find(hql);
	}
}
