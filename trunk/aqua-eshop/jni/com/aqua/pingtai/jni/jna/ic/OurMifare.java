package com.aqua.pingtai.jni.jna.ic;

import com.sun.jna.Library;

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
