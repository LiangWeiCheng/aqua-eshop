<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>物料列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	
	<script type="text/javascript">
		
		var currentActiveRow; 
		function selectLine(obj,selectedId,selectedName,selectedLevel){
			if(currentActiveRow==null){
				currentActiveRow = obj;
			}
			currentActiveRow.style.backgroundColor="";   
			currentActiveRow=obj;
			currentActiveRow.style.backgroundColor="#00FF00";
			document.getElementById('selectedId').value=selectedId;
			document.getElementById('selectedName').value=selectedName;
		}
	
		function materialAdd(){
			window.location.href = "${pageContext.request.contextPath }/material/materialAction!materialShow.action";
		}
		
		function materialEdit(){
			var selectedId = document.getElementById("selectedId").value;
			window.location.href = "${pageContext.request.contextPath }/material/materialAction!materialShow.action?selectedId="+selectedId+"&update=true";
		}
		
		function materialDelete(){
			var selectedId = document.getElementById("selectedId").value;
			if(selectedId==null){
				alert("请选择想要删除的物料");
			}else{
				var selectedName = document.getElementById("selectedName").value;
				if(confirm("确定要删除所选物料："+selectedName+"？")){
					window.location.href = "${pageContext.request.contextPath }/material/materialAction!materialDelete.action?selectedId="+selectedId;
				}
			}
		}
		
			
	</script>
</head>
<body>
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<input type="button" id="addButton" name="addButton" value="添加" onclick="materialAdd();"/>
		    	<input type="button" id="editButton" name="editButton" value="编辑" onclick="materialEdit();"/>
		    	<input type="button" id="deleteButton" name="deleteButton" value="删除" onclick="materialDelete();"/>
		    </td>
		    <td bgcolor="#353c44">
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
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">物料列表</span>
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
			<td>物料名称</td>
			<td>物料编码</td>
			<td>品牌</td>
			<td>所属目录</td>
			<td>描述</td>
			<td>登记时间</td>
		</tr>
			<s:iterator value="queryResult.resultList" var="material">
				<tr align="center" bgcolor="#FFFFFF" class="STYLE19" onclick="selectLine(this,'${material.id}','${material.name}');">
					<td align="center"><s:property value="name"/></td>
					<td align="center"><s:property value="materialCode"/></td>
					<td align="center"><s:property value="brand"/></td>
					<td align="center">
					<s:if test="category.name!=null&&category.name!=''">
					<s:property value="category.name"/> -> <s:property value="category.parentCategory.name"/>
					</s:if>
					</td>
					<td align="center"><s:property value="description"/></td>
					<td align="center"><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/></td>
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
	<input id="selectedId" name="selectedId" value="" type="hidden"/>
	<input id="selectedName" name="selectedName" value="" type="hidden"/>
</body>
</html>
