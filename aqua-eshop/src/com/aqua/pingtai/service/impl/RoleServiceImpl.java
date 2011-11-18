package com.aqua.pingtai.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.dao.interfaces.RoleDao;
import com.aqua.pingtai.entity.bean.authority.Group;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.Role;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.RoleService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("roleServiceImpl")
@Scope("singleton")
public class RoleServiceImpl implements RoleService {
	
	private static final Log log = LogFactory.getLog(RoleServiceImpl.class);
	
	@Resource(name="roleDaoImpl")
	private RoleDao roleDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;
	
	@Resource(name="oscacheFactory")
	private OscacheFactory oscacheFactory;
	
	public RoleServiceImpl(){
		
	}
	
	/**
	 * 删除角色
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteRole(Role role) {
		try {
			daoHibernateBase.excuteHQL("delete from Role where ids="+role.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个角色
	 * @param dictTypeids
	 * @return
	 */
	public Role getOneRole(Long roleIds) {
		Role role = null;
		try {
			role = daoHibernateBase.findOneEntity(Role.class, roleIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	/**
	 * 保存角色
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveRole(Role role) {
		User createUser = Context.getCurrentUser();
		try {
			daoHibernateBase.saveOneEntity(role, createUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询角色列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryRole(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(Role.class, filterString, queryResult);
	}

	/**
	 * 更新角色
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateRole(Role role) {
		User updateUser = Context.getCurrentUser();
		try {
			daoHibernateBase.updateOneEntity(role, updateUser);
			log.info(updateUser.getUserName()+",更新角色,角色名:"+role.getNames());
		} catch (Exception e) {
			log.error(updateUser.getUserName()+",更新角色异常!!!角色名:"+role.getNames());
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新角色缓存
	 * @param role
	 */
	public void updateRoleByCache(Role role){
		//A.查询角色关联的组
		RowMapper mapperGroup = new RowMapper() {
	        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Group group = new Group();
	        	group.setIds(rs.getLong("ids"));
	            return group;
	        }
	    };
	    String selectGroup = "select ids from pingtai_group where ids in (select groupIds from pingtai_rolegroup where roleIds="+role.getIds()+")";
	    List<Group> groupList =  (List<Group>)( daoJdbcBase.getJdbcTemplate().query(selectGroup, mapperGroup));
	    //B.更新每个组的缓存
	    for (Group group : groupList) {
	    	RowMapper mapper = new RowMapper() {
		        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		            Operator operator = new Operator();
		            operator.setIds(rs.getLong("ids"));
		            operator.setNames(rs.getString("names"));
		            operator.setUrl(rs.getString("url"));
		            return operator;
		        }
		    };
		    String selectOperator = "select ids,names,url from pingtai_operator where ids in (select operatorIds from pingtai_roleoperator where roleIds in (select roleIds from pingtai_rolegroup where groupIds="+group.getIds()+"))";
		    List<Operator> operatorList =  (List<Operator>)( daoJdbcBase.getJdbcTemplate().query(selectOperator, mapper));
		    oscacheFactory.putObject(group.getIds()+"groupIds", operatorList);
		}
	    log.info(Context.getCurrentUser().getUserName()+",更新角色对应的缓存:"+role.getNames());
	}
	
	/**
	 * HQL查询Role
	 * @param hql
	 * @return
	 */
	public List<Role> getRoleByHql(String hql){
		List<Role> groupList = null;
		try {
			groupList =  daoHibernateBase.findManyEntity(Role.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupList;
	}

	public DaoJdbcBase getDaoJdbcBase() {
		return daoJdbcBase;
	}

	public void setDaoJdbcBase(DaoJdbcBase daoJdbcBase) {
		this.daoJdbcBase = daoJdbcBase;
	}

	public OscacheFactory getOscacheFactory() {
		return oscacheFactory;
	}

	public void setOscacheFactory(OscacheFactory oscacheFactory) {
		this.oscacheFactory = oscacheFactory;
	}
	
	public RoleDao getRoleDaoImpl() {
		return roleDaoImpl;
	}

	public void setRoleDaoImpl(RoleDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	
}
