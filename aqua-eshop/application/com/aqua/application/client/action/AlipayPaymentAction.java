package com.aqua.application.client.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.aqua.alipay.config.jiaoyi.AlipayConfig;
import com.aqua.alipay.util.jiaoyi.AlipayNotify;
import com.aqua.alipay.util.jiaoyi.AlipayService;
import com.aqua.alipay.util.jiaoyi.UtilDate;
import com.aqua.application.entity.AlipayReturnParamater;
import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.QueryParameter;

@SuppressWarnings({"serial"})
public class AlipayPaymentAction extends BaseAction {
	
	private String htmlText;
	private String orderState;
	private String verifyStatus;
	private AlipayReturnParamater alipayReturnParamater;
	
	/**
	 * 功能：设置商品有关信息(入口页),构造订单信息
	 * 详细：该页面是接口入口页面,生成支付时的URL
	 * 版本：3.0
	 * 日期：2010-07-16
	 * 说明：该页面测试时出现“调试错误”请参考：http://club.alipay.com/read-htm-tid-8681712.html
	 */
	@SuppressWarnings("static-access")
	public String toAlipayPayment(){
		String input_charset = AlipayConfig.CharSet;
		String sign_type = AlipayConfig.sign_type;
		String seller_email = AlipayConfig.sellerEmail;
		String partner = AlipayConfig.partnerID;
		String key = AlipayConfig.key;
		String show_url = AlipayConfig.show_url;
		String notify_url = AlipayConfig.notify_url;
		String return_url = AlipayConfig.return_url;
	
		//以下参数是需要通过下单时的订单数据传入进来获得
		//必填参数
		UtilDate date = new UtilDate();//调取支付宝工具类生成订单号
        String out_trade_no = date.getOrderNum();	//请与贵网站订单系统中的唯一订单号匹配
        String subject = "订单名称";					//订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
        String body = "订单描述备注";				//订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
        String price = "0.01";						//订单总金额，显示在支付宝收银台里的“商品单价”里

        String logistics_fee = "0.00";				//物流费用，即运费。
        String logistics_type = "EXPRESS";			//物流类型，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
        String logistics_payment = "SELLER_PAY";	//物流支付方式，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）

        String quantity = "2";						//商品数量，建议默认为1，不改变值，把一次交易看成是一次下订单而非购买一件商品。

        //扩展参数——买家收货信息（推荐作为必填）
        //该功能作用在于买家已经在商户网站的下单流程中填过一次收货信息，而不需要买家在支付宝的付款流程中再次填写收货信息。
        //若要使用该功能，请至少保证receive_name、receive_address有值
        String receive_name	= "收货人姓名";			//收货人姓名，如：张三
        String receive_address = "收货人地址";		//收货人地址，如：XX省XXX市XXX区XXX路XXX小区XXX栋XXX单元XXX号
        String receive_zip = "收货人邮编";			//收货人邮编，如：123456
        String receive_phone = "收货人电话号码";		//收货人电话号码，如：0571-81234567
        String receive_mobile = "收货人手机号码";		//收货人手机号码，如：13312341234

        //扩展参数——第二组物流方式
        //物流方式是三个为一组成组出现。若要使用，三个参数都需要填上数据；若不使用，三个参数都需要为空
        //有了第一组物流方式，才能有第二组物流方式，且不能与第一个物流方式中的物流类型相同，
        //即logistics_type="EXPRESS"，那么logistics_type_1就必须在剩下的两个值（POST、EMS）中选择
        String logistics_fee_1 = "";				//物流费用，即运费。
        String logistics_type_1	= "";				//物流类型，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
        String logistics_payment_1 = "";			//物流支付方式，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）

        //扩展参数——第三组物流方式
        //物流方式是三个为一组成组出现。若要使用，三个参数都需要填上数据；若不使用，三个参数都需要为空
        //有了第一组物流方式和第二组物流方式，才能有第三组物流方式，且不能与第一组物流方式和第二组物流方式中的物流类型相同，
        //即logistics_type="EXPRESS"、logistics_type_1="EMS"，那么logistics_type_2就只能选择"POST"
        String logistics_fee_2 = "";				//物流费用，即运费。
        String logistics_type_2	= "";				//物流类型，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
        String logistics_payment_2 = "";			//物流支付方式，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）

        //扩展功能参数——其他
        String buyer_email = "";					//默认买家支付宝账号
        String discount = "-0.01";					//折扣，是具体的金额，而不是百分比。若要使用打折，请使用负数，并保证小数点最多两位数

		//GET方式传递
		//String strUrl = AlipayService.CreateUrl(partner,seller_email,return_url,notify_url,show_url,out_trade_no,
		//subject,body,price,logistics_fee,logistics_type,logistics_payment,quantity,receive_name,receive_address,
		//receive_zip,receive_phone,receive_mobile,logistics_fee_1,logistics_type_1,logistics_payment_1,
		//logistics_fee_2,logistics_type_2,logistics_payment_2,buyer_email,discount,input_charset,key,sign_type);
		//String sHtmlText = "<a href=" + strUrl + " target='_blank'><img border='0' src='img/alipay.gif' /></a>";
		
		//POST方式传递，得到加密结果字符串。想POST方式传递，请把下面四行代码取消注释。
        htmlText = AlipayService.BuildPostform(partner,seller_email,return_url,notify_url,show_url,out_trade_no,
							subject,body,price,logistics_fee,logistics_type,logistics_payment,quantity,receive_name,receive_address,
							receive_zip,receive_phone,receive_mobile,logistics_fee_1,logistics_type_1,logistics_payment_1,
							logistics_fee_2,logistics_type_2,logistics_payment_2,buyer_email,discount,input_charset,key,sign_type);
        
		returnPageURL = "/WEB-INF/jsp/application/client/payment/pay/orderformDetail.jsp";
		return "dispatcher";
	}
	
	/**
	 * 功能：付完款后跳转的页面(返回页),输出字符串给支付宝确认结果,做补单处理
	 * 版本：3.0
	 * 日期：2010-07-20
	 * 说明：
	 * 		1.该页面不能在本机电脑测试,请到服务器上做测试.请确保外部可以访问该页面。
	 * 		2.创建该页面文件时,请留心该页面文件中无任何HTML代码及空格。
	 * 		3.该页面调试工具请使用写文本函数log_result,该函数已被默认开启
	 * 		该通知页面主要功能是 ：对于返回页面(return_url.jsp)做补单处理,
	 * 		如果没有收到该页面返回的 success信息,支付宝会在24小时内按一定的时间策略重发通知
	 * 		交易过程中服务器通知的页面 要用 http://格式的完整路径,不允许加?id=123这类自定义参数	 
	 */
	@SuppressWarnings("unchecked")
	public String alipayNotifyUrl() throws UnsupportedEncodingException{
		String key = AlipayConfig.key;
		//获取支付宝POST过来反馈信息
		Map params = new HashMap();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用.如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			params.put(name, valueStr);
		}
		
		//判断responsetTxt是否为ture,生成的签名结果mysign与获得的签名结果sign是否一致
		//responsetTxt的结果不是true,与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		//mysign与sign不等，与安全校验码、请求时的参数格式(如：带自定义参数等)、编码格式有关
		String mysign = AlipayNotify.GetMysign(params,key);
		String responseTxt = AlipayNotify.Verify(request.getParameter("notify_id"));
		String sign = request.getParameter("sign");
		
		//写日志记录（若要调试,请取消下面两行注释）
		//String sWord = "responseTxt=" + responseTxt + "\n notify_url_log:sign=" + sign + "&mysign=" + mysign + "\n notify回来的参数：" + AlipayBase.CreateLinkString(params);
		//AlipayBase.LogResult(sWord);
		
		//获取支付宝的通知返回参数
		alipayReturnParamater = new AlipayReturnParamater();
		//支付宝交易号
		String trade_no = request.getParameter("trade_no");
		alipayReturnParamater.setTrade_no(trade_no);
		//获取订单号
		String order_no = request.getParameter("out_trade_no");
		alipayReturnParamater.setOrder_no(order_no);
		//获取总金额
		String total_fee = request.getParameter("price");
		alipayReturnParamater.setTotal_fee(total_fee);
		//商品名称、订单名称
		String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
		alipayReturnParamater.setSubject(subject);
		//商品描述、订单备注、描述
		String body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setBody(body);
		//买家支付宝账号
		String buyer_email = request.getParameter("buyer_email");
		alipayReturnParamater.setBuyer_email(buyer_email);
		//收货人姓名
		String receive_name = new String(request.getParameter("receive_name").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_name(receive_name);
		//收货人地址
		String receive_address = new String(request.getParameter("receive_address").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_address(receive_address);
		//收货人邮编
		String receive_zip = new String(request.getParameter("receive_zip").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_zip(receive_zip);
		//收货人电话
		String receive_phone = new String(request.getParameter("receive_phone").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_phone(receive_phone);
		//收货人手机
		String receive_mobile = new String(request.getParameter("receive_mobile").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_mobile(receive_mobile);
		//交易状态
		String trade_status = request.getParameter("trade_status");
		alipayReturnParamater.setTrade_status(trade_status);
		
		//假设：
		//sOld_trade_status=0	表示订单未处理
		//sOld_trade_status=1	表示买家已在支付宝交易管理中产生了交易记录,但没有付款
		//sOld_trade_status=2	表示买家已在支付宝交易管理中产生了交易记录且付款成功,但卖家没有发货
		//sOld_trade_status=3	表示卖家已经发了货,但买家还没有做确认收货的操作
		//sOld_trade_status=4	表示买家已经确认收货,这笔交易完成
		
		//WAIT_BUYER_PAY : 表示买家已在支付宝交易管理中产生了交易记录,但没有付款
		//WAIT_SELLER_SEND_GOODS : 表示买家已在支付宝交易管理中产生了交易记录且付款成功,但卖家没有发货
		//WAIT_BUYER_CONFIRM_GOODS : 表示卖家已经发了货,但买家还没有做确认收货的操作
		//TRADE_FINISHED : 表示买家已经确认收货,这笔交易完成
		
		int sOld_trade_status = 0;//获取商户数据库中查询得到该笔交易当前的交易状态
		
		if(mysign.equals(sign) && responseTxt.equals("true")){//验证成功
			if(trade_status.equals("WAIT_BUYER_PAY")){
				//表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				//放入订单交易完成后的数据库更新程序代码，请务必保证response.Write出来的信息只有success
				//为了保证不被重复调用，或重复执行数据库更新程序，请判断该笔交易状态是否是订单未处理状态
				//注：该交易状态下，也可不做数据库更新程序，此时，建议把该状态的通知信息记录到商户通知日志数据库表中。
				if (sOld_trade_status == 0)
				{
				    //根据订单号更新订单，把订单状态处理成交易成功
				}
				orderState = "success";
			} else if (trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
				//表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
				//放入订单交易完成后的数据库更新程序代码，请务必保证response.Write出来的信息只有success
				//为了保证不被重复调用，或重复执行数据库更新程序，请判断该笔交易状态是否是WAIT_BUYER_PAY状态
				if (sOld_trade_status == 1 || sOld_trade_status == 0) {
				    //根据订单号更新订单，把订单状态处理成交易成功
				}
				orderState = "success";
			} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
				//表示卖家已经发了货，但买家还没有做确认收货的操作
				//放入订单交易完成后的数据库更新程序代码，请务必保证response.Write出来的信息只有success
				//为了保证不被重复调用，或重复执行数据库更新程序，请判断该笔交易状态是否是WAIT_SELLER_SEND_GOODS状态
				if (sOld_trade_status == 2) {
				    //根据订单号更新订单，把订单状态处理成交易成功
				}
				orderState = "success";
			} else if(trade_status.equals("TRADE_FINISHED")){
				//表示买家已经确认收货，这笔交易完成
				//放入订单交易完成后的数据库更新程序代码，请务必保证response.Write出来的信息只有success
				//为了保证不被重复调用，或重复执行数据库更新程序，请判断该笔交易状态是否是WAIT_BUYER_CONFIRM_GOODS状态
				if (sOld_trade_status == 3 || sOld_trade_status == 1 || sOld_trade_status == 0) {
					//当sOld_trade_status为3时，买家使用的是担保交易付款
					//当sOld_trade_status为1或0时，买家使用的是即时到帐付款
				    //根据订单号更新订单，把订单状态处理成交易成功
				}
				orderState = "success";
			} else {
				orderState = "success";
			}
		}else{//验证失败
			orderState = "fail";
		}
		
		returnPageURL = "/WEB-INF/jsp/application/client/payment/pay/notifyUrl.jsp";
		return "dispatcher";
	}
	
	/**
	 * 功能：付完款后跳转的页面(返回页)
	 * 版本：3.0
	 * 日期：2010-07-19
	 * 说明：
	 *  	该页面可在本机电脑测试
	 * 		该页面称作"返回页",是由支付宝服务器同步调用,可当作是支付完成后的提示信息页,如"您的某某某订单,多少金额已支付成功"
	 * 		可放入HTML等美化页面的代码和订单交易完成后的数据库更新程序代码
	 * 		付完款后跳转的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	 */
	@SuppressWarnings("unchecked")
	public String alipayReturnUrl() throws UnsupportedEncodingException{
		String key = AlipayConfig.key;
		//获取支付宝GET过来反馈信息
		Map params = new HashMap();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			params.put(name, valueStr);
		}
		
		//判断responsetTxt是否为ture，生成的签名结果mysign与获得的签名结果sign是否一致
		//responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		//mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String mysign = AlipayNotify.GetMysign(params,key);
		String responseTxt = AlipayNotify.Verify(request.getParameter("notify_id"));
		String sign = request.getParameter("sign");
		
		//写日志记录（若要调试，请取消下面两行注释）
		//String sWord = "responseTxt=" + responseTxt + "\n return_url_log:sign=" + sign + "&mysign=" + mysign + "\n return回来的参数：" + AlipayBase.CreateLinkString(params);
		//AlipayBase.LogResult(sWord);
		
		//获取支付宝的通知返回参数
		alipayReturnParamater = new AlipayReturnParamater();
		//支付宝交易号
		String trade_no = request.getParameter("trade_no");
		alipayReturnParamater.setTrade_no(trade_no);
		//获取订单号
		String order_no = request.getParameter("out_trade_no");
		alipayReturnParamater.setOrder_no(order_no);
		//获取总金额
		String total_fee = request.getParameter("price");
		alipayReturnParamater.setTotal_fee(total_fee);
		//商品名称、订单名称
		String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
		alipayReturnParamater.setSubject(subject);
		//商品描述、订单备注、描述
		String body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setBody(body);
		//买家支付宝账号
		String buyer_email = request.getParameter("buyer_email");
		alipayReturnParamater.setBuyer_email(buyer_email);
		//收货人姓名
		String receive_name = new String(request.getParameter("receive_name").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_name(receive_name);
		//收货人地址
		String receive_address = new String(request.getParameter("receive_address").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_address(receive_address);
		//收货人邮编
		String receive_zip = new String(request.getParameter("receive_zip").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_zip(receive_zip);
		//收货人电话
		String receive_phone = new String(request.getParameter("receive_phone").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_phone(receive_phone);
		//收货人手机
		String receive_mobile = new String(request.getParameter("receive_mobile").getBytes("ISO-8859-1"), "UTF-8");
		alipayReturnParamater.setReceive_mobile(receive_mobile);
		//交易状态
		String trade_status = request.getParameter("trade_status");
		alipayReturnParamater.setTrade_status(trade_status);
		
		int sOld_trade_status = 0;//获取商户数据库中查询得到该笔交易当前的交易状态
		
		//假设：
		//sOld_trade_status=0	表示订单未处理；
		//sOld_trade_status=1	表示买家已在支付宝交易管理中产生了交易记录，但没有付款
		//sOld_trade_status=2	表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
		//sOld_trade_status=3	表示卖家已经发了货，但买家还没有做确认收货的操作
		//sOld_trade_status=4	表示买家已经确认收货，这笔交易完成
		
		//WAIT_BUYER_PAY : 表示买家已在支付宝交易管理中产生了交易记录,但没有付款
		//WAIT_SELLER_SEND_GOODS : 表示买家已在支付宝交易管理中产生了交易记录且付款成功,但卖家没有发货
		//WAIT_BUYER_CONFIRM_GOODS : 表示卖家已经发了货,但买家还没有做确认收货的操作
		//TRADE_FINISHED : 表示买家已经确认收货,这笔交易完成
		
		if(mysign.equals(sign) && responseTxt.equals("true")){
			if(trade_status.equals("WAIT_SELLER_SEND_GOODS") || trade_status.equals("TRADE_FINISHED")){
				//为了保证不被重复调用,或重复执行数据库更新程序,请判断该笔交易状态是否是订单未处理状态
				if (sOld_trade_status < 1)
				{
				    //根据订单号更新订单,把订单状态处理成交易成功
				}
			}
			verifyStatus = "验证成功";
		}else{
			verifyStatus = "验证失败";
		}
		
		returnPageURL = "/WEB-INF/jsp/application/client/payment/pay/returnUrl.jsp";
		return "dispatcher";
	}
	
	//网站商品的展示地址，不允许加?id=123这类自定义参数
	public String alipayShowUrl(){
		
		returnPageURL = "/WEB-INF/jsp/application/client/payment/pay/ShowUrl.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.jxc.client.action.AlipayPaymentAction.java";
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

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public AlipayReturnParamater getAlipayReturnParamater() {
		return alipayReturnParamater;
	}

	public void setAlipayReturnParamater(AlipayReturnParamater alipayReturnParamater) {
		this.alipayReturnParamater = alipayReturnParamater;
	}

}
