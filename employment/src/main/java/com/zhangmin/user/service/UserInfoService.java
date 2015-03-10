/**  
 * @Title: UserInfoService.java
 * @Package com.zhangmin.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.base.entity.UserInfo;
import com.zhangmin.constant.Util;
import com.zhangmin.user.dao.UserInfoDao;

/**
 * ClassName: UserInfoService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
@Service
public class UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
	public UserInfo getUserInfo(UserInfo user){
		UserInfo userInfo = new UserInfo();
		userInfo = userInfoDao.getUserInfo(user);
		return userInfo;
	}
	
	public void updateLastLoginDate(UserInfo user){
		String createDate = Util.convertDateToString(new Date(), Util.DATE_FORMAT_yyyyMMddhhmmss);
		user.setLastLoginDate(createDate);
		userInfoDao.updateLoginDate(user);
	}
}
