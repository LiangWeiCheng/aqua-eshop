<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>支付成功客户端返回</title>
	<style type="text/css">
		.font_content{
		    font-family:"宋体";
		    font-size:14px;
		    color:#FF6600;
		}
		.font_title{
		    font-family:"宋体";
		    font-size:16px;
		    color:#FF0000;
		    font-weight:bold;
		}
		table{
		    border: 1px solid #CCCCCC;
		}
	</style>
</head>
<body>

	<table align="center" width="350" cellpadding="5" cellspacing="0">
    	<tr>
        	<td align="center" class="font_title" colspan="2">通知返回</td>
        </tr>
        <tr>
            <td class="font_content" align="right">支付宝交易号：</td>
            <td class="font_content" align="left">${alipayReturnParamater.trade_no}</td>
        </tr>
        <tr>
            <td class="font_content" align="right">订单号：</td>
            <td class="font_content" align="left">${alipayReturnParamater.order_no}</td>
        </tr>
        <tr>
            <td class="font_content" align="right">付款总金额：</td>
            <td class="font_content" align="left">${alipayReturnParamater.total_fee}</td>
        </tr>
        <tr>
            <td class="font_content" align="right"> 商品标题：</td>
            <td class="font_content" align="left">${alipayReturnParamater.subject}</td>
      	</tr>
        <tr>
           	<td class="font_content" align="right">商品描述：</td>
            <td class="font_content" align="left">${alipayReturnParamater.body}</td>
        </tr>
       	<tr>
           	<td class="font_content" align="right"> 买家账号：</td>
            <td class="font_content" align="left">${alipayReturnParamater.buyer_email}</td>
        </tr>
        <tr>
            <td class="font_content" align="right"> 收货人姓名：</td>
            <td class="font_content" align="left">${alipayReturnParamater.receive_name}</td>
        </tr>
        <tr>
            <td class="font_content" align="right"> 收货人地址：</td>
            <td class="font_content" align="left">${alipayReturnParamater.receive_address}</td>
        </tr>
        <tr>
            <td class="font_content" align="right">收货人邮编：</td>
            <td class="font_content" align="left">${alipayReturnParamater.receive_zip}</td>
        </tr>
        <tr>
            <td class="font_content" align="right">收货人电话：</td>
            <td class="font_content" align="left">${alipayReturnParamater.receive_phone}</td>
        </tr>
        <tr>
            <td class="font_content" align="right">收货人手机：</td>
            <td class="font_content" align="left">${alipayReturnParamater.receive_mobile}</td>
        </tr>
        <tr>
            <td class="font_content" align="right">交易状态：</td>
            <td class="font_content" align="left">${alipayReturnParamater.trade_status}</td>
        </tr>
        <tr>
            <td class="font_content" align="right">验证状态：</td>
            <td class="font_content" align="left">${verifyStatus}</td>
        </tr>
   	</table>
   
</body>
</html>
