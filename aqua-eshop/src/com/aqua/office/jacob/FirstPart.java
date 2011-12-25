package com.aqua.office.jacob;

import java.text.DecimalFormat;

public class FirstPart {
	
	private String projectName;
	
	private String requestDepartment;
	
	private double expenditure;
	
	private String method;
	
	private String remark;
	
	private DecimalFormat numberFormat = new DecimalFormat("#,###.00");
	
	public FirstPart(String projectName, String requestDepartment, double expenditure, String method, String remark){
		this.projectName=projectName;
		this.requestDepartment=requestDepartment;
		this.expenditure=expenditure;
		this.method=method;
		this.remark=remark;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRequestDepartment() {
		return requestDepartment;
	}

	public void setRequestDepartment(String requestDepartment) {
		this.requestDepartment = requestDepartment;
	}

	public String getExpenditure() {
		return numberFormat.format(expenditure);
	}

	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
