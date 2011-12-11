package com.aqua.test;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.aqua.pingtai.action.json.DictJsonAction;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

/**
 * @author xinxuan.wang
 * 
 */
public class TestDict extends StrutsSpringTestCase {
	
	private UserService userServiceImpl;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session hibernateSession = getSession(sessionFactory);
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(hibernateSession));

		// 模拟登录
		User loginUser = new User();
		loginUser.setUserName("admins");
		loginUser.setPassWord("000000");
		userServiceImpl = (UserService) applicationContext.getBean("userServiceImpl");
		User user = userServiceImpl.userLogin(loginUser);
		request.getSession().setAttribute("currentUser", user);
	}

	private Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
		FlushMode flushMode = FlushMode.NEVER;
		if (flushMode != null) {
			session.setFlushMode(flushMode);
		}
		return session;
	}

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
//		System.out.println(dictJsonAction.getActionClassFullName());
		System.out.println(dictJsonAction.getDictTypes());
		System.out.println(proxy.getMethod());

		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		// assertEquals("FD", dictJsonAction.getUsername());
	}
	
	@Test
	public void testDictTypeList() throws UnsupportedEncodingException, ServletException {
		String data = executeAction("/pingTai/dictPingTaiAction!dictTypeList.action");
		
		System.out.println(data.toString());
		assertNotNull(data);
	}

	@Test
	public void testGetDictTypes() throws Exception {
//		QueryResult queryResult = new QueryResult();//查询结果
//		request.getSession().setAttribute("com.aqua.pingtai.action.pingtai.DictManagerAction.java", queryParameter);
		
		// 输出json数据
		String data = executeAction("/jsonPackage/dictJsonAction!getDictTypes.action?queryResult.currentPage=2");
		System.out.println(data.toString());
		assertNotNull(data);
	}

	@Test
	public void testGetDicts() throws Exception {
		request.setParameter("dictTypeId", "1");
		String data = executeAction("/jsonPackage/dictJsonAction!getDicts.action");
		System.out.println(data.toString());
		assertNotNull(data);
	}
}
