package com.aqua.material.dao;

import java.util.List;

import com.aqua.pingtai.common.QueryResult;

public interface MaterialDao{
	
	public  void saveObject(Object object);
	
	public void saveCommitObject(Object object);
	
	public  void updateObject(Object object);
	
	public void updateCommitObject(Object object);
	
	public  void saveOrUpdate(Object object);
	
	public  <T> T findOneObject(Class<T> entityClass, Long id);
	
	public  <T> List<T> findObjectByFilter(Class<T> entityClass, String filterString);
	
	public  <T> T findObjectByName(Class<T> entityClass, String name);
	
	public  void deleteObject(Object object);
	
	public  void deleteCommitObject(Object object);
	
	public  <T> QueryResult findSplitPageByOrder(Class<T> entityClass, String filterString, String orderByString, QueryResult queryResult);
	
}
