package com.aqua.pingtai.webservice.xfire;

import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.aqua.pingtai.webservice.cxf.adapter.MapAdapter;

@WebService
public interface HelloWorld {
	
	public String sayHello(@WebParam(name="name") String name);
	
	@WebResult(name="returnMap") 
	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String, String> sayHello2(@WebParam(name="name") String name);
	
}
