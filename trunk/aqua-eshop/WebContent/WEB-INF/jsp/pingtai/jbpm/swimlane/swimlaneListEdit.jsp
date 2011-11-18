<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- swimlaneListEdit.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>流程泳道管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
		
		var baseIndex = 0;
		var swimlaneSize = parseInt("${fn:length(swimlaneList)}", 10);
		
		//添加行
		function addLine(){
			var table = document.getElementById("swimlaneTableId");//表格
			var rows = table.rows.length;//行数
			var index = baseIndex + swimlaneSize;//数组索引
			var trNode = table.insertRow();//插入新行
			trNode.style.backgroundColor = "#FFFFFF";
			trNode.className = "STYLE19"
			
			var tdNode01 = trNode.insertCell(0);//tdNode.className = "myTableTh1";
			tdNode01.innerHTML = '<input type="text" name="swimlaneList['+ index +'].processDefinitionId" value="${requestScope.processDefinitionId}" readonly="readonly" style="width:95%"/>';
			
			var tdNode02 = trNode.insertCell(1);
			tdNode02.innerHTML = '<s:textfield name="swimlaneList['+ index +'].swimlaneName" maxlength="" cssStyle="width:95%"></s:textfield>';
			
			var tdNode03 = trNode.insertCell(2);
			var userIdsNames = '<s:hidden id="'+ index +'_userIds" name="swimlaneList['+ index +'].userIds"></s:hidden>';
			userIdsNames += '<s:textfield id="'+ index +'_userNames" name="swimlaneList['+ index +'].userNames" maxlength="" readonly="true" cssStyle="width:88%"></s:textfield>';
			userIdsNames += '<a href="#" onclick="showModelUser('+index+')">浏览</a>';
			tdNode03.innerHTML = userIdsNames;
			
			var tdNode04 = trNode.insertCell(3);
			var groupIdsNames = '<s:hidden id="'+ index +'_groupIds" name="swimlaneList['+ index +'].groupIds"></s:hidden>';
			groupIdsNames += '<s:textfield id="'+ index +'_groupNames" name="swimlaneList['+ index +'].groupNames" maxlength="" readonly="true" cssStyle="width:80%"></s:textfield>';
			groupIdsNames += '<a href="#" onclick="showModelGroup('+index+')">浏览</a>';
			tdNode04.innerHTML = groupIdsNames;
			
			var tdNode05 = trNode.insertCell(4);
			tdNode05.align = "center";
			tdNode05.innerHTML = '<s:textfield name="swimlaneList['+ index +'].variableName" maxlength="" cssStyle="width:95%"></s:textfield>';
			
			var tdNode06 = trNode.insertCell(5);
			tdNode06.align = "center";
			tdNode06.innerHTML = '<input type="button" value="删除" onclick="deleteLine(this.parentElement.parentElement.rowIndex)" class="button2"/>';
			
			baseIndex = baseIndex+1;
		}
		
		//删除行
		function deleteLine(rowIndex){
			var table = document.getElementById("swimlaneTableId");//表格
			table.deleteRow(rowIndex);//删除行
		}
		
		//人员对话框
		function showModelUser(statusIndex){
			var url = "${pageContext.request.contextPath }/pingTai/userPingTaiAction!userListDialogTree.action";
			showModalDialogs(url, callbackFunUser, statusIndex);
		}
		
		//人员对话框回调
		function callbackFunUser(rec, statusIndex) {
	    	var id = rec.id;
	    	var name = rec.name;
	    	document.getElementById(statusIndex+"_userIds").value = id;
	    	document.getElementById(statusIndex+"_userNames").value = name;
     	}
		
		//组对话框
		function showModelGroup(statusIndex){
			var url = "${pageContext.request.contextPath }/pingTai/departmentPingTaiAction!departmentListDialog.action";
			showModalDialogs(url, callbackFunGroup, statusIndex);
		}
		
		//组对话框回调
		function callbackFunGroup(rec, statusIndex) {
	    	var id = rec.id;
	    	var name = rec.name;
	    	document.getElementById(statusIndex+"_groupIds").value = id;
	    	document.getElementById(statusIndex+"_groupNames").value = name;
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
		    	<span class="STYLE1">流程泳道</span>
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
	
	<s:form id="swimlaneAddForm" name="swimlaneAddForm" namespace="/pingTai" action="swimlanePingTaiAction!swimlaneUpdate.action" method="POST">
		<s:token name="formToken"></s:token>
		<s:hidden name="processDefinitionId" value="%{#request.processDefinitionId}"></s:hidden>
		<table id="swimlaneTableId" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
			<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
				<td width="15%">流程ID</td>
				<td width="10%">泳道名称</td>
				<td width="30%">用户</td>
				<td width="20%">用户组</td>
				<td width="15%">变量</td>
				<td width="10%">操作</td>
			</tr>
			<s:iterator value="%{swimlaneList}" var="swimlane" status="status">
				<s:hidden name="swimlaneList[%{#status.index}].ids" value="%{#swimlane.ids}"></s:hidden>
				<s:hidden name="swimlaneList[%{#status.index}].version" value="%{#swimlane.version}"></s:hidden>
				<s:hidden name="swimlaneList[%{#status.index}].creator.ids" value="%{#swimlane.creator.ids}"></s:hidden>
				<input type="hidden" name="swimlaneList[${status.index}].createdDate" value='<s:date name="%{#swimlane.createdDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
				<s:hidden name="taskAssignmentList[%{#status.index}].valid" value="%{#swimlane.valid}"></s:hidden>
				<tr bgcolor="#FFFFFF" class="STYLE19">
					<td>
						<input type="text" name='swimlaneList[<s:property value="%{#status.index}"/>].processDefinitionId' value="${requestScope.processDefinitionId}" readonly="readonly" style="width:95%"/>			
					</td>
					<td>
						<s:textfield name="swimlaneList[%{#status.index}].swimlaneName" value="%{swimlaneName}" maxlength="20" cssStyle="width:95%"></s:textfield>
					</td>
					<td>
						<s:hidden id="%{#status.index}_userIds" name="swimlaneList[%{#status.index}].userIds" value="%{userIds}"></s:hidden>
						<s:textfield id="%{#status.index}_userNames" name="swimlaneList[%{#status.index}].userNames" value="%{userNames}" maxlength="500" readonly="true" cssStyle="width:88%"></s:textfield>
						<a href="#" onclick='showModelUser(<s:property value="%{#status.index}"/>)'>浏览</a>
					</td>
					<td>
						<s:hidden id="%{#status.index}_groupIds" name="swimlaneList[%{#status.index}].groupIds" value="%{groupIds}"></s:hidden>
						<s:textfield id="%{#status.index}_groupNames" name="swimlaneList[%{#status.index}].groupNames" value="%{groupNames}" maxlength="500" readonly="true" cssStyle="width:80%"></s:textfield>
						<a href="#" onclick='showModelGroup(<s:property value="%{#status.index}"/>)'>浏览</a>
					</td>
					<td>
						<s:textfield name="swimlaneList[%{#status.index}].variableName" value="%{variableName}" maxlength="20" cssStyle="width:95%"></s:textfield>
					</td>
					<td align="center">
						<input type="button" value="删除" onclick="deleteLine(this.parentElement.parentElement.rowIndex)" class='button2'/>
					</td>
				</tr>	
			</s:iterator>
		</table>
		
		<br/>
		
		<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#a8c7ce">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td colspan="5">
					<input type="button" value="添加行" onclick="addLine()" class='button2'/>&nbsp;&nbsp;
					<s:submit value="更新" cssClass='button2'></s:submit>&nbsp;&nbsp;
					<input type="button" value="返回" onclick="javascript:history.go(-1);" class='button2'/>
				</td>
			</tr>
		</table>
		
	</s:form>
	
</body>
</html>
