package com.aqua.pingtai.jbpm.service.impl;


import javax.annotation.Resource;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.jbpm.dao.interfaces.TaskLogDao;
import com.aqua.pingtai.jbpm.entity.TaskLog;
import com.aqua.pingtai.jbpm.service.TaskLogService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("taskLogServiceImpl")
@Scope("singleton")
public class TaskLogServiceImpl implements TaskLogService {
	
	private static final Log log = LogFactory.getLog(TaskLogServiceImpl.class);
	
	@Resource(name="taskLogDaoImpl")
	private TaskLogDao taskLogDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	public TaskLogServiceImpl(){
		
	}

	public TaskLogDao getTaskLogDaoImpl() {
		return taskLogDaoImpl;
	}

	public void setTaskLogDaoImpl(TaskLogDao taskLogDaoImpl) {
		this.taskLogDaoImpl = taskLogDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	/**
	 * 分页任务列表
	 * @param filterString
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryTaskLog(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(TaskLog.class, filterString, queryResult);
	}	
}
