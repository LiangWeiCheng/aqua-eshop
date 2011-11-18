package com.aqua.pingtai.jbpm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

/**
 * 流程任务分派操作
 */
@Entity
@Table(name="pingtai_jbpm4_taskoperator")
@SuppressWarnings("serial")
public class TaskOperator extends EntityBase {

	@Column(nullable=true,name="processDefinitionId",length=20,columnDefinition="varchar(20)")
	private String processDefinitionId;//流程定义编号
	
	@Column(nullable=true,name="taskName",length=20,columnDefinition="varchar(20)")
	private String taskName;//任务名称
	
	@Column(nullable=true,name="operator",length=20,columnDefinition="varchar(35)")
	private String operator;//任务操作
	
	@ManyToOne(targetEntity=TaskAssignment.class,
		cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
		fetch=FetchType.EAGER,optional=true
	)
	@JoinColumn(name="taskAssignmentId",referencedColumnName="ids")
	private TaskAssignment taskAssignment;

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public TaskAssignment getTaskAssignment() {
		return taskAssignment;
	}

	public void setTaskAssignment(TaskAssignment taskAssignment) {
		this.taskAssignment = taskAssignment;
	}
	
	/*
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
		TaskOperation other = (TaskOperation) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}*/

	
}
