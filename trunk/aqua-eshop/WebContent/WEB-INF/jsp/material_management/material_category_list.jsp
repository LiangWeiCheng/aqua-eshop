<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>物料目录列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script type="text/javascript"
	djConfig="parseOnLoad: true, isDebug: true"
	src="${pageContext.request.contextPath }/jsFile/dojoroot/dojo/dojo.js"></script>
	
	<script type="text/javascript">
	function addCategory(){
		location.href="${pageContext.request.contextPath }/material/materialCategoryAction!categoryShow.action";
   }
	
	function editCategory(){
		var selections = document.categoryListForm.selection;
		var selectedCategoryId=0;
		var count=0;
		if(selections!=null){
			if(selections.length!=null&&selections.length!='undefined'){
				for(var i=0;i<selections.length;i++){
					if(selections[i].checked){
						selectedCategoryId=selections[i].value;
						count++;
					}
				}
			}else{
				if(selections.checked){					
					selectedCategoryId=selections.value;
					count=1;
				}
			}
		}
		if(count==0){
			alert("请选择物料目录");
		}else if(count>1){
			alert("一次只能编辑一个物料目录");
		}else{
			location.href="${pageContext.request.contextPath }/material/materialCategoryAction!categoryShow.action?update=true&selectedId="+selectedCategoryId;
		}
	}
	
	function deleteCategories(){
		var selections = document.categoryListForm.selection;
		var count=0;
		var deleteItems=new Array();
		if(selections!=null){
			if(selections.length!=null&&selections.length!='undefined'){
				for(var i=0;i<selections.length;i++){
					if(selections[i].checked){
						deleteItems[count]=selections[i].value;
						count++;
					}
				}
			}else{
				if(selections.checked){					
					deleteItems[0]=selections.value;
					count=1;
				}
			}
		}
		
		if(count==0){
			alert("请选择物料目录");
		}else{
			dojo.xhrPost({
                url:"${pageContext.request.contextPath }/material/materialCategoryAction!categoryDelete.action",
                preventCache: true,
                content: {"selection":deleteItems},
                timeout:10000,
                error:function(error){
                	alert("删除物料目录失败！");
                },
                load:function(response){
                	location.href="${pageContext.request.contextPath }/material/materialCategoryAction!categoryList.action";
                }

            });
		}
	}
	
	function queryCategories(){
		location.href=encodeURI("${pageContext.request.contextPath }/material/materialCategoryAction!categoryList.action?categoryName="+escape(document.getElementById("categoryNameSearch").value));
	}			
	</script>
</head>
<body>
	<form id="categoryListForm" name="categoryListForm">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<input type="button" class="button2" id="addButton" name="addButton" value="添加" onclick="addCategory();"/>
		    	<input type="button" class="button2" id="editButton" name="editButton" value="编辑" onclick="editCategory();"/>
		    	<input type="button" class="button2" id="deleteButton" name="deleteButton" value="删除" onclick="deleteCategories();"/>
		    	<input type="button" class="button2" id="deleteButton" name="deleteButton" value="导入"/>
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
				<input type="button" class="button2" value="查询" onclick="queryCategories();" />
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
			<td>上级目录</td>
			<td>描述</td>
			<td>登记时间</td>
		</tr>
			<s:iterator value="queryResult.resultList" var="category">
				<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
					<td><input type="checkbox" name="selection" value="<s:property value="id"/>"/></td>
					<td align="center"><s:property value="name"/></td>
					<td align="center"><s:property value="level"/></td>
					<td align="center"><s:property value="parentCategory.name"/></td>
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
	</form>
</body>
</html>
