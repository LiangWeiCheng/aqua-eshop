package com.aqua.pingtai.entity.bean.authority;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;


@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_group")
public class Group extends EntityBase {
	
	/**
	 * 组名称
	 */
	@Column(nullable=true,name="names",length=25,columnDefinition="varchar(25)")
	private String names;
	
	/**
	 * 描述
	 */
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	private String description;
	
	/**
	 * 组拥有的角色
	 */
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="pingtai_rolegroup",joinColumns={@JoinColumn(name="groupIds",referencedColumnName="ids",columnDefinition="bigint(20)")},
			inverseJoinColumns={@JoinColumn(name="roleIds",referencedColumnName="ids",columnDefinition="bigint(20)")}
			)
	@OrderBy(" names asc ")
	private Set<Role> roleSet = new HashSet<Role>();
	
	/**
	 * 组用户
	 */
	@ManyToMany(mappedBy="groupSet",cascade={CascadeType.REFRESH},
			fetch=FetchType.LAZY,targetEntity=User.class)
	private Set<User> userSet = new HashSet<User>();

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
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
		Group other = (Group) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}
	*/
	
}
