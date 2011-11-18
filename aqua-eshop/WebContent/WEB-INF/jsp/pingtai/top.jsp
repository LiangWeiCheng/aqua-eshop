<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- top.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>无标题文档</title>
	<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.STYLE1 {
			font-size: 12px;
			color: #000000;
		}
		.STYLE5 {font-size: 12}
		.STYLE7 {font-size: 12px; color: #FFFFFF; }
		-->
	</style>
	<script language="javascript">
	
		//1.动态时间
	    var t = null;
	    t = setTimeout(time, 1000);
	    function time(){
	       clearTimeout(t);
	       dt = new Date();
	       var h=dt.getHours();
	       var m=dt.getMinutes();
	       var s=dt.getSeconds();
	       var tp = document.getElementById("timePlace");
	       result = dt.toLocaleDateString()+" "+dt.toLocaleTimeString();
	       document.getElementById("timeShow").innerHTML = "当前的时间："+h+"时"+m+"分"+s+"秒";
	       t = setTimeout(time,1000);              
	    } 
	    
	</script>
</head>
<body>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
		    <td height="57" background="${pageContext.request.contextPath}/images/pingTai/main_03.gif">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			      	<tr>
				        <td width="378" height="57">&nbsp;</td><!--  background="${pageContext.request.contextPath}/images/pingTai/main_01.gif" -->
				        <td>&nbsp;</td>
				        <td width="281" valign="bottom">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          	<tr>
						            <td width="33" height="27"><img src="${pageContext.request.contextPath}/images/pingTai/main_05.gif" width="33" height="27" /></td>
						            <td width="248" background="${pageContext.request.contextPath}/images/pingTai/main_06.gif">
						            <table width="225" border="0" align="center" cellpadding="0" cellspacing="0">
						              	<tr>
							                <td height="17"><div align="right"><img src="${pageContext.request.contextPath}/images/pingTai/pass.gif" width="69" height="17" /></div></td>
							                <td><div align="right"><img src="${pageContext.request.contextPath}/images/pingTai/user.gif" width="69" height="17" /></div></td>
							                <td>
								                <div align="right">
								                	<a href="${pageContext.request.contextPath}/loginPackage/loginPingTaiAction!logout.action">
								                		<img src="${pageContext.request.contextPath}/images/pingTai/quit.gif" border="0" width="69" height="17" />
								                	</a>
								                </div>
							                </td>
						              	</tr>
						            </table>
						            </td>
					          	</tr>
					        </table>
				        </td>
			      	</tr>
			    </table>
		    </td>
	  	</tr>
	  	<tr>
		    <td height="40" background="${pageContext.request.contextPath}/images/pingTai/main_10.gif">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			      	<tr>
				        <td width="194" height="40" >&nbsp;</td><!-- background="${pageContext.request.contextPath}/images/pingTai/main_07.gif" -->
				        <td>
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          	<tr>
						            <td width="21"><img src="${pageContext.request.contextPath}/images/pingTai/main_13.gif" width="19" height="14" /></td>
						            <td width="35" class="STYLE7" title="首页">
						            	<div align="center" onclick="javascript:parent.mainFrame.right.location.href='${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/right.jsp'">首页</div>  	
						            </td>
						            <td width="21" class="STYLE7">
						            	<img src="${pageContext.request.contextPath}/images/pingTai/main_15.gif" width="19" height="14" />
						            </td>
						            <td width="35" class="STYLE7" title="后退">
						            	<div align="center" onclick="javascript:parent.mainFrame.right.history.go(-1)">后退</div>
						            </td>
						            <td width="21" class="STYLE7">
						            	<img src="${pageContext.request.contextPath}/images/pingTai/main_17.gif" width="19" height="14" />
						            </td>
						            <td width="35" class="STYLE7" title="前进">
						            	<div align="center" onclick="javascript:parent.mainFrame.right.history.go(1)">前进</div>
						            </td>
						            <td width="21" class="STYLE7">
						            	<img src="${pageContext.request.contextPath}/images/pingTai/main_19.gif" width="19" height="14" />
						            </td>
						            <td width="35" class="STYLE7" title="刷新">
						            	<div align="center" onclick="javascript:parent.mainFrame.right.location.reload()">刷新</div>
						            </td>
						            <td width="21" class="STYLE7">
						            	<img src="${pageContext.request.contextPath}/images/pingTai/main_21.gif" width="19" height="14" />
						            </td>
						            <td width="35" class="STYLE7" title="帮助">
						            	<div align="center" onclick="javascript:alert('暂无帮助')">帮助</div>
						            </td>
						            <td>&nbsp;</td>
					          	</tr>
					        </table>
				        </td>
				        <td width="248" background="${pageContext.request.contextPath}/images/pingTai/main_11.gif">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          	<tr>
						            <td width="16%"><span class="STYLE5"></span></td>
						            <td width="75%"><div align="center"><span class="STYLE7" id="timeShow"></span></div></td>
						            <td width="9%">&nbsp;</td>
					          	</tr>
					        </table>
				        </td>
			      	</tr>
			    </table>
		    </td>
	  	</tr>
	  	<tr>
		    <td height="30" background="${pageContext.request.contextPath}/images/pingTai/main_31.gif">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			      	<tr>
				        <td width="8" height="30"><img src="${pageContext.request.contextPath}/images/pingTai/main_28.gif" width="8" height="30" /></td>
				        <td width="147" background="${pageContext.request.contextPath}/images/pingTai/main_29.gif">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          	<tr>
						            <td width="24%">&nbsp;</td>
						            <td width="43%" height="20" valign="bottom" class="STYLE1">管理菜单</td>
						            <td width="33%">&nbsp;</td>
					          	</tr>
					        </table>
				        </td>
				        <td width="39"><img src="${pageContext.request.contextPath}/images/pingTai/main_30.gif" width="39" height="30" /></td>
				        <td>
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          	<tr>
						            <td height="20" valign="bottom"><span class="STYLE1">当前登录用户：${sessionScope.currentUser.userName}&nbsp;</span></td>
						            <td valign="bottom" class="STYLE1">
							            <div align="right">
								            <img src="${pageContext.request.contextPath}/images/pingTai/sj.gif" width="6" height="7" />
								            IP：${sessionScope.clientIp}&nbsp; &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/pingTai/sj.gif" width="6" height="7" /> &nbsp;
								                               提供商：中国武汉 &nbsp; &nbsp;<img src="${pageContext.request.contextPath}/images/pingTai/sj.gif" width="6" height="7" /> &nbsp;
								                               单位：武汉某单位
							            </div>
						            </td>
					          	</tr>
					        </table>
				        </td>
				        <td width="17"><img src="${pageContext.request.contextPath}/images/pingTai/main_32.gif" width="17" height="30" /></td>
			      	</tr>
			    </table>
		    </td>
	  	</tr>
	</table>
	
</body>
</html>