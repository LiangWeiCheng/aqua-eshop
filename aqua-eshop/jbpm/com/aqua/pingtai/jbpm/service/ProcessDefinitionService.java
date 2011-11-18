package com.aqua.pingtai.jbpm.service;

import java.util.List;

import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;

import org.jbpm.api.ProcessDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProcessDefinitionService {
	
	/**
	 * 删除流程
	 * @param jbpmTemplate
	 * @param id
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeProcessDefinition(JbpmTemplate jbpmTemplate, String id);
	
	/**
	 * HQL查询任务分派
	 * @param hql
	 * @return
	 */
	public List<TaskAssignment> getTaskAssignmentByHQL(String hql);
	
	/**
	 * 保存任务分派
	 * @param taskAssignment
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveTaskAssignmentList(String processDefinitionId, List<TaskAssignment> taskAssignmentList);
	
	/**
	 * 分页流程定义
	 * @param jbpmTemplate
	 * @param queryParameter
	 * @param queryResult
	 * @return
	 */
	public List<ProcessDefinition> splitPageQueryProcessDefinition(JbpmTemplate jbpmTemplate, QueryParameter queryParameter, QueryResult queryResult);

}