/**  
 * @Title: RoleService.java
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

import com.zhangmin.base.dao.RoleDao;
import com.zhangmin.base.entity.Role;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: RoleService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public void saveRole(Role role){
		role.setFlag("Y");
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		role.setCreateDate(createDate);
		role.setUpdateDate(createDate);
		roleDao.save(role);
	}
	
	public Page getPagedRoleInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=roleDao.getHQLCount(counthql, params);
		List<?> dbList= roleDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Role where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Role findRoleById(String id){
		String hql = "from Role where id = '" +id + "'and flag = 'y'";
		List<Role> roleInfo = roleDao.find(hql);
		if(roleInfo.size() > 0){
			return roleInfo.get(0);
		}
		return null;
	}
	
	public void updateRole(Role role){
		roleDao.update(role);
	}
	
	public void deleteRole(String roleId){
		Role role = findRoleById(roleId);
		if(role != null){
			roleDao.realDel(role);
		}
	}
	
	public List<Role> roleListInfo(){
		String hql = "from Role where flag = 'y'";
		List<Role> roleList = roleDao.find(hql);
		if(roleList.size()>0){
			return roleList;
		}
		return null;
	}
}
