/**  
 * @Title: Stu_AbiService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.center.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.center.dao.Stu_AbiDao;
import com.zhangmin.center.entity.Ability;
import com.zhangmin.center.entity.Stu_Abi;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: Stu_AbiService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Service
public class Stu_AbiService {

	@Autowired
	private Stu_AbiDao stuAbiDao;
	
	public void saveStu_Abi(Stu_Abi stuAbi){
		stuAbi.setFlag("Y");
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		stuAbi.setCreateDate(createDate);
		stuAbi.setUpdateDate(createDate);
		stuAbiDao.save(stuAbi);
	}
	
	public Page getPagedStu_AbiInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=stuAbiDao.getHQLCount(counthql, params);
		List<?> dbList= stuAbiDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Stu_Abi where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Stu_Abi findStu_AbiById(String id){
		String hql = "from Stu_Abi where id = '" +id + "'and flag = 'y'";
		List<Stu_Abi> stuAbiInfo = stuAbiDao.find(hql);
		if(stuAbiInfo.size() > 0){
			return stuAbiInfo.get(0);
		}
		return null;
	}
	
	public Stu_Abi findStu_AbiInfo(Stu_Abi stuAbi){
		String hql = "from Stu_Abi where student = '" + stuAbi.getStudent().getId() +
		"' and pos_Abi= '"+stuAbi.getPos_Abi().getId()+ "'and flag = 'y'";
		List<Stu_Abi> stuAbiInfo = stuAbiDao.find(hql);
		if(stuAbiInfo.size() > 0){
			return stuAbiInfo.get(0);
		}
		return null;
	}
	
	public void updateStu_Abi(Stu_Abi stuAbi){
		stuAbiDao.update(stuAbi);
	}
	
	public void deleteStu_Abi(String stuAbiId){
		Stu_Abi stuAbi = findStu_AbiById(stuAbiId);
		if(stuAbi != null){
			stuAbiDao.realDel(stuAbi);
		}
	}
	public List<Stu_Abi> stuAbiList(Stu_Abi stuAbi){
		String hql = "from Stu_Abi where student='"+stuAbi.getStudent().getId()+ "' and job='"+stuAbi.getJob().getId()+"'";
		return stuAbiDao.find(hql);
	}
	
	public List<Stu_Abi> getStatus(String stuId,String jobId){
		String hql = "from Stu_Abi where student='"+stuId+ "' and job='"+jobId+"'";
		return stuAbiDao.find(hql);
	}
	/**
	 * 
	 * @Description: 就业能力分析结果查询
	 * @param @param job
	 * @param @return   
	 * @return Map  
	 * @author 张敏
	 * @date 2015-3-27
	 */
	@SuppressWarnings("unchecked")
	public Map getAbilityInfo(Stu_Abi stu_Abi){
		String hql="from Stu_Abi where flag = 'Y' and student = '"+stu_Abi.getStudent().getId()+"'" +
				" and job = '" +stu_Abi.getJob().getId()+"'";
		Map abilityMap = new HashMap();
		Set<Ability> idSet = new HashSet();
		List<Stu_Abi> stu_AbiList = stuAbiDao.find(hql);
		for (Stu_Abi stuAbi : stu_AbiList) {
				idSet.add(stuAbi.getAbility().getPreId());
		}
		Iterator<Ability> iter = idSet.iterator();
		while(iter.hasNext()){
			List<Stu_Abi> stuAbiList = new ArrayList<Stu_Abi>();
			Ability abilityVal = iter.next();
			for (Stu_Abi stuAbi : stu_AbiList) {
			String idStr = abilityVal.getId();
			String secondId = stuAbi.getAbility().getPreId().getId();
			if(secondId.equals(idStr)){
				stuAbiList.add(stuAbi);
			}
		}
			abilityMap.put(abilityVal.getName(), stuAbiList);
		}
		return abilityMap;
	}
}
