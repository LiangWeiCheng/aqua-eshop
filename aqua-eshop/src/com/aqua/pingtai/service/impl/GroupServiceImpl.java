package com.aqua.pingtai.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.dao.interfaces.GroupDao;
import com.aqua.pingtai.entity.bean.authority.Group;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.extend.JbpmTemplate;
import com.aqua.pingtai.service.GroupService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.IdentityService;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("groupServiceImpl")
@Scope("singleton")
public class GroupServiceImpl implements GroupService {
	
	private static final Log log = LogFactory.getLog(GroupServiceImpl.class);
	
	@Resource(name="groupDaoImpl")
	private GroupDao groupDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="jbpmTemplate")
	private JbpmTemplate jbpmTemplate;
	
	@Resource(name="daoJdbcBase")
	private DaoJdbcBase daoJdbcBase;
	
	@Resource(name="oscacheFactory")
	private OscacheFactory oscacheFactory;
	
	public GroupServiceImpl(){
		
	}

	public GroupDao getGroupDaoImpl() {
		return groupDaoImpl;
	}

	public void setGroupDaoImpl(GroupDao groupDaoImpl) {
		this.groupDaoImpl = groupDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	/**
	 * 删除组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteGroup(Group group) {
		try {
			//处理JBPM
			IdentityService identityService = jbpmTemplate.getProcessEngine().getIdentityService();
			group = daoHibernateBase.findOneEntity(Group.class, group.getIds());
			Set<User> groupSet = group.getUserSet();
			for (User user : groupSet) {
				identityService.deleteMembership(user.getIds().toString(), group.getIds().toString(), "");//删除用户组关系
			}
			//identityService.deleteGroup(group.getIds().toString());//删除JBPM组
			daoHibernateBase.excuteSQL("delete from pingtai_jbpm4_groupimpl where groupIds=" + group.getIds() );//删除JBPM扩展组
			daoHibernateBase.excuteHQL("delete from Group where ids=" + group.getIds() );//删除组
			//删除缓存
			oscacheFactory.removeObject(group.getIds()+"groupIds");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取一个组
	 * @param dictTypeids
	 * @return
	 */
	public Group getOneGroup(Long groupIds) {
		Group group = null;
		try {
			group = daoHibernateBase.findOneEntity(Group.class, groupIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}

	/**
	 * 保存组
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveGroup(Group group) {
		User createUser = Context.getCurrentUser();
		try {
			daoHibernateBase.saveOneEntity(group, createUser);//创建组
			
			//处理JBPM
			IdentityService identityService = jbpmTemplate.getProcessEngine().getIdentityService();
			identityService.createGroup(group.getIds().toString(), group.getNames(), group.getDescription());//创建组
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询组列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryGroup(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(Group.class, filterString, queryResult);
	}

	/**
	 * 更新组
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateGroup(Group group) {
		User updateUser = Context.getCurrentUser();
		try {
			daoHibernateBase.updateOneEntity(group, updateUser);

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
			log.info(updateUser.getUserName()+",更新组,组名:"+group.getNames());
		} catch (Exception e) {
			log.error(updateUser.getUserName()+",更新组异常!!!组名:"+group.getNames());
			e.printStackTrace();
		}
	}

	/**
	 * HQL查询group
	 * @param hql
	 * @return
	 */
	public List<Group> getGroupByHql(String hql){
		List<Group> groupList = null;
		try {
			groupList =  daoHibernateBase.findManyEntity(Group.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupList;
	}

	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}

	public DaoJdbcBase getDaoJdbcBase() {
		return daoJdbcBase;
	}

	public void setDaoJdbcBase(DaoJdbcBase daoJdbcBase) {
		this.daoJdbcBase = daoJdbcBase;
	}
	
	
}
