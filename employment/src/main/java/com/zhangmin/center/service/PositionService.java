/**  
 * @Title: PositionService.java
 * @Package com.zhangmin.center.service
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
package com.zhangmin.center.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangmin.center.dao.PositionDao;
import com.zhangmin.center.entity.Position;
import com.zhaosen.base.Page;
import com.zhaosen.util.DateUtil;

/**
 * ClassName: PositionService 
 * @Description: TODO
 * @author 张敏
 * @date 2015-3-19
 */
@Service
public class PositionService {

	@Autowired
	private PositionDao positionDao;
	
	public void savePosition(Position position){
		String currentTime = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		position.setCreateDate(currentTime);
		position.setUpdateDate(currentTime);
		position.setFlag("Y");
		positionDao.save(position);
	}
	
	public Page getPagedPositionInfo(int pageNo,int pageSize) throws Exception{		
		List<Object> params = new LinkedList<Object>();
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		final int endIndex = pageSize;
		
		String hql = hqlCondition();
		String counthql="select count(*)" + hql;
		int totalSize=positionDao.getHQLCount(counthql, params);
		List<?> dbList= positionDao.getHQLPageList(hql, params, startIndex, endIndex);
		
		return new Page(startIndex, totalSize, pageSize, dbList);
	}
	
	public String  hqlCondition(){
		StringBuffer hql=new StringBuffer();
		hql.append("from Position where flag ='y'");
		hql.append("order by updateDate desc,");
		hql.append("createDate desc");
		return hql.toString();
	}
	
	public Position findPositionById(String id){
		String hql = "from Position where id = '" +id + "'and flag = 'y'";
		List<Position> positionInfo = positionDao.find(hql);
		if(positionInfo.size() > 0){
			return positionInfo.get(0);
		}
		return null;
	}
	
	public void updatePosition(Position position){
		Position positionInfo = findPositionById(position.getId());
		position.setCreateDate(positionInfo.getCreateDate());
		String updateDate = DateUtil.convertDateToString(new Date(), DateUtil.DATE_FORMAT_yyyyMMddhhmmss);
		position.setUpdateDate(updateDate);
		position.setFlag(positionInfo.getFlag());
		positionDao.update(position);
	}
	
	public void deletePosition(String positionId){
		Position position = findPositionById(positionId);
		if(position != null){
			positionDao.realDel(position);
		}
	}
	
	public List<Position> getPositionInfo(){
		String hql="from Position where flag = 'y'";
		return positionDao.find(hql);
	}
}
