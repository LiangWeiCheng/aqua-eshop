package com.aqua.pingtai.utils;

import java.io.UnsupportedEncodingException;  
import java.security.NoSuchAlgorithmException;  
import java.util.HashMap;  
import java.util.Map;  
  
public class MyMD5Test {  
	
    private static Map<String,Object> users = new HashMap<String,Object>();  
      
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{ 
    	/*System.out.println(MyMD5Util.getEncryptedPwd("admin"));
        String userName = "zyg";  
        String password = "123";  
        registerUser(userName,password);  
          
        userName = "changong";  
        password = "456";  
        registerUser(userName,password);  
          
        String loginUserId = "zyg";  
        String pwd = "123";  */
        try {  
            /*if(loginValid(loginUserId,pwd)){  
                System.out.println("欢迎登陆！！！");  
            }else{  
                System.out.println("口令错误，请重新输入！！！");  
            }  */
        	String ps = "000000";
        	String dbps = "";
        	dbps = MyMD5Util.getEncryptedPwd(ps);
        	System.out.println(dbps);
        	boolean result = MyMD5Util.validPassword(ps, "8C5B01A76155FE67314D1C916B206EC8BF0E2DDE2D3282E8318785B4");  
        	System.out.println(result);
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }   
    }  
      
    /** 
     * 注册用户 
     *  
     * @param userName 
     * @param password 
     */  
    public static void registerUser(String userName,String password){  
        String encryptedPwd = null;  
        try {  
            encryptedPwd = MyMD5Util.getEncryptedPwd(password);    
            users.put(userName, encryptedPwd);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 验证登陆  
     * @param userName 
     * @param password 
     * @return 
     * @throws UnsupportedEncodingException  
     * @throws NoSuchAlgorithmException  
     */  
    public static boolean loginValid(String userName,String password)   
                throws NoSuchAlgorithmException, UnsupportedEncodingException{  
        String pwdInDb = (String)users.get(userName);  
        if(null!=pwdInDb){ // 该用户存在  
                return MyMD5Util.validPassword(password, pwdInDb);  
        }else{  
            System.out.println("不存在该用户！！！");  
            return false;  
        }  
    }  
} 

