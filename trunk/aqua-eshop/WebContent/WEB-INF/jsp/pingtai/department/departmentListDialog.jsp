<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- departmentListDialog.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>部门列表</title>
    <base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jsFile/dtree/dtree.css">
	<script src="${pageContext.request.contextPath }/jsFile/dtree/dtree.js" type="text/javascript" language="javascript"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		function select(index) {
    		var rec = new Object();
    		var id = document.getElementsByName("id")[index].value;
    		var name = document.getElementsByName("name")[index].value;
    		rec.id = id;
    		rec.name = name;
    		window.returnValue = rec;
    		window.close();
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
		    	<span class="STYLE1">部门列表</span>
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
	
	<script type="text/javascript">
		var mydir="${pageContext.request.contextPath}/jsFile/dtree/";
		d = new dTree("d");
	</script>
	
	<div class="dtree">
		<p>
			<a href="javascript: d.openAll();">&nbsp;全部展开&nbsp;</a> | 
			<a href="javascript: d.closeAll();">&nbsp;全部关闭</a>
		</p>
		<s:iterator value="departmentList" var="department" status="status">
			<s:hidden name="id" value="%{ids}" />
			<s:hidden name="name" value="%{names}" />
			<script type="text/javascript">
				var str = '<a href="javascript:select(<s:property value="#status.index" />)">';
				str +=	'<img alt="点击选中" src="${pageContext.request.contextPath}/images/pingTai/select.gif" border="0"/>';
				str += '</a>';
			</script>
			<s:if test="departmentLevel == -1">
				<script type="text/javascript">
					d.add(<s:property value="ids"/>, <s:property value="departmentLevel"/>, '<s:property value="names"/>');
				</script>
			</s:if>
			<s:else>
				<script type="text/javascript">
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
