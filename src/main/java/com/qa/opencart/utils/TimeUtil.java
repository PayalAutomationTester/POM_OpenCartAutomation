package com.qa.opencart.utils;

public class TimeUtil {

	public final static int DEFAULT_TIMEOUT = 10;
	public final static long SMALL_TIMEOUT = 2000;
	public final static long MEDIUM_TIMEOUT = 5000;
	public final static long LARGE_TIMEOUT = 10000;
	public final static long SUPER_LARGE_TIMEOUT = 20000;

	public static void SmallWait() {
		try {
			Thread.sleep(SMALL_TIMEOUT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void MediumWait() {

		try {
			Thread.sleep(MEDIUM_TIMEOUT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void LargeWait() {
		try {
			Thread.sleep(LARGE_TIMEOUT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SuperLargeWait() {
		try {
			Thread.sleep(SUPER_LARGE_TIMEOUT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Wait(long timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
