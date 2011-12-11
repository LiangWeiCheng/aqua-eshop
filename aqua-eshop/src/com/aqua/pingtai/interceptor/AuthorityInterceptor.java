package com.aqua.pingtai.interceptor;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.entity.bean.authority.SysLog;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.SysLogService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 进行权限认证拦截
 * 1.拦截是否已经登录
 * 2.拦截多访问的URL是否具有操作权限
 * 认证失败直接返回到登录页面
 */
public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	private static final Log logger = LogFactory.getLog(AuthorityInterceptor.class);
	
	//静态oscacheFactory
	public static OscacheFactory oscacheFactory ;
	
	//
	@Resource
	private SysLogService sysLogServiceImpl;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map<?,?> session = ctx.getSession();
		User user = (User)session.get("currentUser");
		
		String requestUrl = Context.getRequest().getRequestURI();//用户请求URL
		
		if (user != null && user.getUserClass().equals("userClass_houTai")){//*********注意:userClass_houTai***********
			
			int lastIndexOne = requestUrl.lastIndexOf("/");//倒数第一个/
			String subRequestUrl = requestUrl.substring(0, lastIndexOne);
			int lastIndexTwo = subRequestUrl.lastIndexOf("/");//倒数第二个/
			
			String returnPageURL = Context.getRequest().getParameter("returnPageURL");
			if(null!=returnPageURL && !returnPageURL.trim().equals("")){
				//returnPageURL应该是:/WEB-INF/jsp/pingtai/role/roleAdd.jsp
				boolean hasPrivilege = Context.hasPrivilege(returnPageURL);
				if(hasPrivilege){
					String desc = user.getUserName() + "(id=" + user.getIds() + ")请求[" + requestUrl + "]";
					log(user, SysLog.SUCCESS_TYPE, Context.getOperatorName(returnPageURL), desc);
					return invocation.invoke();
				}else{
					String desc = user.getUserName() + "(id=" + user.getIds() + ")没有[" + returnPageURL + "]操作权限!";
					log(user, SysLog.ERROR_TYPE, SysLog.USER_AUTHORITY_ERROR_TITLE, desc);
					Context.getRequest().setAttribute("operatorMessage", "您没有此操作权限!");
					return "operatorMessage";
				}
			}else{
				//hasUrl应该是:/pingTai/rolePingTaiAction!roleOperatorView.action
				String hasUrl = requestUrl.substring(lastIndexTwo);
				boolean hasPrivilege = Context.hasPrivilege(hasUrl);
				/*boolean hasPrivilege = false;
				Object operatorObj = oscacheFactory.getObject(hasUrl);
				if(operatorObj != null){
					hasPrivilege = true;
	            }*/
				if(hasPrivilege){
					String desc = user.getUserName() + "(id=" + user.getIds() + ")请求[" + hasUrl + "]";
					log(user, SysLog.SUCCESS_TYPE, Context.getOperatorName(hasUrl), desc);
					return invocation.invoke();
				}else{
					String desc = user.getUserName() + "(id=" + user.getIds() + ")没有[" + hasUrl + "]操作权限!";
					log(user, SysLog.ERROR_TYPE, SysLog.USER_AUTHORITY_ERROR_TITLE, desc);
					Context.getRequest().setAttribute("operatorMessage", "您没有此操作权限!");
					return "operatorMessage";
				}
			}
		}else{
			log(user, SysLog.ERROR_TYPE, SysLog.USER_TYPE_ERROR_TITLE, "session中不存在user或用户为非后台用户");
			ActionContext.getContext().getSession().remove("currentUser");
			return "login";//跳转到后台登陆首页
		}
	}

	public static OscacheFactory getOscacheFactory() {
		return oscacheFactory;
	}

	public static void setOscacheFactory(OscacheFactory oscacheFactory) {
		AuthorityInterceptor.oscacheFactory = oscacheFactory;
	}

	/**
	 * 同时记录数据库日志及文件日志
	 * @param user
	 * @param types
	 * @param titles
	 * @param description
	 */
	private void log(User user, String types, String titles, String description) {
		// 创建系统日志
		Date now = new Date();
		SysLog entity = new SysLog();
		entity.setCreatedDate(now);
		entity.setModifiedDate(now);
		// 记录操作用户
		if (user != null) {
			entity.setCreator(user);
			entity.setModified(user);
		} else {// 如果session中不存在user则创建记录日志的临时用户
			User tempUser = new User();
			tempUser.setIds(0L);
			entity.setCreator(tempUser);
			entity.setModified(tempUser);
		}
		entity.setTypes(types);
		entity.setTitles(titles);
		entity.setDescription(description);
		sysLogServiceImpl.save(entity);
		// 记录到日志文件
		logger.info(entity.toString());
	}
}
