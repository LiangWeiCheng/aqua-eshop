package com.aqua.pingtai.jbpm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.model.Activity;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.entity.Swimlane;
import com.aqua.pingtai.jbpm.entity.TaskAssignment;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;
import com.aqua.pingtai.jbpm.service.ProcessDefinitionService;

/**
 * 流程定义管理
 */
@SuppressWarnings({"serial"})
public class ProcessDefinitionAction extends BaseAction{

	private static final Log log = LogFactory.getLog(ProcessDefinitionAction.class);
	
	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	
	@Resource(name="processDefinitionServiceImpl")
	private ProcessDefinitionService processDefinitionServiceImpl;
	
	private List<File> upload;//上传文件
	private List<String> uploadContentType;//上传文件类型
	private List<String> uploadFileName;//文件名
	
	private List<ProcessDefinition> processDefinitionList = null;//保存数据
	private List<? extends Activity> activityList = null;//保存数据
	private List<TaskAssignment> taskAssignmentList = null;
	private List<Swimlane> swimlaneAssignmentList = null;
	
	/**
	 * 分页流程定义
	 * @return
	 */
	public String processDefinitionList(){
		try {
			initQueryList(true);
			processDefinitionList = processDefinitionServiceImpl.splitPageQueryProcessDefinition(jbpmTemplate, queryParameter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/processDefinition/processDefinitionList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 流程定义上传部署
	 * @return
	 */
	public String processUploadDeploy(){
		User currentUser = Context.getCurrentUser();
		List<File> files = getUpload();
		FileInputStream fis = null;
		for (int i = 0 ; i < files.size() ; i++)
		{	
			try {
				fis = new FileInputStream(files.get(i));
				RepositoryService repositoryService = jbpmTemplate.getRepositoryService();//获取存储服务
				repositoryService.createDeployment().addResourcesFromZipInputStream(new ZipInputStream(fis)).deploy();//部署
				log.info(currentUser.getUserName()+"上传流程");
			} catch (FileNotFoundException e) {
				log.error(currentUser.getUserName()+"上传流程失败!!!");
				e.printStackTrace();
			}
		}
		
		returnPageURL = "/pingTai/processDefinitionPingTaiAction!processDefinitionList.action";
		return "redirect";
	}
	
	/**
	 * 删除指定流程定义
	 * @return
	 */
	public String processRemove(){
		processDefinitionServiceImpl.removeProcessDefinition(jbpmTemplate, request.getParameter("id"));
		returnPageURL = "/pingTai/processDefinitionPingTaiAction!processDefinitionList.action";
		return "redirect";
	}
	
	/**
	 * 查看流程图
	 * @return
	 */
	public String viewProcessDefinitionImage(){
		String deploymentId = request.getParameter("deploymentId");//部署id
		String imageResourceName = request.getParameter("imageResourceName");//图片名称 
		if(null!=deploymentId && !deploymentId.trim().equals("") && null!=imageResourceName && !imageResourceName.trim().equals("")){
			RepositoryService repositoryService = jbpmTemplate.getRepositoryService();
			InputStream imageInputStream = repositoryService.getResourceAsStream(deploymentId, imageResourceName);
			request.setAttribute("imageInputStream", imageInputStream);
		}
		
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/processDefinition/viewProcessDefinitionImage.jsp";
		return "dispatcher";
	}
	
	/**
	 * 启动流程实例
	 * @return
	 */
	public String createProcessInstance(){
		String processDefinitionId = request.getParameter("processDefinitionId");//流程定义id
		if(null!=processDefinitionId && !processDefinitionId.trim().equals("")){
			jbpmTemplate.startProcessById(processDefinitionId);
		}
		
		returnPageURL = "/pingTai/processDefinitionPingTaiAction!processDefinitionList.action";
		return "redirect";
	}
	
	/**
	 * 查询流程定义的所有task节点
	 * @return
	 */
	public String viewProcessDefinitionTaskList(){
		String processDefinitionId = request.getParameter("processDefinitionId");//流程定义id
		if(null!=processDefinitionId && !processDefinitionId.trim().equals("")){
			RepositoryService repositoryService = jbpmTemplate.getProcessEngine().getRepositoryService();
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl)processDefinitionQuery.processDefinitionId(processDefinitionId).uniqueResult();
			activityList = processDefinitionImpl.getActivities();//1.所有流程定义节点
			request.setAttribute("processDefinitionId", processDefinitionId);
			
			taskAssignmentList = processDefinitionServiceImpl.getTaskAssignmentByHQL(" where processDefinitionId='"+processDefinitionId+"' order by createdDate asc");
			int taskAssignmentListSize = taskAssignmentList.size();
			if(taskAssignmentListSize==0){
				taskAssignmentList = new ArrayList<TaskAssignment>();//3.某个任务对应的流程分派
				TaskAssignment flowAssign = null;
				for (Activity activity : activityList) {
					System.out.println(activity.getType());
					if(activity.getType().equals("task")){
						String taskName = activity.getName();
						flowAssign = null;
						flowAssign = new TaskAssignment();
						flowAssign.setTaskName(taskName);
						taskAssignmentList.add(flowAssign);
					}
				}
			}
		}
		jbpmTemplate.getProcessEngine().close();
		
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/processDefinition/processDefinitionTaskList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 保存流程定义task对应的所有流程分派
	 * @return
	 */
	public String saveProcessDefinitionTaskFlowAssign(){
		String processDefinitionId = request.getParameter("processDefinitionId");//流程定义id
		if(null != taskAssignmentList){
			processDefinitionServiceImpl.saveTaskAssignmentList(processDefinitionId, taskAssignmentList);
		}	
		returnPageURL = "/pingTai/processDefinitionPingTaiAction!viewProcessDefinitionTaskList.action?processDefinitionId="+processDefinitionId;
		return "redirect";
	}
	
	/**
	 * 查询流程定义的所有swimlane节点
	 * @return
	 */
	public String viewSwimlaneList(){
		String processDefinitionId = request.getParameter("processDefinitionId");//流程定义id
		if(null!=processDefinitionId && !processDefinitionId.trim().equals("")){
			RepositoryService repositoryService = jbpmTemplate.getProcessEngine().getRepositoryService();
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			ProcessDefinitionImpl processDefinitionImpl = (ProcessDefinitionImpl)processDefinitionQuery.processDefinitionId(processDefinitionId).uniqueResult();
			activityList = processDefinitionImpl.getActivities();//1.所有流程定义节点
			request.setAttribute("processDefinitionId", processDefinitionId);
			
			swimlaneAssignmentList = null;//processDefinitionServiceImpl.getTaskAssignmentByHQL(" where processDefinitionId='"+processDefinitionId+"' order by createdDate asc");
			int swimlaneAssignmentListSize = swimlaneAssignmentList.size();
			if(swimlaneAssignmentListSize == 0){
				taskAssignmentList = new ArrayList<TaskAssignment>();//3.某个任务对应的流程分派
				TaskAssignment flowAssign = null;
				for (Activity activity : activityList) {
					if(activity.getType().equals("swimlane")){
						String taskName = activity.getName();
						flowAssign = null;
						flowAssign = new TaskAssignment();
						flowAssign.setTaskName(taskName);
						taskAssignmentList.add(flowAssign);
					}
				}
			}
		}
		jbpmTemplate.getProcessEngine().close();
		
		returnPageURL = "/WEB-INF/jsp/pingtai/jbpm/processDefinition/processDefinitionTaskList.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.JbpmProcessDefinitionAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		return null;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<ProcessDefinition> getProcessDefinitionList() {
		return processDefinitionList;
	}

	public void setProcessDefinitionList(
			List<ProcessDefinition> processDefinitionList) {
		this.processDefinitionList = processDefinitionList;
	}

	public List<? extends Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<? extends Activity> activityList) {
		this.activityList = activityList;
	}

	public List<TaskAssignment> getTaskAssignmentList() {
		return taskAssignmentList;
	}

	public void setTaskAssignmentList(List<TaskAssignment> taskAssignmentList) {
		this.taskAssignmentList = taskAssignmentList;
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
	
	

}
