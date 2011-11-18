package com.aqua.pingtai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.interfaces.OperatorDao;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.OperatorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("operatorServiceImpl")
@Scope("singleton")
public class OperatorServiceImpl implements OperatorService {

	private static final Log log = LogFactory.getLog(OperatorServiceImpl.class);
	
	@Resource(name="operatorDaoImpl")
	private OperatorDao operatorDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="oscacheFactory")
	private OscacheFactory oscacheFactory;
	
	public OperatorServiceImpl(){
		
	}

	public OperatorDao getOperatorDaoImpl() {
		return operatorDaoImpl;
	}

	public void setOperatorDaoImpl(OperatorDao operatorDaoImpl) {
		this.operatorDaoImpl = operatorDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	public OscacheFactory getOscacheFactory() {
		return oscacheFactory;
	}

	public void setOscacheFactory(OscacheFactory oscacheFactory) {
		this.oscacheFactory = oscacheFactory;
	}

	/**
	 * 删除操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOperator(Operator operator) {
		try {
			operator = daoHibernateBase.findOneEntity(Operator.class, operator.getIds());
			oscacheFactory.removeObject(operator.getUrl());//删除缓存
			daoHibernateBase.excuteHQL("delete Operator Role where ids="+operator.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个操作
	 * @param dictTypeids
	 * @return
	 */
	public Operator getOneOperator(Long operatorIds) {
		Operator operator = null;
		try {
			operator = daoHibernateBase.findOneEntity(Operator.class, operatorIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operator;
	}

	/**
	 * 保存操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOperator(Operator operator) {
		User createUser = Context.getCurrentUser();
		try {
			daoHibernateBase.saveOneEntity(operator, createUser);
			oscacheFactory.putObject(operator.getUrl(), operator);//缓存对象
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询操作列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryOperator(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(Operator.class, filterString, queryResult);
	}

	/**
	 * 更新操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOperator(Operator operator) {
		User updateUser = Context.getCurrentUser();
		try {
			daoHibernateBase.updateOneEntity(operator, updateUser);
			oscacheFactory.putObject(operator.getUrl(), operator);//缓存对象
			log.info(updateUser.getUserName()+",更新操作,操作名:"+operator.getNames());
		} catch (Exception e) {
			log.error(updateUser.getUserName()+",更新操作异常!!!操作名:"+operator.getNames());
			e.printStackTrace();
		}
	}

	/**
	 * HQL查询Operator
	 * @param hql
	 * @return
	 */
	public List<Operator> getOperatorByHql(String hql){
		List<Operator> operatorList = null;
		try {
			operatorList =  daoHibernateBase.findManyEntity(Operator.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operatorList;
	}
	
	/**
	 * SQL查询Operator
	 * @param SQL
	 * @return
	 */
	public List<Operator> selectUserOperator(String SQL){
		return operatorDaoImpl.selectUserOperator(SQL);
	}
}
