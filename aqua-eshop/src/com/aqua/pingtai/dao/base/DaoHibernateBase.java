package com.aqua.pingtai.dao.base;

import java.util.Date;
import java.util.List;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.base.EntityBase;
import com.aqua.pingtai.entity.bean.authority.User;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings({"unchecked"})
public class DaoHibernateBase extends HibernateDaoSupport {

	public DaoHibernateBase() {
		
	}
	
	/**
	 * 获取一个对象
	 * @param <T>
	 * @param entityClass 对象类
	 * @param ids
	 * @return
	 */
	public <T extends EntityBase> T findOneEntity(Class<T> entityClass, Long ids) throws Exception {	
		return (T) getHibernateTemplate().get(entityClass, ids);
	}

	/**
	 * 获取一个对象
	 * @param <T>
	 * @param entityClass 对象类
	 * @param filterString 过滤HQL
	 * @return
	 * @throws Exception
	 */
	public <T extends EntityBase> T findOneEntity(Class<T> entityClass,
			String filterString) throws Exception {	
		Query query = this.getSession().createQuery("from " + entityClass.getName() + filterString);
		return (T) query.uniqueResult();
	}

	/**
	 * 获取多个对象
	 * @param <T>
	 * @param entityClass 对象类
	 * @param filterString 过滤HQL
	 * @return
	 * @throws Exception
	 */
	public <T extends EntityBase> List<T> findManyEntity(Class<T> entityClass, String filterString) throws Exception {
		Query query = this.getSession().createQuery("from "+entityClass.getName()+filterString);
		List<T> list = query.list();
		return list;
	}
	
	/**
	 * 获取多个区段对象
	 * @param <T>
	 * @param entityClass 对象类
	 * @param filterString 过滤HQL
	 * @return
	 * @throws Exception
	 */
	public <T extends EntityBase> List<T> findManyEntityByStartEnd(Class<T> entityClass, String filterString, int start, int end) throws Exception {	
		Query query = this.getSession().createQuery("from " + entityClass.getName() + filterString);
		query.setFirstResult(start);
		query.setMaxResults(end);
		List<T> list = query.list();
		return list;
	}
	
	/**
	 * 获取多个对象
	 * @param <T>
	 * @param entityClass 对象类
	 * @param filterString 过滤HQL
	 * @param orderByString 排序
	 * @return
	 * @throws Exception
	 */
	public <T extends EntityBase> List<T> findManyEntityByOrder(Class<T> entityClass, String filterString, String orderByString)
			throws Exception {
		Query query = this.getSession().createQuery("from " + entityClass.getName() + filterString + orderByString);
		List<T> list = query.list();
		return list;
	}
	
	/**
	 * 分页获取多个对象
	 * @param entityClass 对象类
	 * @param filterString 过滤HQL
	 * @param queryResult
	 * @return
	 * @throws Exception
	 */
	public QueryResult findSplitPage(Class<? extends EntityBase> entityClass, String filterString, QueryResult queryResult) throws Exception {
		Query queryCount = this.getSession().createQuery("select count(*) from " + entityClass.getName() + filterString);
		Long recordCount = (Long)queryCount.uniqueResult();
		
		Long currentPage = queryResult.getCurrentPage();//当前页数
		Long maxResult = queryResult.getMaxResult();//抓取数
		Long firstResult = (currentPage-1l)==0l? 0l : (currentPage-1l)*maxResult;//开始处
		
		Query query = this.getSession().createQuery("from " + entityClass.getName() + filterString);
		query.setFirstResult(firstResult.intValue());
		query.setMaxResults(maxResult.intValue());
		
		queryResult.setRecordCount(recordCount);//记录总数
		queryResult.setResultList(query.list());//记录集
		
		queryResult.compute();//计算
		
		return queryResult;
	}

	/**
	 * 分页获取多个对象
	 * @param entityClass 对象类
	 * @param filterString 过滤HQL
	 * @param orderByString 排序
	 * @param queryResult
	 * @return
	 * @throws Exception
	 */
	public QueryResult findSplitPageByOrder(Class<? extends EntityBase> entityClass, String filterString, String orderByString, QueryResult queryResult) throws Exception {
	
		Query queryCount = this.getSession().createQuery("select count(*) from " + entityClass.getName() + filterString + orderByString);
		Long recordCount = (Long)queryCount.uniqueResult();
		
		Long currentPage = queryResult.getCurrentPage();//当前页数
		Long maxResult = queryResult.getMaxResult();//抓取数
		Long firstResult = (currentPage-1l)*maxResult;//开始处
		
		Query query = this.getSession().createQuery("from " + entityClass.getName() + filterString + orderByString);
		query.setFirstResult(firstResult.intValue());
		query.setMaxResults(maxResult.intValue());
	
		queryResult.setRecordCount(recordCount);//记录总数
		queryResult.setResultList(query.list());//记录集
		
		queryResult.compute();//计算
		
		return queryResult;
	}
	
	/**
	 * 获取记录条数
	 * @param entityClass 对象类
	 * @param filterString 过滤JPQL
	 * @return
	 * @throws Exception
	 */
	public Long findRecordCount(Class<? extends EntityBase> entityClass, String filterString) throws Exception {
		Query query = this.getSession().createQuery("select count(*) from " + entityClass.getName() + filterString);
		Long recordCount = (Long)query.uniqueResult();
		return recordCount;
	}

	/**
	 * 保存
	 * @param entityClass
	 * @param user
	 */
	public void saveOneEntity(EntityBase entity, User user) throws Exception {
		if(null==entity.getIds() || entity.getIds()==0l){
			Date date = new Date();
			entity.setCreator(user);//创建人
			entity.setCreatedDate(date);//创建时间
			entity.setValid("youXiao");//是否有效
			this.getSession().save(entity);
		}
	}

	/**
	 * 更新
	 * @param entityClass
	 * @param user
	 */
	public void updateOneEntity(EntityBase entity, User user) throws Exception {
		if(null!=entity.getIds() && entity.getIds()!=0l){
			Date date = new Date();
			entity.setModified(user);//修改人
			entity.setModifiedDate(date);//修改时间
			User creatorUser = (User) this.getSession().get(User.class, entity.getCreator().getIds());//创建人
			entity.setCreator(creatorUser);
			this.getSession().clear();//临时解决org.hibernate.NonUniqueObjectException
			this.getSession().update(entity);
		}
	}
	
	/**
	 * 保存或则更新
	 * @param entity
	 */
	public void saveOrUpdate(EntityBase entity, User user) throws Exception {
		if(null==entity.getIds() || entity.getIds()==0l){
			Date date = new Date();
			entity.setCreator(user);//创建人
			entity.setCreatedDate(date);//创建时间
			entity.setValid("youXiao");//是否有效
			this.getSession().save(entity);
		}
		if(null!=entity.getIds() && entity.getIds()!=0l){
			Date date = new Date();
			entity.setModified(user);//修改人
			entity.setModifiedDate(date);//修改时间
			User creatorUser = (User) this.getSession().get(User.class, entity.getCreator().getIds());//创建人
			entity.setCreator(creatorUser);
			//this.getSession().clear();
			this.getSession().update(entity);
		}
	}
	
	/**
	 * 删除
	 * @param entityClass
	 * @param user
	 */
	public void deleteOneEntity(EntityBase entity) throws Exception {
		if(null!=entity.getIds() && entity.getIds()!=0l){
			this.getSession().delete(entity);
		}
	}
	
	/**
	 * 标记为不可用
	 * @param entityClass
	 * @param user
	 */
	public void updateOneEntityToWuXiao(EntityBase entity, User user) throws Exception {
		if(null!=entity.getIds() && entity.getIds()!=0l){
			entity.setValid("wuXiao");
			Date date = new Date();
			entity.setModified(user);//修改人
			entity.setModifiedDate(date);//修改时间
			User creatorUser = (User) this.getSession().get(User.class, entity.getCreator().getIds());//创建人
			entity.setCreator(creatorUser);
			//this.getSession().clear();
			this.getSession().update(entity);
		}
	}
	
	/**
	 * 执行一段HQL
	 * @param HQL
	 * @return
	 */
	public int excuteHQL(String HQL) throws Exception {
		int processResult = 0;
		if(null!=HQL && !HQL.trim().equals("")){
			processResult = this.getSession().createQuery(HQL).executeUpdate();
		}
		return processResult;
	}
	
	/**
	 * 执行一段SQL
	 * @param SQL
	 * @return
	 */
	public int excuteSQL(String SQL) throws Exception {
		int processResult = 0;
		if(null!=SQL && !SQL.trim().equals("")){
			processResult = this.getSession().createSQLQuery(SQL).executeUpdate();
		}
		return processResult;
	}

	
}
