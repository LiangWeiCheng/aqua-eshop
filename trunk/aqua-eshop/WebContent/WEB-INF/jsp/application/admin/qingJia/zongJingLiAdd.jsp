<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- zongJingLiAdd.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>总经理审批</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<s:head/>
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		function dataVali(){
			document.zongJingLiSave.submit();
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
		    	<span class="STYLE1">总经理审批</span>
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
		<s:form id="zongJingLiSave" name="zongJingLiSave" action="qingJiaApplicationAdminAction!zongJingLiSave.action" namespace="/applicationAdmin" method="POST">
		<s:token name="formToken"></s:token>
		
		<s:hidden name="qingJia.ids" value="%{qingJia.ids}"></s:hidden>
		<s:hidden name="qingJia.creator.ids" value="%{qingJia.creator.ids}"></s:hidden>
		<input type="hidden" name="qingJia.createdDate" value='<s:date name="%{qingJia.createdDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
		<s:hidden name="qingJia.valid" value="%{qingJia.valid}"></s:hidden>
		<s:hidden name="qingJia.version" value="%{qingJia.version}"></s:hidden>
		
		<s:hidden name="taskId" value="%{#request.taskId}"></s:hidden>
		
		<s:hidden name="qingJia.qingJiaUser.ids" value="%{qingJia.qingJiaUser.ids}"></s:hidden>
		<s:hidden name="qingJia.qingJiaDes" value="%{qingJia.qingJiaDes}"></s:hidden>
		<s:hidden name="qingJia.countDay" value="%{qingJia.countDay}"></s:hidden>
		<input type="hidden" name="qingJia.countDate" value='<s:date name="%{qingJia.countDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
		<input type="hidden" name="qingJia.startDate" value='<s:date name="%{qingJia.startDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
		<input type="hidden" name="qingJia.endDate" value='<s:date name="%{qingJia.endDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
		
		<input type="hidden" name="qingJia.buMenJingLiDate" value='<s:date name="%{qingJia.buMenJingLiDate}" format="yyyy-MM-dd HH:mm:ss"/>'/>
		<s:hidden name="qingJia.buMenJingLiShenPi" value="%{qingJia.buMenJingLiShenPi}"></s:hidden>
		<s:hidden name="qingJia.buMenJingLiDes" value="%{qingJia.buMenJingLiDes}"></s:hidden>
		
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">请假人:</td>
			<td width="35%">
				<s:property value="%{qingJia.qingJiaUser.userInfo.names}"/>
			</td>
			<td align="right" width="15%">天数:</td>
			<td width="35%">
				<s:property value="%{qingJia.countDay}"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">时间:</td>
			<td width="35%">
				<s:date name="%{qingJia.startDate}" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;至&nbsp;
				<s:date name="%{qingJia.endDate}" format="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td align="right" width="15%">原因:</td>
			<td width="35%">
				<s:property value="%{qingJia.qingJiaDes}"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">部门经理审批:</td>
			<td width="35%">
				<s:date name="%{qingJia.buMenJingLiDate}" format="yyyy-MM-dd HH:mm:ss"/>
				<s:if test='qingJia.buMenJingLiShenPi.equals("1")'>
					同意
				</s:if>
				<s:else>
					不同意
				</s:else>
			</td>
			<td align="right" width="15%">批注:</td>
			<td width="35%">
				<s:property value="%{qingJia.buMenJingLiDes}"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">审批:</td>
			<td colspan="3">
				<input type="radio" name="qingJia.zongJingLiShenPi" value="1"/>同意&nbsp;&nbsp;
				<input type="radio" name="qingJia.zongJingLiShenPi" value="2"/>不同意
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">批注:</td>
			<td colspan="3">
				<textarea id="zongJingLiDesId" name="qingJia.zongJingLiDes" style="width:100%;height:200px;"></textarea>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="button" value="保存" onclick="dataVali();" class="button2"/>&nbsp;&nbsp;
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
		</s:form>
	</table>
		
</body>
</html>
