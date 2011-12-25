package com.aqua.office.poi.ppt;

public class CompareInformation {
	
	public CompareInformation(String supplier, String information){
		this.setSupplier(supplier);
		this.setInformation(information);
	}
	
	private String supplier;
	
	private String information;

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}
