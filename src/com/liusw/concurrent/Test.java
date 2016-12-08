package com.liusw.concurrent;

public class Test {

	public static void main(String[] args) {
		Goods goods = new Goods();
		Consumer c = new Consumer(goods);
		Consumer c1 = new Consumer(goods);
		Producer p = new Producer(goods);
		new Thread(p).start();
		new Thread(c).start();
		new Thread(c1).start();
	}

}
