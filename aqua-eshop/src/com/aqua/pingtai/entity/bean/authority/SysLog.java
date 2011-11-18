package com.aqua.pingtai.entity.bean.authority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_syslog")
public class SysLog extends EntityBase {
	
	@Column(name="titles",length=50,columnDefinition="varchar(50)",nullable=true)
	private String titles;//标题
	
	@Column(name="types",length=1,columnDefinition="char(20)",nullable=true)
	private String types;//日志类型
	
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	private String description;//日志描述

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
