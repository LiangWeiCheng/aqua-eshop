package com.aqua.office.poi.ppt;

import java.util.Date;

import org.apache.poi.hslf.model.TextRun;

public class SupplierAndPrice implements SlideModel{
	
	private String projectDescription;
	
	private String requestDepartment;
	
	private String requester;
	
	private int requesterTel;
	
	private double expenditure;
	
	private String purchaseEngineer;
	
	private int purchaseEngineerTel;
	
	private Date purchaseDate = new Date();
	
	private Date firstDate = new Date();
	
	private String method;
	
	private Date secondDate = new Date();
	
	private Date deliveryDate = new Date();
	
	private String[] suppliers;
	
	private CompareInformation[] compareInformations;
	
	private boolean approve = false;
	
	public CompareInformation[] getCompareInformations() {
		return compareInformations;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public void setCompareInformations(CompareInformation[] compareInformations) {
		this.compareInformations = compareInformations;
	}

	public String getRequestDepartment() {
		return requestDepartment;
	}

	public void setRequestDepartment(String requestDepartment) {
		this.requestDepartment = requestDepartment;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public int getRequesterTel() {
		return requesterTel;
	}

	public void setRequesterTel(int requesterTel) {
		this.requesterTel = requesterTel;
	}

	public String getExpenditure() {
		return numberFormat.format(expenditure);
	}

	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}

	public String getPurchaseEngineer() {
		return purchaseEngineer;
	}

	public void setPurchaseEngineer(String purchaseEngineer) {
		this.purchaseEngineer = purchaseEngineer;
	}

	public int getPurchaseEngineerTel() {
		return purchaseEngineerTel;
	}

	public void setPurchaseEngineerTel(int purchaseEngineerTel) {
		this.purchaseEngineerTel = purchaseEngineerTel;
	}

	public String getPurchaseDate() {
		return dateFormat.format(purchaseDate);
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getFirstDate() {
		return dateFormat.format(firstDate);
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDeliveryDate() {
		return dateFormat.format(deliveryDate);
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String[] getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String[] suppliers) {
		this.suppliers = suppliers;
	}

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

	public String getSecondDate() {
		return dateFormat.format(secondDate);
	}

	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}
	
	public void setValue(Integer i, TextRun textRun,
			SupplierAndPrice supplierAndPrice, String lineSeparator) {
		StringBuilder stringBuilder = new StringBuilder();
		switch (i) {
		case 2:
			stringBuilder.append(textRun.getRichTextRuns()[0].getText());
			stringBuilder.append(supplierAndPrice.getProjectDescription());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 3:
			String depart = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(depart.substring(0, depart.length()-1));
			stringBuilder.append(supplierAndPrice.getRequestDepartment());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 4:
			String requster = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(requster.substring(0, requster.length()-1));
			stringBuilder.append(supplierAndPrice.getRequester());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 5:
			String requesterTel = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(requesterTel.substring(0, requesterTel.length()-1));
			stringBuilder.append(supplierAndPrice.getRequesterTel());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 6:
			String expenditure = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(expenditure.substring(0, expenditure.length()-1));
			stringBuilder.append(supplierAndPrice.getExpenditure());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 7:
			String purchaseEngineer = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseEngineer.substring(0, purchaseEngineer.length()-1));
			stringBuilder.append(supplierAndPrice.getPurchaseEngineer());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 8:
			String purchaseEngineerTel = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseEngineerTel.substring(0, purchaseEngineerTel.length()-1));
			stringBuilder.append(supplierAndPrice.getPurchaseEngineerTel());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 9:
			String purchaseDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseDate.substring(0, purchaseDate.length()-1));
			stringBuilder.append(supplierAndPrice.getPurchaseDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 10:
			String firstDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(firstDate.substring(0, firstDate.length()-1));
			stringBuilder.append(supplierAndPrice.getFirstDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 11:
			String method = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(method.substring(0, method.length()-1));
			stringBuilder.append(supplierAndPrice.getMethod());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 12:
			String secondDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(secondDate.substring(0, secondDate.length()-1));
			stringBuilder.append(supplierAndPrice.getSecondDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 13:
			String deliveryDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(deliveryDate.substring(0, deliveryDate.length()-1));
			stringBuilder.append(supplierAndPrice.getDeliveryDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 15:
			if(supplierAndPrice.isApprove()){
				textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0],textRun.getRichTextRuns()[0].getText().replace("批准 Approval	  ", "批准 Approval	 "+approveBy));
			}else {
				textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0],textRun.getRichTextRuns()[0].getText().replace("待定 To be determined   ", "待定 To be determined   "+approveBy));
			}
			break;
		case 22:
			stringBuilder.append(supplierAndPrice.getSuppliers()[0]);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 23:
			stringBuilder.append(supplierAndPrice.getCompareInformations()[0].getSupplier());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 24:
			stringBuilder.append(supplierAndPrice.getCompareInformations()[0].getInformation());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 27:
			stringBuilder.append(supplierAndPrice.getSuppliers()[1]);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 28:
			stringBuilder.append(supplierAndPrice.getCompareInformations()[1].getSupplier());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 29:
			stringBuilder.append(supplierAndPrice.getCompareInformations()[1].getInformation());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 32:
			stringBuilder.append(supplierAndPrice.getSuppliers()[2]);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 33:
			stringBuilder.append(supplierAndPrice.getCompareInformations()[2].getSupplier());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 34:
			stringBuilder.append(supplierAndPrice.getCompareInformations()[2].getInformation());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		default:
			break;
		}
		
	}
	
}
