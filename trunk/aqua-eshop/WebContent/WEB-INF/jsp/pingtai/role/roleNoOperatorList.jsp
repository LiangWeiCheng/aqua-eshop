<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- roleOperatorList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>			
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		function submitQueryForm(){
			var typeId = document.getElementById("typeId").value.trim();
			if(typeId.length!=0){
				var typeIdResult = utils_chinaLetterNumber(typeId, "操作分类", 2, 25);
				if(!typeIdResult){
					return false;
				}
			}
			
			var namesId = document.getElementById("namesId").value.trim();
			if(namesId.length!=0){
				var namesIdResult = utils_chinaLetterNumber(namesId, "操作名", 0, 25);
				if(!namesIdResult){
					return false;
				}
			}
			
			document.roleSearchForm.submit();
		}
		
		var adds = '<bu:hasPrivilege operator="/pingTai/operatorPingTaiAction!roleOperatorAdd.action" htmlSrc="true"/>';		
		
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
		    	<span class="STYLE1">添加角色功能查询(角色名称:${role.names})</span>
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
		<s:form id="roleSearchForm" name="roleSearchForm" namespace="/pingTai" action="operatorPingTaiAction!roleNoOperatorView.action" method="POST">		
		<s:token name="formToken"></s:token>
		<s:hidden name="isSearch" value="true"></s:hidden>
		<s:hidden name="role.ids" value="%{role.ids}"></s:hidden>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">操作分类:</td>
			<td width="35%">
				<s:textfield id="typeId" name="_query.type" value="%{queryParameter.parameterMap['type']}" maxlength="10" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right" width="15%">操作名:</td>
			<td width="35%">
				<s:textfield id="namesId" name="_query.names" value="%{queryParameter.parameterMap['names']}" maxlength="10" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="button" value="查询" onclick="submitQueryForm()" class="button2"/>&nbsp;&nbsp;
				<input type="reset" value="重置" class="button2"/>&nbsp;&nbsp;
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
		    	<span class="STYLE1">添加角色功能列表(角色名称:${role.names})</span>
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
			<td width="15%">操作分类</td>
			<td width="15%">操作名</td>
			<td width="55%">URL</td>
			<td width="15%">操作</td>
		</tr>
		<s:iterator value="queryResult.resultList" var="operator">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td align="center">${operator.type }</td>
				<td align="center">${operator.names }</td>
				<td align="center">${operator.url }</td>
				<td align="center">
					<script type="text/javascript">
						if(adds == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/operatorPingTaiAction!roleOperatorAdd.action?role.ids=${role.ids}&operator.ids=${operator.ids}">添加</a>&nbsp;');
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
	        	<bu:splitPage urlParamater="&role.ids=%{role.ids}"></bu:splitPage>
	        </td>
      	</tr>
   	</table>
   	
</body>
</html>
