package com.aqua.pingtai.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.dao.interfaces.OperatorDao;
import com.aqua.pingtai.entity.bean.authority.Operator;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class OperatorDaoImpl implements OperatorDao {
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	public OperatorDaoImpl(){
		
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

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * SQL查询Operator
	 * @param SQL
	 * @return
	 */
	public List<Operator> selectUserOperator(String SQL){
	    RowMapper mapper = new RowMapper() {
	        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Operator operator = new Operator();
	            operator.setIds(rs.getLong("ids"));
	            operator.setNames(rs.getString("names"));
	            operator.setUrl(rs.getString("url"));
	            return operator;
	        }
	    };
	    List<Operator> operatorList =  (List<Operator>)( daoJdbcBase.getJdbcTemplate().query(SQL, mapper));
	    return operatorList;
	}

}
