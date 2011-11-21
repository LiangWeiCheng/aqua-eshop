package com.aqua.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.test.AbstractTransactionalSpringContextTests;

import com.aqua.pingtai.entity.bean.authority.Menu;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.MenuService;
import com.aqua.pingtai.service.OperatorService;
import com.aqua.pingtai.service.UserService;

/**
 * @author xinxuan.wang
 * 
 */
public class TestJsonMenu extends AbstractTransactionalSpringContextTests {
	
	private OperatorService operatorServiceImpl;
	private UserService userServiceImpl;
	private MenuService menuServiceImpl;
	
	/* (non-Javadoc)
	 * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
	 */
	@Override
	protected String[] getConfigLocations() {
		String[] configLocations = { "applicationContext.xml", "applicationContext-compass.xml" }; 
        return configLocations; 
	}

	@Test
	public void testGetJsonOperator() {
		System.out.println("testGetJsonOperator");
//		OperatorService operatorServiceImpl = (OperatorService) applicationContext.getBean("operatorServiceImpl");
		User loginUser = new User();
		loginUser.setUserName("admins");
		loginUser.setPassWord("000000");
		User user = userServiceImpl.userLogin(loginUser);
		
		String selectOperator = "select ids,names,url from pingtai_operator where ids in (select operatorIds from pingtai_roleoperator where roleIds in (select roleIds from pingtai_rolegroup where groupIds in ( select groupIds from pingtai_usergroup where userIds="+user.getIds()+")))";
		List<Operator> operatorList = operatorServiceImpl.selectUserOperator(selectOperator);
		List<String> operatorStringList = new ArrayList<String>();
		for (Operator operator : operatorList) {
			operatorStringList.add(operator.getUrl().trim());
		}
		List<Menu> menuList = menuServiceImpl.getMenuByHQL(" where parentMenu.menuLevel='-1' and menuType='menuType_houTai' and menuLevel='1' order by orderIds asc");
		JSONObject jsonOperator = new JSONObject();
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
					JSONObject childrenObj = new JSONObject();
					childrenObj.put("_reference", menuTwo.getNames());
					children.add(childrenObj);
				}
			}
			parentObj.put("children", children);
			items.add(parentObj);
		}

		jsonOperator.put("items", items);
		System.out.println(jsonOperator.toJSONString());
		Assert.assertEquals(loginUser.getUserName(), user.getUserName());
	}

	public void setOperatorServiceImpl(OperatorService operatorServiceImpl) {
		this.operatorServiceImpl = operatorServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public void setMenuServiceImpl(MenuService menuServiceImpl) {
		this.menuServiceImpl = menuServiceImpl;
	}
}
