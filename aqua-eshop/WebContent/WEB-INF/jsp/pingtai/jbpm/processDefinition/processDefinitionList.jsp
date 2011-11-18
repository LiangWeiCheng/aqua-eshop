<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- processDefinitionList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>流程列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
		
		//流程查询
		function processDefinitionSearch(){
			document.processDefinitionSearchForm.action = "${pageContext.request.contextPath }/pingTai/processDefinitionPingTaiAction!processDefinitionList.action";
			document.processDefinitionSearchForm.method = "POST";
			document.processDefinitionSearchForm.submit();
		}
		
		//流程上传
		function uploadZipFile(){
			document.processDefinitionSearchForm.action = "${pageContext.request.contextPath }/pingTai/processDefinitionPingTaiAction!processUploadDeploy.action";
			document.processDefinitionSearchForm.method = "POST";
			document.processDefinitionSearchForm.enctype = "multipart/form-data";
			var uploadZip = document.processDefinitionSearchForm.uploadZip.value;
			var fileType = uploadZip.split(".")[(uploadZip.split(".").length-1)];
			if(fileType=="zip"){
				document.processDefinitionSearchForm.submit();
			}else{
				alert("文件格式不正确!");
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
		    	<span class="STYLE1">流程查询</span>
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
		<s:form id="processDefinitionSearchForm" name="processDefinitionSearchForm" method="POST" enctype="multipart/form-data">
		<s:hidden name="isSearch" value="true"></s:hidden>
		<s:token name="formToken"></s:token>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">流程名称:</td>
			<td width="35%">
				<s:textfield name="_query.name" value="%{queryParameter.parameterMap['name']}" maxlength="16" cssStyle="width:95%"></s:textfield>
			</td>
			<td align="right" width="15%">流程ZIP文件:</td>
			<td width="35%">
				<s:file id="uploadZip" name="upload" title="请选择流程文件(.zip格式)" cssStyle="width:95%"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">最新版本:</td>
			<td colspan="3">
				<s:radio id="redioId" name="_query.version" list='#{"true":"是","false":"否"}' listKey="key" listValue="value" value="%{queryParameter.parameterMap['version']}"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<bu:hasPrivilege operator="/pingTai/processDefinitionPingTaiAction!processDefinitionList.action" htmlSrc="<input type='button' value='查询' onclick='processDefinitionSearch()' class='button2'/>"/>&nbsp;&nbsp;
				<bu:hasPrivilege operator="/pingTai/processDefinitionPingTaiAction!processUploadDeploy.action" htmlSrc="<input type='button' value='部署流程' onclick='uploadZipFile()' class='button4'/>"/>
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
		    	<span class="STYLE1">流程列表</span>
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
			<td>流程id</td>
			<td>流程名称</td>
			<td>流程key</td>
			<td>流程版本</td>
			<td>事项</td>
		</tr>
		<s:iterator value="%{processDefinitionList}" var="processDefinition">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td align="center">
					<s:property value="%{id}" />
				</td>
				<td align="center">
					<s:property value="%{name}" />
				</td>
				<td align="center">
					<s:property value="%{key}" />
				</td>
				<td align="center">
					<s:property value="%{version}" />
				</td>
				<td align="center">
					<a onclick='javascript:window.showModalDialog("${pageContext.request.contextPath }/pingTai/processDefinitionPingTaiAction!viewProcessDefinitionImage.action?deploymentId=${processDefinition.deploymentId}&imageResourceName=${processDefinition.imageResourceName}", "", "dialogHeight:600px;dialogWidth:959px;help:no;maximize:no;minimize:no;scroll:no;status:no;resizable:no")' href="#"> 
						查看流程图
					</a>
					<a onclick='javascript:window.location.href="${pageContext.request.contextPath }/pingTai/processDefinitionPingTaiAction!viewProcessDefinitionTaskList.action?processDefinitionId=${id}"' href="#">
						流程分派
					</a>
					<a onclick='javascript:window.location.href="${pageContext.request.contextPath }/pingTai/swimlanePingTaiAction!swimlaneList.action?processDefinitionId=${id}"' href="#"> 
						泳道
					</a>
					<a onclick='javascript:window.location.href = "${pageContext.request.contextPath }/pingTai/processDefinitionPingTaiAction!processRemove.action?id=${id}"' href="#"> 
						删除流程
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
