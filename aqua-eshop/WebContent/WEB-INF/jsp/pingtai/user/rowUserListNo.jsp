<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- rowUserListNo.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		var adds = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!rowUserListAdd.action" htmlSrc="true"/>';
		
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
		    	<span class="STYLE1">不能操纵的数据用户查询[目标用户:${user.userInfo.names}]</span>
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
		<s:form id="userSearchForm" name="userSearchForm" namespace="/pingTai" action="userPingTaiAction!rowUserList.action" method="POST">
		<s:hidden name="isSearch" value="true"></s:hidden>
		<s:hidden name="user.ids" value="%{user.ids}"></s:hidden>
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
			<td align="right">邮箱:</td>
			<td>
				<s:textfield name="_query.email" value="%{queryParameter.parameterMap['email']}" maxlength="30" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right">身份证:</td>
			<td>
				<s:textfield name="_query.idCard" value="%{queryParameter.parameterMap['idCard']}" maxlength="18" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<s:submit value="查询" cssClass="button2"></s:submit>&nbsp;&nbsp;
				<s:reset value="重置" cssClass="button2"></s:reset>&nbsp;&nbsp;
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
		    	<span class="STYLE1">不能操纵的数据用户列表[目标用户:${user.userInfo.names}]</span>
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
	        <td width="25%">登录名</td>
	        <td width="10%">姓名</td>
	        <td width="14%">邮箱</td>
	        <td width="16%">手机</td>
	        <td width="10%">生日</td>
	        <td width="15%">户籍</td>
	        <td width="20%">操作</td>
      	</tr>
      	<s:iterator value="queryResult.resultList" var="user">
	      	<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
		        <td><s:property value="userName"/></td>
		        <td><s:property value="userInfo.names"/></td>
		        <td><s:property value="userInfo.email"/></td>
		        <td><s:property value="userInfo.mobile"/></td>
		        <td><s:date name="userInfo.birthday" format="yyyy-MM-dd"/></td>
		        <td><s:property value="userInfo.householder"/></td>
		        <td>
		        	<script type="text/javascript">
						if(adds == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!rowUserListAdd.action?user.ids=${user.ids}&ids=${ids}">添加</a>&nbsp;');
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
	        	<bu:splitPage urlParamater="&user.ids=%{user.ids}"></bu:splitPage>
	        </td>
      	</tr>
   	</table>
   	
</body>
</html>
