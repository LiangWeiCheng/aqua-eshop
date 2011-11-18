package com.aqua.pingtai.jbpm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aqua.pingtai.entity.base.EntityBase;


@Entity
@Table(name="pingtai_jbpm4_taskloghistory")
@SuppressWarnings("serial")
public class TaskLogHistory extends EntityBase {

	@Column(nullable=true,name="processDefinitionId",length=20,columnDefinition="varchar(20)")
	private String processDefinitionId;//流程定义id
	
	@Column(nullable=true,name="processName",length=20,columnDefinition="varchar(20)")
	private String processName;//流程名称
	
	@Column(nullable=true,name="processInstanceId",length=20,columnDefinition="varchar(20)")
	private String processInstanceId;//流程实例id
	
	@Column(nullable=true,name="taskId",length=20,columnDefinition="varchar(20)")
	private String taskId;//JBPM任务id
	
	@Column(nullable=true,name="taskState",length=20,columnDefinition="varchar(20)")
	private String taskState;//任务状态
	
	@Column(nullable=true,name="names",length=20,columnDefinition="varchar(20)")
	private String names;//任务名称
	
	@Column(nullable=true,name="taskDescription",length=200,columnDefinition="varchar(200)")
	private String taskDescription;//任务描述
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="timeLimit")
    private Date timeLimit;//最后时限
    
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Date getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskLogHistory other = (TaskLogHistory) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}

    
}
