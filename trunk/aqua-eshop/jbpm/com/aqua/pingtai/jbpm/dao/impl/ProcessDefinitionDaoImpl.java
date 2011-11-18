package com.aqua.pingtai.jbpm.dao.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.jbpm.dao.interfaces.ProcessDefinitionDao;

@Repository
@Scope("singleton")
public class ProcessDefinitionDaoImpl implements ProcessDefinitionDao{
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}

	public DaoJdbcBase getDaoJdbcBase() {
		return daoJdbcBase;
	}

	public void setDaoJdbcBase(DaoJdbcBase daoJdbcBase) {
		this.daoJdbcBase = daoJdbcBase;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	
}