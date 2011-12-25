package com.aqua.office.poi.ppt;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public interface SlideModel {
	
	public DecimalFormat numberFormat = new DecimalFormat("#,###.00");
	
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public String approveBy="√";

}
