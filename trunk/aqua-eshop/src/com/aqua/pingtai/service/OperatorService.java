package com.aqua.pingtai.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.Operator;

@Transactional
public interface OperatorService {
	
	/**
	 * 获取一个操作
	 * @param dictTypeids
	 * @return
	 */
	public Operator getOneOperator(Long operatorIds);
	
	/**
	 * 查询操作列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryOperator(String filterString, QueryResult queryResult) throws Exception ;
	
	/**
	 * 保存操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveOperator(Operator operator);
	
	/**
	 * 更新操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOperator(Operator operator);
	
	/**
	 * 删除操作
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOperator(Operator operator);
	
	/**
	 * HQL查询Operator
	 * @param hql
	 * @return
	 */
	public List<Operator> getOperatorByHql(String hql);
	
	/**
	 * SQL查询Operator
	 * @param SQL
	 * @return
	 */
	public List<Operator> selectUserOperator(String SQL);
	
}