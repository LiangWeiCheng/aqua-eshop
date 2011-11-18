package com.aqua.pingtai.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.Role;

@Transactional
public interface RoleService {
	
	/**
	 * 获取一个角色
	 * @param dictTypeids
	 * @return
	 */
	public Role getOneRole(Long roleIds);
	
	/**
	 * 查询角色列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryRole(String filterString, QueryResult queryResult) throws Exception;
	
	/**
	 * 保存角色
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveRole(Role role);
	
	/**
	 * 更新角色
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateRole(Role role);
	
	/**
	 * 更新角色缓存
	 * @param role
	 */
	public void updateRoleByCache(Role role);
	
	/**
	 * 删除角色
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteRole(Role role);
	
	/**
	 * HQL查询Role
	 * @param hql
	 * @return
	 */
	public List<Role> getRoleByHql(String hql);
	
}