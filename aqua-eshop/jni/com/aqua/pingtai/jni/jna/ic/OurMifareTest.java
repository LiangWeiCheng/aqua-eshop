package com.aqua.pingtai.jni.jna.ic;

import java.io.UnsupportedEncodingException;

import com.sun.jna.Native;

public class OurMifareTest {
	
	public static final byte BLOCK0_EN = 0x01;
	public static final byte BLOCK1_EN = 0x02;
	public static final byte BLOCK2_EN = 0x04;
	public static final byte NEEDSERIAL = 0x08;
	public static final byte EXTERNKEY = 0x10;
	
	//以下控制字含义：读块0、块1、块2,仅读指定序列号的卡,需要每次指定密码
	public static byte Ctrlword = BLOCK0_EN + BLOCK1_EN + BLOCK2_EN + NEEDSERIAL+ EXTERNKEY ;
	//以下控制字含义：读块0、块1、块2,需要每次指定密码
	public static byte Ctrlword2 = BLOCK0_EN + BLOCK1_EN + BLOCK2_EN + EXTERNKEY ;
	//以下控制字含义：读块0、块2,可读任意卡,需要每次指定密码
	public static byte Ctrlword3 = BLOCK0_EN + BLOCK2_EN +  EXTERNKEY ;
	//以下控制字含义：读块0、块2,可读任意卡,启用芯片内部密码
	public static byte Ctrlword4 = BLOCK0_EN + BLOCK2_EN ;
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		OurMifare ourMifare = (OurMifare) Native.loadLibrary("OUR_MIFARE", OurMifare.class);
		
		//读卡
		//readCard(ourMifare);
		//beepCard(ourMifare);//蜂鸣
		
		//写卡
		//writeCard(ourMifare);
		//beepCard(ourMifare);//蜂鸣
		
		//修改卡区密码
		//updateCardPass(ourMifare);
		//beepCard(ourMifare);//蜂鸣
		
		//读设备编号
		//String devno = readDeviceNumber(ourMifare);
		//System.out.println(devno);
		//beepCard(ourMifare);//蜂鸣
		
		//读卡编号
		String readCardNo = readCardNumber(ourMifare);
		System.out.println("卡序号"+readCardNo);
		beepCard(ourMifare);//蜂鸣

		/*//寻卡
		byte[] devno = new byte[4];//卡序号01卡:-2-88-36-93
		devno[0] = 14;
		devno[1] = -84;
		devno[2] = -36;
		devno[3] = -93;
		String findCardNo = findCard(ourMifare, devno);
		System.out.println("卡序号:"+findCardNo);
		beepCard(ourMifare);//蜂鸣
		*/		
		
		//密码认证方式1
		//authMethodOne(ourMifare);
		//beepCard(ourMifare);//蜂鸣
		
		//密码认证方式2:必须先执行piccrequest或则piccrequestex
		//authMethodTwo(ourMifare);
		//beepCard(ourMifare);//蜂鸣
		
		//写密码到只写区
		//writePassToOnlyRead(ourMifare);
		//beepCard(ourMifare);//蜂鸣
		
		//休眠选中卡
		//ourMifare.picchalt();
		//beepCard(ourMifare);//蜂鸣
		
		/*String str = "qwertyuioplkjhgfdsazxcvbnm1234567890,./?><';\":[]}{\\|=-+_)(*&^%$#@!`";
		char[] strChar = str.toCharArray();
		for (char c : strChar) {
			String iHex = Integer.toHexString(c);//十六进制
			System.out.print(iHex+" ");
		}
		
		System.out.println();
		
		byte[] as = str.getBytes("UTF-8");
		for (byte b : as) {
			String iHex = Integer.toHexString(b);//十六进制
			System.out.print(iHex+" ");
		}*/
	}
	
	//写密码到只写区
	public static void writePassToOnlyRead(OurMifare ourMifare){
		byte myareano = 8;//指定区号为第8区
		byte authmode = 1;//大于0表示用A密码认证,推荐用A密码认证
		byte[] piccoldkey = new byte[6];//密码
		
		piccoldkey[0] = (byte) 0xaa;
		piccoldkey[1] = (byte) 0xaa;
		piccoldkey[2] = (byte) 0xaa;
		piccoldkey[3] = (byte) 0xaa;
		piccoldkey[4] = (byte) 0xaa;
		piccoldkey[5] = (byte) 0xaa;
		
		int result = ourMifare.pcdwritekeytoe2(myareano, authmode, piccoldkey);
		if(result == 0){
			System.out.println("写密码到只写区成功!");
		}else{
			getStatusInfo(result);
		}
	}
	
	//密码认证方式2
	public static void authMethodTwo(OurMifare ourMifare){
		byte[] mypiccserial = new byte[4];//卡序列号
		byte myareano = 8;//指定区号为第8区
		byte authmode = 1;//大于0表示用A密码认证,推荐用A密码认证
		
		mypiccserial[0] = -2;
		mypiccserial[1] = -88;
		mypiccserial[2] = -36;
		mypiccserial[3] = -93;
		
		byte[] devno = new byte[4];
		int result = ourMifare.piccrequest(devno);
		if(result == 0){
			result = ourMifare.piccauthkey2(mypiccserial, myareano, authmode);
			if(result == 0){
				System.out.println("密码认证方式2成功!");
			}else{
				getStatusInfo(result);
			}
		}else{
			getStatusInfo(result);
		}
	}
	
	//密码认证方式1
	public static void authMethodOne(OurMifare ourMifare){
		byte[] mypiccserial = new byte[4];//卡序列号
		byte myareano = 8;//指定区号为第8区
		byte authmode = 1;//大于0表示用A密码认证,推荐用A密码认证
		byte[] piccoldkey = new byte[6];//密码
		
		mypiccserial[0] = -2;
		mypiccserial[1] = -88;
		mypiccserial[2] = -36;
		mypiccserial[3] = -93;
		
		piccoldkey[0] = (byte) 0xff;
		piccoldkey[1] = (byte) 0xff;
		piccoldkey[2] = (byte) 0xff;
		piccoldkey[3] = (byte) 0xff;
		piccoldkey[4] = (byte) 0xff;
		piccoldkey[5] = (byte) 0xff;
		
		int result = ourMifare.piccauthkey1(mypiccserial, myareano, authmode, piccoldkey);
		if(result == 0){
			System.out.println("密码认证方式1成功!");
		}else{
			getStatusInfo(result);
		}
	}
	
	//寻卡并选中指定序列号的IC卡,必须指定序列号
	public static String findCard(OurMifare ourMifare, byte[] devno){
		int result = ourMifare.piccrequestex(devno);
		if(result == 0){
			return String.valueOf(devno[0])+String.valueOf(devno[1])+String.valueOf(devno[2])+String.valueOf(devno[3]);
		}else{
			getStatusInfo(result);
		}
		return null;
	}
	
	//读卡编号
	public static String readCardNumber(OurMifare ourMifare){
		byte[] devno = new byte[4];
		int result = ourMifare.piccrequest(devno);
		if(result == 0){
			System.out.println(String.valueOf(devno[0])+String.valueOf(devno[1])+String.valueOf(devno[2])+String.valueOf(devno[3]));
			return byteToString(devno[0])+"-"+byteToString(devno[1])+"-"+byteToString(devno[2])+"-"+byteToString(devno[3]);
		}else{
			getStatusInfo(result);
		}
		return null;
	}
	
	//读设备编号
	public static String readDeviceNumber(OurMifare ourMifare){
		byte[] devno = new byte[4];
		int result = ourMifare.pcdgetdevicenumber(devno);
		if(result == 0){
			//System.out.println(String.valueOf(devno[0])+String.valueOf(devno[1])+String.valueOf(devno[2])+String.valueOf(devno[3]));
			return byteToString(devno[0])+"-"+byteToString(devno[1])+"-"+byteToString(devno[2])+"-"+byteToString(devno[3]);
		}else{
			getStatusInfo(result);
		}
		return null;
	}
	
	//修改卡区密码
	public static int updateCardPass(OurMifare ourMifare){
		byte[] mypiccserial = new byte[4];//卡序列号
		byte myareano = 8;//指定区号为第8区
		byte authmode = 1;//大于0表示用A密码认证,推荐用A密码认证
		byte[] piccoldkey = new byte[6];//密码
		byte[] piccnewkey = new byte[6];//卡数据缓冲
		//旧密码
		piccoldkey[0] = (byte) 0xff;
		piccoldkey[1] = (byte) 0xff;
		piccoldkey[2] = (byte) 0xff;
		piccoldkey[3] = (byte) 0xff;
		piccoldkey[4] = (byte) 0xff;
		piccoldkey[5] = (byte) 0xff;
		//新密码
		piccnewkey[0] = (byte) 0xff;
		piccnewkey[1] = (byte) 0xff;
		piccnewkey[2] = (byte) 0xff;
		piccnewkey[3] = (byte) 0xff;
		piccnewkey[4] = (byte) 0xff;
		piccnewkey[5] = (byte) 0xff;
		
		int result = ourMifare.piccchangesinglekey(Ctrlword2, mypiccserial, myareano, authmode, piccoldkey, piccnewkey);
		if(result == 0){
			System.out.println("修改卡密码成功!");
		}else{
			getStatusInfo(result);
		}
		
		return result;
	}
	
	//读卡
	public static int readCard(OurMifare ourMifare){
		byte[] mypiccserial = new byte[4];//卡序列号
		byte myareano = 8;//指定区号为第8区
		byte keyA1B0 = 1;//大于0表示用A密码认证,推荐用A密码认证
		byte[] mypicckey = new byte[6];//密码
		byte[] mypiccdata = new byte[48];//卡数据缓冲
		//指定密码
		mypicckey[0] = (byte) 0xff;
		mypicckey[1] = (byte) 0xff;
		mypicckey[2] = (byte) 0xff;
		mypicckey[3] = (byte) 0xff;
		mypicckey[4] = (byte) 0xff;
		mypicckey[5] = (byte) 0xff;
		
		int result = ourMifare.piccreadex(Ctrlword2, mypiccserial, myareano, keyA1B0, mypicckey, mypiccdata);
		if(result == 0){
			String temp = null;
			for (byte b : mypiccdata) {
				temp = String.valueOf(b);
				if(temp.length()==1){
					temp = "0"+temp;
				}
				System.out.print(temp);
			}
			System.out.println();
		}else{
			getStatusInfo(result);
		}
		
		return result;
	}
	
	//写卡
	public static int writeCard(OurMifare ourMifare){
		byte[] mypiccserial = new byte[4];//卡序列号
		byte myareano = 8;//指定区号为第8区
		byte authmode = 1;//大于0表示用A密码认证，推荐用A密码认证
		byte[] mypicckey = new byte[6];//密码
		byte[] mypiccdata = new byte[48]; //卡数据缓冲
		//指定密码
		mypicckey[0] = (byte) 0xff;
		mypicckey[1] = (byte) 0xff;
		mypicckey[2] = (byte) 0xff;
		mypicckey[3] = (byte) 0xff;
		mypicckey[4] = (byte) 0xff;
		mypicckey[5] = (byte) 0xff;
		//指定写卡数据
		for (int i = 0; i < 48; i++)
		{
			mypiccdata[i] = (byte) i;
		}
		
		int result = ourMifare.piccwriteex(Ctrlword2, mypiccserial, myareano, authmode, mypicckey, mypiccdata);
		if(result == 0){
			System.out.println("写卡成功");
		}else{
			getStatusInfo(result);
		}
		
		return result;
	}
	
	//蜂鸣
	public static int beepCard(OurMifare ourMifare){
		long millisecond = 100;
		int result = ourMifare.pcdbeep(millisecond);
		//System.out.println("蜂鸣返回状态:"+result);
		//getStatusInfo(result);
		return result;
	}
	
	//操作卡错误状态码明细
	public static void getStatusInfo(int status){
		switch (status) {//13, 14, 23, 25, 26, 29
			case 0:
				System.out.println("操作成功,读出的数据有效");
				break;
			case 1:
				System.out.println("0~2块都没读出来,可能刷卡太块,但卡序列号已被读出来");
				break;
			case 2:
				System.out.println("第0块已被读出,但1~2块读取失败,卡序列号已被读出来");
				break;
			case 3:
				System.out.println("第0、1块已被读出,但2块读取失败,卡序列号已被读出来");
				break;
			case 8:
				System.out.println("寻卡错误,根本就没有卡在感应区,*serial无效");
				break;
			case 9:
				System.out.println("有多张卡在感应区,寻卡过程中防冲突失败,*serial无效");
				break;
			case 10:
				System.out.println("该卡可能已被休眠,无法选中,但卡序列号已被读出,*serial数组中的数据有效");
				break;
			case 11:
				System.out.println("密码装载失败");
				break;
			case 12:
				System.out.println("密码认证失败");
				break;
			case 13:
				System.out.println("读卡错误");
				break;
			case 14:
				System.out.println("写卡错误");
				break;
			case 21:
				System.out.println("本函数需要引用的动态库ICUSB.DLL不在当前目录下");
				break;
			case 22:
				System.out.println("动态库或驱动程序异常,解决方法是退出程序,拔出IC卡读写器,重装驱动程序再插上IC卡读写器重试,或者重新拷贝动态库OUR_MIFARE.dll到正确的位置");
				break;
			case 23:
				System.out.println("读卡器未插上或动态库或驱动程序异常");
				break;
			case 24:
				System.out.println("操作超时,可能是电脑中毒导致USB帧传递调度缓慢,或者是IC卡读写器有问题,解决方法是重启电脑或重新拔插IC卡读写器");
				break;
			case 25:
				System.out.println("发送字数不够");
				break;
			case 26:
				System.out.println("发送的CRC错");
				break;
			case 27:
				System.out.println("USB传输不稳定导致传输的字符不全,不需理会这个错误,因为基本上是不会出现这个错误的");
				break;
			case 28:
				System.out.println("USB传输不稳定导致CRC校验错。不需理会这个错误，因为基本上是不会出现这个错误的");
				break;
			case 29:
				System.out.println("函数输入参数格式错误,请仔细查看");
				break;
			default:
				System.out.println("未知错误");
				break;
		}
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
		  
		String hex16 = buf.toString();//16进制
		//return hex16;
		return Integer.valueOf(hex16, 16).toString();//10进制     
	}    

	public static char findHex(byte b) {    
		int t = new Byte(b).intValue();    
		t = t < 0 ? t + 16 : t;    
		  
		if ((0 <= t) &&(t <= 9)) {    
			return (char)(t + '0');    
		}    
		
		return (char)(t-10+'A');    
	}    

}
