<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>物料列表</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script type="text/javascript"
	djConfig="parseOnLoad: true, isDebug: true"
	src="${pageContext.request.contextPath }/jsFile/dojoroot/dojo/dojo.js"></script>
	<script type="text/javascript">  
		function addMaterial(){
			location.href="${pageContext.request.contextPath }/material/materialAction!materialShow.action";
	   }
		
		function editMaterial(){
			var selections = document.materialListForm.selection;
			var selectedMaterialId=0;
			var count=0;
			if(selections!=null){
				if(selections.length!=null&&selections.length!='undefined'){
					for(var i=0;i<selections.length;i++){
						if(selections[i].checked){
							selectedMaterialId=selections[i].value;
							count++;
						}
					}
				}else{
					if(selections.checked){					
						selectedMaterialId=selections.value;
						count=1;
					}
				}
			}
			if(count==0){
				alert("请选择物料");
			}else if(count>1){
				alert("一次只能编辑一个物料");
			}else{
				location.href="${pageContext.request.contextPath }/material/materialAction!materialShow.action?update=true&selectedId="+selectedMaterialId;
			}
		}
		
		function deleteMaterials(){
			var selections = document.materialListForm.selection;
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
				alert("请选择物料");
			}else{
				dojo.xhrPost({
	                url:"${pageContext.request.contextPath }/material/materialAction!materialDelete.action",
	                preventCache: true,
	                content: {"selection":deleteItems},
	                timeout:10000,
	                error:function(error){
	                	alert("删除物料失败！");
	                },
	                load:function(response){
	                	location.href="${pageContext.request.contextPath }/material/materialAction!materialList.action";
	                }

	            });
			}
		}
		
		function queryMaterial(){
			location.href=encodeURI("${pageContext.request.contextPath }/material/materialAction!materialList.action?materialName="+escape(document.getElementById("materialNameSearch").value));
		}
 </script> 
</head>
<body>
<form id="materialListForm" name="materialListForm">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<input type="button" class="button2" id="addButton" name="addButton" value="添加" onclick="addMaterial();"/>
		    	<input type="button" class="button2" id="editButton" name="editButton" value="编辑" onclick="editMaterial();"/>
		    	<input type="button" class="button2" id="deleteButton" name="deleteButton" value="删除" onclick="deleteMaterials();"/>
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
		    	<label for="materialNameSearch">目录名称：</label>
		    	<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<input type="text" id="materialNameSearch" name="materialNameSearch" value="${materialName }" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<input type="button" class="button2" value="查询" onclick="queryMaterial();" />
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
			<td>物料</td>
			<td>物料编码</td>
			<td>所属目录</td>
			<td>描述</td>
			<td>登记时间</td>
		</tr>
			<s:iterator value="queryResult.resultList" var="category">
				<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
					<td><input type="checkbox" name="selection" value="<s:property value="id"/>"/></td>
					<td align="center"><s:property value="name"/></td>
					<td align="center"><s:property value="materialCode"/></td>
					<td align="center"><s:property value="category.name"/></td>
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
