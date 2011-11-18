package com.aqua.pingtai.jbpm.listener;

import java.util.List;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;

import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.task.Task;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.internal.log.Log;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 任务回退监听器
 */
@SuppressWarnings("serial")
public class RollbackListener implements EventListener {
	
	private static final Log log = Log.getLog(RollbackListener.class.getName());
	
	private JbpmTemplate jbpmTemplate;
	private DaoHibernateBase daoHibernateBase;
	private DaoJdbcBase daoJdbcBase;
	private HibernateTemplate hibernateTemplate;
	
	private String rollbackTo;
	
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		// 获取流程定义对象
		ProcessInstance processInstance = execution.getProcessInstance();
		String processDefinitionId = processInstance.getProcessDefinitionId();
		ProcessDefinitionImpl processDefinition = (ProcessDefinitionImpl) jbpmTemplate.getProcessEngine()
				.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).uniqueResult();

		ActivityImpl toActivityImpl = processDefinition.findActivity(rollbackTo);
		if (toActivityImpl == null) {
			String msg = "回退错误:流程定义ID是" + processDefinitionId + ",回退到的节点是" + rollbackTo;
			log.error(msg);
			throw new Exception(msg);
		}

		ActivityImpl fromActivityImpl = ((ExecutionImpl) execution).getActivity();

		TransitionImpl transition = fromActivityImpl.createOutgoingTransition();
		transition.setName(fromActivityImpl.getName() + " to " + rollbackTo);//"B to A"
		transition.setDestination(toActivityImpl);
	}
	
	public JbpmTemplate getJbpmTemplate() {
		jbpmTemplate = (JbpmTemplate) Context.getSpringBean("jbpmTemplate");
		return jbpmTemplate;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		daoHibernateBase = (DaoHibernateBase) Context.getSpringBean("daoHibernateBase");
		return daoHibernateBase;
	}
	
	public DaoJdbcBase getDaoJdbcBase() {
		daoJdbcBase = (DaoJdbcBase) Context.getSpringBean("daoJdbcBase");
		return daoJdbcBase;
	}

	public HibernateTemplate getHibernateTemplate() {
		hibernateTemplate = (HibernateTemplate) Context.getSpringBean("hibernateTemplate");
		return hibernateTemplate;
	}
	
	//测试代码
	public void test(){
		
		//开始	 A:小张	B:小李	结束
		
		TaskService taskService = jbpmTemplate.getTaskService();
		Task task = taskService.findPersonalTasks("小李").get(0);
		String rollbackToTaskName = "A";
		taskService.completeTask(task.getId(), task.getActivityName() + " to " + rollbackToTaskName);//"B to A"	
	
		//无用
		/*jbpmTemplate.getProcessEngine().execute(new Command<Void>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Void execute(Environment environment) throws Exception {
				// TODO Auto-generated method stub
				HistoryService historyService = environment.get(HistoryService.class);
				List<HistoryTask> historyTaskList = historyService.createHistoryTaskQuery().taskId(taskOutlayApply.getId()).list();
				for (HistoryTask historyTask : historyTaskList) {
					if (log.isInfoEnabled()) {
						log.info("Task " + historyTask.getId() + " end by " + historyTask.getAssignee() + " at "
								+ historyTask.getEndTime().toString());
					}
				}
				return null;
			}
		});
		
		<process name="Rollback" xmlns="http://jbpm.org/4.3/jpdl">
			<start g="62,26,129,179" name="start">
				<transition g="-53,-17" name="to outlay apply" to="outlay apply" />
			</start>
			<task assignee="Jack" g="38,147,92,52" name="outlay apply">
				<transition g="-53,-17" name="to leader audit" to="leader audit" />
			</task>
			<task assignee="Alex" g="38,231,92,52" name="leader audit">
				<on event="start">
					<event-listener class="com.examples.jbpm4.n3_cn_rollback.RollbackListener">
						<field name="m_rollbackTo">
							<string value="outlay apply" />
						</field>
					</event-listener>
				</on>
				<transition to="end" />
			</task>
			<end g="60,315,48,48" name="end" />
		</process>
		
		*/
		
	}
	
}
