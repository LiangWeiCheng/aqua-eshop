<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- dictTypeList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>字典类型列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		function dictTypeAdd(){
			window.location.href = "${pageContext.request.contextPath }/pingTai/dictPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/dict/dictTypeAdd.jsp";
		}
		
		var views = '<bu:hasPrivilege operator="/pingTai/dictPingTaiAction!viewDicts.action" htmlSrc="true"/>';
		var updates = '<bu:hasPrivilege operator="/pingTai/dictPingTaiAction!toUpdateDictType.action" htmlSrc="true"/>';
		var deletes = '<bu:hasPrivilege operator="/pingTai/dictPingTaiAction!deleteDictType.action" htmlSrc="true"/>';
		
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
		    	<span class="STYLE1">字典查询</span>
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
		<s:form id="dictSearchForm" name="dictSearchForm" namespace="/pingTai" action="dictPingTaiAction!dictTypeList.action" method="POST">
		<s:hidden name="isSearch" value="true"></s:hidden>
		<s:token name="formToken"></s:token>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">类型名称:</td>
			<td width="35%">
				<s:textfield name="_query.names" value="%{queryParameter.parameterMap['names']}" maxlength="20" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right" width="15%">类型编号:</td>
			<td width="35%">
				<s:textfield name="_query.numbers" value="%{queryParameter.parameterMap['numbers']}" maxlength="20" cssStyle="width:95%"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="submit" value="查询" class="button2"/>&nbsp;&nbsp;
				<input type="reset" value="重置" class="button2"/>&nbsp;&nbsp;
				<bu:hasPrivilege operator="/WEB-INF/jsp/pingtai/dict/dictTypeAdd.jsp" htmlSrc="<input type='button' value='添加类型' onclick='dictTypeAdd()' class='button2'/>"/>
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
		    	<span class="STYLE1">字典列表</span>
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
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto(event)"  onmouseout="changeback(event)">
		<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
			<td>类型名称</td>
			<td>类型编号</td>
			<td>类型排序</td>
			<td>操作</td>
		</tr>
		<s:iterator value="queryResult.resultList" var="dictType">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td align="center">${dictType.names }</td>
				<td align="center">${dictType.numbers }</td>
				<td align="center">${dictType.orderIds }</td>
				<td align="center">
					<script type="text/javascript">
						if(views == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/dictPingTaiAction!viewDicts.action?dictType.ids=${dictType.ids}">查看</a>&nbsp;');
						}
						if(updates == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/dictPingTaiAction!toUpdateDictType.action?dictType.ids=${dictType.ids}">修改</a>&nbsp;');
						}
						if(deletes == 'true'){
							document.write('<a href="${pageContext.request.contextPath}/pingTai/dictPingTaiAction!deleteDictType.action?dictType.ids=${dictType.ids}">删除</a>');
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
