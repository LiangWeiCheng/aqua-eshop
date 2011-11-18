<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!-- dictTypeView.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>查看数据字典</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
</head>
<body>
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">查看字典</span>
		    </td>
		    <td align="right" bgcolor="#353c44">
		    	<span class="STYLE1">
              	</span>
		    </td>
	  	</tr>
	  	<tr>
		    <td height="3" colspan="2"></td>
	  	</tr>
	</table>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">类型编号:</td>
			<td width="25%">
				<s:property value="dictType.numbers"/>
			</td>
			<td align="right" width="15%">类型名称:</td>
			<td width="25%">
				<s:property value="dictType.names"/>
			</td>
			<td align="right" width="15%">类型排序:</td>
			<td width="15%">
				<s:property value="dictType.orderIds"/>
			</td>
		</tr>
	</table>
	
	<br/>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
		<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
			<td width="25%">字典编号</td>
			<td width="25%">字典名称</td>
			<td width="25%">字典值</td>
			<td width="25%">字典排序</td>
		</tr>
		<s:iterator value="%{dictType.dictSet}" id="dictId" var="dictVar" status="status">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td>
					<s:property value="numbers"/>
				</td>
				<td>
					<s:property value="names"/>
				</td>
				<td>
					<s:property value="value"/>
				</td>
				<td>
					<s:property value="orderIds"/>
				</td>
			</tr>	
		</s:iterator>
	</table>
	
	<br/>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#a8c7ce">
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="5" align="center">
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
	</table>
		
	
</body>
</html>
