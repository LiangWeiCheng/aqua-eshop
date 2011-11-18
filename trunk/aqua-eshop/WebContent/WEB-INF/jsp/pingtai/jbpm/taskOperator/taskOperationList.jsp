<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- taskOperationEdit.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>管理任务操作</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<s:head/>
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script type="text/javascript">
	
		var baseIndex = 1;
		
		//添加行
		function addLine(){
			var table = document.getElementById("taskOperatorTableId");//表格
			var rows = table.rows.length;//行数
			var index = rows-1;//索引行数
			var trNode = table.insertRow();//插入新行
			trNode.style.backgroundColor = "#FFFFFF";
			trNode.className = "STYLE19"
			
			var tdNode01 = trNode.insertCell(0);//tdNode.className = "myTableTh1";
			var tdNode01Str = '<s:hidden name="taskOperationList[0].processDefinitionId" value=""></s:hidden>';
				tdNode01Str += '<s:hidden name="taskOperationList[0].taskAssignment.ids" value=""></s:hidden>';
				tdNode01Str += '<s:textfield name="taskOperationList['+ baseIndex +'].taskName" maxlength="" cssStyle="width:95%"></s:textfield>';
			tdNode01.innerHTML = tdNode01Str;
			
			var tdNode02 = trNode.insertCell(1);
			tdNode02.innerHTML = '<s:textfield name="taskOperationList['+ baseIndex +'].operator" maxlength="" cssStyle="width:95%"></s:textfield>';
			
			var tdNode03 = trNode.insertCell(2);
			tdNode03.align = "center";
			tdNode03.innerHTML = '<input type="button" value="删除" onclick="deleteLine(this.parentElement.parentElement.rowIndex)" class="button2"/>';
			
			baseIndex = baseIndex+1;
		}
		
		function deleteLine(rowIndex){
			var table = document.getElementById("dictTableId");//表格
			table.deleteRow(rowIndex);//删除行
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
		    	<span class="STYLE1">管理任务操作</span>
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
	
	<s:form id="taskOperatorAddForm" name="taskOperatorAddForm" namespace="/pingTai" action="taskOperatorPingTaiAction!saveTaskOperator.action" method="POST">
		<s:token name="formToken"></s:token>
		<table id="taskOperatorTableId" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
			<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
				<th width="30%">任务名称</th>
				<th width="50%">操作</th>
				<th width="20%">操作</th>
			</tr>
			<tr bgcolor="#FFFFFF" class="STYLE19">
				<td>
					<s:hidden name="taskOperationList[0].processDefinitionId" value="%{processDefinitionId}"></s:hidden>
					<s:hidden name="taskOperationList[0].taskAssignment.ids" value="%{taskAssignment.ids}"></s:hidden>
					<s:textfield name="taskOperationList[0].taskName" maxlength="" cssStyle="width:95%"></s:textfield>
				</td>
				<td>
					<s:textfield name="taskOperationList[0].operator" maxlength="" cssStyle="width:95%"></s:textfield>
				</td>
				<td align="center">
					<input type="button" value="删除" onclick="deleteLine(this.parentElement.parentElement.rowIndex)" class='button2'/>
				</td>
			</tr>
		</table>
		
		<br/>
		
		<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#a8c7ce">
			<tr align="center" bgcolor="#FFFFFF" class="STYLE19">
				<td colspan="5" align="center">
					<input type="button" value="添加行" onclick="addLine()" class='button2'/>&nbsp;&nbsp;
					<s:submit value="保存" cssClass='button2'></s:submit>&nbsp;&nbsp;
					<input type="button" value="返回" onclick="javascript:history.go(-1);" class='button2'/>
				</td>
			</tr>
		</table>
		
	</s:form>
	
</body>
</html>
