package com.aqua.office.poi.ppt;

import java.util.Date;

import org.apache.poi.hslf.usermodel.SlideShow;

public class CircularSlide {
	
	public static void main(String...strings){
		String input = "D:\\项目需求\\15.QS5-PC300-014紧急通报.ppt";
		String output = "D:\\项目需求\\test.ppt";
		Circular circular = new Circular();
		circular.setProjectDescription("project description");
		circular.setRequestDepartment("request department");
		circular.setRequester("requester");
		circular.setRequesterTel(67821111);
		circular.setExpenditure(100023000.793);
		circular.setPurchaseEngineer("purchaseEngineer");
		circular.setPurchaseEngineerTel(67822222);
		circular.setPurchaseDate(new Date());
		circular.setFirstDate(new Date());
		circular.setSecondDate(new Date());
		circular.setDeliveryDate(new Date());
		circular.setProjectStatus(new String[]{"project status1","project status2","project status3"});
		circular.setProblems(new String[]{"problem1","problem2","problem3"});
		
		try {
			SlideShow slideShow = SlideUtil.dealSlide(input, circular);
			SlideUtil.writeSlide(slideShow, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
