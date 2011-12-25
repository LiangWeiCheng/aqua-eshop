<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>物料目录列表</title>
<style type="text/css">
@import	"${pageContext.request.contextPath }/jsFile/dojoroot/dojo/resources/dojo.css";
@import	"${pageContext.request.contextPath }/jsFile/dojoroot/dijit/themes/soria/soria.css";
@import	"${pageContext.request.contextPath }/jsFile/dojoroot/dojox/grid/resources/soriaGrid.css";
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsFile/dojoroot/dojo/dojo.js" djConfig="parseOnLoad: true, isDebug: true"></script>

<script>
	dojo.require("dojo.parser");
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dojo.data.ItemFileWriteStore");  
	dojo.require("dojox.grid.DataGrid");
	dojo.require("dojox.grid.Grid");
    dojo.require("dojox.grid._data.model");
    //var dataStore = new dojo.data.ItemFileReadStore({url: "${pageContext.request.contextPath }/material/materialCategoryAction!categoryListJson.action"});
    //var model = new dojox.grid.data.DojoData(null, dataStore, {query:{ id :'*' }, clientSort:true});
</script>
<style>
#grid { 
    border: 1px solid #333;
    width: 100%;
    margin: 10px;
    height: 50em;
    font-size: 0.9em;
    font-family: Geneva, Arial, Helvetica, sans-serif;
}
</style>
</head>
<body class="soria">
<div>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr height="20" valign="baseline">
		<td>
		<input type="button" id="addButton"	name="addButton" value="添加" onclick="categoryAdd();" /> 
		<input type="button" id="editButton" name="editButton" value="编辑"	onclick="categoryEdit();" /> 
		<input type="button" id="deleteButton" name="deleteButton" value="删除" onclick="categoryDelete();" />
		</td>
		<td></td>
		<td align="right"><span class="STYLE1"></span></td>
	</tr>
	<tr>
		<td height="3" colspan="2"></td>
	</tr>
</table>
</div>
<div>
<div dojoType="dojo.data.ItemFileReadStore" jsId="categoryStore" url="${pageContext.request.contextPath }/material/materialCategoryAction!categoryListJson.action"></div>
 	<table id="grid" dojoType="dojox.grid.DataGrid" store="categoryStore" query="{categoryId:'*'}" clientSort="true" align="center" valign="middle">
 		<script type="dojo/method" event="onCellClike" args="evt">
			console.debug(evt);
 		</script>
        <thead>
           <tr>
            <th field="name" width="20%" style="text-align:center;">物料目录</th>
			<th field="level" width="20%" align="center">目录级别</th>
			<th field="parentCategory.name" width="20%" align="center">上级目录</th>
			<th field="description" width="20%" align="center">描述</th>
			<th field="createdDate" width="20%" align="center">登记时间</th>
           </tr>
        </thead>
    </table>
</div>
</body>
</html>
