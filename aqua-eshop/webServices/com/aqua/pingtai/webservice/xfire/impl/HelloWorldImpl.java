package com.aqua.pingtai.webservice.xfire.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import com.aqua.pingtai.webservice.xfire.HelloWorld;

@WebService(endpointInterface="com.aqua.pingtai.webservice.xfire.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	public String sayHello(String name) {
		System.out.println("sayHello is called by " + name);
		return "Hello " + name;
	}

	public Map<String, String> sayHello2(String name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("name2", name);
		return map;
	}
	
	/**
	public static void main(String[] args) {
		HelloWorldImplService helloWorldImplService = new HelloWorldImplService();
		MapConvertor mapConvertor = helloWorldImplService.getHelloWorldImplPort().sayHello2("namessss");
		List<MapEntry> list = mapConvertor.getEntries();
		for (MapEntry mapEntry : list) {
			System.out.println(mapEntry.getKey());
			System.out.println(mapEntry.getValue());
		}
	}*/
}
