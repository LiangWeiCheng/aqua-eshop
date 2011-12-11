package com.aqua.pingtai.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.dao.interfaces.SysLogDao;
import com.aqua.pingtai.entity.bean.authority.SysLog;
import com.aqua.pingtai.service.SysLogService;

@Transactional
@Service("sysLogServiceImpl")
@Scope("singleton")
public class SysLogServiceImpl implements SysLogService {
	
	private static final Log log = LogFactory.getLog(SysLogServiceImpl.class);
	
	@Resource(name="sysLogDaoImpl")
	private SysLogDao sysLogDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;

	/**
	 * 日志分页
	 * @param filterString
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQuerySysLog(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(SysLog.class, filterString, queryResult);
	}
	
	public SysLogDao getSysLogDaoImpl() {
		return sysLogDaoImpl;
	}

	public void setSysLogDaoImpl(SysLogDao sysLogDaoImpl) {
		this.sysLogDaoImpl = sysLogDaoImpl;
	}

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

	@Override
	public void save(SysLog entity) {
		sysLogDaoImpl.save(entity);
	}
}
