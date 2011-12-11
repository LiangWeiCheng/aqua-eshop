package com.aqua.pingtai.dao.interfaces;

import java.util.List;

import com.aqua.pingtai.entity.bean.authority.SysLog;

public interface SysLogDao {
	
	/**
	 * 保存一个SysLog
	 * @param entity
	 */
	public void save(SysLog entity);

	/**
	 * SQL查询SysLog
	 * @param SQL
	 * @return
	 */
	public List<SysLog> query(String queryString);
}
