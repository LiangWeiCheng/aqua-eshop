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
@import	"${pageContext.request.contextPath }/jsFile/dojoroot/dojox/grid/resources/soriaGrid.css";
</style>

<script type="text/javascript"
	djConfig="parseOnLoad: true, isDebug: true"
	src="${pageContext.request.contextPath }/WebContent/jsFile/dojoroot/dojo/dojo.js"></script>
<script type="text/javascript">  
	dojo.require("dojo.parser");
	dojo.require("dojo.data.ItemFileReadStore");  
	
	function queryCategory(){
		var categoryNameSearch=document.getElementById("categoryNameSearch").value;
		window.location.href = encodeURI("${pageContext.request.contextPath }/material/materialCategoryAction!selectCategories.action?categoryName="+escape(categoryNameSearch)+"&level=${level }");
	}
	
	function selectCategory(){
		var categorySelectors=document.categoryGridForm.selectCategory;
		if(categorySelectors!=null){
			var categoryIdAndName="";
			if(categorySelectors.length!=null&&categorySelectors.length!='undefined'){
				for(var i=0;i<categorySelectors.length;i++){
					var categorySelector = categorySelectors[i];
					if(categorySelector.checked){
						categoryIdAndName=categorySelector.value;
						break;
					}
				}
			}else{
				if(categorySelectors.checked){
					categoryIdAndName=categorySelectors.value;
				}
			}
			if(categoryIdAndName==""){
				alert("请选择物料目录");
			}else{
				var strings = categoryIdAndName.split(";");
				var categoryId=strings[0];
				var categoryName=strings[1];
				if('1'=='${level}'){
					opener.document.getElementById("selectedCategory.parentCategory.name").value=categoryName;
					opener.document.getElementById("parentCategoryId").value=categoryId;
				}else {
					opener.document.getElementById("selectedMaterial.category.name").value=categoryName;
					opener.document.getElementById("categoryId").value=categoryId;
				}
				window.close();
			}
		}
	}
	
 </script> 
</head>
<body class="soria">
<div style="border: 1px solid #0099CC;">
	<div>
	<span>物料目录列表</span>
	</div>	
	<div>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr height="20" valign="baseline">
			<td>
				<input type="button" value="确定" onclick="selectCategory();"/>
			</td>
			<td>
			<label for="categoryNameSearch">目录名称：</label>
			<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<input type="text" dojoType="dijit.form.TextBox" id="categoryNameSearch" name="categoryNameSearch" value="${categoryName }" />
			<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<input type="button" id="search" value="查询" onclick="queryCategory();"/>
			</td>
			<td align="right"><span class="STYLE1"></span></td>
		</tr>
		<tr>
			<td height="3" colspan="2"></td>
		</tr>
	</table>
	</div>
</div>
<form dojoType="dijit.form.Form" name="categoryGridForm" id="categoryGridForm">
<table id="grid" align="left" valign="middle" border="1" style="solid #0099CC;" width="100%">
<tr>
	<td align=center bgcolor="#9DBEEE"><span>&nbsp;</span></td>
	<td align=center bgcolor="#9DBEEE"><span>物料目录</span></td>
	<td align=center bgcolor="#9DBEEE"><span>目录级别</span></td>
	<s:if test="level==2">
	<td align=center><span bgcolor="#9DBEEE">上级目录</span></td>
	</s:if>
	<td align=center bgcolor="#9DBEEE"><span>描述</span></td>
	<td align=center bgcolor="#9DBEEE"><span>创建时间</span></td>
</tr>
<s:if test="materialCategories.size !=0">
		<s:set name="listSize" value="materialCategories.size"/>
		<s:iterator value="materialCategories" status="start">
		<tr onmouseover="this.style.backgroundColor='#60A1EA'" onmouseout="this.style.backgroundColor=''">			
			<td align=center><span><input type="radio" name="selectCategory" value="${id};${name}"/></span></td>
			<td align=center><span>${name }</span></td>
			<td align=center><span>${level }</span></td>
			<s:if test="level==2">
				<td align=center><span>${parentCategory.name }</span></td>
			</s:if>
			<td align=center><span>${description }</span></td>
			<td align=center><span>
			<s:date	name="createdDate" format="yyyy-MM-dd HH:mm:ss" />
			</span></td>
		</tr>
		</s:iterator>
	</s:if>	
</table>
</form>
</body>
</html>
