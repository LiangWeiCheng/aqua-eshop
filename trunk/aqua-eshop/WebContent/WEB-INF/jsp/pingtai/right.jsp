<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>无标题文档</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/pingTai/pingTai.js" type="text/javascript" language="javascript"></script>	
</head>
<body>

	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">管理人员基本信息列表</span>
		    </td>
		    <td align="right" bgcolor="#353c44">
		    	<span class="STYLE1">
	              	<input type="checkbox" name="checkbox11" id="checkbox11" />
	              	全选	&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/pingTai/add.gif" width="10" height="10" /> 
	              	添加 &nbsp; <img src="${pageContext.request.contextPath}/images/pingTai/del.gif" width="10" height="10" /> 
	              	删除 &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/pingTai/edit.gif" width="10" height="10" /> 
	              	编辑 &nbsp;
              	</span>
		    </td>
	  	</tr>
	  	<tr>
		    <td height="3" colspan="2"></td>
	  	</tr>
	</table>  
	 
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()" onclick="clickto()">
      	<tr align="center" class="STYLE10">
	        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10">
		        <input type="checkbox" name="checkbox" id="checkbox" />
	        </td>
	        <td width="10%" height="20" bgcolor="d3eaef">用户名</td>
	        <td width="15%" height="20" bgcolor="d3eaef" >用户角色</td>
	        <td width="14%" height="20" bgcolor="d3eaef">联系方式</td>
	        <td width="16%" height="20" bgcolor="d3eaef">IP地址</td>
	        <td width="27%" height="20" bgcolor="d3eaef">详细描述</td>
	        <td width="14%" height="20" bgcolor="d3eaef">基本操作</td>
      	</tr>
      	<tr align="center" bgcolor="#FFFFFF"class="STYLE19">
	        <td height="20">
		        <input type="checkbox" name="checkbox2" id="checkbox2" />
	        </td>
	        <td>admin</td>
	        <td>系统管理员</td>
	        <td>13913612548</td>
	        <td>192.168.0.124</td>
	        <td>用户可以对系统的所有操作进行管理...</td>
	        <td>删除 | 查看</td>
     	</tr>
     	<tr align="center" bgcolor="#FFFFFF"class="STYLE19">
	        <td height="20">
		        <input type="checkbox" name="checkbox2" id="checkbox2" />
	        </td>
	        <td>admin</td>
	        <td>系统管理员</td>
	        <td>13913612548</td>
	        <td>192.168.0.124</td>
	        <td>
	        	<input type="button" value="open" onclick="testFun001();"/>
	        	<input type="button" value="close" onclick="testFun002();"/>
	        	<script type="text/javascript">
	        		function testFun001(){
	        			//self.top.viewMsg("XXX");
	        			self.top.sendMessage("2","XXX");
	        			//var node = self.top["topFrame"].document.getElementById("divMsg");
	        			//self.top.frames["topFrame"].showSendMessage("123", "QQ");
	        			//self.top.frames["topFrame"].sendMessage("2","XXX");
	        			//alert(self.top.frames["topFrame"].showSendMessage("123", "QQ"));
	        		}
	        		function testFun002(){
	        			self.top.closeDiv();
	        		}
	        	</script>
	        </td>
	        <td>删除 | 查看</td>
     	</tr>
    </table>
	  
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      	<tr class="STYLE22" valign="bottom">
	        <td width="33%">
	        </td>
      	</tr>
   	</table>
	  
	
</body>
</html>
