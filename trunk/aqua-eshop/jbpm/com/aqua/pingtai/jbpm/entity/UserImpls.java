package com.aqua.pingtai.jbpm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;


@Entity
@Table(name="pingtai_jbpm4_userimpl")
@SuppressWarnings("serial")
public class UserImpls extends EntityBase implements org.jbpm.api.identity.User {

	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,optional=true,targetEntity=com.aqua.pingtai.entity.bean.authority.User.class)//optional=true,optional声明关系是否是必须存在的,即是否允许其中一端为null
	@JoinColumn(name="userIds",columnDefinition="bigint(20)",nullable=true)
	private com.aqua.pingtai.entity.bean.authority.User user;
	
	//用户id
	@Column(name="id",length=20,columnDefinition="varchar(20)",nullable=true,unique=true)
	private String id;
	
	//邮箱
	@Column(name="businessEmail",length=100,columnDefinition="varchar(100)",nullable=true)
	private String businessEmail;
	
	//名
	@Column(name="familyName",length=10,columnDefinition="varchar(10)",nullable=true)
	private String familyName;
	
	//姓
	@Column(name="givenName",length=10,columnDefinition="varchar(10)",nullable=true)
	private String givenName;
	
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity=com.aqua.pingtai.jbpm.entity.GroupImpls.class)
	@JoinTable(name="pingtai_jbpm4_usergroup",joinColumns={@JoinColumn(name="userIds",referencedColumnName="ids",columnDefinition="bigint(20)",nullable=true)},
			inverseJoinColumns={@JoinColumn(name="groupIds",referencedColumnName="ids",columnDefinition="bigint(20)",nullable=true)}
			)
	@OrderBy(" type asc ")
	private Set<com.aqua.pingtai.jbpm.entity.GroupImpls> groupImplSet = new HashSet<com.aqua.pingtai.jbpm.entity.GroupImpls>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public com.aqua.pingtai.entity.bean.authority.User getUser() {
		return user;
	}

	public void setUser(com.aqua.pingtai.entity.bean.authority.User user) {
		this.user = user;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public Set<GroupImpls> getGroupImplSet() {
		return groupImplSet;
	}

	public void setGroupImplSet(Set<GroupImpls> groupImplSet) {
		this.groupImplSet = groupImplSet;
	}

	

}
