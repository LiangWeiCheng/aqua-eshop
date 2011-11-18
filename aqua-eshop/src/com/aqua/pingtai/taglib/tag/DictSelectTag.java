package com.aqua.pingtai.taglib.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aqua.pingtai.taglib.bean.DictSelectBean;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class DictSelectTag extends ComponentTagSupport {

	private String nodeId;
	private String nodeClass;
	private String nodeStyle;
	private String selectName;
	private String dictTypeNumbers;
	private String dictNumbers;
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		return new DictSelectBean(stack);
	}

    protected void populateParams() {
    	DictSelectBean dictSelectBean = (DictSelectBean) getComponent();
    	dictSelectBean.setNodeId(((String)findValue(nodeId, String.class)).trim());
    	dictSelectBean.setNodeClass(((String)findValue(nodeClass, String.class)).trim());
    	dictSelectBean.setNodeStyle(((String)findValue(nodeStyle, String.class)).trim());
    	dictSelectBean.setSelectName(((String)findValue(selectName, String.class)).trim());
    	dictSelectBean.setDictTypeNumbers(((String)findValue(dictTypeNumbers, String.class)).trim());
    	dictSelectBean.setDictNumbers(((String)findValue(dictNumbers, String.class)).trim());
    }

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getSelectName() {
		return selectName;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	public String getDictTypeNumbers() {
		return dictTypeNumbers;
	}

	public void setDictTypeNumbers(String dictTypeNumbers) {
		this.dictTypeNumbers = dictTypeNumbers;
	}

	public String getDictNumbers() {
		return dictNumbers;
	}

	public void setDictNumbers(String dictNumbers) {
		this.dictNumbers = dictNumbers;
	}

	public String getNodeClass() {
		return nodeClass;
	}

	public void setNodeClass(String nodeClass) {
		this.nodeClass = nodeClass;
	}

	public String getNodeStyle() {
		return nodeStyle;
	}

	public void setNodeStyle(String nodeStyle) {
		this.nodeStyle = nodeStyle;
	}

	
	
}
