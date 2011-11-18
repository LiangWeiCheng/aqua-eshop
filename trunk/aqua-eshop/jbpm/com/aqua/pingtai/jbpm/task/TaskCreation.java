package com.aqua.pingtai.jbpm.task;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.entity.TaskLog;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.internal.log.Log;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 任务创建监听器
 */
@SuppressWarnings("serial")
public class TaskCreation implements EventListener{

	private static final Log log = Log.getLog(TaskCreation.class.getName());
	
	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	public void notify(EventListenerExecution execution) throws Exception {
		
		log.debug("通知创建任务log开始。。。。。。。。。。。。。。");
		/*
		String execution_id = execution.getId();//流程定义名称加上dbid,如:leave.570010
		
		User user =  Context.getCurrentUser();
		String processDefinitionId = execution.getProcessDefinitionId();//流程定义Id
		
		ProcessInstance processInstance = execution.getProcessInstance();
		String processInstanceId = processInstance.getId();//流程实例Id
		
		String currentTaskName = null;//当前任务位置
		Set<String> activityNames = processInstance.findActiveActivityNames();
		for (Iterator<String> iterator = activityNames.iterator(); iterator.hasNext();) {
			currentTaskName = (String) iterator.next();
		}
		
		TaskLog taskLog = new TaskLog();
		taskLog.setProcessDefinitionId(processDefinitionId);//流程定义id
		taskLog.setProcessInstanceId(processInstanceId);//流程实例id
		taskLog.setTaskId(execution_id.replace(".", "#").split("#")[1]);//JBPM任务excutue id
		taskLog.setTaskState("start");//任务状态
		taskLog.setNames(currentTaskName);//任务名称
		taskLog.setTaskDescription("taskDescription");//描述
		taskLog.setTimeLimit(new Date());//最后时限
		
		try {
			getDaoHibernateBase().saveOneEntity(taskLog, user);//保存
		} catch (Exception e) {
			log.error("任务日志taskLog创建错误!");
			e.printStackTrace();
		}
		*/
		log.debug("通知创建任务log结束。。。。。。。。。。。。。。");
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
