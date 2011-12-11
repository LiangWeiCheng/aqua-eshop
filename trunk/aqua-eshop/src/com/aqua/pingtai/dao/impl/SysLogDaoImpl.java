package com.aqua.pingtai.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.dao.interfaces.SysLogDao;
import com.aqua.pingtai.entity.bean.authority.SysLog;

@Repository
@Scope("singleton")
public class SysLogDaoImpl implements SysLogDao {
	
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

	@Override
	public void save(SysLog entity) {
		hibernateTemplate.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysLog> query(String queryString) {
		return (List<SysLog>) hibernateTemplate.find(queryString);
	}
	
	
}
