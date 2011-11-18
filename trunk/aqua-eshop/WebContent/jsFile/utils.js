
//去除字符串两端空格
String.prototype.trim = function(){  
		    				return this.replace(/(^\s*)|(\s*$)/g, "");  
					   	};

//日期格式化:new Date().format('yyyy-MM-dd hh:mm:ss')
Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    };
    
    if(/(y+)/.test(format)){
    	format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    
    for(var k in o){
    	if(new RegExp("("+ k +")").test(format)){
    		format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    	}
    }
    
    return format;
};

/**
 * 全屏
 */
function key_F11(){
    var WsShell = new ActiveXObject("WScript.Shell");
    WsShell.SendKeys("{F11}");
}					   	
					   	
/**
 * 中文_字母_数字
 * @param {Object} str 待验证的字符串
 * @param {Object} message 提示时的说明
 * @param {Object} minLength 最小长度
 * @param {Object} maxLength 最大长度
 * @return {TypeName} 
 */
function utils_chinaLetterNumber(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength<minLength || objectLength>maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	var pattern = /^[0-9a-zA-Z\u4e00-\u9fa5]+$/i; 
	if (pattern.test(str)){ 
	  	return true; 
	}else{ 
		alert(message+",只能包含中文、字母、数字"); 
		return false; 
	} 
} 

/**
 * 字母_数字
 * @param {Object} str 待验证的字符串
 * @param {Object} message 提示时的说明
 * @param {Object} minLength 最小长度
 * @param {Object} maxLength 最大长度
 * @return {TypeName} 
 */
function utils_letterNumber(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength<minLength || objectLength>maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位");
		return false;
	}
	var letterNumber=/^[A-Za-z0-9]+$/;
	if(letterNumber.test(str)){
		return true;
	}else {
		alert(message+",只能包含字母、数字!");
		return false;
	}
}

/**
 * 验证邮箱
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_email(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength<minLength || objectLength>maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位");  
		return false;
	}
	var email = /\b[^\s\@]+\@(?:[a-z\d-]+\.)+(?:com|net|org|info|cn|jp|gov|aero)\b/;
	if(email.test(str)){
		return true;
	}else {
		alert(message+",格式不正确!");
		return false;
	}
	
}

/**
 * 匹配固定电话或小灵通，例如：031185907468或02185907468格式
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_tell(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength<minLength || objectLength>maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	//var partten = /^0(([1-9]\d)|([3-9]\d{2}))\d{8}$/;
	var partten = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	if(partten.test(str)){
		return true;
	}else{
		alert(message+",格式不正确!");
		return false;
	}
}

/**
 * 手机
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_phone(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength<minLength || objectLength>maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	var partten = /^1[3,5]\d{9}$/;
	if(partten.test(str)){
		return true;
	}else{
		alert(message+",格式不正确!");
		return false;
	}
}

/**
 * 邮编
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_postboy(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength<minLength || objectLength>maxLength){
		alert(message+",长度不正确,应该是6位"); 
		return false;
	}
	var partten = /^[a-zA-Z0-9 ]{3,12}$/;
	if(partten.test(str)){
		return true;
	}else{
		alert(message+",格式不正确!");
		return false;
	}
}

/**
 * 身份证15-18位
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_idCard(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength<minLength || objectLength>maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	var result=str.match(/\d{15}|\d{18}/);
    if(result==null){
    	alert(message+",格式不正确!");
    	return false;
    }else{
    	return true;
    }
}

/**
 * 整数
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_number(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength < minLength || objectLength > maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	var result = str.match(/^(-|\+)?\d+$/);
    if(result == null){
    	alert(message+",格式不正确!");
    	return false;
    }else{
    	return true;
    }
}

/**
 * QQ
 * @param {Object} str
 * @param {Object} message
 * @return {TypeName} 
 */
function utils_qq(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength < minLength || objectLength > maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	var result = str.match(/^(-|\+)?\d+$/);
    if(result == null){
    	alert(message+",格式不正确!");
    	return false;
    }else{
    	return true;
    }
}

/**
 * URL 网址
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_url(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength < minLength || objectLength > maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	var result = str.match(/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/);
    if(result == null){
    	alert(message+",格式不正确!");
    	return false;
    }else{
    	return true;
    }
}

/**
 * 身高
 * @param {Object} str
 * @param {Object} message
 * @return {TypeName} 
 */
function utils_stature(str, message){
	var result = str.match(/^(-|\+)?\d+$/);
    if(result == null){
    	alert(message+",格式不正确!");
    	return false;
    }else{
    	if(parseInt(str) < 25 || parseInt(str) > 250){
			alert(message+",应该在"+25+"-"+250+"cm之间"); 
			return false;
		}
    	return true;
    }
}


/**
 * 体重
 * @param {Object} str
 * @param {Object} message
 * @return {TypeName} 
 */
function utils_avoirdupoi(str, message){
	var result = str.match(/^(-|\+)?\d+$/);
    if(result == null){
    	alert(message+",格式不正确!");
    	return false;
    }else{
    	if(parseInt(str) < 2 || parseInt(str) > 500){
			alert(message+",应该在"+2+"-"+500+"kg之间"); 
			return false;
		}
    	return true;
    }
}

/**
 * 对话窗口
 * @param {Object} url 
 * @param {Object} callback
 * @param {Object} parameter
 */
function showModalDialogs(url, callback, parameter) {
	var windowReturn = window.showModalDialog(url, window, "dialogHeight:600px; dialogWidth:959px; help:no; center:yes; maximize:no; minimize:no; scroll:no; status:no; resizable:no");
	if (windowReturn != null) {
		callback(windowReturn, parameter);
	}
}

/**
 * 对话窗口：自定义窗口属性
 * @param {Object} url
 * @param {Object} callback
 * @param {Object} parameter
 * @param {Object} dialogParamater
 */
function showModalDialogsMySize(url, callback, parameter, dialogParamater) {
	var windowReturn = window.showModalDialog(url,"", dialogParamater);
	if (windowReturn != null) {
		callback(windowReturn, parameter);
	}
}

/**
 * 商品单价
 * @param {Object} str
 * @param {Object} message
 * @param {Object} minLength
 * @param {Object} maxLength
 * @return {TypeName} 
 */
function utils_price(str, message, minLength, maxLength){
	var objectLength = str.length;
	if(objectLength < minLength || objectLength > maxLength){
		alert(message+",长度不正确,应该在"+minLength+"-"+maxLength+"位"); 
		return false;
	}
	var partten = /^\d{1,10}$|^\d{1,10}\.\d{1,2}\w?$/;
    if(partten.test(str)){
    	return true;
    }else{
    	alert(message+",格式不正确!");
    	return false;
    }
}

/**
 * 获取昨天日期
 */
function utils_getYesterday(){
	//获取系统时间 
	var LSTR_ndate = new Date(); 
	var LSTR_Year = LSTR_ndate.getYear(); 
	var LSTR_Month = LSTR_ndate.getMonth(); 
	var LSTR_Date = LSTR_ndate.getDate(); 
	//处理 
	var uom = new Date(LSTR_Year,LSTR_Month,LSTR_Date); 
	uom.setDate(uom.getDate()-1);//取得系统时间的前一天,重点在这里,负数是前几天,正数是后几天 
	var LINT_MM = uom.getMonth(); 
	LINT_MM++; 
	var LSTR_MM = LINT_MM > 10?LINT_MM:("0"+LINT_MM); 
	var LINT_DD = uom.getDate(); 
	var LSTR_DD = LINT_DD > 10?LINT_DD:("0"+LINT_DD); 
	//得到最终结果 
	uom = uom.getFullYear() + "-" + LSTR_MM + "-" + LSTR_DD; 
	return uom;
}

/**
 * 写cookies
 * @param {Object} name
 * @param {Object} value
 * @param {Object} day	保存天数
 */
function utils_setCookie(name, value, day){
    var exp  = new Date();
    exp.setTime(exp.getTime() + day*24*60*60*1000);
    document.cookie = name + "="+ escape(value) + ";expires=" + exp.toGMTString();
}

/**
 * 取cookies
 * @param {Object} name
 * @return {TypeName} 
 */
function utils_getCookie(name){
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null) {
		return unescape(arr[2]); 
	}
	return null;
}

/**
 * 删除cookie
 * @param {Object} name
 */
function utils_delCookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = utils_getCookie(name);
    if(cval != null) {
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
}

/**
 * 是否支持Cookie
 * @returns {Boolean}
 */
function CookieEnable(){
    var result=false;
    if(navigator.cookiesEnabled){
    	return true;
    }
    document.cookie = "testcookie=yes;";
    var cookieSet = document.cookie;
    if (cookieSet.indexOf("testcookie=yes") > -1){
    	result=true;
    }
    document.cookie = "";
    return result;
}
 

