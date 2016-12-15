package com.liusw.threadlocal;

public class ThreadLocalTest0 {
	private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {

		@Override
		protected Integer initialValue() {
			return 0;
		}

		@Override
		public Integer get() {
			return super.get();
		}

		@Override
		public void set(Integer value) {
			super.set(value);
		}

	};

	public void countUp() {
		for (int i = 0; i < 7; i++) {
			long id = Thread.currentThread().getId();
			int value = this.count.get();
			System.out.println("thread-" + id + ":count=" + value++);
			this.count.set(value);
		}
	}

	public static void main(String[] args) {
		ThreadLocalTest0 test0 = new ThreadLocalTest0();
		for (int i = 0; i < 9; i++) {
			Local local = new Local(test0);
			new Thread(local).start();
		}
	}

}

class Local implements Runnable {
	private ThreadLocalTest0 test0;

	public Local(ThreadLocalTest0 test0) {
		this.test0 = test0;
	}

	@Override
	public void run() {
		test0.countUp();
	}

}