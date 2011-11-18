package com.aqua.pingtai.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.cache.SystemParamaterCache;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.interceptor.AuthorityInterceptor;
import com.aqua.pingtai.taglib.bean.DictReadBean;
import com.aqua.pingtai.taglib.bean.DictSelectBean;
import com.aqua.pingtai.taglib.bean.HasPrivilegeBean;

import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextLoaderListener implements ServletContextListener {
	
	//销毁
	public void contextDestroyed(ServletContextEvent event) {
		//ServletContext context = event.getServletContext();
		//context.setAttribute("", null);
	}
	
	//启动加载
	public void contextInitialized(ServletContextEvent event) {
		
		//1.注入Context对象
		ServletContext context = event.getServletContext();
		Context.servletContext = context;
		Context.webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		
		//查询oscacheFactory
		OscacheFactory oscacheFactory = (OscacheFactory) Context.getSpringBean("oscacheFactory");
		
		//2.注入SystemParamaterCache对象
		SystemParamaterCache.oscacheFactory = oscacheFactory;
		SystemParamaterCache.daoHibernateBase = (DaoHibernateBase)Context.getSpringBean("daoHibernateBase");
		SystemParamaterCache.daoJdbcBase = (DaoJdbcBase)Context.getSpringBean("daoJdbcBase");
		
		//3.缓存所有字典
		SystemParamaterCache.cacheAllDict();
		
		//4.缓存所有操作
		SystemParamaterCache.cacheAllOperator();
		
		//5.注入DictSelectBean对象
		DictSelectBean.oscacheFactory = oscacheFactory;
		
		//6.注入DictReadBean对象
		DictReadBean.oscacheFactory = oscacheFactory;
		
		//7.注入HasPrivilegeBean对象
		HasPrivilegeBean.oscacheFactory = oscacheFactory;
		
		//8.注入AuthorityInterceptor对象
		AuthorityInterceptor.oscacheFactory = oscacheFactory;
		
		//9.缓存组拥有的操作
		SystemParamaterCache.cacheAllGroupOperator();
	}

}
