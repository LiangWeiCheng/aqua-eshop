<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- userList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jsFile/dtree/dtree.css">
	<script src="${pageContext.request.contextPath }/jsFile/dtree/dtree.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
		
		function selectFun() {
			var idsStr = "";
			var namesStr = "";
    		var arr = document.getElementsByName("userCheckbox");
    		for(i=0; i<arr.length; i++){
            	if(arr[i].checked){
      				var nodeVelue = arr[i].value;
      				var idsNameArr = nodeVelue.split("&");
      				idsStr += idsNameArr[0]+",";
      				namesStr += idsNameArr[1]+",";
    			}
    		}
    		idsStr = idsStr.substring(0, idsStr.length-1);
    		namesStr = namesStr.substring(0, namesStr.length-1);
    		
    		var rec = new Object();
    		rec.id = idsStr;
    		rec.name = namesStr;
    		window.returnValue = rec;
    		window.close();
    	}
		
		var mydir="${pageContext.request.contextPath}/jsFile/dtree/";
		d = new dTree("d");
		
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
		    	<span class="STYLE1">用户列表</span>
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
			<input type="button" value="全部展开" onclick="d.openAll();" class="button2"/>&nbsp;| 
			<input type="button" value="全部关闭" onclick="d.closeAll();" class="button2"/>&nbsp;|
			<input type="button" value="确定" onclick="selectFun();" class="button2"/>
		</p>
		
		<s:iterator value="departmentList" var="department">
			<s:if test="departmentLevel == -1">
				<script type="text/javascript">
					d.add(<s:property value="ids"/>, <s:property value="departmentLevel"/>, '<s:property value="names"/>');
				</script>
			</s:if>
			<s:else>
				<script type="text/javascript">
					d.add(<s:property value="ids"/>, <s:property value="parentDepartment.ids"/>, '<s:property value="names"/>');
				</script>
			</s:else>
		</s:iterator>
		
		<s:iterator value="userList" var="user">
			<script type="text/javascript">
				d.add(${user.ids}-1000, ${user.department.ids}, '${user.userInfo.names}'+'<input type="checkbox" name="userCheckbox" value="${user.ids}&${user.userInfo.names}"/>');
			</script>
		</s:iterator>
		
	</div>
	
   	<script type="text/javascript">
   		document.write(d);
	</script>
   	
</body>
</html>
