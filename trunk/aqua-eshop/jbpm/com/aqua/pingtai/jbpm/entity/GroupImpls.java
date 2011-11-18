package com.aqua.pingtai.jbpm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

@Entity
@Table(name="pingtai_jbpm4_groupimpl")
@SuppressWarnings("serial")
public class GroupImpls extends EntityBase implements org.jbpm.api.identity.Group {
	
	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,optional=true,targetEntity=com.aqua.pingtai.entity.bean.authority.Group.class)//optional=true,optional声明关系是否是必须存在的,即是否允许其中一端为null
	@JoinColumn(name="groupIds",columnDefinition="bigint(20)",nullable=true)
	private com.aqua.pingtai.entity.bean.authority.Group group;
	
	@Column(name="id",length=20,columnDefinition="varchar(20)",nullable=true,unique=true)
	private String id;
	
	@Column(name="name",length=25,columnDefinition="varchar(25)",nullable=true)
	private String name;
	
	@Column(name="type",length=25,columnDefinition="varchar(25)",nullable=true)
	private String type;
	
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	private String description;
	
	@ManyToMany(mappedBy="groupImplSet",cascade={CascadeType.REFRESH},
			fetch=FetchType.LAZY,targetEntity=com.aqua.pingtai.jbpm.entity.UserImpls.class)
	private Set<com.aqua.pingtai.jbpm.entity.UserImpls> userImplSet = new HashSet<com.aqua.pingtai.jbpm.entity.UserImpls>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public com.aqua.pingtai.entity.bean.authority.Group getGroup() {
		return group;
	}

	public void setGroup(com.aqua.pingtai.entity.bean.authority.Group group) {
		this.group = group;
	}

	public Set<UserImpls> getUserImplSet() {
		return userImplSet;
	}

	public void setUserImplSet(Set<UserImpls> userImplSet) {
		this.userImplSet = userImplSet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
