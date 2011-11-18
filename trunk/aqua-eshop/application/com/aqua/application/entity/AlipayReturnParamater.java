package com.aqua.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

//支付宝的通知返回参数
@SuppressWarnings("serial")
@Entity
@Table(name="application_alipay_returnparamater")
public class AlipayReturnParamater extends EntityBase {
	
	@Column(name="trade_no",length=200,columnDefinition="varchar(200)",nullable=true)
	private String trade_no;//支付宝交易号
	
	@Column(name="order_no",length=200,columnDefinition="varchar(200)",nullable=true)
	private String order_no;//获取订单号
	
	@Column(name="total_fee",length=25,columnDefinition="varchar(25)",nullable=true)
	private String total_fee;//获取总金额
	
	@Column(name="subject",length=200,columnDefinition="varchar(200)",nullable=true)
	private String subject;//商品名称、订单名称
	
	@Column(name="body",length=500,columnDefinition="varchar(500)",nullable=true)
	private String body;//商品描述、订单备注、描述
	
	@Column(name="buyer_email",length=100,columnDefinition="varchar(100)",nullable=true)
	private String buyer_email;//买家支付宝账号
	
	@Column(name="receive_name",length=100,columnDefinition="varchar(100)",nullable=true)
	private String receive_name;//收货人姓名
	
	@Column(name="receive_address",length=200,columnDefinition="varchar(200)",nullable=true)
	private String receive_address;//收货人地址
	
	@Column(name="receive_zip",length=25,columnDefinition="varchar(25)",nullable=true)
	private String receive_zip;//收货人邮编
	
	@Column(name="receive_phone",length=50,columnDefinition="varchar(50)",nullable=true)
	private String receive_phone;//收货人电话
	
	@Column(name="receive_mobile",length=50,columnDefinition="varchar(50)",nullable=true)
	private String receive_mobile;//收货人手机
	
	@Column(name="trade_status",length=50,columnDefinition="varchar(50)",nullable=true)
	private String trade_status;//交易状态
	
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String tradeNo) {
		trade_no = tradeNo;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String orderNo) {
		order_no = orderNo;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String totalFee) {
		total_fee = totalFee;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyerEmail) {
		buyer_email = buyerEmail;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receiveName) {
		receive_name = receiveName;
	}
	public String getReceive_address() {
		return receive_address;
	}
	public void setReceive_address(String receiveAddress) {
		receive_address = receiveAddress;
	}
	public String getReceive_zip() {
		return receive_zip;
	}
	public void setReceive_zip(String receiveZip) {
		receive_zip = receiveZip;
	}
	public String getReceive_phone() {
		return receive_phone;
	}
	public void setReceive_phone(String receivePhone) {
		receive_phone = receivePhone;
	}
	public String getReceive_mobile() {
		return receive_mobile;
	}
	public void setReceive_mobile(String receiveMobile) {
		receive_mobile = receiveMobile;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String tradeStatus) {
		trade_status = tradeStatus;
	}
	
	
	
}
