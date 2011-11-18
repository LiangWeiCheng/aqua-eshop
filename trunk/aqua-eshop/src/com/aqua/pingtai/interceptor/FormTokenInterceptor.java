package com.aqua.pingtai.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.*;

import com.aqua.pingtai.common.Context;

/**
 * 表单重复提交验证
 */
@SuppressWarnings({"serial"})
public class FormTokenInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		
		Map<?,?> session = ctx.getSession();
		String tokenSession = (String)session.get("formTokenSession");
		String tokenRequest = null;
		
		Map<String, Object> requestParameters = invocation.getInvocationContext().getParameters();
		for (String key : requestParameters.keySet()) {
			if (key.equals("formToken")) {
				String[] parameterValue = (String[]) requestParameters.get("formToken");
				tokenRequest = parameterValue[0];
				break;
			}
		}
		
		if(tokenRequest==null){//1.没有token
			return invocation.invoke();
		}else if (null==tokenSession || !tokenSession.equals(tokenRequest)){//2.不重复
			ctx.getSession().put("formTokenSession", tokenRequest.trim());
			return invocation.invoke();
		}else if(tokenSession.equals(tokenRequest)){//3.重复
			Context.getRequest().setAttribute("operatorMessage", "请不要重复提交表单数据!");
			return "operatorMessage";
		}
		return "operatorMessage";
	}

}
