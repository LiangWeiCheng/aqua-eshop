<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>物料目录列表</title>
<style type="text/css">
@import	"${pageContext.request.contextPath }/jsFile/dojoroot/dojo/resources/dojo.css";
@import	"${pageContext.request.contextPath }/jsFile/dojoroot/dijit/themes/soria/soria.css";
</style>

<script type="text/javascript"
	djConfig="parseOnLoad: true, isDebug: true"
	src="${pageContext.request.contextPath }/WebContent/jsFile/dojoroot/dojo/dojo.js"></script>
<script type="text/javascript">  
	dojo.require("dojo.data.ItemFileReadStore");  
	dojo.require("dijit.form.Form");
	
	function categorySave(){
		var id=document.getElementById('selectedId').value;
		var name=document.getElementById('selectedCategory.name').value;
		if(name==null||name.replace(/\s/g,"")==""){
			alert("必须填写物料目录名称");
		}else{
			var level=document.getElementById('selectedCategory.level').value;
			var oldLevel=document.getElementById('oldCategoryLevel').value;
			var parentCategoryId=document.getElementById('parentCategoryId').value;
			if(parentCategoryId==null||parentCategoryId==''){
				parentCategoryId = 0;
			}
			var description=document.getElementById('selectedCategory.description').value;
			var update=document.getElementById('update').value;
			var url="";
			if(level!=oldLevel){
				if(level==1){
					if(confirm("将所选的二级目录改为一级目录会使其所包含的所有物料失去目录属性，确定修改目录级别？")){
						url = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
							+id+"&selectedCategory.level="+level+"&parentCategoryId="+0;
					}
				}else{
					if(confirm("将所选的一级目录改为二级目录会使其所包含的所有二级目录失去上级目录，确定修改目录级别？")){
						url = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
							+id+"&selectedCategory.level="+level+"&parentCategoryId="+parentCategoryId;
					}
				}
			}else{
				if(level==1){
					url = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
						+id+"&selectedCategory.level="+level+"&parentCategoryId="+0;
				}else{
					url = "${pageContext.request.contextPath }/material/materialCategoryAction!categoryEdit.action?selectedCategory.id="
							+id+"&selectedCategory.level="+level+"&parentCategoryId="+parentCategoryId;
				}
			}
			url=url+"&selectedCategory.name="+escape(name)+"&selectedCategory.description="+escape(description)+"&update="+update;
			dijit.byId("center").set('href',encodeURI(url));
		}
	}
	
	function changeCategoryLevel(){
		var level=document.getElementById('selectedCategory.level').value;
		if(level==1){
			document.getElementById("selectedCategory.parentCategory.name").value="";
			document.getElementById("selectedCategory.parentCategory.name").disabled="disabled";
			document.getElementById("selectCategoryButton").disabled="disabled";
			document.getElementById("parentCategoryId").value=0;
		}else{
			document.getElementById("selectedCategory.parentCategory.name").disabled="";
			document.getElementById("selectCategoryButton").disabled="";
		}
	}
	
	function cancelEdit(){
		dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialCategoryAction!categoryList.action");
	}
	
	function showLevel1Categories(){
		var top = (window.screen.height-440)/2;
		var left = (window.screen.width-640)/2;
		var parentCategoryId = dojo.byId("parentCategoryId").value;
		if(parentCategoryId==null||parentCategoryId==''){
			parentCategoryId=0
		}
		var returnValue = window.showModalDialog("${pageContext.request.contextPath }/material/materialCategoryAction!selectCategories.action?parentCategoryId="+parentCategoryId+"&level=1", '', "resizable:no;dialogHeight:440px;dialogWidth:640px;center:yes;menubar:no;toolbar:no;location:no;resizable:no;status:no;scrollbars:yes;help:no;dialogTop:"+top+";dialogLeft:"+left);
		//var returnValue = window.open("${pageContext.request.contextPath }/material/materialCategoryAction!selectCategories.action?parentCategoryId="+dojo.byId("parentCategoryId").value+"&level=1", '', "width=640,height=440,toolbar=no, menubar=no, scrollbars=yes,resizable=no,location=no, status=no,top=\""+top+"\",left=\""+left+"\"");
	}
	
 </script> 
</head>
<body class="soria">
<form name="categoryEditForm" id="categoryEditForm">
<div style="border: 2px; solid #D2E4F7; margin: 10px; width: 50%;background-color:#D2E4F7;font-size: 1.2em;">
	<div style="width: 80%;">
		<label for="selectedCategory.name">目录名称：</label>
		<input type="text" dojoType="dijit.form.TextBox" id="selectedCategory.name" name="selectedCategory.name" value="${selectedCategory.name }" />
	</div>
	<p>
	<div>
		<label for="selectedCategory.level">目录级别：</label>
		<select id="selectedCategory.level"	name="selectedCategory.level" onchange="changeCategoryLevel();">
			<s:if test="selectedCategory.level==1">
				<option value="1" selected>级别一</option>
				<option value="2">级别二</option>
			</s:if>
			<s:else>
				<option value="1">级别一</option>
				<option value="2" selected>级别二</option>
			</s:else>
		</select>
	</div>
	<p>
	<div>
		<label for="selectedCategory.parentCategory.name">上级目录：</label>
		<input type="text" dojoType="dijit.form.TextBox" id="selectedCategory.parentCategory.name" name="selectedCategory.parentCategory.name" value="${selectedCategory.parentCategory.name }" />
		<div dojoType="dijit.form.Button" id="selectCategoryButton" style="font-size: 0.9em;">选择上级目录
			<script type="dojo/method" event="onClick">
				showLevel1Categories();
			</script>
		</div>
	</div>
	<p>
	<div>
		<label for="selectedCategory.description">描述： </label><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<textarea id="selectedCategory.description" name="selectedCategory.description" rows="5" cols="23">${selectedCategory.description }</textarea>
	</div>
	<p>
	<div>
		<label>登记时间：</label><s:date	name="selectedCategory.createdDate" format="yyyy-MM-dd HH:mm:ss" />
	</div>
</div>
<p><p>
<div style="border: 1px; solid #0099CC; ">
	<div dojoType="dijit.form.Button" id="addCategoryButton">
	保存
	<script type="dojo/method" event="onClick" args="inRowIndex">
		categorySave();
	</script>
	</div>
	<div dojoType="dijit.form.Button" id="editCategoryButton">
	取消
	<script type="dojo/method" event="onClick" args="inRowIndex">
		cancelEdit();
	</script>
	</div>
</div>
</form>

<input id="selectedId" name="selectedId" value="${selectedCategory.id }" type="hidden" />
<input id="parentCategoryId" name="parentCategoryId" value="${selectedCategory.parentCategory.id }"	type="hidden" />
<input id="oldCategoryLevel" name="oldCategoryLevel" value="${selectedCategory.level }" type="hidden" />
<input id="update" name="update" value="${update }" type="hidden" />
</body>
</html>
