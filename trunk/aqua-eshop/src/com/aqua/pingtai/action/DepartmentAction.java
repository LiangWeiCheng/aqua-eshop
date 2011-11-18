package com.aqua.pingtai.action;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Department;
import com.aqua.pingtai.service.DepartmentService;

@SuppressWarnings({"serial"})
public class DepartmentAction extends BaseAction {
	
	@Resource(name="departmentServiceImpl")
	private DepartmentService departmentServiceImpl;
	
	public Department department;
	private List<Department> departmentList;
	
	/**
	 * 保存部门
	 * @return
	 */
	public String saveDepartment(){
		if(null!=department){
			departmentServiceImpl.saveDepartment(department);
		}
		returnPageURL = "/pingTai/departmentPingTaiAction!departmentList.action";
		return "redirect";
	}
	
	/**
	 * 查看部门
	 * @return
	 */
	public String viewDepartment(){
		if(null!=department && 0l!=department.getIds()){
			department = departmentServiceImpl.getOneDepartment(department.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/department/departmentView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新部门
	 * @return
	 */
	public String toUpdateDepartment(){
		if(null!=department && 0l!=department.getIds()){
			department = departmentServiceImpl.getOneDepartment(department.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/department/departmentUpdate.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新部门
	 * @return
	 */
	public String updateDepartment(){
		if(null!=department && null!=department.getIds() && 0l!=department.getIds()){
			departmentServiceImpl.updateDepartment(department);
		}
		returnPageURL = "/pingTai/departmentPingTaiAction!departmentList.action";
		return "redirect";
	}
	
	/**
	 * 删除部门
	 * @return
	 */
	public String deleteDepartment(){
		if(null!=department && null!=department.getIds() && 0l!=department.getIds()){
			department = departmentServiceImpl.getOneDepartment(department.getIds());
			if(department.getDepartmentSet().size()!=0){
				Context.getRequest().setAttribute("operatorMessage", "请先删除拥有的子部门!");
				return "operatorMessage";
			}
			departmentServiceImpl.deleteDepartment(department);
		}
		returnPageURL = "/pingTai/departmentPingTaiAction!departmentList.action";
		return "redirect";
	}

	/**
	 * 显示部门
	 * @return
	 */
	public String departmentList(){
		departmentList = departmentServiceImpl.getDepartmentByHQL("");
		returnPageURL = "/WEB-INF/jsp/pingtai/department/departmentList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 显示部门对话框
	 * @return
	 */
	public String departmentListDialog(){
		departmentList = departmentServiceImpl.getDepartmentByHQL("");
		returnPageURL = "/WEB-INF/jsp/pingtai/department/departmentListDialog.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.DepartmentAction";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		StringBuffer sb = new StringBuffer();
		
		String names = queryParameter.getParameter("names");
		if(null!=names && !names.equals("")){
			sb.append(" names like '%").append(names.trim()).append("%'").append(" and ");
		}
		
		String parentDepartment = queryParameter.getParameter("parentDepartment");
		if(null!=parentDepartment && !parentDepartment.equals("")){
			sb.append(" parentDepartment.names like '%").append(parentDepartment.trim()).append("%'").append(" and ");
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public DepartmentService getDepartmentServiceImpl() {
		return departmentServiceImpl;
	}

	public void setDepartmentServiceImpl(DepartmentService departmentServiceImpl) {
		this.departmentServiceImpl = departmentServiceImpl;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	

}
