package com.aqua.pingtai.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.Department;

@Transactional
public interface DepartmentService {
	
	/**
	 * 获取一个部门
	 * @param dictTypeids
	 * @return
	 */
	public Department getOneDepartment(Long departmentIds);
	
	/**
	 * 查询部门列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryDepartment(String filterString, QueryResult queryResult) throws Exception;
	
	/**
	 * 保存部门
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDepartment(Department department);
	
	/**
	 * 更新部门
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateDepartment(Department department);
	
	/**
	 * 删除部门
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteDepartment(Department department);
	
	/**
	 * HQL查询Department
	 * @param hql
	 * @return
	 */
	public List<Department> getDepartmentByHQL(String hql);
	
}