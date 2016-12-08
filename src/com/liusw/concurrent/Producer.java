package com.liusw.concurrent;

import java.util.Random;

public class Producer implements Runnable {
	private Goods goods;

	public Producer(Goods goods) {
		this.goods = goods;
	}

	public void run() {
		System.out.println("Producer started.");
		while (true) {
			synchronized (goods) {
				System.out.println(
						"Producer-" + Thread.currentThread().getId() + " working. Total goods: " + (++Goods.count));
				goods.notifyAll();
			}
			try {
				Thread.sleep(1000 * (new Random().nextInt(3) + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
