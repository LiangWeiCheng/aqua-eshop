package com.aqua.office.jacob;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondPart {

	private String projectName;

	private String requestDepartment;

	private double expenditure;

	private String supplier;

	private String price;

	private Date approveDate;

	private String method;

	private boolean approveCondition;

	private String remark;
	
	private DecimalFormat numberFormat = new DecimalFormat("#,###.00");
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public SecondPart(String projectName, String requestDepartment,
			double expenditure, String supplier, String price,
			Date approveDate, String method, boolean approveCondition,
			String remark) {
		this.projectName=projectName;
		this.requestDepartment=requestDepartment;
		this.expenditure=expenditure;
		this.supplier=supplier;
		this.price=price;
		this.approveDate=approveDate;
		this.method=method;
		this.approveCondition=approveCondition;
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getApproveDate() {
		return dateFormat.format(approveDate);
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public boolean isApproveCondition() {
		return approveCondition;
	}

	public void setApproveCondition(boolean approveCondition) {
		this.approveCondition = approveCondition;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
