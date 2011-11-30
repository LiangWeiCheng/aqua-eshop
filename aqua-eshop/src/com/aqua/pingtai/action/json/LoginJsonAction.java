package com.aqua.pingtai.action.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Menu;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.MenuService;
import com.aqua.pingtai.service.OperatorService;
import com.aqua.pingtai.service.UserService;

@SuppressWarnings({"serial"})
public class LoginJsonAction extends BaseAction {
	
	@Resource(name="userServiceImpl")
	private UserService userServiceImpl;
	
	@Resource(name="operatorServiceImpl")
	private OperatorService operatorServiceImpl;
	
	@Resource(name="menuServiceImpl")
	private MenuService menuServiceImpl;
	
	private User user;
	private String yanZhengMa;
	private String menuString;
	
	private HashMap<String, Serializable> jsonOperator;
	
    public String getJsonMenu() throws Exception {
		user = Context.getCurrentUser();
		// 测试用户
//		User loginUser = new User();
//		loginUser.setUserName("admins");
//		loginUser.setPassWord("000000");
//		user = userServiceImpl.userLogin(loginUser);
		
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			return SUCCESS;
		} else {
			return ERROR;
		}
    }

	/**
	 * 登录成功后查询操作权限
	 * @return
	 */
	public HashMap<String, Serializable> getJsonOperator(){
		jsonOperator = new JSONObject();
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			String selectOperator = "select ids,names,url from pingtai_operator where ids in (select operatorIds from pingtai_roleoperator where roleIds in (select roleIds from pingtai_rolegroup where groupIds in ( select groupIds from pingtai_usergroup where userIds="+user.getIds()+")))";
			List<Operator> operatorList = operatorServiceImpl.selectUserOperator(selectOperator);
			List<String> operatorStringList = new ArrayList<String>();
			for (Operator operator : operatorList) {
				operatorStringList.add(operator.getUrl().trim());
			}
			List<Menu> menuList = menuServiceImpl.getMenuByHQL(" where parentMenu.menuLevel='-1' and menuType='menuType_houTai' and menuLevel='1' order by orderIds asc");

			jsonOperator.put("identifier", "name");
			jsonOperator.put("label", "name");
			JSONArray items = new JSONArray();
			
			for (Menu menu : menuList) {
				Set<Menu> menuSet = menu.getMenuSet();
				if(menuSet.size()==0){
					continue;
				}
				JSONObject parentObj = new JSONObject();
				parentObj.put("name", menu.getNames());
				parentObj.put("type", "parentObj");
				JSONArray children = new JSONArray();
				
				for (Iterator<Menu> iterator = menuSet.iterator(); iterator.hasNext();) {
					Menu menuTwo = (Menu) iterator.next();
					boolean bool = operatorStringList.contains(menuTwo.getUrl().trim());
					if(bool){
						JSONObject childrenRefObj = new JSONObject();
						childrenRefObj.put("_reference", menuTwo.getNames());
						children.add(childrenRefObj);
						
						JSONObject childrenObj = new JSONObject();
						childrenObj.put("name", menuTwo.getNames());
						childrenObj.put("type", "childrenObj");
						childrenObj.put("link", request.getContextPath() + menuTwo.getUrl());
						items.add(childrenObj);
					}
				}
				parentObj.put("children", children);
				items.add(parentObj);
			}

			jsonOperator.put("items", items);
		}else{
			jsonOperator.put("error", "user is null");
		}
		System.out.println(jsonOperator);
		return jsonOperator;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getYanZhengMa() {
		return yanZhengMa;
	}

	public void setYanZhengMa(String yanZhengMa) {
		this.yanZhengMa = yanZhengMa;
	}

	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public OperatorService getOperatorServiceImpl() {
		return operatorServiceImpl;
	}

	public void setOperatorServiceImpl(OperatorService operatorServiceImpl) {
		this.operatorServiceImpl = operatorServiceImpl;
	}

	public MenuService getMenuServiceImpl() {
		return menuServiceImpl;
	}

	public void setMenuServiceImpl(MenuService menuServiceImpl) {
		this.menuServiceImpl = menuServiceImpl;
	}

	public String getMenuString() {
		return menuString;
	}

	public void setMenuString(String menuString) {
		this.menuString = menuString;
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionClassFullName() {
		// TODO Auto-generated method stub
		return null;
	}

}
