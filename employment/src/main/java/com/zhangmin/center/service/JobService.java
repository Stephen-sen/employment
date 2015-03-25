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
	
	public Page getPagedJobInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=jobDao.getHQLCount(counthql, params);
		List<?> dbList= jobDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Job where flag ='y'");
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
