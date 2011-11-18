package com.aqua.pingtai.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Operator;
import com.aqua.pingtai.entity.bean.authority.Role;
import com.aqua.pingtai.service.OperatorService;
import com.aqua.pingtai.service.RoleService;

@SuppressWarnings({"serial"})
public class OperatorAction extends BaseAction {
	
	@Resource(name="operatorServiceImpl")
	private OperatorService operatorServiceImpl;
	@Resource(name="roleServiceImpl")
	private RoleService roleServiceImpl;
	
	public Operator operator;
	public List<Operator> operatorList;
	
	public Role role;
	
	/**
	 * 保存操作
	 * @return
	 */
	public String saveOperator(){
		if(null!=operator){
			operatorServiceImpl.saveOperator(operator);
		}
		returnPageURL = "/pingTai/operatorPingTaiAction!operatorList.action";
		return "redirect";
	}
	
	/**
	 * 查看操作
	 * @return
	 */
	public String viewOperator(){
		if(null!=operator && 0l!=operator.getIds()){
			operator = operatorServiceImpl.getOneOperator(operator.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/operator/operatorView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新操作
	 * @return
	 */
	public String toUpdateOperator(){
		if(null!=operator && 0l!=operator.getIds()){
			operator = operatorServiceImpl.getOneOperator(operator.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/operator/operatorUpdate.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新操作
	 * @return
	 */
	public String updateOperator(){
		if(null!=operator && null!=operator.getIds() && 0l!=operator.getIds()){
			operatorServiceImpl.updateOperator(operator);
		}
		returnPageURL = "/pingTai/operatorPingTaiAction!operatorList.action";
		return "redirect";
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	public String deleteOperator(){
		if(null!=operator && null!=operator.getIds() && 0l!=operator.getIds()){
			operator = operatorServiceImpl.getOneOperator(operator.getIds());
			if(operator.getRoleSet().size()!=0){
				Context.getRequest().setAttribute("operatorMessage", "请先解除功能拥有的角色关系!");
				return "operatorMessage";
			}
			operatorServiceImpl.deleteOperator(operator);
		}
		returnPageURL = "/pingTai/operatorPingTaiAction!operatorList.action";
		return "redirect";
	}

	
	/**
	 * 分页显示操作
	 * @return
	 */
	public String operatorList(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by type,names asc";
			operatorServiceImpl.splitPageQueryOperator(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/operator/operatorList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 查看角色拥有的操作
	 * @return
	 */
	public String roleOperatorView(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			if(null!=role && null!=role.getIds() && 0l!=role.getIds()){
				role = roleServiceImpl.getOneRole(role.getIds());
				List<Operator> operatorList = new ArrayList<Operator>(role.getOperatorSet());
				if(operatorList.size()!=0){
					StringBuffer sb = new StringBuffer(" and ids in(");
					for (int i = 0; i < operatorList.size()-1; i++) {
						sb.append(operatorList.get(i).getIds()).append(",");
					}
					sb.append(operatorList.get(operatorList.size()-1).getIds());
					sb.append(")");
					hqlFilter += sb.toString();
				}else{
					hqlFilter += " and ids in(0)";
				}
			}
			hqlFilter += " order by type,names asc";
			operatorServiceImpl.splitPageQueryOperator(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/role/roleOperatorList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 查看角色没有的操作
	 * @return
	 */
	public String roleNoOperatorView(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			if(null!=role && null!=role.getIds() && 0l!=role.getIds()){
				role = roleServiceImpl.getOneRole(role.getIds());
				List<Operator> operatorList = new ArrayList<Operator>(role.getOperatorSet());
				if(operatorList.size()!=0){
					StringBuffer sb = new StringBuffer(" and ids not in(");
					for (int i = 0; i < operatorList.size()-1; i++) {
						sb.append(operatorList.get(i).getIds()).append(",");
					}
					sb.append(operatorList.get(operatorList.size()-1).getIds());
					sb.append(")");
					hqlFilter += sb.toString();
				}
			}
			hqlFilter += " order by type,names asc";
			operatorServiceImpl.splitPageQueryOperator(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/role/roleNoOperatorList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 角色功能删除
	 * @return
	 */
	public String roleOperatorDelete(){
		if(null!=role && null!=role.getIds() && 0l!=role.getIds() && null!=operator && null!=operator.getIds()){
			role = roleServiceImpl.getOneRole(role.getIds());
			operator = operatorServiceImpl.getOneOperator(operator.getIds());
			role.getOperatorSet().remove(operator);
			roleServiceImpl.updateRole(role);
			roleServiceImpl.updateRoleByCache(role);
		}
		returnPageURL = "/pingTai/operatorPingTaiAction!roleOperatorView.action?role.ids="+role.getIds();
		return "redirect";
	}
	
	/**
	 * 角色功能添加
	 * @return
	 */
	public String roleOperatorAdd(){
		if(null!=role && null!=role.getIds() && 0l!=role.getIds() && null!=operator && null!=operator.getIds()){
			role = roleServiceImpl.getOneRole(role.getIds());
			operator = operatorServiceImpl.getOneOperator(operator.getIds());
			role.getOperatorSet().add(operator);
			roleServiceImpl.updateRole(role);
			roleServiceImpl.updateRoleByCache(role);
		}
		returnPageURL = "/pingTai/operatorPingTaiAction!roleNoOperatorView.action?role.ids="+role.getIds();
		return "redirect";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.RoleAction";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		StringBuffer sb = new StringBuffer();
		
		String type = queryParameter.getParameter("type");
		if(null!=type && !type.equals("")){
			sb.append(" type like '%").append(type.trim()).append("%'").append(" and ");
		}
		
		String names = queryParameter.getParameter("names");
		if(null!=names && !names.equals("")){
			sb.append(" names like '%").append(names.trim()).append("%'").append(" and ");
		}
		
		String url = queryParameter.getParameter("url");
		if(null!=url && !url.equals("")){
			sb.append(" url like '%").append(url.trim()).append("%'").append(" and ");
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

	public OperatorService getOperatorServiceImpl() {
		return operatorServiceImpl;
	}

	public void setOperatorServiceImpl(OperatorService operatorServiceImpl) {
		this.operatorServiceImpl = operatorServiceImpl;
	}

	public RoleService getRoleServiceImpl() {
		return roleServiceImpl;
	}

	public void setRoleServiceImpl(RoleService roleServiceImpl) {
		this.roleServiceImpl = roleServiceImpl;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Operator> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<Operator> operatorList) {
		this.operatorList = operatorList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	


}
