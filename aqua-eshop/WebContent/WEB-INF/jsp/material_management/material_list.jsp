<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- qingJiaList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>物料列表</title>
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
	store = new dojo.data.ItemFileReadStore({ url: '${pageContext.request.contextPath }/material/materialAction!materialListJson.action?materialName='+dijit.byId("materialNameSearch").value });  
	var layout = [
	                  {
	                	 cells: [
	                        {name: '物料', width: "20%", field: "name", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '物料编码', width: "20%", field: "materialCode", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '所属目录', width: "20%", field: "categoryName", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '描述', width: "20%", field: "description", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'},
	                        {name: '创建时间', width: "20%", field: "createdDate", cellStyles: 'text-decoration: none; cursor:default; text-align: center;position: relative; left: -10px'}
	                          ]
	                  	}
	                  ];  
    
	   grid = new dojox.grid.EnhancedGrid({  
		 rowSelector: '20px',
	     store: store,  
	     structure: layout,  
	     plugins : {  
	    	 pagination: {   
	    		 pageSizes: ["10", "25", "50", "100", "All"],   
	    		 description: true,   
	    		 sizeSwitch: true,   
	    		 pageStepper: true,   
	    		 gotoButton: true,   
	    		 maxPageStep: 5,
	    		 rowsPerPage: 25,
	    		 itemTitle: "物料",
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
	   dojo.byId("materialGrid").appendChild(grid.domNode);  
	   grid.startup();  
	 });  
	
	function addMaterial(){
		dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialAction!materialShow.action");
   }
	
	function editMaterial(){
		var selectedMaterials = grid.selection.getSelected();
		var length = selectedMaterials.length;
		if(length==0){
			alert("请选择物料");
		}else if(length>1){
			alert("一次只能修改一个物料");
		}else{
			var selectedMaterial = selectedMaterials[0];
			var materialId=this.store.getValue(selectedMaterial,"materialId");
			dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialAction!materialShow.action?update=true&selectedId="+materialId);
		}
	}
	
	function deleteMaterials(){
		var selectedMaterials = grid.selection.getSelected();
		var length = selectedMaterials.length;
		if(length==0){
			alert("请选择物料目录");
		}else{
			var deleteItems=new Array();
			var count=0;
			dojo.forEach(selectedMaterials,function(selectedMaterial){
				var materialId=this.store.getValue(selectedMaterial,"materialId");
				deleteItems[count]=parseFloat(materialId);
				count++;
			});
			dojo.xhrPost({
                url:"${pageContext.request.contextPath }/material/materialAction!materialDelete.action",
                preventCache: true,
                content: {"selection":deleteItems},
                timeout:10000,
                error:function(error){
                	alert("删除物料目录失败！");
                },
                load:function(response){
                	dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialAction!materialList.action");
                }

            });
		}
	}
	
	function queryMaterial(){
		dijit.byId("center").set('href',"${pageContext.request.contextPath }/material/materialAction!materialList.action?materialName="+dijit.byId("materialNameSearch").value);
	}
 </script> 
</head>
<body>
<div style="border: 1px; solid #0099CC; ">
<div dojoType="dijit.form.Button">
新建
<script type="dojo/method" event="onClick" args="inRowIndex">
	addMaterial();
</script>
</div>
<div dojoType="dijit.form.Button">
修改
<script type="dojo/method" event="onClick" args="inRowIndex">
	editMaterial();
</script>
</div>
<div dojoType="dijit.form.Button">
删除
<script type="dojo/method" event="onClick" args="inRowIndex">
	deleteMaterials();
</script>
</div>
<div dojoType="dijit.form.Button">导入</div>
</div>
<div style="border: 1px solid #0099CC; ">
<div><span>物料列表</span></div>	
<div>
<label for="materialNameSearch">物料名称：</label>
<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
<input type="text" dojoType="dijit.form.TextBox" id="materialNameSearch" name="materialNameSearch" value="${materialName }" />
<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
<div dojoType="dijit.form.Button">
查询
<script type="dojo/method" event="onClick" args="inRowIndex">
	queryMaterial();
</script>
</div>
</div>
</div>
<form dojoType="dijit.form.Form" name="materialGridForm" id="materialGridForm">
<div id="materialGrid" style="width: 100%; height: 88%;"></div>
</form>
</body>
</html>
