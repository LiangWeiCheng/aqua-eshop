<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.aqua.alipay.config.jiaoyi.*"%>
<%@ page import="com.aqua.alipay.util.jiaoyi.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>支付宝担保交易付款</title>
	<style type="text/css">
		<!--
		.style1 {
			color: #FF0000
		}
		-->
	</style>
</head>
<body>

	<br>
	<br>
	
	<table width="30%" border="0" align="center">
		<tr>
			<th colspan="2" scope="col" style="FONT-SIZE: 14px; COLOR: #FF6600; FONT-FAMILY: Verdana">订单确认</th>
		</tr>
		<tr>
			<td colspan="2" height="2" bgcolor="#ff7300" style="FONT-SIZE: 14px; COLOR: #FF6600; FONT-FAMILY: Verdana"></td>
		</tr>
		<tr>
			<td style="FONT-SIZE: 14px; COLOR: #FF6600; FONT-FAMILY: Verdana">订单号:</td>
			<td style="FONT-SIZE: 14px; COLOR: #FF6600; FONT-FAMILY: Verdana">${requestScope.out_trade_no }</td>
		</tr>
		<tr>
			<td style="FONT-SIZE: 14px; COLOR: #FF6600; FONT-FAMILY: Verdana">付款总金额:</td>
			<td style="FONT-SIZE: 14px; COLOR: #FF6600; FONT-FAMILY: Verdana">${requestScope.price }</td>
		</tr>
		<tr>
			<td colspan="2">
				${requestScope.htmlText }
			</td>
		</tr>
		<tr>
			<td colspan="2" height="2" bgcolor="#ff7300"></td>
		</tr>
	</table>
	
</body>
</html>
