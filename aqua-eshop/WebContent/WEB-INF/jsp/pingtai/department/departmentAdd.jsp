<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- departmentAdd.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>添加部门</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<s:head/>
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		//用户对话框
		function showModelUser(){
			var url = "${pageContext.request.contextPath}/pingTai/userPingTaiAction!userListDialog.action";
			showModalDialogs(url, callbackFunProduct, "");
		}
		
		//用户回调
		function callbackFunProduct(rec, statusIndex) {
	    	var id = rec.id;
	    	var name = rec.name;
	    	document.getElementById("userIdsId").value = id;
	    	document.getElementById("userNamesId").value = name;
     	}
     	
		function submitForm(){
			var namesId = document.getElementById("namesId").value;
			var namesIdResult = utils_chinaLetterNumber(namesId, "部门名", 2, 25);
			if(!namesIdResult){
				return false;
			}
			
			var descriptionId = document.getElementById("descriptionId").value;
			var descriptionIdResult = utils_chinaLetterNumber(descriptionId, "描述", 2, 200);
			if(!descriptionIdResult){
				return false;
			}
			
			document.departmentAddForm.submit();
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
		    	<span class="STYLE1">添加部门</span>
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
		<s:form id="departmentAddForm" name="departmentAddForm" namespace="/pingTai" action="departmentPingTaiAction!saveDepartment.action" method="POST">
		<s:token name="formToken"></s:token>
		<s:hidden name="department.parentDepartment.ids" value="%{returnParamater}"></s:hidden>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">部门名称:</td>
			<td>
				<s:textfield id="namesId" name="department.names" value="" size="30" maxlength="25"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">部门负责人:</td>
			<td>
				<s:hidden id="userIdsId" name="department.principal.ids" value=""></s:hidden>
				<s:textfield id="userNamesId" name="department.principal.names" size="30" maxlength="" readonly="true"></s:textfield>
				<input type="button" value="浏览" onclick="showModelUser()" class="button2"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">部门描述:</td>
			<td>
				<s:textarea id="descriptionId" name="department.description" cssStyle="width:100%; height:50px;"></s:textarea><!-- 200 -->
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="2" align="center">
				<input type="button" value="保存" onclick="submitForm()" class="button2"/>&nbsp;&nbsp;
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
		</s:form>
	</table>	
	
	
</body>
</html>
