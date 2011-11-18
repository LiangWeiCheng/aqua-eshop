<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- userList.jsp -->
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
	
		function userAdd(){
			window.location.href = "${pageContext.request.contextPath }/pingTai/userPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/user/userAdd.jsp";
		}
		
		var views = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!viewUser.action" htmlSrc="true"/>';
		var updates = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!toUpdateUser.action" htmlSrc="true"/>';
		var deletes = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!deleteUser.action" htmlSrc="true"/>';
		
		var viewsGroup = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!userGroupView.action" htmlSrc="true"/>';
		var updatesGroup = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!toUserGroupUpdate.action" htmlSrc="true"/>';
		
		var rowUserList = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!rowUserList.action" htmlSrc="true"/>';
		var rowUserListNo = '<bu:hasPrivilege operator="/pingTai/userPingTaiAction!rowUserListNo.action" htmlSrc="true"/>';
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
		<s:form id="userSearchForm" name="userSearchForm" namespace="/pingTai" action="userPingTaiAction!userList.action" method="POST">
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
				<bu:hasPrivilege operator="/WEB-INF/jsp/pingtai/user/userAdd.jsp" htmlSrc="<input type='button' value='添加用户' onclick='userAdd()' class='button4'/>"/>
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
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()" onmouseout="changeback()" onclick="clickto()">
      	<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
      		<td width="8%">部门</td>
	        <td width="10%">登录名</td>
	        <td width="8%">姓名</td>
	        <td width="14%">邮箱</td>
	        <td width="10%">手机</td>
	        <td width="10%">生日</td>
	        <td width="10%">户籍</td>
	        <td width="35%">操作</td>
      	</tr>
      	<s:iterator value="queryResult.resultList" var="user">
	      	<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
	      		<td><s:property value="department.names"/></td>
		        <td><s:property value="userName"/></td>
		        <td><s:property value="userInfo.names"/></td>
		        <td><s:property value="userInfo.email"/></td>
		        <td><s:property value="userInfo.mobile"/></td>
		        <td><s:date name="userInfo.birthday" format="yyyy-MM-dd"/></td>
		        <td><s:property value="userInfo.householder"/></td>
		        <td>
		        	<script type="text/javascript">
						if(views == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!viewUser.action?user.ids=${user.ids}">查看</a>&nbsp;');
						}
						if(updates == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!toUpdateUser.action?user.ids=${user.ids}">修改</a>&nbsp;');
						}
						if(deletes == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!deleteUser.action?user.ids=${user.ids}">删除</a>&nbsp;');
						}
						if(viewsGroup == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!userGroupView.action?user.ids=${user.ids}">查看组</a>&nbsp;');
						}
						if(updatesGroup == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!toUserGroupUpdate.action?user.ids=${user.ids}">修改组</a>&nbsp;');
						}
						if(rowUserList == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!rowUserList.action?user.ids=${user.ids}">访问级别</a>&nbsp;');
						}
						if(rowUserListNo == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/userPingTaiAction!rowUserListNo.action?user.ids=${user.ids}">添加级别</a>');
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
