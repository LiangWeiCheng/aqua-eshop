package com.aqua.pingtai.cache;

/**
 * 缓存操作工厂类
 */
public class OscacheFactory {
	
	private OscacheExtends oscacheExtends;
	private static OscacheFactory instance; 
	private static Object lock = new Object();
	
	public OscacheFactory() {
		oscacheExtends = new OscacheExtends("aquaOScache", 3600*24*365);
	}
	
	public static OscacheFactory getInstance(){
		if (instance == null){
			synchronized( lock ){
				if (instance == null){
					instance = new OscacheFactory();
				}
			}
		}
		return instance;
	}
	
	//缓存一个对象
	public void putObject(String key, Object value) {
		oscacheExtends.put(key, value);
	}
	
	//获取一个对象
	public Object getObject(String key) {
		try {
			return oscacheExtends.get(key);
		} catch (Exception e) {
			System.out.println("getObject("+key+"):"+e.getMessage());
			return null;
		}
	} 
	
	//删除一个对象
	public void removeObject(String key) {
		oscacheExtends.remove(key);
	} 
	
	//删除所有对象
	public void removeAllNews() {
		oscacheExtends.removeAll();
	} 
	
}
