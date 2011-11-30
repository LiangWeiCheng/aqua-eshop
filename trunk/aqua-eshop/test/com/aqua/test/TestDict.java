package com.aqua.test;

import javax.servlet.http.HttpServletRequest;

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
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

/**
 * @author xinxuan.wang
 * 
 */
public class TestDict extends StrutsSpringTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		Session hibernateSession = getSession(sessionFactory);
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(hibernateSession));
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
		// System.out.println(dictJsonAction.getDicts().toString());
		System.out.println(proxy.getMethod());

		String result = proxy.execute();
		assertEquals(Action.SUCCESS, result);
		// assertEquals("FD", dictJsonAction.getUsername());
	}

	@Test
	public void testGetDictTypes() throws Exception {
		String data = executeAction("/jsonPackage/dictJsonAction!getDictTypes.action");
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
