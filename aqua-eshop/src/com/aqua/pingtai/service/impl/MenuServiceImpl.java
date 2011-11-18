package com.aqua.pingtai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.interfaces.MenuDao;
import com.aqua.pingtai.entity.bean.authority.Menu;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.MenuService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("menuServiceImpl")
@Scope("singleton")
public class MenuServiceImpl implements MenuService {
	
	private static final Log log = LogFactory.getLog(MenuServiceImpl.class);
	
	@Resource(name="menuDaoImpl")
	private MenuDao menuDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	public MenuServiceImpl(){
		
	}

	public MenuDao getMenuDaoImpl() {
		return menuDaoImpl;
	}


	public void setMenuDaoImpl(MenuDao menuDaoImpl) {
		this.menuDaoImpl = menuDaoImpl;
	}


	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	/**
	 * 删除菜单
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteMenu(Menu menu) {
		try {
			daoHibernateBase.excuteHQL("delete from Menu where ids="+menu.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个菜单
	 * @param dictTypeids
	 * @return
	 */
	public Menu getOneMenu(Long menuIds) {
		Menu menu = null;
		try {
			menu = daoHibernateBase.findOneEntity(Menu.class, menuIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	/**
	 * 保存菜单
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveMenu(Menu menu) {
		User createUser = Context.getCurrentUser();
		Menu parentMenu = (Menu) daoHibernateBase.getHibernateTemplate().get(Menu.class, menu.getParentMenu().getIds());
		menu.setParentMenu(parentMenu);
		try {
			daoHibernateBase.saveOneEntity(menu, createUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询菜单列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryMenu(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(Menu.class, filterString, queryResult);
	}

	/**
	 * 更新菜单
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateMenu(Menu menu) {
		User updateUser = Context.getCurrentUser();
		Menu parentMenu = (Menu) daoHibernateBase.getHibernateTemplate().get(Menu.class, menu.getParentMenu().getIds());
		menu.setParentMenu(parentMenu);
		try {
			daoHibernateBase.updateOneEntity(menu, updateUser);
			log.info(updateUser.getUserName()+",更新菜单,菜单名:"+menu.getNames());
		} catch (Exception e) {
			log.error(updateUser.getUserName()+",更新菜单异常!!!菜单名:"+menu.getNames());
			e.printStackTrace();
		}
	}

	/**
	 * DWR获取父菜单节点
	 * @param menuType
	 * @param menuLeve
	 * @return
	 */
	public List<Menu> getParentMenuDwr(String menuTypeParamater, Long menuLevelParamater){
		List<Menu> menuList = null;
		Long menuLevel = menuLevelParamater-1l;
		try {
			menuList = daoHibernateBase.findManyEntity(Menu.class, " where menuType='"+menuTypeParamater+"' and menuLevel="+menuLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return menuList;
	}
	
	/**
	 * HQL查询Menu
	 * @param hql
	 * @return
	 */
	public List<Menu> getMenuByHQL(String hql){
		List<Menu> menuList = null;
		try {
			menuList = daoHibernateBase.findManyEntity(Menu.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}
	
}
