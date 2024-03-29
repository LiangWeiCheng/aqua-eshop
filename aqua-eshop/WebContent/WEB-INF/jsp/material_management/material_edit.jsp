<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>物料</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">

<script type="text/javascript">  
	
	function materialSave(){
		var id=document.getElementById('selectedId').value;
		var name=document.getElementById('selectedMaterial.name').value;
		var materialCode = document.getElementById("selectedMaterial.materialCode").value;
		if(name==null||name.replace(/\s/g,"")==""){
			alert("必须填写物料名称");
		}else if(materialCode==null||materialCode.replace(/\s/g,"")==""){
			alert("必须填写物料编码");
		}else{
			var brand = document.getElementById("selectedMaterial.brand").value;
			var categoryId = document.getElementById('categoryId').value;
			if(categoryId==null||categoryId==''){
				categoryId=0;
			}
			var description = document.getElementById("selectedMaterial.description").value;
			var update=document.getElementById('update').value;
			var url = "${pageContext.request.contextPath }/material/materialAction!materialEdit.action?selectedMaterial.id="
				+id+"&selectedMaterial.name="+escape(name)+"&selectedMaterial.materialCode="+escape(materialCode)+"&selectedMaterial.brand="+escape(brand)+"&categoryId="+categoryId+"&selectedMaterial.description="+escape(description)+"&update="+update;
			location.href=encodeURI(url);
		}
	}
	
	function cancelEdit(){
		location.href="${pageContext.request.contextPath }/material/materialAction!materialList.action";
	}
	
	function showLevel2Categories(){
		var top = (window.screen.height-440)/2;
		var left = (window.screen.width-640)/2;
		var parentCategoryId = document.getElementById("categoryId").value;
		if(parentCategoryId==null||parentCategoryId==''){
			parentCategoryId=0;
		}
		var returnValue = window.showModalDialog("${pageContext.request.contextPath }/material/materialCategoryAction!selectCategories.action?parentCategoryId="+parentCategoryId+"&level=2", '', "resizable:no;dialogHeight:440px;dialogWidth:640px;center:yes;menubar:no;toolbar:no;location:no;resizable:no;status:no;scrollbars:yes;help:no;dialogTop:"+top+";dialogLeft:"+left);
	}
 </script> 
</head>
<body class="soria">
<form name="categoryEditForm" id="categoryEditForm">
<div style="border: 2px; solid #D2E4F7; margin: 10px; width: 50%;background-color:#D2E4F7;font-size: 1.2em;">
	<div style="width: 80%;">
		<label for="selectedMaterial.name">物料名称：</label>
		<input type="text" id="selectedMaterial.name" name="selectedMaterial.name" value="${selectedMaterial.name }" />
	</div>
	<p>
	<div>
		<label for="selectedMaterial.materialCode">物料编码：</label>
		<input type="text" id="selectedMaterial.materialCode" name="selectedMaterial.materialCode" value="${selectedMaterial.materialCode }" />
	</div>
	<p>
	<div>
		<label for="selectedMaterial.brand">品牌：</label><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<input type="text" id="selectedMaterial.brand" name="selectedMaterial.brand" value="${selectedMaterial.brand }" />
	</div>
	<p>
	<div>
		<label for="selectedMaterial.category.name">所属目录：</label>
		<input type="text" id="selectedMaterial.category.name" name="selectedMaterial.category.name" value="${selectedMaterial.category.name }" />
		<input type="button" value="选择所属目录" onclick="showLevel2Categories();" class="button2" />
	</div>
	<p>
	<div>
		<label for="selectedMaterial.description">描述： </label><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<textarea id="selectedMaterial.description" name="selectedMaterial.description" rows="5" cols="23">${selectedMaterial.description }</textarea>
	</div>
	<p>
	<div>
		<label>登记时间：</label><s:date	name="selectedMaterial.createdDate" format="yyyy-MM-dd HH:mm:ss" />
	</div>
</div>
<p><p>
<div style="border: 1px; solid #0099CC; ">
	<input type="button" value="保存" class="button2" onclick="materialSave();" />
	<span>&nbsp;&nbsp;</span>
	<input type="button" value="取消" class="button2" onclick="cancelEdit();" />
</div>
<input id="selectedId" name="selectedId" value="${selectedMaterial.id }" type="hidden" />
<input id="categoryId" name="categoryId" value="${selectedMaterial.category.id }"	type="hidden" />
<input id="update" name="update" value="${update }" type="hidden" />
</form>
</body>
</html>
