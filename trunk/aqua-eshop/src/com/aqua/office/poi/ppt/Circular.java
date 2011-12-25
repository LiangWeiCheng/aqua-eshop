package com.aqua.office.poi.ppt;

import java.util.Date;

import org.apache.poi.hslf.model.TextRun;

public class Circular implements SlideModel{
	
private String projectDescription;
	
	private String requestDepartment;
	
	private String requester;
	
	private int requesterTel;
	
	private double expenditure;
	
	private String purchaseEngineer;
	
	private int purchaseEngineerTel;
	
	private Date purchaseDate=new Date();
	
	private Date firstDate=new Date();
	
	private Date secondDate=new Date();
	
	private Date deliveryDate=new Date();
	
	private String[] projectStatus;
	
	private String[] problems;
	
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

	public String getSecondDate() {
		return dateFormat.format(secondDate);
	}

	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}

	public String getDeliveryDate() {
		return dateFormat.format(deliveryDate);
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String[] getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String[] projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String[] getProblems() {
		return problems;
	}

	public void setProblems(String[] problems) {
		this.problems = problems;
	}
	
	public void setValue(Integer i, TextRun textRun, Circular circular,
			String lineSeparator) {
		StringBuilder stringBuilder = new StringBuilder();
		switch (i) {
		case 2:
			stringBuilder.append(circular.getProjectDescription());
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[1], stringBuilder.toString());
			break;
		case 3:
			String depart = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(depart.substring(0, depart.length()-1));
			stringBuilder.append(circular.getRequestDepartment());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 4:
			String requster = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(requster.substring(0, requster.length()-1));
			stringBuilder.append(circular.getRequester());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 5:
			String requesterTel = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(requesterTel.substring(0, requesterTel.length()-1));
			stringBuilder.append(circular.getRequesterTel());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 6:
			String expenditure = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(expenditure.substring(0, expenditure.length()-1));
			stringBuilder.append(circular.getExpenditure());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 7:
			String purchaseEngineer = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseEngineer.substring(0, purchaseEngineer.length()-1));
			stringBuilder.append(circular.getPurchaseEngineer());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 8:
			String purchaseEngineerTel = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseEngineerTel.substring(0, purchaseEngineerTel.length()-1));
			stringBuilder.append(circular.getPurchaseEngineerTel());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 9:
			String purchaseDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(purchaseDate.substring(0, purchaseDate.length()-1));
			stringBuilder.append(circular.getPurchaseDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 10:
			String firstDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(firstDate.substring(0, firstDate.length()-1));
			stringBuilder.append(circular.getFirstDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 11:
			String deliveryDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(deliveryDate.substring(0, deliveryDate.length()-1));
			stringBuilder.append(circular.getDeliveryDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 12:
			String secondDate = textRun.getRichTextRuns()[0].getText();
			stringBuilder.append(secondDate.substring(0, secondDate.length()-1));
			stringBuilder.append(circular.getSecondDate());
			stringBuilder.append(lineSeparator);
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 17:
			String[] projectStatus = circular.getProjectStatus();
			for (int j = 0; j < projectStatus.length; j++) {
				stringBuilder.append(j+1);
				stringBuilder.append("、");
				stringBuilder.append(projectStatus[j]);
				stringBuilder.append(lineSeparator);
			}
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		case 18:
			String[] problems = circular.getProblems();
			for (int j = 0; j < problems.length; j++) {
				stringBuilder.append(j+1);
				stringBuilder.append("、");
				stringBuilder.append(problems[j]);
				stringBuilder.append(lineSeparator);
			}
			textRun.changeTextInRichTextRun(textRun.getRichTextRuns()[0], stringBuilder.toString());
			break;
		default:
			break;
		}
	}

}
