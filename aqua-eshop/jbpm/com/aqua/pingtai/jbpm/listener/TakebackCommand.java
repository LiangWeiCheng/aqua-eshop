package com.aqua.pingtai.jbpm.listener;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.api.task.Task;
import org.jbpm.internal.log.Log;
import org.jbpm.pvm.internal.history.model.HistoryActivityInstanceImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

@SuppressWarnings("serial")
public class TakebackCommand implements Command<Void> {
	
	private static final Log log = Log.getLog(TakebackListener.class.getName());
	
	private String processInstanceId;
	private String takebackTaskName;
	
	public TakebackCommand(String processInstanceId, String takebackTaskName) {
		this.processInstanceId = processInstanceId;
		this.takebackTaskName = takebackTaskName;
	}
	
	@Override
	public Void execute(Environment environment) throws Exception {
		ExecutionService executionService = environment.get(ExecutionService.class);
		TaskService taskService = environment.get(TaskService.class);
		HistoryService historyService = environment.get(HistoryService.class);
		Session session = environment.get(Session.class);

		Execution exec = executionService.findExecutionById(processInstanceId);
		Set<String> actNames = exec.findActiveActivityNames();

		// 合法性判断
		if (actNames == null || actNames.size() == 0) {
			String msg = "没有可以取回的活动";
			log.error(msg);
			throw new Exception(msg);
		}
		if (actNames.size() > 1) {
			String msg = "存在多个活动的节点";
			log.error(msg);
			throw new Exception(msg);
		}

		String actName = actNames.iterator().next();
		String withdrawPath = actName + " to " + takebackTaskName;
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId)
				.activityName(actName).list();
		for (Task task : tasks) {
			// 完成任务 - 暨取回任务。如果completeTask执行失败，则可以说明当前活动已非取回目的地活动的下一步了……
			taskService.completeTask(task.getId(), withdrawPath);
		}

		// 清除历史痕迹，即删除历史活动实例及其任务
		HistoryActivityInstanceImpl hActInst = (HistoryActivityInstanceImpl) historyService
				.createHistoryActivityInstanceQuery().activityName(actName)
				.executionId(processInstanceId).uniqueResult();
		session.delete(hActInst);

		return null;
	}

	/**
	 * jBPM4 原则上只能删除通过 taskService.newTask 生成的“孤儿”任务，<br/>
	 * 对于已经有上下文（即执行令牌）的任务则需要做如下“违规”处理，才能删除。
	 * 
	 * @deprecated
	 * @param task
	 */
	public void deleteTask(Task task, TaskService taskService) {
		((TaskImpl) task).setExecution((Execution) null);
		((TaskImpl) task).setExecutionDbid(null);
		taskService.saveTask(task);
		taskService.deleteTaskCascade(task.getId());
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTakebackTaskName() {
		return takebackTaskName;
	}

	public void setTakebackTaskName(String takebackTaskName) {
		this.takebackTaskName = takebackTaskName;
	}

}
