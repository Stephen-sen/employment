/**  
 * @Title: BaseDao.java
 * @Package com.zhangmin.base.dao
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.base.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * ClassName: BaseDao 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
public class BaseDao extends HibernateDaoSupport {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
	super.setSessionFactory(sessionFactory);  
	}
}
