package com.aqua.pingtai.jbpm.extend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.jbpm.entity.GroupImpls;
import com.aqua.pingtai.jbpm.entity.UserImpls;

import org.jbpm.api.IdentityService;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class IdentitySessionImpl implements org.jbpm.pvm.internal.identity.spi.IdentitySession{
	
	private DaoHibernateBase daoHibernateBase;
	private DaoJdbcBase daoJdbcBase;
	private HibernateTemplate hibernateTemplate;
	private JbpmTemplate jbpmTemplate;
	
	public DaoHibernateBase getDaoHibernateBase() {
		daoHibernateBase = (DaoHibernateBase)Context.getSpringBean("daoHibernateBase");
		return daoHibernateBase;
	}

	public DaoJdbcBase getDaoJdbcBase() {
		daoJdbcBase = (DaoJdbcBase)Context.getSpringBean("daoJdbcBase");
		return daoJdbcBase;
	}

	public HibernateTemplate getHibernateTemplate() {
		hibernateTemplate = (HibernateTemplate)Context.getSpringBean("hibernateTemplate");
		return hibernateTemplate;
	}

	public JbpmTemplate getJbpmTemplate() {
		jbpmTemplate = (JbpmTemplate)Context.getSpringBean("jbpmTemplate");
		return jbpmTemplate;
	}

	//创建一个组
	@Transactional(propagation=Propagation.REQUIRED)
	public String createGroup(String groupIds, String groupType, String description) {
		GroupImpls groupImpl = new GroupImpls();
		com.aqua.pingtai.entity.bean.authority.Group group = null;
		try {
			group = getDaoHibernateBase().findOneEntity(com.aqua.pingtai.entity.bean.authority.Group.class, " where ids="+groupIds);
			groupImpl.setId(group.getIds().toString());
			groupImpl.setName(group.getNames());
			groupImpl.setType(groupType);
			groupImpl.setDescription(description);
			groupImpl.setGroup(group);
			getDaoHibernateBase().saveOneEntity(groupImpl, Context.getCurrentUser());
			
			//IdentityService identityService = this.getJbpmTemplate().getProcessEngine().getIdentityService();
			//identityService.createGroup(groupIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//创建用户和组的关系
	@Transactional(propagation=Propagation.REQUIRED)
	public void createMembership(String userIds, String groupIds, String no) {
		UserImpls userImpl = null;
		GroupImpls groupImpl = null;
		try {
			userImpl = getDaoHibernateBase().findOneEntity(UserImpls.class, " where id='"+userIds+"'");
			groupImpl = getDaoHibernateBase().findOneEntity(GroupImpls.class, " where id='"+groupIds+"'");
			userImpl.getGroupImplSet().add(groupImpl);
			getDaoHibernateBase().updateOneEntity(userImpl, Context.getCurrentUser());
			
			//IdentityService identityService = this.getJbpmTemplate().getProcessEngine().getIdentityService();
			//identityService.createMembership(userIds, groupIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//创建一个用户
	@Transactional(propagation=Propagation.REQUIRED)
	public String createUser(String userIds, String businessEmail, String givenName, String familyName) {
		com.aqua.pingtai.entity.bean.authority.User user = null;
		UserImpls userImpl = new UserImpls();
		userImpl.setId(userIds);
		userImpl.setBusinessEmail(businessEmail);
		userImpl.setGivenName(givenName);
		userImpl.setFamilyName(familyName);
		try {
			user = getDaoHibernateBase().findOneEntity(com.aqua.pingtai.entity.bean.authority.User.class, " where ids="+userIds);
			userImpl.setUser(user);
			getDaoHibernateBase().saveOneEntity(userImpl, Context.getCurrentUser());
			
			//IdentityService identityService = this.getJbpmTemplate().getProcessEngine().getIdentityService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//删除一个组
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteGroup(String groupIds) {
		try {
			GroupImpls groupImpl = getDaoHibernateBase().findOneEntity(GroupImpls.class, " where id='"+groupIds+"'");
			getDaoHibernateBase().deleteOneEntity(groupImpl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//删除用户和组的关系
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteMembership(String userIds, String groupIds, String no) {
		UserImpls userImpl = null;
		GroupImpls groupImpl = null;
		try {
			userImpl = getDaoHibernateBase().findOneEntity(UserImpls.class, " where id='"+userIds+"'");
			groupImpl = getDaoHibernateBase().findOneEntity(GroupImpls.class, " where id='"+groupIds+"'");
			userImpl.getGroupImplSet().remove(groupImpl);
			getDaoHibernateBase().updateOneEntity(userImpl, Context.getCurrentUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//删除一个用户
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(String userIds) {
		try {
			UserImpls userImpl = getDaoHibernateBase().findOneEntity(UserImpls.class, " where id='"+userIds+"'");
			getDaoHibernateBase().deleteOneEntity(userImpl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询一个组
	public Group findGroupById(String groupIds) {
		GroupImpls groupImpl = null;
		try {
			groupImpl = getDaoHibernateBase().findOneEntity(GroupImpls.class, " where id='"+groupIds+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupImpl;
	}

	//查询用户所在的组
	public List<Group> findGroupsByUser(String userIds) {
		List<Group> groupList = new ArrayList<Group>();
		try {
			UserImpls userImpl = getDaoHibernateBase().findOneEntity(UserImpls.class, " where id='"+userIds+"'");
			Set<GroupImpls> groupImplList = userImpl.getGroupImplSet();
			for (Iterator<GroupImpls> iterator = groupImplList.iterator(); iterator.hasNext();) {
				GroupImpls groupImpl = (GroupImpls) iterator.next();
				Group group = groupImpl;
				groupList.add(group);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupList;
	}
	
	//根据一个用户和组类型来查询多个组
	public List<Group> findGroupsByUserAndGroupType(String userIds, String groupType) {
		List<Group> groupList = new ArrayList<Group>();
		try {
			UserImpls userImpl = getDaoHibernateBase().findOneEntity(UserImpls.class, " where id='"+userIds+"'");
			Set<GroupImpls> groupImplList = userImpl.getGroupImplSet();
			for (Iterator<GroupImpls> iterator = groupImplList.iterator(); iterator.hasNext();) {
				GroupImpls groupImpl = (GroupImpls) iterator.next();
				boolean groupTypeBool = groupImpl.getType().equals(groupType);
				if(groupTypeBool){
					Group group = groupImpl;
					groupList.add(group);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupList;
	}

	//查询一个用户
	public User findUserById(String userIds) {
		UserImpls userImpl = null;
		try {
			userImpl = getDaoHibernateBase().findOneEntity(UserImpls.class, " where id='"+userIds+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userImpl;
	}

	//不考虑使用
	public List<User> findUsers() {
		return null;
	}
	
	//查询组拥有的多个用户
	public List<User> findUsersByGroup(String groupIds) {
		List<User> userImplList = new ArrayList<User>();
		try {
			GroupImpls groupImpl = getDaoHibernateBase().findOneEntity(GroupImpls.class, " where id='"+groupIds+"'");
			Set<UserImpls> userImplSet = groupImpl.getUserImplSet();
			for (Iterator<UserImpls> iterator = userImplSet.iterator(); iterator.hasNext();) {
				UserImpls userImpl = (UserImpls) iterator.next();
				User user = userImpl;
				userImplList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userImplList;
	}
	
	//根据多个用户id查询多个用户
	public List<User> findUsersById(String... userIdsArr) {
		List<User> userList = new ArrayList<User>();
		StringBuffer sb = new StringBuffer(" where id='");
		for (int i = 0; i < userIdsArr.length-1; i++) {
			sb.append(userIdsArr[i]).append("' or id='");
		}
		sb.append(userIdsArr[userIdsArr.length-1]).append("'");
		try {
			List<UserImpls> userImplList = getDaoHibernateBase().findManyEntity(UserImpls.class, sb.toString());
			for (UserImpls userImpl : userImplList) {
				User user = userImpl;
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	
	
}
