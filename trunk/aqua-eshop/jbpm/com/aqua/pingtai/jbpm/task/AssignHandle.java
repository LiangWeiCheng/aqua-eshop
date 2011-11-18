package com.aqua.pingtai.jbpm.task;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.entity.TaskLog;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.jbpm.internal.log.Log;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 实现任务分派
 */
@SuppressWarnings({"serial"})
public class AssignHandle implements AssignmentHandler{
	
	private static final Log log = Log.getLog(AssignHandle.class.getName());
	
	private JbpmTemplate jbpmTemplate;
	private DaoHibernateBase daoHibernateBase;
	private DaoJdbcBase daoJdbcBase;
	private HibernateTemplate hibernateTemplate;
	
	public JbpmTemplate getJbpmTemplate() {
		jbpmTemplate = (JbpmTemplate) Context.getSpringBean("jbpmTemplate");
		return jbpmTemplate;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		daoHibernateBase = (DaoHibernateBase) Context.getSpringBean("daoHibernateBase");
		return daoHibernateBase;
	}
	
	public DaoJdbcBase getDaoJdbcBase() {
		daoJdbcBase = (DaoJdbcBase) Context.getSpringBean("daoJdbcBase");
		return daoJdbcBase;
	}

	public HibernateTemplate getHibernateTemplate() {
		hibernateTemplate = (HibernateTemplate) Context.getSpringBean("hibernateTemplate");
		return hibernateTemplate;
	}
	
	public void assign(Assignable assignable, OpenExecution execution) throws Exception {
		//TaskImpl taskImpl = (TaskImpl)assignable;   
		//ExecutionImpl executionImpl = (ExecutionImpl)execution; 
		for(String activityName:execution.findActiveActivityNames()){
			String currentTaskName = (String) activityName;//当前任务位置
			String processDefinitionId = execution.getProcessDefinitionId();//流程定义id
					
			//流程任务分派
			TaskAssignment taskAssignment = getDaoHibernateBase().findOneEntity(TaskAssignment.class, " where processDefinitionId='"+processDefinitionId+"' and taskName='"+currentTaskName+"'");			
			
			creatTaskLog(assignable, execution, currentTaskName, taskAssignment);//创建taskLog
			
			String[] userIdsArr = {};
			String assignType = taskAssignment.getAssignType();
			
			if(null == assignType){
				throw new Exception("分派类型不能为空!");
			}else if(assignType.equals("bianLiang")){
				String varName = taskAssignment.getVariableName();
				String varValue = (String) execution.getVariable(varName);
				userIdsArr = varValue.split(",");
			}else if(assignType.equals("peiZhi")){
				String assignModeParamaterId = taskAssignment.getAssignModeParamaterId();
				userIdsArr = assignModeParamaterId.split(",");
			}

			//分派人员
			List<String> assigneeUserIds = Arrays.asList(userIdsArr);
			
			//1.分派到人
			if(assigneeUserIds.size()==1){
				assignable.setAssignee(assigneeUserIds.get(0));
			}
			
			//2.分派到候选者
			if(assigneeUserIds.size()>1){
				StringBuffer sbUserIds = new StringBuffer();
				for (int i = 0; i < assigneeUserIds.size()-1; i++) {
					String userIds = assigneeUserIds.get(i);
					sbUserIds.append(userIds).append(",");
				}
				sbUserIds.append(assigneeUserIds.size()-1);
				assignable.addCandidateUser(sbUserIds.toString());
			}
		}
	}
	
	/**
	 * 创建taskLog任务
	 * @param daoHibernateBase
	 * @param user
	 * @param assignable
	 * @param execution
	 * @param taskName
	 */
	public void creatTaskLog(Assignable assignable, OpenExecution execution, String taskName, TaskAssignment taskAssignment){
		User user = Context.getCurrentUser();//当前登录用户
		String processDefinitionId = execution.getProcessDefinitionId();//流程定义id
		String processInstanceId = execution.getProcessInstance().getId();
		String taskId = ((TaskImpl)assignable).getId();
		String processName = ((ExecutionImpl)execution.getProcessInstance()).getProcessDefinition().getName();
		
		TaskLog taskLog = new TaskLog();
		taskLog.setProcessDefinitionId(processDefinitionId);//流程定义id
		taskLog.setProcessName(processName);
		taskLog.setProcessInstanceId(processInstanceId);//流程实例id
		taskLog.setTaskId(taskId);
		taskLog.setTaskState("开始");//任务状态
		taskLog.setNames(taskName);//任务名称
		taskLog.setTaskDescription("");//描述
		taskLog.setTimeLimit(new Date());//最后时限
		taskLog.setTaskAssignment(taskAssignment);
		
		try {
			getDaoHibernateBase().saveOneEntity(taskLog, user);//保存
		} catch (Exception e) {
			log.error("任务日志taskLog创建错误!");
			e.printStackTrace();
		}
	}
	
}
