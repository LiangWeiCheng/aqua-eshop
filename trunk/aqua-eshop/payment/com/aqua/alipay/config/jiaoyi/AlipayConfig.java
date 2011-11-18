/* *
 *功能：设置帐户有关信息及返回路径（基础配置页面）
 *版本：3.0
 *日期：2010-06-18
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.访问支付宝首页(www.alipay.com)，然后用您的签约支付宝账号登陆.
 *2.点击导航栏中的“商家服务”，即可查看
	
 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 * */
package com.aqua.alipay.config.jiaoyi;

import com.aqua.pingtai.common.Context;

public class AlipayConfig {
	
	//partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
	public static String partnerID = "2088002267031188"; //合作身份者ID
	
	public static String key = "7odizwjgfsbe00l22x7dbxczrvfz729n"; //安全检验码
	
	public static String sellerEmail = "dongcb678@163.com"; //签约支付宝账号或卖家收款支付宝帐户
	
	// notify_url 交易过程中服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	public static String notify_url = Context.getContextAllPath()+"/applicationClient/alipayPaymentApplicationClientAction!alipayNotifyUrl.action";
	
	// 付完款后跳转的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	public static String return_url = Context.getContextAllPath()+"/applicationClient/alipayPaymentApplicationClientAction!alipayReturnUrl.action";
	
	// 网站商品的展示地址，不允许加?id=123这类自定义参数
	public static String show_url = Context.getContextAllPath()+"/applicationClient/alipayPaymentApplicationClientAction!alipayShowUrl.action"; 

	public static String CharSet = "UTF-8";//页面编码
	
	public static String sign_type = "MD5";//加密方式 不需修改
	//访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
	public static String transport = "http";

}
