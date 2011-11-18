package com.aqua.pingtai.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Group;
import com.aqua.pingtai.entity.bean.authority.Role;
import com.aqua.pingtai.service.GroupService;
import com.aqua.pingtai.service.RoleService;

@SuppressWarnings({"serial"})
public class GroupAction extends BaseAction {
	
	@Resource(name="groupServiceImpl")
	private GroupService groupServiceImpl;
	@Resource(name="roleServiceImpl")
	private RoleService roleServiceImpl;
	
	public Group group;
	public List<Group> groupList;
	
	private List<Role> allRoleList;//所有的组
	private List<Long> roleIdsList;//用户拥有的组的ids
	
	/**
	 * 保存组
	 * @return
	 */
	public String saveGroup(){
		if(null!=group){
			groupServiceImpl.saveGroup(group);
		}
		returnPageURL = "/pingTai/groupPingTaiAction!groupList.action";
		return "redirect";
	}
	
	/**
	 * 查看组
	 * @return
	 */
	public String viewGroup(){
		if(null!=group && 0l!=group.getIds()){
			group = groupServiceImpl.getOneGroup(group.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/group/groupView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新组
	 * @return
	 */
	public String toUpdateGroup(){
		if(null!=group && 0l!=group.getIds()){
			group = groupServiceImpl.getOneGroup(group.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/group/groupUpdate.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新组
	 * @return
	 */
	public String updateGroup(){
		if(null!=group && null!=group.getIds() && 0l!=group.getIds()){
			groupServiceImpl.updateGroup(group);
		}
		returnPageURL = "/pingTai/groupPingTaiAction!groupList.action";
		return "redirect";
	}
	
	/**
	 * 删除组
	 * @return
	 */
	public String deleteGroup(){
		if(null!=group && null!=group.getIds() && 0l!=group.getIds()){
			group = groupServiceImpl.getOneGroup(group.getIds());
			if(group.getUserSet().size()!=0){
				Context.getRequest().setAttribute("operatorMessage", "请先解除组拥有的用户关系!");
				return "operatorMessage";
			}
			if(group.getRoleSet().size()!=0){
				Context.getRequest().setAttribute("operatorMessage", "请先解除组拥有的角色关系!");
				return "operatorMessage";
			}
			groupServiceImpl.deleteGroup(group);
		}
		returnPageURL = "/pingTai/groupPingTaiAction!groupList.action";
		return "redirect";
	}

	/**
	 * 分页显示组
	 * @return
	 */
	public String groupList(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by names asc";
			groupServiceImpl.splitPageQueryGroup(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/group/groupList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 查看组所拥有的角色
	 * @return
	 */
	public String groupRoleView(){
		if(null!=group && null!=group.getIds() && 0l!=group.getIds()){
			group = groupServiceImpl.getOneGroup(group.getIds());
			roleIdsList = new ArrayList<Long>();
			List<Role> groupByRoleList = new ArrayList<Role>(group.getRoleSet());
			for (Role role : groupByRoleList) {
				roleIdsList.add(role.getIds());
			}
		}
		allRoleList = roleServiceImpl.getRoleByHql(" order by names asc ");
		returnPageURL = "/WEB-INF/jsp/pingtai/group/groupByRoleView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新组所拥有的角色
	 * @return
	 */
	public String toGroupRoleUpdate(){
		if(null!=group && null!=group.getIds() && 0l!=group.getIds()){
			group = groupServiceImpl.getOneGroup(group.getIds());
			roleIdsList = new ArrayList<Long>();
			List<Role> groupByRoleList = new ArrayList<Role>(group.getRoleSet());
			for (Role role : groupByRoleList) {
				roleIdsList.add(role.getIds());
			}
		}
		allRoleList = roleServiceImpl.getRoleByHql(" order by names asc ");
		returnPageURL = "/WEB-INF/jsp/pingtai/group/groupByRole.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新组拥有的角色
	 * @return
	 */
	public String groupRoleUpdate(){
		if(null!=roleIdsList && null!=group && null!=group.getIds() && 0l!=group.getIds()){
			group = groupServiceImpl.getOneGroup(group.getIds());
			
			Set<Role> groupRoleSet = null;
			
			if(roleIdsList.size()!=0){
				StringBuffer hql = new StringBuffer(" where ids=");
				for (int i = 0; i < roleIdsList.size()-1; i++) {
					hql.append(roleIdsList.get(i)).append(" or ids=");
				}
				hql.append(roleIdsList.get(roleIdsList.size()-1));
				List<Role> groupRoleList = roleServiceImpl.getRoleByHql(hql.toString());
				groupRoleSet = new HashSet<Role>(groupRoleList);
			}else{
				groupRoleSet = new HashSet<Role>();
			}
			
			group.setRoleSet(groupRoleSet);
			groupServiceImpl.updateGroup(group);
		}
		returnPageURL = "/pingTai/groupPingTaiAction!groupRoleView.action?group.ids="+group.getIds();
		return "redirect";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.GroupAction";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		StringBuffer sb = new StringBuffer();
		
		String names = queryParameter.getParameter("names");
		if(null!=names && !names.equals("")){
			sb.append(" names like '%").append(names.trim()).append("%'").append(" and ");
		}
		
		String description = queryParameter.getParameter("description");
		if(null!=description && !description.equals("")){
			sb.append(" description like '%").append(description.trim()).append("%'").append(" and ");
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public GroupService getGroupServiceImpl() {
		return groupServiceImpl;
	}

	public void setGroupServiceImpl(GroupService groupServiceImpl) {
		this.groupServiceImpl = groupServiceImpl;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public List<Role> getAllRoleList() {
		return allRoleList;
	}

	public void setAllRoleList(List<Role> allRoleList) {
		this.allRoleList = allRoleList;
	}

	public List<Long> getRoleIdsList() {
		return roleIdsList;
	}

	public void setRoleIdsList(List<Long> roleIdsList) {
		this.roleIdsList = roleIdsList;
	}

	public RoleService getRoleServiceImpl() {
		return roleServiceImpl;
	}

	public void setRoleServiceImpl(RoleService roleServiceImpl) {
		this.roleServiceImpl = roleServiceImpl;
	}

}
