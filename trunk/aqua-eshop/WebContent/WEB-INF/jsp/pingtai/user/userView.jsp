<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- userView.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>查看用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
</head>
<body>
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">用户详细信息</span>
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
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">部门:</td>
			<td width="35%">
				${user.department.names }
			</td>
			<td align="right" width="15%">数据操作级别:</td>
			<td width="35%">
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="userOperatorDataLevel" dictNumbers="%{user.userOperatorDataLevel}" nodeId=""/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">真实姓名:</td>
			<td width="35%">
				<s:property value="user.userInfo.names"/>
			</td>
			<td align="right" width="15%">登录名:</td>
			<td width="35%">
				<s:property value="user.userName"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">登录名:</td>
			<td width="35%">
				<s:property value="user.userName"/>
			</td>
			<td align="right" width="15%">真实姓名:</td>
			<td width="35%">
				<s:property value="user.userInfo.names"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">用户类型:</td>
			<td>
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="userClass" dictNumbers="%{user.userClass}" nodeId=""/>
			</td>
			<td align="right">性别:</td>
			<td>
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="sex" dictNumbers="%{user.userInfo.sex}" nodeId=""/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">邮箱:</td>
			<td>
				<s:property value="user.userInfo.email"/>
			</td>
			<td align="right">手机号:</td>
			<td>
				<s:property value="user.userInfo.mobile"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">电话:</td>
			<td>
				<s:property value="user.userInfo.telephone"/>
			</td>
			<td align="right">邮编:</td>
			<td>
				<s:property value="user.userInfo.postboy"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">生日:</td>
			<td>
				<s:date name="user.userInfo.birthday" format="yyyy-MM-dd"/>
			</td>
			<td align="right">身份证:</td>
			<td>
				<s:property value="user.userInfo.idCard"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">QQ号码:</td>
			<td>
				<s:property value="user.userInfo.qq"/>
			</td>
			<td align="right">个人主页:</td>
			<td>
				<s:property value="user.userInfo.homepage"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">民族:</td>
			<td>
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="folk" dictNumbers="%{user.userInfo.folk}" nodeId=""/>
			</td>
			<td align="right">婚姻状况:</td>
			<td>
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="marriage" dictNumbers="%{user.userInfo.marriage}" nodeId=""/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">政治面貌:</td>
			<td>
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="government" dictNumbers="%{user.userInfo.government}" nodeId=""/>
			</td>
			<td align="right">身高(cm):</td>
			<td>
				<s:property value="user.userInfo.stature"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">体重(kg):</td>
			<td>
				<s:property value="user.userInfo.avoirdupois"/>
			</td>
			<td align="right">血型:</td>
			<td>
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="bloodGroup" dictNumbers="%{user.userInfo.bloodGroup}" nodeId=""/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">出生地:</td>
			<td>
				<s:property value="user.userInfo.nativityAddress"/>
			</td>
			<td align="right">户口所在地:</td>
			<td>
				<s:property value="user.userInfo.householder"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">文化程度:</td>
			<td>
				<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="wenHuaChenDu" dictNumbers="%{user.userInfo.culture}" nodeId=""/>
			</td>
			<td align="right">毕业院校:</td>
			<td>
				<s:property value="user.userInfo.schoolName"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">专业:</td>
			<td>
				<s:property value="user.userInfo.speciality"/>
			</td>
			<td align="right">毕业时间:</td>
			<td>
				<s:date name="user.userInfo.finishSchoolDate" format="yyyy-MM-dd"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">地址:</td>
			<td colspan="3">
				<s:textarea id="addresId" name="user.userInfo.address" disabled="true" cssStyle="width:100%;height:auto;" readonly="true"></s:textarea>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">其它说明:</td>
			<td colspan="3">
				<s:textarea id="descriptionId" name="user.userInfo.description" disabled="true" cssStyle="width:100%;height:auto;" readonly="true"></s:textarea>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
	</table>
	
</body>
</html>
