package com.aqua.pingtai.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Department;
import com.aqua.pingtai.entity.bean.authority.Group;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.DepartmentService;
import com.aqua.pingtai.service.GroupService;
import com.aqua.pingtai.service.UserService;

@SuppressWarnings({"serial"})
public class UserAction extends BaseAction {
	
	@Resource(name="userServiceImpl")
	private UserService userServiceImpl;
	@Resource(name="groupServiceImpl")
	private GroupService groupServiceImpl;
	@Resource(name="departmentServiceImpl")
	private DepartmentService departmentServiceImpl;
	
	private Long ids;
	private User user;
	private List<User> userList;
	private List<Group> allGroupList;//所有的组
	private List<Long> groupIdsList;//用户拥有的组ids
	private List<Department> departmentList;
	
	/**
	 * 添加用户
	 * @return
	 */
	public String userAdd(){
		if(null!=user){
			userServiceImpl.saveUser(user);
		}
		returnPageURL = "/pingTai/userPingTaiAction!userList.action";
		return "redirect";
	}
	
	/**
	 * 查看用户
	 * @return
	 */
	public String viewUser(){
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			user = userServiceImpl.getUser(user.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/user/userView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新用户
	 * @return
	 */
	public String toUpdateUser(){
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			user = userServiceImpl.getUser(user.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/user/userUpdate.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新用户
	 * @return
	 */
	public String updateUser(){
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			userServiceImpl.updateUser(user);
		}
		returnPageURL = "/pingTai/userPingTaiAction!userList.action";
		return "redirect";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String deleteUser(){
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			user = userServiceImpl.getUser(user.getIds());
			if(user.getGroupSet().size()!=0){//解除用户组关系
				Context.getRequest().setAttribute("operatorMessage", "请先解除用户拥有的组关系!");
				return "operatorMessage";
			}
			userServiceImpl.deleteUser(user);
		}
		returnPageURL = "/pingTai/userPingTaiAction!userList.action";
		return "redirect";
	}
	
	/**
	 * 用户列表
	 * @return
	 */
	public String userList(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by userName, userInfo.names asc createdDate desc";
			userServiceImpl.splitPageQueryUser(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/user/userList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 用户列表对话框
	 * @return
	 */
	public String userListDialog(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by userName, userInfo.names asc createdDate desc";
			userServiceImpl.splitPageQueryUser(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/user/userListDialog.jsp";
		return "dispatcher";
	}
	
	/**
	 * 用户列表对话框Tree
	 * @return
	 */
	public String userListDialogTree(){
		try {
			departmentList = departmentServiceImpl.getDepartmentByHQL("");
			userList = userServiceImpl.queryUserListByHql(" where userClass='userClass_houTai' ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/user/userListTree.jsp";
		return "dispatcher";
	}
	
	/**
	 * 查看用户所在的组
	 * @return
	 */
	public String userGroupView(){
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			user = userServiceImpl.getUser(user.getIds());
			groupIdsList = new ArrayList<Long>();
			List<Group> userByGroupList = new ArrayList<Group>(user.getGroupSet());
			for (Group group : userByGroupList) {
				groupIdsList.add(group.getIds());
			}
		}
		allGroupList = groupServiceImpl.getGroupByHql(" order by names asc ");
		returnPageURL = "/WEB-INF/jsp/pingtai/user/userByGroupView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备修改用户所在的组
	 * @return
	 */
	public String toUserGroupUpdate(){
		if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
			user = userServiceImpl.getUser(user.getIds());
			groupIdsList = new ArrayList<Long>();
			List<Group> userByGroupList = new ArrayList<Group>(user.getGroupSet());
			for (Group group : userByGroupList) {
				groupIdsList.add(group.getIds());
			}
		}
		allGroupList = groupServiceImpl.getGroupByHql(" order by names asc ");
		returnPageURL = "/WEB-INF/jsp/pingtai/user/userByGroup.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新用户所在的组
	 * @return
	 */
	public String userGroupUpdate(){
		if(null!=groupIdsList && null!=user && null!=user.getIds() && 0l!=user.getIds()){
			user = userServiceImpl.getUser(user.getIds());
			
			Set<Group> userGroupSet = null;
			
			if(groupIdsList.size()!=0){
				StringBuffer hql = new StringBuffer(" where ids=");
				for (int i = 0; i < groupIdsList.size()-1; i++) {
					hql.append(groupIdsList.get(i)).append(" or ids=");
				}
				hql.append(groupIdsList.get(groupIdsList.size()-1));
				List<Group> userGroupList = groupServiceImpl.getGroupByHql(hql.toString());
				userGroupSet = new HashSet<Group>(userGroupList);
			}else{
				userGroupSet = new HashSet<Group>();
			}
			
			user.setGroupSet(userGroupSet);
			userServiceImpl.updateUser(user);
		}
		returnPageURL = "/pingTai/userPingTaiAction!userGroupView.action?user.ids="+user.getIds();
		return "redirect";
	}
	
	/**
	 * 用户能访问的用户
	 * @return
	 */
	public String rowUserList(){
		try {
			if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
				user = userServiceImpl.getUser(user.getIds());
				Set<User> rowUserSet = user.getRowUserSet();
				String idsStr = null;
				if(rowUserSet.size()>0){
					StringBuffer idsSb = new StringBuffer();
					for (User user : rowUserSet) {
						idsSb.append(user.getIds()).append(",");
					}
					idsStr = idsSb.toString();
					idsStr = idsStr.substring(0, idsStr.length()-1);
				}else{
					idsStr = "0";
				}
				String hqlFilter = initQueryList(false);//过滤HQL
				hqlFilter += " and ids in("+ idsStr +") ";
				hqlFilter += " order by userName, userInfo.names asc createdDate desc";
				userServiceImpl.splitPageQueryUser(hqlFilter, queryResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/user/rowUserList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 删除用户能访问的用户
	 * @return
	 */
	public String rowUserListDelete(){
		try {
			if(null!=user && null!=user.getIds() && 0l!=user.getIds() && null!=ids && 0l!=ids){
				user = userServiceImpl.getUser(user.getIds());
				Set<User> rowUserSet = user.getRowUserSet();
				User userDelete = userServiceImpl.getUser(ids);
				rowUserSet.remove(userDelete);//删除
				userServiceImpl.updateUser(user);
				if(Context.getCurrentUser().getIds().equals(user.getIds())){
					Context.setCurrentUser(user);//重设当前登陆用户
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/pingTai/userPingTaiAction!rowUserList.action?user.ids="+user.getIds();
		return "redirect";
	}

	/**
	 * 用户不能访问的用户
	 * @return
	 */
	public String rowUserListNo(){
		try {
			if(null!=user && null!=user.getIds() && 0l!=user.getIds()){
				user = userServiceImpl.getUser(user.getIds());
				Set<User> rowUserSet = user.getRowUserSet();
				String idsStr = null;
				if(rowUserSet.size()>0){
					StringBuffer idsSb = new StringBuffer();
					for (User user : rowUserSet) {
						idsSb.append(user.getIds()).append(",");
					}
					idsStr = idsSb.toString();
					idsStr = idsStr.substring(0, idsStr.length()-1);
				}else{
					idsStr = "0";
				}
				String hqlFilter = initQueryList(false);//过滤HQL
				hqlFilter += " and ids not in("+ idsStr +")";
				hqlFilter += " order by department.names, userName, userInfo.names asc createdDate desc";
				userServiceImpl.splitPageQueryUser(hqlFilter, queryResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/user/rowUserListNo.jsp";
		return "dispatcher";
	}
	
	/**
	 * 添加用户能访问的用户
	 * @return
	 */
	public String rowUserListAdd(){
		try {
			if(null!=user && null!=user.getIds() && 0l!=user.getIds() && null!=ids && 0l!=ids){
				boolean bool = Context.getCurrentUser().getIds().equals(user.getIds());
				/*if(bool){
					user = userServiceImpl.getUserByHql(user.getIds());//数据库查询
				}else{
					user = userServiceImpl.getUser(user.getIds());//直接取缓存
				}*/
				user = userServiceImpl.getUser(user.getIds());//直接取缓存
				Set<User> rowUserSet = user.getRowUserSet();
				User userAdd = userServiceImpl.getUser(ids);
				rowUserSet.add(userAdd);//添加
				userServiceImpl.updateUser(user);
				if(bool){
					Context.setCurrentUser(user);//重设当前登陆用户
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/pingTai/userPingTaiAction!rowUserList.action?user.ids="+user.getIds();
		return "redirect";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.UserAction.action";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		String userName = queryParameter.getParameter("userName");//用户名
		String userClass = queryParameter.getParameter("userClass");//用户分类
		String names = queryParameter.getParameter("names");//姓名
		String sex = queryParameter.getParameter("sex");//性别
		String email = queryParameter.getParameter("email");//邮箱
		String mobile = queryParameter.getParameter("mobile");//手机
		String telephone = queryParameter.getParameter("telephone");//电话
		String idCard = queryParameter.getParameter("idCard");//省份证
		String qq = queryParameter.getParameter("qq");//QQ
		String birthday = queryParameter.getParameter("birthday");//生日
		
		StringBuffer sb = new StringBuffer();
		
		if(null!=userName && !userName.equals("")){
			sb.append(" userName like '%").append(userName.trim()).append("%'").append(" and ");
		}
		if(null!=userClass && !userClass.equals("")){
			sb.append(" userClass like '%").append(userClass.trim()).append("%'").append(" and ");
		}
		if(null!=names && !names.equals("")){
			sb.append(" userInfo.names like '%").append(names.trim()).append("%'").append(" and ");
		}
		if(null!=sex && !sex.equals("")){
			sb.append(" userInfo.sex like '%").append(sex.trim()).append("%'").append(" and ");
		}
		if(null!=email && !email.equals("")){
			sb.append(" userInfo.email like '%").append(email.trim()).append("%'").append(" and ");
		}
		if(null!=mobile && !mobile.equals("")){
			sb.append(" userInfo.mobile like '%").append(mobile.trim()).append("%'").append(" and ");
		}
		if(null!=telephone && !telephone.equals("")){
			sb.append(" userInfo.telephone like '%").append(telephone.trim()).append("%'").append(" and ");
		}
		if(null!=idCard && !idCard.equals("")){
			sb.append(" userInfo.idCard like '%").append(idCard.trim()).append("%'").append(" and ");
		}
		if(null!=qq && !qq.equals("")){
			sb.append(" userInfo.qq like '%").append(qq.trim()).append("%'").append(" and ");
		}
		if(null!=birthday && !birthday.equals("")){
			sb.append(" userInfo.birthday='").append(birthday.trim()).append("'");
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public List<Group> getAllGroupList() {
		return allGroupList;
	}

	public void setAllGroupList(List<Group> allGroupList) {
		this.allGroupList = allGroupList;
	}

	public GroupService getGroupServiceImpl() {
		return groupServiceImpl;
	}

	public void setGroupServiceImpl(GroupService groupServiceImpl) {
		this.groupServiceImpl = groupServiceImpl;
	}

	public List<Long> getGroupIdsList() {
		return groupIdsList;
	}

	public void setGroupIdsList(List<Long> groupIdsList) {
		this.groupIdsList = groupIdsList;
	}

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public DepartmentService getDepartmentServiceImpl() {
		return departmentServiceImpl;
	}

	public void setDepartmentServiceImpl(DepartmentService departmentServiceImpl) {
		this.departmentServiceImpl = departmentServiceImpl;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	
}
