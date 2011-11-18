<%@page language="java" isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>frame</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsFile/dwr/engine.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsFile/dwr/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jsFile/dwr/interface/messageManager.js"></script>
<script type="text/javascript">
	
	window.onload = init;
	window.onunload = destory;
	
	function init() {
		dwr.engine.setActiveReverseAjax(true);	//激活反转
		messageManager.init("${sessionScope.userObject.userId}", "${sessionScope.userObject.userName}");//登录
	}
	function destory(){
		messageManager.destory("${sessionScope.userObject.userId}");
	}
	
	//1.即时消息:发送者ID,发送者名称,接收者ID,消息内容
	function instantSend(receiverId, sendContent){
		messageManager.instantSend("${sessionScope.userObject.userId}", "${sessionScope.userObject.userName}", receiverId, sendContent);
	}
	
	//发送消息:机构ID,发送者名称,接收者ID,消息内容
	function sendMessageORG(orgId, sendContent){
		messageManager.sendORG("${sessionScope.userObject.userId}", "${sessionScope.userObject.userName}", orgId, sendContent);
	}
	
	function showInstantSend(senderUserName, message){//接收消息,进行提示,消息类型:messageTypeOne
		if(senderUserName=="${sessionScope.userObject.userName}"){//处理刚登陆时未处理任务的提醒
			document.getElementById("sendMessageTypeId").innerHTML = "您有未处理的消息,请注意查收!";
		}else{
			document.getElementById("sendMessageTypeId").innerHTML = "<font color='#ff0000'>"+senderUserName+"</font>给您发了一条新消息,请注意查收!";
		}
		viewMsg(); //显示提示框
	}
	function clickMethodOne(){//点击查看触发
		window.frames["bodyFrame"].location.href="${pageContext.request.contextPath}/com.bos.mp.workflow.email.MpEmailMaintain.flow";
		closeDiv();
	}
	
	//2.定时消息:接收者ID
	function timeSend(receiverId){
		messageManager.timeSend(receiverId);
	}
	function showTimeSend(messageContent){//接收消息,进行提示,消息类:messageTypeTwo
		document.getElementById("timeSendId").innerHTML = messageContent;
		viewMsg2(); //显示提示框
	}
	function clickMethodTwo(){//点击查看触发
		//window.frames["iframeMainName"].frames["bodyFrame"].location.href="${pageContext.request.contextPath}/com.bos.mp.workflow.email.MpEmailMaintain.flow";
		//closeDiv2();
	}

	//3.下面开始处理DIV显示
	
	//根据窗体高度和宽度，改变短消息提示框的高度和宽度
	window.onresize = function(){
		resizeDiv(); 
		resizeDiv2();
	};
	//出现错误时，不做任何处理
	window.onerror = function(){} 

	var divTop, divLeft, divWidth, divHeight, docHeight, docWidth, objTimer, i = 0;//关于位置的相关变量
	//显示提示框
	function viewMsg()
	{
		try{
			divTop = parseInt(document.getElementById("divMsg").style.top,10); //div的x坐标
			divLeft = parseInt(document.getElementById("divMsg").style.left,10); //div的y坐标
			divHeight = parseInt(document.getElementById("divMsg").offsetHeight,10);//div的高度
			divWidth = parseInt(document.getElementById("divMsg").offsetWidth,10); //div的宽度
			docWidth = document.body.clientWidth; //窗体宽度
			docHeight = document.body.clientHeight; //窗体高度
			document.getElementById("divMsg").style.top = parseInt(document.body.scrollTop,10) + docHeight + 10;//设置div的Y坐标
			document.getElementById("divMsg").style.left = parseInt(document.body.scrollLeft,10) + docWidth - divWidth-15;//设置div的X坐标
			document.getElementById("divMsg").style.visibility = "visible"; //设置div显示
			objTimer = window.setInterval("moveDiv()", 1) //设置定时器
		}catch(e){
		
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
			divHeight = parseInt(document.getElementById("divMsg").offsetHeight,10); //设置div高度
			divWidth = parseInt(document.getElementById("divMsg").offsetWidth,10); //设置div宽度
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
	
	
	var divTop2, divLeft2, divWidth2, divHeight2, docHeight2, docWidth2, objTimer2, i2 = 0;//关于位置的相关变量
	//显示提示框
	function viewMsg2()
	{
		try{
			divTop2 = parseInt(document.getElementById("divMsg2").style.top,10); //div的x坐标
			divLeft2 = parseInt(document.getElementById("divMsg2").style.left,10); //div的y坐标
			divHeight2 = parseInt(document.getElementById("divMsg2").offsetHeight,10);//div的高度
			divWidth2 = parseInt(document.getElementById("divMsg2").offsetWidth,10); //div的宽度
			docWidth2 = document.body.clientWidth; //窗体宽度
			docHeight2 = document.body.clientHeight; //窗体高度
			document.getElementById("divMsg2").style.top = parseInt(document.body.scrollTop,10) + docHeight2 + 10;//设置div的Y坐标
			document.getElementById("divMsg2").style.left = parseInt(document.body.scrollLeft,10) + docWidth2 - divWidth2-200;//设置div的X坐标
			document.getElementById("divMsg2").style.visibility = "visible"; //设置div显示
			objTimer2 = window.setInterval("moveDiv2()", 1) //设置定时器
		}catch(e){
		
		}
	}
	//设置提示框大小
	function resizeDiv2()
	{
		try{
			divHeight2 = parseInt(document.getElementById("divMsg2").offsetHeight,10); //设置div高度
			divWidth2 = parseInt(document.getElementById("divMsg2").offsetWidth,10); //设置div宽度
			docWidth2 = document.body.clientWidth; //获取窗体宽度
			docHeight2 = document.body.clientHeight; //设置窗体高度
			document.getElementById("divMsg2").style.top = docHeight2 - divHeight2 + parseInt(document.body.scrollTop,10);//设置div的y坐标
			document.getElementById("divMsg2").style.left = docWidth2 - divWidth2 + parseInt(document.body.scrollLeft,10)-200;//设置div的x坐标
		}catch(e){
		
		}
	}
	//移动提示框
	function moveDiv2()
	{
		try{
			if(parseInt(document.getElementById("divMsg2").style.top,10) <= (docHeight2 - divHeight2 + parseInt(document.body.scrollTop,10)))
			{
				window.clearInterval(objTimer2);
				objTimer2 = window.setInterval("resizeDiv2()",1); //调整div的位置和大小
			}
			divTop2 = parseInt(document.getElementById("divMsg").style.top,10);//获取y坐标
			document.getElementById("divMsg2").style.top = divTop2 - divTop2;//调整div的Y坐标
		}catch(e){
		
		}
	}
	//关闭提示框
	function closeDiv2()
	{	
		document.getElementById("divMsg2").style.visibility="hidden";//将短信息提示层隐藏
		if(objTimer2){
			window.clearInterval(objTimer2); //清除定时器
		}
		divTop2 = 0;
		divLeft2 = 0;
		divWidth2 = 0;
		divHeight2 = 0;
		docHeight2 = 0;
		docWidth2 = 0;
		objTimer2 = 0;
		i2 = 0;
	}
	
</script>
</head>
<body style="margin:0px;overflow-y:hidden;">

	<table height="100%" width="100%" cellspacing="0" style="border: 0px; border-bottom-width: 0px; border-left-width: 0px; border-right-width: 0px;border-top-width: 0px;">
		<tr>
			<td colspan="3" height="50px">
				<iframe src="" scrolling="NO" frameborder="0px;" name="topFrame" height="50px" width="100%"></iframe>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="20px">
				<iframe src="" scrolling="NO" name="toolbarFrame" frameborder="0px;" noresize height="20px" width="100%"></iframe>
			</td>
		</tr>
		<tr>
			<td id="hiddenTdId" height="100%" width="200px">
				<iframe id="hiddenIframeId" src='org.gocom.abframe.auth.Frame.flow' name="menuFrame" frameborder="0px;" height="100%" width="200px"></iframe>
			</td>
			<td height="100%" width="10px">
				<iframe src="" name="slideFrame" noresize="noresize" frameborder="0px;" scrolling="no" height="100%" width="10px"></iframe>
			</td>
			<td height="100%" width="100%">
				<iframe src="" frameborder="0px;" name="bodyFrame" height="100%" width="100%"></iframe>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="30px">
				<iframe src="" scrolling="no" name="copyrightFrame" frameborder="0px;" height="28px" width="100%"></iframe>
			</td>
		</tr>
	</table>
	
	<div id="divmsg" style="border-right: #455690 1px solid; border-top: #a6b4cf 1px solid; z-index:99999; left: 0px; visibility: hidden; border-left: #a6b4cf 1px solid; width: 150px; border-bottom: #455690 1px solid; position: absolute; top: 0px; height: 116px; background-color: #c9d3f3">
		<table style="border-top: #ffffff 1px solid; border-left: #ffffff 1px solid" cellspacing="0" cellpadding="0" width="100%" bgcolor="#cfdef4" border="0">
			<tbody>
				<tr>
					<td style="font-size: 12px;color: #0f2c8c" width="30" height="24"></td>
					<td style="font-weight: normal; font-size: 12px;color: #1f336b; padding-top: 4px;padding-left: 4px" valign=center width="100%">消息提示:</td>
					<td style="padding-top: 2px;padding-right:2px" valign="center" align="right" width="19"><span title="关闭" style="cursor: hand;color:red;font-size:12px;font-weight:bold;margin-right:4px;" onclick="closeDiv()" >×</span></td>
				</tr>
				<tr>
					<td style="padding-right: 1px;padding-bottom: 1px" colspan="3" height="90">
						<div id="showDivId" style="border-right: #b9c9ef 1px solid; padding-right: 2px; border-top: #728eb8 1px solid; padding-left: 2px; font-size: 12px; padding-bottom: 2px; border-left: #728eb8 1px solid; width: 100%; color: #1f336b; padding-top: 5px; border-bottom: #b9c9ef 1px solid; height: 100%">
							<font id="sendMessageTypeId">
								<font id="sendUserNameId" color="#ff0000"></font>给您发了一条新消息,请注意查收!
							</font>
							<br/><br/>
							<div align="center" style="word-break:break-all">
								<a id="hrefId" onclick="clickMethodOne()" title="点击此处查看">
									<font id="fontId" color=#ff0000>点击查看</font>
								</a>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="divmsg2" style="border-right: #455690 1px solid; border-top: #a6b4cf 1px solid; z-index:99998; left: 0px; visibility: hidden; border-left: #a6b4cf 1px solid; width: 150px; border-bottom: #455690 1px solid; position: absolute; top: 0px; height: 116px; background-color: #c9d3f3">
		<table style="border-top: #ffffff 1px solid; border-left: #ffffff 1px solid" cellspacing="0" cellpadding="0" width="100%" bgcolor="#cfdef4" border="0">
			<tbody>
				<tr>
					<td style="font-size: 12px;color: #0f2c8c" width="30" height="24"></td>
					<td style="font-weight: normal; font-size: 12px;color: #1f336b; padding-top: 4px;padding-left: 4px" valign=center width="100%">定时提醒:</td>
					<td style="padding-top: 2px;padding-right:2px" valign="center" align="right" width="19"><span title="关闭" style="cursor: hand;color:red;font-size:12px;font-weight:bold;margin-right:4px;" onclick="closeDiv2()" >×</span></td>
				</tr>
				<tr>
					<td style="padding-right: 1px;padding-bottom: 1px" colspan="3" height="90">
						<div id="showDivId" style="border-right: #b9c9ef 1px solid; padding-right: 13px; border-top: #728eb8 1px solid; padding-left: 13px; font-size: 12px; padding-bottom: 13px; border-left: #728eb8 1px solid; width: 100%; color: #1f336b; padding-top: 18px; border-bottom: #b9c9ef 1px solid; height: 100%">
							<font id="timeSendId"></font>
							<!--  
							<br><br>
							<div align="center" style="word-break:break-all">
								<a id="hrefId" onclick="clickMethodTwo()">
									<font id="fontId" color=#ff0000>点击查看</font>
								</a>
							</div>
							-->
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</body>
</html>
