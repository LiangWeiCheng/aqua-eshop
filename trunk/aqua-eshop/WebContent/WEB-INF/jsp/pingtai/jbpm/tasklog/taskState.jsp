<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.jbpm.api.*,java.util.*,org.jbpm.api.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- taskState.jsp -->
<HTML>
<HEAD>
	<TITLE>当前任务状态</TITLE>
	<meta http-equiv="cache-control" content="no-cache, must-revalidate">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="pragram" content="no-cache">
</HEAD>
<BODY>

<!--
<s:property value="%{deploymentId}"/>
<s:property value="%{imageResourceName}"/>
<s:property value="%{activityCoordinates.x}"/>
<s:property value="%{activityCoordinates.y}"/>
<s:property value="%{activityCoordinates.width}"/>
<s:property value="%{activityCoordinates.height}"/>
-->  

	<img src="${pageContext.request.contextPath }/pingTai/processDefinitionPingTaiAction!viewProcessDefinitionImage.action?deploymentId=<s:property value="%{deploymentId}"/>&imageResourceName=<s:property value="%{imageResourceName}"/>" style="position:absolute;left:0px;top:0px;">
	<div style="position:absolute;border:2px solid red;left:<s:property value="%{activityCoordinates.x}"/>px;top:<s:property value="%{activityCoordinates.y}"/>px;width:<s:property value="%{activityCoordinates.width}"/>px;height:<s:property value="%{activityCoordinates.height}"/>px;"></div>

</BODY>
</HTML>
