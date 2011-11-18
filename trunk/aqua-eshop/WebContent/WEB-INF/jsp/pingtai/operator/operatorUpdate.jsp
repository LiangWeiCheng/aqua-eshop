<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- operatorUpdate.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>更新操作</title>
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
			var namesIdResult = utils_chinaLetterNumber(namesId, "操作名", 2, 25);
			if(!namesIdResult){
				return false;
			}
			
			var urlId = document.getElementById("urlId").value.trim();
			if(urlId.length==0){
				alert("操作URL不能为空");
				return false;
			}else if(urlId.length>200){
				alert("操作URL长度不能超过200");
				return false;
			}
			
			var typeId = document.getElementById("typeId").value;
			var typeIdResult = utils_chinaLetterNumber(typeId, "操作分类", 2, 25);
			if(!typeIdResult){
				return false;
			}
			
			var descriptionId = document.getElementById("descriptionId").value;
			var descriptionIdResult = utils_chinaLetterNumber(descriptionId, "描述", 2, 200);
			if(!descriptionIdResult){
				return false;
			}
			
			document.operatorUpdateForm.submit();
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
		    	<span class="STYLE1">更新功能</span>
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
		<s:form id="operatorUpdateForm" name="operatorUpdateForm" namespace="/pingTai" action="operatorPingTaiAction!updateOperator.action" method="POST">
		<s:token name="formToken"></s:token>
		<s:hidden name="operator.ids" value="%{operator.ids}"></s:hidden>
		<s:hidden name="operator.creator.ids" value="%{operator.creator.ids}"></s:hidden>
		<input type="hidden" name="operator.createdDate" value='<s:date name="%{operator.createdDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
		<s:hidden name="operator.valid" value="%{operator.valid}"></s:hidden>
		<s:hidden name="operator.version" value="%{operator.version}"></s:hidden>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">操作名:</td>
			<td width="85%">
				<s:textfield id="namesId" name="operator.names" maxlength="25" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">操作URL:</td>
			<td>
				<s:textfield id="urlId" name="operator.url" maxlength="200" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">操作分类:</td>
			<td>
				<s:textfield id="typeId" name="operator.type" maxlength="25" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">操作描述:</td>
			<td>
				<s:textarea id="descriptionId" name="operator.description" cssStyle="width:95%; height:auto;"></s:textarea><!-- 200 -->
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="2" align="center">
				<input type="button" value="更新" onclick="submitForm()" class="button2"/>&nbsp;&nbsp;
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
		</s:form>
	</table>	
	
	
</body>
</html>
