package com.aqua.pingtai.common;

public interface Dict {
	
	//订单状态
	public static final String orderForm_status_1 = "1";//在库
	public static final String orderForm_status_2 = "2";//在途
	public static final String orderForm_status_3 = "3";//签收
	
	//订单类型
	public static final String orderForm_types_1 = "1";//
	public static final String orderForm_types_2 = "2";//
	public static final String orderForm_types_3 = "3";//
	
	//订单配送状态
	public static final String deliverstatus_status_1 = "1";//入库
	public static final String deliverstatus_status_2 = "2";//出库
	public static final String deliverstatus_status_3 = "3";//签收
	
	//宣传分类
	public static final String companyInfo_types_1 = "1";//上边:企业宣传
	public static final String companyInfo_types_2 = "2";//左边:产品展示
	public static final String companyInfo_types_3 = "3";//中间:解决方案
	public static final String companyInfo_types_4 = "4";//右边:服务项目
	
	//宣传状态
	public static final String companyInfo_status_1 = "1";//显示
	public static final String companyInfo_status_2 = "2";//不显示
	
	//IC卡状态
	public static final String icCard_status_1 = "1";//未激活
	public static final String icCard_status_2 = "2";//已激活
	public static final String icCard_status_3 = "3";//不可用
	public static final String icCard_status_4 = "4";//作废卡
	
	//IC卡交易类型
	public static final String icCardTrans_type_1 = "1";//消费
	public static final String icCardTrans_type_2 = "2";//充值
	
	//IC卡充值交易设备默认编号
	public static final String icCard_operatorCardDevice_1 = "000000";//默认设备,无设备操作
	
	//日志类型
	public static final String log_types_1 = "1";//说明类型
	public static final String log_types_2 = "2";//错误类型
	
}
