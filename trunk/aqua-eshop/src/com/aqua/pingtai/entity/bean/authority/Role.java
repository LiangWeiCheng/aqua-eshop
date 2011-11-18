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
@Table(name="pingtai_role")
public class Role extends EntityBase {

	/**
	 * 角色名称
	 */
	@Column(nullable=true,name="names",length=25,columnDefinition="varchar(25)")
	private String names;
	
	/**
	 * 角色说明
	 */
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	private String description;
	
	/**
	 * 角色组
	 */
	@ManyToMany(mappedBy="roleSet",cascade={CascadeType.REFRESH},
			fetch=FetchType.LAZY,targetEntity=Group.class)
	private Set<Group> groupSet = new HashSet<Group>();
	
	/**
	 * 角色拥有的操作
	 */
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity=Operator.class)
	@JoinTable(name="pingtai_roleoperator",joinColumns={@JoinColumn(name="roleIds",referencedColumnName="ids",columnDefinition="bigint(20)",nullable=true)},
			inverseJoinColumns={@JoinColumn(name="operatorIds",referencedColumnName="ids",columnDefinition="bigint(20)",nullable=true)}
			)
	@OrderBy(" type asc ")
	private Set<Operator> operatorSet = new HashSet<Operator>();

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

	public Set<Group> getGroupSet() {
		return groupSet;
	}

	public void setGroupSet(Set<Group> groupSet) {
		this.groupSet = groupSet;
	}

	public Set<Operator> getOperatorSet() {
		return operatorSet;
	}

	public void setOperatorSet(Set<Operator> operatorSet) {
		this.operatorSet = operatorSet;
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
		Role other = (Role) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}
*/
}
