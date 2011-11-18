package com.aqua.pingtai.action;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Menu;
import com.aqua.pingtai.service.MenuService;

@SuppressWarnings({"serial"})
public class MenuAction extends BaseAction {
	
	@Resource(name="menuServiceImpl")
	private MenuService menuServiceImpl;
	
	private Menu menu;
	private List<Menu> menuList;
	
	/**
	 * 保存菜单
	 * @return
	 */
	public String saveMenu(){
		if(null!=menu){
			menuServiceImpl.saveMenu(menu);
		}
		returnPageURL = "/pingTai/menuPingTaiAction!menuList.action";
		return "redirect";
	}
	
	/**
	 * 查看菜单
	 * @return
	 */
	public String viewMenu(){
		if(null!=menu && 0l!=menu.getIds()){
			menu = menuServiceImpl.getOneMenu(menu.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/menu/menuView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新菜单
	 * @return
	 */
	public String toUpdateMenu(){
		if(null!=menu && 0l!=menu.getIds()){
			menu = menuServiceImpl.getOneMenu(menu.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/menu/menuUpdate.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新菜单
	 * @return
	 */
	public String updateMenu(){
		if(null!=menu && null!=menu.getIds() && 0l!=menu.getIds()){
			menuServiceImpl.updateMenu(menu);
		}
		returnPageURL = "/pingTai/menuPingTaiAction!menuList.action";
		return "redirect";
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	public String deleteMenu(){
		if(null!=menu && null!=menu.getIds() && 0l!=menu.getIds()){
			menu = menuServiceImpl.getOneMenu(menu.getIds());
			if(menu.getMenuSet().size()!=0){
				Context.getRequest().setAttribute("operatorMessage", "请先删除拥有的子菜单!");
				return "operatorMessage";
			}
			menuServiceImpl.deleteMenu(menu);
		}
		returnPageURL = "/pingTai/menuPingTaiAction!menuList.action";
		return "redirect";
	}

	/**
	 * 分页显示菜单
	 * @return
	 */
	public String menuList(){
		menuList = menuServiceImpl.getMenuByHQL(" where menuType='menuType_houTai' ");
		returnPageURL = "/WEB-INF/jsp/pingtai/menu/menuList.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.MenuAction";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		StringBuffer sb = new StringBuffer();
		
		String names = queryParameter.getParameter("names");
		if(null!=names && !names.equals("")){
			sb.append(" names like '%").append(names.trim()).append("%'").append(" and ");
		}
		
		String description = queryParameter.getParameter("description");
		if(null!=description && !description.equals("")){
			sb.append(" description like '%").append(description.trim()).append("%'").append(" and ");
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public MenuService getMenuServiceImpl() {
		return menuServiceImpl;
	}

	public void setMenuServiceImpl(MenuService menuServiceImpl) {
		this.menuServiceImpl = menuServiceImpl;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	

}
