package com.aqua.pingtai.common;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.entity.bean.authority.Group;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.User;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * WEB上下文工具类
 */
public class Context {

	public static ServletContext servletContext;
	public static WebApplicationContext webApplicationContext;
	
	/**
	 * 得到request对象
	 * @return  request
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 得到Session对象
	 * @return Session
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 得到servletContent对象
	 * @return
	 */
	public static ServletContext getServletContext(){
		return servletContext; 
	}

	/**
	 * 得到spring上下文
	 * @return
	 */
	public static WebApplicationContext getWebApplicationContext() {
		//例子: HibernateDao dao = (HibernateDao)webApplicationContext.getBean("dao");
		return webApplicationContext;
	}
	
	/**
	 * 从spring配置文件中得到Bean的实例 
	 * @param beanName  名称
	 * @return  Bean 实例
	 */
	public static Object getSpringBean(String beanName){
		ServletContext servletContext = getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return wac.getBean(beanName);
	}
	
	/**
	 * 查询条件填充到Session 
	 * @param actionName 访问action 名称
	 * @param queryParameter 条件对象
	 */
	public static void setQueryParameter(String actionName,QueryParameter queryParameter) {
		ServletActionContext.getRequest().getSession().setAttribute(actionName, queryParameter);
	}
	
	/**
	 * 得到查询条件
	 * @param actionName 访问action 名称
	 * @return 条件对象
	 */
	public static QueryParameter getQueryParameter(String actionName) {
		Object queryParameterObject = ServletActionContext.getRequest().getSession().getAttribute(actionName);
		QueryParameter queryParameter = null;
		if(null!=queryParameterObject){
			queryParameter = (QueryParameter)queryParameterObject;
		}
		return queryParameter;
	}
	
	/**
	 * 删除查询条件
	 * @param actionName 访问action 名称
	 * @return 条件对象
	 */
	public static void removeQueryParameter(String actionName) {
		ServletActionContext.getRequest().getSession().removeAttribute(actionName);
	}
	
	/**
	 * 判断当前用户是否对某个按钮具有操作权限
	 * @param operator
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Boolean hasPrivilege(String operator){
		/*User currentUser = getCurrentUser();
		if(null!=currentUser){
			String selectOperator = "select * from pingtai_operator where url='"+operator+"' and ids in (select operatorIds from pingtai_roleoperator where roleIds in (select roleIds from pingtai_rolegroup where groupIds in ( select groupIds from pingtai_usergroup where userIds="+currentUser.getIds()+")))";
			OperatorDao operatorDaoImpl = (OperatorDaoImpl)getSpringBean("operatorDaoImpl");
			List<Operator> operatorList = operatorDaoImpl.selectUserOperator(selectOperator);
			if(operatorList.size()!=1){
				return false;
			}else{
				return true;
			}
		}
		return false;*/
		User currentUser = getCurrentUser();
		Set<Group> groupSet = currentUser.getGroupSet();
		OscacheFactory oscacheFactory = (OscacheFactory) Context.getSpringBean("oscacheFactory");
		for (Group group : groupSet) {
			List<Operator> operatorList = (List<Operator>) oscacheFactory.getObject(group.getIds()+"groupIds");
			for (Operator operators : operatorList) {
				String url = operators.getUrl();
				if(operator.equals(url)){
					return true;
				}
			}
		}
		return false;
		//return true;
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static User getCurrentUser(){
		Object userObject = getSession().getAttribute("currentUser");
		User currentUser = null;
		if(null!=userObject){
			currentUser = (User)userObject;
		}
		return currentUser;
	}
	
	/**
	 * 设置当前登录用户
	 * @return
	 */
	public static void setCurrentUser(User user){
		if(null!=user){
			getSession().setAttribute("currentUser", user);
		}
	}
	
	/**
	 * 获取上下文根路径
	 * @return
	 */
	public static String getContextPath(){
		return ServletActionContext.getRequest().getContextPath();
	}
	
	/**
	 * 获取上下文URL全路径
	 * @return
	 */
	public static String getContextAllPath(){
		return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath();
	}
}
