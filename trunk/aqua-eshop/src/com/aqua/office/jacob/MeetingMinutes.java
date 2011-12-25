package com.aqua.office.jacob;

import java.util.ArrayList;
import java.util.List;

public class MeetingMinutes {
	
	private List<FirstPart> firstParts = new ArrayList<FirstPart>();
	
	private List<SecondPart> secondParts = new ArrayList<SecondPart>();
	
	private String purchaseSig;
	
	private String financeSig;
	
	private String lawSig;
	
	private String auditSig;
	
	private String disciplineSig;
	
	public void addFirstPart(FirstPart firstPart){
		this.firstParts.add(firstPart);
	}
	
	public void addSecondPart(SecondPart secondPart){
		this.secondParts.add(secondPart);
	}

	public List<FirstPart> getFirstParts() {
		return firstParts;
	}

	public void setFirstParts(List<FirstPart> firstParts) {
		this.firstParts = firstParts;
	}

	public List<SecondPart> getSecondParts() {
		return secondParts;
	}

	public void setSecondParts(List<SecondPart> secondParts) {
		this.secondParts = secondParts;
	}

	public String getPurchaseSig() {
		return purchaseSig;
	}

	public void setPurchaseSig(String purchaseSig) {
		this.purchaseSig = purchaseSig;
	}

	public String getFinanceSig() {
		return financeSig;
	}

	public void setFinanceSig(String financeSig) {
		this.financeSig = financeSig;
	}

	public String getLawSig() {
		return lawSig;
	}

	public void setLawSig(String lawSig) {
		this.lawSig = lawSig;
	}

	public String getAuditSig() {
		return auditSig;
	}

	public void setAuditSig(String auditSig) {
		this.auditSig = auditSig;
	}

	public String getDisciplineSig() {
		return disciplineSig;
	}

	public void setDisciplineSig(String disciplineSig) {
		this.disciplineSig = disciplineSig;
	}

}
