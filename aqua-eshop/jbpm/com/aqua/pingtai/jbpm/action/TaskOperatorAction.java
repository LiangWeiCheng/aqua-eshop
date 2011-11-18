package com.aqua.pingtai.jbpm.action;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.entity.TaskOperator;
import com.aqua.pingtai.jbpm.service.TaskOperatorService;

/**
 * 任务操作action:一个任务对应多个操作
 */
@SuppressWarnings({"serial"})
public class TaskOperatorAction extends BaseAction {
	
	@Resource(name="taskOperatorServiceImpl")
	private TaskOperatorService taskOperatorServiceImpl;
	
	private String processDefinitionId;
	private TaskAssignment taskAssignment;
	private List<TaskOperator> taskOperationList;
	

	/**
	 * 查看更新任务操作
	 * @return
	 */
	public String viewTaskOperator(){
		if(null!=taskAssignment && 0l!=taskAssignment.getIds()){
			taskOperationList = taskOperatorServiceImpl.getTaskOperatorByHql(" where taskAssignment.ids ="+taskAssignment.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/taskOperator/taskOperationList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 保存任务操作
	 * @return
	 */
	public String saveTaskOperator(){
		if(null!=taskOperationList && null!=taskAssignment && 0l!=taskAssignment.getIds()){
			taskOperatorServiceImpl.saveTaskOperatorList(taskOperationList, taskAssignment);
		}
		returnPageURL = "/pingTai/taskOperatorPingTaiAction!viewTaskOperator.action";
		return "redirect";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.jbpm.TaskOperationAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		return null;
	}

	public List<TaskOperator> getTaskOperationList() {
		return taskOperationList;
	}

	public void setTaskOperationList(List<TaskOperator> taskOperationList) {
		this.taskOperationList = taskOperationList;
	}

	public TaskOperatorService getTaskOperatorServiceImpl() {
		return taskOperatorServiceImpl;
	}

	public void setTaskOperatorServiceImpl(
			TaskOperatorService taskOperatorServiceImpl) {
		this.taskOperatorServiceImpl = taskOperatorServiceImpl;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public TaskAssignment getTaskAssignment() {
		return taskAssignment;
	}

	public void setTaskAssignment(TaskAssignment taskAssignment) {
		this.taskAssignment = taskAssignment;
	}
	
	
}
