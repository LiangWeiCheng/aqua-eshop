<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- left.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>菜单测试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		
		.STYLE3 {
			font-size: 12px;
			color: #435255;
		}
		
		.STYLE4 {
			font-size: 12px
		}
		
		.STYLE5 {
			font-size: 12px;
			font-weight: bold;
		}
		
		a:link {
		   	color:#006400;
		   	text-decoration:none;
	   	}
	   	
	   	a:visited {
		   	color:#030303;
		   	text-decoration:none;
	   	}
	   	
	   	a:hover {
		   	color:#00688B;
		   	text-decoration:none;
	   	}
	   	
	   	a:active {
		   	color:#B22222;
	   	   	text-decoration:none;
	  	}
		-->
	</style>
	<script type="text/javascript">
		function onclickEvent(thisNode) {
			closeAll();
			var index = thisNode.rowIndex + 1;
			var tr = document.getElementById('menuTable').rows[index];
			var isNone = tr.style.display;
			if (isNone != "none") {
				tr.style.display = "none";
				openDefaultTr();
			} else {
				tr.style.display = "";
			}
		}

		function closeAll() {
			var tableNode = document.getElementById('menuTable');
			var rows = document.getElementById('menuTable').rows.length;
			for ( var i = 0; i < rows; i++) {
				var rowNode = tableNode.rows[i];
				var className = rowNode.className;
				if (className == "twoTrClass") {
					rowNode.style.display = "none";
				}
			}
		}

		function openDefaultTr() {
			var tableNode = document.getElementById('menuTable');
			var rows = document.getElementById('menuTable').rows.length;
			var rowNode = tableNode.rows[1];
			rowNode.style.display = "";
		}
	</script>
</head>
<body onload="closeAll();openDefaultTr()">

	<%-- 输出 --%>
	<%
		Object menuStringObject = request.getSession().getAttribute("menuString"); 
		if(null!=menuStringObject){
	%>	
			<%=(String)menuStringObject %>
	<%	
		}
	%>
	
	<%-- 删除 --%>
	<%
		//request.getSession().removeAttribute("menuString"); 
	%>
	
</body>
</html>

