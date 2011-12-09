<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>eShop系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<style type="text/css">
@import	"${pageContext.request.contextPath }/jsFile/dojoroot/dojo/resources/dojo.css";
@import "${pageContext.request.contextPath }/jsFile/dojoroot/dijit/themes/soria/soria.css";
@import "${pageContext.request.contextPath }/jsFile/dojoroot/dojox/grid/resources/soriaGrid.css"; 
</style>
<script type="text/javascript"
	djConfig="parseOnLoad: true, isDebug: true"
	src="${pageContext.request.contextPath }/jsFile/dojoroot/dojo/dojo.js"></script>
<script>
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dojo.data.ItemFileWriteStore");
	dojo.require("dijit.Tree");
	dojo.require("dijit.layout.BorderContainer");
    dojo.require("dijit.layout.ContentPane");
    dojo.require("dojox.grid.DataGrid");
    dojo.require("dojox.grid._Events");
    dojo.require("dojox.grid.cells.dijit");
    
    dojo.addOnLoad(function() {
		dojo.subscribe("tree", null, function(message) {//打开树结点的网页连接
			dijit.byId("center").set('title', message.node.item.name);
			dijit.byId("center").set('href', message.node.item.link);
		});
	});
	var treeStore = new dojo.data.ItemFileReadStore({
		url : '${pageContext.request.contextPath }/jsonPackage/loginJsonAction!getJsonMenu.action'
	});
	var treeModel = new dijit.tree.ForestStoreModel({
		query : {
			type : 'parentObj'
		},
		store : treeStore,
		root : true,
		rootId : "eShop",
		rootLabel : "eShop系统",
		childrenAttrs : [ 'children' ]
	});
	
	
    var gridLayout=[ // 布局是由多个 view 组成的数组
                     { // 每个 view 对象包含名为 cells 的数组
                         cells:[ //cells 是由多个 row 组成的数组
                         [ //row 是由多个描述映射关系的对象组成的数组
                         {name:'类型排序',field:"id"}, 
                         ] 
                         ], 
                         noscroll:true 
                         }, 
                         { 
                         cells:[ 
                         [ 
                         {name:'类型名称',field:"name", editable: true, }, 
                         {name:'类型编号',field:"number", editable: true}, 
                         ] 
                         ] 
                         } 
                         ];
</script>
</head>
<body class="soria">
	<div dojoType="dijit.layout.BorderContainer" design="headline"
		gutters="true" liveSplitters="true" id="borderContainer"
		style="width: 100%; height: 100%;">
        
        <div dojoType="dijit.layout.ContentPane" splitter="true" liveSplitters="true" region="top">
          eShop系统
        </div>
		
		<div dojoType="dijit.layout.ContentPane" splitter="true" region="leading" style="width: 150px;">
            <div dojoType="dijit.Tree" id="tree" jsId="tree " model="treeModel" openOnClick="true" showRoot="false"></div>
		</div>
		<!-- 
		<div dojoType="dijit.layout.ContentPane" splitter="true" region="trailing" style="width: 100px;">Tailing Region</div>
		 -->
		 
		<div dojoType="dijit.layout.ContentPane" splitter="true" region="center" id="center">
	            eShop系统欢迎信息
			<span dojoType="dojo.data.ItemFileWriteStore" jsId="dataStore" 
			    url="${pageContext.request.contextPath }/jsonPackage/dictJsonAction!getDictTypes.action"> 
			</span>
<div id="grid" dojoType="dojox.grid.DataGrid" jsId="icGrid"
    store="dataStore" clientSort="true" style="width: 30em; height: 15em;"
    structure=gridLayout rowSelector="20px">
    <script type="dojo/method" event="onApplyEdit" args="inRowIndex">
        var item=icGrid.getItem(inRowIndex);
        console.debug(item);
        if(item){
            dojo.xhrPost({
                url:"${pageContext.request.contextPath }/jsonPackage/dictJsonAction!upDictType.action",
                preventCache: true,//解决IE缓存问题
                content:item,
                timeout:3000,
                error:function(error){
                    alert("The cell wasn\'t saved!");
                },
                load:function(response){
                    console.debug(response);
                }

            });
        //console.dir(item);
        }
    </script></div>

		</div>
		
		<div dojoType="dijit.layout.ContentPane" splitter="true" region="bottom">
		  Powered by aqua.com
		</div>
	</div>
</body>
</html>