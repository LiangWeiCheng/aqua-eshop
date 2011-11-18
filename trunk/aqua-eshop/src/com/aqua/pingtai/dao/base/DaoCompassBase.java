package com.aqua.pingtai.dao.base;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.User;

import org.compass.core.Compass;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.spring.CompassDaoSupport;

public class DaoCompassBase extends CompassDaoSupport{
	
	/**
	 * 注入父类CompassDaoSupport属性compass
	 * @param compass
	 */
	@Resource
	public void setSessionFactory(Compass compass){
		super.setCompass(compass);
	}
	
	/**
	 * compass搜索查询:用户
	 * @param queryString
	 * @return
	 */
	public List<User> queryUser(String queryString, QueryResult queryResult){
		Compass compass = this.getCompassTemplate().getCompass();
		CompassSession session=compass.openSession();
		
		Long currentPage = queryResult.getCurrentPage();//当前页数
		Long maxResult = queryResult.getMaxResult();//抓取数
		Long firstResult = (currentPage-1l)==0l? 0l : (currentPage-1l)*maxResult;//开始处
		
		List<User> list = new ArrayList<User>();
		CompassHits hits = session.find(queryString);
		int start = firstResult.intValue();//开始
		int end = start + maxResult.intValue();//结束
		for (int i=start; i<end; i++) {
			User user = (User)hits.data(i);
			if(hits.highlighter(i).fragment("userName")!=null){
				user.setUserName(hits.highlighter(i).fragment("userName"));//高亮显示
			}
			list.add(user);
		}
		queryResult.setRecordCount(Long.valueOf(String.valueOf(hits.length())));//记录总数
		queryResult.setResultList(list);//记录集
		queryResult.compute();//计算
		
		return list;
	}
	
}
