package com.aqua.alipay.util.fahuo;

import java.util.*;

import com.aqua.alipay.util.fahuo.AlipayBase;


/**
 *类名：alipay_service
 *功能：支付宝外部服务接口控制
 *详细：该页面是请求参数核心处理文件，不需要修改
 *版本：3.0
 *修改日期：2010-08-4
 *说明：
  以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
  该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@SuppressWarnings("unchecked")
public class AlipayService {
	
	/**
	 * 功能：构造请求URL（GET方式请求）
	 * @param partner 合作身份者ID
     * @param trade_no 支付宝交易号。它是登陆支付宝网站在交易管理中查询得到，一般以8位日期开头的纯数字（如：20100419XXXXXXXXXX）
     * @param logistics_name 物流公司名称
     * @param invoice_no 物流发货单号
     * @param transport_type 物流发货时的运输类型，三个值可选：POST（平邮）、EXPRESS（快递）、EMS（EMS）
     * @param seller_ip 卖家本地电脑IP地址
	 * @param input_charset 字符编码格式 目前支持 GBK 或 utf-8
	 * @param sign_type 加密方式 不需修改
	 * @param key 安全校验码
	 * @return 请求URL
	 */
	public static String CreateUrl(String partner,
			String trade_no,
			String logistics_name,
			String invoice_no,
			String transport_type,
			String seller_ip,
            String input_charset,
            String key,
            String sign_type){
		Map sPara = new HashMap();
		sPara.put("service","send_goods_confirm_by_platform");
		sPara.put("partner", partner);
		sPara.put("trade_no", trade_no);
		sPara.put("logistics_name", logistics_name);
		sPara.put("invoice_no", invoice_no);
		sPara.put("transport_type", transport_type);
		sPara.put("seller_ip", seller_ip);
		sPara.put("_input_charset", input_charset);
		
		String strUrl = "https://www.alipay.com/cooperate/gateway.do?";
		Map sParaNew = AlipayBase.ParaFilter(sPara); //除去数组中的空值和签名参数
		String mysign = AlipayBase.BuildMysign(sParaNew, key);//生成签名结果
		//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String arg = AlipayBase.CreateLinkString_urlencode(sParaNew, input_charset);
		//得到请求URL
		strUrl = strUrl + arg + "sign=" + mysign + "&sign_type=" + sign_type;
		
		return strUrl;
	}
	
	/**
	 * 功能：构造Post表单提交HTML（POST方式请求）
	 * @param partner 合作身份者ID
     * @param trade_no 支付宝交易号。它是登陆支付宝网站在交易管理中查询得到，一般以8位日期开头的纯数字（如：20100419XXXXXXXXXX）
     * @param logistics_name 物流公司名称
     * @param invoice_no 物流发货单号
     * @param transport_type 物流发货时的运输类型，三个值可选：POST（平邮）、EXPRESS（快递）、EMS（EMS）
     * @param seller_ip 卖家本地电脑IP地址
	 * @param input_charset 字符编码格式 目前支持 GBK 或 utf-8
	 * @param sign_type 加密方式 不需修改
	 * @param key 安全校验码
	 * @return 表单提交HTML文本
	 */
	public static String BuildPostform(String partner,
			String trade_no,
			String logistics_name,
			String invoice_no,
			String transport_type,
			String seller_ip,
            String input_charset,
            String key,
            String sign_type){
		Map sPara = new HashMap();
		sPara.put("service","send_goods_confirm_by_platform");
		sPara.put("partner", partner);
		sPara.put("trade_no", trade_no);
		sPara.put("logistics_name", logistics_name);
		sPara.put("invoice_no", invoice_no);
		sPara.put("transport_type", transport_type);
		sPara.put("seller_ip", seller_ip);
		sPara.put("_input_charset", input_charset);
		
		Map sParaNew = AlipayBase.ParaFilter(sPara); //除去数组中的空值和签名参数
		String mysign = AlipayBase.BuildMysign(sParaNew, key);//生成签名结果
		
		StringBuffer sbHtml = new StringBuffer();
		List keys = new ArrayList(sParaNew.keySet());
		String gateway = "https://www.alipay.com/cooperate/gateway.do?";
		
		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + gateway + "_input_charset=" + input_charset + "\" method=\"post\">");
		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sParaNew.get(name);
			
			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}
        sbHtml.append("<input type=\"hidden\" name=\"sign\" value=\"" + mysign + "\"/>");
        sbHtml.append("<input type=\"hidden\" name=\"sign_type\" value=\"" + sign_type + "\"/></form>");
        
        sbHtml.append("<input type=\"button\" name=\"v_action\" value=\"支付宝发货\" onClick=\"document.forms['alipaysubmit'].submit();\">");
		
		return sbHtml.toString();
	}
}
