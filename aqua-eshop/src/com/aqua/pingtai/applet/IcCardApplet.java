package com.aqua.pingtai.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;

@SuppressWarnings("serial")
public class IcCardApplet extends Applet {
	
	public static final byte BLOCK0_EN = 0x01;//操作第0块(16个字节)
	public static final byte BLOCK1_EN = 0x02;//操作第1块(16个字节)
	public static final byte BLOCK2_EN = 0x04;//操作第2块(16个字节)
	
	public static final byte NEEDSERIAL = 0x08;//仅读指定序列号的卡
	public static final byte EXTERNKEY = 0x10;//用明码认证密码,产品开发完成后,建议把密码放到设备的只写区,然后用该区的密码后台认证,这样谁都不知道密码是多少,需要这方面支持请联系
	public static final byte NEEDHALT = 0x20;//读/写完卡后立即休眠该卡,相当于这张卡不在感应区,要相重新操作该卡必要拿开卡再放上去
	
	//以下控制字含义：读块0、块1、块2,仅读指定序列号的卡,需要每次指定密码
	public static byte Ctrlword = BLOCK0_EN + BLOCK1_EN + BLOCK2_EN + NEEDSERIAL+ EXTERNKEY ;
	
	//以下控制字含义：读块0、块1、块2,需要每次指定密码
	public static byte Ctrlword2 = BLOCK0_EN + BLOCK1_EN + BLOCK2_EN + EXTERNKEY ;
	
	//以下控制字含义：读块0、块2,可读任意卡,需要每次指定密码
	public static byte Ctrlword3 = BLOCK0_EN + BLOCK2_EN +  EXTERNKEY ;
	
	//以下控制字含义：读块0、块2,可读任意卡,启用芯片内部密码
	public static byte Ctrlword4 = BLOCK0_EN + BLOCK2_EN ;
	
	public static OurMifare ourMifare = null;
	
	static{
		ourMifare = (OurMifare) Native.loadLibrary("OUR_MIFARE", OurMifare.class);
	}
	
	@Override
	public void paint(Graphics graphics) {
		graphics.drawString("处理IC卡业务applet", 2, 20);
	}
	
	//蜂鸣
	public static void pcdbeep(int time){
		ourMifare.pcdbeep(time);
	}
	
	//写密码到只写区
	public static String writePassToOnlyRead(int quHao, int authMode, String pass){
		byte myareano = (byte) quHao;//指定区号为第8区
		byte authmode = (byte) authMode;//大于0表示用A密码认证,推荐用A密码认证
		byte[] piccoldkey = hexByteConver(pass);//密码
		int result = ourMifare.pcdwritekeytoe2(myareano, authmode, piccoldkey);
		if(result == 0){
			return "true";//写密码到只写区成功!
		}else{
			return getStatusInfo(result);
		}
	}
	
	//密码认证方式2					//		卡序列号			区号8			1
	public static String authMethodTwo(String cardSerial, int quHao, int authMode){
		byte[] mypiccserial = hexByteConverMany(cardSerial);//卡序列号
		byte myareano = (byte) quHao;//指定区号为第几区
		byte authmode = (byte) authMode;//大于0表示用A密码认证,推荐用A密码认证
		
		byte[] devno = new byte[4];
		int result = ourMifare.piccrequest(devno);
		if(result == 0){
			result = ourMifare.piccauthkey2(mypiccserial, myareano, authmode);
			if(result == 0){
				return "true";
			}else{
				return getStatusInfo(result);
			}
		}else{
			return getStatusInfo(result);
		}
	}
	
	//密码认证方式1
	public static String authMethodOne(String cardSerial, int quHao, int authMode, String pass){
		byte[] mypiccserial = hexByteConverMany(cardSerial);//卡序列号
		byte myareano = (byte) quHao;//指定区号为第8区
		byte authmode = (byte) authMode;//大于0表示用A密码认证,推荐用A密码认证
		byte[] piccoldkey = hexByteConver(pass);//密码
		
		int result = ourMifare.piccauthkey1(mypiccserial, myareano, authmode, piccoldkey);
		if(result == 0){
			return "true";
		}else{
			return getStatusInfo(result);
		}
	}
	
	//寻卡并选中指定序列号的IC卡,必须指定序列号
	public static String findCard(String cardSerial){
		byte[] mypiccserial = hexByteConverMany(cardSerial);//卡序列号
		int result = ourMifare.piccrequestex(mypiccserial);
		if(result == 0){
			return "true"+String.valueOf(mypiccserial[0])+String.valueOf(mypiccserial[1])+String.valueOf(mypiccserial[2])+String.valueOf(mypiccserial[3]);
		}else{
			return getStatusInfo(result);
		}
	}
	
	//读卡编号
	public static String readCardNumber(){
		byte[] devno = new byte[4];
		int result = ourMifare.piccrequest(devno);
		if(result == 0){
			//error return "true"+String.valueOf(devno[0])+String.valueOf(devno[1])+String.valueOf(devno[2])+String.valueOf(devno[3]);
			StringBuffer sb = new StringBuffer("true");
			sb.append(Integer.valueOf(byteToString(devno[0]), 16).toString()).append("-");//10进制
			sb.append(Integer.valueOf(byteToString(devno[1]), 16).toString()).append("-");//10进制
			sb.append(Integer.valueOf(byteToString(devno[2]), 16).toString()).append("-");//10进制
			sb.append(Integer.valueOf(byteToString(devno[3]), 16).toString());//10进制
			return sb.toString();
		}else{
			return getStatusInfo(result);
		}
	}
	
	//读设备编号
	public static String readDeviceNumber(){
		byte[] devno = new byte[4];
		int result = ourMifare.pcdgetdevicenumber(devno);
		if(result == 0){
			//error return "true"+String.valueOf(devno[0])+String.valueOf(devno[1])+String.valueOf(devno[2])+String.valueOf(devno[3]);
			StringBuffer sb = new StringBuffer("true");
			sb.append(Integer.valueOf(byteToString(devno[0]), 16).toString()).append("-");//10进制
			sb.append(Integer.valueOf(byteToString(devno[1]), 16).toString()).append("-");//10进制
			sb.append(Integer.valueOf(byteToString(devno[2]), 16).toString()).append("-");//10进制
			sb.append(Integer.valueOf(byteToString(devno[3]), 16).toString());//10进制
			return sb.toString();
		}else{
			return getStatusInfo(result);
		}
	}
	
	//修改卡区密码
	public static String updateCardPass(int controlWord, String cardSerial, int quHao, int authMode, String oldPass, String newPass){
		byte myCtrlword = (byte) controlWord;
		byte[] mypiccserial = hexByteConverMany(cardSerial);//卡序列号
		byte myareano = (byte) quHao;//指定区号为第8区
		byte authmode = (byte) authMode;//大于0表示用A密码认证,推荐用A密码认证
		byte[] piccoldkey = hexByteConver(oldPass);//旧密码
		byte[] piccnewkey = hexByteConver(newPass);//新密码
		
		int result = ourMifare.piccchangesinglekey(myCtrlword, mypiccserial, myareano, authmode, piccoldkey, piccnewkey);
		if(result == 0){
			return "true";
		}else{
			return getStatusInfo(result);
		}
	}
	
	//读卡
	public static String readCard(int controlWord, String cardSerial, int quHao, int authMode, String pass){
		byte myCtrlword = (byte) controlWord;
		byte[] mypiccserial = hexByteConverMany(cardSerial);//卡序列号
		byte myareano = (byte) quHao;//指定区号为第8区
		byte keyA1B0 = (byte) authMode;//大于0表示用A密码认证,推荐用A密码认证
		byte[] mypicckey = hexByteConver(pass);//密码
		byte[] mypiccdata = new byte[48];//卡数据缓冲
		
		int result = ourMifare.piccreadex(myCtrlword, mypiccserial, myareano, keyA1B0, mypicckey, mypiccdata);
		if(result == 0){
			StringBuffer sb = new StringBuffer();
			for (byte b : mypiccdata) {
				sb.append(byteToString(b));//16进制
			}
			return "true"+sb.toString();
		}else{
			return getStatusInfo(result);
		}
	}
	
	//写卡
	public static String writeCard(int controlWord, String cardSerial, int quHao, int authMode, String pass, String data){
		byte myCtrlword = (byte) controlWord;
		byte[] mypiccserial = hexByteConverMany(cardSerial);//卡序列号
		byte myareano = (byte) quHao;//指定区号为第8区
		byte authmode = (byte) authMode;//大于0表示用A密码认证，推荐用A密码认证
		byte[] mypicckey = hexByteConver(pass);//密码
		byte[] mypiccdata = hexByteConver(data);//卡数据缓冲
		
		int result = ourMifare.piccwriteex(myCtrlword, mypiccserial, myareano, authmode, mypicckey, mypiccdata);
		if(result == 0){
			return "true";
		}else{
			return getStatusInfo(result);
		}
	}
	
	//操作卡错误状态码明细
	public static String getStatusInfo(int status){
		String result = "";
		switch (status) {//13, 14, 23, 25, 26, 29
			case 0:
				result = "操作成功,读出的数据有效";
				break;
			case 1:
				result = "0~2块都没读出来,可能刷卡太块,但卡序列号已被读出来";
				break;
			case 2:
				result = "第0块已被读出,但1~2块读取失败,卡序列号已被读出来";
				break;
			case 3:
				result = "第0、1块已被读出,但2块读取失败,卡序列号已被读出来";
				break;
			case 8:
				result = "寻卡错误,根本就没有卡在感应区,*serial无效";
				break;
			case 9:
				result = "有多张卡在感应区,寻卡过程中防冲突失败,*serial无效";
				break;
			case 10:
				result = "该卡可能已被休眠,无法选中,但卡序列号已被读出,*serial数组中的数据有效";
				break;
			case 11:
				result = "密码装载失败";
				break;
			case 12:
				result = "密码认证失败";
				break;
			case 13:
				result = "读卡错误";
				break;
			case 14:
				result = "写卡错误";
				break;
			case 21:
				result = "本函数需要引用的动态库ICUSB.DLL不在当前目录下";
				break;
			case 22:
				result = "动态库或驱动程序异常,解决方法是退出程序,拔出IC卡读写器,重装驱动程序再插上IC卡读写器重试,或者重新拷贝动态库OUR_MIFARE.dll到正确的位置";
				break;
			case 23:
				result = "读卡器未插上或动态库或驱动程序异常";
				break;
			case 24:
				result = "操作超时,可能是电脑中毒导致USB帧传递调度缓慢,或者是IC卡读写器有问题,解决方法是重启电脑或重新拔插IC卡读写器";
				break;
			case 25:
				result = "发送字数不够";
				break;
			case 26:
				result = "发送的CRC错";
				break;
			case 27:
				result = "USB传输不稳定导致传输的字符不全,不需理会这个错误,因为基本上是不会出现这个错误的";
				break;
			case 28:
				result = "USB传输不稳定导致CRC校验错。不需理会这个错误，因为基本上是不会出现这个错误的";
				break;
			case 29:
				result = "函数输入参数格式错误,请仔细查看";
				break;
			default:
				result = "未知错误";
				break;
		}
		return result;
	}
	
	public interface OurMifare extends Library {
		
		//读卡
		int piccreadex(byte myctrlword, byte[] mypiccserial, byte myareano, 
				byte keyA1B0, byte[] picckey, byte[] piccdata);
		
		//写卡
		int piccwriteex(byte myctrlword, byte[] mypiccserial, byte myareano, 
				byte keyA1B0, byte[] picckey, byte[] piccdata);

		//修改区密码
		int piccchangesinglekey(byte myctrlword, byte[] mypiccserial, byte myareano, byte authmode, byte[] piccoldkey, byte[] piccnewkey);
		
		//蜂鸣
		int pcdbeep(long xms);
		
		//读设备号
		int pcdgetdevicenumber(byte[] devno);
		
		//读卡号
		int piccrequest(byte[] devno);
		
		//寻卡
		int piccrequestex(byte[] devno);
		
		//密码认证方式1	选中卡序列号		认证的区号		认证方式			区密码
		int piccauthkey1(byte[] serial, byte area, byte keyA1B0, byte[] picckey);
		
		//密码认证方式2	选中卡序列号		认证的区号		认证方式	
		int piccauthkey2(byte[] serial, byte area, byte keyA1B0);
		
		//将密码写入芯片内部保密性极高的只写区域,此函数写入密码仅仅是为了piccauthkey2函数的使用
		int pcdwritekeytoe2(byte area, byte keyA1B0, byte[] picckey);
		
		//读出一块的数据,也就是16个字节,必须在执行piccrequest或Piccrequestex函数,
		//接着执行piccauthkey1或 piccauthkey2函数,然后执行piccread才能成功读出一块的数据
		//1、block是IC卡的绝对块号,当需要读IC卡的第x区的第y块时,绝对块号必须是 block = x*4 + y。
		//2、piccdata是指向下标个数大于16的数组,作为返回16个字节的卡数据的缓存
		int piccread(byte block, byte[] piccdata);
		
		//写一块的数据,也就是16个字节,必须在执行piccrequest或Piccrequestex函数
		//接着执行piccauthkey1或 piccauthkey2函数,然后执行piccwrite才能成功写进一块的数据
		//1、block是IC卡的绝对块号,当需要读IC卡的第x区的第y块时,绝对块号必须是block = x * 4 + y。
		//2、*piccdata是指向下标个数大于16的数组,特别提醒在调用piccwrite之前,必须对piccdata数组明确赋值,千万不能写进不明数据,特别对是存放卡权限的第3块,更要明确写入,否则极有可能导致卡作废
		int piccwrite(byte block, byte[] piccdata);
		
		//休眠选中卡,卡一旦被休眠,将不能再次被选中、不能被读被写,必须得拿卡离开感应区,再次放卡才能再次选中和读写卡
		int picchalt();
	}

	//十六进制字符串转byte
	public static byte[] hexByteConver(String hexStr){
		int length = hexStr.length();
		int count = length/2;
		byte[] byte16 = new byte[count];
		for (int i = 0; i < count; i++) {
			int j = i*2+2;
			String temStr = hexStr.substring(j-2, j);
			byte16[i] = Integer.decode("0x"+temStr).byteValue(); 
		}
		return byte16;
	}
	
	//十六进制字符串转byte(包含-11类型) 例如:"-11-11aa"
	public static byte[] hexByteConverMany(String hexStr){
		List<String> strList = new ArrayList<String>();
		String temp = null;
		while (!hexStr.equals("")) {
			if(hexStr.startsWith("-")){
				temp = hexStr.substring(0, 3);
				hexStr = hexStr.substring(3);
				strList.add(temp);
			}else{
				temp = hexStr.substring(0, 2);
				hexStr = hexStr.substring(2);
				strList.add(temp);
			}
		}
		
		byte[] byteArr = new byte[strList.size()];
		String tempByte = null;
		for (int i=0; i<strList.size(); i++) {
			tempByte = strList.get(i);
			if(!tempByte.startsWith("-")){
				tempByte = "0x"+tempByte;
			}
			byteArr[i] = Integer.decode(tempByte).byteValue();
			System.out.println(byteArr[i]);
		}
		return byteArr;
	}
	
	//字节转字符串(转换后为16进制)
	public static String byteToString(byte b) {    
		byte high, low;    
		byte maskHigh = (byte)0xf0;    
		byte maskLow = 0x0f;    
		  
		high = (byte)((b & maskHigh) >> 4);    
		low = (byte)(b & maskLow);    
		  
		StringBuffer buf = new StringBuffer();    
		buf.append(findHex(high));    
		buf.append(findHex(low));    
		
		return buf.toString();//16进制
	}    

	public static char findHex(byte b) {    
		int t = new Byte(b).intValue();    
		t = t < 0 ? t + 16 : t;    
		  
		if ((0 <= t) &&(t <= 9)) {    
			return (char)(t + '0');    
		}    
		
		return (char)(t-10+'A');    
	}    
	
	//测试main
	public static void main(String[] args){
		hexByteConverMany("000000000000");
		
	}
	
}
