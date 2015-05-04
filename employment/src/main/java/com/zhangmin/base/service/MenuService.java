/**  
 * @Title: MenuService.java
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

import com.zhangmin.base.dao.MenuDao;
import com.zhangmin.base.entity.Menu;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: MenuService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-18
 */
@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	
	public void saveMenu(Menu menu){
		menu.setFlag("Y");
		String createDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		menu.setCreateDate(createDate);
		menu.setUpdateDate(createDate);
		menuDao.save(menu);
	}
	
	public Page getPagedMenuInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=menuDao.getHQLCount(counthql, params);
		List<?> dbList= menuDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Menu where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Menu findMenuById(String id){
		String hql = "from Menu where id = '" +id + "'and flag = 'y'";
		List<Menu> menuInfo = menuDao.find(hql);
		if(menuInfo.size() > 0){
			return menuInfo.get(0);
		}
		return null;
	}
	
	public void updateMenu(Menu menu){
		menuDao.update(menu);
	}
	
	public void deleteMenu(String menuId){
		Menu menu = findMenuById(menuId);
		if(menu != null){
			menuDao.realDel(menu);
		}
	}
	
	public List<Menu> menuList(Menu menu){
		if(menu.getMenuType()!=null && menu.getMenuType()!=""){
			int menuType=Integer.parseInt(menu.getMenuType())-1;
			if(menuType==0){
				menu.setMenuType(menuType+"");
			}else{
				menu.setMenuType("0"+menuType);
			}
			
		}
		String hql = "from Menu where flag ='y' and menuType='"+ menu.getMenuType()+"'";
		return menuDao.find(hql);
	}
	
	public List<Menu> menuListInfo(){
		String hql = "from Menu where flag='y'";
		return menuDao.find(hql);
	}
}
