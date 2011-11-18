package com.aqua.pingtai.entity.bean.authority;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_operator")
public class Operator extends EntityBase {
	
	/**
	 * 操作名称
	 */
	@Column(nullable=true,name="names",length=25,columnDefinition="varchar(25)")
	private String names;
	
	/**
	 * 操作url
	 */
	@Column(nullable=true,name="url",length=200,columnDefinition="varchar(200)")
	private String url;
	
	/**
	 * 操作type
	 */
	@Column(nullable=true,name="type",length=25,columnDefinition="varchar(25)")
	private String type;
	
	/**
	 * 操作说明
	 */
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	private String description;
	
	/**
	 * 操作所拥有的角色
	 */
	@ManyToMany(mappedBy="operatorSet",cascade={CascadeType.REFRESH},
			fetch=FetchType.LAZY,targetEntity=Role.class)
	private Set<Role> roleSet = new HashSet<Role>();
	
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		Operator other = (Operator) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}
	*/
}
