package com.aqua.pingtai.jbpm.task;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.entity.TaskLog;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.internal.log.Log;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 任务结束监听器
 */
@SuppressWarnings("serial")
public class TaskEnd implements EventListener{

	private static final Log log = Log.getLog(TaskEnd.class.getName());
	
	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	/**
	 * 更新相应的TaskLog记录
	 */
	public void notify(EventListenerExecution execution) throws Exception {
		log.debug("通知结束任务开始................");
		/*
		User user =  Context.getCurrentUser();//当前登录用户
		String processDefinitionId = execution.getProcessDefinitionId();//流程定义Id
		String execution_id = execution.getProcessInstance().getId();;//流程定义名称加上dbid,如:leave.570010
	
		TaskLog taskLog = null;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			for(String activityName:execution.findActiveActivityNames()){
				String taskName = (String) activityName;//当前任务位置
				Query queryTaskLog = session.createQuery("from TaskLog where processDefinitionId='"+processDefinitionId+"' and name='"+taskName+"' and processInstanceId='"+execution_id+"'");
				taskLog = (TaskLog) queryTaskLog.uniqueResult();
				taskLog.setTaskState("结束");//任务状态
				getDaoHibernateBase().saveOneEntity(taskLog, user );//更新
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		*/
		log.debug("通知结束任务结束................");
	}

	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}

	public DaoJdbcBase getDaoJdbcBase() {
		return daoJdbcBase;
	}

	public void setDaoJdbcBase(DaoJdbcBase daoJdbcBase) {
		this.daoJdbcBase = daoJdbcBase;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	
	
}
