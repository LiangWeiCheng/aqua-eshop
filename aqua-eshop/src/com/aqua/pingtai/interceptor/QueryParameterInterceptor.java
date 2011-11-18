package com.aqua.pingtai.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.QueryParameter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings({"serial"})
public class QueryParameterInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if (action instanceof BaseAction) {
			BaseAction baseAction = (BaseAction)action;
			Map<String, Object> requestParameters = invocation.getInvocationContext().getParameters();
			QueryParameter queryParameter = fetchQueryParameter(requestParameters);
			baseAction.setQueryParameter(queryParameter);
		}
		return invocation.invoke();
	}
	
	/**
	 * 把request中的参数用键值对封装
	 * @param request
	 * @param requestParameterNames
	 * @return
	 */
	private QueryParameter fetchQueryParameter(Map<String, Object> requestParameterNames) {
		QueryParameter result = new QueryParameter();
		List<String> deleteKey = new ArrayList<String>();
		
		for (String key : requestParameterNames.keySet()) {
			if (key.startsWith("_query")) {
				String parameterName = key.substring(7);
				String[] parameterValue = (String[]) requestParameterNames.get(key);
				result.addParameter(parameterName.trim(), parameterValue[0].trim());
				deleteKey.add(key);
			}
		}
		
		for (String parameterKey : deleteKey) {
			requestParameterNames.remove(parameterKey);
		}
		
		return result;
	}

}
