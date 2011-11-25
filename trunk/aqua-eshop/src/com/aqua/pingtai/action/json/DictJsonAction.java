package com.aqua.pingtai.action.json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aqua.pingtai.action.DictManagerAction;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.entity.bean.authority.DictType;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.UserService;

@SuppressWarnings({"serial"})
public class DictJsonAction extends DictManagerAction {
	
	@Resource
	private UserService userServiceImpl;
	public HashMap<String, Serializable> jsonMap;
	
    public String getDicts() throws Exception {
		return SUCCESS;
    }
	
    public HashMap<String, Serializable> getJsonMap() {
		User loginUser = new User();
		loginUser.setUserName("admins");
		loginUser.setPassWord("000000");
		User user = userServiceImpl.userLogin(loginUser);
		
		request.getSession().setAttribute("currentUser", user);

		jsonMap = new JSONObject();
		jsonMap.put("identifier", "name");
		jsonMap.put("label", "name");
		// 取出分页数据
		super.dictTypeList();
		// 初始化数据生成Json
		JSONArray items = new JSONArray();
		for (Object o : queryResult.getResultList()) {
			DictType dictType = (DictType) o;
			JSONObject dictTypeObj = new JSONObject();
			dictTypeObj.put("id", dictType.getIds());
			dictTypeObj.put("number", dictType.getNumbers());
			dictTypeObj.put("name", dictType.getNames());
			items.add(dictTypeObj);
		}
		jsonMap.put("items", items);
		return jsonMap;
    }

    public String execute() throws Exception {
		System.out.println(request.getAttributeNames());
			return SUCCESS;
    }

}
