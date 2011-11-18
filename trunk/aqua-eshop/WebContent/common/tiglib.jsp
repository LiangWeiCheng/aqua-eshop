<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="oscache" uri="http://www.opensymphony.com/oscache" %>

<% 
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>

<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>
<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/jsFile/kindEditor/kindeditor-min.js"></script>
<script src="${pageContext.request.contextPath}/jsFile/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
<script src="${pageContext.request.contextPath}/jsFile/dwr/engine.js" type="text/javascript"></script> 