<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" contentType="image/JPEG; charset=UTF-8"%>
<%@ page import="java.io.*;"%>
<!-- viewProcessDefinitionImage.jsp -->
<%
	if(null!=request.getAttribute("imageInputStream")){
		InputStream imageInputStream = (InputStream)request.getAttribute("imageInputStream");
		byte[] imageByte = new byte[1024];
		int lengthInt = -1;
		try {
			while ((lengthInt = imageInputStream.read(imageByte, 0, 1024))!=-1) {
				response.getOutputStream().write(imageByte, 0 , lengthInt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else{
		out.write("数据加载有误!");
	}
	out.clear();
	out = pageContext.pushBody();
%>