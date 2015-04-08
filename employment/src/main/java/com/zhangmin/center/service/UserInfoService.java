/**  
 * @Title: UserInfoService.java
 * @Package com.zhangmin.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-10
 */
package com.zhangmin.center.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.center.dao.UserInfoDao;
import com.zhangmin.center.entity.UserInfo;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

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
	/**
	 * 
	 * @Description: 获取登录用户的信息
	 * @param @param user
	 * @param @return   
	 * @return UserInfo  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-15
	 */
	public UserInfo getUserInfo(UserInfo user){
		UserInfo userInfo = new UserInfo();
		userInfo = userInfoDao.getUserInfo(user);
		return userInfo;
	}
	
	/**
	 * 
	 * @Description: 用户登录后更新登录时间
	 * @param @param user   
	 * @return void  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-15
	 */
	public void updateLastLoginDate(UserInfo user){
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		user.setLastLoginDate(createDate);
		user.setUpdateDate(createDate);
		userInfoDao.updateLoginDate(user);
	}
	
	/**
	 * 
	 * @Description:保存登录用户信息，同时设置初始状态位，（0:审核未通过，1：审核通过）
	 * @param @param user   
	 * @return void  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-15
	 */
	public void saveUserInfo(UserInfo user){
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		user.setCreateDate(createDate);
		user.setStatus("0");
		user.setFlag("Y");
		userInfoDao.saveUserInfo(user);
	}
	public void deleteUser(String userId){
		userInfoDao.deleteUser(userId);
	}
	
	public UserInfo findUserById(String userId){
		return userInfoDao.findUserById(userId);
	}
	
	public void updateUser(UserInfo user){
		userInfoDao.update(user);
	}
	
	public void userStatusMan(String userId){
		UserInfo userInfo = userInfoDao.findUserById(userId);
		if(userInfo.getStatus().equals("1")){
			userInfo.setStatus("2");
		}
		else{
			userInfo.setStatus("1");
		}
		userInfoDao.userStatusMan(userId,userInfo.getStatus());
	}
	
	public List<UserInfo> findRegist(){
		return  userInfoDao.findRegist();
	}
	
	public void registPass(UserInfo user){
		userInfoDao.updateUser(user);
	}
	
	
	/**
	 * 功能：分页查询
	 * @param UserInfo 查询条件
	 * @param pageNo 页码
	 * @param pageSize 行数
	 * @return Page对象
	 * @throws Exception
	 */
	public Page getPagedUserInfo(UserInfo userInfo,int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition(userInfo);
		String counthql="select count(*)" + hql;
		int totalSize=userInfoDao.getHQLCount(counthql, params);
		List<?> dbList= userInfoDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(UserInfo userInfo){
		StringBuffer hql=new StringBuffer();
		hql.append("from UserInfo where flag ='y'");
		if(!StringUtils.isEmpty(userInfo.getUserName())){
			hql.append("and userName like '" + userInfo.getUserName()+"%'");
		}
		if(!StringUtils.isEmpty(userInfo.getCreateDate())){
			hql.append("and createDate like '" + userInfo.getCreateDate()+"%'");
		}
		if(!StringUtils.isEmpty(userInfo.getTel())){
			hql.append("and tel like '" + userInfo.getTel()+"%'");
		}
		if((userInfo.getMajor() != null) && userInfo.getMajor().getId()!=""){
			hql.append("and major like '" + userInfo.getMajor().getId()+"%'");
		}
		hql.append("and status = '1' or status = '2'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	/**
	 * 
	 * @Description: 查询注册用户
	 * @param @param userInfo
	 * @param @param pageNo
	 * @param @param pageSize
	 * @param @return
	 * @param @throws Exception   
	 * @return Page  
	 * @throws
	 * @author 张敏
	 * @date 2015-3-17
	 */
	public Page getPagedRegistList(UserInfo userInfo,int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition1(userInfo);
		String counthql="select count(*)" + hql;
		int totalSize=userInfoDao.getHQLCount(counthql, params);
		List<?> dbList= userInfoDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	public String  hqlCondition1(UserInfo userInfo){
		StringBuffer hql=new StringBuffer();
		hql.append("from UserInfo where flag ='y'");
		if(!StringUtils.isEmpty(userInfo.getUserName())){
			hql.append("and userName like '" + userInfo.getUserName()+"%'");
		}
		if(!StringUtils.isEmpty(userInfo.getCreateDate())){
			hql.append("and createDate like '" + userInfo.getCreateDate()+"%'");
		}
		if(!StringUtils.isEmpty(userInfo.getTel())){
			hql.append("and tel like '" + userInfo.getTel()+"%'");
		}
		if(!StringUtils.isEmpty(userInfo.getStatus())){
			hql.append("and status = '" + userInfo.getStatus()+"'");
		}
		hql.append("order by status asc,");
		hql.append("updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public void updatePasw(UserInfo userInfo){
		String hql = "update UserInfo set passWord=? where id=?";
		userInfoDao.bulkUpdate(hql, new Object[] {userInfo.getPassWord(), userInfo.getId() });
	}
	
	public List<UserInfo> findUserByName(String userName){
		String hql = "from UserInfo where userName = '"+userName+"' and flag='y'";
		return userInfoDao.find(hql);
	}
}
