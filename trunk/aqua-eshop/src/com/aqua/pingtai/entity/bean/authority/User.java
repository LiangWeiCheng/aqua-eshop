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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Searchable(root=true)
public class User extends EntityBase {

	@ManyToOne(targetEntity=Department.class,cascade={CascadeType.REFRESH},fetch=FetchType.EAGER,optional=true)//多对一
	@JoinColumn(name="departmentIds",columnDefinition="bigint(20)",nullable=true)//外键名称
	private Department department;//用户所在的部门
	
	@Column(nullable=true,name="userOperatorDataLevel",length=50,columnDefinition="varchar(50)")
	@SearchableProperty(name="userOperatorDataLevel",index=Index.TOKENIZED,store=Store.YES,boost=1)//store是否存储; boost:优先级
	private String userOperatorDataLevel;//用户操作数据级别
	
	@Column(nullable=true,name="userName",length=50,columnDefinition="varchar(50)")
	@SearchableProperty(name="userName",index=Index.TOKENIZED,store=Store.YES,boost=1)//store是否存储; boost:优先级
	private String userName;//用户名
	
	@Column(nullable=true,name="passWord",length=50,columnDefinition="varchar(200)")
	@SearchableProperty(name="passWord",index=Index.NO,store=Store.YES,boost=1)//index:是否索引; store是否存储; boost:优先级
	private String passWord;//用户密码
	
	@Column(name="userClass",nullable=true,length=25,columnDefinition="varchar(25)")
	@SearchableProperty(name="userClass",index=Index.NO,store=Store.YES,boost=1)
	private String userClass;//用户分类
	
	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER,optional=true,targetEntity=UserInfo.class)//optional=true,optional声明关系是否是必须存在的,即是否允许其中一端为null
	@JoinColumn(name="userInfoIds",columnDefinition="bigint(20)",unique=true,nullable=true)
	@SearchableComponent
	private UserInfo userInfo;//用户详细信息
	
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="pingtai_usergroup",joinColumns={@JoinColumn(name="userIds",referencedColumnName="ids",columnDefinition="bigint(20)")},
			inverseJoinColumns={@JoinColumn(name="groupIds",referencedColumnName="ids",columnDefinition="bigint(20)")}
			)
	@OrderBy("createdDate desc, names asc")
	private Set<Group> groupSet = new HashSet<Group>();//用户拥有的组
	
	//-开始----------下面是用户行级访问权限:多对多配置,一个人可以看多个人的数据,他自己的数据也可以被多个人看
	@ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER,targetEntity=User.class)
	@JoinTable(name="pingtai_userviewrowbyuser",joinColumns={@JoinColumn(name="userIds",referencedColumnName="ids",columnDefinition="bigint(20)")},
			inverseJoinColumns={@JoinColumn(name="targetUserIds",referencedColumnName="ids",columnDefinition="bigint(20)")}
			)
	@OrderBy(" userName asc")
	private Set<User> rowUserSet = new HashSet<User>();//此用户能查看的多个用户
	
	//@ManyToMany(mappedBy="rowUserSet",cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity=User.class)
	//private Set<User> targetUserSet = new HashSet<User>();//此用户的数据能被哪些人查看
	//-结束-------------------------------
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Set<Group> getGroupSet() {
		return groupSet;
	}

	public void setGroupSet(Set<Group> groupSet) {
		this.groupSet = groupSet;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public Set<User> getRowUserSet() {
		return rowUserSet;
	}

	public void setRowUserSet(Set<User> rowUserSet) {
		this.rowUserSet = rowUserSet;
	}

	/*
	public Set<User> getTargetUserSet() {
		return targetUserSet;
	}

	public void setTargetUserSet(Set<User> targetUserSet) {
		this.targetUserSet = targetUserSet;
	}
	*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		return result;
	}

	public String getUserOperatorDataLevel() {
		return userOperatorDataLevel;
	}

	public void setUserOperatorDataLevel(String userOperatorDataLevel) {
		this.userOperatorDataLevel = userOperatorDataLevel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}

}
