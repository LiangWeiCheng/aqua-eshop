package com.aqua.pingtai.dao.impl;

import javax.annotation.Resource;

import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.dao.interfaces.DictDao;
import com.aqua.pingtai.entity.bean.authority.Dict;
import com.aqua.pingtai.entity.bean.authority.DictType;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class DictDaoImpl implements DictDao{

	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	public DictDaoImpl() {
		super();
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
	 * 获取一个数据字典类型
	 * @param dictTypeids
	 * @return
	 */
	public DictType getOneDictType(Long dictTypeids){
		return (DictType) hibernateTemplate.get(DictType.class, dictTypeids);
	}

	/**
	 * 保存一个字典
	 * @param user
	 */
	public void saveOneDict(Dict dict) {
		hibernateTemplate.save(dict);
	}

	
	

}
