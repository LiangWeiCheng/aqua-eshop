<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>中间JSP</title>
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		overflow:hidden;
	}
	-->
	</style>
</head>
<body>

	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
		    <td width="8" bgcolor="#353c44">&nbsp;</td>
		    <td width="147" valign="top">
		    	<iframe id="left" name="left" height="100%" width="100%" frameborder="0" src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/left.jsp"></iframe>
		    </td>
		    <td width="10" bgcolor="#add2da">&nbsp;</td>
		    <td valign="top">
		    	<iframe id="right" name="right" height="100%" width="100%"  frameborder="0" src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/right.jsp"></iframe>
		    </td>
		    <td width="8" bgcolor="#353c44">&nbsp;</td>
	  	</tr>
	</table>
	
</body>
</html>