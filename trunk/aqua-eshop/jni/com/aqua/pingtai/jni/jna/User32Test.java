package com.aqua.pingtai.jni.jna;

import com.sun.jna.Native;

public class User32Test {

	public static void main(String[] args) {
		User32Interface user32Interface = (User32Interface) Native.loadLibrary("user32", User32Interface.class);
		user32Interface.LockWorkStation();
	}

}
