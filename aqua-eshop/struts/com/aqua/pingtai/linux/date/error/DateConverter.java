package com.aqua.pingtai.linux.date.error;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import ognl.DefaultTypeConverter;

@SuppressWarnings("unchecked")
public class DateConverter extends DefaultTypeConverter {

	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
		new SimpleDateFormat("yyyy-MM-dd"),
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
	};

	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == Date.class) {
			String dateString = null;
			String[] params = (String[]) value;
			dateString = params[0];
			if(dateString.length()==10){
				try {
					return ACCEPT_DATE_FORMATS[0].parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if(dateString.length()==19){
				try {
					return ACCEPT_DATE_FORMATS[1].parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if(dateString.length()==23){
				try {
					return ACCEPT_DATE_FORMATS[2].parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}else if (toType == String.class) {
			Date date = (Date) value;
			String dateString = null;
			String[] params = (String[]) value;
			dateString = params[0];
			if(dateString.length() == 10){
				return ACCEPT_DATE_FORMATS[0].format(date);
			}else if(dateString.length() == 19){
				return ACCEPT_DATE_FORMATS[1].format(date);
			}else if(dateString.length() == 23){
				return ACCEPT_DATE_FORMATS[2].format(date);
			}
		}
		return null;
	}
	
}
