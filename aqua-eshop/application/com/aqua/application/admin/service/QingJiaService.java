package com.aqua.application.admin.service;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.application.entity.QingJia;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QingJiaService {
	
	/**
	 * 分页请假列表
	 * @param filterString
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryGuide(String filterString, QueryResult queryResult) throws Exception ;
	
	/**
	 * 保存--申请人
	 * @param taskId
	 * @param qingJia
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveQingJia(String taskId, QingJia qingJia);
	
	/**
	 * 保存--部门经理
	 * @param taskId
	 * @param qingJia
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void buMenJingLiSave(String taskId, QingJia qingJia);
	
	/**
	 * 保存--总经理
	 * @param taskId
	 * @param qingJia
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void zongJingLiSave(String taskId, QingJia qingJia);
}
