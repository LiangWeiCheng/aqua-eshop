package com.aqua.application.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;
import com.aqua.application.admin.dao.QingJiaDao;
import com.aqua.application.admin.service.QingJiaService;
import com.aqua.application.entity.QingJia;

@Transactional
@Service("qingJiaServiceImpl")
@Scope("singleton")
public class QingJiaServiceImpl implements QingJiaService {
	
	@Resource(name="qingJiaDaoImpl")
	private QingJiaDao qingJiaDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	
	public QingJiaServiceImpl() {
		
	}
		
	/**
	 * 分页请假列表
	 * @param filterString
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryGuide(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(QingJia.class, filterString, queryResult);
	}
	
	/**
	 * 保存--申请人
	 * @param taskId
	 * @param qingJia
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveQingJia(String taskId, QingJia qingJia){
		try {
			qingJia.setCountDate(new Date());
			qingJia.setQingJiaUser(Context.getCurrentUser());
			qingJia.setQingJiaState("提交请假单");
			daoHibernateBase.saveOneEntity(qingJia, Context.getCurrentUser());
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("countDay", qingJia.getCountDay());//传递请假天数
			map.put("qingJiaIds", qingJia.getIds());//传递请假天数
			jbpmTemplate.completeTask(taskId, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存--部门经理
	 * @param taskId
	 * @param qingJia
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void buMenJingLiSave(String taskId, QingJia qingJia){
		try {
			User qingJiaUser = daoHibernateBase.findOneEntity(User.class, qingJia.getQingJiaUser().getIds());
			qingJia.setQingJiaUser(qingJiaUser);
			qingJia.setBuMenJingLiDate(new Date());
			qingJia.setQingJiaState("部门经理审批完毕");
			daoHibernateBase.updateOneEntity(qingJia, Context.getCurrentUser());
			jbpmTemplate.completeTask(taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存--总经理
	 * @param taskId
	 * @param qingJia
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void zongJingLiSave(String taskId, QingJia qingJia){
		try {
			User qingJiaUser = daoHibernateBase.findOneEntity(User.class, qingJia.getQingJiaUser().getIds());
			qingJia.setQingJiaUser(qingJiaUser);
			qingJia.setZongJingLiDate(new Date());
			qingJia.setQingJiaState("总经理审批完毕");
			daoHibernateBase.updateOneEntity(qingJia, Context.getCurrentUser());
			jbpmTemplate.completeTask(taskId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}

	public QingJiaDao getQingJiaDaoImpl() {
		return qingJiaDaoImpl;
	}

	public void setQingJiaDaoImpl(QingJiaDao qingJiaDaoImpl) {
		this.qingJiaDaoImpl = qingJiaDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}

	
}
