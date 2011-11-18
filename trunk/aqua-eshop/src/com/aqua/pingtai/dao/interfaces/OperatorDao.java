package com.aqua.pingtai.dao.interfaces;

import java.util.List;

import com.aqua.pingtai.entity.bean.authority.Operator;

public interface OperatorDao {

	/**
	 * SQL查询Operator
	 * @param SQL
	 * @return
	 */
	public List<Operator> selectUserOperator(String SQL);
	
}