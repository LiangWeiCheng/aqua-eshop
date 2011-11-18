<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	Object orderState = request.getAttribute("orderState");
	if(null!=orderState){
		out.print(orderState.toString());
	}
%>
