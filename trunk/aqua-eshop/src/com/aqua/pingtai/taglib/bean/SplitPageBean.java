package com.aqua.pingtai.taglib.bean;

import java.io.IOException;
import java.io.Writer;

import com.aqua.pingtai.common.Context;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class SplitPageBean extends Component {

	private Long recordCount;//记录总数
	
	private Long firstResult;//开始处
	private Long maxResult;//取出数
	
	private Long pageCount;//总页数
	private Long currentPage;//当前第几页
	private Long currentPageCount;//当前页记录数量
	
	private String url;
	
	private boolean isFirst;//是否第一页
	private boolean isLast;//是否最后一页
	
	private String urlParamater;//参数
	
	public SplitPageBean(ValueStack stack) {
		super(stack);
	}

    public boolean start(Writer writer) {
    	boolean result = super.start(writer);
    	if(result){
    		String contentPath = Context.getRequest().getContextPath();
    		try {
                StringBuilder sb = new StringBuilder();
                
                sb.append("第").append(recordCount==0l? 0:currentPage).append("页").append("&nbsp;&nbsp;");
                sb.append("共").append(pageCount).append("页").append("&nbsp;&nbsp;");
                
                sb.append("当前").append(currentPageCount).append("条").append("&nbsp;&nbsp;");
                sb.append("共").append(recordCount).append("条").append("&nbsp;&nbsp;&nbsp;&nbsp;");
                
                if(isFirst || recordCount==0l){
                	sb.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_54.gif'/>").append("&nbsp;");//第一页
                }else{
                	 sb.append("<a href='").append(url).append("?queryResult.currentPage=1").append(urlParamater).append("'>")
                	   .append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_54.gif'/>")//第一页
                	   .append("</a>").append("&nbsp;");
                }
                
                if(recordCount==0l){
                	sb.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_56.gif'/>").append("&nbsp;");//上一页
                	sb.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_58.gif'/>").append("&nbsp;");//下一页
                }else{
                	if(isFirst){
                		sb.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_56.gif'/>").append("&nbsp;");//上一页
                	}else{
                    	sb.append("<a href='").append(url).append("?queryResult.currentPage=").append(currentPage!=1l?currentPage-1l:currentPage).append(urlParamater).append("'>")
                    	  .append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_56.gif'/>")//上一页
                        .append("</a>").append("&nbsp;");
                	}
                	if(isLast){
                		sb.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_58.gif'/>").append("&nbsp;");//下一页
                	}else{
                		sb.append("<a href='").append(url).append("?queryResult.currentPage=").append(currentPage!=pageCount?currentPage+1l:currentPage).append(urlParamater).append("'>")
                	  	  .append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_58.gif'/>")//下一页
                	  	  .append("</a>").append("&nbsp;");
                	}
                }
                
                if(isLast || recordCount==0l){
                	sb.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_60.gif'/>").append("&nbsp;");//最后一页
                }else{
                	 sb.append("<a href='").append(url).append("?queryResult.currentPage=").append(pageCount).append(urlParamater).append("'>")
                	   		.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_60.gif'/>")//最后一页
                	   .append("</a>").append("&nbsp;");
                }
                
                sb.append("&nbsp;&nbsp;");
                
                sb.append("<select id=\"toPage\" name=\"toPage\">");
                for (Long i = 0l; i < pageCount; i++) {
                	if((i+1l)!=currentPage){
                		sb.append("<option value=\"").append(i+1l).append("\">").append(i+1l).append("</option>");
                	}else{
                		sb.append("<option value=\"").append(i+1l).append("\" selected=\"selected\">").append(i+1l).append("</option>");
                	}
				}
                sb.append("</select>");
                sb.append("<img border=\"0\" src='"+contentPath+"/images/pingTai/main_62.gif' onclick=\"toPage('").append(url).append("');\" ");
                
                /*
                if(pageCount<=10l){
                	for (Long i = 0l; i < pageCount; i++) {
                		sb.append("<a href='").append(url).append("?queryResult.currentPage=").append(i+1).append("'>")
                			.append("[").append(i+1).append("]")
                			.append("</a>");
                	}
                }else{
                	Long startIndex = currentPage-4l;
                	Long leaveCount = pageCount-currentPage;
                	Long endIndex = 0l;
                	if(leaveCount==0l){
                		endIndex = currentPage;
                	}else if(leaveCount<=5l){
                		endIndex = currentPage+leaveCount;
                	}else if(leaveCount>5l){
                		endIndex = currentPage+5l;
                	}
                	if(startIndex<0l){
                		endIndex = endIndex-startIndex+1;
                		startIndex = 1l;
                	}
                	for (Long i = startIndex-1l; i < endIndex; i++) {
                		sb.append("<a href='").append(url).append("?queryResult.currentPage=").append(i+1).append("'>")
            			.append("[").append(i+1).append("]")
            			.append("</a>");
                	}
                }
                */
                writer.write(sb.toString());
            }catch(IOException ex){
                ex.printStackTrace();
            }
    	}
        return result;
    }

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
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

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getCurrentPageCount() {
		return currentPageCount;
	}

	public void setCurrentPageCount(Long currentPageCount) {
		this.currentPageCount = currentPageCount;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlParamater() {
		return urlParamater;
	}

	public void setUrlParamater(String urlParamater) {
		this.urlParamater = urlParamater;
	}

	
}
