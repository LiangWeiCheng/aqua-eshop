package com.aqua.pingtai.action;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Role;
import com.aqua.pingtai.service.OperatorService;
import com.aqua.pingtai.service.RoleService;

@SuppressWarnings({"serial"})
public class RoleAction extends BaseAction {
	
	@Resource(name="roleServiceImpl")
	private RoleService roleServiceImpl;
	@Resource(name="operatorServiceImpl")
	private OperatorService operatorServiceImpl;
	
	public Role role;
	
	/**
	 * 保存角色
	 * @return
	 */
	public String saveRole(){
		if(null!=role){
			roleServiceImpl.saveRole(role);
		}
		returnPageURL = "/pingTai/rolePingTaiAction!roleList.action";
		return "redirect";
	}
	
	/**
	 * 查看角色
	 * @return
	 */
	public String viewRole(){
		if(null!=role && 0l!=role.getIds()){
			role = roleServiceImpl.getOneRole(role.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/role/roleView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新角色
	 * @return
	 */
	public String toUpdateRole(){
		if(null!=role && 0l!=role.getIds()){
			role = roleServiceImpl.getOneRole(role.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/role/roleUpdate.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新角色
	 * @return
	 */
	public String updateRole(){
		if(null!=role && null!=role.getIds() && 0l!=role.getIds()){
			roleServiceImpl.updateRole(role);
		}
		returnPageURL = "/pingTai/rolePingTaiAction!roleList.action";
		return "redirect";
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	public String deleteRole(){
		if(null!=role && null!=role.getIds() && 0l!=role.getIds()){
			role = roleServiceImpl.getOneRole(role.getIds());
			if(role.getGroupSet().size()!=0){
				Context.getRequest().setAttribute("operatorMessage", "请先解除角色拥有的组关系!");
				return "operatorMessage";
			}
			if(role.getOperatorSet().size()!=0){
				Context.getRequest().setAttribute("operatorMessage", "请先解除角色拥有的功能关系!");
				return "operatorMessage";
			}
			roleServiceImpl.deleteRole(role);
		}
		returnPageURL = "/pingTai/rolePingTaiAction!roleList.action";
		return "redirect";
	}

	/**
	 * 分页显示角色
	 * @return
	 */
	public String roleList(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by names asc";
			roleServiceImpl.splitPageQueryRole(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/role/roleList.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.RoleAction";
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

	public RoleService getRoleServiceImpl() {
		return roleServiceImpl;
	}

	public void setRoleServiceImpl(RoleService roleServiceImpl) {
		this.roleServiceImpl = roleServiceImpl;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public OperatorService getOperatorServiceImpl() {
		return operatorServiceImpl;
	}

	public void setOperatorServiceImpl(OperatorService operatorServiceImpl) {
		this.operatorServiceImpl = operatorServiceImpl;
	}

}
