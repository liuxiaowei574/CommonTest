package com.liusw;

public class ThreadTest extends Thread {
	private static String msg = "Datas:";

	public static void main(String[] args) {
		ThreadTest test = new ThreadTest();
		test.calc();
		System.out.println(test.msg);
	}

	private void calc() {
		msg += "=";
		start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			msg += i;
		}
//		System.out.println(msg);
	}

}