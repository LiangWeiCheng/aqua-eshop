<%@ page language="java" isELIgnored="false" import="java.util.*"
	pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- left.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>菜单测试</title>
<style type="text/css">
	@import "${pageContext.request.contextPath }/jsFile/dojoroot/dojo/resources/dojo.css";
	@import "${pageContext.request.contextPath }/jsFile/dojoroot/dijit/themes/soria/soria.css";
</style>
<script type="text/javascript"
	djConfig="parseOnLoad: true, isDebug: true" src="${pageContext.request.contextPath }/jsFile/dojoroot/dojo/dojo.js"></script>
<script>
	dojo.require("dojo.data.ItemFileReadStore");
	dojo.require("dijit.Tree");
	dojo.require("dijit.layout.ContentPane");
	dojo.addOnLoad(function() {
		dojo.subscribe("tree", null, function(message) {
			console.log("选择的结点:" + message.node.item.name);
			console.log("选择的结点:" + message.node.item.link);
			parent.right.window.location.href = message.node.item.link;
			//dojo.byId("cc").attr('href', message.node.item.link);//打开树结点的网页连接 
			//dojo.byId("cc").refresh();
			//在此可以调用XHR修改后台JSON数据，这样可以增加或修改树的结点 
		});
	});
	var treeStore = new dojo.data.ItemFileReadStore({
        url : '${pageContext.request.contextPath }/loginJsonPackage/LoginJsonAction!getJsonMenu.action'
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
</script>
</head>
<body class="soria">
	<div dojoType="dijit.Tree" id="tree" jsId="tree " model="treeModel" openOnClick="true" showRoot="false"></div>
	<div dojoType="dijit.layout.ContentPane" region="bottom" id="cc"
		style="background-color: #ACBFD0; height: 200px;" splitter="true">
	</div>
</body>
</html>

