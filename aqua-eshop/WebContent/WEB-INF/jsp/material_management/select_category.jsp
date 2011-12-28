<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>物料目录列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
<script type="text/javascript">  
	
	function queryCategory(){
		var categoryNameSearch=document.getElementById("categoryNameSearch").value;
		window.location.href = encodeURI("${pageContext.request.contextPath }/material/materialCategoryAction!selectCategories.action?categoryName="+escape(categoryNameSearch)+"&level=${level }");
	}
	
	function chooseCategory(id, name){
		if('1'=='${level}'){
			opener.document.getElementById("selectedCategory.parentCategory.name").value=name;
			opener.document.getElementById("parentCategoryId").value=id;
		}else {
			opener.document.getElementById("selectedMaterial.category.name").value=name;
			opener.document.getElementById("categoryId").value=id;
		}
		window.close();
	}
	
	function chooseCategory2(){
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
<form name="categoryGridForm" id="categoryGridForm">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<input type="button" class="button2" value="确定" onclick="chooseCategory();"/>
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
	  	<tr height="20" valign="baseline">
		    <td>
		    	<label for="categoryNameSearch">目录名称：</label>
		    	<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<input type="text" id="categoryNameSearch" name="categoryNameSearch" value="${categoryName }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<input type="button" class="button2" value="查询" onclick="queryCategory();" />
		    </td>
		    <td>
		    </td>
		    <td align="right">
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
		    	<span class="STYLE1">物料目录列表</span>
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
			<td>&nbsp;&nbsp;</td>
			<td>物料目录</td>
			<td>目录级别</td>
			<s:if test="level==2">
				<td>上级目录</td>
			</s:if>
			<td>描述</td>
			<td>登记时间</td>
		</tr>
			<s:iterator value="queryResult.resultList" var="category">
				<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
					<td><input type="radio" name="selectCategory" value="${id};${name}" onclick="chooseCategory(${id},'${name}');"/></td>
					<td align="center"><s:property value="name"/></td>
					<td align="center"><s:property value="level"/></td>
					<s:if test="level==2">
						<td align="center"><s:property value="parentCategory.name"/></td>
					</s:if>
					<td align="center"><s:property value="description"/></td>
					<td align="center"><s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</s:iterator>
	</table>
	
	
</form>
</body>
</html>
