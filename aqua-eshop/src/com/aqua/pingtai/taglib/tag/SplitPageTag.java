package com.aqua.pingtai.taglib.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.taglib.bean.SplitPageBean;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class SplitPageTag extends ComponentTagSupport {

	private String recordCount;//记录总数
	
	private String firstResult;//开始处
	private String maxResult;//取出数
	
	private String pageCount;//总页数
	private String currentPage;//当前第几页
	private String currentPageCount;//当前页记录数量
	
	private String url;
	
	private String isFirst;//是否第一页
	private String isLast;//是否最后一页
	
	private String urlParamater;//参数
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		return new SplitPageBean(stack);
	}

    protected void populateParams() {
    	SplitPageBean splitPageBean = (SplitPageBean) getComponent();
    	QueryResult queryResult = (QueryResult)Context.getRequest().getAttribute("queryResult");
    	splitPageBean.setRecordCount(queryResult.getRecordCount());
    	splitPageBean.setFirstResult(queryResult.getFirstResult());
    	splitPageBean.setMaxResult(queryResult.getMaxResult());
    	splitPageBean.setPageCount(queryResult.getPageCount());
    	splitPageBean.setCurrentPage(queryResult.getCurrentPage());
    	splitPageBean.setCurrentPageCount(queryResult.getCurrentPageCount());
    	splitPageBean.setUrl(queryResult.getUrl());
    	splitPageBean.setFirst(queryResult.isFirst());
    	splitPageBean.setLast(queryResult.isLast());
    	splitPageBean.setUrlParamater((String)findValue(urlParamater, String.class));
    }

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPageCount() {
		return currentPageCount;
	}

	public void setCurrentPageCount(String currentPageCount) {
		this.currentPageCount = currentPageCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}

	public String getUrlParamater() {
		return urlParamater;
	}

	public void setUrlParamater(String urlParamater) {
		this.urlParamater = urlParamater;
	}


}
