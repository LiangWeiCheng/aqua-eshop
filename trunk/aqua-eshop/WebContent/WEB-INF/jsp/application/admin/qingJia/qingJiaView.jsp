<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaView.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>填写请假单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<s:head/>
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
		    	<span class="STYLE1">请假单明细</span>
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
			<td align="right" width="15%">请假天数:</td>
			<td width="15%">
				${qingJia.countDay}
			</td>
			<td align="right" width="15%">时间:</td>
			<td width="55%">
				<s:date name="%{qingJia.startDate}" format="yyyy-MM-dd HH:mm:ss"/>
				至
				<s:date name="%{qingJia.endDate}" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">部门经理审批:</td>
			<td width="35%">
				<s:if test='qingJia.buMenJingLiShenPi.equals("1")'>
					同意
				</s:if>
				<s:else>
					不同意
				</s:else>
			</td>
			<td align="right" width="15%">审批时间:</td>
			<td width="35%">
				<s:date name="%{qingJia.buMenJingLiDate}" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">部门经理批注:</td>
			<td colspan="3">
				${qingJia.buMenJingLiDes}
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">总经理审批:</td>
			<td width="35%">
				<s:if test='qingJia.zongJingLiShenPi.equals("1")'>
					同意
				</s:if>
				<s:else>
					不同意
				</s:else>
			</td>
			<td align="right" width="15%">审批时间:</td>
			<td width="35%">
				<s:date name="%{qingJia.zongJingLiDate}" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">总经理批注:</td>
			<td colspan="3">
				${qingJia.zongJingLiDes}
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
	</table>
		
</body>
</html>
