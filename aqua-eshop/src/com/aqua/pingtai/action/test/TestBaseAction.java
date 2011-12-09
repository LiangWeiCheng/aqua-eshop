package com.aqua.pingtai.action.test;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.UserService;

import org.jbpm.api.ProcessEngine;

@SuppressWarnings("serial")
public class TestBaseAction extends BaseAction {
	
	@Resource(name="processEngine")
	private ProcessEngine processEngine;
	
	@Resource(name="userServiceImpl")
	private UserService userServiceImpl;
	
	private Long userIds;

	public TestBaseAction(){
		
	}
	
	//http://localhost:8088/SSHD/pingTai/testBaseAction!test.action
	public String test(){
		//http://localhost:8088/SSHD/admin/testBaseAction!test.action?isSearch=true&_query.aa=aa&_query.bb=bb
		//http://localhost:8088/SSHD/admin/testBaseAction!test.action
		if(null!=isSearch && isSearch.booleanValue()){//点击查询
			Context.setQueryParameter(getActionClassFullName(), queryParameter);//保存查询条件
			System.out.println("111"+getQueryParameter().getParameter("aa"));
			System.out.println("111"+getQueryParameter().getParameter("bb"));
		}else{
			queryParameter = Context.getQueryParameter(getActionClassFullName());//获取查询条件
			System.out.println("222"+getQueryParameter().getParameter("aa"));
			System.out.println("222"+getQueryParameter().getParameter("bb"));
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/queryParameterTest.jsp";
		return "dispatcher";
	}
	
	//http://localhost:8088/SSHD/pingTai/testBaseAction!save.action
	public String save(){
		User user = new User();
		user.setUserName("用户名");
		user.setPassWord("密码");
		user.setUserClass("houTai");
		userServiceImpl.saveUser(user);
		returnPageURL = "/WEB-INF/jsp/pingtai/saveTest.jsp";
		return "dispatcher";
	}
	
	//http://localhost:8088/SSHD/pingTai/testBaseAction!query.action?userIds=1
	public String query(){
		
		User user = userServiceImpl.getUser(userIds);
		System.out.println(user.getUserName());
		
		user.setUserName("newName33");
		userServiceImpl.updateUser(user);
		
		User user2 = userServiceImpl.getUser(userIds);
		System.out.println(user2.getUserName());
		
		returnPageURL = "/WEB-INF/jsp/pingtai/queryTest.jsp";
		return "dispatcher";
	}
	
	/**
	 * compass 搜索
	 * @return
	 */
	//http://localhost:8088/SSHD/pingTai/testBaseAction!query2.action
//	public String query2(){
//		List<User> list = userServiceImpl.queryUser("*test*", new QueryResult());
//		for (User user : list) {
//			System.out.println(user.getUserName());
//		}
//		returnPageURL = "/WEB-INF/jsp/pingtai/queryTest.jsp";
//		return "dispatcher";
//	}
	
	//http://localhost:8088/SSHD/pingTai/testBaseAction!query3.action
	public String query3(){
		initQueryResult(BaseAction.queryResultCountPingTai);//初始化查询分页参数
		try {
			userServiceImpl.splitPageQueryUser("", queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/splitPageTest.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.test.TestBaseAction.java";
	}
	
	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		String xxx = queryParameter.getParameter("xxx");
		String yyy = queryParameter.getParameter("yyy");
		String result = "";
		if (xxx != null && !xxx.equals("")) {
			result += " xxx like '" + xxx + "%' and";
		}
		if (yyy != null && !yyy.equals("")) {
			result += " yyy like '%" + yyy + "%'";
		}
		if (result.endsWith(" and")) {
			result = result.substring(0,result.length() - 4);
		}
		return result;
	}

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public Long getUserIds() {
		return userIds;
	}

	public void setUserIds(Long userIds) {
		this.userIds = userIds;
	}

}
