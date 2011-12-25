<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>物料</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
<script
	src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js"
	type="text/javascript" language="javascript"></script>

<script type="text/javascript">
	
		function categorySave(){
			var id=document.getElementById('selectedId').value;
			var name=document.getElementById('selectedMaterial.name').value;
			var materialCode=document.getElementById('selectedMaterial.materialCode').value;			
			var brand=document.getElementById('selectedMaterial.brand').value;
			var categoryId=document.getElementById('categoryId').value;
			var description=document.getElementById('selectedMaterial.description').value;
			var update=document.getElementById('update').value;
			if(categoryId==null||categoryId==''){
				categoryId=0;
			}
			window.location.href = "${pageContext.request.contextPath }/material/materialAction!materialEdit.action?selectedMaterial.id="
				+id+"&selectedMaterial.name="+name+"&selectedMaterial.materialCode="+materialCode+"&selectedMaterial.brand="+brand+"&categoryId="+categoryId+"&selectedMaterial.description="+description+"&update="+update;
		}
		
		function showCategories(){
			document.getElementById("selectedMaterial.category.name").value="category_sub14";
			document.getElementById("categoryId").value="31";
		}
		
		
		function cancelEdit(){
			window.location.href = "${pageContext.request.contextPath }/material/materialAction!materialList.action";
		}
			
	</script>
</head>
<body>

<table width="100%" border="0" align="center" cellpadding="1"
	cellspacing="0">
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">物料名称</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2"><input type="text"
			id="selectedMaterial.name" name="selectedMaterial.name"
			value="${selectedMaterial.name }" /></td>
	</tr>
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">物料编码</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2">
		<input type="text"
			id="selectedMaterial.materialCode" name="selectedMaterial.materialCode"
			value="${selectedMaterial.materialCode }" />
		
		</td>
	</tr>
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">品牌</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2">
			<input type="text"
				id="selectedMaterial.brand"
				name="selectedMaterial.brand"
				value="${selectedMaterial.brand }" />
				
			</td>
	</tr>
	
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">所属目录</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2">
			<input type="text"
				id="selectedMaterial.category.name"
				name="selectedMaterial.category.name"
				value="${selectedMaterial.category.name }" /><input type="button" id="selectCategory" name="selectCategory" value="选择所属目录" onclick="showCategories();"/>
			</td>
	</tr>
	
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">描述</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2"><textarea
			id="selectedMaterial.description" name="selectedMaterial.description"
			rows="5" cols="50">${selectedMaterial.description }</textarea></td>
	</tr>
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">登记时间</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2"><s:date
			name="selectedMaterial.createdDate" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>

</table>



<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr height="20" valign="baseline">
		<td bgcolor="#353c44"><input type="button" id="addButton"
			name="addButton" value="保存" onclick="categorySave();" /> <input
			type="button" id="cancelButton" name="cancelButton" value="cancel"
			onclick="cancelEdit();" /></td>
		<td bgcolor="#353c44"></td>
		<td align="right" bgcolor="#353c44"><span class="STYLE1">
		</span></td>
	</tr>
	<tr>
		<td height="3" colspan="2"></td>
	</tr>
</table>

<input id="selectedId" name="selectedId" value="${selectedMaterial.id }" type="hidden" />
<input id="categoryId" name="categoryId" value="${selectedMaterial.category.id }"	type="hidden" />
<input id="update" name="update" value="${update }" type="hidden" />
</body>
</html>
