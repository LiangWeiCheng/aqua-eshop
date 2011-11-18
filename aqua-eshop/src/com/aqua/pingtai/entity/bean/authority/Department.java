package com.aqua.pingtai.entity.bean.authority;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_department")
public class Department extends EntityBase{

	@Column(nullable=true,name="names",length=25,columnDefinition="varchar(25)")
	private String names;//部门名称
	
	@Column(nullable=true,name="departmentLevel",length=20,columnDefinition="bigint(20)")
	private Long departmentLevel;//级别
	
	@Column(nullable=true,name="url",length=100,columnDefinition="varchar(100)")
	private String url;//URL
	
	@Column(nullable=true,name="orderIds",length=20,columnDefinition="bigint(20)")
	private Long orderIds;//排序
	
	@Column(nullable=true,name="images",length=50,columnDefinition="varchar(50)")
	private String images;//显示图片
	
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	private String description;//描述
	
	@ManyToOne(targetEntity=Department.class,cascade={CascadeType.REFRESH},fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="parentDepartmentIds",columnDefinition="bigint(20)",nullable=true)
	private Department parentDepartment;//上级部门
	
	@OneToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER,
			targetEntity=Department.class,mappedBy="parentDepartment")
	@OrderBy(" orderIds asc ")
	private Set<Department> departmentSet = new HashSet<Department>();//下级部门
	
	@ManyToOne(targetEntity=User.class,cascade={CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="principalUserIds",columnDefinition="bigint(20)",nullable=true)
	private User principal;//负责人
	
	@OneToMany(cascade={CascadeType.REFRESH},
			fetch=FetchType.LAZY,targetEntity=User.class,mappedBy="department")
	private Set<User> userSet = new HashSet<User>();//部门拥有的用户
	
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

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	public User getPrincipal() {
		return principal;
	}

	public void setPrincipal(User principal) {
		this.principal = principal;
	}

	public Long getDepartmentLevel() {
		return departmentLevel;
	}

	public void setDepartmentLevel(Long departmentLevel) {
		this.departmentLevel = departmentLevel;
	}

	public Long getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Long orderIds) {
		this.orderIds = orderIds;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Department getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(Department parentDepartment) {
		this.parentDepartment = parentDepartment;
	}

	public Set<Department> getDepartmentSet() {
		return departmentSet;
	}

	public void setDepartmentSet(Set<Department> departmentSet) {
		this.departmentSet = departmentSet;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		Department other = (Department) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}
	*/
}
