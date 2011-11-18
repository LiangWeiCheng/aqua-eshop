package com.aqua.pingtai.jbpm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.dao.interfaces.ProcessDefinitionDao;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;
import com.aqua.pingtai.jbpm.service.ProcessDefinitionService;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.RepositoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("processDefinitionServiceImpl")
@Scope("singleton")
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
	
	@Resource(name="processDefinitionDaoImpl")
	private ProcessDefinitionDao processDefinitionDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	public ProcessDefinitionServiceImpl(){
		
	}

	public ProcessDefinitionDao getProcessDefinitionDaoImpl() {
		return processDefinitionDaoImpl;
	}

	public void setProcessDefinitionDaoImpl(
			ProcessDefinitionDao processDefinitionDaoImpl) {
		this.processDefinitionDaoImpl = processDefinitionDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	/**
	 * 删除流程
	 * @param jbpmTemplate
	 * @param id
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeProcessDefinition(JbpmTemplate jbpmTemplate, String id){
		try {
			RepositoryService repositoryService = jbpmTemplate.getRepositoryService();
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).uniqueResult();
			repositoryService.deleteDeploymentCascade(pd.getDeploymentId());
			daoHibernateBase.excuteHQL("delete from TaskAssignment where processDefinitionId='"+id+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HQL查询任务分派
	 * @param hql
	 * @return
	 */
	public List<TaskAssignment> getTaskAssignmentByHQL(String hql){
		List<TaskAssignment> taskAssignmentList = null;
		try {
			taskAssignmentList =  daoHibernateBase.findManyEntity(TaskAssignment.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskAssignmentList;
	}
	
	/**
	 * 保存任务分派
	 * @param taskAssignment
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveTaskAssignmentList(String processDefinitionId, List<TaskAssignment> taskAssignmentList){
		User createUser = Context.getCurrentUser();
		try {
			for (TaskAssignment taskAssignment : taskAssignmentList) {
				if(null==taskAssignment.getIds()){
					daoHibernateBase.saveOneEntity(taskAssignment, createUser);
				}else{
					daoHibernateBase.updateOneEntity(taskAssignment, Context.getCurrentUser());
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 分页流程定义
	 * @param jbpmTemplate
	 * @param queryParameter
	 * @param queryResult
	 * @return
	 */
	public List<ProcessDefinition> splitPageQueryProcessDefinition(JbpmTemplate jbpmTemplate, QueryParameter queryParameter, QueryResult queryResult){
		List<ProcessDefinition> processDefinitionList = null;//保存数据
		try {
			processDefinitionList = findProcessDefinitionList( jbpmTemplate,  queryParameter,  queryResult);
			queryResult.setResultList(processDefinitionList);
			queryResult.compute();//计算
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processDefinitionList;
	}

	/**
	 * 获取流程定义集合
	 * @param jbpmTemplate
	 * @param queryParameter
	 * @param queryResult
	 * @return
	 * @throws Exception
	 */
	protected List<ProcessDefinition> findProcessDefinitionList(JbpmTemplate jbpmTemplate, QueryParameter queryParameter, QueryResult queryResult) throws Exception {
		RepositoryService repositoryService = jbpmTemplate.getProcessEngine().getRepositoryService();
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
															.orderDesc(ProcessDefinitionQuery.PROPERTY_DEPLOYMENT_TIMESTAMP);
		
		//是否按流程名称查询
		if(null!=queryParameter.getParameter("name") && !queryParameter.getParameter("name").equals("")){
			processDefinitionQuery.processDefinitionNameLike("%"+queryParameter.getParameter("name")+"%");
		}
		
		//获取所有流程
		List<ProcessDefinition> processDefinitionsAll = null;
		
		//得到最新流程,并处理分页
		if(null!=queryParameter.getParameter("version") && !queryParameter.getParameter("version").equals("") && 
				queryParameter.getParameter("version").equals("true")){
			//查询按版本排序
			processDefinitionsAll = processDefinitionQuery.orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION).list();
			//获取最新流程
			Map<String,ProcessDefinition> map = new LinkedHashMap<String,ProcessDefinition>();
			for (ProcessDefinition processDefinition : processDefinitionsAll) 
			{
				String key = processDefinition.getKey();
				ProcessDefinition definition = map.get(key);
				if ((definition == null) || (definition.getVersion() < processDefinition.getVersion())) {
					map.put(key, processDefinition);
				}
			}
			
			//map转list
			List<ProcessDefinition> processDefinitionsList = new ArrayList<ProcessDefinition>();
			for(Map.Entry<String, ProcessDefinition> entry : map.entrySet())    
			{    
				processDefinitionsList.add(entry.getValue());//key=entry.getKey() value=entry.getValue()
			}
			
			jbpmTemplate.getProcessEngine().close();
			
			//最新流程分页
			return listSplitPage(processDefinitionsList, queryResult);
		}
		
		//所有流程分页
		long countLong = processDefinitionQuery.orderDesc(ProcessDefinitionQuery.PROPERTY_DEPLOYMENT_TIMESTAMP).count();
		queryResult.setRecordCount(new Long(countLong));//记录总数
		
		int pageSize = queryResult.getMaxResult().intValue();//每页显示多少条
		int firstRecordIndex = queryResult.getFirstResult().intValue();//当前页的索引0 10 20 30 
		
		processDefinitionsAll = processDefinitionQuery.page(firstRecordIndex, pageSize).list();
		
		jbpmTemplate.getProcessEngine().close();
		
		return processDefinitionsAll;	
	}
	
	/**
	 * List分页函数
	 * @param processDefinitionsList
	 * @param queryResult
	 * @return
	 */
	public List<ProcessDefinition> listSplitPage(List<ProcessDefinition> processDefinitionsList, QueryResult queryResult){
		int count = processDefinitionsList.size();
		
		queryResult.setRecordCount(new Long((long)count));//记录总数
		int pageSize = queryResult.getMaxResult().intValue();//每页显示多少条
		
		int firstRecordIndex = queryResult.getFirstResult().intValue();//当前页的索引0 10 20 30 
		
		List<ProcessDefinition> list = null;
		if(count>firstRecordIndex){
			if((count-(firstRecordIndex))>pageSize){
				list = processDefinitionsList.subList(firstRecordIndex, firstRecordIndex+pageSize-1);
				list.add(processDefinitionsList.get(firstRecordIndex+pageSize-1));
				return list;
			}else{
				list = processDefinitionsList.subList(firstRecordIndex, count-1);
				list.add(processDefinitionsList.get(count-1));
				return list;
			}
		}else{
			return processDefinitionsList;
		}
	}
	
}
