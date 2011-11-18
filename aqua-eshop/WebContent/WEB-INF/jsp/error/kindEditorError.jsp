<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<% 
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<!-- kindEditorError.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>kindEditor上传错误</title>
</head>
<body>

	<table>
   		<tr>
   			<td>请检查您的操作是否有误,否则请与管理员联系.谢谢!</td>
   		</tr>
   		<tr>
   			<td>管理员QQ:	150584428</td>
   		</tr>
   		<tr>
   			<td>管理员主页:http://www.4bu4.com</td>
   		</tr>
   		<tr>
   			<td>管理员邮箱:dongcb678@163.com</td>
   		</tr>
   		<tr>
   			<td>
   				<div style="display:none;">
			  		kindEditor上传错误!!!
			    </div>
   			</td>
   		</tr>
   	</table>
	
</body>
</html>
