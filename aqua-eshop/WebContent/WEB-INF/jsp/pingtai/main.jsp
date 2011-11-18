<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>LittleAnt管理工作平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body style="margin:0px;overflow-y:hidden;">

	<!-- 
  	<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
		<frame src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/center.jsp" name="mainFrame" id="mainFrame" />
		<frame src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/down.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
	</frameset>
	
	<noframes>
		<body>
		</body>
	</noframes>
	-->
	
	<table height="100%" width="100%" cellspacing="0" style="border: 0px; border-bottom-width: 0px; border-left-width: 0px; border-right-width: 0px;border-top-width: 0px;">
		<tr height="127px">
			<td>
				<iframe src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/top.jsp" 
					id="topFrame" name="topFrame" scrolling="no" noresize="noresize" frameborder="0px;" 
					width="100%" height="127px" marginheight="0px" marginwidth="0px"></iframe>
			</td>
		</tr>
		<tr height="100%">
			<td>
				<iframe src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/center.jsp" 
					 id="mainFrame" name="mainFrame" frameborder="0px;" height="100%" width="100%" scrolling="auto" 
					 marginheight="0px" marginwidth="0px"></iframe>
			</td>
		</tr>
		<tr height="11px">
			<td>
				<iframe src="${pageContext.request.contextPath }/loginPackage/loginPingTaiAction!goToUrl.action?returnPageURL=/WEB-INF/jsp/pingtai/down.jsp" 
					id="bottomFrame" name="bottomFrame" scrolling="no" noresize="noresize" frameborder="0px;" 
					height="11px" width="100%" marginheight="0px" marginwidth="0px"></iframe>
			</td>
		</tr>
	</table>
	
	<div id="divmsg" style="border-right: #455690 1px solid; border-top: #a6b4cf 1px solid; z-index:99999; left: 0px; visibility: hidden; border-left: #a6b4cf 1px solid; width: 250px; border-bottom: #455690 1px solid; position: absolute; top: 0px; height: 150px; background-color: #c9d3f3">
		<table style="border-top: #ffffff 1px solid; border-left: #ffffff 1px solid" cellspacing="0" cellpadding="0" width="100%" bgcolor="#cfdef4" border="0">
			<tbody>
				<tr>
					<td style="font-size: 12px;color: #0f2c8c" width="30" height="24"></td>
					<td style="font-weight: normal; font-size: 12px;color: #1f336b; padding-top: 4px;padding-left: 4px" valign=center width="100%">消息提示:</td>
					<td style="padding-top: 2px;padding-right:2px" valign="center" align="right" width="19"><span title="关闭" style="cursor: hand;color:red;font-size:12px;font-weight:bold;margin-right:4px;" onclick="closeDiv()" >×</span></td>
				</tr>
				<tr>
					<td style="padding-right: 1px;padding-bottom: 1px" colspan="3" height="125">
						<div id="showDivId" style="border-right: #b9c9ef 1px solid; padding-right: 2px; border-top: #728eb8 1px solid; padding-left: 2px; font-size: 12px; padding-bottom: 2px; border-left: #728eb8 1px solid; width: 100%; color: #1f336b; padding-top: 5px; border-bottom: #b9c9ef 1px solid; height: 100%">
							<br/><br/>
							<div align="center" style="word-break:break-all">
								<font id="sendMessageTypeId">
									<font id="sendUserNameId" color="#ff0000"></font>给您发了一条新消息,请注意查收!
								</font>
							</div>
							<br/><br/>
							<div align="center" style="word-break:break-all">
								<a id="hrefId" onclick="" title="点击此处查看">
									<font id="fontId" color=#ff0000>点击查看</font>
								</a>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<script src="${pageContext.request.contextPath}/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/jsFile/dwr/engine.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/jsFile/dwr/util.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/jsFile/dwr/interface/messageManager.js"></script>
	<script type="text/javascript">
		/**
		//防止session超时
	    var t = null;
		var changeTime = 28*60*1000;//30分钟超时,28分钟请求一次
	    t = setTimeout(time, changeTime);
	    function time(){
	       clearTimeout(t);
	       //AJAX...
	       t = setTimeout(time, changeTime);              
	    }*/
	    
	    //全屏
	    //key_F11();
	    
		//对话框
		function showModalDialogsMySize(url, callback, parameter, dialogParamater){
			//"dialogHeight:500px;dialogWidth:600px;help:no;maximize:no;minimize:no;scroll:no;status:no;resizable:no"
			showModalDialogsMySize(url, callback, parameter, dialogParamater)
		}
		
		function callback(windowReturn, parameter){
			
		}
		
		/**
		 * API说明:
		 * 		1.sendMessage(receiverId, sendContent)	发送即时消息
		 * 		2.clickMethod(url)						提示框点击查看触发
		 * 		3.viewMsg(message)						显示提示框
		 * 		4.closeDiv()							关闭提示框
		 **/	
	
		window.onload = init;
		window.onunload = destory;
		
		function init() {
			dwr.engine.setActiveReverseAjax(true);	//激活反转
			messageManager.init("${sessionScope.currentUser.ids}", "${sessionScope.currentUser.userName}");//登录
		}
		
		function destory(){
			messageManager.destory("${sessionScope.currentUser.ids}");
		}
		
		//1.发送即时消息:发送者ID,发送者名称,接收者ID,消息内容
		function sendMessage(receiverId, sendContent){
			messageManager.instantSend("${sessionScope.userObject.userId}", "${sessionScope.userObject.userName}", receiverId, sendContent);
		}
		
		//2.提示框点击查看触发
		function clickMethod(url){
			window.frames["mainFrame"].frames["right"].location.href = "${pageContext.request.contextPath}" + url;
			closeDiv();
		}
		
		//3.java调用显示消息
		function showSendMessage(senderUserName, message){//接收消息,进行提示,消息类型:messageTypeOne
			if(senderUserName=="${sessionScope.userObject.userName}"){//处理刚登陆时未处理任务的提醒
				document.getElementById("sendMessageTypeId").innerHTML = message;//注入显示消息
			}else{
				document.getElementById("sendMessageTypeId").innerHTML = message;//注入显示消息
			}
			viewMsg(message); //显示提示框
		}
		
		//根据窗体高度和宽度，改变短消息提示框的高度和宽度
		window.onresize = function(){
			resizeDiv();
		};
		
		//出现错误时，不做任何处理
		window.onerror = function(){}; 

		var divTop, divLeft, divWidth, divHeight, docHeight, docWidth, objTimer, i = 0;//关于位置的相关变量
		
		//显示提示框
		function viewMsg(message)
		{	
			//document.getElementById("sendMessageTypeId").innerHTML = message;//注入显示消息
			try{
				divTop = parseInt(document.getElementById("divMsg").style.top, 10); //div的x坐标
				divLeft = parseInt(document.getElementById("divMsg").style.left, 10); //div的y坐标
				divHeight = parseInt(document.getElementById("divMsg").offsetHeight, 10);//div的高度
				divWidth = parseInt(document.getElementById("divMsg").offsetWidth, 10); //div的宽度
				docWidth = document.body.clientWidth; //窗体宽度
				docHeight = document.body.clientHeight; //窗体高度
				document.getElementById("divMsg").style.top = parseInt(document.body.scrollTop,10) + docHeight + 10;//设置div的Y坐标
				document.getElementById("divMsg").style.left = parseInt(document.body.scrollLeft,10) + docWidth - divWidth-15;//设置div的X坐标
				document.getElementById("divMsg").style.visibility = "visible"; //设置div显示
				objTimer = window.setInterval("moveDiv()", 1); //设置定时器
			}catch(e){
				alert("程序运行异常!");
			}
		}
	
		//设置提示框大小
		function resizeDiv()
		{
			/**这是设置自动关闭
			i+=1;
			if(i>500){
				closeDiv();
			}
			*/
			try{
				divHeight = parseInt(document.getElementById("divMsg").offsetHeight, 10); //设置div高度
				divWidth = parseInt(document.getElementById("divMsg").offsetWidth, 10); //设置div宽度
				docWidth = document.body.clientWidth; //获取窗体宽度
				docHeight = document.body.clientHeight; //设置窗体高度
				document.getElementById("divMsg").style.top = docHeight - divHeight + parseInt(document.body.scrollTop,10);//设置div的y坐标
				document.getElementById("divMsg").style.left = docWidth - divWidth + parseInt(document.body.scrollLeft,10)-15;//设置div的x坐标
			}catch(e){
			
			}
		}
	
		//移动提示框
		function moveDiv()
		{
			try{
				if(parseInt(document.getElementById("divMsg").style.top,10) <= (docHeight - divHeight + parseInt(document.body.scrollTop,10)))
				{
					window.clearInterval(objTimer);
					objTimer = window.setInterval("resizeDiv()",1); //调整div的位置和大小
				}
				divTop = parseInt(document.getElementById("divMsg").style.top,10);//获取y坐标
				document.getElementById("divMsg").style.top = divTop - divTop;//调整div的Y坐标
			}catch(e){
			
			}
		}
	
		//关闭提示框
		function closeDiv()
		{	
			document.getElementById("divMsg").style.visibility="hidden";//将短信息提示层隐藏
			if(objTimer){
				window.clearInterval(objTimer); //清除定时器
			}
			divTop = 0;
			divLeft = 0;
			divWidth = 0;
			divHeight = 0;
			docHeight = 0;
			docWidth = 0;
			objTimer = 0;
			i = 0;
		}
	</script>
</body>
</html>
