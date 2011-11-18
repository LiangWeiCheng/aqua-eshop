<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<% 
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<!-- toRedirectLogin.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登陆跳转</title>
    <script type="text/javascript">
    	top.location.href = "${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!toLoginJsp.action";
    </script>
</head>
<body>

</body>
</html>
