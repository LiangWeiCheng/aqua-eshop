<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- menuUpdate.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>更新菜单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<s:head/>
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		function submitForm(){
			var namesId = document.getElementById("namesId").value;
			var namesIdResult = utils_chinaLetterNumber(namesId, "菜单名", 2, 25);
			if(!namesIdResult){
				return false;
			}
			
			var orderId = document.getElementById("orderId").value;
			var orderIdResult = utils_number(orderId, "排序", 1, 2);
			if(!orderIdResult){
				return false;
			}
			
			var descriptionId = document.getElementById("descriptionId").value;
			var descriptionIdResult = utils_chinaLetterNumber(descriptionId, "描述", 2, 200);
			if(!descriptionIdResult){
				return false;
			}
			
			if(confirm("确定更新?")){
				document.menuAddForm.submit();
			}
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
		    	<span class="STYLE1">更新菜单</span>
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
		<s:form id="menuAddForm" name="menuAddForm" namespace="/pingTai" action="menuPingTaiAction!updateMenu.action" method="POST">
		<s:token name="formToken"></s:token>
		<s:hidden name="menu.ids" value="%{menu.ids}"></s:hidden>
		<s:hidden name="menu.creator.ids" value="%{menu.creator.ids}"></s:hidden>
		<input type="hidden" name="menu.createdDate" value='<s:date name="%{menu.createdDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
		<s:hidden name="menu.valid" value="%{menu.valid}"></s:hidden>
		<s:hidden name="menu.version" value="%{menu.version}"></s:hidden>
		
		<s:hidden name="menu.parentMenu.ids" value="%{menu.parentMenu.ids}"></s:hidden><%-- 上级菜单 --%>
		<s:hidden name="menu.menuType" value="%{menu.menuType}"></s:hidden><%-- 菜单类型 --%>
		<s:hidden name="menu.menuLevel" value="%{menu.menuLevel}"></s:hidden><%-- 菜单级别 --%>
		
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">菜单名:</td>
			<td>
				<s:textfield id="namesId" name="menu.names" maxlength="25" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">菜单URL:</td>
			<td>
				<s:textfield id="urlId" name="menu.url" maxlength="200" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">菜单排序:</td>
			<td>
				<s:textfield id="orderId" name="menu.orderIds" size="2" maxlength="50"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">图标:</td>
			<td>
				<input type="radio" name="menu.images" value="main_40.gif" ${menu.images eq "main_40.gif" ? "checked=checked" : ""}>
				<img alt="" src="${pageContext.request.contextPath }/images/pingTai/menu/main_40.gif">
				<input type="radio" name="menu.images" value="main_46.gif" ${menu.images eq "main_46.gif" ? "checked=checked" : ""}>
				<img alt="" src="${pageContext.request.contextPath }/images/pingTai/menu/main_46.gif">
				<input type="radio" name="menu.images" value="main_48.gif" ${menu.images eq "main_48.gif" ? "checked=checked" : ""}>
				<img alt="" src="${pageContext.request.contextPath }/images/pingTai/menu/main_48.gif">
				<input type="radio" name="menu.images" value="main_50.gif" ${menu.images eq "main_50.gif" ? "checked=checked" : ""}>
				<img alt="" src="${pageContext.request.contextPath }/images/pingTai/menu/main_50.gif">
				<input type="radio" name="menu.images" value="main_52.gif" ${menu.images eq "main_52.gif" ? "checked=checked" : ""}>
				<img alt="" src="${pageContext.request.contextPath }/images/pingTai/menu/main_52.gif">
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="center">菜单描述:</td>
			<td>
				<s:textarea id="descriptionId" name="menu.description" cssStyle="width:100%; height:auto;"></s:textarea><!-- 200 -->
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
		