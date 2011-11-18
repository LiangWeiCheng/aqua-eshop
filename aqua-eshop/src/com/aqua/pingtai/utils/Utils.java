package com.aqua.pingtai.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aqua.pingtai.common.Context;

import org.safehaus.uuid.UUIDGenerator;

public class Utils {
	
	/**
	 * 当前时间减五分钟
	 * @return
	 */
	public static String getDatetimeJian5(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(new Date().getTime() - 1000*60*5);
		return formatter.format(date);
	}
	
	/**
	 * 获取今天日期		yyyy-MM-dd
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String sysTimeStr = formatter.format(new Date());
		return sysTimeStr;
	}
	
	/**
	 * 获取明天前日期	yyyy-MM-dd
	 * @return
	 */
	public static String getDateAddOne(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(new Date().getTime()+ 1000*60*60*24);  
		String sysTimeStr = formatter.format(date);
		return sysTimeStr;
	}
	
	/**
	 * 获取今天00:00:00	 hh:12小时制 HH:24小时制
	 * @return
	 */
	public static String getTodayDateZone(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		//long t = System.currentTimeMillis(); 
		//t = t / (1000 * 3600 * 24) * (1000 * 3600 * 24); 
		//System.out.println(formatter.format(new Timestamp(t))); 
		long timeMillis = System.currentTimeMillis(); 
		timeMillis = timeMillis / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset(); 
		//System.out.println(formatter.format(new Timestamp(timeMillis))); 
		return formatter.format(new Timestamp(timeMillis));
	}
	
	/**
	 * 获取明天00:00:00	 hh:12小时制 HH:24小时制
	 * @return
	 */
	public static String getMananaDateZone(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		//long t = System.currentTimeMillis(); 
		//t = t / (1000 * 3600 * 24) * (1000 * 3600 * 24); 
		//System.out.println(formatter.format(new Timestamp(t))); 
		long timeMillis = System.currentTimeMillis()+1000*60*60*24; 
		timeMillis = timeMillis / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset(); 
		//System.out.println(formatter.format(new Timestamp(timeMillis))); 
		return formatter.format(new Timestamp(timeMillis));
	}
	
	/**
	 * 格式化系统当前时间		hh:12小时制 HH:24小时制
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sysTimeStr = formatter.format(new Date());
		return sysTimeStr;
	}
	
	/**
	 * 获取系统当前时间字符串,精确到毫秒	 hh:12小时制 HH:24小时制
	 * @return
	 */
	public static String getNowTimeString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
		String sysTimeStr = formatter.format(new Date());
		return sysTimeStr;
	}
	
	/**
	 * 获取系统当前时间字符创，精确到毫秒	 hh:12小时制 HH:24小时制
	 * @return
	 */
	public static String getNowTimeString2() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String sysTimeStr = formatter.format(new Date());
		return sysTimeStr;
	}

	/**
	 * double精度调整
	 * @param doubleValue 需要调整的值123.454
	 * @param format 目标样式".##"
	 * @return
	 */
	public static String decimalFormatToString(double doubleValue, String format){
		DecimalFormat myFormatter = new DecimalFormat(format);  
		String formatValue = myFormatter.format(doubleValue);
		return formatValue;
	}

	/**
	 * 返回两个日期之间隔了多少天
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return date1比date2早返回负数，否则返回正数
	 */
	public static int getDateDiff(Date date1, Date date2) {
		int i = (int) ((date1.getTime() - date2.getTime()) / 3600 / 24 / 1000);
		return i;
	}

	/**
	 * 得到某一天是星期几
	 * @param strDate 日期字符串
	 * @return int 星期几（-1异常）
	 */
	@SuppressWarnings("static-access")
	public static int getDateInWeek(String strDate) {
		DateFormat df = DateFormat.getDateInstance();
		try {
			df.parse(strDate);
			java.util.Calendar c = df.getCalendar();
			int day = c.get(c.DAY_OF_WEEK) - c.SUNDAY;
			return day;
		} catch (ParseException e) {
			return -1;
		}
	}
	
	/**
	 * 获取操作系统路径类型
	 * @return
	 */
	public static String getOsPathType(){
		String osPathType = System.getProperty("file.separator");
		if(osPathType.equals("\\")){
			return "\\\\";
		}
		if(osPathType.equals("/")){
			return "/";
		}
		return null;
	}
	
	/**
	 * 获取操作系统类型名称
	 * @return
	 */
	public static String getOsTypeName(){
		String osTypeName = System.getProperty("os.name");
		if (osTypeName.equals("Linux")) { 
			return "linux";
		} else if (osTypeName.equals("Windows XP")) {
			return "windows";
		} else if (osTypeName.equals("Windows 7")) {
			return "Windows 7";
		}
		return null;
	}
	
	/**
	 * 获取系统临时目录
	 * @return
	 */
	public static String getOsTempDir(){
		return System.getProperty("java.io.tmpdir");
	}
	
	/**
	 * 操作系统的体系结构	 如:x86
	 * @return
	 */
	public static String getOsArch(){
		return System.getProperty("os.arch");
	}
	
	/**
	 * 获取java系统环境变量
	 * @param key
	 * @return
	 */
	public static String getSystemProperty(String key){
		return System.getProperty(key);
	}
	
	/**
	 * 获取UUID by jdk
	 * @return
	 */
	public static String getUUID_ByJDK(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * 获取UUID by jug
	 * @param length32	32 36
	 * @return
	 */
	public static String getUUID_ByJug(boolean length32) {  
		UUIDGenerator generator = UUIDGenerator.getInstance();
		org.safehaus.uuid.UUID uuid = generator.generateRandomBasedUUID();
		if(length32){
			return uuid.toString().replace("-", ""); 
		}
        return uuid.toString();  
    }  
	
	/**
	 * 验证手机号
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){ 
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"); 
		Matcher m = p.matcher(mobiles); 
		return m.matches(); 
	} 
	
	/**
	 * 生成订单号
	 * @return
	 */
	public static String getOrderFormId(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String sysTimeStr = formatter.format(new Date());
		
		String userIds = String.valueOf(Context.getCurrentUser().getIds());
		int userIdsLength = userIds.length();
		int subIds = 5 - userIdsLength;
		switch (subIds) {
			case 1: 
				userIds = "0" + userIds; break;
			case 2: 
				userIds = "00" + userIds; break;
			case 3: 
				userIds = "000" + userIds; break;
			case 4: 
				userIds = "0000" + userIds; break;
			default:
				break;
		}
		
		String rand = String.valueOf((int)(Math.random()*99999));
		int randLength = rand.length();
		int subRand = 5 - randLength;
		switch (subRand) {
			case 1: 
				rand = "0" + rand; break;
			case 2: 
				rand = "00" + rand; break;
			case 3: 
				rand = "000" + rand; break;
			case 4: 
				rand = "0000" + rand; break;
			default:
				break;
		}
		
		return sysTimeStr + userIds + rand;
	}
	
	public static void main(String[] args){
		/*
		System.out.println(Utils.getOsPathType());
		System.out.println(Utils.getOsTypeName());
		System.out.println(System.getProperty("path.separator"));
		System.out.println(Utils.getUUID_ByJug(true));
		*/
		System.out.println(Utils.isMobileNO("13871558042"));
	}
	
}
