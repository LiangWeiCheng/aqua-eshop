<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- processDefinitionTaskList.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>流程任务分派</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
		
		//人员对话框
		function showModel(statusIndex){
			var url = "${pageContext.request.contextPath }/pingTai/userPingTaiAction!userListDialogTree.action";
			showModalDialogs(url, callbackFun, statusIndex);
		}
		
		//回调
		function callbackFun(rec, statusIndex) {
	    	var id = rec.id;
	    	var name = rec.name;
	    	document.getElementById(statusIndex+"_paraId").value = id;
	    	document.getElementById(statusIndex+"_paraName").value = name;
     	}
     
		function onSubmitForm(){
			document.definitionTaskAssignmentForm.submit();
		}
		
		//任务操作
		function taskOperator(){
			window.location.href = "${pageContext.request.contextPath }/pingTai/taskOperatorPingTaiAction!viewTaskOperator.action";
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
		    	<span class="STYLE1">流程分派</span>
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
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
		<s:form id="definitionTaskAssignmentForm" name="definitionTaskAssignmentForm" action="processDefinitionPingTaiAction!saveProcessDefinitionTaskFlowAssign.action" namespace="/pingTai" method="POST">
		<s:hidden name="processDefinitionId" value="%{#request.processDefinitionId}"></s:hidden>
		<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
			<th>任务名称</th>
			<th>分派类型</th>
			<th>分派参数</th>
			<th>变量名</th>
			<!--  
			<th>时间限制(天)</th>
			-->
			<th>处理Action</th>
			<!--<th>操作</th>-->
		</tr>
		<s:iterator value="%{taskAssignmentList}" status="status" var="taskAssignment">
			<s:hidden name="taskAssignmentList[%{#status.index}].ids" value="%{#taskAssignment.ids}"></s:hidden>
			<s:hidden name="taskAssignmentList[%{#status.index}].version" value="%{#taskAssignment.version}"></s:hidden>
			<s:hidden name="taskAssignmentList[%{#status.index}].creator.ids" value="%{#taskAssignment.creator.ids}"></s:hidden>
			<input type="hidden" name="taskAssignmentList[${status.index}].createdDate" value='<s:date name="%{#taskAssignment.createdDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
			<s:hidden name="taskAssignmentList[%{#status.index}].valid" value="%{#taskAssignment.valid}"></s:hidden>
			
			<s:hidden name="taskAssignmentList[%{#status.index}].processDefinitionId" value="%{#request.processDefinitionId}"></s:hidden>
			<tr bgcolor="#FFFFFF" class="STYLE19">
				<td align="center" width="15%">
					<s:textfield name="taskAssignmentList[%{#status.index}].taskName" value="%{taskName}" readonly="true" cssStyle="width:95%"/>
				</td>
				<td align="center" width="10%">
					<select name="taskAssignmentList[${status.index}].assignType">
						<s:if test='%{taskAssignmentList[#status.index].assignType.equals("peiZhi")}'>
							<option value="peiZhi" selected="selected">配置分派</option>
							<option value="bianLiang">变量分派</option>
						</s:if>
						<s:else>
							<option value="peiZhi">配置分派</option>
							<option value="bianLiang" selected="selected">变量分派</option>
						</s:else>
					</select>
				</td>
				<td align="center" width="30%">
					<s:hidden id="%{#status.index}_paraId" name="taskAssignmentList[%{#status.index}].assignModeParamaterId" value="%{assignModeParamaterId}"></s:hidden>
					<s:textfield id="%{#status.index}_paraName" name="taskAssignmentList[%{#status.index}].assignModeParamaterName" value="%{assignModeParamaterName}" readonly="true" cssStyle="width:75%"/>
					<a href="#" onclick='showModel(<s:property value="#status.index"/>)'>浏览</a>
				</td>
				<td align="center" width="15%">
					<s:textfield name="taskAssignmentList[%{#status.index}].variableName" value="%{variableName}"  cssStyle="width:95%"/>
				</td>
				<!--  
				<td align="center">
					<s:textfield name="taskAssignmentList[%{#status.index}].countDay" value="%{countDay}" />
				</td>
				-->
				<td align="center" width="30%">
					<s:textfield name="taskAssignmentList[%{#status.index}].processAction" value="%{processAction}" cssStyle="width:95%"/>
				</td>
				<!--
				<td align="center" width="10%">
					<a href="#" onclick='taskOperator("${requestScope.processDefinitionId}", "${taskAssignment.ids}")'>任务操作</a>
				</td>
				-->
			</tr>
		</s:iterator>
		<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="6" align="center">
				<input type="button" value="保存" onclick="onSubmitForm()" class='button2'/>&nbsp;&nbsp;
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class='button2'/>
			</td>
		</tr>
		</s:form>
	</table>
	
	
</body>
</html>
