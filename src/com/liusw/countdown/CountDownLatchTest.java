package com.liusw.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Work类（运动员）
 * 
 * @author Administrator
 *
 */
class Work implements Runnable {
	private int id;
	private CountDownLatch beginSignal;
	private CountDownLatch endSignal;

	public Work(int id, CountDownLatch begin, CountDownLatch end) {
		this.id = id;
		this.beginSignal = begin;
		this.endSignal = end;
	}

	@Override
	public void run() {
		try {
			beginSignal.await();
			System.out.println("起跑...");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("work" + id + "到达终点");
			endSignal.countDown();
			System.out.println("work" + id + "继续干其他事情");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * Main类（终点统计仪器）：
 * 
 * @author Administrator
 *
 */
public class CountDownLatchTest {
	public static void main(String[] args) {
		// 其实1对应每个运动员，8对应计数器，当8个运动countDown完便停止技术
		CountDownLatch begSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(8);

		for (int i = 0; i < 8; i++) {
			new Thread(new Work(i, begSignal, endSignal)).start();
		}

		try {
			begSignal.countDown();// 统一起跑
			endSignal.await();// 等待运动员到达终点
			System.out.println("结果发送到汇报成绩的系统");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}