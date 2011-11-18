<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	/*
	 功能：支付宝发货信息同步接口的入口页面，生成请求URL
	 *版本：3.0
	 *日期：2010-08-4
	 *说明：
	 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

	 */
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>支付宝发货通道宝发货</title>
	<style type="text/css">
		.form-left{
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
	</style>
	<SCRIPT language=JavaScript>
		<!-- 
		//校验输入框 
		//Author: William 2010-3-31 -->
		function CheckForm()
		{
			if (document.alipayment.trade_no.value.length == 0) {
				alert("请输入支付宝交易号.");
				document.alipayment.trade_no.focus();
				return false;
			}
			if (document.alipayment.logistics_name.value.length == 0) {
				alert("请输入物流公司名称.");
				document.alipayment.logistics_name.focus();
				return false;
			}
			if (document.alipayment.invoice_no.value.length == 0) {
				alert("请输入物流发货单号.");
				document.alipayment.invoice_no.focus();
				return false;
			}
			if (document.alipayment.transport_type.value.length == 0) {
				alert("请输入物流发货时的运输类型.");
				document.alipayment.transport_type.focus();
				return false;
			}
		
		}  
	</SCRIPT>
</head>
<BODY text=#000000 bgColor=#ffffff leftMargin=0 topMargin=4>

	<CENTER>
	
	  	<BR />
	  	
	  	<FORM name=alipayment onSubmit="return CheckForm();" action="sendgoods.jsp" method=post target="_blank">
		    <TABLE cellSpacing=0 cellPadding=0 width=450 border=0>
		      	<TR>
		        	<TD class=font_title valign="middle">支付宝发货通道</TD>
		      	</TR>
		      	<TR>
		        	<TD align="center"><HR width=450 SIZE=2 color="#999999"></TD>
		      	</TR>
		      	<tr>
			        <td align="center">
			        	<TABLE cellSpacing=0 cellPadding=0 width=350 border=0>
				            <TR>
				              	<TD class=form-left>支付宝交易号：</TD>
				              	<TD class=form-right><INPUT size=30 name=trade_no maxlength="20"></TD>
				            </TR>
				            <TR>
				              	<TD class=form-left>发货类型：</TD>
				              	<TD class=form-right>
				              		<select name="transport_type">
					                  	<option value="EMS">EMS</option>
					                  	<option value="POST">平邮</option>
					                  	<option value="EXPRESS" selected="selected">快递</option>
				                	</select>
				              	</TD>
				            </TR>
				            <TR>
				              	<TD class=form-left>物流公司名称：</TD>
				              	<TD class=form-right><INPUT size=30 name=logistics_name maxlength="30"></TD>
				            </TR>
				            <TR>
				              	<TD class=form-left>物流发货单号：</TD>
				              	<TD class=form-right><INPUT size=30 name=invoice_no maxlength="50"></TD>
				            </TR>
				            <TR>
				              	<TD class=form-left></TD>
				              	<TD class=form-right><input name="alipaysendgoods" id="alipaysendgoods" value="发 货" type="submit"></TD>
				            </TR>
			          	</TABLE>
			         </td>
		      	</tr>
		    </TABLE>
	  	</FORM>
	  	
	</CENTER>
	
</BODY>
</html>

