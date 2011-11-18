<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- departmentList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>部门列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jsFile/dtree/dtree.css">
	<script src="${pageContext.request.contextPath }/jsFile/dtree/dtree.js" type="text/javascript" language="javascript"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		var views = '<bu:hasPrivilege operator="/pingTai/departmentPingTaiAction!viewDepartment.action" htmlSrc="true"/>';
		var adds = '<bu:hasPrivilege operator="/WEB-INF/jsp/pingtai/department/departmentAdd.jsp" htmlSrc="true"/>';
		var updates = '<bu:hasPrivilege operator="/pingTai/departmentPingTaiAction!toUpdateDepartment.action" htmlSrc="true"/>';
		var deletes = '<bu:hasPrivilege operator="/pingTai/departmentPingTaiAction!deleteDepartment.action" htmlSrc="true"/>';
		
		/***	原始测试数据
		var mydir='${pageContext.request.contextPath}/jsFile/dtree/';
		d = new dTree('d');

		d.add(0, -1, '公司');
		d.add(1, 0, '研发部', '#');
		d.add(2, 1, 'c/c++小组', '#');
		d.add(3, 1, 'Java小组', '#');
		d.add(4, 2, '图形图像', '#');
		d.add(5, 4, '动画', '#');
		
		d.add(6, 0, '财务部', '#');
		d.add(7, 0, '市场部', '#');
		d.add(8, 0, '行政部', '#');
		
		d.add(9, 0, '我的图片', '#','Pictures I\'ve taken over the years', '', '', mydir+'img/imgfolder.gif');
		d.add(10, 9, '同事合影', '#', 'Pictures of Gullfoss and Geysir');
		d.add(11, 9, '我和家人', '#');

		d.add(12, 0, '回收站', '#', '', '', mydir+'/img/trash.gif');

		document.write(d);
		***/
		
		var mydir="${pageContext.request.contextPath}/jsFile/dtree/";
		d = new dTree("d");
		
		function deleteFun(departmentIds){
			if(confirm("确定删除?")){
				document.location.href = "${pageContext.request.contextPath }/pingTai/departmentPingTaiAction!deleteDepartment.action?department.ids="+departmentIds;
			}
		}
		
	</script>
</head>
<body>
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">部门管理</span>
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
	
	<div class="dtree">
		<p>
			<a href="javascript: d.openAll();">&nbsp;全部展开&nbsp;</a> | 
			<a href="javascript: d.closeAll();">&nbsp;全部关闭</a>
		</p>
		
		<s:iterator value="departmentList" var="department">
			<script type="text/javascript">
				var str = '';
				if(views == 'true'){
					str += '<a href="${pageContext.request.contextPath }/pingTai/departmentPingTaiAction!viewDepartment.action?department.ids=${department.ids}">&nbsp;&nbsp;<img border="0" src="${pageContext.request.contextPath}/images/pingTai/sj.gif" title="查看"/>&nbsp;&nbsp;</a>';
				}
				if(adds == 'true'){
					str += '<a href="${pageContext.request.contextPath }/pingTai/departmentPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/department/departmentAdd.jsp&returnParamater=${department.ids}">&nbsp;&nbsp;<img border="0" src="${pageContext.request.contextPath}/images/pingTai/add.gif" title="添加"/>&nbsp;&nbsp;</a>';
				}
			</script>
			<s:if test="departmentLevel == -1">
				<script type="text/javascript">
					d.add(<s:property value="ids"/>, <s:property value="departmentLevel"/>, '<s:property value="names"/>'+str);
				</script>
			</s:if>
			<s:else>
				<script type="text/javascript">
					if(updates == 'true'){
						str += '<a href="${pageContext.request.contextPath }/pingTai/departmentPingTaiAction!toUpdateDepartment.action?department.ids=${department.ids}">&nbsp;&nbsp;<img border="0" src="${pageContext.request.contextPath}/images/pingTai/edit.gif" title="修改"/>&nbsp;&nbsp;</a>';
					}
					if(deletes == 'true'){
						str += '<a href="#" onclick="deleteFun(${department.ids});">&nbsp;&nbsp;<img border="0" src="${pageContext.request.contextPath}/images/pingTai/del.gif" title="删除"/>&nbsp;&nbsp;</a>';
					}
					d.add(<s:property value="ids"/>, <s:property value="parentDepartment.ids"/>, '<s:property value="names"/>'+str);
				</script>
			</s:else>
		</s:iterator>
		
	</div>
	
   	<script type="text/javascript">
		document.write(d);
	</script>
	
</body>
</html>
