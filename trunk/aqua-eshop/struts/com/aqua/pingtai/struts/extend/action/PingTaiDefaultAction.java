package com.aqua.pingtai.struts.extend.action;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.QueryParameter;

public class PingTaiDefaultAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造函数
	 */
	public PingTaiDefaultAction(){
		
	}

	@Override
	public String execute() throws Exception {
		return "login";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionClassFullName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
