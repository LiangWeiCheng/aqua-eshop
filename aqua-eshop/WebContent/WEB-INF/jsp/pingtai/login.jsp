<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<% 
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<!-- login.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			overflow:hidden;
		}
		.STYLE3 {font-size: 12px; color: #adc9d9; }
		-->
	</style>
	<link type="text/css" href="${pageContext.request.contextPath }/jsFile/keybody/keyboard_style_1.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/keybody/base.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/keybody/keyboard.js" type="text/javascript" language="javascript"></script>	
	<script type="text/javascript">
	
		$(function(){
			$(".hs_keyboard2").plugins_keyboard();
			$(".hs_keyboard").plugins_keyboard({type:"mini"});
			$(".hs_keyboard3").plugins_keyboard({type:"mini",other:"mini2"});
		});
		
		function validata(){
			var userNameId = document.getElementById("userNameId").value.trim();
			var userNameIdResult = utils_letterNumber(userNameId, "登录名", 6, 16);
			if(!userNameIdResult){
				return false;
			}
			
			var passWordId = document.getElementById("passWordId").value.trim();
			var passWordIdResult = utils_letterNumber(passWordId, "密码", 6, 16);
			if(!passWordIdResult){
				return false;
			}
			
			var yanZhengMaId = document.getElementById("yanZhengMaId").value.trim();
			var yanZhengMaIdResult = utils_letterNumber(yanZhengMaId, "验证码", 4, 6);
			if(!yanZhengMaIdResult){
				return false;
			}
			
			document.userLoginForm.submit();
			
		}
		
		function resetFun(){
			document.userLoginForm.reset();
		}
		
		$(document).ready(function() {
			//1.初始化页面焦点
			document.getElementById("userNameId").focus();
			//2.键盘事件
			var tds = $("html"); 
			tds.keydown(function(event){
				var myEvent = event || window.event; //解决不同浏览器获取事件对象的差异
					switch(myEvent.keyCode) {
						case 13://回车事件
							validata(); break;
					}
			});
		});
		
	</script>
</head>
<body>
	
	<s:form id="userLoginForm" name="userLoginForm" namespace="/loginPackage" action="loginPingTaiAction!loginPingTai.action" method="POST">
		<s:token name="formToken"></s:token>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td bgcolor="#1075b1">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="608" background="${pageContext.request.contextPath }/images/pingTai/login/login_03.gif">
					<table width="847" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="318" background="${pageContext.request.contextPath }/images/pingTai/login/login_04.gif">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="84">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="381" height="84" background="${pageContext.request.contextPath }/images/pingTai/login/login_06.gif">
											&nbsp;
										</td>
										<td width="162" valign="middle"
											background="${pageContext.request.contextPath }/images/pingTai/login/login_07.gif">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="44" height="24" valign="bottom">
														<div align="right">
															<span class="STYLE3">用户</span>
														</div>
													</td>
													<td width="10" valign="bottom">
														&nbsp;
													</td>
													<td height="24" colspan="2" valign="bottom">
														<div align="left">
															<s:textfield id="userNameId" name="user.userName" maxlength="16" title="请输入登录名" cssStyle="width: 100px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;"></s:textfield>													
														</div>
													</td>
												</tr>
												<tr>
													<td height="24" valign="bottom">
														<div align="right">
															<span class="STYLE3">密码</span>
														</div>
													</td>
													<td width="10" valign="bottom">
														&nbsp;
													</td>
													<td height="24" colspan="2" valign="bottom">
														<s:password id="passWordId" name="user.passWord" maxlength="16" title="请输入登录密码" cssClass="hs_keyboard2" cssStyle="width: 100px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;"></s:password>
													</td>
												</tr>
												<tr>
													<td height="24" valign="bottom">
														<div align="right">
															<span class="STYLE3">验证码</span>
														</div>
													</td>
													<td width="10" valign="bottom">
														&nbsp;
													</td>
													<td width="52" height="24" valign="bottom">
														<s:textfield id="yanZhengMaId" name="yanZhengMa" maxlength="10" title="请输入右侧验证码" cssStyle="width: 50px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;"></s:textfield>
													</td>
													<td width="62" valign="bottom">
														<div align="left">
															<img id="yanZhengMa" src="${pageContext.request.contextPath }/loginPackage/authImagePingTaiAction!toImageJsp.action?rand=<%=Math.random()%>" onclick="this.src='${pageContext.request.contextPath}/loginPackage/authImagePingTaiAction!toImageJsp.action?rand='+Math.random();" width="60" height="18">
														</div>
													</td>
												</tr>
												<tr>
												</tr>
											</table>
										</td>
										<td width="26">
											<img src="${pageContext.request.contextPath }/images/pingTai/login/login_08.gif" width="26" height="84">
										</td>
										<td width="67" background="${pageContext.request.contextPath }/images/pingTai/login/login_09.gif">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="25">
														<div align="center">
															<a href="#" title="点击登录">
																<img border="0" src="${pageContext.request.contextPath }/images/pingTai/login/dl.gif" width="57" height="20" onclick="validata()">
															</a>
														</div>
													</td>
												</tr>
												<tr>
													<td height="25">
														<div align="center" title="点击清空">
															<a href="#">
																<img border="0" src="${pageContext.request.contextPath }/images/pingTai/login/cz.gif" width="57" height="20" onclick="resetFun()">
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="211" background="${pageContext.request.contextPath }/images/pingTai/login/login_10.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="206" background="${pageContext.request.contextPath }/images/pingTai/login/login_11.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#152753">
					&nbsp;
				</td>
			</tr>
		</table>
	</s:form>
		
</body>
</html>
