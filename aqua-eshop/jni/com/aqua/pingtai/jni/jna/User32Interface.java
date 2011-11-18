package com.aqua.pingtai.jni.jna;

import com.sun.jna.Library;

public interface User32Interface extends Library {
	
	boolean LockWorkStation();
	
}
