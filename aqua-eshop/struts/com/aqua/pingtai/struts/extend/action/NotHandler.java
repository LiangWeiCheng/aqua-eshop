package com.aqua.pingtai.struts.extend.action;

import org.apache.struts2.dispatcher.ServletDispatcherResult;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.UnknownHandler;
import com.opensymphony.xwork2.XWorkException;
import com.opensymphony.xwork2.config.entities.ActionConfig;

public class NotHandler implements UnknownHandler {

	/**
	 * @param namespace 用户请求的Action所在的命名空间。
	 * @param actionName 用户请求的Action的名字。
	 * @return 该Action最后生成的ActionConfig，可以返回null。 
	 */
	public ActionConfig handleUnknownAction(String namespace, String actionName)
			throws XWorkException {
		//直接配置默认action就OK,无需此处配置
		return null;
	}

	/**
	 * @param action 用户请求的Action对象。
	 * @param methodName 用户请求的Action的方法名
	 * @return 该Action的该方法处理后返回的Result。 
	 */
	public Object handleUnknownActionMethod(Object action, String methodName)
			throws NoSuchMethodException {
		System.out.println();
		return null;
	}

	/**
	 * @param actionContext 该Result所在ActionContext。
	 * @param actionName 该Result所在的Action名。
	 * @param actionConfig 该Result所在ActionContext。
	 * @param resultCode 该Result所对应的逻辑视图字符串。
	 * @return 将要被处理的结果，可以返回null
	 */
	public Result handleUnknownResult(ActionContext actionContext,
			String actionName, ActionConfig actionConfig,
			String resultCode) throws XWorkException {
		actionContext.put("action" , actionName);
		actionContext.put("result" , resultCode);
		//总是转入unknownResult.jsp页面
		return new ServletDispatcherResult("/WEB-INF/jsp/error/notResult.jsp");
	}

}
