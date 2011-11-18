package com.aqua.pingtai.jbpm.service;

import java.util.List;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.entity.TaskOperator;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TaskOperatorService {
	
	/**
	 * 删除任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteTaskOperator(TaskOperator taskOperator);
	
	/**
	 * 获取一个任务操作
	 * @param dictTypeids
	 * @return
	 */
	public TaskOperator getOneTaskOperator(Long taskOperatorIds);
	
	/**
	 * 保存任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveTaskOperator(TaskOperator taskOperator);
	
	/**
	 * 保存多个任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveTaskOperatorList(List<TaskOperator> taskOperatorList, TaskAssignment taskAssignment);
	
	/**
	 * 查询任务操作列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryTaskOperator(String filterString, QueryResult queryResult) throws Exception ;
	
	/**
	 * 更新任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateTaskOperator(TaskOperator taskOperator);
	
	/**
	 * HQL查询任务操作
	 * @param hql
	 * @return
	 */
	public List<TaskOperator> getTaskOperatorByHql(String hql);

}