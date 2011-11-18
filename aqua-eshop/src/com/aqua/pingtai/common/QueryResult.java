package com.aqua.pingtai.common;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class QueryResult implements Serializable {
	
	private Long recordCount;//记录总数
	private List<?> resultList;//记录集合
	
	private Long firstResult;//开始处
	private Long maxResult;//取出数
	
	private Long pageCount;//总页数
	private Long currentPage;//当前第几页
	private Long currentPageCount;//当前页记录数量
	
	private String url;
	
	private boolean isFirst;//是否第一页
	private boolean isLast;//是否最后一页

	public QueryResult() {
		this.firstResult = 0l;
		this.currentPage = 1l;
	}

	/**
	 * 分页计算
	 */
	public void compute(){
		if((this.recordCount%this.maxResult)==0){
			this.pageCount = this.recordCount/this.maxResult;//计算多少页
		}else{
			this.pageCount = this.recordCount/this.maxResult+1l;//计算多少页
		}
		
		this.currentPageCount = Long.valueOf(String.valueOf(this.resultList.size()));//当前页记录数
		
		if(currentPage.equals(1l)){
			this.isFirst = true;
		}else{
			this.isFirst = false;
		}
		
		if(currentPage.equals(pageCount)){
			this.isLast = true;
		}else{
			this.isLast = false;
		}
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public Long getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Long firstResult) {
		this.firstResult = firstResult;
	}

	public Long getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(Long maxResult) {
		this.maxResult = maxResult;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public Long getCurrentPageCount() {
		return currentPageCount;
	}

	public void setCurrentPageCount(Long currentPageCount) {
		this.currentPageCount = currentPageCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
