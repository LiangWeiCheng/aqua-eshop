<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaAdd.jsp -->
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
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		function dataVali(){
			document.qingJiaSaveForm.submit();
		}
		
	</script>
</head>
<body>
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">填写请假单</span>
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
		<s:form id="qingJiaSaveForm" name="qingJiaSaveForm" action="qingJiaApplicationAdminAction!qingJiaSave.action" namespace="/applicationAdmin" method="POST">
		<s:token name="formToken"></s:token>
		<s:hidden name="taskId" value="%{#request.taskId}"></s:hidden>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">请假天数:</td>
			<td width="15%">
				<s:textfield id="countDayId" name="qingJia.countDay" maxlength="2" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right" width="15%">时间:</td>
			<td width="55%">
				<s:textfield name="qingJia.startDate" size="30" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"></s:textfield>
				至
				<s:textfield name="qingJia.endDate" size="30" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">请假原因:</td>
			<td colspan="3">
				<textarea id="qingJiaDesId" name="qingJia.qingJiaDes" style="width:100%;height:200px;"></textarea>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="button" value="保存" onclick="dataVali();" class="button2"/>&nbsp;&nbsp;
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
		</s:form>
	</table>
		
</body>
</html>
