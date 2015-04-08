/**  
 * @Title: JobService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.center.dao.JobDao;
import com.zhangmin.center.dao.Pos_AbiDao;
import com.zhangmin.center.entity.Job;
import com.zhangmin.center.entity.Pos_Abi;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: JobService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Service
public class JobService {

	@Autowired
	private JobDao jobDao;
	@Autowired
	private Pos_AbiDao pos_AbiDao;
	
	public void saveJob(Job job){
		String currentTime = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		job.setCreateDate(currentTime);
		job.setUpdateDate(currentTime);
		job.setFlag("Y");
		jobDao.save(job);
	}
	
	public Page getPagedJobInfo(Job job,int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition(job);
		String counthql="select count(*)" + hql;
		int totalSize=jobDao.getHQLCount(counthql, params);
		List<?> dbList= jobDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(Job job){
		StringBuffer hql=new StringBuffer();
		hql.append("from Job where flag ='y'");
		if(job.getCompany()!=null){
			hql.append("and company.name like '" + job.getCompany().getName()+"%'");
		}
		if(null!=job.getPosition()){
			hql.append("and position.name like '" + job.getPosition().getName()+"%'");
		}
		if(!StringUtils.isEmpty(job.getSalary())){
			if(job.getSalary().equals("12")){
				hql.append("and salary >=1000 and salary <2000");
			}
			if(job.getSalary().equals("24")){
				hql.append("and salary >=2000 and salary <4000");
			}
			if(job.getSalary().equals("46")){
				hql.append("and salary >=4000 and salary <6000");
			}
			if(job.getSalary().equals("68")){
				hql.append("and salary >=6000 and salary <8000");
			}
			if(job.getSalary().equals("81")){
				hql.append("and salary >=8000 and salary <10000");
			}
			if(job.getSalary().equals("100")){
				hql.append("and salary >='10000'");
			}
		}
		if(!StringUtils.isEmpty(job.getStatus())){
			hql.append("and status like '" + job.getStatus()+"%'");
		}
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Job findJobById(String id){
		String hql = "from Job where id = '" +id + "'and flag = 'y'";
		List<Job> jobInfo = jobDao.find(hql);
		if(jobInfo.size() > 0){
			return jobInfo.get(0);
		}
		return null;
	}
	
	public Job findJobDetail(Job job){
		String hql = "from Job where id = '" +job.getId() + "'and flag = 'y'";
		List<Job> jobInfo = jobDao.find(hql);
		if(jobInfo.size() > 0){
			return jobInfo.get(0);
		}
		return null;
	}
	
	public void updateJob(Job job){
		jobDao.update(job);
	}
	
	public void deleteJob(String jobId){
		Job job = findJobById(jobId);
		if(job != null){
			jobDao.realDel(job);
		}
	}
	
	public void deleteUpDate(String companyId,String positoinId){
		String hql="from Job where flag = 'Y' and company = '"+companyId+"'" +
		" and position = '" +positoinId+"'";
		List<Job> jobList = jobDao.find(hql);
		for (Job job : jobList) {
			if(job != null){
				jobDao.realDel(job);
			}
		}
		String hql1="from Pos_Abi where flag = 'Y' and company = '"+companyId+"'" +
		" and position = '" +positoinId+"'";
		List<Pos_Abi> pos_AbiList = pos_AbiDao.find(hql1);
		for (Pos_Abi posAbi : pos_AbiList) {
			if(posAbi != null){
				pos_AbiDao.realDel(posAbi);
			}
		}
		}
}
