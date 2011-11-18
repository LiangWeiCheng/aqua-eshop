<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- dictTypeAdd.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>添加数据字典</title>
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
			var table = document.getElementById("dictTableId");//表格
			var rows = table.rows.length;//行数
			var index = rows-1;//索引行数
			var trNode = table.insertRow();//插入新行
			trNode.style.backgroundColor = "#FFFFFF";
			trNode.className = "STYLE19";
			
			var tdNode01 = trNode.insertCell(0);//tdNode.className = "myTableTh1";
			tdNode01.innerHTML = '<s:textfield name="dictList['+ baseIndex +'].numbers" size="" maxlength="" cssStyle="width:95%"></s:textfield>';
			
			var tdNode02 = trNode.insertCell(1);
			tdNode02.innerHTML = '<s:textfield name="dictList['+ baseIndex +'].names" size="" maxlength="" cssStyle="width:95%"></s:textfield>';
			
			var tdNode03 = trNode.insertCell(2);
			tdNode03.innerHTML = '<s:textfield name="dictList['+ baseIndex +'].value" size="" maxlength="" cssStyle="width:95%"></s:textfield>';
			
			var tdNode04 = trNode.insertCell(3);  
			tdNode04.innerHTML = '<s:textfield name="dictList['+ baseIndex +'].orderIds" size="" maxlength="" cssStyle="width:95%"></s:textfield>';
			
			var tdNode05 = trNode.insertCell(4);
			tdNode05.align = "center";
			tdNode05.innerHTML = '<input type="button" value="删除" onclick="deleteLine(this.parentElement.parentElement.rowIndex)" class="button2"/>';
			
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
		    	<span class="STYLE1">添加字典</span>
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
	
	<s:form id="dictAddForm" name="dictAddForm" namespace="/pingTai" action="dictPingTaiAction!saveDictType.action" method="POST">
		<s:token name="formToken"></s:token>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
			<tr bgcolor="#FFFFFF" class="STYLE19">
				<td align="right" width="15%">类型编号:</td>
				<td width="20%">
					<s:textfield name="dictType.numbers" value="" maxlength="50" cssStyle="width:95%"></s:textfield>
				</td>
				<td align="right" width="15%">类型名称:</td>
				<td width="20%">
					<s:textfield name="dictType.names" value="" maxlength="25" cssStyle="width:95%"></s:textfield>
				</td>
				<td align="right" width="15%">类型排序:</td>
				<td width="15%">
					<s:textfield name="dictType.orderIds" value="" maxlength="20" cssStyle="width:95%"></s:textfield>
				</td>
			</tr>
		</table>
		
		<br/>
		
		<table id="dictTableId" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
			<tr align="center" class="STYLE10" height="20" bgcolor="d3eaef">
				<th width="25%">字典编号</th>
				<th width="25%">字典名称</th>
				<th width="25%">字典值</th>
				<th width="10%">字典排序</th>
				<th width="15%">操作</th>
			</tr>
			<tr bgcolor="#FFFFFF" class="STYLE19">
				<td>
					<s:textfield name="dictList[0].numbers" size="" maxlength="" cssStyle="width:95%"></s:textfield>
				</td>
				<td>
					<s:textfield name="dictList[0].names" size="" maxlength="" cssStyle="width:95%"></s:textfield>
				</td>
				<td>
					<s:textfield name="dictList[0].value" size="" maxlength="" cssStyle="width:95%"></s:textfield>
				</td>
				<td>
					<s:textfield name="dictList[0].orderIds" size="" maxlength="" cssStyle="width:95%"></s:textfield>
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
