package com.aqua.pingtai.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.*;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.entity.bean.authority.User;

/**
 * 进行权限认证拦截
 * 1.拦截是否已经登录
 * 2.拦截多访问的URL是否具有操作权限
 * 认证失败直接返回到登录页面
 */
public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	//private static final Log log = LogFactory.getLog(AuthorityInterceptor.class);
	
	//静态oscacheFactory
	public static OscacheFactory oscacheFactory ;
	
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
					return invocation.invoke();
				}else{
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
					return invocation.invoke();
				}else{
					Context.getRequest().setAttribute("operatorMessage", "您没有此操作权限!");
					return "operatorMessage";
				}
			}
		}else{
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

}
