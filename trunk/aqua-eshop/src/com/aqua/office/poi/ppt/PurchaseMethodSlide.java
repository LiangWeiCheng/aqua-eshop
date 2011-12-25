package com.aqua.office.poi.ppt;

import java.util.Date;

import org.apache.poi.hslf.usermodel.SlideShow;


public class PurchaseMethodSlide {
	
	
	public static void main(String...strings){
		String input = "D:\\项目需求\\2.QS5-PC300-003非生产材料采购货源委员会上会文件_采购方式.ppt";
		String output="D:\\项目需求\\test.ppt";
		PurchaseMethod purchaseMethod = new PurchaseMethod();
		purchaseMethod.setProjectDescription("project description员会");
		purchaseMethod.setRequestDepartment("request department员会");
		purchaseMethod.setRequester("requester员会");
		purchaseMethod.setRequesterTel(67821111);
		purchaseMethod.setExpenditure(100023000.793);
		purchaseMethod.setPurchaseEngineer("purchase engineer");
		purchaseMethod.setPurchaseEngineerTel(67821112);
		purchaseMethod.setPurchaseDate(new Date());
		purchaseMethod.setFirstDate(new Date());
		purchaseMethod.setDeliveryDate(new Date());
		purchaseMethod.setSuppliers(new String[]{"supplier1","supplier2","supplier3","supplier4"});
		purchaseMethod.setRemarks(new String[]{"remark1","remark2","remark3"});
		purchaseMethod.setMethod("function");
		purchaseMethod.setApproveMethod(3);
		try {
			SlideShow slideShow = SlideUtil.dealSlide(input, purchaseMethod);
			SlideUtil.writeSlide(slideShow, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
