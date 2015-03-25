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
		String hql="from Pos_Abi where flag = 'Y' and company = '"+pos_Abi.getCompany().getId()+"'" +
		" and position = '" +pos_Abi.getPosition().getId()+"'";
		List<Pos_Abi> pos_AbiList = pos_AbiDao.find(hql);
		String[] scoreKeyArray = pos_Abi.getScoreKeyArray();
		String[] scoreValArray = pos_Abi.getScoreValArray();
 		if(null!=pos_AbiList){
			for (Pos_Abi posAbi : pos_AbiList) {
				Pos_Abi pos_AbiInfo=posAbi;
				for (int i=0; i<scoreKeyArray.length;i++) {
					if(scoreKeyArray[i].equals(pos_AbiInfo.getAbility().getId())){
						pos_AbiInfo.setScore(scoreValArray[i]);
						String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
						pos_AbiInfo.setUpdateDate(updateDate);
						pos_AbiDao.update(pos_AbiInfo);
					}
				}
			}
		}
	}
	
	public void deletePos_Abi(Job job){
		String hql="from Pos_Abi where flag = 'Y' and company = '"+job.getCompany().getId()+"'" +
		" and position = '" +job.getPosition().getId()+"'";
		List<Pos_Abi> pos_AbiList = pos_AbiDao.find(hql);
		if(pos_AbiList != null){
			for (Pos_Abi posAbi : pos_AbiList) {
				pos_AbiDao.realDel(posAbi);
			}
		}
	}
	/**
	 * 此方法用来获取发布职位时企业选择的考核素质项，用于下一步的分值设置
	 * 方法中先查询出该职位对应的各个素质项，然后查询出各个素质项的上级素质
	 * 然后分组，以map的方式保存。保存的形式是（key=上级素质的名字，value=本级职位素质对象），
	 * 这样的好处是在查看职位的详细信息时可以很方便的获取到职位对应的素质的分值。
	 * @Description: TODO
	 * @param @param job
	 * @param @return   
	 * @return Map  
	 * @author 张敏
	 * @date 2015-3-24
	 */
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
			List<Pos_Abi> posAbiList = new ArrayList<Pos_Abi>();
			Ability abilityVal = iter.next();
			for (Pos_Abi posAbi : pos_AbiList) {
			String idStr = abilityVal.getId();
			String secondId = posAbi.getAbility().getPreId().getId();
			if(secondId.equals(idStr)){
				posAbiList.add(posAbi);
			}
		}
			abilityMap.put(abilityVal.getName(), posAbiList);
		}
		return abilityMap;
	}
}
