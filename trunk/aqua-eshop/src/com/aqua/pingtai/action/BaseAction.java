package com.aqua.pingtai.action;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.utils.OpenPropertiesFile;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, Serializable{
	@Resource
	DaoHibernateBase daoHibernateBase;
	
	public Boolean isSearch;//是否查询
	public QueryParameter queryParameter;//封装查询参数
	public QueryResult queryResult;//查询结果
	public static Long queryResultCountPingTai = 15l;//平台分页(每页显示记录数10)
	
	public HttpServletRequest request;
	public HttpServletResponse response;
	
	public String returnPageURL;
	public String returnParamater;
	public String returnParamater2;
	
	public BaseAction(){
		
	}
	
	//把查询参数对象拼接成SQL
	protected abstract String makeFilterString(QueryParameter queryParameter);
	
	//得到Action类的全名(包括所在的包名)
	protected abstract String getActionClassFullName();
	
	//去指定的URL地址
	public String goToUrl(){
		return "dispatcher";
	}
	
	//实体列表初始化
	protected String initQueryList(boolean isRowFilter) {
		if(null!=isSearch && isSearch.booleanValue()){//点击查询
			Context.setQueryParameter(getActionClassFullName(), queryParameter);
		}else if(null!=isSearch && !isSearch.booleanValue()){//非查询
			queryParameter = Context.getQueryParameter(getActionClassFullName());
			if(null!=queryParameter){
				Context.removeQueryParameter(getActionClassFullName());
			}
			queryParameter = new QueryParameter();
		}else{//查询分页
			queryParameter = Context.getQueryParameter(getActionClassFullName());//获取查询条件
			if(null == queryParameter){
				queryParameter = new QueryParameter();
			}
		}
		
		initQueryResult(BaseAction.queryResultCountPingTai);//初始化查询分页参数
		
		StringBuffer sb = new StringBuffer(" where ");
		sb.append(makeFilterString(queryParameter));//过滤HQL
		
		//暂时没用上,都是查的有效数据
		if(sb.toString().trim().equals("where")){
			sb.append(" valid='youXiao' ");
		}else{
			sb.append(" and valid='youXiao' ");
		}
		
		//行级数据过滤
		if(isRowFilter){
			String createUserStr = filterUser();
			if(!createUserStr.equals("")){
				sb.append(" and ").append("(").append(createUserStr).append(")");
			}
		}
		
		return sb.toString();
	}
	
	//初始化查询分页参数
	protected void initQueryResult(Long queryResultCountType) {
		if(null==queryResult){
			queryResult = new QueryResult();
		}
		queryResult.setMaxResult(queryResultCountType);
		String currentRequest = request.getRequestURI();
		queryResult.setUrl(currentRequest);
	}
	
	protected String filterUser(){
		User currentUser = Context.getCurrentUser();
		StringBuffer createUser = new StringBuffer();
		
		//开始处理行级数据过滤:1.按部门级别查询 
		String userOperatorDataLevel = currentUser.getUserOperatorDataLevel();
		if(userOperatorDataLevel.equals("userOperatorDataLevel_yongHuJi")){//用户级
			createUser.append(" creator.ids=").append(currentUser.getIds()).append(" or ");
		}else if(userOperatorDataLevel.equals("userOperatorDataLevel_buMenJi")){//部门级
//			DaoHibernateBase daoHibernateBase = (DaoHibernateBase)Context.getSpringBean("daoHibernateBase");
			List<User> userList = null;
			try {
				userList = daoHibernateBase.findManyEntity(User.class, " where department.ids="+currentUser.getDepartment().getIds());
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (User user : userList) {
				createUser.append(" creator.ids=").append(user.getIds()).append(" or ");
			}
		}
		
		//开始处理行级数据过滤:2.按人员查询
		Set<User> rowUserSet = currentUser.getRowUserSet();
		for (User user : rowUserSet) {
			createUser.append(" creator.ids=").append(user.getIds()).append(" or ");
		}
		
		String createUserStr = createUser.toString().trim();
		if(!createUserStr.equals("")){
			createUserStr = createUserStr.substring(0, createUserStr.length()-2);
		}
		
		return createUserStr;
	}
	
	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	public OpenPropertiesFile getOpenPropertiesFile() {
		return OpenPropertiesFile.getInstance();
	}

	public QueryParameter getQueryParameter() {
		return queryParameter;
	}

	public void setQueryParameter(QueryParameter queryParameter) {
		this.queryParameter = queryParameter;
	}

	public Boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public QueryResult getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
	}

	public static Long getQueryResultCountPingTai() {
		return queryResultCountPingTai;
	}

	public static void setQueryResultCountPingTai(Long queryResultCountPingTai) {
		BaseAction.queryResultCountPingTai = queryResultCountPingTai;
	}

	public String getReturnPageURL() {
		return returnPageURL;
	}

	public void setReturnPageURL(String returnPageURL) {
		this.returnPageURL = returnPageURL;
	}

	public String getReturnParamater() {
		return returnParamater;
	}

	public void setReturnParamater(String returnParamater) {
		this.returnParamater = returnParamater;
	}

	public String getReturnParamater2() {
		return returnParamater2;
	}

	public void setReturnParamater2(String returnParamater2) {
		this.returnParamater2 = returnParamater2;
	}

    /**
     * 返回"success"字符串
     * @return
     */
    public String getSuccess() {
    	return SUCCESS;
    }

    /**
     * 返回"error"字符串
     * @return
     */
    public String getError() {
    	return SUCCESS;
    }
}
