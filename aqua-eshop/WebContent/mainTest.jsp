<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>生成条形码测试</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="${pageContext.request.contextPath }/jsFile/jquery/jquery-1.4.4.min.js" type="text/javascript" language="javascript"></script>
		<script src="${pageContext.request.contextPath }/jsFile/autoMessage.js" type="text/javascript" language="javascript"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body onload="autoMessage('getDataUrl', 'toPageUrl');">
		<%=request.getRealPath("/WEB-INF/ftl") %>
		<input type="text" id="word" name="" size="50" style="margin-top: 0px;"/>
		<div id="auto"></div>

		<br />

		<a target="_blank"
			href="http://wpa.qq.com/msgrd?v=3&uin=150584428&site=qq&menu=yes"><img
				border="0" src="http://wpa.qq.com/pa?p=2:150584428:41"
				alt="点击这里给我发消息" title="点击这里给我发消息">
		</a>

		<br />

		<bu:dictRead nodeStyle="" nodeClass="" dictTypeNumbers="sex"
			dictNumbers="man" nodeId=""/>
			
		<br />
		
		下面是条形码	
		<table border="1">
			<tr>
				<td>
					<h1>
						code39
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=0123456789&type=code39&fmt=png&hrfont=Arial"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						code128
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=0123456789&type=code128&fmt=png&hrfont=Arial"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						Codabar
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=0123456789&type=codabar"
						height="100px" width=300px />
				</td>
				<td>
					
				</td>
			</tr>
			<tr>
				<td>
					<h1>
						intl2of5
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=01234567890540&type=intl2of5"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						upc-a
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=012345678912&type=upc-a"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						ean-13
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=200123457893&type=ean-13"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						ean-8
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=20123451&type=ean-8"
						height="100px" width=300px />
				</td>
			</tr>
			<tr>
				<td>
					<h1>
						postnet
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=01234567890540&type=postnet"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						royal-mail-cbc
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=012345AS678912&type=royal-mail-cbc"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						pdf417
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=200123457893&type=pdf417"
						height="100px" width=300px />
				</td>
				<td>
					<h1>
						datamatrix
					</h1>
					<img
						src="<%=request.getContextPath()%>/barcode4j?msg=20123451&type=datamatrix"
						height="100px" width=300px />
				</td>
			</tr>
		</table>

	</body>
</html>

