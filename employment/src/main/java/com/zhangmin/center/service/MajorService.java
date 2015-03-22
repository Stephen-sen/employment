/**  
 * @Title: MajorService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.center.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.center.dao.MajorDao;
import com.zhangmin.center.entity.Major;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: MajorService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Service
public class MajorService {

	@Autowired
	private MajorDao majorDao;
	
	public void saveMajor(Major major){
		major.setFlag("Y");
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		major.setCreateDate(createDate);
		major.setUpdateDate(createDate);
		majorDao.save(major);
	}
	
	public Page getPagedMajorInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=majorDao.getHQLCount(counthql, params);
		List<?> dbList= majorDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Major where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Major findMajorById(String id){
		String hql = "from Major where id = '" +id + "'and flag = 'y'";
		List<Major> majorInfo = majorDao.find(hql);
		if(majorInfo.size() > 0){
			return majorInfo.get(0);
		}
		return null;
	}
	
	public void updateMajor(Major major){
		Major majorInfo = findMajorById(major.getId());
		major.setCreateDate(majorInfo.getCreateDate());
		String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		major.setUpdateDate(updateDate);
		major.setFlag(majorInfo.getFlag());
		majorDao.update(major);
	}
	
	public void deleteMajor(String majorId){
		Major major = findMajorById(majorId);
		if(major != null){
			majorDao.realDel(major);
		}
	}
	
	public List<Major> majorList(){
		String hql = "from Major where flag ='y'";
		return majorDao.find(hql);
	}
}
