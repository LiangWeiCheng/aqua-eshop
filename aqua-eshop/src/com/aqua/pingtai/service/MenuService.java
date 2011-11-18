package com.aqua.pingtai.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.Menu;

@Transactional
public interface MenuService {
	
	/**
	 * 获取一个菜单
	 * @param dictTypeids
	 * @return
	 */
	public Menu getOneMenu(Long menuIds);
	
	/**
	 * 查询菜单列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryMenu(String filterString, QueryResult queryResult) throws Exception;
	
	/**
	 * 保存菜单
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveMenu(Menu menu);
	
	/**
	 * 更新菜单
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateMenu(Menu menu);
	
	/**
	 * 删除菜单
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteMenu(Menu menu);
	
	/**
	 * DWR获取父菜单节点
	 * @param menuType
	 * @param menuLeve
	 * @return
	 */
	public List<Menu> getParentMenuDwr(String menuType, Long menuLeve);
	
	/**
	 * HQL查询Menu
	 * @param hql
	 * @return
	 */
	public List<Menu> getMenuByHQL(String hql);
	
}