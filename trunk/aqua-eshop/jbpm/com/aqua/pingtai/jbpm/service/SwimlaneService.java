package com.aqua.pingtai.jbpm.service;

import java.util.List;

import com.aqua.pingtai.jbpm.entity.Swimlane;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SwimlaneService {
	
	/**
	 * 更新泳道
	 * @param swimlaneList
	 * @param processDefinitionId
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void swimlaneUpdate(List<Swimlane> swimlaneList, String processDefinitionId);

	/**
	 * HQL查询泳道
	 * @param hql
	 * @return
	 */
	public List<Swimlane> getSwimlaneByHql(String hql);

}