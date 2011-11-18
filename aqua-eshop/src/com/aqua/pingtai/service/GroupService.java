package com.aqua.pingtai.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.Group;

@Transactional
public interface GroupService {
	
	/**
	 * 获取一个组
	 * @param dictTypeids
	 * @return
	 */
	public Group getOneGroup(Long groupIds);
	
	/**
	 * 查询组列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryGroup(String filterString, QueryResult queryResult) throws Exception;
	
	/**
	 * 保存组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveGroup(Group group);
	
	/**
	 * 更新组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateGroup(Group group);
	
	/**
	 * 删除组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteGroup(Group group);
	
	/**
	 * HQL查询group
	 * @param hql
	 * @return
	 */
	public List<Group> getGroupByHql(String hql);
	
}