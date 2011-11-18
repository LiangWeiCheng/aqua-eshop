package com.aqua.pingtai.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.User;

@Transactional
public interface UserService {

	/**
	 * 保存一个用户
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * 获取一个用户
	 * @param userIds
	 * @return
	 */
	public User getUser(Long userIds);
	
	/**
	 * 获取一个用户
	 */
	public User getUserByHql(Long userIds);
	
	/**
	 * 更新一个用户
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 删除一个用户
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(User user);
	
	/**
	 * compass搜索查询
	 * @param queryString
	 * @return
	 */
	public List<User> queryUser(String queryString, QueryResult queryResult);
	
	/**
	 * 查询用户列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryUser(String filterString, QueryResult queryResult) throws Exception;
	
	/**
	 * DWR验证
	 * @param ids
	 * @param passWord
	 * @return
	 */
	public String valiPassWordDWR(Long ids, String passWord);
	
	/**
	 * 用户登录后台验证
	 * @param user
	 * @return
	 */
	public User userLogin(User user);
	
	/**
	 * 当前在线用户
	 * @return
	 */
	public User getCurrentUserDWR();
	
	/**
	 * 验证用户名是否已被注册
	 * @param username
	 * @return
	 */
	public String queryUserNameIsExist(String username);
	
	/**
	 * 验证email是否已被注册
	 * @param username
	 * @return
	 */
	public String queryEmailIsExist(String email);
	
	/**
	 * HQL查询用户
	 * @param filterString
	 * @return
	 */
	public List<User> queryUserListByHql(String filterString);
	
}