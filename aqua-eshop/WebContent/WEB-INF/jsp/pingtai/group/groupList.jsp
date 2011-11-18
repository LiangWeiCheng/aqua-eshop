<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- groupList.jsp -->
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
	
		function groupAdd(){
			window.location.href = "${pageContext.request.contextPath }/pingTai/groupPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/group/groupAdd.jsp";
		}
		
		function submitQueryForm(){
			var namesId = document.getElementById("namesId").value.trim();
			if(namesId.length!=0){
				var namesIdResult = utils_chinaLetterNumber(namesId, "组名", 0, 25);
				if(!namesIdResult){
					return false;
				}
			}
			
			var descriptionId = document.getElementById("descriptionId").value.trim();
			if(descriptionId.length!=0){
				var descriptionIdResult = utils_chinaLetterNumber(descriptionId, "描述", 0, 200);
				if(!descriptionIdResult){
					return false;
				}
			}
			
			document.groupSearchForm.submit();
		}
		
		var views = '<bu:hasPrivilege operator="/pingTai/groupPingTaiAction!viewGroup.action" htmlSrc="true"/>';
		var updates = '<bu:hasPrivilege operator="/pingTai/groupPingTaiAction!toUpdateGroup.action" htmlSrc="true"/>';
		var deletes = '<bu:hasPrivilege operator="/pingTai/groupPingTaiAction!deleteGroup.action" htmlSrc="true"/>';
		
		var viewsRole = '<bu:hasPrivilege operator="/pingTai/groupPingTaiAction!groupRoleView.action" htmlSrc="true"/>';
		var updatesRole = '<bu:hasPrivilege operator="/pingTai/groupPingTaiAction!toGroupRoleUpdate.action" htmlSrc="true"/>';
		
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
		    	<span class="STYLE1">用户组列表</span>
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
		<s:form id="groupSearchForm" name="groupSearchForm" namespace="/pingTai" action="groupPingTaiAction!groupList.action" method="POST">
		<s:hidden name="isSearch" value="true"></s:hidden>
		<s:token name="formToken"></s:token>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">组名:</td>
			<td width="35%">
				<s:textfield id="namesId" name="_query.names" value="%{queryParameter.parameterMap['names']}" maxlength="10" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right" width="15%">描述:</td>
			<td width="35%">
				<s:textfield id="descriptionId" name="_query.description" value="%{queryParameter.parameterMap['description']}" maxlength="20" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="button" value="查询" onclick="submitQueryForm()" class="button2"/>&nbsp;&nbsp;
				<input type="reset" value="重置" class="button2"/>&nbsp;&nbsp;
				<bu:hasPrivilege operator="/WEB-INF/jsp/pingtai/group/groupAdd.jsp" htmlSrc="<input type='button' value='添加组' onclick='groupAdd()' class='button2'/>"/>
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
		    	<span class="STYLE1">用户组列表</span>
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
			<td width="30%">组名</td>
			<td width="30%">描述</td>
			<td width="30%">操作</td>
		</tr>
		<s:iterator value="queryResult.resultList" var="group">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td align="center">${group.names }</td>
				<td align="center">${group.description }</td>
				<td align="center">			
					<script type="text/javascript">
						if(views == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/groupPingTaiAction!viewGroup.action?group.ids=${group.ids}">查看</a>&nbsp;');
						}
						if(updates == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/groupPingTaiAction!toUpdateGroup.action?group.ids=${group.ids}">修改</a>&nbsp;');
						}
						if(deletes == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/groupPingTaiAction!deleteGroup.action?group.ids=${group.ids}">删除</a>&nbsp;');
						}
						if(viewsRole == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/groupPingTaiAction!groupRoleView.action?group.ids=${group.ids}">查看角色</a>&nbsp;');
						}
						if(updatesRole == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/groupPingTaiAction!toGroupRoleUpdate.action?group.ids=${group.ids}">修改角色</a>');
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
