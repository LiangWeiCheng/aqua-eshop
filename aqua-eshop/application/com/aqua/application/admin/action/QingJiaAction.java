package com.aqua.application.admin.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;
import com.aqua.pingtai.jbpm.service.ProcessDefinitionService;
import com.aqua.application.admin.service.QingJiaService;
import com.aqua.application.entity.QingJia;

//请假
@SuppressWarnings({"serial"})
public class QingJiaAction extends BaseAction {
	
	@Resource(name="qingJiaServiceImpl")
	private QingJiaService qingJiaServiceImpl;
	
	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	
	@Resource(name="processDefinitionServiceImpl")
	private ProcessDefinitionService processDefinitionServiceImpl;

	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	private QingJia qingJia;
	private String taskId;
	
	/**
	 * 请假列表
	 * @return
	 */
	public String qingJiaList(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by countDate desc";
			qingJiaServiceImpl.splitPageQueryGuide(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/application/admin/qingJia/qingJiaList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 请假单明细
	 * @return
	 */
	public String qingJiaView(){
		try {
			qingJia = daoHibernateBase.findOneEntity(QingJia.class, qingJia.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/application/admin/qingJia/qingJiaView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 启动请假流程,跳转到任务列表
	 * @return
	 */
	public String qingJiaStart(){
		User currentUser = Context.getCurrentUser();
		String taskAssignmentHql = " where processDefinitionId='QingJia-1' and taskName='填写请假单' ";
		List<TaskAssignment> taskAssignmentList = processDefinitionServiceImpl.getTaskAssignmentByHQL(taskAssignmentHql);
		TaskAssignment taskAssignment = taskAssignmentList.get(0);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(taskAssignment.getVariableName(), currentUser.getIds().toString());
		jbpmTemplate.startProcessByKey("QingJiaNew", map);//启动流程

		returnPageURL = "/pingTai/taskLogPingTaiAction!taskLogList.action";
		return "redirect";
	}
	
	/**
	 * 填写请假单
	 * @return
	 */
	public String qingJiaAdd(){
		returnPageURL = "/WEB-INF/jsp/application/admin/qingJia/qingJiaAdd.jsp";
		return "dispatcher";
	}
	
	/**
	 * 保存请假单
	 * @return
	 */
	public String qingJiaSave(){
		qingJiaServiceImpl.saveQingJia(taskId, qingJia);
		returnPageURL = "/applicationAdmin/qingJia/ApplicationAdminAction!qingJiaList.action";
		return "redirect";
	}
	
	/**
	 * 部门经理审批
	 * @return
	 */
	public String buMenJingLiAdd(){
		Long qingJiaIds = (Long) jbpmTemplate.getTaskVariables(taskId, "qingJiaIds");
		try {
			qingJia = daoHibernateBase.findOneEntity(QingJia.class, qingJiaIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/application/admin/qingJia/buMenJingLiAdd.jsp";
		return "dispatcher";
	}
	
	/**
	 * 部门经理保存
	 * @return
	 */
	public String buMenJingLiSave(){
		qingJiaServiceImpl.buMenJingLiSave(taskId, qingJia);
		returnPageURL = "/pingTai/taskLogPingTaiAction!taskLogList.action";
		return "redirect";
	}
	
	/**
	 * 总经理审批
	 * @return
	 */
	public String zongJingLiAdd(){
		Long qingJiaIds = (Long) jbpmTemplate.getTaskVariables(taskId, "qingJiaIds");
		try {
			qingJia = daoHibernateBase.findOneEntity(QingJia.class, qingJiaIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/application/admin/qingJia/zongJingLiAdd.jsp";
		return "dispatcher";
	}
	
	/**
	 * 总经理保存
	 * @return
	 */
	public String zongJingLiSave(){
		qingJiaServiceImpl.zongJingLiSave(taskId, qingJia);
		returnPageURL = "/pingTai/taskLogPingTaiAction!taskLogList.action";
		return "redirect";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.jxc.admin.action.QingJiaAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		String start = queryParameter.getParameter("start");
		String end = queryParameter.getParameter("end");
		
		StringBuffer sb = new StringBuffer();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(null!=start && !start.equals("")){
			try {
				Date date = DateFormat.getDateInstance().parse(start);
				sb.append(" countDate>='").append(formatter.format(date)).append("'").append(" and ");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(null!=end && !end.equals("")){
			try {
				Date date = DateFormat.getDateInstance().parse(end);
				sb.append(" countDate<='").append(formatter.format(date)).append("'");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public QingJiaService getQingJiaServiceImpl() {
		return qingJiaServiceImpl;
	}

	public void setQingJiaServiceImpl(QingJiaService qingJiaServiceImpl) {
		this.qingJiaServiceImpl = qingJiaServiceImpl;
	}

	public QingJia getQingJia() {
		return qingJia;
	}

	public void setQingJia(QingJia qingJia) {
		this.qingJia = qingJia;
	}

	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}

	public ProcessDefinitionService getProcessDefinitionServiceImpl() {
		return processDefinitionServiceImpl;
	}

	public void setProcessDefinitionServiceImpl(
			ProcessDefinitionService processDefinitionServiceImpl) {
		this.processDefinitionServiceImpl = processDefinitionServiceImpl;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}

}
