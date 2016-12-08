package com.liusw.concurrent;

import java.util.Random;

public class Consumer implements Runnable {
	private Goods goods;

	public Consumer(Goods goods) {
		this.goods = goods;
	}

	public void run() {
		System.out.println("Consumer started.");
		while (true) {
			synchronized (goods) {
				if (Goods.count < 1) {
					System.out.println("Consumer-" + Thread.currentThread().getId() + " comes but found no goods.");
					try {
						goods.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				System.out.println("Consumer-" + Thread.currentThread().getId() + " consumed a product. Total goods: "
						+ (--Goods.count));
			}
			try {
				Thread.sleep(1000 * (new Random().nextInt(3) + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
