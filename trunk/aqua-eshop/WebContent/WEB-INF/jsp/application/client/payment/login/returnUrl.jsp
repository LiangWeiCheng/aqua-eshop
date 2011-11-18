<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>支付宝会员免注册登录返回信息</title>
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
		    <td align="center" class="font_title">
		    	${requestScope.loginResult }
		    </td>
		</tr>
	</table>

</body>
</html>
