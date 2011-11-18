package com.aqua.pingtai.jbpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

//swimlane分派管理
@Entity
@Table(name="pingtai_jbpm4_swimlane")
@SuppressWarnings("serial")
public class Swimlane extends EntityBase {
	
	@Column(nullable=true,name="processDefinitionId",length=35,columnDefinition="varchar(35)")
	private String processDefinitionId;//流程定义ID
	
	@Column(nullable=true,name="swimlaneName",length=20,columnDefinition="varchar(20)")
	private String swimlaneName;//泳道名称
	
	@Column(nullable=true,name="userIds",length=500,columnDefinition="varchar(500)")
	private String userIds;
	
	@Column(nullable=true,name="userNames",length=500,columnDefinition="varchar(500)")
	private String userNames;
	
	@Column(nullable=true,name="groupIds",length=500,columnDefinition="varchar(500)")
	private String groupIds;
	
	@Column(nullable=true,name="groupNames",length=500,columnDefinition="varchar(500)")
	private String groupNames;
	
	@Column(nullable=true,name="variableName",length=20,columnDefinition="varchar(20)")
	private String variableName;//变量名

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getSwimlaneName() {
		return swimlaneName;
	}

	public void setSwimlaneName(String swimlaneName) {
		this.swimlaneName = swimlaneName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public String getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	
}
