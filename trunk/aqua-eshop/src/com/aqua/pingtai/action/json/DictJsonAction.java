package com.aqua.pingtai.action.json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aqua.pingtai.action.DictManagerAction;
import com.aqua.pingtai.entity.bean.authority.Dict;
import com.aqua.pingtai.entity.bean.authority.DictType;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.UserService;

@SuppressWarnings({"serial"})
public class DictJsonAction extends DictManagerAction {
	
	@Resource
	private UserService userServiceImpl;
	
	/**
	 * 字典类型
	 */
	public HashMap<String, Serializable> dictTypeMap;
	/**
	 * 某字典类型下的字典列表
	 */
	public HashMap<String, Serializable> dictMap;
	
    /**
     * 得到字典类型数据
     * @return
     * @throws Exception
     */
    public String getDictTypes() throws Exception {
		User loginUser = new User();
		loginUser.setUserName("admins");
		loginUser.setPassWord("000000");
		User user = userServiceImpl.userLogin(loginUser);
		
		request.getSession().setAttribute("currentUser", user);

		dictTypeMap = new JSONObject();
		dictTypeMap.put("identifier", "name");
		dictTypeMap.put("label", "name");
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
		dictTypeMap.put("items", items);
		return "dictType";
    }
	
    /**
     * 得到某字典类型下的字典列表数据
     * @return
     * @throws Exception
     */
    public String getDicts() throws Exception {
    	dictMap = new JSONObject();
    	dictMap.put("identifier", "name");
    	dictMap.put("label", "name");
    	
    	String id = request.getParameter("dictTypeId");
    	System.out.println("id=" + id);
		// 取出数据
    	DictType dictType = dictServiceImpl.getOneDictType(Long.valueOf(id));
		// 初始化数据生成Json
		JSONArray items = new JSONArray();
    	for (Dict o : dictType.getDictSet()) {
			JSONObject dictTypeObj = new JSONObject();
			dictTypeObj.put("id", o.getIds());
			dictTypeObj.put("number", o.getNumbers());
			dictTypeObj.put("name", o.getNames());
			dictTypeObj.put("value", o.getValue());
			dictTypeObj.put("orderIds", o.getOrderIds());
			items.add(dictTypeObj);
    	}
    	dictMap.put("items", items);
		return "dict";
    }
	
    /**
     * @return
     * @throws IOException 
     */
    public String upDictType() throws IOException {
    	String id = request.getParameter("id");
    	String name = request.getParameter("name");
    	String number = request.getParameter("number");
    	System.out.println("id=" + id + ",number=" + number);
//    	DictType dictType = new DictType();
//    	dictType.setIds(Long.valueOf(id));
//    	dictType.setNames(name);
//    	dictType.setNumbers(number);
//    	System.out.println(dictType);
//    	super.setDictType(dictType);
		return SUCCESS;
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
