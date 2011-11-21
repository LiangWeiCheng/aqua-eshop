package com.aqua.pingtai.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.aqua.pingtai.entity.bean.authority.User;

public class Json {
	
	public static void main(String[] args) throws IOException {
		testTree();
	}
	
	public static void testTree() throws IOException {
		List<User> userList = new ArrayList<User>();
		
		User user = new User();
		user.setUserName("aaaa");
		user.setPassWord("bbbb");
		userList.add(user);
		
		User user2 = new User();
		user2.setUserName("aaaa");
		user2.setPassWord("bbbb");
		userList.add(user2);
		
		ObjectMapper mapper = new ObjectMapper();  
		StringWriter sw = new StringWriter();   
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);   
		mapper.writeValue(gen, userList);//user   
		gen.close();   
		String json = sw.toString();   
		System.out.println(json);
	}
	
	public void testUser() throws IOException {
		List<User> userList = new ArrayList<User>();
		
		User user = new User();
		user.setUserName("aaaa");
		user.setPassWord("bbbb");
		userList.add(user);
		
		User user2 = new User();
		user2.setUserName("aaaa");
		user2.setPassWord("bbbb");
		userList.add(user2);
		
		ObjectMapper mapper = new ObjectMapper();  
		StringWriter sw = new StringWriter();   
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);   
		mapper.writeValue(gen, userList);//user   
		gen.close();   
		String json = sw.toString();   
		System.out.println(json);
	}
}
