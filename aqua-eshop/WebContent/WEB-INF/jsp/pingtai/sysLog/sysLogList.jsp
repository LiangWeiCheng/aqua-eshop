<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- sysLogList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>系统日志列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		var views = '<bu:hasPrivilege operator="/pingTai/sysLogPingTaiAction!viewSysLog.action" htmlSrc="true"/>';
		
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
		    	<span class="STYLE1">系统日志查询</span>
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
		<s:form id="sysLogSearchForm" name="sysLogSearchForm" namespace="/pingTai" action="sysLogPingTaiAction!icCardList.action" method="POST">
		<s:hidden name="isSearch" value="true"></s:hidden>
		<s:token name="formToken"></s:token>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">标题:</td>
			<td width="35%">
				<s:textfield name="_query.titles" value="%{queryParameter.parameterMap['titles']}" maxlength="20" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right" width="15%">创建者:</td>
			<td width="35%">
				<s:textfield name="_query.userNames" value="%{queryParameter.parameterMap['userNames']}" maxlength="20" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">创建时间:</td>
			<td colspan="2">
				<s:textfield name="_query.start" value="%{queryParameter.parameterMap['start']}" size="30" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"></s:textfield>
				至
				<s:textfield name="_query.end" value="%{queryParameter.parameterMap['end']}" size="30" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"></s:textfield>
			</td>
			<td>
				类型:
					<select name="_query.types">
						<option value=""></option>
						<option value="1" ${queryParameter.parameterMap['types'] eq "1" ? "selected='selected'":"" }>说明类型</option>
						<option value="2" ${queryParameter.parameterMap['types'] eq "2" ? "selected='selected'":"" }>错误类型</option>
					</select>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<s:submit value="查询" cssClass="button2"></s:submit>&nbsp;&nbsp;&nbsp;
				<s:reset value="重置" cssClass="button2"></s:reset>
			</td>
		</tr>
		</s:form>
	</table>	
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">系统日志列表</span>
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
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()" onclick="clickto()">
		<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
			<td width="35%">标题</td>
			<td width="15%">类型</td>
			<td width="15%">创建者</td>
			<td width="20%">创建时间</td>
			<td width="15%">操作</td>
		</tr>
		<s:iterator value="queryResult.resultList" var="sysLog">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td align="center">${sysLog.titles }</td>
				<td align="center">
					${sysLog.types eq "s" ? "说明类型":""}
					${sysLog.types eq "e" ? "错误类型":""}
				</td>
				<td align="center">${sysLog.creator.userInfo.names }</td>
				<td align="center"><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">
					<script type="text/javascript">
						if(views == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/sysLogPingTaiAction!viewSysLog.action?sysLog.ids=${sysLog.ids}">查看</a>');
						}
					</script>
				</td>
			</tr>
		</s:iterator>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="6">
	        <td>
	        </td>
      	</tr>
      	<tr class="STYLE22">
	        <td align="right" valign="baseline">
	        	<bu:splitPage urlParamater=""></bu:splitPage>
	        </td>
      	</tr>
   	</table>
   	
</body>
</html>
