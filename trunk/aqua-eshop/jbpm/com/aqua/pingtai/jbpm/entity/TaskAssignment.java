package com.aqua.pingtai.jbpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

/**
 * task分派管理
 */
@Entity
@Table(name="pingtai_jbpm4_taskassignment")
@SuppressWarnings("serial")
public class TaskAssignment extends EntityBase {
	
	@Column(nullable=true,name="processDefinitionId",length=35,columnDefinition="varchar(35)")
	private String processDefinitionId;//流程定义->ID
	
	@Column(nullable=true,name="taskName",length=20,columnDefinition="varchar(20)")
	private String taskName;//流程定义任务->任务名称
	
	@Column(nullable=true,name="assignType",length=20,columnDefinition="varchar(20)")
	private String assignType;//分派类型
	
	@Column(nullable=true,name="assignModeParamaterId",length=500,columnDefinition="varchar(500)")
	private String assignModeParamaterId;//分派方式->参数查找链接返回id
	
	@Column(nullable=true,name="assignModeParamaterName",length=500,columnDefinition="varchar(500)")
	private String assignModeParamaterName;//分派方式->参数查找链接返回name
	
	@Column(nullable=true,name="variableName",length=20,columnDefinition="varchar(20)")
	private String variableName;//变量名
	
	@Column(nullable=true,name="countDay",columnDefinition="bigint(20)")
	private Long countDay;//时间限制天数
	
	@Column(nullable=true,name="processAction",length=200,columnDefinition="varchar(200)")
	private String processAction;//处理Action

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

	public String getAssignModeParamaterId() {
		return assignModeParamaterId;
	}

	public void setAssignModeParamaterId(String assignModeParamaterId) {
		this.assignModeParamaterId = assignModeParamaterId;
	}

	public String getAssignModeParamaterName() {
		return assignModeParamaterName;
	}

	public void setAssignModeParamaterName(String assignModeParamaterName) {
		this.assignModeParamaterName = assignModeParamaterName;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Long getCountDay() {
		return countDay;
	}

	public void setCountDay(Long countDay) {
		this.countDay = countDay;
	}

	public String getProcessAction() {
		return processAction;
	}

	public void setProcessAction(String processAction) {
		this.processAction = processAction;
	}

	public String getAssignType() {
		return assignType;
	}

	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}
	
	/*@Override
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
		TaskAssignment other = (TaskAssignment) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}
*/
	
}
