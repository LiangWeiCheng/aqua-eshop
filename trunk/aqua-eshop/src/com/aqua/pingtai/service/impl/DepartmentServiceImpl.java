package com.aqua.pingtai.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.interfaces.DepartmentDao;
import com.aqua.pingtai.entity.bean.authority.Department;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.DepartmentService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("departmentServiceImpl")
@Scope("singleton")
public class DepartmentServiceImpl implements DepartmentService {
	
	private static final Log log = LogFactory.getLog(DepartmentServiceImpl.class);
	
	@Resource(name="departmentDaoImpl")
	private DepartmentDao departmentDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	public DepartmentServiceImpl(){
		
	}

	public DepartmentDao getDepartmentDaoImpl() {
		return departmentDaoImpl;
	}

	public void setDepartmentDaoImpl(DepartmentDao departmentDaoImpl) {
		this.departmentDaoImpl = departmentDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	/**
	 * 删除部门
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteDepartment(Department department) {
		try {
			daoHibernateBase.excuteHQL("delete from Department where ids="+department.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个部门
	 * @param dictTypeids
	 * @return
	 */
	public Department getOneDepartment(Long departmentIds) {
		Department department = null;
		try {
			department = daoHibernateBase.findOneEntity(Department.class, departmentIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department;
	}

	/**
	 * 保存部门
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDepartment(Department department) {
		User createUser = Context.getCurrentUser();
		try {
			Department parentDepartment = (Department) daoHibernateBase.getHibernateTemplate().get(Department.class, department.getParentDepartment().getIds());
			department.setParentDepartment(parentDepartment);
			
			User principalUser = daoHibernateBase.findOneEntity(User.class, department.getPrincipal().getIds());
			department.setPrincipal(principalUser);
			
			daoHibernateBase.saveOneEntity(department, createUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询部门列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryDepartment(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(Department.class, filterString, queryResult);
	}

	/**
	 * 更新部门
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateDepartment(Department department) {
		User updateUser = Context.getCurrentUser();
		try {
			Department parentDepartment = (Department) daoHibernateBase.getHibernateTemplate().get(Department.class, department.getParentDepartment().getIds());
			department.setParentDepartment(parentDepartment);
			
			User principalUser = daoHibernateBase.findOneEntity(User.class, department.getPrincipal().getIds());
			department.setPrincipal(principalUser);
			
			daoHibernateBase.updateOneEntity(department, updateUser);
			log.info(updateUser.getUserName()+",更新部门,部门名:"+department.getNames());
		} catch (Exception e) {
			log.error(updateUser.getUserName()+",更新部门异常!!!部门名:"+department.getNames());
			e.printStackTrace();
		}
	}

	/**
	 * HQL查询Department
	 * @param hql
	 * @return
	 */
	public List<Department> getDepartmentByHQL(String hql){
		List<Department> departmentList = null;
		try {
			departmentList = daoHibernateBase.findManyEntity(Department.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}
	
}
