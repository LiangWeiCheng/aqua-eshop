package com.aqua.test;

import javax.annotation.Resource;

import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.aqua.pingtai.action.json.DictJsonAction;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.UserService;
import com.aqua.pingtai.webservice.xfire.HelloWorld;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

/**
 * @author xinxuan.wang
 * 
 */
public class TestDict extends StrutsSpringTestCase {

	@Test
	public void testGetActionMapping() throws Exception {
		ActionMapping mapping = getActionMapping("/jsonPackage/dictJsonAction.action");
		assertNotNull(mapping);
		assertEquals("/jsonPackage", mapping.getNamespace());
		assertEquals("dictJsonAction", mapping.getName());
	}
	
	@Test
	public void testGetActionProxy() throws Exception {
		ActionProxy proxy = getActionProxy("/jsonPackage/dictJsonAction.action");
		assertNotNull(proxy);

		DictJsonAction dictJsonAction = (DictJsonAction) proxy.getAction();
		assertNotNull(dictJsonAction);

		request.setParameter("username", "FD");
		dictJsonAction.request = request;
		System.out.println(dictJsonAction.getDicts().toString());
		
		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		assertNotNull(dictJsonAction.jsonMap);
//		assertEquals("FD", dictJsonAction.getUsername());
	}
}
