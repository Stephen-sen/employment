/**  
 * @Title: Pos_AbiService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
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

import com.zhangmin.center.dao.AbilityDao;
import com.zhangmin.center.dao.Pos_AbiDao;
import com.zhangmin.center.entity.Ability;
import com.zhangmin.center.entity.Job;
import com.zhangmin.center.entity.Pos_Abi;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: Pos_AbiService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Service
public class Pos_AbiService {

	@Autowired
	private Pos_AbiDao pos_AbiDao;
	@Autowired
	private AbilityDao abilityDao;
	
	public void savePos_Abi(Pos_Abi pos_Abi){
		String currentTime = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		pos_Abi.setCreateDate(currentTime);
		pos_Abi.setUpdateDate(currentTime);
		pos_Abi.setFlag("Y");
		pos_AbiDao.save(pos_Abi);
	}
	
	public Page getPagedPos_AbiInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=pos_AbiDao.getHQLCount(counthql, params);
		List<?> dbList= pos_AbiDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Pos_Abi where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Pos_Abi findPos_AbiById(String id){
		String hql = "from Pos_Abi where id = '" +id + "'and flag = 'y'";
		List<Pos_Abi> pos_AbiInfo = pos_AbiDao.find(hql);
		if(pos_AbiInfo.size() > 0){
			return pos_AbiInfo.get(0);
		}
		return null;
	}
	
	public void updatePos_Abi(Pos_Abi pos_Abi){
		Pos_Abi pos_AbiInfo = findPos_AbiById(pos_Abi.getId());
		pos_Abi.setCreateDate(pos_AbiInfo.getCreateDate());
		String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		pos_Abi.setUpdateDate(updateDate);
		pos_Abi.setFlag(pos_AbiInfo.getFlag());
		pos_AbiDao.update(pos_Abi);
	}
	
	public void deletePos_Abi(String pos_AbiId){
		Pos_Abi pos_Abi = findPos_AbiById(pos_AbiId);
		if(pos_Abi != null){
			pos_AbiDao.realDel(pos_Abi);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map getAbilityInfo(Job job){
		String hql="from Pos_Abi where flag = 'Y' and company = '"+job.getCompany().getId()+"'" +
				" and position = '" +job.getPosition().getId()+"'";
		Map abilityMap = new HashMap();
		Set<Ability> idSet = new HashSet();
		List<Pos_Abi> pos_AbiList = pos_AbiDao.find(hql);
		for (Pos_Abi posAbi : pos_AbiList) {
				idSet.add(posAbi.getAbility().getPreId());
		}
		Iterator<Ability> iter = idSet.iterator();
		while(iter.hasNext()){
			List<Ability> ablityList = new ArrayList<Ability>();
			Ability abilityVal = iter.next();
			for (Pos_Abi posAbi : pos_AbiList) {
			String idStr = abilityVal.getId();
			String secondId = posAbi.getAbility().getPreId().getId();
			if(secondId.equals(idStr)){
				ablityList.add(posAbi.getAbility());
			}
		}
			abilityMap.put(abilityVal.getName(), ablityList);
		}
		return abilityMap;
	}
}
