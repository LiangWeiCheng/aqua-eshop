package com.aqua.pingtai.jbpm.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;
import com.aqua.pingtai.jbpm.service.TaskLogService;

@SuppressWarnings({"serial"})
public class TaskLogAction extends BaseAction {

	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	@Resource(name="taskLogServiceImpl")
	private TaskLogService taskLogServiceImpl;
	
	private String deploymentId = null;//部署id
	private String processDefinitionId = null;//流程定义id
	private String processInstanceId = null;//流程实例id
	private String imageResourceName = null;//流程图名称
	private ActivityCoordinates activityCoordinates;
	private List<TaskAssignment> taskAssignmentList = null;

	/**
	 * 任务列表
	 */
	public String taskLogList(){
		try {
			StringBuffer hqlSb = new StringBuffer(initQueryList(false));//过滤HQL
			User currentUser = Context.getCurrentUser();
			TaskService taskService = jbpmTemplate.getProcessEngine().getTaskService();
			List<Task> taskList = taskService.findPersonalTasks(String.valueOf(currentUser.getIds()));
			hqlSb.append(" and taskId in(");
			if(null!=taskList && taskList.size()>0){
				for (int i = 0; i < taskList.size()-1; i++) {
					String taskId = taskList.get(i).getId();
					hqlSb.append("'").append(taskId).append("'").append(",");
				}
				hqlSb.append("'").append(taskList.get(taskList.size()-1).getId()).append("'");
			}else{
				hqlSb.append(0);
			}
			hqlSb.append(") order by createdDate desc");

			taskLogServiceImpl.splitPageQueryTaskLog(hqlSb.toString(), queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/tasklog/taskLogList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 查看流程状态
	 * @return
	 */
	public String viewTaskState(){
		if(null!=processDefinitionId && !processDefinitionId.trim().equals("") && null!=processInstanceId && !processInstanceId.trim().equals("")){
			RepositoryService repositoryService = jbpmTemplate.getRepositoryService();
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl)processDefinitionQuery.processDefinitionId(processDefinitionId).uniqueResult();
			imageResourceName = processDefinitionImpl.getImageResourceName();//获取流程图
			deploymentId = processDefinitionImpl.getDeploymentId();
			
			ExecutionService executionService = jbpmTemplate.getExecutionService();
			ProcessInstance processInstance = executionService.findProcessInstanceById(processInstanceId);
			Set<String> activityNames = processInstance.findActiveActivityNames();
			activityCoordinates = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),activityNames.iterator().next());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/tasklog/taskState.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.TaskLogAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		String start = queryParameter.getParameter("start");
		String end = queryParameter.getParameter("end");
		
		StringBuffer sb = new StringBuffer();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(null!=start && !start.equals("")){
			try {
				Date date = DateFormat.getDateInstance().parse(start);
				sb.append(" createdDate>='").append(formatter.format(date)).append("'").append(" and ");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(null!=end && !end.equals("")){
			try {
				Date date = DateFormat.getDateInstance().parse(end);
				sb.append(" createdDate<='").append(formatter.format(date)).append("'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getImageResourceName() {
		return imageResourceName;
	}

	public void setImageResourceName(String imageResourceName) {
		this.imageResourceName = imageResourceName;
	}

	public ActivityCoordinates getActivityCoordinates() {
		return activityCoordinates;
	}

	public void setActivityCoordinates(ActivityCoordinates activityCoordinates) {
		this.activityCoordinates = activityCoordinates;
	}

	public List<TaskAssignment> getTaskAssignmentList() {
		return taskAssignmentList;
	}

	public void setTaskAssignmentList(List<TaskAssignment> taskAssignmentList) {
		this.taskAssignmentList = taskAssignmentList;
	}

}
