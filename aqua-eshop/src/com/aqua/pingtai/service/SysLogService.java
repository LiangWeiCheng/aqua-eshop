package com.aqua.pingtai.service;

import com.aqua.pingtai.common.QueryResult;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SysLogService {
	
	/**
	 * 日志分页
	 * @param filterString
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQuerySysLog(String filterString, QueryResult queryResult) throws Exception;
	
}
