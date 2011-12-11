package com.aqua.pingtai.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.aqua.pingtai.entity.bean.authority.User;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class EntityBase implements Serializable {
	
	/**
	 * 实体id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ids",length=20,unique=true,columnDefinition="bigint(20)")
	protected Long ids;

	/**
	 * 创建者
	 * (记录-->人:n-->1)
	 */
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY, optional=true, targetEntity=User.class)//多对一
	@JoinColumn(name="creatorUserIds",columnDefinition="bigint(20)",nullable=true,insertable=true,updatable=false)//外键名称
	protected User creator;

	/**
	 * 最后修改者
	 * (记录-->人:n-->1)
	 */
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY, optional=true, targetEntity=User.class)//多对一
	@JoinColumn(name="modifiedUserIds",columnDefinition="bigint(20)",nullable=true,insertable=false,updatable=true)//外键名称
	protected User modified;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="createdDate")
	protected Date createdDate;
	
	/**
	 * 最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="modifiedDate")
	protected Date modifiedDate;
	
	/**
	 * 是否有效
	 */
	@Column(name="valid",nullable=true,length=7,columnDefinition="varchar(7)")
	protected String valid = "youXiao";
	
	/**
	 * 版本控制
	 */
	@Version
	@Column(nullable=true,name="version",columnDefinition="bigint(20)")
	protected Long version;

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getModified() {
		return modified;
	}

	public void setModified(User modified) {
		this.modified = modified;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
	
}
