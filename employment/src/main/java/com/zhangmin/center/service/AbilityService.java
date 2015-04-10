/**  
 * @Title: AbilityService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.center.dao.AbilityDao;
import com.zhangmin.center.entity.Ability;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;
/**
 * ClassName: AbilityService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Service
public class AbilityService {

	@Autowired
	private AbilityDao abilityDao;
	
	public void saveAbility(Ability ability){
		String currentTime = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		ability.setCreateDate(currentTime);
		ability.setUpdateDate(currentTime);
		ability.setFlag("Y");
		abilityDao.save(ability);
	}
	
	public Page getPagedAbilityInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=abilityDao.getHQLCount(counthql, params);
		List<?> dbList= abilityDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Ability where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Ability findAbilityById(String id){
		String hql = "from Ability where id = '" +id + "'and flag = 'y'";
		List<Ability> abilityInfo = abilityDao.find(hql);
		if(abilityInfo.size() > 0){
			return abilityInfo.get(0);
		}
		return null;
	}
	
	public void updateAbility(Ability ability){
		abilityDao.update(ability);
	}
	
	public void deleteAbility(String abilityId){
		Ability ability = findAbilityById(abilityId);
		if(ability != null){
			abilityDao.realDel(ability);
		}
	}
	
	public List<Ability> findPreAbility(){
		String hql = "from Ability where assessmentType= '01' and flag = 'y'";
		return abilityDao.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public Map findFistAbilityList(){
		List<Ability> firstAbilityList = findPreAbility();
		Map abilityMap = new HashMap();
		for (Ability ability : firstAbilityList) {
			String hql = "from Ability where preId= '"+ability.getId()+ "'and flag = 'y'";
			List<Ability> secondAbilityList =abilityDao.find(hql);
			abilityMap.put(ability.getName(), secondAbilityList);
		}
		return abilityMap;
		
	}
	
	public List<Ability> findAbility(String name){
		String hql = "from Ability where flag ='y' and name='"+ name+"'";
		return abilityDao.find(hql);
	}
}
