package com.aqua.pingtai.jbpm.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aqua.pingtai.jbpm.task.AssignHandle;
import com.aqua.pingtai.jbpm.task.TaskCreation;
import com.aqua.pingtai.jbpm.task.TaskEnd;

import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.TaskService;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.api.history.HistoryComment;
import org.jbpm.api.model.Activity;
import org.jbpm.api.task.Participation;
import org.jbpm.api.task.Task;
import org.jbpm.jpdl.internal.activity.TaskActivity;
import org.jbpm.pvm.internal.client.ClientProcessInstance;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.EventImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.task.TaskDefinitionImpl;
import org.jbpm.pvm.internal.wire.Descriptor;
import org.jbpm.pvm.internal.wire.descriptor.ObjectDescriptor;
import org.jbpm.pvm.internal.wire.usercode.UserCodeEventListener;
import org.jbpm.pvm.internal.wire.usercode.UserCodeReference;

@SuppressWarnings("serial")
public class JbpmTemplate{
	
	private ProcessEngine processEngine;
	private RepositoryService repositoryService = null;
	private ExecutionService executionService = null;
	private TaskService taskService = null;
	private HistoryService historyService = null;
	private ManagementService managementService = null;
	
	public JbpmTemplate() {
		
	}
	
	public JbpmTemplate(ProcessEngine processEngine) {
		this.processEngine = processEngine;
		repositoryService = processEngine.getRepositoryService();
		executionService = processEngine.getExecutionService();
		taskService = processEngine.getTaskService();
		historyService = processEngine.getHistoryService();
		managementService = processEngine.getManagementService();
	}
	
	public HistoryComment addReplyComment(String commentId, String message) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.addReplyComment(commentId, message);
	}
	
	public HistoryComment addTaskComment(String taskId, String message) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.addTaskComment(taskId, message);
	}
	
	public void addTaskParticipatingGroup(String taskId, String groupId, String participationType) {
		TaskService taskService = processEngine.getTaskService();
		taskService.addTaskParticipatingGroup(taskId, groupId, participationType);
	}
	
	
	public void addTaskParticipatingUser(String taskId, String userId,
			String participationType) {
		TaskService taskService = processEngine.getTaskService();
		taskService.addTaskParticipatingUser(taskId, userId, participationType);
	}
	
	/**
	 * 分派任务给某人
	 * @param taskId 任务标识
	 * @param userId 用户标识
	 */
	public void assignTask(String taskId, String userId) {
		getTaskService().assignTask(taskId, userId);
    }
	
	/**
	 * 提交任务
	 * @param taskId
	 */
	public void completeTask(String taskId){
		TaskService taskService = processEngine.getTaskService();
		taskService.completeTask(taskId);
	}
	
	/**
	 * 提交任务
	 * @param taskId
	 * @param variables
	 */
	public void completeTask(String taskId,Map<String,Object> variables){
		TaskService taskService = processEngine.getTaskService();
		taskService.setVariables(taskId, variables);
		//taskService.completeTask(taskId, variables);
		taskService.completeTask(taskId);
	}
	
	/**
	 * 将任务流转到指定名字的流程中去
	 * @param taskId
	 * @param outcome
	 */
	public void completeTask(String taskId,String outcome){
		taskService = processEngine.getTaskService();
		taskService.completeTask(taskId, outcome);
	}
	
	/**
	 * 提交任务
	 * @param taskId
	 * @param outcome
	 * @param variables
	 */
	public void completeTask(String taskId, String outcome,
			Map<String, Object> variables) {
		TaskService taskService = processEngine.getTaskService();
		taskService.completeTask(taskId, outcome, variables);
	}
	
	/**
	 * 创建流程实例查询
	 * @return
	 */
	public ProcessInstanceQuery createProcessInstanceQuery() {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.createProcessInstanceQuery();
	}
	
	/**
	 * 创建任务查询
	 * @return
	 */
	public TaskQuery createTaskQuery() {
		TaskService taskService = processEngine.getTaskService();
		return taskService.createTaskQuery();
	}
	
	public void deleteComment(String commentId) {
		TaskService taskService = processEngine.getTaskService();
		taskService.deleteComment(commentId);
	}
	
	/**
	 * 彻底删除文件的部署 
	 * @param deploymentId	流程定义id
	 */
	public void deleteDeploymentCascade(String deploymentId) {
		repositoryService.deleteDeploymentCascade(deploymentId);
	}
	
	/**
	 * 删除流程实例
	 * @param processInstanceId
	 */
	public void deleteProcessInstance(String processInstanceId) {
		ExecutionService executionService = processEngine
        .getExecutionService();
		executionService.deleteProcessInstance(processInstanceId);
	}
	
	/**
	 * 删除流程实例---级联删除
	 * @param processInstanceId
	 */
	public void deleteProcessInstanceCascade(String processInstanceId) {
		ExecutionService executionService = processEngine
        .getExecutionService();
		executionService.deleteProcessInstanceCascade(processInstanceId);
	}
	 
	/**
	 * 删除任务
	 * @param taskId
	 */
	public void deleteTask(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		taskService.deleteTask(taskId);
	}
	
	/**
	 * 删除任务
	 * @param taskId
	 * @param reason
	 */
	public void deleteTask(String taskId, String reason) {
		TaskService taskService = processEngine.getTaskService();
		taskService.deleteTask(taskId, reason);
	}
	
	/**
	 * 删除任务---级联
	 * @param taskId
	 */
	public void deleteTaskCascade(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		taskService.deleteTaskCascade(taskId);
	}
	
	/**
	 * 部署流程到数据库
	 * @param resourceName
	 * @return
	 */
	public String deploy(String resourceName) {
		return repositoryService.createDeployment().addResourceFromClasspath(resourceName).deploy();
	}

	/**
	 * 结束流程实例
	 * @param processInstanceId
	 * @param state
	 */
	public void endProcessInstance(String processInstanceId, String state) {
		ExecutionService executionService = processEngine.getExecutionService();
		executionService.endProcessInstance(processInstanceId, state);
	}

	public <T> void execute(Command<T> command){
		this.processEngine.execute(command);
	}

	/**
	 * 根据流程实例id获取
	 * @param executionId
	 * @return
	 */
	public Execution findExecutionById(String executionId) {
		return executionService.findExecutionById(executionId);
	}

	/**
	 * 根据用户ID查询自己所在的组的任务
	 * @param userId
	 * @return
	 */
	public List<Task> findGroupTasks(String userId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.findGroupTasks(userId);
	}

	/**
	 * 根据用户ID查询自己的任务
	 * @param userId
	 * @return
	 */
	public List<Task> findPersonalTasks(String userId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.findPersonalTasks(userId);
	}

	/**
	 * 根据部署标识查找流程定义.	
	 * @param deploymentId	部署标识.
	 * @return	流程定义.
	 */
	public ProcessDefinition findProcessDefinitionByDeploymentId(String deploymentId) {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        										.deploymentId(deploymentId).uniqueResult();
		return pd;
	}

	/**
	 * 根据流程定义标识查找流程定义.	
	 * @param id	流程定义标识
	 * @return	流程定义
	 */
	public ProcessDefinition findProcessDefinitionById(String id) {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
        		.processDefinitionId(id).orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION).uniqueResult();
		return pd;
	}

	/**
	 * 查询流程实例
	 * @param processInstanceId 流程实例ID
	 * @return
	 */
	public ProcessInstance findProcessInstanceById(String processInstanceId) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService .findProcessInstanceById(processInstanceId);
	}

	/**
	 * 获取指定用户名字的任务
	 * @param userId
	 * @return	任务列表
	 */
	public List<Task> findUserTasks(String userId){
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.findPersonalTasks(userId);
		tasks.addAll(taskService.findGroupTasks(userId));
		return tasks;
	}

	public List<Activity> getAllTaskDef(String deploymentId){
		RepositoryService repositoryService = processEngine.getRepositoryService();
		List<Activity> taskActivities = new ArrayList<Activity>();
		List<? extends Activity> activities = ((ProcessDefinitionImpl)repositoryService.createProcessDefinitionQuery()
				.deploymentId(deploymentId).uniqueResult()).getActivities();
		for (Activity activity : activities) {
			if(activity.getType().equals("task")){
				taskActivities.add(activity);
			}
		}
		return taskActivities;
	}

	public ExecutionService getExecutionService() {
		return executionService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public ManagementService getManagementService() {
		return managementService;
	}
	
	public Set<String> getOutcomes(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.getOutcomes(taskId);
	}

    public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	/**
	 * 根据key获取流程实例
	 * @param key	(对应于数据库表jbpm4_execution中的KEY_字段)
	 * @return 返回查找到得流程实例，没有返回null
	 */
	public ProcessInstance getProcessInstance(String key) {
		return executionService.createProcessInstanceQuery().processInstanceKey(key).uniqueResult();
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public List<Task> getSubTasks(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.getSubTasks(taskId);
	}

	/**
	 * 根据任务id获取任务
	 * @param taskId
	 * @return
	 */
	public Task getTask(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.getTask(taskId);
		
	}

	public List<HistoryComment> getTaskComments(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.getTaskComments(taskId);
	}

	public List<Participation> getTaskParticipations(String taskId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.getTaskParticipations(taskId);
	}

	public TaskService getTaskService() {
		if(taskService==null){
			taskService=processEngine.getTaskService();
		}
		return taskService;
	}

	public Object getTaskVariables(String taskId, String variableName) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.getVariable(taskId, variableName);
	}

	public Object getVariable(String executionId, String variableName) {
		ExecutionService executionService = processEngine
        .getExecutionService();
		return executionService.getVariable(executionId, variableName);
	}

	/**
	 * 根据executionId获取指定的变量值
	 * @param executionId
	 * @param variableName
	 * @return
	 */
	public Object getVariableByexecutionId(String executionId,String variableName){
		return executionService.getVariable(executionId, variableName);
	}

	/**
	 * 根据任务id获取指定变量值
	 * @param taskId
	 * @param variableName
	 * @return
	 */
	public Object getVariableByTaskId(String taskId,String variableName){
		TaskService taskService = processEngine.getTaskService();
		return taskService.getVariable(taskId, variableName);
	}

	public Set<String> getVariableNames(String executionId) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.getVariableNames(executionId);
	}

	public Map<String, Object> getVariables(String executionId, Set<String> variableNames) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.getVariables(executionId, variableNames);
	}

	public Map<String, Object> getVariablesForProcess(String id) {
        ExecutionService executionService = processEngine.getExecutionService();
        Set<String> names = executionService.getVariableNames(id);

        return executionService.getVariables(id, names);
    }

	public Map<String, Object> getVariablesForTask(String id) {
        TaskService taskService = processEngine.getTaskService();
        Set<String> names = taskService.getVariableNames(id);
        return taskService.getVariables(id, names);
    }

	public Task newTask() {
		TaskService taskService = processEngine.getTaskService();
		return taskService.newTask();
	}

	public Task newTask(String parentTaskId) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.newTask(parentTaskId);
	}

	/**
	 * 根据流程定义标识删除流程定义.
	 * @param id 流程定义标识
	 */
	public void removeProcessDefinitionById(String id) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                                                .processDefinitionId(id).uniqueResult();
        repositoryService.deleteDeploymentCascade(pd.getDeploymentId());
    }

	public void removeTaskParticipatingGroup(String taskId, String groupId, String participationType) {
		TaskService taskService = processEngine.getTaskService();
		taskService.removeTaskParticipatingGroup(taskId, groupId, participationType);
	}

	public void removeTaskParticipatingUser(String taskId, String userId, String participationType) {
		TaskService taskService = processEngine.getTaskService();
		taskService.removeTaskParticipatingUser(taskId, userId, participationType);
	}

	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, ?> variables) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceByKey(processDefinitionKey, variables);
	}

	public String saveTask(Task task) {
		TaskService taskService = processEngine.getTaskService();
		return taskService.saveTask(task);
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
		repositoryService = processEngine.getRepositoryService();
		executionService = processEngine.getExecutionService();
		taskService = processEngine.getTaskService();
		historyService = processEngine.getHistoryService();
		managementService = processEngine.getManagementService();
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setTaskVariables(String taskId, Map<String, Object> variables) {
		TaskService taskService = processEngine.getTaskService();
		taskService.setVariables(taskId, variables);
		
	}

	public void setVariable(String executionId, String name, Object value) {
		ExecutionService executionService = processEngine.getExecutionService();
		executionService.setVariable(executionId, name, value);
	}

	public void setVariables(String executionId, Map<String, ?> variables) {
		ExecutionService executionService = processEngine.getExecutionService();
		executionService.setVariables(executionId, variables);
	}

	public ProcessInstance signalExecutionById(String executionId) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.signalExecutionById(executionId);
	}

	public ProcessInstance signalExecutionById(String executionId, Map<String, ?> parameters) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.signalExecutionById(executionId, parameters);
	}

	public ProcessInstance signalExecutionById(String executionId, String signalName) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.signalExecutionById(executionId, signalName);
	}

	public ProcessInstance signalExecutionById(String executionId, String signalName, Map<String, ?> parameters) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.signalExecutionById(executionId, signalName, parameters);
	}

	/**
	 * 启动一个流程
	 * @param processDefinitionId
	 * @return
	 */
	public ProcessInstance startProcessById(final String processDefinitionId) {
		ProcessInstance pi = (ProcessInstance) processEngine.execute(new Command<ProcessInstance>() {
		    public ProcessInstance execute(Environment env) {
		    	//设置监听
				ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION).list().get(0);
				List<? extends Activity> activityList = ((ProcessDefinitionImpl)pd).getActivities();
				for (Activity activity : activityList) {
					if(activity.getType().equals("task")){
						//设置开始事件监听
						ActivityImpl activityImpl = ((ActivityImpl)activity);
						EventImpl startEventImpl = activityImpl.createEvent("start");
						org.jbpm.pvm.internal.model.EventListenerReference startEvent = startEventImpl.createEventListenerReference();
						UserCodeEventListener startEventListener = new UserCodeEventListener();
						UserCodeReference startEventListenerReference = new UserCodeReference();
						ObjectDescriptor startDescriptor = new ObjectDescriptor(TaskCreation.class.getName());
						startEventListenerReference.setDescriptor(startDescriptor);
						startEventListener.setEventListenerReference(startEventListenerReference);
						startEvent.setEventListener(startEventListener);
						
						//设置结束事件监听
						EventImpl endEventImpl = activityImpl.createEvent("end");
						org.jbpm.pvm.internal.model.EventListenerReference endEvent = endEventImpl.createEventListenerReference();
						UserCodeEventListener endEventListener = new UserCodeEventListener();
						UserCodeReference endEventListenerReference = new UserCodeReference();
						ObjectDescriptor endDescriptor = new ObjectDescriptor(TaskEnd.class.getName());
						endEventListenerReference.setDescriptor(endDescriptor);
						endEventListener.setEventListenerReference(endEventListenerReference);
						endEvent.setEventListener(endEventListener);
						
						//设置分派实现类
						TaskActivity taskActivity = (TaskActivity)activityImpl.getActivityBehaviour();
						TaskDefinitionImpl taskDefinition = taskActivity.getTaskDefinition();
						UserCodeReference userHandler = new UserCodeReference();
						Descriptor descriptor = new ObjectDescriptor(AssignHandle.class.getName());
						userHandler.setDescriptor(descriptor);
						taskDefinition.setAssignmentHandlerReference(userHandler);
					}
				}
				return ((ProcessDefinitionImpl)pd).startProcessInstance();
		    }
		});
		return pi;		
	}

	/**
	 * 创建一个新的流程实例
	 * @param processDefinitionKey	(process.jpdl.xml中process标签的key)
	 * @return 流程实例
	 */
	public ProcessInstance startProcessByKey(final String processDefinitionKey) {
		ProcessInstance pi = (ProcessInstance) processEngine.execute(new Command<ProcessInstance>() {
		    public ProcessInstance execute(Environment env) {
		    	//设置监听
				ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION).list().get(0);
				List<? extends Activity> activityList = ((ProcessDefinitionImpl)pd).getActivities();
				for (Activity activity : activityList) {
					if(activity.getType().equals("task")){
						//设置开始事件监听
						ActivityImpl activityImpl = ((ActivityImpl)activity);
						EventImpl startEventImpl = activityImpl.createEvent("start");
						org.jbpm.pvm.internal.model.EventListenerReference startEvent = startEventImpl.createEventListenerReference();
						UserCodeEventListener startEventListener = new UserCodeEventListener();
						UserCodeReference startEventListenerReference = new UserCodeReference();
						ObjectDescriptor startDescriptor = new ObjectDescriptor(TaskCreation.class.getName());
						startEventListenerReference.setDescriptor(startDescriptor);
						startEventListener.setEventListenerReference(startEventListenerReference);
						startEvent.setEventListener(startEventListener);
						
						//设置结束事件监听
						EventImpl endEventImpl = activityImpl.createEvent("end");
						org.jbpm.pvm.internal.model.EventListenerReference endEvent = endEventImpl.createEventListenerReference();
						UserCodeEventListener endEventListener = new UserCodeEventListener();
						UserCodeReference endEventListenerReference = new UserCodeReference();
						ObjectDescriptor endDescriptor = new ObjectDescriptor(TaskEnd.class.getName());
						endEventListenerReference.setDescriptor(endDescriptor);
						endEventListener.setEventListenerReference(endEventListenerReference);
						endEvent.setEventListener(endEventListener);
						
						//设置分派实现类
						TaskActivity taskActivity = (TaskActivity)activityImpl.getActivityBehaviour();
						TaskDefinitionImpl taskDefinition = taskActivity.getTaskDefinition();
						UserCodeReference userHandler = new UserCodeReference();
						Descriptor descriptor = new ObjectDescriptor(AssignHandle.class.getName());
						userHandler.setDescriptor(descriptor);
						taskDefinition.setAssignmentHandlerReference(userHandler);
					}
				}
				return ((ProcessDefinitionImpl)pd).startProcessInstance();
		    }
		});
		
//		ProcessInstance pi = executionService.startProcessInstanceByKey(processDefinitionKey);
		return pi;

	}

	/**
	 * 启动流程
	 * @param id 流程定义key
	 * @param variables 启动流程时所需的变量
	 */
	public ProcessInstance startProcessByKey(final String processDefinitionKey, final Map<String, Object> variables) {
        ProcessInstance pi = (ProcessInstance) processEngine.execute(new Command<ProcessInstance>() {
		    public ProcessInstance execute(Environment env) {
		    	//设置监听
				ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION).list().get(0);
				List<? extends Activity> activityList = ((ProcessDefinitionImpl)pd).getActivities();
				for (Activity activity : activityList) {
					if(activity.getType().equals("task")){
						//设置开始事件监听
						ActivityImpl activityImpl = ((ActivityImpl)activity);
						EventImpl startEventImpl = activityImpl.createEvent("start");
						org.jbpm.pvm.internal.model.EventListenerReference startEvent = startEventImpl.createEventListenerReference();
						UserCodeEventListener startEventListener = new UserCodeEventListener();
						UserCodeReference startEventListenerReference = new UserCodeReference();
						ObjectDescriptor startDescriptor = new ObjectDescriptor(TaskCreation.class.getName());
						startEventListenerReference.setDescriptor(startDescriptor);
						startEventListener.setEventListenerReference(startEventListenerReference);
						startEvent.setEventListener(startEventListener);
						
						//设置结束事件监听
						EventImpl endEventImpl = activityImpl.createEvent("end");
						org.jbpm.pvm.internal.model.EventListenerReference endEvent = endEventImpl.createEventListenerReference();
						UserCodeEventListener endEventListener = new UserCodeEventListener();
						UserCodeReference endEventListenerReference = new UserCodeReference();
						ObjectDescriptor endDescriptor = new ObjectDescriptor(TaskEnd.class.getName());
						endEventListenerReference.setDescriptor(endDescriptor);
						endEventListener.setEventListenerReference(endEventListenerReference);
						endEvent.setEventListener(endEventListener);
						
						//设置分派实现类
						TaskActivity taskActivity = (TaskActivity)activityImpl.getActivityBehaviour();
						TaskDefinitionImpl taskDefinition = taskActivity.getTaskDefinition();
						UserCodeReference userHandler = new UserCodeReference();
						Descriptor descriptor = new ObjectDescriptor(AssignHandle.class.getName());
						userHandler.setDescriptor(descriptor);
						taskDefinition.setAssignmentHandlerReference(userHandler);
					}
				}
				ClientProcessInstance cpi = ((ProcessDefinitionImpl)pd).createProcessInstance();
				cpi.setVariables(variables);
				cpi.start();
				return cpi;
		    }
		});
        
        return pi;
    }

	public ProcessInstance startProcessInstanceById(String processDefinitionId) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceById(processDefinitionId);
	}

	public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> variables) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceById(processDefinitionId, variables);
	}

	public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> variables, String processInstanceKey) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceById(processDefinitionId, variables, processInstanceKey);
	}

	public ProcessInstance startProcessInstanceById(String processDefinitionId, String processInstanceKey) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceById(processDefinitionId, processInstanceKey);
	}

	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceByKey(processDefinitionKey);
	}
	
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, ?> variables, String processInstanceKey) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceByKey(processDefinitionKey, variables, processInstanceKey);
	}

	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String processInstanceKey) {
		ExecutionService executionService = processEngine.getExecutionService();
		return executionService.startProcessInstanceByKey(processDefinitionKey, processInstanceKey);
	}

	public void takeTask(String taskId, String userId) {
		TaskService taskService = processEngine.getTaskService();
		taskService.takeTask(taskId, userId);
	}
}
