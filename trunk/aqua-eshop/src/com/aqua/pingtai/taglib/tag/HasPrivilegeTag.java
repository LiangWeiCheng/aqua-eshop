package com.aqua.pingtai.taglib.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aqua.pingtai.taglib.bean.HasPrivilegeBean;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;


@SuppressWarnings("serial")
public class HasPrivilegeTag extends ComponentTagSupport {

	private String operator;
	private String htmlSrc;
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		return new HasPrivilegeBean(stack);
	}

    protected void populateParams() {
    	HasPrivilegeBean hasPrivilegeBean = (HasPrivilegeBean) getComponent();
    	hasPrivilegeBean.setOperator(((String)findValue(operator, String.class)).trim());
    	hasPrivilegeBean.setHtmlSrc(((String)findValue(htmlSrc, String.class)).trim());
    }

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getHtmlSrc() {
		return htmlSrc;
	}

	public void setHtmlSrc(String htmlSrc) {
		this.htmlSrc = htmlSrc;
	}

}
