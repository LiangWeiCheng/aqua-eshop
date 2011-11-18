<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<% 
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<!-- notActionMethod.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>程序运行错误</title>
</head>
<body>

	<table width="960" border="0" cellspacing="0" cellpadding="0" align="center">
  		<tr>
    		<td width="5">&nbsp;</td>
		    <td valign="top">
		    	<h1>未知Action Method的显示页</h1>
				用户请求的Action Method：<s:property value="notActionMethod"/><br />
      		</td>
  		</tr>
	</table>

</body>
</html>
