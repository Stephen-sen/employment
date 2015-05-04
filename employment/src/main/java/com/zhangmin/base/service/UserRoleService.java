/**  
 * @Title: UserRoleService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
package com.zhangmin.base.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.base.dao.UserRoleDao;
import com.zhangmin.base.entity.UserRole;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: UserRoleService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Service
public class UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	public void saveUserRole(UserRole userRole){
		userRole.setFlag("Y");
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		userRole.setCreateDate(createDate);
		userRole.setUpdateDate(createDate);
		userRoleDao.save(userRole);
	}
	
	public Page getPagedUserRoleInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=userRoleDao.getHQLCount(counthql, params);
		List<?> dbList= userRoleDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from UserRole where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public UserRole findUserRoleById(String id){
		String hql = "from UserRole where id = '" +id + "'and flag = 'y'";
		List<UserRole> userRoleInfo = userRoleDao.find(hql);
		if(userRoleInfo.size() > 0){
			return userRoleInfo.get(0);
		}
		return null;
	}
	
	public void updateUserRole(UserRole userRole){
		userRoleDao.update(userRole);
	}
	
	public void deleteUserRole(String userRoleId){
		UserRole userRole = findUserRoleById(userRoleId);
		if(userRole != null){
			userRoleDao.realDel(userRole);
		}
	}
	
	public void deleteUserRoleByUserId(String userId){
		UserRole userRole = findUserRole(userId);
		if(userRole != null){
			userRoleDao.realDel(userRole);
		}
	}
	
	public UserRole findUserRole(String userId){
		String hql = "from UserRole where user='"+ userId+"' and flag ='y'";
		List<UserRole> userRoleList = userRoleDao.find(hql);
		if(userRoleList.size() > 0){
			return userRoleList.get(0);
		}
		return null;
	}
	
	public void deleteAll(List<UserRole> userRoleList){
		userRoleDao.deleteAll(userRoleList);
	}
}
