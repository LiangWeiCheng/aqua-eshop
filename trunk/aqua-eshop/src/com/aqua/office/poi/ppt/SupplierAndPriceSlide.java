package com.aqua.office.poi.ppt;

import java.util.Date;

import org.apache.poi.hslf.usermodel.SlideShow;

public class SupplierAndPriceSlide {
	
	public static void main(String... strings) {
		String input = "D:\\项目需求\\9.QS5-PC300-011非生产材料采购货源委员会上会文件_供应商&价格.ppt";
		String output = "D:\\项目需求\\test.ppt";
		SupplierAndPrice supplierAndPrice = new SupplierAndPrice();
		supplierAndPrice.setProjectDescription("project description");
		supplierAndPrice.setRequestDepartment("request department");
		supplierAndPrice.setRequester("requester");
		supplierAndPrice.setRequesterTel(67821111);
		supplierAndPrice.setExpenditure(100023000.793);
		supplierAndPrice.setPurchaseEngineer("purchaseEngineer");
		supplierAndPrice.setPurchaseEngineerTel(67822222);
		supplierAndPrice.setPurchaseDate(new Date());
		supplierAndPrice.setFirstDate(new Date());
		supplierAndPrice.setSecondDate(new Date());
		supplierAndPrice.setMethod("method");
		supplierAndPrice.setDeliveryDate(new Date());
		supplierAndPrice.setSuppliers(new String[] { "supplier1", "supplier2",
				"supplier3" });
		supplierAndPrice.setCompareInformations(new CompareInformation[] {
				new CompareInformation("otherSupplier1", "information1"),
				new CompareInformation("otherSupplier2", "information2"),
				new CompareInformation("otherSupplier3", "information3") });
		supplierAndPrice.setApprove(false);
		try {
			SlideShow slideShow = SlideUtil.dealSlide(input, supplierAndPrice);
			SlideUtil.writeSlide(slideShow, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
