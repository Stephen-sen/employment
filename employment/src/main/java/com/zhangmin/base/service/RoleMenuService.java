/**  
 * @Title: RoleMenuService.java
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

import com.zhangmin.base.dao.RoleMenuDao;
import com.zhangmin.base.entity.RoleMenu;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: RoleMenuService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Service
public class RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	public void saveRoleMenu(RoleMenu roleMenu){
		roleMenu.setFlag("Y");
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		roleMenu.setCreateDate(createDate);
		roleMenu.setUpdateDate(createDate);
		roleMenuDao.save(roleMenu);
	}
	
	public Page getPagedRoleMenuInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=roleMenuDao.getHQLCount(counthql, params);
		List<?> dbList= roleMenuDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from RoleMenu where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public RoleMenu findRoleMenuById(String id){
		String hql = "from RoleMenu where id = '" +id + "'and flag = 'y'";
		List<RoleMenu> roleMenuInfo = roleMenuDao.find(hql);
		if(roleMenuInfo.size() > 0){
			return roleMenuInfo.get(0);
		}
		return null;
	}
	
	public void updateRoleMenu(RoleMenu roleMenu){
		roleMenuDao.update(roleMenu);
	}
	
	public void deleteRoleMenu(String roleMenuId){
		RoleMenu roleMenu = findRoleMenuById(roleMenuId);
		if(roleMenu != null){
			roleMenuDao.realDel(roleMenu);
		}
	}
	
	public List<RoleMenu> findRoleMenu(String roleId){
		String hql = "from RoleMenu where role='"+ roleId+"' and flag ='y'";
		List<RoleMenu> roleMenuList = roleMenuDao.find(hql);
		if(roleMenuList.size() > 0){
			return roleMenuList;
		}
		return null;
	}
	
	public void deleteAll(List<RoleMenu> roleMenuList){
		roleMenuDao.deleteAll(roleMenuList);
	}
}
