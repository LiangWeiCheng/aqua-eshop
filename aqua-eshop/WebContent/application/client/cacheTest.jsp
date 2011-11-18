<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试缓存</title>
</head>
<body>
	<cache:cache time="30">
		<%=new Date()%>
	</cache:cache>
	
	<br/>
	
	<div>
		<%=new Date()%>
	</div>
	
</body>
</html>