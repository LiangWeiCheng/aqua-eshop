package com.aqua.pingtai.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.interfaces.UserDao;
import com.aqua.pingtai.entity.bean.authority.Department;
import com.aqua.pingtai.entity.bean.authority.Group;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;
import com.aqua.pingtai.service.UserService;
import com.aqua.pingtai.utils.MyMD5Util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compass.core.Compass;
import org.compass.core.CompassHits;
import org.compass.core.CompassQuery;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.jbpm.api.IdentityService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userServiceImpl") 
@Scope("singleton")
public class UserServiceImpl implements UserService {
	
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	/** 由于系统中直接使用的compassTemplate所以没有使用此方法
	extend CompassDaoSupport
	@Resource
	public void setSessionFactory(Compass compass){
		super.setCompass(compass);
	}
	 */
	
	@Resource(name="userDaoImpl")
	public UserDao userDao;
	
	@Resource(name="compassTemplate")
	public CompassTemplate compassTemplate;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	
	public UserServiceImpl(){
		
	}

	/**
	 * 保存一个用户
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveUser(User user){
		try {
			User createUser = Context.getCurrentUser();
			
			Department department = daoHibernateBase.findOneEntity(Department.class, user.getDepartment().getIds());
			user.setDepartment(department);//所属部门
			
			String encodePassWord = MyMD5Util.getEncryptedPwd(user.getPassWord());
			user.setPassWord(encodePassWord);//密码加密
			
			daoHibernateBase.saveOneEntity(user.getUserInfo(), createUser);//保存用户扩展信息
			daoHibernateBase.saveOneEntity(user, createUser);//保存用户
			
			IdentityService identityService = jbpmTemplate.getProcessEngine().getIdentityService();
			//创建JBPM用户
			identityService.createUser(user.getIds().toString(), user.getUserInfo().getEmail(), user.getUserName(), user.getUserInfo().getNames());			
		} catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace();  
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取一个用户
	 */
	public User getUser(Long userIds){
		try {
			return daoHibernateBase.findOneEntity(User.class, userIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取一个用户
	 */
	public User getUserByHql(Long userIds){
		try {
			return daoHibernateBase.findOneEntity(User.class, " where ids="+userIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新一个用户
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateUser(User user){
		try {
			User updateUser = Context.getCurrentUser();
			daoHibernateBase.updateOneEntity(user.getUserInfo(), updateUser);
			
			Department department = daoHibernateBase.findOneEntity(Department.class, user.getDepartment().getIds());
			user.setDepartment(department);//所属部门

			User oldUser = (User) daoHibernateBase.getHibernateTemplate().get(User.class, user.getIds());
			if(null!=user.getPassWord() && !user.getPassWord().trim().equals("")){
				String encodePassWord = MyMD5Util.getEncryptedPwd(user.getPassWord());
				user.setPassWord(encodePassWord);
			}else{
				user.setPassWord(oldUser.getPassWord());
			}
			
			//处理JBPM
			IdentityService identityService = jbpmTemplate.getProcessEngine().getIdentityService();
			//删除旧关系
			Set<Group> groupSetOld = oldUser.getGroupSet();
			for (Group group : groupSetOld) {
				identityService.deleteMembership(user.getIds().toString(), group.getIds().toString(), "");//删除用户组关系
			}
			//建立新关系
			Set<Group> groupSetNew = user.getGroupSet();
			for (Group group : groupSetNew) {
				identityService.createMembership(user.getIds().toString(), group.getIds().toString(), "");//创建用户组关系
			}
			//更新用户
			daoHibernateBase.updateOneEntity(user, updateUser);
		} catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace();  
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一个用户
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(User user){
		User deleteUser = Context.getCurrentUser();
		try {
			user.setValid("wuXiao");
			user.getUserInfo().setValid("wuXiao");
			//daoHibernateBase.deleteOneEntity(user);
			daoHibernateBase.updateOneEntity(user.getUserInfo(), deleteUser);//假删除
			daoHibernateBase.updateOneEntity(user, deleteUser);//假删除
			
			//处理JBPM
			IdentityService identityService = jbpmTemplate.getProcessEngine().getIdentityService();
			Set<Group> groupSet = user.getGroupSet();
			for (Group group : groupSet) {
				identityService.deleteMembership(user.getIds().toString(), group.getIds().toString(), "");//删除用户组关系
			}
			identityService.deleteUser(user.getIds().toString());//删除用户
		} catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace();  
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * compass搜索查询
	 * @param queryString
	 * @return
	 */
	public List<User> queryUser(String queryString, QueryResult queryResult){
		List<User> list = new ArrayList<User>();
		Compass compass = compassTemplate.getCompass();
		CompassSession session=compass.openSession();

		CompassHits hits = session.queryBuilder().queryString("userName:"+queryString).toQuery()
		.addSort("userName", CompassQuery.SortPropertyType.STRING, CompassQuery.SortDirection.REVERSE).hits();
		for(int i=0;i<hits.length();i++){
			Object obj = hits.data(i);
			if(obj instanceof User){
				User user=(User)hits.data(i);
				user.setUserName(hits.highlighter(i).fragment("userName"));//高亮显示
				list.add(user);
			}
		}
		
		System.out.println("查询条件:"+queryString);
		System.out.println("总记录数:"+hits.getLength());
		/*
		Long currentPage = queryResult.getCurrentPage();//当前页数
		Long maxResult = queryResult.getMaxResult();//抓取数
		Long firstResult = (currentPage-1l)==0l? 0l : (currentPage-1l)*maxResult;//开始处
		
		List<User> list2 = new ArrayList<User>();
		CompassHits hits2 = session.find(queryString);
		int start = firstResult.intValue();//开始
		int end = start + maxResult.intValue();//结束
		for (int i=start; i<end; i++) {
			User user = (User)hits.data(i);
			if(hits.highlighter(i).fragment("userName")!=null){
				user.setUserName(hits.highlighter(i).fragment("userName"));//高亮显示
			}
			list2.add(user);
		}
		queryResult.setRecordCount(Long.valueOf(String.valueOf(hits2.length())));//记录总数
		queryResult.setResultList(list2);//记录集
		queryResult.compute();//计算
		*/
		return new ArrayList<User>(list);
	}
	
	/**
	 * 查询用户列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryUser(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(User.class, filterString, queryResult);
	}
	
	/**
	 * DWR验证
	 * @param ids
	 * @param passWord
	 * @return
	 */
	public String valiPassWordDWR(Long ids, String passWord){
		try {
			User user = (User) daoHibernateBase.getHibernateTemplate().get(User.class, ids);
			String oldPassWord = user.getPassWord();
			boolean valiPass = MyMD5Util.validPassword(passWord, oldPassWord);
			if(valiPass){
				return "right";
			}else{
				return "error";
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "runError";
	}
	
	/**
	 * 用户登录后台验证
	 * @param user
	 * @return
	 */
	public User userLogin(User user){
		User resultUser = null ;
		try {
			List<User> userList = daoHibernateBase.findManyEntity(User.class, " where userName='"+user.getUserName()+"' and userClass='userClass_houTai'");
			int userSize = userList.size();
			if(userSize==1){
				resultUser = userList.get(0);
				String dbPassWord = resultUser.getPassWord();
				boolean valiPass = MyMD5Util.validPassword(user.getPassWord(), dbPassWord);
				if(valiPass){
					return resultUser;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * DWR当前在线用户
	 * @return
	 */
	public User getCurrentUserDWR(){
		return Context.getCurrentUser();
	}
	
	/**
	 * 验证用户名是否已被注册
	 * @param username
	 * @return
	 */
	public String queryUserNameIsExist(String username){
		try {
			List<User> userList = daoHibernateBase.findManyEntity(User.class, " where userName='"+username+"' and userClass='userClass_houTai'");
			int userSize = userList.size();
			if(userSize!=1){
				return "yes";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "no";
	}
	
	/**
	 * 验证email是否已被注册
	 * @param username
	 * @return
	 */
	public String queryEmailIsExist(String email){
		try {
			List<User> userList = daoHibernateBase.findManyEntity(User.class, " where userInfo.email='"+email+"' and userClass='userClass_houTai'");
			int userSize = userList.size();
			if(userSize!=1){
				return "yes";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "no";
	}

	/**
	 * HQL查询用户
	 * @param filterString
	 * @return
	 */
	public List<User> queryUserListByHql(String filterString){
		List<User> userList = null;
		try {
			userList =  daoHibernateBase.findManyEntity(User.class, filterString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CompassTemplate getCompassTemplate() {
		return compassTemplate;
	}

	public void setCompassTemplate(CompassTemplate compassTemplate) {
		this.compassTemplate = compassTemplate;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}

	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}
	
}
