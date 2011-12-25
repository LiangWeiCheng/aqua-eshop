package com.aqua.office.poi.ppt;

import java.util.Date;

import org.apache.poi.hslf.model.TextRun;

public class PurchaseMethod implements SlideModel{
	
	private String projectDescription;
	
	private String requestDepartment;
	
	private String requester;
	
	private int requesterTel;
	
	private double expenditure;
	
	private String purchaseEngineer;
	
	private int purchaseEngineerTel;
	
	private Date purchaseDate=new Date();
	
	private Date firstDate=new Date();
	
	private Date deliveryDate=new Date();
	
	private String[] suppliers;
	
	private String[] remarks;
	
	private String method;
	
	private int approveMethod=1;	//1:委托招投标;2:多家询价;3:单一货源.
	
	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
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

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getApproveMethod() {
		return approveMethod;
	}

	public void setApproveMethod(int approveMethod) {
		this.approveMethod = approveMethod;
	}
	
	public void setValue(Integer i, TextRun textRun, PurchaseMethod purchaseMethod, String lineSeparator) {
		StringBuilder stringBuilder = new StringBuilder();
		switch (i) {
		case 2:
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[3], purchaseMethod.getProjectDescription());
			break;
		case 3:
			String depart = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(depart.substring(0, depart.length()-1));
			stringBuilder.append(purchaseMethod.getRequestDepartment());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 4:
			String requster = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(requster.substring(0, requster.length()-1));
			stringBuilder.append(purchaseMethod.getRequester());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 5:
			String requesterTel = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(requesterTel.substring(0, requesterTel.length()-1));
			stringBuilder.append(purchaseMethod.getRequesterTel());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 6:
			String expenditure = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(expenditure.substring(0, expenditure.length()-1));
			stringBuilder.append(purchaseMethod.getExpenditure());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 7:
			String purchaseEngineer = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseEngineer.substring(0, purchaseEngineer.length()-1));
			stringBuilder.append(purchaseMethod.getPurchaseEngineer());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 8:
			String purchaseEngineerTel = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseEngineerTel.substring(0, purchaseEngineerTel.length()-1));
			stringBuilder.append(purchaseMethod.getPurchaseEngineerTel());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 9:
			String purchaseDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseDate.substring(0, purchaseDate.length()-1));
			stringBuilder.append(purchaseMethod.getPurchaseDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 10:
			String firstDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(firstDate.substring(0, firstDate.length()-1));
			stringBuilder.append(purchaseMethod.getFirstDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 11:
			String deliveryDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(deliveryDate.substring(0, deliveryDate.length()-1));
			stringBuilder.append(purchaseMethod.getDeliveryDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 12:
			String[] suppliers = purchaseMethod.getSuppliers();
			stringBuilder.append(lineSeparator);
			for (int j = 0; j < suppliers.length; j++) {
				stringBuilder.append(j+1);
				stringBuilder.append("、");
				stringBuilder.append(suppliers[j]);
				stringBuilder.append(lineSeparator);
			}
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[1], textRun.getRichTextRuns()[1].getText()+stringBuilder.toString());
			break;
		case 13:
			String[] remarks = purchaseMethod.getRemarks();
			for (int j = 0; j < remarks.length; j++) {
				stringBuilder.append(j+1);
				stringBuilder.append("、");
				stringBuilder.append(remarks[j]);
				stringBuilder.append(lineSeparator);
			}
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], textRun.getRichTextRuns()[0].getText()+stringBuilder.toString());
			break;
		case 14:
			int approveMethod = purchaseMethod.getApproveMethod();
			if(approveMethod==1){
				textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], textRun.getRichTextRuns()[0].getText().replace("委托招投标	", "委托招投标   "+approveBy));
			}else if (approveMethod==2) {
				textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], textRun.getRichTextRuns()[0].getText().replace("多家询价	", "多家询价	"+approveBy));
			}else if (approveMethod==3) {
				textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], textRun.getRichTextRuns()[0].getText().replace("单一货源", "单一货源     "+approveBy));
			}
			break;
		default:
			break;
		}
	}

}
