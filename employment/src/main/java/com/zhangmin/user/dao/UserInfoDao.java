/**  
 * @Title: UserInfoDao.java
 * @Package com.zhangmin.dao
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.user.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.zhangmin.base.dao.BaseDao;
import com.zhangmin.base.entity.UserInfo;

/**
 * ClassName: UserInfoDao 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
@Repository
public class UserInfoDao extends BaseDao{

	public UserInfo getUserInfo(UserInfo userInfo){
		if(null==userInfo){
			return null;
		}
		if(StringUtils.isEmpty(userInfo.getUserName())){
			return null;
		}
		if(StringUtils.isEmpty(userInfo.getPassword())){
			return null;
		}
		String hql = "from UserInfo u where u.flag='Y' and u.userName=?  and u.password=? ";
		Object[] objs=new Object[]{userInfo.getUserName(),userInfo.getPassword()};
		List<UserInfo> userList = (List<UserInfo>) this.getHibernateTemplate().find(hql, objs);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
	
	public void updateLoginDate(UserInfo user){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
	    session.clear();
		this.getHibernateTemplate().update(user);
	}
}
