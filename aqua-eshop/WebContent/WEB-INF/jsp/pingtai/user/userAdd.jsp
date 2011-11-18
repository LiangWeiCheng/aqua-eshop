<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bu" uri="http://www.4bu4.com"%>

<!-- userAdd.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>添加用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<s:head/>
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/pingTai/pingTai.css">
	<script src="${pageContext.request.contextPath }/jsFile/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript"></script>
	<script src="${pageContext.request.contextPath }/jsFile/utils.js" type="text/javascript" language="javascript"></script>
	<script type="text/javascript">
	
		//部门对话框
		function showModelDepartment(){
			var url = "${pageContext.request.contextPath}/pingTai/departmentPingTaiAction!departmentListDialog.action";
			showModalDialogs(url, callbackFunDepartment, "");
		}
		
		//部门回调
		function callbackFunDepartment(rec, statusIndex) {
	    	var id = rec.id;
	    	var name = rec.name;
	    	document.getElementById("departmentIdsId").value = id;
	    	document.getElementById("departmentNamesId").value = name;
     	}
     	
		<%-- --%>
		function dataVali(){
			var userNameId = document.getElementById("userNameId").value;
			var userNameIdResult = utils_letterNumber(userNameId, "登录名", 6, 16);
			if(!userNameIdResult){
				return false;
			}
			
			var namesId = document.getElementById("namesId").value;
			var namesIdResult = utils_chinaLetterNumber(namesId, "姓名", 2, 10);
			if(!namesIdResult){
				return false;
			}
			
			var pass1Id = document.getElementById("pass1Id").value;
			var pass2Id = document.getElementById("pass2Id").value;
			if(pass1Id == pass2Id && pass1Id!=""){
				var result = utils_letterNumber(pass1Id, "密码", 6, 18);
				if(!result){
					return false;
				}
			}else{
				alert("密码不一致或为空");
			}
			
			var emailId = document.getElementById("emailId").value;
			var emailIdResult = utils_email(emailId, "邮箱", 6, 30);
			if(!emailIdResult){
				return false;
			}
			
			var mobilId = document.getElementById("mobilId").value;
			var mobilIdResult = utils_phone(mobilId, "手机号码", 11, 12);
			if(!mobilIdResult){
				return false;
			}
			
			var telephoneId = document.getElementById("telephoneId").value;
			var telephoneIdResult = utils_tell(telephoneId, "电话号码", 10, 20);
			if(!telephoneIdResult){
				return false;
			}
			
			var postboyId = document.getElementById("postboyId").value;
			var postboyIdResult = utils_postboy(postboyId, "邮编", 6, 6);
			if(!postboyIdResult){
				return false;
			}
			
			var idCardId = document.getElementById("idCardId").value;
			var idCardIdResult = utils_idCard(idCardId, "身份证", 15, 18);
			if(!idCardIdResult){
				return false;
			}
			
			var qqId = document.getElementById("qqId").value;
			var qqIdResult = utils_qq(qqId, "QQ", 5, 15);
			if(!qqIdResult){
				return false;
			}
			
			/*
			var homepageId = document.getElementById("homepageId").value;
			var homepageIdResult = utils_url(homepageId, "个人主页", 15, 100);
			if(!homepageIdResult){
				return false;
			}*/
			
			var statureId = document.getElementById("statureId").value;
			var statureIdResult = utils_stature(statureId, "身高");
			if(!statureIdResult){
				return false;
			}
			
			var avoirdupoiId = document.getElementById("avoirdupoiId").value;
			var avoirdupoiIdResult = utils_avoirdupoi(avoirdupoiId, "体重");
			if(!avoirdupoiIdResult){
				return false;
			}
			
			var nativityAddressId = document.getElementById("nativityAddressId").value;
			var nativityAddressIdResult = utils_chinaLetterNumber(nativityAddressId, "出生地", 0, 20);
			if(!nativityAddressIdResult){
				return false;
			}
			
			var householderId = document.getElementById("householderId").value;
			var householderIdResult = utils_chinaLetterNumber(householderId, "户口所在地", 0, 20);
			if(!householderIdResult){
				return false;
			}
			
			var schoolNameId = document.getElementById("schoolNameId").value;
			var schoolNameIdResult = utils_chinaLetterNumber(schoolNameId, "毕业院校", 0, 20);
			if(!schoolNameIdResult){
				return false;
			}
			
			var specialityId = document.getElementById("specialityId").value;
			var specialityIdResult = utils_chinaLetterNumber(specialityId, "专业", 0, 20);
			if(!specialityIdResult){
				return false;
			}
			
			var addresId = document.getElementById("addresId").value;
			var addresIdResult = utils_chinaLetterNumber(addresId, "地址", 0, 250);
			if(!addresIdResult){
				return false;
			}
			
			var descriptionId = document.getElementById("descriptionId").value;
			var descriptionIdResult = utils_chinaLetterNumber(descriptionId, "其它说明", 0, 200);
			if(!descriptionIdResult){
				return false;
			}
			
			document.userUpdateForm.submit();
		}
		
	</script>
</head>
<body>
	
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		    <td height="3"></td>
	  	</tr>    
	  	<tr height="20" valign="baseline">
		    <td bgcolor="#353c44">
		    	<img src="${pageContext.request.contextPath}/images/pingTai/tb.gif" width="14" height="14" />
		    	<span class="STYLE1">添加用户</span>
		    </td>
		    <td align="right" bgcolor="#353c44">
		    	<span class="STYLE1">
              	</span>
		    </td>
	  	</tr>
	  	<tr>
		    <td height="3" colspan="2"></td>
	  	</tr>
	</table>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
		<s:form id="userUpdateForm" name="userUpdateForm" action="userPingTaiAction!userAdd.action" namespace="/pingTai" method="POST">
		<s:token name="formToken"></s:token>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">所属部门:</td>
			<td>
				<s:hidden id="departmentIdsId" name="user.department.ids" value=""></s:hidden>
				<s:textfield id="departmentNamesId" name="user.department.names" size="30" maxlength="" readonly="true"></s:textfield>
				<input type="button" value="浏览" onclick="showModelDepartment()" class="button2"/>
			</td>
			<td align="right" width="15%">数据操作级别:</td>
			<td width="35%">
				<bu:dictSelect nodeId="userOperatorId" nodeClass="" nodeStyle="" selectName="user.userOperatorDataLevel" dictTypeNumbers="userOperatorDataLevel " dictNumbers="userOperatorDataLevel_yongHuJi"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right" width="15%">真实姓名:</td>
			<td width="35%" colspan="3">
				<s:textfield id="namesId" name="user.userInfo.names" cssStyle="width:100%" maxlength="10"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">用户类型:</td>
			<td>
				<bu:dictSelect nodeId="userClassId" nodeClass="" nodeStyle="" selectName="user.userClass" dictTypeNumbers="userClass" dictNumbers="userClass_houTai"/>
			</td>
			<td align="right" width="15%">登录名:</td>
			<td width="35%">
				<s:textfield id="userNameId" name="user.userName" cssStyle="width:100%" maxlength="16"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">输入密码:</td>
			<td>
				<s:password id="pass1Id" name="user.passWord" cssStyle="width:100%" maxlength="18"></s:password>
			</td>
			<td align="right">确认密码:</td>
			<td>
				<s:password id="pass2Id" name="passWord" cssStyle="width:100%" maxlength="18"></s:password>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">性别:</td>
			<td>
				<bu:dictSelect nodeId="sexId" nodeClass="" nodeStyle="" selectName="user.userInfo.sex" dictTypeNumbers="sex" dictNumbers="man"/>
			</td>
			<td align="right">邮箱:</td>
			<td>
				<s:textfield id="emailId" name="user.userInfo.email" cssStyle="width:100%" maxlength="30"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">手机号:</td>
			<td>
				<s:textfield id="mobilId" name="user.userInfo.mobile" cssStyle="width:100%" maxlength="15"></s:textfield>
			</td>
			<td align="right">电话:</td>
			<td>
				<s:textfield id="telephoneId" name="user.userInfo.telephone" cssStyle="width:100%" maxlength="20"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">邮编:</td>
			<td>
				<s:textfield id="postboyId" name="user.userInfo.postboy" cssStyle="width:100%" maxlength="6"></s:textfield>
			</td>
			<td align="right">生日:</td>
			<td>
				<s:textfield id="birthdayId" name="user.userInfo.birthday" cssStyle="width:100%" maxlength="" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">身份证:</td>
			<td>
				<s:textfield id="idCardId" name="user.userInfo.idCard" cssStyle="width:100%" maxlength="20"></s:textfield>
			</td>
			<td align="right">QQ号码:</td>
			<td>
				<s:textfield id="qqId" name="user.userInfo.qq" cssStyle="width:100%" maxlength="20"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">民族:</td>
			<td>
				<bu:dictSelect nodeId="folkId" nodeClass="" nodeStyle="" selectName="user.userInfo.folk" dictTypeNumbers="folk" dictNumbers="folk_han"/>
			</td>
			<td align="right">婚姻状况:</td>
			<td>
				<bu:dictSelect nodeId="marriageId" nodeClass="" nodeStyle="" selectName="user.userInfo.marriage" dictTypeNumbers="marriage" dictNumbers="marriage_no"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">政治面貌:</td>
			<td>
				<bu:dictSelect nodeId="governmentId" nodeClass="" nodeStyle="" selectName="user.userInfo.government" dictTypeNumbers="government" dictNumbers="government_qunZhong"/>
			</td>
			<td align="right">身高(cm):</td>
			<td>
				<s:textfield id="statureId" name="user.userInfo.stature" cssStyle="width:100%" maxlength="3"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">体重(kg):</td>
			<td>
				<s:textfield id="avoirdupoiId" name="user.userInfo.avoirdupois" cssStyle="width:100%" maxlength="3"></s:textfield>
			</td>
			<td align="right">血型:</td>
			<td>
				<bu:dictSelect nodeId="bloodGroupId" nodeClass="" nodeStyle="" selectName="user.userInfo.bloodGroup" dictTypeNumbers="bloodGroup" dictNumbers="bloodGroup_A"/>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">出生地:</td>
			<td>
				<s:textfield id="nativityAddressId" name="user.userInfo.nativityAddress" cssStyle="width:100%" maxlength="20"></s:textfield>
			</td>
			<td align="right">户口所在地:</td>
			<td>
				<s:textfield id="householderId" name="user.userInfo.householder" cssStyle="width:100%" maxlength="20"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">文化程度:</td>
			<td>
				<bu:dictSelect nodeId="cultureId" nodeClass="" nodeStyle="" selectName="user.userInfo.culture" dictTypeNumbers="wenHuaChenDu" dictNumbers="wenHuaChenDu_dazhaun"/>
			</td>
			<td align="right">毕业院校:</td>
			<td>
				<s:textfield id="schoolNameId" name="user.userInfo.schoolName" cssStyle="width:100%" maxlength="20"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">专业:</td>
			<td>
				<s:textfield id="specialityId" name="user.userInfo.speciality" cssStyle="width:100%" maxlength="20"></s:textfield>
			</td>
			<td align="right">毕业时间:</td>
			<td>
				<s:textfield id="finishSchoolDateId" name="user.userInfo.finishSchoolDate" cssStyle="width:100%" maxlength="" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"></s:textfield>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">地址:</td>
			<td colspan="3">
				<s:textarea id="addresId" name="user.userInfo.address" cssStyle="width:100%;height:40px;"></s:textarea><!-- maxlength="250 -->
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td align="right">其它说明:</td>
			<td colspan="3">
				<s:textarea id="descriptionId" name="user.userInfo.description" cssStyle="width:100%;height:40px;"></s:textarea><!-- maxlength="200 -->
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" class="STYLE19">
			<td colspan="4" align="center">
				<input type="button" value="保存" onclick="dataVali();" class="button2"/>&nbsp;&nbsp;
				<input type="button" value="返回" onclick="javascript:history.go(-1);" class="button2"/>
			</td>
		</tr>
		</s:form>
	</table>
	
	
</body>
</html>
