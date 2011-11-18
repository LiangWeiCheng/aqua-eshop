<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- userListDialog.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>用户列表</title>
    <base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		function select(index) {
    		var rec = new Object();
    		var id = document.getElementsByName("id")[index].value;
    		var name = document.getElementsByName("name")[index].value;
    		rec.id = id;
    		rec.name = name;
    		window.returnValue = rec;
    		window.close();
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
		    	<span class="STYLE1">用户查询</span>
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
		<s:form id="userSearchForm" name="userSearchForm" namespace="/pingTai" action="userPingTaiAction!userListDialog.action" method="POST">
		<s:hidden name="isSearch" value="true"></s:hidden>
		<s:token name="formToken"></s:token>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">登录名:</td>
			<td width="35%">
				<s:textfield name="_query.userName" value="%{queryParameter.parameterMap['userName']}" maxlength="16" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right" width="15%">姓名:</td>
			<td width="35%">
				<s:textfield name="_query.names" value="%{queryParameter.parameterMap['names']}" maxlength="10" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<s:submit value="查询" cssClass="button2"></s:submit>
				<s:reset value="清除" cssClass="button2"></s:reset>
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
		    	<span class="STYLE1">用户列表</span>
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
			<td>登录名</td>
			<td>姓名</td>
			<td>邮箱</td>
			<td>户籍</td>
			<td>操作</td>
		</tr>
		<s:iterator value="queryResult.resultList" var="user" status="status">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td align="center"><s:property value="userName"/></td>
				<td align="center"><s:property value="userInfo.names"/></td>
				<td align="center"><s:property value="userInfo.email"/></td>
				<td align="center"><s:property value="userInfo.householder"/></td>
				<td align="center">
					<s:hidden name="id" value="%{ids}" />
					<s:hidden name="name" value="%{userInfo.names}" />
					<a href='javascript:select(<s:property value="#status.index" />)'>
						<img alt="点击选中" src="${pageContext.request.contextPath}/images/pingTai/select.gif" border="0"/>
					</a>
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
