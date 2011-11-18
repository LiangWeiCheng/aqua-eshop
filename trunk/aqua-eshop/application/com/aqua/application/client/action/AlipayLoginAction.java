package com.aqua.application.client.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.aqua.alipay.config.login.AlipayConfig;
import com.aqua.alipay.util.login.AlipayNotify;
import com.aqua.alipay.util.login.AlipayService;
import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.QueryParameter;

@SuppressWarnings({"serial"})
public class AlipayLoginAction extends BaseAction {
	
	private String htmlText;
	private String loginResult;
	
	/**
	 * 功能：会员免注册登录接口的入口页面，生成请求URL
	 * 版本：3.1
	 * 日期：2010-10-26
	 * 说明：
	 *		如果您在接口集成过程中遇到问题，
	 *		您可以到商户服务中心(https://b.alipay.com/support/helperApply.htm?action=consultationApply)
	 *		提交申请集成协助,我们会有专业的技术工程师主动联系您协助解决
	 *		您也可以到支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）寻找相关解决方案
	 *  	如果不想使用扩展功能请把扩展功能参数赋空值
	 *  	要传递的参数要么不允许为空，要么就不要出现在数组与隐藏控件或URL链接里
	 */
	public String toLoginJsp(){
		String input_charset = AlipayConfig.input_charset;
		String sign_type = AlipayConfig.sign_type;
		String partner = AlipayConfig.partnerID;
		String key = AlipayConfig.key;
		String return_url = AlipayConfig.return_url;
		
		//选填参数
		String email = "492654954@qq.com"; //会员免注册登陆时，会员的支付宝账号
		
		//GET方式传递
		//String strUrl = AlipayService.CreateUrl(partner, return_url, email, input_charset, key, sign_type);
		//String sHtmlText = "<a href=" + strUrl + " target='_blank'><img border='0' src='images/alipaylog.gif' /></a>";

		//POST方式传递
		htmlText = AlipayService.BuildPostform(partner, return_url, email, input_charset, key, sign_type);
		
		returnPageURL = "/WEB-INF/jsp/application/client/payment/login/toLogin.jsp";
		return "dispatcher";
	}
	
	/**
	 * 功能：支付宝会员登录完成后跳转返回的页面（返回页）
	 * 版本：3.1
	 * 日期：2010-10-26
	 * 说明：
	 * 		该页面可在本机电脑测试
	 * 		该页面称作"返回页",是由支付宝服务器同步调用
	 * 		可放入HTML等美化页面的代码和订单交易完成后的数据库更新程序代码
	 * 建议：
	 * 		在商户网站会员数据库中增加一个字段：user_id(支付宝用户唯一ID)
	 * 		若返回的信息不止有参数user_id, 那么再增加支付宝会员信息的数据表
	 * 		会员信息的数据表中的唯一ID号是商户网站会员数据表中的
	 */
	@SuppressWarnings("unchecked")
	public String loginReturnUrl(){
		String key = AlipayConfig.key;
		//获取支付宝GET过来反馈信息
		Map params = new HashMap();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			try {
				//乱码解决,这段代码在出现乱码时使用,如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.put(name, valueStr);
		}
		
		//判断responsetTxt是否为ture,生成的签名结果mysign与获得的签名结果sign是否一致
		//1.responsetTxt的结果不是true,与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		//2.mysign与sign不等,与安全校验码、请求时的参数格式(如:带自定义参数等)、编码格式有关
		String mysign = AlipayNotify.GetMysign(params, key);
		String responseTxt = AlipayNotify.Verify(request.getParameter("notify_id"));
		String sign = request.getParameter("sign");
		
		//写日志记录(若要调试,请取消下面两行注释)
		//String sWord = "responseTxt=" + responseTxt + "\n return_url_log:sign=" + sign + "&mysign=" + mysign + "\n return回来的参数：" + AlipayBase.CreateLinkString(params);
		//AlipayBase.LogResult(sWord);

		if(mysign.equals(sign) && responseTxt.equals("true")){
		    //获取支付宝的通知返回参数
			String user_id = request.getParameter("user_id");//获取支付宝用户唯一ID号
			
			//以下是示例:
			//判断获取到的user_id的值是否在商户会员数据库中存在(即：是否曾经做过支付宝会员免注册登陆)
			//1.若不存在,则程序自动为会员快速注册一个会员，把信息插入商户网站会员数据表中
			//	且把该会员的在商户网站上的登录状态，更改成“已登录”状态。并记录在商家网站会员数据表中记录登陆信息，如登陆时间、次数、IP等。
			//2.若存在,判断该会员在商户网站上的登录状态是否是“已登录”状态
			//	若不是,则把该会员的在商户网站上的登录状态，更改成“已登录”状态。并记录在商家网站会员数据表中记录登陆信息，如登陆时间、次数、IP等。
			//	若是,则不做任何数据库业务逻辑处理。判定该次反馈信息为重复刷新返回链接导致。
			
			loginResult = "亲爱的商城会员:"+user_id+",您已经登录成功!";
		}else{
			loginResult = "登陆失败!";
		}
		returnPageURL = "/WEB-INF/jsp/application/client/payment/login/returnUrl.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.jxc.client.action.AlipayLoginAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		return null;
	}

	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

}
