package com.aqua.pingtai.jbpm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.dao.interfaces.TaskOperatorDao;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.entity.TaskOperator;
import com.aqua.pingtai.jbpm.service.TaskOperatorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("taskOperatorServiceImpl")
@Scope("singleton")
public class TaskOperatorServiceImpl implements TaskOperatorService {
	
	private static final Log log = LogFactory.getLog(TaskOperatorServiceImpl.class);
	
	@Resource(name="processDefinitionDaoImpl")
	private TaskOperatorDao taskOperatorDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	public TaskOperatorServiceImpl(){
		
	}

	public TaskOperatorDao getTaskOperatorDaoImpl() {
		return taskOperatorDaoImpl;
	}

	public void setTaskOperatorDaoImpl(TaskOperatorDao taskOperatorDaoImpl) {
		this.taskOperatorDaoImpl = taskOperatorDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	
	/**
	 * 删除任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteTaskOperator(TaskOperator taskOperator) {
		try {
			daoHibernateBase.excuteHQL("delete from TaskOperator where ids="+taskOperator.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个任务操作
	 * @param dictTypeids
	 * @return
	 */
	public TaskOperator getOneTaskOperator(Long taskOperatorIds) {
		TaskOperator taskOperator = null;
		try {
			taskOperator = daoHibernateBase.findOneEntity(TaskOperator.class, taskOperatorIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskOperator;
	}

	/**
	 * 保存任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveTaskOperator(TaskOperator taskOperator) {
		User createUser = Context.getCurrentUser();
		try {
			daoHibernateBase.saveOneEntity(taskOperator, createUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存多个任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveTaskOperatorList(List<TaskOperator> taskOperatorList, TaskAssignment taskAssignment) {
		User createUser = Context.getCurrentUser();
		try {
			taskAssignment = daoHibernateBase.findOneEntity(TaskAssignment.class, taskAssignment.getIds());
			for (TaskOperator taskOperator : taskOperatorList) {
				daoHibernateBase.saveOneEntity(taskOperator, createUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询任务操作列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryTaskOperator(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(TaskOperator.class, filterString, queryResult);
	}

	/**
	 * 更新任务操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateTaskOperator(TaskOperator taskOperator) {
		User updateUser = Context.getCurrentUser();
		try {
			daoHibernateBase.updateOneEntity(taskOperator, updateUser);
			log.info(updateUser.getUserName()+",更新任务操作,任务操作型:"+taskOperator.getOperator());
		} catch (Exception e) {
			log.error(updateUser.getUserName()+",更新任务操作异常!!!任务操作:"+taskOperator.getOperator());
			e.printStackTrace();
		}
	}

	/**
	 * HQL查询任务操作
	 * @param hql
	 * @return
	 */
	public List<TaskOperator> getTaskOperatorByHql(String hql){
		List<TaskOperator> taskOperatorList = null;
		try {
			taskOperatorList =  daoHibernateBase.findManyEntity(TaskOperator.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskOperatorList;
	}
	
	
}
