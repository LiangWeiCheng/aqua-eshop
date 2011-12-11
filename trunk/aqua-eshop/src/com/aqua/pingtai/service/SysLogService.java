package com.aqua.pingtai.service;

import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.SysLog;

@Transactional
public interface SysLogService {
	
	/**
	 * 日志分页
	 * @param filterString
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQuerySysLog(String filterString, QueryResult queryResult) throws Exception;
	
	/**
	 * 保存一个SysLog
	 * @param entity
	 */
	public void save(SysLog entity);

}
