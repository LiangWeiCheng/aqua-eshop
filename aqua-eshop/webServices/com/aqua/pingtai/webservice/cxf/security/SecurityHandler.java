package com.aqua.pingtai.webservice.cxf.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class SecurityHandler implements CallbackHandler {
	
	//凡是角色为 ws的用户 才可访问你的这个 WebService   
	private static final String ROLE_SERVICES = "ws";
	
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];   
		String userName = callback.getIdentifer();   
		/*//判断角色   
		String[] roles=UserFactory.getRoleRemote(null).findByUser(userName);   
		if(!Arrays.asList(roles).contains(ROLE_SERVICES)){   
			throw new WSSecurityException(String.format("not '%s' role privilege ", ROLE_SERVICES));   
		}     
		if (WSConstants.PASSWORD_TEXT.equals(callback.getPasswordType())) {   
			String pw = callback.getPassword();   
			if (!UserFactory.getUserRemote(null).verify(userName, pw)) {   
				throw new WSSecurityException("password not match");   
			}   
		} else {   
			UserEntity user = (UserEntity)UserFactory.getUserRemote(null).findUser(userName);   
			callback.setPassword(user.getPassword());   
		}   */

	}

}
