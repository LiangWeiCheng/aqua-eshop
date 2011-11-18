package com.aqua.pingtai.jbpm.listener;

import java.util.List;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;

import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.internal.log.Log;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 任务取回
 */
@SuppressWarnings("serial")
public class TakebackListener implements EventListener {

	private static final Log log = Log.getLog(TakebackListener.class.getName());
	
	private JbpmTemplate jbpmTemplate;
	private DaoHibernateBase daoHibernateBase;
	private DaoJdbcBase daoJdbcBase;
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		TransitionImpl transition = ((ExecutionImpl) execution).getTransition();
		ActivityImpl withdrawFrom = transition.getDestination();
		ActivityImpl withdrawBackto = transition.getSource();

		if (withdrawFrom == null || withdrawBackto == null) {
			String msg = "Not support withdraw.";
			log.error(msg);
			throw new Exception(msg);
		}

		TransitionImpl newTran = withdrawFrom.createOutgoingTransition();
		newTran.setName(withdrawFrom.getName() + " to " + withdrawBackto.getName());
		newTran.setDestination(withdrawBackto);
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
	
	//
	public void test(){
		//开始	 A:小张	B:小李	结束
		
		String processInstanceId = "processInstanceId";//processInstance.getId();
		
		// 取回任务
		jbpmTemplate.getProcessEngine().execute(new TakebackCommand(processInstanceId, "A"));

		// 验证历史痕迹已经消除
		HistoryService historyService = jbpmTemplate.getHistoryService();
		List<HistoryTask> historyTaskList = historyService.createHistoryTaskQuery().assignee("小李").list();
		//historyTaskList.size()==0	验证小李的任务 应该为零
		List<HistoryActivityInstance> historyActivityInstanceList = historyService
				.createHistoryActivityInstanceQuery().activityName("B").list();
		//historyActivityInstanceList.size()==0	验证B节点的任务也应该为零
		
		// 断言任务已经取回
		ProcessInstance processInstance = jbpmTemplate.getExecutionService().findProcessInstanceById(processInstanceId);
		//processInstance.isActive("outlay apply")
	}
	
	/*	
 	<process name="Withdraw" xmlns="http://jbpm.org/4.3/jpdl">
		<start g="62,37,129,179" name="start">
			<transition g="-53,-17" name="to outlay apply" to="outlay apply"/>
		</start>
		<task assignee="Jack" g="38,147,92,52" name="outlay apply">
			<transition g="-53,-17" name="to leader audit" to="leader audit">
				<event-listener class="com.examples.jbpm4.n3_cn_withdraw.WithdrawListener"/>
			</transition>
		</task>
		<task assignee="Alex" g="38,231,92,52" name="leader audit">
			<transition to="end"/>
		</task>
		<end g="61,315,48,48" name="end"/>
	</process>
	*/
	
}
