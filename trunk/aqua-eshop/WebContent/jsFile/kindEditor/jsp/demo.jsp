<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>KindEditor JSP</title>
	<script type="text/javascript" charset="utf-8" src="../kindeditor.js"></script>
	<script type="text/javascript">
		KE.show({
			id : 'content1',
			imageUploadJson : '${pageContext.request.contextPath }/pingTai/kindEditorFileUploadAction!uploadImage.action',
			fileManagerJson : '${pageContext.request.contextPath }/pingTai/kindEditorFileUploadAction!imageFileManager.action',
			allowFileManager : true,
			afterCreate : function(id) {
				KE.event.ctrl(document, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
				KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
			}
			/*
			,
			items : [   
                'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline', 
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'emoticons', 'link']
                */
		});
	</script>
</head>
<body>

	<%=htmlData%>
	<form name="example" method="post" action="demo.jsp">
		<textarea id="content1" name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
	
</body>
</html>

<%!
	private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
%>
