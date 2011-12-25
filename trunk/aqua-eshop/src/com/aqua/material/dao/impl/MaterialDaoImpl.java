package com.aqua.material.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.aqua.material.dao.MaterialDao;
import com.aqua.pingtai.common.QueryResult;

@SuppressWarnings("unchecked")
@Repository("materialDaoImpl")
public class MaterialDaoImpl extends HibernateDaoSupport implements MaterialDao{
	
	public MaterialDaoImpl(){
		
	}
	
	public void saveObject(Object object) {
		this.getSession().persist(object);
	}
	
	public void saveCommitObject(Object object) {
		Session session = this.getSession();
		Transaction tx = session.getTransaction();
		session.persist(object);
		session.flush();
		tx.commit();
	}
	
	public void updateObject(Object object) {
		this.getSession().merge(object);
	}
	
	public void updateCommitObject(Object object){
		Session session = this.getSession();
		Transaction tx = session.getTransaction();
		session.merge(object);
		session.flush();
		tx.commit();
	}

	public void saveOrUpdate(Object object) {
		this.getSession().saveOrUpdate(object);
	}

	public <T> T findOneObject(Class<T> entityClass, Long id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public <T> List<T> findObjectByFilter(Class<T> entityClass,
			String filterString) {
		Query query = this.getSession().createQuery("from "+entityClass.getName()+filterString);
		List<T> list = query.list();
		if(list==null){
			list=new ArrayList<T>();
		}
		return list;
	}

	public <T> T findObjectByName(Class<T> entityClass, String name) {
		Query query = this.getSession().createQuery("from "+entityClass.getName()+" o where o.name='"+name+"' order by o.id");
		List<T> list = query.list();
		if(list!=null){
			return list.get(0);
		}else {
			return null;
		}
	}

	public void deleteObject(Object object) {
		this.getSession().delete(object);
	}
	
	public void deleteCommitObject(Object object) {
		Session session = this.getSession();
		Transaction tx = session.getTransaction();
		session.delete(object);
		session.flush();
		tx.commit();
	}
	
	public <T> QueryResult findSplitPageByOrder(Class<T> entityClass, String filterString, String orderByString, QueryResult queryResult) {
		Query queryCount = this.getSession().createQuery("select count(*) from " + entityClass.getName() + filterString + orderByString);
		Long recordCount = (Long)queryCount.uniqueResult();
		
		Long currentPage = queryResult.getCurrentPage();
		Long maxResult = queryResult.getMaxResult();
		Long firstResult = (currentPage-1l)*maxResult;
		
		Query query = this.getSession().createQuery("from " + entityClass.getName() + filterString + orderByString);
		query.setFirstResult(firstResult.intValue());
		query.setMaxResults(maxResult.intValue());
	
		queryResult.setRecordCount(recordCount);
		queryResult.setResultList(query.list());
		
		queryResult.compute();
		
		return queryResult;
	}

}
