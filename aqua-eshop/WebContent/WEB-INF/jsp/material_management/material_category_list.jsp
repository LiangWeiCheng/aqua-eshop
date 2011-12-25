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
@import "${pageContext.request.contextPath }/jsFile/dojoroot/dojox/grid/enhanced/resources/EnhancedGrid.css";   
@import "${pageContext.request.contextPath }/jsFile/dojoroot/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";   
@import "${pageContext.request.contextPath }/jsFile/dojoroot/dojox/grid/enhanced/resources/Pagination.css";
</style>

<script type="text/javascript"
	djConfig="parseOnLoad: true, isDebug: true"
	src="${pageContext.request.contextPath }/WebContent/jsFile/dojoroot/dojo/dojo.js"></script>
<script type="text/javascript">  
 	dojo.require("dojox.grid.EnhancedGrid");  
	dojo.require("dojox.grid.enhanced.plugins.Pagination");
	dojo.require("dojox.grid.enhanced.plugins.IndirectSelection");
	dojo.require("dojo.data.ItemFileReadStore");  
	var grid = null;
	var store = null;
	dojo.addOnLoad(function(){  
	store = new dojo.data.ItemFileReadStore({ url: '${pageContext.request.contextPath }/material/materialCategoryAction!categoryListJson.action?categoryName='+dijit.byId("categoryNameSearch").value});  
	var layout = [
	                  {
	                	 cells: [
	                        {name: '物料目录', width: "20%", field: "name", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '目录级别', width: "20%", field: "level", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '上级目录', width: "20%", field: "parentCategory.name", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '描述', width: "20%", field: "description", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '创建时间', width: "20%", field: "createdDate", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'}
	                          ]
	                  	}
	                  ];  
    
	   grid = new dojox.grid.EnhancedGrid({  
		 rowSelector: '20px',
	     store: store,  
	     structure: layout,  
	     rowsPerPage: 25,
	     plugins : {  
	    	 pagination: {   
	    		 pageSizes: ["10", "25", "50", "100", "All"],     
	    		 description: true,   
	    		 sizeSwitch: true,   
	    		 pageStepper: true,   
	    		 gotoButton: true,   
	    		 maxPageStep: 5,   
	    		 itemTitle: "物料目录",
	    		 position: "bottom"   
	    		 },
    		 indirectSelection : {  
    			 headerSelector : true,  
    			 name : "selection",  
    			 width : "60px",  
    			 styles : "text-align: center;"  
    			 }  
	     }  
	   }, document.createElement('div'));  
	   dojo.byId("categoryGrid").appendChild(grid.domNode);  
	   grid.startup();
	 });  
	
	function addCategory(){
		dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialCategoryAction!categoryShow.action");
   }
	
	function editCategory(){
		var selectedCategories = grid.selection.getSelected();
		var length = selectedCategories.length;
		if(length==0){
			alert("请选择物料目录");
		}else if(length>1){
			alert("一次只能修改一个物料目录");
		}else{
			var selectedCategory = selectedCategories[0];
			var categoryId=this.store.getValue(selectedCategory,"categoryId");
			dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialCategoryAction!categoryShow.action?update=true&selectedId="+categoryId);
		}
	}
	
	function deleteCategories(){
		var selectedCategories = grid.selection.getSelected();
		var length = selectedCategories.length;
		if(length==0){
			alert("请选择物料目录");
		}else{
			var deleteItems=new Array();
			var count=0;
			dojo.forEach(selectedCategories,function(selectedCategory){
				var categoryId=this.store.getValue(selectedCategory,"categoryId");
				deleteItems[count]=parseFloat(categoryId);
				count++;
			});
			dojo.xhrPost({
                url:"${pageContext.request.contextPath }/material/materialCategoryAction!categoryDelete.action",
                preventCache: true,
                content: {"selection":deleteItems},
                timeout:10000,
                error:function(error){
                	alert("删除物料目录失败！");
                },
                load:function(response){
                	dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialCategoryAction!categoryList.action");
                }

            });
		}
	}
	
	function queryCategories(){
		dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialCategoryAction!categoryList.action?categoryName="+dijit.byId("categoryNameSearch").value);
	}
 </script> 
</head>
<body class="soria">
<div style="border: 1px; solid #0099CC; ">
	<div dojoType="dijit.form.Button" id="addCategoryButton">
	新建
	<script type="dojo/method" event="onClick" args="inRowIndex">
		addCategory();
	</script>
	</div>
	<div dojoType="dijit.form.Button" id="editCategoryButton">
	修改
	<script type="dojo/method" event="onClick" args="inRowIndex">
		editCategory();
	</script>
	</div>
	<div dojoType="dijit.form.Button" id="removeCategoriesButton">
	删除
	<script type="dojo/method" event="onClick" args="inRowIndex">
		deleteCategories();
	</script>
	</div>
	<div dojoType="dijit.form.Button" id="importCategoriesButton">导入</div>
</div>
<div style="border: 1px solid #0099CC; ">
<div><span>物料目录列表</span></div>	
<div>
<label for="categoryNameSearch">目录名称：</label>
<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
<input type="text" dojoType="dijit.form.TextBox" id="categoryNameSearch" name="categoryNameSearch" value="${categoryName }" />
<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
<div dojoType="dijit.form.Button">
查询
<script type="dojo/method" event="onClick" args="inRowIndex">
	queryCategories();
</script>
</div>
</div>
</div>
<form dojoType="dijit.form.Form" name="categoryGridForm" id="categoryGridForm">
<div id="categoryGrid" style="width: 100%; height: 88%;"></div>
</form>
</body>
</html>
