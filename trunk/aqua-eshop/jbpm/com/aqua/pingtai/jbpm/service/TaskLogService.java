package com.aqua.pingtai.jbpm.service;

import com.aqua.pingtai.common.QueryResult;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TaskLogService {
	
	/**
	 * 分页任务列表
	 * @param filterString
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryTaskLog(String filterString, QueryResult queryResult) throws Exception ;

}