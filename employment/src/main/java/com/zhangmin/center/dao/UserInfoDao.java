/**  
 * @Title: UserInfoDao.java
 * @Package com.zhangmin.dao
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.center.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.zhangmin.center.entity.UserInfo;
import com.zhaosen.base.BaseDao;

/**
 * ClassName: UserInfoDao 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
@Repository
public class UserInfoDao extends BaseDao<UserInfo>{

	public UserInfo getUserInfo(UserInfo userInfo){
		if(null==userInfo){
			return null;
		}
		if(StringUtils.isEmpty(userInfo.getUserName())){
			return null;
		}
		if(StringUtils.isEmpty(userInfo.getPassWord())){
			return null;
		}
		String hql = "from UserInfo u where u.flag='Y' and u.userName=?  and u.passWord=? ";
		Object[] objs=new Object[]{userInfo.getUserName(),userInfo.getPassWord()};
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
	
	public void saveUserInfo(UserInfo user){
		this.getHibernateTemplate().save(user);
	}
	
	public void deleteUser(String userId){
		String hql = "update UserInfo set flag='N',status = '0' where id = '"+userId +"'";
		this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public UserInfo findUserById(String userId){
		String hql = "from UserInfo u where u.id = ? ";
		Object[] objs=new Object[]{userId};
		List<UserInfo> userList = (List<UserInfo>) this.getHibernateTemplate().find(hql, objs);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
	
	public void updateUser(UserInfo user){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
	    session.clear();
		this.getHibernateTemplate().update(user);
	}
	
	public void userStatusMan(String userId,String status){
		String hql = "update UserInfo set status = '"+status+"' "+"where id = '"+userId +"'";
		this.getHibernateTemplate().getSessionFactory().openSession().createQuery(hql).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> findRegist(){
		String hql = "from UserInfo u where u.flag = 'y' order by u.status ";
		List<UserInfo> userList = (List<UserInfo>) this.getHibernateTemplate().find(hql);
		if(userList.size() > 0){
			return  userList;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> queryUser(String hql){
		
		List<UserInfo> userList = (List<UserInfo>) this.getHibernateTemplate().find(hql);
		if (userList.size() > 0) {
			return userList;
		}
		return null;
	}
}
