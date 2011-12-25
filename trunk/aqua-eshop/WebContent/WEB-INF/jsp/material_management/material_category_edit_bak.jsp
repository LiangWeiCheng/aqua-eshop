<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>物料目录</title>
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
			var name=document.getElementById('selectedCategory.name').value;
			var level=document.getElementById('selectedCategory.level').value;
			var oldLevel=document.getElementById('oldCategoryLevel').value;
			var parentCategoryId=document.getElementById('parentCategoryId').value;
			var description=document.getElementById('selectedCategory.description').value;
			var update=document.getElementById('update').value;
			if(level!=oldLevel){
				if(level==1){
					if(confirm("将所选的二级目录改为一级目录会使其所包含的所有物料失去目录属性，确定修改目录级别？")){
						window.location.href = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
							+id+"&selectedCategory.name="+name+"&selectedCategory.level="+level+"&parentCategoryId="+0+"&selectedCategory.description="+description+"&update="+update;
					}
				}else{
					if(confirm("将所选的一级目录改为二级目录会使其所包含的所有二级目录失去上级目录，确定修改目录级别？")){
						window.location.href = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
							+id+"&selectedCategory.name="+name+"&selectedCategory.level="+level+"&parentCategoryId="+parentCategoryId+"&selectedCategory.description="+description+"&update="+update;
					}
				}
			}else{
				if(level==1){
					window.location.href = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
						+id+"&selectedCategory.name="+name+"&selectedCategory.level="+level+"&parentCategoryId="+0+"&selectedCategory.description="+description+"&update="+update;
				}else{
					window.location.href = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
							+id+"&selectedCategory.name="+name+"&selectedCategory.level="+level+"&parentCategoryId="+parentCategoryId+"&selectedCategory.description="+description+"&update="+update;
				}
			}
		}
		
		function showCategories(){
			document.getElementById("selectedCategory.parentCategory.name").value="category_parent2";
			document.getElementById("parentCategoryId").value="25";
		}
		
		function changeCategoryLevel(){
			var level=document.getElementById('selectedCategory.level').value;
			if(level==1){
				document.getElementById("selectedCategory.parentCategory.name").value="";
				document.getElementById("selectedCategory.parentCategory.name").disabled="disabled";
				document.getElementById("selectParentCategory").disabled="disabled";
				document.getElementById("parentCategoryId").value=0;
			}else{
				document.getElementById("selectedCategory.parentCategory.name").disabled="";
				document.getElementById("selectParentCategory").disabled="";
			}
		}
		
		function cancelEdit(){
			window.location.href = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryList.action";
		}
			
	</script>
</head>
<body>

<table width="100%" border="0" align="center" cellpadding="1"
	cellspacing="0">
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">目录名称</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2"><input type="text"
			id="selectedCategory.name" name="selectedCategory.name"
			value="${selectedCategory.name }" /></td>
	</tr>
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">目录级别</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2">
		<select id="selectedCategory.level"
			name="selectedCategory.level" onchange="changeCategoryLevel();">
			<s:if test="selectedCategory.level==1">
				<option value="1" selected>级别一</option>
				<option value="2">级别二</option>
			</s:if>
			<s:else>
				<option value="1">级别一</option>
				<option value="2" selected>级别二</option>
			</s:else>
		</select></td>
	</tr>
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">上级目录</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2">
		<s:if test="selectedCategory.level==1">
			<input type="text"
				id="selectedCategory.parentCategory.name"
				name="selectedCategory.parentCategory.name"
				value="${selectedCategory.parentCategory.name }" disabled="disabled" />
				<input type="button" id="selectParentCategory" name="selectParentCategory" value="选择一级目录" onclick="showCategories();" disabled="disabled"/>
		</s:if>
		<s:else>
			<input type="text"
				id="selectedCategory.parentCategory.name"
				name="selectedCategory.parentCategory.name"
				value="${selectedCategory.parentCategory.name }" /><input type="button" id="selectParentCategory" name="selectParentCategory" value="选择一级目录" onclick="showCategories();"/>
		</s:else>
			</td>
	</tr>
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">描述</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2"><textarea
			id="selectedCategory.description" name="selectedCategory.description"
			rows="5" cols="50">${selectedCategory.description }</textarea></td>
	</tr>
	<tr height="20" valign="baseline">
		<td bgcolor="d3eaef" align="center" valign="top">登记时间</td>
		<td>&nbsp;</td>
		<td align="left" colspan="2"><s:date
			name="selectedCategory.createdDate" format="yyyy-MM-dd HH:mm:ss" /></td>
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

<input id="selectedId" name="selectedId" value="${selectedCategory.id }" type="hidden" />
<input id="parentCategoryId" name="parentCategoryId" value="${selectedCategory.parentCategory.id }"	type="hidden" />
<input id="oldCategoryLevel" name="oldCategoryLevel" value="${selectedCategory.level }" type="hidden" />
<input id="update" name="update" value="${update }" type="hidden" />
</body>
</html>
